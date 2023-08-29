package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户和角色关联表ListQuery
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Getter
@Setter
@ToString
@Schema(description = "用户和角色关联表ListQuery")
public class SysUserRoleListQuery{

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
}
