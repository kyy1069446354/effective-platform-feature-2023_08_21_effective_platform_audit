package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色和菜单关联表GetQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色和菜单关联表GetQuery")
public class SysRoleMenuGetQuery{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
