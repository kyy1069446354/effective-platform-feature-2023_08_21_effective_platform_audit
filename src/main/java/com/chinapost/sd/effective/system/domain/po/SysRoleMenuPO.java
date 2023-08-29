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
 * 角色和菜单关联表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@TableName("sys_role_menu")
@Schema(description = "角色和菜单关联表")
public class SysRoleMenuPO extends BasePO {

    /**
    * 主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 角色ID
    */
    @TableField("role_id")
    private Long roleId;

    /**
    * 菜单ID
    */
    @TableField("menu_id")
    private Long menuId;
}
