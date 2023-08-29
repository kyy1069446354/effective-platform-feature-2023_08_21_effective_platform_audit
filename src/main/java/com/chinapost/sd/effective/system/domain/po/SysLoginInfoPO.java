package com.chinapost.sd.effective.system.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinapost.sd.boot.infrastructure.base.BasePO;
import io.swagger.v3.oas.annotations.media.Schema;
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
@TableName("sys_login_info")
@Schema(description = "系统访问记录")
public class SysLoginInfoPO extends BasePO {

    /**
    * 访问ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 用户账号
    */
    @TableField("username")
    private String username;

    /**
    * 登录IP地址
    */
    @TableField("ip_address")
    private String ipAddress;

    /**
    * 登录地点
    */
    @TableField("login_location")
    private String loginLocation;

    /**
    * 浏览器类型
    */
    @TableField("browser")
    private String browser;

    /**
    * 操作系统
    */
    @TableField("operation_system")
    private String operationSystem;

    /**
    * 登录状态（1成功 0失败）
    */
    @TableField("status")
    private Integer status;

    /**
    * 提示消息
    */
    @TableField("msg")
    private String msg;

    /**
    * 访问时间
    */
    @TableField("login_time")
    private LocalDateTime loginTime;

    /**
    * 逻辑删除
    */
    @TableField("deleted")
    private Boolean deleted;
}
