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
 * 字典类型表
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@TableName("sys_dict")
@Schema(description = "字典类型表")
public class SysDictPO extends BasePO {

    /**
    * 字典主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;
    /**
     * 字典英文名
     */
    @TableField("code")
    private String code;

    /**
     * 字典中文名
     */
    @TableField("name")
    private String name;

    /**
    * 状态（1正常 0停用）
    */
    @TableField("status")
    private Integer status;
}
