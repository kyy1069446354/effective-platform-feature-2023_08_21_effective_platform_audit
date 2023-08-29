package com.chinapost.sd.effective.common.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.WebMvcConstants;
import com.chinapost.sd.boot.infrastructure.error.BusinessErrorCode;
import com.chinapost.sd.boot.infrastructure.exception.BusinessException;
import com.chinapost.sd.boot.infrastructure.storage.FileUploadService;
import com.chinapost.sd.boot.infrastructure.utils.ServletHolderUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用请求处理
 *
 * @author tangyang
 */
@Tag(name = "上传API", description = "上传相关接口")
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {


    @Autowired
    private FileUploadService fileUploadService;


    /**
     * 通用上传请求（单个）
     */
    @Operation(summary = "单个上传文件")
    @PostMapping("/upload")
    public ResponseResult<UploadVO> uploadFile(MultipartFile file) throws IOException {
        if (file == null) {
            throw new BusinessException(BusinessErrorCode.UPLOAD_FILE_IS_EMPTY);
        }

        // 上传并返回新文件名称
        String fileName = fileUploadService.upload(file);

        String url = ServletHolderUtils.getContextUrl() + WebMvcConstants.UPLOAD_RESOURCE_URL_PREFIX + fileName;

        UploadVO uploadVo= UploadVO.builder()
                // 全路径
                .url(url)
                // 相对路径
                .fileName(fileName)
                // 新生成的文件名
                .newFileName(FileNameUtil.getName(fileName))
                // 原始的文件名
                .originalFilename(file.getOriginalFilename()).build();

        return ResponseResult.success(uploadVo);
    }

    /**
     * 通用上传请求（多个）
     */
    @Operation(summary = "多个上传文件")
    @PostMapping("/uploads")
    public ResponseResult<List<UploadVO>> uploadFiles(List<MultipartFile> files) throws IOException{
        if (CollUtil.isEmpty(files)) {
            throw new BusinessException(BusinessErrorCode.UPLOAD_FILE_IS_EMPTY);
        }

        List<UploadVO> uploads = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null) {
                // 上传并返回新文件名称
                String fileName = fileUploadService.upload(file);
                String url = ServletHolderUtils.getContextUrl() + WebMvcConstants.UPLOAD_RESOURCE_URL_PREFIX + fileName;
                UploadVO uploadDTO = UploadVO.builder()
                        .url(url)
                        .fileName(fileName)
                        .newFileName(FileNameUtil.getName(fileName))
                        .originalFilename(file.getOriginalFilename()).build();

                uploads.add(uploadDTO);

            }
        }
        return ResponseResult.success(uploads);
    }

}
