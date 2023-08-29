package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * <p>
 * 用户信息表PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "用户信息表PageQuery")
public class SysUserPageQuery extends BasePageReq {


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
    * 手机号码
    */
    @Schema(description = "手机号码")
    private String phoneNumber;


    /**
    * 帐号状态（1正常 2停用 3冻结）
    */
    @Schema(description = "帐号状态（1正常 2停用 3冻结）")
    private Integer status;

    /**
    * 开始时间, 查询创建时间用
    */
    @Schema(description = "开始时间")
    private LocalDate beginTime;

    /**
     * 结束时间, 查询创建时间用
     */
    @Schema(description = "结束时间")
    private LocalDate endTime;

}
