package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核员表DeleteCommand
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@Schema(name = "审核员表DeleteCommand")
public class SysAuditorDeleteCommand{
      /**
      * 申请工单ID
      */
      @Schema(description = "申请工单ID")
      private Long id;
}
