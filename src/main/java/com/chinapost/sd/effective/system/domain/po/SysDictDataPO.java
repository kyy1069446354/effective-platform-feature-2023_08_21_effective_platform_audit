package com.chinapost.sd.effective.system.domain.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.chinapost.sd.boot.infrastructure.base.BasePO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典数据表
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@TableName("sys_dict_data")
@Schema(description = "字典数据表")
public class SysDictDataPO extends BasePO {

    /**
    * 字典编码
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 字典排序
    */
    @TableField("order_num")
    private Integer orderNum;

    /**
    * 字典标签
    */
    @TableField("label")
    private String label;

    /**
    * 字典键值
    */
    @TableField("value")
    private Integer value;

    /**
    * sys_dict的code
    */
    @TableField("dict_code")
    private String dictCode;

    /**
    * 样式属性（其他样式扩展）
    */
    @TableField("css_class")
    private String cssClass;

    /**
    * 表格回显样式
    */
    @TableField("list_class")
    private String listClass;

    /**
    * 是否默认（1是 0否）
    */
    @TableField("is_default")
    private Boolean isDefault;

    /**
    * 状态（1正常 0停用）
    */
    @TableField("status")
    private Integer status;

    /**
    * 备注
    */
    @TableField("remark")
    private String remark;
}
