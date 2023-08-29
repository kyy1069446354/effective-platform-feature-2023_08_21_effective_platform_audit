package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统访问记录AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "系统访问记录AddCommand")
public class SysLoginInfoAddCommand{

    /**
    * 用户账号
    */
    @Schema(description = "用户账号")
    private String username;

    /**
    * 登录IP地址
    */
    @Schema(description = "登录IP地址")
    private String ipAddress;

    /**
    * 登录地点
    */
    @Schema(description = "登录地点")
    private String loginLocation;

    /**
    * 浏览器类型
    */
    @Schema(description = "浏览器类型")
    private String browser;

    /**
    * 操作系统
    */
    @Schema(description = "操作系统")
    private String operationSystem;

    /**
    * 登录状态（1成功 0失败）
    */
    @Schema(description = "登录状态（1成功 0失败）")
    private Integer status;

    /**
    * 提示消息
    */
    @Schema(description = "提示消息")
    private String msg;

    /**
    * 访问时间
    */
    @Schema(description = "访问时间")
    private LocalDateTime loginTime;

    /**
    * 逻辑删除
    */
    @Schema(description = "逻辑删除")
    private Boolean deleted;
}
