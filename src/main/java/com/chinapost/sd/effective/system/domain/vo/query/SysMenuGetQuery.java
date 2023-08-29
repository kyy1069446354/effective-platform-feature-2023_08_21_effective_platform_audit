package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 菜单权限表GetQuery
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "菜单权限表GetQuery")
public class SysMenuGetQuery{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
