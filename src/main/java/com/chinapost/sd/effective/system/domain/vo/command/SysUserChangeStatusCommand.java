package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户信息表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "修改用户状态Command")
public class SysUserChangeStatusCommand {
    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    private Long userId;


    /**
    * 帐号状态（1正常 2停用 3冻结）
    */
    @Schema(description = "帐号状态（1正常 2停用 3冻结）")
    private Integer status;
}
