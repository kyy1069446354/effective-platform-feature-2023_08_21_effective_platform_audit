package com.chinapost.sd.effective.tool.domain.vo.command;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/14
 */
@Getter
@Setter
@ToString
@Schema(description = "数据库表导入")
public class ImportTableCommand {
    private List<String> tables;
}
