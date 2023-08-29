package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典数据表ListQuery
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典数据表ListQuery")
public class SysDictDataListQuery{
    /**
     * 字典标签
     */
    @Schema(description = "字典标签")
    private String label;


    /**
     * sys_dict的code
     */
    @Schema(description = "sys_dict的code")
    private String dictCode;


    /**
     * 状态（0正常 1停用）
     */
    @Schema(description = "状态（0正常 1停用）")
    private Boolean status;
}
