package com.chinapost.sd.effective.system.domain.po;

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
@TableName("sys_dept")
@Schema(description = "部门表")
public class SysDeptPO extends BasePO {

    /**
    * 部门id
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 父部门id
    */
    @TableField("parent_id")
    private Long parentId;

    /**
    * 祖级列表
    */
    @TableField("ancestors")
    private String ancestors;

    /**
     * 是否有子部门
     */
    @TableField("has_children")
    private Boolean hasChildren;

    /**
    * 部门名称
    */
    @TableField("name")
    private String name;

    /**
     * 部门编码
     */
    @TableField("code")
    private String code;

    /**
    * 显示顺序
    */
    @TableField("order_num")
    private Integer orderNum;

    /**
    * 负责人id
    */
    @TableField("leader_id")
    private Long leaderId;

    /**
    * 负责人
    */
    @TableField("leader_name")
    private String leaderName;

    /**
    * 联系电话
    */
    @TableField("phone")
    private String phone;

    /**
    * 邮箱
    */
    @TableField("email")
    private String email;

    /**
    * 部门状态（0停用 1启用）
    */
    @TableField("status")
    private Integer status;

    /**
    * 逻辑删除
    */
    @TableField("deleted")
    private Boolean deleted;
}
