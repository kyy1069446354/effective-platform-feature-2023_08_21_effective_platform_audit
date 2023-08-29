package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色和菜单关联表DeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色和菜单关联表DeleteCommand")
public class SysRoleMenuDeleteCommand{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
