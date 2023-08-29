package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 岗位信息表PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "岗位信息表PageQuery")
public class SysPostPageQuery extends BasePageReq {

    /**
    * 岗位编码
    */
    @Schema(description = "岗位编码")
    private String code;

    /**
    * 岗位名称
    */
    @Schema(description = "岗位名称")
    private String name;

    /**
    * 状态（1正常 0停用）
    */
    @Schema(description = "状态（1正常 0停用）")
    private Integer status;
}
