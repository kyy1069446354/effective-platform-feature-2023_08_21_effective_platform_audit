package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 通知公告表PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "通知公告表PageQuery")
public class SysNoticePageQuery extends BasePageReq {


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
    * 操作人员
    */
    @Schema(description = "操作人员")
    private String creatorName;

}
