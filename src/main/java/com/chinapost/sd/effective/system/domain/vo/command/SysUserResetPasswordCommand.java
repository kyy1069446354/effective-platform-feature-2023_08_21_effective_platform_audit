package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户信息表UpdateCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "用户信息表UpdateCommand")
public class SysUserResetPasswordCommand {

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    private Long userId;

    /**
    * 密码
    */
    @Schema(description = "密码")
    private String password;

}
