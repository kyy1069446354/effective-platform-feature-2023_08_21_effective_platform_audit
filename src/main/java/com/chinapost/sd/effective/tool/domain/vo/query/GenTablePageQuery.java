package com.chinapost.sd.effective.tool.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 代码生成业务表PageQuery
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成业务表PageQuery")
public class GenTablePageQuery extends BasePageReq{

    /**
    * 表名称
    */
    @Schema(description = "表名称")
    private String name;

    /**
    * 表描述
    */
    @Schema(description = "表描述")
    private String comment;


    private LocalDate beginTime;

    private LocalDate endTime;
}
