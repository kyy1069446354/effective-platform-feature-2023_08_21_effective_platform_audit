package com.chinapost.sd.effective.common.login.controller;

import com.chinapost.sd.effective.common.login.convert.LoginConvert;
import com.chinapost.sd.effective.common.login.service.LoginService;
import com.chinapost.sd.effective.common.login.service.RouteService;
import com.chinapost.sd.effective.common.login.vo.*;
import com.chinapost.sd.effective.system.domain.po.SysUserPO;
import com.chinapost.sd.effective.system.mapper.SysUserMapper;
import com.chinapost.sd.boot.infrastructure.base.ResponseResult;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 首页
 *
 * @author tangyang
 */
@Tag(name = "登录API", description = "登录相关接口")
@RestController
public class LoginController {
    private final LoginConvert loginConvert = LoginConvert.INSTANCE;

    @Autowired
    private LoginService loginService;
    @Autowired
    private RouteService routeService;
    @Autowired
    private SysUserMapper sysUserMapper;
    /**
     * 登录方法
     *
     * @param loginRequest 登录信息
     * @return 结果
     */
    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseResult<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        // 生成令牌
        String token = loginService.login(loginRequest);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return ResponseResult.success(loginResponse);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/getLoginUserInfo")
    public ResponseResult<LoginUserInfoVO> getLoginUserInfo() {
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        LoginUserInfoVO loginUserVo = loginConvert.convert(loginUser);
        SysUserPO userPo = sysUserMapper.selectById(loginUser.getUserId());
        loginUserVo.setAvatar(userPo.getAvatar());
        return ResponseResult.success(loginUserVo);
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @Operation(summary = "获取用户对应的菜单路由", description = "用于动态生成路由")
    @GetMapping("/getRouters")
    public ResponseResult<List<RouterVO>> getRouters() {
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        List<RouterVO> routerTree = routeService.getRouterTree(loginUser);
        return ResponseResult.success(routerTree);
    }

    /**
     * 生成验证码
     */
    @Operation(summary = "验证码")
    @GetMapping("/captchaImage")
    public ResponseResult<CaptchaImgVO> getCaptchaImg() {
        CaptchaImgVO captchaImg = loginService.generateCaptchaImg();
        return ResponseResult.success(captchaImg);
    }
}
