package com.chinapost.sd.effective.tool.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 代码生成业务表
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成业务表")
public class GenTableVO{

    /**
    * 编号
    */
    @Schema(description = "编号")
    private Long id;

    /**
    * 表名称
    */
    @Schema(description = "表名称")
    private String name;

    /**
    * 表描述
    */
    @Schema(description = "表描述")
    private String comment;

    /**
    * 关联子表的表名
    */
    @Schema(description = "关联子表的表名")
    private String subTableName;

    /**
    * 子表关联的外键名
    */
    @Schema(description = "子表关联的外键名")
    private String subTableFkName;

    /**
    * 实体类名称
    */
    @Schema(description = "实体类名称")
    private String className;

    /**
     * 对应实体的中文名，如用户，部门等
     */
    @Schema(description = "对应实体的中文名，如用户，部门等")
    private String entityName;

    /**
    * 使用的模板（crud单表操作 tree树表操作）
    */
    @Schema(description = "使用的模板（crud单表操作 tree树表操作）")
    private String tplCategory;

    /**
    * 生成包路径
    */
    @Schema(description = "生成包路径")
    private String packageName;

    /**
    * 生成模块名
    */
    @Schema(description = "生成模块名")
    private String moduleName;

    /**
    * 生成业务名
    */
    @Schema(description = "生成业务名")
    private String businessName;

    /**
     * 生成的菜单名
     */
    @Schema(description = "menu_name")
    private String menuName;

    /**
     * 父菜单id
     */
    @Schema(description = "parent_menu_id")
    private Long parentMenuId;

    /**
    * 生成功能作者
    */
    @Schema(description = "生成功能作者")
    private String author;

    /**
    * 其它生成选项
    */
    @Schema(description = "其它生成选项")
    private String options;


    private List<GenTableColumnVO> columns;
}
