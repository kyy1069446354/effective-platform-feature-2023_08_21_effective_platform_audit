package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
public class SysRole{

    /**
    * 角色ID
    */
    private Long id;

    /**
     * 角色英文名
     */
    private String code;

    /**
     * 角色中文名
     */
    private String name;

    /**
    * 显示顺序
    */
    private Integer orderNum;

    /**
    * 数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）
    */
    private Integer dataScope;

    /**
    * 角色状态（1正常 0停用）
    */
    private Integer status;

    /**
    * 备注
    */
    private String remark;

    /**
    * 删除标志（0代表存在 1代表删除）
    */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 角色拥有的菜单权限
     */
    private Set<Long> menuIds;


    /**
     * 角色所拥有的部门数据权限
     */
    private Set<Long> deptIds;
}
