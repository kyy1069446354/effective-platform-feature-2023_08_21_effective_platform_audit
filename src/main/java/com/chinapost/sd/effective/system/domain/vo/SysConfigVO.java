package com.chinapost.sd.effective.system.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "参数配置表")
public class SysConfigVO{

    /**
    * 参数主键
    */
    @Schema(description = "参数主键")
    private Long id;

    /**
     * 配置英文名
     */
    @Schema(description = "配置英文名")
    private String code;

    /**
     * 配置中文名
     */
    @Schema(description = "配置中文名")
    private String name;

    /**
    * 可选的选项
    */
    @Schema(description = "可选的选项")
    private String options;

    /**
    * 配置值
    */
    @Schema(description = "配置值")
    private String value;

    /**
    * 是否允许修改
    */
    @Schema(description = "是否允许修改")
    private Boolean isAllowChange;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;


    private LocalDateTime createTime;
}
