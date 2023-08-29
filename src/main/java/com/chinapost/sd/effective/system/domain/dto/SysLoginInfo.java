package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统访问记录
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
public class SysLoginInfo{

    /**
    * 访问ID
    */
    private Long id;

    /**
    * 用户账号
    */
    private String username;

    /**
    * 登录IP地址
    */
    private String ipAddress;

    /**
    * 登录地点
    */
    private String loginLocation;

    /**
    * 浏览器类型
    */
    private String browser;

    /**
    * 操作系统
    */
    private String operationSystem;

    /**
    * 登录状态（1成功 0失败）
    */
    private Integer status;

    /**
    * 提示消息
    */
    private String msg;

    /**
    * 访问时间
    */
    private LocalDateTime loginTime;

    /**
    * 逻辑删除
    */
    private Boolean deleted;
}
