package com.chinapost.sd.effective.tool.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.tool.convert.GenTableColumnConvert;
import com.chinapost.sd.effective.tool.convert.GenTableConvert;
import com.chinapost.sd.effective.tool.domain.po.GenTableColumnPO;
import com.chinapost.sd.effective.tool.domain.po.GenTablePO;
import com.chinapost.sd.effective.tool.domain.vo.DbTableVO;
import com.chinapost.sd.effective.tool.domain.vo.query.DbTablePageQuery;
import com.chinapost.sd.effective.tool.domain.vo.query.GenTablePageQuery;
import com.chinapost.sd.effective.tool.mapper.GenTableColumnMapper;
import com.chinapost.sd.effective.tool.mapper.GenTableMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import com.chinapost.sd.boot.constant.TplCategoryConstant;
import com.chinapost.sd.boot.domain.GenColumn;
import com.chinapost.sd.boot.domain.GenTable;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import com.chinapost.sd.boot.utils.ColumnUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * GenTableService类
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Service
public class GenTableService {
    private final GenTableConvert genTableConvert = GenTableConvert.INSTANCE;
    private final GenTableColumnConvert genTableColumnConvert = GenTableColumnConvert.INSTANCE;

    @Resource
    private GenTableMapper genTableMapper;
    @Resource
    private GenTableColumnMapper genTableColumnMapper;
    @Autowired
    private DataSource dataSource;

    public GenTable getById(Long id){
        GenTablePO tablePo = genTableMapper.selectById(id);
        List<GenTableColumnPO> columnPoList = genTableColumnMapper.selectByTableId(id);
        List<GenColumn> columnList = genTableColumnConvert.poList2DtoList(columnPoList);
        GenTable genTable = genTableConvert.po2Dto(tablePo);
        genTable.setBeanName(StrUtil.lowerFirst(genTable.getClassName()));
        genTable.setColumns(columnList);
        genTable.setDate(DateUtil.today());
        genTable.setMenuId(IdGenerator.generate());
        return genTable;
    }


