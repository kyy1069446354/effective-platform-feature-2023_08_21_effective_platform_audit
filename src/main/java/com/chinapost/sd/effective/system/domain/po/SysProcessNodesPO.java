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
 * 流程节点表
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@TableName("sys_process_nodes")
@Schema(name = "流程节点表")
public class SysProcessNodesPO extends BasePO {

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 申请工单ID
    */
    @TableField("request_form_id")
    private Long requestFormId;

    /**
    * 申请员工ID
    */
    @TableField("requester_id")
    private Long requesterId;

    /**
    * 申请员工姓名
    */
    @TableField("requester_name")
    private String requesterName;

    /**
    * 该节点所处审核顺序
    */
    @TableField("audit_order")
    private Integer auditOrder;

    /**
    * 该节点审核人ID
    */
    @TableField("auditor_id")
    private Long auditorId;

    /**
    * 该节点审核人姓名
    */
    @TableField("auditor_name")
    private String auditorName;

    /**
    * 审核类型（0-vpn、1-机器）
    */
    @TableField("audit_type")
    private Integer auditType;

    /**
    * 该节点审核结果，0-待审核，1-已通过，2-已拒绝
    */
    @TableField("audit_result")
    private Integer auditResult;

    /**
    * 该节点审核人意见
    */
    @TableField("audit_advise")
    private String auditAdvise;

    /**
    * 该节点审核人审核日期
    */
    @TableField("audit_date")
    private LocalDateTime auditDate;
}
