package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 批量取消授予用户角色Command
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "批量取消授予用户角色Command")
public class SysRoleCancelGrantCommand {
    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long roleId;


    @NotEmpty
    private List<Long> userIds;

}
