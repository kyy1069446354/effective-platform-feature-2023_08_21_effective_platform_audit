package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 菜单权限表DeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "菜单权限表DeleteCommand")
public class SysMenuDeleteCommand{

    /**
    * 菜单ID
    */
    @Schema(description = "菜单ID")
    private Long id;
}
