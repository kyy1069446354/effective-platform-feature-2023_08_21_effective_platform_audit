package com.chinapost.sd.effective.system.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * <p>
 * 通知公告表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
public class SysNotice{

    /**
    * 公告ID
    */
    private Long id;

    /**
    * 公告标题
    */
    private String title;

    /**
    * 公告类型（1通知 2公告）
    */
    private Integer type;

    /**
    * 公告内容
    */
    private String content;

    /**
    * 公告状态（1正常 0关闭）
    */
    private Integer status;

    /**
    * 备注
    */
    private String remark;


    private String creatorName;

    private LocalDateTime createTime;
}
