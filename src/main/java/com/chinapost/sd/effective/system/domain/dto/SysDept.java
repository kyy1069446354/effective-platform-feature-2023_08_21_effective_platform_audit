package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
public class SysDept{

    /**
    * 部门id
    */
    private Long id;

    /**
    * 父部门id
    */
    private Long parentId;

    /**
    * 祖级列表
    */
    private String ancestors;

    /**
     * 有孩子节点
     */
    private Boolean hasChildren;

    /**
    * 部门名称
    */
    private String name;

    /**
     * 部门编码
     */
    private String code;

    /**
    * 显示顺序
    */
    private Integer orderNum;

    /**
    * 负责人id
    */
    private Long leaderId;

    /**
    * 负责人
    */
    private String leaderName;

    /**
    * 联系电话
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 部门状态（0停用 1启用）
    */
    private Integer status;

    /**
    * 逻辑删除
    */
    private Boolean deleted;

    private LocalDateTime createTime;
}
