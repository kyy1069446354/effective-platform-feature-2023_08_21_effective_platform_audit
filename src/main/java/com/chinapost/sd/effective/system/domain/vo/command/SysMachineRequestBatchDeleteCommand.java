package com.chinapost.sd.effective.system.domain.vo.command;

import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 机器审核工单表BatchDeleteCommand
 *
 * @author admin
 * @since 2023-08-28
 */
@Getter
@Setter
@ToString
@Schema(name = "机器审核工单表BatchDeleteCommand")
public class SysMachineRequestBatchDeleteCommand{

    /**
    * 主键数组
    */
    @Schema(description = "主键数组")
    private List<Long> ids;
}
