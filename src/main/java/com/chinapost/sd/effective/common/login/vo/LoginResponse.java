package com.chinapost.sd.effective.common.login.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/7
 */
@Getter
@Setter
@ToString
@Schema(description = "登录vo")
public class LoginResponse {
    private String token;
}
