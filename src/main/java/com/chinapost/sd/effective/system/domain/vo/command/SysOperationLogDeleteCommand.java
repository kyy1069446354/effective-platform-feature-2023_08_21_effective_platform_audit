package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 操作日志记录DeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "操作日志记录DeleteCommand")
public class SysOperationLogDeleteCommand{

    /**
    * 日志主键
    */
    @Schema(description = "日志主键")
    private Long id;
}
