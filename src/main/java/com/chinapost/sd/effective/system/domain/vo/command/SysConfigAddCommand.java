package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 参数配置表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "参数配置表AddCommand")
public class SysConfigAddCommand{

    /**
    * 配置名称
    */
    @Schema(description = "配置名称")
    private String name;

    /**
    * 配置键名
    */
    @Schema(description = "配置键名")
    private String key;

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

    /**
    * 逻辑删除
    */
    @Schema(description = "逻辑删除")
    private Boolean deleted;
}
