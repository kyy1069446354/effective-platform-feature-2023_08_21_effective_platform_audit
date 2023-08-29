package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
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
@Schema(description = "用户信息表")
public class PageSysUserVO {

    /**
    * 用户ID
    */
    @Excel(name = "用户ID", cellType = Excel.ColumnType.NUMERIC)
    @Schema(description = "用户ID")
    private Long id;

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
     * 部门
     */
    @Excel(name = "部门")
    @Schema(description = "部门")
    private String deptName;

    /**
    * 用户账号
    */
    @Excel(name = "用户名称")
    @Schema(description = "用户账号")
    private String username;

    /**
    * 用户昵称
    */
    @Excel(name = "用户昵称")
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
    @Excel(name = "手机号码")
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
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    @Schema(description = "帐号状态（1正常 0停用）")
    private Integer status;

    /**
    * 最后登录IP
    */
    @Schema(description = "最后登录IP")
    private String loginIp;

    /**
    * 最后登录时间
    */
    @Schema(description = "最后登录时间")
    private LocalDateTime loginDate;

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

    /**
    * 删除标志（0代表存在 1代表删除）
    */
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    private Boolean deleted;

    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
