package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典类型表DeleteCommand
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典类型表DeleteCommand")
public class SysDictDeleteCommand{

    /**
    * 字典主键
    */
    @Schema(description = "字典主键")
    private Long id;
}
