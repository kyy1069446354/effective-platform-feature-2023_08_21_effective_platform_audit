package com.chinapost.sd.effective.tool.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.tool.convert.GenTableConvert;
import com.chinapost.sd.effective.tool.domain.vo.DbTableVO;
import com.chinapost.sd.effective.tool.domain.vo.GenTableVO;
import com.chinapost.sd.effective.tool.domain.vo.PageGenTableVO;
import com.chinapost.sd.effective.tool.domain.vo.command.GenTableBatchDeleteCommand;
import com.chinapost.sd.effective.tool.domain.vo.command.GenTableSynDbCommand;
import com.chinapost.sd.effective.tool.domain.vo.command.ImportTableCommand;
import com.chinapost.sd.effective.tool.domain.vo.query.DbTablePageQuery;
import com.chinapost.sd.effective.tool.domain.vo.query.GenTableGetQuery;
import com.chinapost.sd.effective.tool.domain.vo.query.GenTablePageQuery;
import com.chinapost.sd.effective.tool.mapper.GenTableMapper;
import com.chinapost.sd.effective.tool.service.GenTableService;
import com.chinapost.sd.boot.domain.GenTable;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * GenTableController类
 *
 * @author tangyang
 * @since 2023/7/14
 */
@RestController
@RequestMapping("/tool/gen")
@Tag(name = "GenTable表API", description = "GenTable表API")
public class GenTableController {
    private final GenTableConvert genTableConvert = GenTableConvert.INSTANCE;
    @Autowired
    private GenTableService genTableService;
    @Resource
    private GenTableMapper genTableMapper;

    @Operation(summary = "分页列表", description = "分页列表")
    @PostMapping("/page")
    public ResponseResult<Page<PageGenTableVO>> page(@RequestBody GenTablePageQuery query) {
        Page<PageGenTableVO> genTablePage = genTableMapper.selectByPage(Page.of(query.getPageNum(), query.getPageSize()), query);
        return ResponseResult.success(genTablePage);
    }


    /**
     * 查询数未导入的表
     */
    @PostMapping("/pageDbTable")
    public ResponseResult<Page<DbTableVO>> pageDbTable(@RequestBody DbTablePageQuery query) {
        return ResponseResult.success(genTableService.queryDbTablePage(query));
    }

    /**
     * get
     */
    @PostMapping(value = "/get")
    public ResponseResult<GenTableVO> get(@RequestBody GenTableGetQuery query) {
        GenTable table = genTableService.getById(query.getId());
        return ResponseResult.success(genTableConvert.dto2Vo(table));
    }


    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public ResponseResult<Void> importTable(@RequestBody ImportTableCommand command) {
        genTableService.importTable(command.getTables());
        return ResponseResult.success();
    }

    /**
     * 更新表
     */
    @PostMapping("/update")
    public ResponseResult<Void> update(@RequestBody GenTableVO genTableVo) {
        genTableService.update(genTableConvert.vo2Dto(genTableVo));
        return ResponseResult.success();
    }

    /**
     * 删除代码生成
     */
    @PostMapping("/batchDelete")
    public ResponseResult<Void> batchDelete(@RequestBody GenTableBatchDeleteCommand command) {
        genTableService.batchDelete(command.getIds());
        return ResponseResult.success();
    }

    /**
     * 同步数据库
     */
    @PostMapping("/synchDb")
    public ResponseResult<Void> synchDb(@RequestBody GenTableSynDbCommand command) {
        genTableService.syncDb(command.getId());
        return ResponseResult.success();
    }
}
