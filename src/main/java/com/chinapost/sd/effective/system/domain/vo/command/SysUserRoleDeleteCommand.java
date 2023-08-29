package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户和角色关联表DeleteCommand
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Getter
@Setter
@ToString
@Schema(description = "用户和角色关联表DeleteCommand")
public class SysUserRoleDeleteCommand{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
