package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典数据表DeleteCommand
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典数据表DeleteCommand")
public class SysDictDataDeleteCommand{

    /**
    * 字典编码
    */
    @Schema(description = "字典编码")
    private Long id;
}
