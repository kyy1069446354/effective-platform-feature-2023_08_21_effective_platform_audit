package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 用户信息表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "用户信息表AddCommand")
public class SysUserAddCommand{

    /**
    * 职位id
    */
    @Schema(description = "职位id")
    private Long postId;

    /**
    * 角色ids
    */
    @Schema(description = "角色ids")
    private List<Long> roleIds;

    /**
    * 部门ID
    */
    @Schema(description = "部门ID")
    private Long deptId;

    /**
    * 用户账号
    */
    @Schema(description = "用户账号")
    private String username;

    /**
    * 用户昵称
    */
    @Schema(description = "用户昵称")
    private String nickName;

    /**
    * 用户类型（00系统用户）
    */
    @Schema(description = "用户类型（00系统用户）")
    private Integer userType;

    /**
    * 用户邮箱
    */
    @Schema(description = "用户邮箱")
    private String email;

    /**
    * 手机号码
    */
    @Schema(description = "手机号码")
    private String phoneNumber;

    /**
    * 用户性别（0男 1女 2未知）
    */
    @Schema(description = "用户性别（0男 1女 2未知）")
    private Integer sex;

    /**
    * 头像地址
    */
    @Schema(description = "头像地址")
    private String avatar;

    /**
    * 密码
    */
    @Schema(description = "密码")
    private String password;

    /**
    * 帐号状态（1正常 2停用 3冻结）
    */
    @Schema(description = "帐号状态（1正常 2停用 3冻结）")
    private Integer status;


    /**
    * 超级管理员标志（1是，0否）
    */
    @Schema(description = "超级管理员标志（1是，0否）")
    private Boolean isAdmin;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;

}
