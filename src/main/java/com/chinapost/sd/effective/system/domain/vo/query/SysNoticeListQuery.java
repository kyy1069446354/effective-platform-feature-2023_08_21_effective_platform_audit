package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 通知公告表ListQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "通知公告表ListQuery")
public class SysNoticeListQuery{

    /**
    * 公告ID
    */
    @Schema(description = "公告ID")
    private Long id;

    /**
    * 公告标题
    */
    @Schema(description = "公告标题")
    private String title;

    /**
    * 公告类型（1通知 2公告）
    */
    @Schema(description = "公告类型（1通知 2公告）")
    private Integer type;

    /**
    * 公告内容
    */
    @Schema(description = "公告内容")
    private String content;

    /**
    * 公告状态（1正常 0关闭）
    */
    @Schema(description = "公告状态（1正常 0关闭）")
    private Integer status;

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
