package com.chinapost.sd.effective.tool.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinapost.sd.boot.infrastructure.base.BasePO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成业务表
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@TableName("sys_gen_table")
@Schema(description = "代码生成业务表")
public class GenTablePO extends BasePO {

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 表名称
    */
    @TableField("name")
    private String name;

    /**
    * 表全名：dbname.schemName.tableName
    */
    @TableField("full_name")
    private String fullName;

    /**
    * 表注释
    */
    @TableField("comment")
    private String comment;

    /**
    * 实体类名称
    */
    @TableField("class_name")
    private String className;

    /**
    * 生成的菜单名
    */
    @TableField("menu_name")
    private String menuName;

    /**
    * 所属模块名
    */
    @TableField("module_name")
    private String moduleName;

    /**
    * 对应业务名
    */
    @TableField("business_name")
    private String businessName;

    /**
    * 对应实体的中文名，如用户，部门等
    */
    @TableField("entity_name")
    private String entityName;

    /**
    * 关联子表id
    */
    @TableField("sub_table_id")
    private Long subTableId;

    /**
    * 子表关联的外键名
    */
    @TableField("sub_table_fk_name")
    private String subTableFkName;

    /**
    * 父菜单id
    */
    @TableField("parent_menu_id")
    private Long parentMenuId;

    /**
     * 使用的模板（1 crud单表操作 2 tree树表操作 3 主子表操作）
     */
    @TableField("tpl_category")
    private Integer tplCategory;

    /**
    * 生成包路径
    */
    @TableField("package_name")
    private String packageName;

    /**
    * 生成功能作者
    */
    @TableField("author")
    private String author;

    /**
    * 其它生成选项
    */
    @TableField("options")
    private String options;

}
