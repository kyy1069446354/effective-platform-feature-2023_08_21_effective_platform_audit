package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * <p>
 * 部门表BatchDeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "部门表BatchDeleteCommand")
public class SysDeptBatchDeleteCommand{

    /**
    * 主键数组
    */
    @Schema(description = "主键数组")
    private List<Long> ids;
}
