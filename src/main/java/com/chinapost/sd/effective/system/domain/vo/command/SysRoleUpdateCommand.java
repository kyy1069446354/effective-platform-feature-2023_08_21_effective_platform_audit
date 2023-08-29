package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * <p>
 * 角色信息表UpdateCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色信息表UpdateCommand")
public class SysRoleUpdateCommand{

    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long id;

    /**
    * 角色名称
    */
    @Schema(description = "角色名称")
    private String name;

    /**
    * 角色权限字符串
    */
    @Schema(description = "角色权限字符串")
    private String code;

    /**
    * 显示顺序
    */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
    * 数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）
    */
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）")
    private Integer dataScope;

    /**
    * 角色所拥有的部门数据权限
    */
    @Schema(description = "角色所拥有的部门数据权限")
    private String deptIdSet;

    /**
    * 角色状态（1正常 0停用）
    */
    @Schema(description = "角色状态（1正常 0停用）")
    private Integer status;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;


    /**
     * 有权限的菜单id
     */
    @Schema(description = "有权限的菜单id")
    private Set<Long> menuIds;
}
