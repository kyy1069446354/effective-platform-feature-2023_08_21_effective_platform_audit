package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核员表DeleteCommand
 *
 * @author admin
 * @since 2023-08-29
 */
@Getter
@Setter
@ToString
@Schema(name = "审核员表DeleteCommand")
public class SysAuditorDeleteCommand{
      /**
      * 主键
      */
      @Schema(description = "主键")
      private Long id;
}
