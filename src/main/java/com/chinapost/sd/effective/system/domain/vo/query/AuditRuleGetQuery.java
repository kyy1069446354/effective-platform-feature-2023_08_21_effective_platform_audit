package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 审核规则表GetQuery
 *
 * @author admin
 * @since 2023-08-30
 */
@Getter
@Setter
@ToString
@Schema(name = "审核规则表GetQuery")
public class AuditRuleGetQuery{

      /**
      * 主键
      */
      @Schema(description = "主键")
      private Long id;
}
