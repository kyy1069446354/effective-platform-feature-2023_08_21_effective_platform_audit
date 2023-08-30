package com.chinapost.sd.effective.system.domain.dto;

import java.time.LocalDateTime;

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
public class AuditRule{

    /**
    * 主键
    */
    private Long id;

    /**
    * 审核规则ID
    */
    private Long auditRuleId;

    /**
    * 审核类型
    */
    private String auditType;

    /**
    * 规则状态（0-停用、1-启用）
    */
    private Integer status;

    /**
    * 备注
    */
    private String note;

    private LocalDateTime createTime;
}
