package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 系统访问记录DeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "系统访问记录DeleteCommand")
public class SysLoginInfoDeleteCommand{

    /**
    * 访问ID
    */
    @Schema(description = "访问ID")
    private Long id;
}
