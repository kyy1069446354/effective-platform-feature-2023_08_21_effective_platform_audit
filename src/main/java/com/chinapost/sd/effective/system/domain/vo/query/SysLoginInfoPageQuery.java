package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * <p>
 * 系统访问记录PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "系统访问记录PageQuery")
public class SysLoginInfoPageQuery extends BasePageReq {

    /**
    * 用户账号
    */
    @Schema(description = "用户账号")
    private String username;

    /**
    * 登录IP地址
    */
    @Schema(description = "登录IP地址")
    private String ipAddress;

    /**
    * 登录状态（sys_login_status）
    */
    @Schema(description = "登录状态（sys_login_status）")
    private Integer status;

    /**
     * 开始日期, 查询访问时间用
     */
    @Schema(description = "开始日期")
    private LocalDate beginTime;

    /**
     * 结束日期, 查询访问时间用
     */
    @Schema(description = "结束日期")
    private LocalDate endTime;

}
