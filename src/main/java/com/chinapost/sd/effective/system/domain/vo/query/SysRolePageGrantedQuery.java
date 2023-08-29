package com.chinapost.sd.effective.system.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 已关联该角色的用户列表Query
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "已关联该角色的用户列表Query")
public class SysRolePageGrantedQuery extends BasePageReq {
    /**
    * 角色ID
    */
    @Schema(description = "角色ID")
    private Long roleId;

    private String username;

    private String phoneNumber;
}
