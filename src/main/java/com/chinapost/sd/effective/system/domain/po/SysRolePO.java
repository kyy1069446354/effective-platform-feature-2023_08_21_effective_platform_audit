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
 * 角色信息表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@TableName("sys_role")
@Schema(description = "角色信息表")
public class SysRolePO extends BasePO {

    /**
    * 角色ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 角色英文名
    */
    @TableField("code")
    private String code;

    /**
    * 角色中文名
    */
    @TableField("name")
    private String name;

    /**
    * 显示顺序
    */
    @TableField("order_num")
    private Integer orderNum;

    /**
    * 数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）
    */
    @TableField("data_scope")
    private Integer dataScope;

    /**
    * 角色所拥有的部门数据权限
    */
    @TableField("dept_id_set")
    private String deptIdSet;

    /**
    * 角色状态（1正常 0停用）
    */
    @TableField("status")
    private Integer status;

    /**
    * 备注
    */
    @TableField("remark")
    private String remark;

    /**
    * 删除标志（0代表存在 1代表删除）
    */
    @TableField("deleted")
    private Boolean deleted;
}
