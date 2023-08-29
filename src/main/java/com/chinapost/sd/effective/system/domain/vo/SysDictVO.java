package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 字典类型表
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典类型表")
public class SysDictVO{

    /**
    * 字典主键
    */
    @Schema(description = "字典主键")
    private Long id;

    /**
    * 字典英文名
    */
    @Excel(name = "字典类型")
    @Schema(description = "字典英文名")
    private String code;

    /**
    * 字典中文名
    */
    @Excel(name = "字典名称")
    @Schema(description = "字典中文名")
    private String name;

    /**
    * 状态（1正常 0停用）
    */
    @Schema(description = "状态（1正常 0停用）")
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    private Integer status;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
