package com.chinapost.sd.effective.system.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 机器审核工单表
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
public class SysMachineRequest{

    /**
    * 申请工单ID
    */
    private Long id;

    /**
    * 申请员工ID
    */
    private Long requesterId;

    /**
    * 申请人姓名
    */
    private String requesterName;

    /**
    * 系统名称
    */
    private String systemName;

    /**
    * 系统负责人
    */
    private String systemHead;

    /**
    * 系统负责人联系方式
    */
    private Long systemHeadPhoneNumber;

    /**
    * 系统使用人员
    */
    private String systemUser;

    /**
    * 业务访问方式
    */
    private String businessAccessMethod;

    /**
    * 待安装的操作系统版本
    */
    private String osVersion;

    /**
    * 待安装的中间件版本
    */
    private String middlewareUser;

    /**
    * 待安装的数据库版本
    */
    private String databaseVersion;

    /**
    * 资源需求情况
    */
    private String resourceDemand;

    /**
    * CPU数量
    */
    private Integer cpuNum;

    /**
    * 内存数量(G)
    */
    private Integer memoryNum;

    /**
    * 磁盘空间(G)
    */
    private Integer diskSpace;

    /**
    * 申请日期
    */
    private LocalDateTime requestDate;

    /**
    * 申请原因
    */
    private String requestReason;

    /**
    * 审核结果，0-审核中，1-已通过，2-已拒绝，3-已撤销
    */
    private Integer auditResult;

    /**
    * 流程结束时间
    */
    private LocalDateTime finishTime;
}
