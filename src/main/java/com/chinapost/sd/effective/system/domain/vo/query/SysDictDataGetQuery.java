package com.chinapost.sd.effective.system.domain.vo.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典数据表GetQuery
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Getter
@Setter
@ToString
@Schema(description = "字典数据表GetQuery")
public class SysDictDataGetQuery{

    /**
    * 主键
    */
    @Schema(description = "主键")
    private Long id;
}
