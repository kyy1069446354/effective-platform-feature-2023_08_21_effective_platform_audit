package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 机器审核工单表DeleteCommand
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@Schema(name = "机器审核工单表DeleteCommand")
public class SysMachineRequestDeleteCommand{
      /**
      * 申请工单ID
      */
      @Schema(description = "申请工单ID")
      private Long id;
}
