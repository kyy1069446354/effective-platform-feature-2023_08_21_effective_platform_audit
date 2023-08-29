package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户和角色关联表
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Getter
@Setter
@ToString
public class SysUserRole{

    /**
    * 主键
    */
    private Long id;

    /**
    * 用户ID
    */
    private Long userId;

    /**
    * 角色ID
    */
    private Long roleId;
}
