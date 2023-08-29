package com.chinapost.sd.effective.system.domain.po;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinapost.sd.boot.infrastructure.base.BasePO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核员表
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@TableName("sys_auditor")
@Schema(name = "审核员表")
public class SysAuditorPO extends BasePO {

    /**
    * 申请工单ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 申请员工ID
    */
    @TableField("requester_id")
    private Long requesterId;

    /**
    * 申请人姓名
    */
    @TableField("requester_name")
    private String requesterName;

    /**
    * 系统名称
    */
    @TableField("system_name")
    private String systemName;

    /**
    * 系统负责人
    */
    @TableField("system_head")
    private String systemHead;

    /**
    * 系统负责人联系方式
    */
    @TableField("system_head_phone_number")
    private Long systemHeadPhoneNumber;

    /**
    * 系统使用人员
    */
    @TableField("system_user")
    private String systemUser;

    /**
    * 业务访问方式
    */
    @TableField("business_access_method")
    private String businessAccessMethod;

    /**
    * 待安装的操作系统版本
    */
    @TableField("os_version")
    private String osVersion;

    /**
    * 待安装的中间件版本
    */
    @TableField("middleware_user")
    private String middlewareUser;

    /**
    * 待安装的数据库版本
    */
    @TableField("database_version")
    private String databaseVersion;

    /**
    * 资源需求情况
    */
    @TableField("resource_demand")
    private String resourceDemand;

    /**
    * CPU数量
    */
    @TableField("cpu_num")
    private Integer cpuNum;

    /**
    * 内存数量(G)
    */
    @TableField("memory_num")
    private Integer memoryNum;

    /**
    * 磁盘空间(G)
    */
    @TableField("disk_space")
    private Integer diskSpace;

    /**
    * 申请日期
    */
    @TableField("request_date")
    private LocalDateTime requestDate;

    /**
    * 申请原因
    */
    @TableField("request_reason")
    private String requestReason;

    /**
    * 审核结果，0-审核中，1-已通过，2-已拒绝，3-已撤销
    */
    @TableField("audit_result")
    private Integer auditResult;

    /**
    * 流程结束时间
    */
    @TableField("finish_time")
    private LocalDateTime finishTime;
}
