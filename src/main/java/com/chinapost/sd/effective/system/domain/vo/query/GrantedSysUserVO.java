package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 用户信息表
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "用户信息表")
public class GrantedSysUserVO {

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
    * 帐号状态（1正常 2停用 3冻结）
    */
    @Schema(description = "帐号状态（1正常 2停用 3冻结）")
    private Integer status;


    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
