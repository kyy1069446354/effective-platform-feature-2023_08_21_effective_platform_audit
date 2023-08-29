package com.chinapost.sd.effective.tool.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 代码生成业务表BatchDeleteCommand
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成业务表BatchDeleteCommand")
public class GenTableBatchDeleteCommand{

    /**
    * 主键数组
    */
    @Schema(description = "主键数组")
    private List<Long> ids;
}
