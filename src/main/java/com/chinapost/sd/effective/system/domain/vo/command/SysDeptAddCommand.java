package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 部门表AddCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "部门表AddCommand")
public class SysDeptAddCommand{

    /**
    * 父部门id
    */
    @Schema(description = "父部门id")
    private Long parentId;

    /**
    * 祖级列表
    */
    @Schema(description = "祖级列表")
    private String ancestors;

    /**
    * 部门名称
    */
    @Schema(description = "部门名称")
    private String name;

    /**
     * 部门编码
     */
    @Schema(description = "部门编码")
    private String code;

    /**
    * 显示顺序
    */
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
    * 负责人id
    */
    @Schema(description = "负责人id")
    private Long leaderId;

    /**
    * 负责人
    */
    @Schema(description = "负责人")
    private String leaderName;

    /**
    * 联系电话
    */
    @Schema(description = "联系电话")
    private String phone;

    /**
    * 邮箱
    */
    @Schema(description = "邮箱")
    private String email;

    /**
    * 部门状态（0停用 1启用）
    */
    @Schema(description = "部门状态（0停用 1启用）")
    private Integer status;

    /**
    * 逻辑删除
    */
    @Schema(description = "逻辑删除")
    private Boolean deleted;
}
