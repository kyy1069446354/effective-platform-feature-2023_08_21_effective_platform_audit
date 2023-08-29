package com.chinapost.sd.effective.system.domain.vo;

import com.chinapost.sd.boot.infrastructure.annotations.Excel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * <p>
 * 角色信息表
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色信息表")
public class SysRoleVO{

    /**
    * 角色ID
    */
    @Excel(name = "角色ID", cellType = Excel.ColumnType.NUMERIC)
    @Schema(description = "角色ID")
    private Long id;

    /**
     * 角色英文名
     */
    @Excel(name = "权限字符")
    @Schema(description = "角色英文名")
    private String code;

    /**
     * 角色中文名
     */
    @Excel(name = "角色名称")
    @Schema(description = "角色中文名")
    private String name;

    /**
    * 显示顺序
    */
    @Excel(name = "显示顺序")
    @Schema(description = "显示顺序")
    private Integer orderNum;

    /**
    * 数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）
    */
    @Schema(description = "数据范围（1：全部数据权限 2：自定数据权限 3: 本部门数据权限 4: 本部门及以下数据权限 5: 本人权限）")
    private Integer dataScope;

    /**
    * 角色所拥有的部门数据权限
    */
    @Schema(description = "角色所拥有的部门数据权限")
    private String deptIdSet;

    /**
    * 角色状态（1正常 0停用）
    */
    @Excel(name = "状态", readConverterExp = "1=正常,0=停用")
    @Schema(description = "角色状态（1正常 0停用）")
    private Integer status;

    /**
    * 备注
    */
    @Schema(description = "备注")
    private String remark;

    /**
     * 创建时间
     */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    /**
     * 角色拥有的菜单权限
     */
    @Schema(description = "角色拥有的菜单权限")
    private Set<Long> menuIds;

    /**
     * 角色所拥有的部门数据权限
     */
    private Set<Long> deptIds;
}
