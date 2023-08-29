package com.chinapost.sd.effective.tool.domain.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/14
 */
@Getter
@Setter
@ToString
@Schema(description = "数据库表VO")
public class DbTableVO {
    private String fullName;
    private String name;

    private String comment;
}
