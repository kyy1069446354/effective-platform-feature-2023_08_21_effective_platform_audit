package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
public class SysPost{

    /**
    * 岗位ID
    */
    private Long id;

    /**
    * 岗位编码
    */
    private String code;

    /**
    * 岗位名称
    */
    private String name;

    /**
    * 显示顺序
    */
    private Integer orderNum;

    /**
    * 状态（1正常 0停用）
    */
    private Integer status;

    /**
    * 备注
    */
    private String remark;

    /**
    * 逻辑删除
    */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
