package com.chinapost.sd.effective.common.file;

import lombok.Builder;
import lombok.Data;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/8/11
 */
@Data
@Builder
public class UploadVO {

    private String url;
    private String fileName;
    private String newFileName;
    private String originalFilename;
}
