package com.chinapost.sd.effective.system.domain.vo;

import java.time.LocalDateTime;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核规则表
 *
 * @author admin
 * @since 2023-08-29
 */
@Getter
@Setter
@ToString
@Schema(name = "审核规则表")
public class AuditRuleVO{

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
    * 审核类型（0-vpn、1-机器）
    */
    @Schema(description = "审核类型（0-vpn、1-机器）")
    private Integer auditType;

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

    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
