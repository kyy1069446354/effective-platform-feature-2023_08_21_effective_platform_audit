package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 字典数据表BatchDeleteCommand
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典数据表BatchDeleteCommand")
public class SysDictDataBatchDeleteCommand{

    /**
    * 主键数组
    */
    @Schema(description = "主键数组")
    private List<Long> ids;
}
