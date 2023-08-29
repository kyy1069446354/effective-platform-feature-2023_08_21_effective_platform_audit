package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 参数配置表PageQuery
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "参数配置表PageQuery")
public class SysConfigPageQuery extends BasePageReq {
    /**
    * 配置名称
    */
    @Schema(description = "配置名称")
    private String name;

    /**
    * 配置键名
    */
    @Schema(description = "配置键名")
    private String code;

    /**
    * 是否允许修改
    */
    @Schema(description = "是否允许修改")
    private Boolean isAllowChange;

    /**
     * 开始时间, 查询创建时间用
     */
    @Schema(description = "开始时间")
    private LocalDate beginTime;

    /**
     * 结束时间, 查询创建时间用
     */
    @Schema(description = "结束时间")
    private LocalDate endTime;

}
