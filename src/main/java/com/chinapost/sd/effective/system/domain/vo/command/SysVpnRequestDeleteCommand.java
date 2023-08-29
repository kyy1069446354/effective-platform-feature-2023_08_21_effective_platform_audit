package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * vpn审核工单表DeleteCommand
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@Schema(name = "vpn审核工单表DeleteCommand")
public class SysVpnRequestDeleteCommand{
      /**
      * 申请工单ID
      */
      @Schema(description = "申请工单ID")
      private Long id;
}
