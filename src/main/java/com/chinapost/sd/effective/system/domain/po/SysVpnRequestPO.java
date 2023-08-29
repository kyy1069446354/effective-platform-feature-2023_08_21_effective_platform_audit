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
 * vpn审核工单表
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@TableName("sys_vpn_request")
@Schema(name = "vpn审核工单表")
public class SysVpnRequestPO extends BasePO {

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
    * 申请单位名称
    */
    @TableField("requester_department")
    private String requesterDepartment;

    /**
    * 申请人联系电话
    */
    @TableField("requester_phone_number")
    private Long requesterPhoneNumber;

    /**
    * 申请人邮箱
    */
    @TableField("requester_email")
    private String requesterEmail;

    /**
    * 申请时间
    */
    @TableField("request_date")
    private LocalDateTime requestDate;

    /**
    * 申请原因
    */
    @TableField("request_reason")
    private String requestReason;

    /**
    * 安全管理员审核意见
    */
    @TableField("safeguard_suggest")
    private String safeguardSuggest;

    /**
    * 省中心安全管理员审核意见
    */
    @TableField("province_safeguard_suggest")
    private String provinceSafeguardSuggest;

    /**
    * 申请增加的生产系统名称及地址
    */
    @TableField("request_system")
    private String requestSystem;

    /**
    * 备注
    */
    @TableField("note")
    private String note;

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
