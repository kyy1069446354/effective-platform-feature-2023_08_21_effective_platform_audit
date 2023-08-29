package com.chinapost.sd.effective.system.domain.vo.command;

import lombok.Data;

/**
 * @author tangyang
 */
@Data
public class UpdateUserPasswordCommand {

    private Long userId;
    private String newPassword;
    private String oldPassword;

}
