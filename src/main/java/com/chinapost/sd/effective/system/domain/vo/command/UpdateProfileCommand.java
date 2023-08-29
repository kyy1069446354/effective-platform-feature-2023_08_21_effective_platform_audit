package com.chinapost.sd.effective.system.domain.vo.command;

import lombok.Data;

/**
 * @author tangyang
 */
@Data
public class UpdateProfileCommand {

    /**
     * 不用传
     */
    private Long userId;
    private Integer sex;
    private String nickName;
    private String phoneNumber;
    private String email;

}
