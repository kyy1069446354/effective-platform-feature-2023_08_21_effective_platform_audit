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
 * @since 2023-08-29
 */
@Getter
@Setter
@ToString
@TableName("sys_auditor")
@Schema(name = "审核员表")
public class SysAuditorPO extends BasePO {

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 审核人ID
    */
    @TableField("auditor_id")
    private Long auditorId;

    /**
    * 审核人姓名
    */
    @TableField("auditor_name")
    private String auditorName;

    /**
    * 审核类型（0-vpn、1-机器）
    */
    @TableField("audit_type")
    private Integer auditType;

    /**
    * 审核顺序
    */
    @TableField("audit_order")
    private Integer auditOrder;
}
