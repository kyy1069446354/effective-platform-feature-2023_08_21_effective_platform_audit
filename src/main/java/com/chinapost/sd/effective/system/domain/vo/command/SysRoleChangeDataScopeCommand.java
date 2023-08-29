package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 修改角色数据权限Command
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "修改角色数据权限Command")
public class SysRoleChangeDataScopeCommand {
    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long id;

    private List<Long> deptIds;

    private Integer dataScope;
}
