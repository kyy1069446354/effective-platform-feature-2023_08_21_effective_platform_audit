package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "岗位信息表")
public class SysPostVO{

    /**
    * 岗位ID
    */
    @Excel(name = "岗位序号", cellType = Excel.ColumnType.NUMERIC)
    @Schema(description = "岗位ID")
    private Long id;

    /**
     * 岗位编码
     */
    @Excel(name = "岗位编码")
    @Schema(description = "岗位编码")
    private String code;

    /**
     * 岗位名称
     */
    @Excel(name = "岗位名称")
    @Schema(description = "岗位名称")
    private String name;

    /**
     * 显示顺序
     */
    @Excel(name = "岗位排序")
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
     * 状态（1正常 0停用）
     */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
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

    /**
     * 创建时间
     */
    @Excel(name = "访问时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
