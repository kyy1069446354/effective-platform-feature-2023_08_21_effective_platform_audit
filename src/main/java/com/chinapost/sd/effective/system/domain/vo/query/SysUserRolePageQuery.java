package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 用户和角色关联表PageQuery
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Getter
@Setter
@ToString
@Schema(description = "用户和角色关联表PageQuery")
public class SysUserRolePageQuery extends BasePageReq {

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;

    /**
    * 用户ID
    */
    @Schema(description = "用户ID")
    private Long userId;

    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long roleId;

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
