package com.chinapost.sd.effective.system.domain.dto;

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
public class SysDictData{

    /**
    * 字典编码
    */
    private Long id;

    /**
    * 字典排序
    */
    private Integer orderNum;

    /**
    * 字典标签
    */
    private String label;

    /**
    * 字典键值
    */
    private Integer value;

    /**
    * sys_dict的key
    */
    private String dictCode;

    /**
    * 样式属性（其他样式扩展）
    */
    private String cssClass;

    /**
    * 表格回显样式
    */
    private String listClass;

    /**
    * 是否默认（1是 0否）
    */
    private Boolean isDefault;

    /**
    * 状态（1正常 0停用）
    */
    private Integer status;

    /**
    * 备注
    */
    private String remark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
