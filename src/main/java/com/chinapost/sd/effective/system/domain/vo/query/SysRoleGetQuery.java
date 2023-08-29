package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 角色信息表GetQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "角色信息表GetQuery")
public class SysRoleGetQuery{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
