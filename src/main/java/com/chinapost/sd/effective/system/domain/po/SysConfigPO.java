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
@TableName("sys_config")
@Schema(description = "参数配置表")
public class SysConfigPO extends BasePO {

    /**
    * 参数主键
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 配置英文名
    */
    @TableField("code")
    private String code;

    /**
    * 配置中文名
    */
    @TableField("name")
    private String name;

    /**
    * 可选的选项
    */
    @TableField("options")
    private String options;

    /**
    * 配置值
    */
    @TableField("value")
    private String value;

    /**
    * 是否允许修改
    */
    @TableField("is_allow_change")
    private Boolean isAllowChange;

    /**
    * 备注
    */
    @TableField("remark")
    private String remark;

    /**
    * 逻辑删除
    */
    @TableField("deleted")
    private Boolean deleted;
}
