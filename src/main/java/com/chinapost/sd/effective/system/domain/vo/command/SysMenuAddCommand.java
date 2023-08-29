package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 菜单权限表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "菜单权限表AddCommand")
public class SysMenuAddCommand{

    /**
    * 菜单名称
    */
    @Schema(description = "菜单名称")
    private String name;

    /**
    * 父菜单ID
    */
    @Schema(description = "父菜单ID")
    private Long parentId;

    /**
    * 显示顺序
    */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
    * 路由地址
    */
    @Schema(description = "路由地址")
    private String path;

    /**
    * 组件路径
    */
    @Schema(description = "组件路径")
    private String component;

    /**
    * 路由参数
    */
    @Schema(description = "路由参数")
    private String query;

    /**
    * 是否为外链（1是 0否）
    */
    @Schema(description = "是否为外链（1是 0否）")
    private Boolean isExternal;

    /**
    * 是否缓存（1缓存 0不缓存）
    */
    @Schema(description = "是否缓存（1缓存 0不缓存）")
    private Boolean isCache;

    /**
    * 菜单类型（M=1目录 C=2菜单 F=3按钮）
    */
    @Schema(description = "菜单类型（M=1目录 C=2菜单 F=3按钮）")
    private Integer menuType;

    /**
    * 菜单状态（1显示 0隐藏）
    */
    @Schema(description = "菜单状态（1显示 0隐藏）")
    private Boolean isVisible;

    /**
    * 菜单状态（1启用 0停用）
    */
    @Schema(description = "菜单状态（1启用 0停用）")
    private Integer status;

    /**
    * 权限标识
    */
    @Schema(description = "权限标识")
    private String perms;

    /**
    * 菜单图标
    */
    @Schema(description = "菜单图标")
    private String icon;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;

    /**
    * 逻辑删除
    */
    @Schema(description = "逻辑删除")
    private Boolean deleted;
}
