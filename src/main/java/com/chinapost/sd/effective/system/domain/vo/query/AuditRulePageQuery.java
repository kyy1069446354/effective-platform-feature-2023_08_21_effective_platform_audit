package com.chinapost.sd.effective.system.domain.vo.query;

import java.time.LocalDateTime;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核规则表PageQuery
 *
 * @author admin
 * @since 2023-08-30
 */
@Getter
@Setter
@ToString
@Schema(name = "审核规则表PageQuery")
public class AuditRulePageQuery extends BasePageReq{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;

    /**
    * 审核规则ID
    */
    @Schema(description = "审核规则ID")
    private Long auditRuleId;

    /**
    * 审核类型
    */
    @Schema(description = "审核类型")
    private String auditType;

    /**
    * 规则状态（0-停用、1-启用）
    */
    @Schema(description = "规则状态（0-停用、1-启用）")
    private Integer status;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String note;
}
