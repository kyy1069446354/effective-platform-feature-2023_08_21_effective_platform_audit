package com.chinapost.sd.effective.tool.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 代码生成业务表GetQuery
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成业务表GetQuery")
public class GenTableGetQuery{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
