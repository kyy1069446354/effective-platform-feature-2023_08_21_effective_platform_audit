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
 * 通知公告表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@TableName("sys_notice")
@Schema(description = "通知公告表")
public class SysNoticePO extends BasePO {

    /**
    * 公告ID
    */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
    * 公告标题
    */
    @TableField("title")
    private String title;

    /**
    * 公告类型（1通知 2公告）
    */
    @TableField("type")
    private Integer type;

    /**
    * 公告内容
    */
    @TableField("content")
    private String content;

    /**
    * 公告状态（1正常 0关闭）
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
