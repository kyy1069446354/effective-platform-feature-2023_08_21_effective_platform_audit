package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * <p>
 * 角色信息表PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色信息表PageQuery")
public class SysRolePageQuery extends BasePageReq {

    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long id;

    /**
    * 角色名称
    */
    @Schema(description = "角色名称")
    private String name;

    /**
    * 角色权限字符串
    */
    @Schema(description = "角色权限字符串")
    private String code;

    /**
    * 角色状态（1正常 0停用）
    */
    @Schema(description = "角色状态（1正常 0停用）")
    private Integer status;


    /**
     * 开始日期, 查询创建时间用
     */
    @Schema(description = "开始日期")
    private LocalDate beginTime;

    /**
     * 结束日期, 查询创建时间用
     */
    @Schema(description = "结束日期")
    private LocalDate endTime;
}
