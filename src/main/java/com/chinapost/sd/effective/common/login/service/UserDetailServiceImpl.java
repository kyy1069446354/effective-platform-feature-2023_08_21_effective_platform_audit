package com.chinapost.sd.effective.common.login.service;

import com.chinapost.sd.effective.common.login.convert.LoginConvert;
import com.chinapost.sd.effective.system.constant.UserStatusEnum;
import com.chinapost.sd.effective.system.domain.dto.SysUser;
import com.chinapost.sd.effective.system.service.SysUserService;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.error.BusinessErrorCode;
import com.chinapost.sd.boot.infrastructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/7
 */
@Slf4j
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;
    private final LoginConvert loginConvert = LoginConvert.INSTANCE;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser sysUser = sysUserService.getByUserName(username);
        if (sysUser == null) {
            log.info("登录用户：{} 不存在.", username);
            throw BusinessException.build(BusinessErrorCode.USER_NON_EXIST, username);
        }
        if (!Objects.equals(UserStatusEnum.NORMAL.getValue(), sysUser.getStatus())) {
            log.info("登录用户：{} 已被停用.", username);
            throw BusinessException.build(BusinessErrorCode.USER_IS_DISABLE, username);
        }
        LoginUserInfo loginUser = loginConvert.convert(sysUser);

        loginUser.setUserId(sysUser.getId());
        loginUser.setRoles(sysUserService.getRoles(sysUser.getId()));
        loginUser.setPermissions(sysUserService.getPermissions(sysUser.getId()));
        return loginUser;
    }
}
