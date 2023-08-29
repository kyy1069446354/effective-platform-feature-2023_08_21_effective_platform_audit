package com.chinapost.sd.effective.common.login.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 登录请求
 *
 * @author tangyang
 * @since 2023/7/7
 */
@Getter
@Setter
@ToString
@Schema(description = "登录请求")
public class LoginRequest {
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 在缓存中查找验证码的唯一标识
     */
    private String uuid;
}
