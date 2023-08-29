package com.chinapost.sd.effective.tool.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成业务表字段
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成业务表字段")
public class GenTableColumnVO{

    /**
    * 编号
    */
    @Schema(description = "编号")
    private Long id;

    /**
    * 归属表编号
    */
    @Schema(description = "归属表编号")
    private String tableId;

    /**
    * 列名称
    */
    @Schema(description = "列名称")
    private String name;

    /**
    * 列描述
    */
    @Schema(description = "列描述")
    private String comment;

    /**
    * SQL type from java.sql.Types
    */
    @Schema(description = "SQL type from java.sql.Types")
    private Integer dataType;

    /**
    * Data source dependent type name
    */
    @Schema(description = "Data source dependent type name")
    private String dataTypeName;

    /**
    * JAVA类型
    */
    @Schema(description = "JAVA类型")
    private String classType;

    /**
    * JAVA字段名
    */
    @Schema(description = "JAVA字段名")
    private String className;

    /**
    * 是否主键（1是）
    */
    @Schema(description = "是否主键（1是）")
    private Boolean isPrimary;

    /**
    * 是否必填
    */
    @Schema(description = "是否必填")
    private Boolean isNotNull;

    /**
    * 是否查询字段（1是）
    */
    @Schema(description = "是否查询字段（1是）")
    private Boolean isQuery;

    /**
    * 查询方式（等于、不等于、大于、小于、范围）
    */
    @Schema(description = "查询方式（等于、不等于、大于、小于、范围）")
    private String queryType;

    /**
    * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
    */
    @Schema(description = "显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）")
    private String htmlType;

    /**
    * 字典类型
    */
    @Schema(description = "字典类型")
    private String dictType;

    /**
    * 排序
    */
    @Schema(description = "排序")
    private Integer orderNum;
}
