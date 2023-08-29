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
@Schema(description = "代码查看VO")
public class PreviewCodeVo {
    private String fileName;
    private String code;
}
