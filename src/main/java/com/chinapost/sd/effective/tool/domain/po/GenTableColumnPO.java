package com.chinapost.sd.effective.tool.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinapost.sd.boot.infrastructure.base.BasePO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

/**
 * 代码生成业务表字段
 *
 * @author tangyang
 * @since 2023-07-14
 */
@ToString
@TableName("sys_gen_table_column")
@Schema(description = "代码生成业务表字段")
public class GenTableColumnPO extends BasePO {

    /**
     * 编号
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 归属表编号
     */
    @TableField("table_id")
    private String tableId;

    /**
     * 列名称
     */
    @TableField("name")
    private String name;

    /**
     * 列描述
     */
    @TableField("comment")
    private String comment;

    /**
     * SQL type from java.sql.Types
     */
    @TableField("data_type")
    private Integer dataType;

    /**
     * Data source dependent type name
     */
    @TableField("data_type_name")
    private String dataTypeName;

    /**
     * 对应字段的JAVA类型
     */
    @TableField("class_type")
    private String classType;

    /**
     * JAVA字段名
     */
    @TableField("class_name")
    private String className;

    /**
     * 是否主键（1是）
     */
    @TableField("is_primary")
    private Boolean isPrimary;

    /**
     * 是否必填
     */
    @TableField("is_not_null")
    private Boolean isNotNull;

    /**
     * 是否查询字段（1是）
     */
    @TableField("is_query")
    private Boolean isQuery;

    /**
     * 查询方式（等于、不等于、大于、小于、范围）
     */
    @TableField("query_type")
    private String queryType;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @TableField("html_type")
    private String htmlType;

    /**
     * 字典类型
     */
    @TableField("dict_type")
    private String dictType;

    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public void setDataTypeName(String dataTypeName) {
        this.dataTypeName = dataTypeName;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setIsPrimary(Boolean isPrimary) {
        this.isPrimary = isPrimary;
    }

    public void setIsNotNull(Boolean isNotNull) {
        this.isNotNull = isNotNull;
    }

    public void setIsQuery(Boolean isQuery) {
        this.isQuery = isQuery;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    public void setHtmlType(String htmlType) {
        this.htmlType = htmlType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getId() {
        return this.id;
    }

    public String getTableId() {
        return this.tableId;
    }

    public String getName() {
        return this.name;
    }

    public String getComment() {
        return this.comment;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public String getClassType() {
        return this.classType;
    }

    public String getClassName() {
        return this.className;
    }

    public Boolean getIsPrimary() {
        return this.isPrimary;
    }

    public Boolean getIsNotNull() {
        return this.isNotNull;
    }

    public Boolean getIsQuery() {
        return this.isQuery;
    }

    public String getQueryType() {
        return this.queryType;
    }

    public String getHtmlType() {
        return this.htmlType;
    }

    public String getDictType() {
        return this.dictType;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
}
