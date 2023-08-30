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
 * 审核规则表
 *
 * @author admin
 * @since 2023-08-30
 */
@Getter
@Setter
@ToString
@TableName("audit_rule")
@Schema(name = "审核规则表")
public class AuditRulePO extends BasePO {

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 审核规则ID
    */
    @TableField("audit_rule_id")
    private Long auditRuleId;

    /**
    * 审核类型
    */
    @TableField("audit_type")
    private String auditType;

    /**
    * 规则状态（0-停用、1-启用）
    */
    @TableField("status")
    private Integer status;

    /**
    * 备注
    */
    @TableField("note")
    private String note;

    @TableField("create_time")
    private LocalDateTime createTime;
}
