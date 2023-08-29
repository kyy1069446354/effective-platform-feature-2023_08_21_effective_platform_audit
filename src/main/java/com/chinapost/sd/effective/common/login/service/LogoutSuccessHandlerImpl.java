package com.chinapost.sd.effective.common.login.service;

import com.chinapost.sd.effective.system.service.SysLoginInfoService;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.security.service.JwtTokenService;
import com.chinapost.sd.boot.infrastructure.utils.ServletHolderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/20
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SysLoginInfoService sysLoginInfoService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUserInfo loginUser = jwtTokenService.getLoginUser(request);
        if (loginUser != null) {
            // 删除用户缓存记录
            jwtTokenService.delete(loginUser);
            sysLoginInfoService.addLogoutInfo(loginUser.getUsername(), request);
        }
        ServletHolderUtils.renderString(response, ResponseResult.success("退出成功").toJson());

    }
}
