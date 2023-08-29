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
 * 岗位信息表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@TableName("sys_post")
@Schema(description = "岗位信息表")
public class SysPostPO extends BasePO {

    /**
    * 岗位ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 岗位编码
    */
    @TableField("code")
    private String code;

    /**
    * 岗位名称
    */
    @TableField("name")
    private String name;

    /**
    * 显示顺序
    */
    @TableField("order_num")
    private Integer orderNum;

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

    /**
    * 逻辑删除
    */
    @TableField("deleted")
    private Boolean deleted;
}
