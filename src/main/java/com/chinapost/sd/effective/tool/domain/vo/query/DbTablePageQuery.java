package com.chinapost.sd.effective.tool.domain.vo.query;

import com.chinapost.sd.boot.infrastructure.base.BasePageReq;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据库表查询
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Getter
@Setter
@ToString
@Schema(description = "数据库表查询")
public class DbTablePageQuery extends BasePageReq {

    /**
    * 表名称
    */
    @Schema(description = "表名称")
    private String name;
}
