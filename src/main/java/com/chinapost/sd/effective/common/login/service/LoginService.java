package com.chinapost.sd.effective.common.login.service;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import com.chinapost.sd.effective.common.login.vo.CaptchaImgVO;
import com.chinapost.sd.effective.common.login.vo.LoginRequest;
import com.chinapost.sd.effective.system.constant.LoginStatusEnum;
import com.chinapost.sd.effective.system.service.SysConfigService;
import com.chinapost.sd.effective.system.service.SysLoginInfoService;
import com.chinapost.sd.boot.infrastructure.cache.service.CacheService;
import com.chinapost.sd.boot.infrastructure.config.SdPostBootProperties;
import com.chinapost.sd.boot.infrastructure.constant.CaptureType;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.error.BusinessErrorCode;
import com.chinapost.sd.boot.infrastructure.error.InternalErrorCode;
import com.chinapost.sd.boot.infrastructure.exception.BusinessException;
import com.chinapost.sd.boot.infrastructure.security.service.JwtTokenService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
@Slf4j
public class LoginService {

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    private SdPostBootProperties sdPostBootProperties;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private SysConfigService sysConfigService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private SysLoginInfoService sysLoginInfoService;
    @Resource(name = "captchaProducerChar")
    private Producer captchaProducerChar;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    /**
     * 登录验证
     */
    public String login(LoginRequest loginRequest) {
        // 验证码开关
        if (sysConfigService.selectCaptchaEnabled()) {
            validateCaptcha(loginRequest.getUsername(), loginRequest.getCode(), loginRequest.getUuid());
        }
        // 用户验证
        try {
            String decryptPassword = decryptPassword(loginRequest.getPassword());
            // 该方法会去调用UserDetailsServiceImpl#loadUserByUsername  然后校验用户名和密码  认证鉴权
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(), decryptPassword));
            // 把当前登录用户 放入上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 这里获取的loginUser是UserDetailsServiceImpl#loadUserByUsername方法返回的LoginUser
            LoginUserInfo loginUser = (LoginUserInfo) authentication.getPrincipal();
            sysLoginInfoService.addLoginInfo(loginRequest.getUsername(), LoginStatusEnum.LOGIN_SUCCESS, LoginStatusEnum.LOGIN_SUCCESS.description());
            // 生成token
            return jwtTokenService.generateToken(loginUser);
        } catch (BadCredentialsException e) {
            sysLoginInfoService.addLoginInfo(loginRequest.getUsername(), LoginStatusEnum.PASSWORD_ERROR, LoginStatusEnum.PASSWORD_ERROR.description());
            throw BusinessException.build(e, BusinessErrorCode.LOGIN_WRONG_USER_PASSWORD);
        }catch (Exception e){
            Throwable cause = e.getCause();
            if (cause instanceof BusinessException){
                BusinessException businessException = (BusinessException) cause;
                if (BusinessErrorCode.USER_NON_EXIST.getCode() == businessException.getErrorCode().getCode()){
                    sysLoginInfoService.addLoginInfo(loginRequest.getUsername(), LoginStatusEnum.USER_NOT_EXITS, LoginStatusEnum.USER_NOT_EXITS.description());
                    throw BusinessException.build(e, BusinessErrorCode.LOGIN_WRONG_USER_PASSWORD);
                }else if (BusinessErrorCode.USER_IS_DISABLE.getCode() == businessException.getErrorCode().getCode()){
                    sysLoginInfoService.addLoginInfo(loginRequest.getUsername(), LoginStatusEnum.USER_IS_DISABLE, LoginStatusEnum.USER_IS_DISABLE.description());
                    throw BusinessException.build(e, BusinessErrorCode.USER_IS_DISABLE);
                }else {
                    sysLoginInfoService.addLoginInfo(loginRequest.getUsername(), LoginStatusEnum.LOGIN_FAIL, businessException.getMessage());
                }
                throw businessException;
            }
            sysLoginInfoService.addLoginInfo(loginRequest.getUsername(), LoginStatusEnum.LOGIN_FAIL, e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public CaptchaImgVO generateCaptchaImg() {
        CaptchaImgVO captchaImg = new CaptchaImgVO();

        boolean captchaEnabled = sysConfigService.selectCaptchaEnabled();

        captchaImg.setIsCaptchaOn(captchaEnabled);

        if (!captchaEnabled){
            return captchaImg;
        }

        String expression, answer = null;
        BufferedImage image = null;

        // 生成验证码
        String captchaType = sdPostBootProperties.getCaptcha().getType();
        if (CaptureType.MATH_TYPE.equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            String[] expressionAndAnswer = capText.split("@");
            expression = expressionAndAnswer[0];
            answer = expressionAndAnswer[1];
            image = captchaProducerMath.createImage(expression);
        }else if (CaptureType.CHAR_TYPE.equals(captchaType)) {
            expression = answer = captchaProducerChar.createText();
            image = captchaProducerChar.createImage(expression);
        }

        if (image == null) {
            throw BusinessException.build(InternalErrorCode.LOGIN_CAPTCHA_GENERATE_FAIL);
        }

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();

        cacheService.set(uuid, answer, sdPostBootProperties.getCaptcha().getExpire());
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImgUtil.writeJpg(image, os);

        captchaImg.setUuid(uuid);
        captchaImg.setImg(Base64.encode(os.toByteArray()));

        return captchaImg;
    }

    private String decryptPassword(String password) {
        byte[] decryptBytes = SecureUtil.rsa(sdPostBootProperties.getRsaPrivateKey(), null)
                .decrypt(Base64.decode(password), KeyType.PrivateKey);
        return StrUtil.str(decryptBytes, CharsetUtil.CHARSET_UTF_8);
    }


    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code 验证码
     * @param uuid 唯一标识
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String captcha = cacheService.get(uuid);
        if (captcha == null) {
            sysLoginInfoService.addLoginInfo(username, LoginStatusEnum.LOGIN_FAIL,
                    BusinessErrorCode.LOGIN_CAPTCHA_CODE_EXPIRE.name());
            throw BusinessException.build(BusinessErrorCode.LOGIN_CAPTCHA_CODE_EXPIRE);
        }
        cacheService.delete(uuid);
        if (!code.equalsIgnoreCase(captcha)) {
            sysLoginInfoService.addLoginInfo(username, LoginStatusEnum.LOGIN_FAIL,
                    BusinessErrorCode.LOGIN_CAPTCHA_CODE_WRONG.name());
            throw BusinessException.build(BusinessErrorCode.LOGIN_CAPTCHA_CODE_WRONG);
        }
    }
}
