package com.chinapost.sd.effective.system.domain.dto;

import java.time.LocalDateTime;

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
public class SysAuditor{

    /**
    * 主键
    */
    private Long id;

    /**
    * 审核人ID
    */
    private Long auditorId;

    /**
    * 审核人姓名
    */
    private String auditorName;

    /**
    * 审核类型（0-vpn、1-机器）
    */
    private Integer auditType;

    /**
    * 审核顺序
    */
    private Integer auditOrder;
}
