package com.chinapost.sd.effective.system.domain.vo.query;

import java.time.LocalDateTime;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核员表PageQuery
 *
 * @author admin
 * @since 2023-08-29
 */
@Getter
@Setter
@ToString
@Schema(name = "审核员表PageQuery")
public class SysAuditorPageQuery extends BasePageReq{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;

    /**
    * 审核人ID
    */
    @Schema(description = "审核人ID")
    private Long auditorId;

    /**
    * 审核人姓名
    */
    @Schema(description = "审核人姓名")
    private String auditorName;

    /**
    * 审核类型（0-vpn、1-机器）
    */
    @Schema(description = "审核类型（0-vpn、1-机器）")
    private Integer auditType;

    /**
    * 审核顺序
    */
    @Schema(description = "审核顺序")
    private Integer auditOrder;
}
