package com.chinapost.sd.effective.common.login.vo;

import lombok.Data;

/**
 * @author tangyang
 */
@Data
public class CaptchaImgVO {

    private Boolean isCaptchaOn;
    private String uuid;
    private String img;

}
