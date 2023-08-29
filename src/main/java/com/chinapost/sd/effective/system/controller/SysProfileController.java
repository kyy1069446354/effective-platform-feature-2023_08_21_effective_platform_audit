package com.chinapost.sd.effective.system.controller;

import com.chinapost.sd.effective.system.constant.Constants;
import com.chinapost.sd.effective.system.domain.vo.UploadFileVO;
import com.chinapost.sd.effective.system.domain.vo.UserProfileVO;
import com.chinapost.sd.effective.system.domain.vo.command.UpdateProfileCommand;
import com.chinapost.sd.effective.system.domain.vo.command.UpdateUserPasswordCommand;
import com.chinapost.sd.effective.system.service.SysUserService;
import com.chinapost.sd.boot.infrastructure.annotations.AccessLog;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.constant.BusinessTypeEnum;
import com.chinapost.sd.boot.infrastructure.constant.WebMvcConstants;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.error.BusinessErrorCode;
import com.chinapost.sd.boot.infrastructure.exception.BusinessException;
import com.chinapost.sd.boot.infrastructure.storage.FileUploadService;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 个人信息 业务处理
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Tag(name = "个人信息API", description = "个人信息相关接口")
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController{

    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private FileUploadService fileUploadService;

    /**
     * 个人信息
     */
    @Operation(summary = "获取个人信息")
    @GetMapping
    public ResponseResult<UserProfileVO> profile() {
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        UserProfileVO userProfile = sysUserService.getUserProfile(loginUser.getUserId());
        return ResponseResult.success(userProfile);
    }

    /**
     * 修改用户
     */
    @Operation(summary = "修改个人信息")
    @AccessLog(title = "个人信息", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping
    public ResponseResult<Void> updateProfile(@RequestBody UpdateProfileCommand command) {
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        command.setUserId(loginUser.getUserId());
        sysUserService.updateUserProfile(command);
        return ResponseResult.success();
    }

    /**
     * 重置密码
     */
    @Operation(summary = "重置个人密码")
    @AccessLog(title = "个人信息", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/password")
    public ResponseResult<Void> updatePassword(@RequestBody UpdateUserPasswordCommand command) {
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        command.setUserId(loginUser.getUserId());
        sysUserService.updatePasswordBySelf(command);
        return ResponseResult.success();
    }

    /**
     * 头像上传
     */
    @Operation(summary = "修改个人头像")
    @AccessLog(title = "用户头像", businessType = BusinessTypeEnum.MODIFY)
    @PostMapping("/avatar")
    public ResponseResult<UploadFileVO> avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new BusinessException(BusinessErrorCode.USER_UPLOAD_FILE_FAILED);
        }
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        String avatarUrl = fileUploadService.upload(Constants.UPLOAD_AVATAR_PATH, file);

        sysUserService.updateUserAvatar(loginUser.getUserId(), WebMvcConstants.UPLOAD_RESOURCE_URL_PREFIX + avatarUrl);
        return ResponseResult.success(new UploadFileVO(WebMvcConstants.UPLOAD_RESOURCE_URL_PREFIX + avatarUrl));
    }
}
