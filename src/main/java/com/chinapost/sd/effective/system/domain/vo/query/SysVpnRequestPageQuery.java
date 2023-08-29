package com.chinapost.sd.effective.system.domain.vo.query;

import java.time.LocalDateTime;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * vpn审核工单表PageQuery
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@Schema(name = "vpn审核工单表PageQuery")
public class SysVpnRequestPageQuery extends BasePageReq{

    /**
    * 申请工单ID
    */
    @Schema(description = "申请工单ID")
    private Long id;

    /**
    * 申请员工ID
    */
    @Schema(description = "申请员工ID")
    private Long requesterId;

    /**
    * 申请人姓名
    */
    @Schema(description = "申请人姓名")
    private String requesterName;

    /**
    * 申请单位名称
    */
    @Schema(description = "申请单位名称")
    private String requesterDepartment;

    /**
    * 申请人联系电话
    */
    @Schema(description = "申请人联系电话")
    private Long requesterPhoneNumber;

    /**
    * 申请人邮箱
    */
    @Schema(description = "申请人邮箱")
    private String requesterEmail;

    /**
    * 申请时间
    */
    @Schema(description = "申请时间")
    private LocalDateTime requestDate;

    /**
    * 申请原因
    */
    @Schema(description = "申请原因")
    private String requestReason;

    /**
    * 安全管理员审核意见
    */
    @Schema(description = "安全管理员审核意见")
    private String safeguardSuggest;

    /**
    * 省中心安全管理员审核意见
    */
    @Schema(description = "省中心安全管理员审核意见")
    private String provinceSafeguardSuggest;

    /**
    * 申请增加的生产系统名称及地址
    */
    @Schema(description = "申请增加的生产系统名称及地址")
    private String requestSystem;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String note;

    /**
    * 审核结果，0-审核中，1-已通过，2-已拒绝，3-已撤销
    */
    @Schema(description = "审核结果，0-审核中，1-已通过，2-已拒绝，3-已撤销")
    private Integer auditResult;

    /**
    * 流程结束时间
    */
    @Schema(description = "流程结束时间")
    private LocalDateTime finishTime;
}
