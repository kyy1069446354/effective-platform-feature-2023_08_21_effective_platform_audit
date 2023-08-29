package com.chinapost.sd.effective.system.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 流程节点表
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
public class SysProcessNodes{

    /**
    * 主键
    */
    private Long id;

    /**
    * 申请工单ID
    */
    private Long requestFormId;

    /**
    * 申请员工ID
    */
    private Long requesterId;

    /**
    * 申请员工姓名
    */
    private String requesterName;

    /**
    * 该节点所处审核顺序
    */
    private Integer auditOrder;

    /**
    * 该节点审核人ID
    */
    private Long auditorId;

    /**
    * 该节点审核人姓名
    */
    private String auditorName;

    /**
    * 审核类型（0-vpn、1-机器）
    */
    private Integer auditType;

    /**
    * 该节点审核结果，0-待审核，1-已通过，2-已拒绝
    */
    private Integer auditResult;

    /**
    * 该节点审核人意见
    */
    private String auditAdvise;

    /**
    * 该节点审核人审核日期
    */
    private LocalDateTime auditDate;
}
