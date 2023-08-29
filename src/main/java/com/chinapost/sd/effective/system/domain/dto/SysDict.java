package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 字典类型表
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
public class SysDict{

    /**
     * 字典主键
     */
    private Long id;
    /**
     * 字典英文名
     */
    private String code;

    /**
     * 字典中文名
     */
    private String name;

    /**
     * 状态（1正常 0停用）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
