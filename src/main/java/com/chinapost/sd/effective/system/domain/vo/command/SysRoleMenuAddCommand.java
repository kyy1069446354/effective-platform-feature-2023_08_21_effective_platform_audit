package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色和菜单关联表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色和菜单关联表AddCommand")
public class SysRoleMenuAddCommand{

    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long roleId;

    /**
    * 菜单ID
    */
    @Schema(description = "菜单ID")
    private Long menuId;
}
