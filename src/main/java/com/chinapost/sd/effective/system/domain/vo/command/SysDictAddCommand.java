package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典类型表AddCommand
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典类型表AddCommand")
public class SysDictAddCommand{

    /**
    * 字典名称
    */
    @Schema(description = "字典名称")
    private String code;

    /**
    * 字典描述
    */
    @Schema(description = "字典描述")
    private String name;

    /**
    * 状态（1正常 0停用）
    */
    @Schema(description = "状态（1正常 0停用）")
    private Integer status;
}
