package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典数据表AddCommand
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典数据表AddCommand")
public class SysDictDataAddCommand{

    /**
    * 字典排序
    */
    @Schema(description = "字典排序")
    private Integer orderNum;

    /**
    * 字典标签
    */
    @Schema(description = "字典标签")
    private String label;

    /**
    * 字典键值
    */
    @Schema(description = "字典键值")
    private Integer value;

    /**
    * sys_dict的code
    */
    @Schema(description = "sys_dict的Code")
    private String dictCode;

    /**
    * 样式属性（其他样式扩展）
    */
    @Schema(description = "样式属性（其他样式扩展）")
    private String cssClass;

    /**
    * 表格回显样式
    */
    @Schema(description = "表格回显样式")
    private String listClass;

    /**
    * 是否默认（1是 0否）
    */
    @Schema(description = "是否默认（1是 0否）")
    private Boolean isDefault;

    /**
    * 状态（1正常 0停用）
    */
    @Schema(description = "状态（1正常 0停用）")
    private Integer status;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;
}
