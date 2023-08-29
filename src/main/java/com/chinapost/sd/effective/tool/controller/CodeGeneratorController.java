package com.chinapost.sd.effective.tool.controller;

import com.chinapost.sd.effective.tool.domain.vo.command.BatchDownLoadCodeCommand;
import com.chinapost.sd.effective.tool.domain.vo.query.PreviewCodeQuery;
import com.chinapost.sd.effective.tool.service.GenTableService;
import com.chinapost.sd.boot.CodeGeneratorService;
import com.chinapost.sd.boot.domain.GenCode;
import com.chinapost.sd.boot.domain.GenTable;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * CodeGenController类
 *
 * @author tangyang
 * @since 2023-07-14
 */
@RestController
@RequestMapping("/tool/gen")
@Tag(name = "代码生成业务表API", description = "代码生成业务表API")
public class CodeGeneratorController {

    private final CodeGeneratorService codeGeneratorService = new CodeGeneratorService();
    @Autowired
    private GenTableService genTableService;

    /**
     * 预览代码key=filename value=code
     */
    @PostMapping("/preview")
    public ResponseResult<List<GenCode>> preview(@RequestBody PreviewCodeQuery query) throws Exception{
        GenTable table = genTableService.getById(query.getTableId());
        List<GenCode> genCodeList = codeGeneratorService.generateCode(table);
        return ResponseResult.success(genCodeList);
    }

    /**
     * 生成代码（下载方式）
     */
    @PostMapping("/download")
    public void download(HttpServletResponse response, @RequestBody PreviewCodeQuery query) throws Exception {
        GenTable table = genTableService.getById(query.getTableId());
        byte[] data = codeGeneratorService.generateCodeZip(table);
        writeToResponse(response, data);
    }



    /**
     * 批量生成代码
     */
    @PostMapping("/batchDownLoad")
    public void batchDownLoad(HttpServletResponse response, @RequestBody BatchDownLoadCodeCommand command) throws Exception {
        List<GenTable> tables = genTableService.getByIds(command.getTableIds());
        byte[] data = codeGeneratorService.generateCodeZip(tables);
        writeToResponse(response, data);
    }

    /**
     * 生成zip文件
     */
    private void writeToResponse(HttpServletResponse response, byte[] data) throws IOException
    {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }

}
