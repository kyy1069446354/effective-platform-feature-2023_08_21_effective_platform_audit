package com.chinapost.sd.effective.system.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 通知公告表DeleteCommand
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Getter
@Setter
@ToString
@Schema(description = "通知公告表DeleteCommand")
public class SysNoticeDeleteCommand{

    /**
    * 公告ID
    */
    @Schema(description = "公告ID")
    private Long id;
}
