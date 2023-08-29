package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 部门表PageQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "部门表PageQuery")
public class SysDeptPageQuery extends BasePageReq {

    @Schema(description = "部门id")
    private Long id;

    /**
    * 父部门id
    */
    @Schema(description = "父部门id")
    private Long parentId;


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
    * 部门状态（0停用 1启用）
    */
    @Schema(description = "部门状态（0停用 1启用）")
    private Integer status;
}
