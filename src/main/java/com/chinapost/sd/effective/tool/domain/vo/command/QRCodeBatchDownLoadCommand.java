package com.chinapost.sd.effective.tool.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Schema(description = "批量生成二维码command")
public class QRCodeBatchDownLoadCommand {
    private Integer num;
}
