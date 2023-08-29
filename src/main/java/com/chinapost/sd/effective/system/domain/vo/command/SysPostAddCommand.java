package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 岗位信息表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "岗位信息表AddCommand")
public class SysPostAddCommand{

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
    * 显示顺序
    */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
    * 状态（1正常 0停用）
    */
    @Schema(description = "状态（1正常 0停用）")
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
