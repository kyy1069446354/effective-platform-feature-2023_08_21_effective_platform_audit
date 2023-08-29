package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 用户和角色关联表BatchDeleteCommand
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Getter
@Setter
@ToString
@Schema(description = "用户和角色关联表BatchDeleteCommand")
public class SysUserRoleBatchDeleteCommand{

    /**
    * 主键数组
    */
    @Schema(description = "主键数组")
    private List<Long> ids;
}
