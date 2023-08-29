package com.chinapost.sd.effective.tool.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 代码生成业务表
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "代码生成业务表")
public class PageGenTableVO {

    /**
    * 编号
    */
    @Schema(description = "编号")
    private Long id;

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


    /**
    * 实体类名称
    */
    @Schema(description = "实体类名称")
    private String className;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
