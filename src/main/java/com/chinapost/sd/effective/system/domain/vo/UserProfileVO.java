package com.chinapost.sd.effective.system.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户信息VO类
 *
 * @author tangyang
 * @since 2023/8/10
 */
@Getter
@Setter
@ToString
@Schema(description = "用户信息")
public class UserProfileVO {

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private Long id;

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
     * 职位id
     */
    @Schema(description = "职位id")
    private Long postId;

    /**
     * 部门ID
     */
    @Schema(description = "部门ID")
    private Long deptId;

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
     * 帐号状态（1正常 2停用 3冻结）
     */
    @Schema(description = "帐号状态（1正常 2停用 3冻结）")
    private Integer status;


    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 所属部门
     */
    @Schema(description = "所属部门")
    private String deptName;

    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    private String postName;

    /**
     * 角色roleName列表, 逗号隔开
     */
    @Schema(description = "角色roleName列表, 逗号隔开")
    private String roleNames;
}
