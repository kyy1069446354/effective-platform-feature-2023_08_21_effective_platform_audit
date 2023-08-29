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

import java.time.LocalDateTime;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@TableName("sys_user")
@Schema(description = "用户信息表")
public class SysUserPO extends BasePO {

    /**
    * 用户ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 职位id
    */
    @TableField("post_id")
    private Long postId;

    /**
    * 部门ID
    */
    @TableField("dept_id")
    private Long deptId;

    /**
    * 用户账号
    */
    @TableField("username")
    private String username;

    /**
    * 用户昵称
    */
    @TableField("nick_name")
    private String nickName;

    /**
    * 用户类型（00系统用户）
    */
    @TableField("user_type")
    private Integer userType;

    /**
    * 用户邮箱
    */
    @TableField("email")
    private String email;

    /**
    * 手机号码
    */
    @TableField("phone_number")
    private String phoneNumber;

    /**
    * 用户性别（0男 1女 2未知）
    */
    @TableField("sex")
    private Integer sex;

    /**
    * 头像地址
    */
    @TableField("avatar")
    private String avatar;

    /**
    * 密码
    */
    @TableField("password")
    private String password;

    /**
    * 帐号状态（1正常 2停用 3冻结）
    */
    @TableField("status")
    private Integer status;

    /**
    * 最后登录IP
    */
    @TableField("login_ip")
    private String loginIp;

    /**
    * 最后登录时间
    */
    @TableField("login_date")
    private LocalDateTime loginDate;

    /**
    * 超级管理员标志（1是，0否）
    */
    @TableField("is_admin")
    private Boolean isAdmin;

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
