package com.chinapost.sd.effective.system.domain.po;

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
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@TableName("sys_menu")
@Schema(description = "菜单权限表")
public class SysMenuPO extends BasePO {

    /**
    * 菜单ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 菜单名称
    */
    @TableField("name")
    private String name;

    /**
    * 父菜单ID
    */
    @TableField("parent_id")
    private Long parentId;

    /**
    * 显示顺序
    */
    @TableField("order_num")
    private Integer orderNum;

    /**
    * 路由地址
    */
    @TableField("path")
    private String path;

    /**
    * 组件路径
    */
    @TableField("component")
    private String component;

    /**
    * 路由参数
    */
    @TableField("query")
    private String query;

    /**
    * 是否为外链（1是 0否）
    */
    @TableField("is_external")
    private Boolean isExternal;

    /**
    * 是否缓存（1缓存 0不缓存）
    */
    @TableField("is_cache")
    private Boolean isCache;

    /**
    * 菜单类型（M=1目录 C=2菜单 F=3按钮）
    */
    @TableField("menu_type")
    private Integer menuType;

    /**
    * 菜单状态（1显示 0隐藏）
    */
    @TableField("is_visible")
    private Boolean isVisible;

    /**
    * 菜单状态（1启用 0停用）
    */
    @TableField("status")
    private Integer status;

    /**
    * 权限标识
    */
    @TableField("perms")
    private String perms;

    /**
    * 菜单图标
    */
    @TableField("icon")
    private String icon;

    /**
    * 备注
    */
    @TableField("remark")
    private String remark;

    /**
    * 逻辑删除
    */
    @TableField("deleted")
    private Boolean deleted;
}
