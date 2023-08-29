package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 流程节点表DeleteCommand
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@Schema(name = "流程节点表DeleteCommand")
public class SysProcessNodesDeleteCommand{
      /**
      * 主键
      */
      @Schema(description = "主键")
      private Long id;
}