    @Transactional(rollbackFor = Exception.class)
    public void add(GenTable genTable){
        GenTablePO genTablePo = genTableConvert.dto2Po(genTable);
        List<GenTableColumnPO> columnPoList = genTableColumnConvert.dtoList2PoList(genTable.getColumns());
        genTableMapper.save(genTablePo);
        genTableColumnMapper.save(columnPoList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(GenTable genTable){
        GenTablePO genTablePo = genTableConvert.dto2Po(genTable);
        genTableMapper.update(genTablePo);
        List<GenTableColumnPO> genTableColumnPoList = genTableColumnConvert.dtoList2PoList(genTable.getColumns());
        genTableColumnMapper.update(genTableColumnPoList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        if (CollectionUtils.isEmpty(ids)){
            return;
        }
        genTableMapper.deleteBatchIds(ids);
        genTableColumnMapper.deleteByTableIds(ids);
    }

    public Page<DbTableVO> queryDbTablePage(DbTablePageQuery query){
        List<DbTableVO> tableList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            String dbName = connection.getCatalog();
            String schema = connection.getSchema();
            String tableNamePattern = null;
            if (StringUtils.isNotBlank(query.getName())){
                tableNamePattern = "%" + query.getName() + "%";
            }
            ResultSet tableResultSet = metaData.getTables(dbName, schema, tableNamePattern, new String[]{"TABLE"});
            while (tableResultSet.next()){
                DbTableVO table = new DbTableVO();
                String dbname = tableResultSet.getString(1);
                String schemaName = tableResultSet.getString(2);
                String tableName = tableResultSet.getString(3);
                String tableComment = tableResultSet.getString(5);
                table.setName(tableName);
                table.setComment(tableComment);
                table.setFullName(dbName + "." + schemaName + "." + tableName);
                tableList.add(table);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        List<GenTablePO> genTableList = genTableMapper.selectByList(new GenTablePageQuery());
        Set<String> genTableNameSet = genTableList.stream().map(GenTablePO::getName).collect(Collectors.toSet());
        tableList = tableList.stream()
                .filter(table -> !genTableNameSet.contains(table.getName()))
                .sorted(Comparator.comparing(DbTableVO::getName))
                .collect(Collectors.toList());
        Page<DbTableVO> page = new Page<>();
        page.setTotal(tableList.size());
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        if (tableList.isEmpty()){
            page.setRecords(tableList);
            return page;
        }
        long recordsStart = (page.getCurrent() - 1) * page.getSize();
        long recordsEnd = page.getCurrent() * page.getSize()  ;
        if (recordsEnd > tableList.size()){
            recordsEnd = tableList.size();
        }
        page.setRecords(tableList.subList((int)recordsStart, (int)recordsEnd));
        return page;
    }

    @Transactional(rollbackFor = Exception.class)
    public void importTable(List<String> tables) {
        if (CollectionUtils.isEmpty(tables)){
            return;
        }
        List<GenTable> tableList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            DatabaseMetaData metaData = connection.getMetaData();
            String dbName = connection.getCatalog();
            String schema = connection.getSchema();
            for (String tableName : tables) {
                ResultSet tableResultSet = metaData.getTables(dbName, schema, tableName, new String[]{"TABLE"});
                while (tableResultSet.next()){
                    String dbname = tableResultSet.getString(1);
                    String schemaName = tableResultSet.getString(2);
                    String tableNameRes = tableResultSet.getString(3);
                    String tableComment = tableResultSet.getString(5);

                    GenTable table = new GenTable();
                    table.setId(IdGenerator.generate());
                    table.setFullName(dbName + "." + schemaName + "." + tableName);
                    table.setName(tableName);
                    table.setComment(tableComment);
                    // sys_config -> sysConfig
                    String name = StrUtil.toCamelCase(tableName);
                    table.setBeanName(name);
                    // sysConfig -> SysConfig
                    table.setClassName(StrUtil.upperFirst(name));
                    table.setEntityName(tableComment);
                    table.setAuthor(AuthenticationUtils.getLoginUser().getUsername());
                    table.setDate(DateUtil.today());
                    table.setTplCategory(TplCategoryConstant.CRUD);
                    table.setMenuName(tableComment);
                    tableList.add(table);
                }

                for (GenTable table : tableList) {
                    Set<String> primaryKeys = new HashSet<>();
                    ResultSet primaryKeysResultSet = metaData.getPrimaryKeys(dbName, null, tableName);
                    while (primaryKeysResultSet.next()){
                        String columnName = primaryKeysResultSet.getString(4);
                        primaryKeys.add(columnName);
                    }
                    ResultSet columnsResultSet = metaData.getColumns(dbName, null, tableName, null);
                    List<GenColumn> columns = new ArrayList<>();
                    while (columnsResultSet.next()){
                        String columnName = columnsResultSet.getString(4);
                        if (ColumnUtil.isCommonColumn(columnName)){
                            continue;
                        }
                        int jdbcType = columnsResultSet.getInt(5);
                        String typeName = columnsResultSet.getString(6);
                        int nullable = columnsResultSet.getInt(11);
                        String columnComment = columnsResultSet.getString(12);
                        String isNullable = columnsResultSet.getString(18);

                        GenColumn column = new GenColumn();
                        column.setId(IdGenerator.generate());
                        column.setTableId(table.getId());
                        column.setName(columnName);
                        column.setDataType(jdbcType);
                        column.setDataTypeName(typeName);
                        column.setComment(columnComment);
                        Class<?> javaType = ColumnUtil.getJavaType(jdbcType);
                        if (javaType == null){
                            throw new RuntimeException("没有对应的javaType, jdbcType=" + jdbcType + ", jdbcTypeName = " + typeName);
                        }
                        column.setClassType(ColumnUtil.getJavaType(jdbcType).getSimpleName());
                        column.setClassName(StrUtil.toCamelCase(columnName));
                        /**
                         * YES --- if the column can include NULLs
                         * NO --- if the column cannot include NULLs
                         * empty string --- if the nullability for the column is unknown
                         */
                        column.setIsNotNull(isNullable.equals("NO"));
                        column.setIsQuery(true);
                        columns.add(column);
                        column.setIsPrimary(primaryKeys.contains(columnName));
                    }

                    table.setColumns(columns);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        List<GenTablePO> tablePoList = genTableConvert.dtoList2PoList(tableList);
        genTableMapper.save(tablePoList);

        List<GenColumn> columnList = tableList.stream().flatMap(table -> table.getColumns().stream()).collect(Collectors.toList());
        List<GenTableColumnPO> columnPoList = genTableColumnConvert.dtoList2PoList(columnList);
        genTableColumnMapper.save(columnPoList);
    }

    public void syncDb(Long tableId) {
        throw new RuntimeException("待实现");
    }

    public List<GenTable> getByIds(List<Long> tableIds) {
        if (CollectionUtils.isEmpty(tableIds)){
            return Collections.emptyList();
        }
        List<GenTable> tables = new ArrayList<>();
        for (Long tableId : tableIds) {
            GenTable table = getById(tableId);
            tables.add(table);
        }
        return tables;
    }
}
