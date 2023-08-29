package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 参数配置表DeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "参数配置表DeleteCommand")
public class SysConfigDeleteCommand{

    /**
    * 参数主键
    */
    @Schema(description = "参数主键")
    private Long id;
}
