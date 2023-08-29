package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 字典数据表
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典数据表")
public class SysDictDataVO{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;

    /**
    * 字典排序
    */
    @Excel(name = "字典排序")
    @Schema(description = "字典排序")
    private Integer orderNum;

    /**
    * 字典标签
    */
    @Excel(name = "字典标签")
    @Schema(description = "字典标签")
    private String label;

    /**
    * 字典键值
    */
    @Excel(name = "字典键值")
    @Schema(description = "字典键值")
    private Integer value;

    /**
    * sys_dict的code
    */
    @Excel(name = "字典类型")
    @Schema(description = "sys_dict的code")
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
    @Excel(name = "状态", readConverterExp = "1=是,0=否")
    private Boolean isDefault;

    /**
    * 状态（1正常 0停用）
    */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    @Schema(description = "状态（0正常 1停用）")
    private Integer status;

    /**
    * 备注
    */
    @Excel(name = "备注")
    @Schema(description = "备注")
    private String remark;
    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
