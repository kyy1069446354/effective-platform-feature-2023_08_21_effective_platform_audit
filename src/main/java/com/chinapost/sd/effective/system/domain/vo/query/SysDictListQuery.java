package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/**
 * 字典类型表ListQuery
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典类型表ListQuery")
public class SysDictListQuery{

    /**
    * 字典英文名
    */
    @Schema(description = "字典英文名")
    private String code;

    /**
    * 字典中文名
    */
    @Schema(description = "字典中文名")
    private String name;

    /**
    * 状态（1正常 0停用）
    */
    @Schema(description = "状态（1正常 0停用）")
    private Boolean status;

    /**
     * 开始日期, 查询创建时间用
     */
    @Schema(description = "开始日期")
    private LocalDate beginTime;

    /**
     * 结束日期, 查询创建时间用
     */
    @Schema(description = "结束日期")
    private LocalDate endTime;
}
