package com.chinapost.sd.effective.system.constant;

/**
 * 用户状态
 * 100.登录成功，101.用户不存在，102.用户已停用，103.密码错误，104.验证码错误，105.验证码已失效，199.未知错误
 * 200，登出成功
 * 300。注册成功
 * @author tangyang
 */
public enum LoginStatusEnum {
    /**
     * status of user
     */
    LOGIN_SUCCESS(100, "登录成功"),
    USER_NOT_EXITS(101, "用户不存在"),
    USER_IS_DISABLE(102, "用户已停用"),
    PASSWORD_ERROR(103, "密码错误"),
    CAPTCHA_ERROR(104, "验证码错误"),
    CAPTCHA_EXPIRED(105, "验证码已失效"),

    LOGIN_FAIL(199, "未知错误"),
    LOGOUT(200, "退出成功"),
    REGISTER(300, "注册"),

        ;

    private final int value;
    private final String msg;

    LoginStatusEnum(int status, String msg) {
        this.value = status;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public String description() {
        return msg;
    }


}
