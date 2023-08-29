package com.chinapost.sd.effective.system.domain.vo;

import lombok.Data;

/**
 * @author tangyang
 */
@Data
public class UploadFileVO {

    public UploadFileVO(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private String imgUrl;

    public UploadFileVO() {
    }
}
