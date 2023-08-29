package com.chinapost.sd.effective.common.login.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/7
 */
@Getter
@Setter
@ToString
@Schema(description = "登录用户的信息")
public class LoginUserInfoVO {
    /**
     * 用户ID
     */
    private Long userId;

    private String username;

    /**
     * 部门ID
     */
    private Long deptId;


    private Set<String> roles;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    /**
     * 头像
     */
    private String avatar;
}
