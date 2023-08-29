package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import com.chinapost.sd.boot.infrastructure.thread.AsyncExecutor;
import com.chinapost.sd.boot.infrastructure.utils.IPUtils;
import com.chinapost.sd.boot.infrastructure.utils.ServletHolderUtils;
import com.chinapost.sd.effective.system.constant.LoginStatusEnum;
import com.chinapost.sd.effective.system.convert.SysLoginInfoConvert;
import com.chinapost.sd.effective.system.domain.dto.SysLoginInfo;
import com.chinapost.sd.effective.system.domain.po.SysLoginInfoPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysLoginInfoPageQuery;
import com.chinapost.sd.effective.system.mapper.SysLoginInfoMapper;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * SysLoginInfoService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Slf4j
@Service
public class SysLoginInfoService {
    private final SysLoginInfoConvert sysLoginInfoConvert = SysLoginInfoConvert.INSTANCE;

    @Resource
    private SysLoginInfoMapper sysLoginInfoMapper;

    public SysLoginInfo getById(Long id) {
        SysLoginInfoPO po = sysLoginInfoMapper.selectById(id);
        return sysLoginInfoConvert.po2Dto(po);
    }

    public Page<SysLoginInfo> page(SysLoginInfoPageQuery pageQuery) {
        Page<SysLoginInfoPO> page = pageQuery.toPage();
        Page<SysLoginInfoPO> poPage = sysLoginInfoMapper.selectByPage(page, pageQuery);
        return sysLoginInfoConvert.poPage2DtoPage(poPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids) {
        sysLoginInfoMapper.deleteBatchIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void clear() {
        sysLoginInfoMapper.deleteAll();
    }

    public void addLoginInfo(final String username, final LoginStatusEnum loginStatusEnum, final String message) {
        // logout的时候这样拿不到request
        HttpServletRequest request = ServletHolderUtils.getRequest();
        addLoginInfo(username, loginStatusEnum, message, request);
    }

    public void addLogoutInfo(final String username, HttpServletRequest request) {
        addLoginInfo(username, LoginStatusEnum.LOGOUT, LoginStatusEnum.LOGOUT.description(), request);
    }

    private void addLoginInfo(final String username, final LoginStatusEnum loginStatusEnum, final String message, HttpServletRequest request) {
        AsyncExecutor.execute(() -> {
            try {
                final UserAgent userAgent = UserAgent.parseUserAgentString(
                        request.getHeader("User-Agent"));
                // 获取客户端浏览器
                final String browser = userAgent.getBrowser() != null ? userAgent.getBrowser().getName() : "";
                final String ip = IPUtils.getIpAddr(request);
                final String address = "";
                // 获取客户端操作系统
                final String os = userAgent.getOperatingSystem() != null ? userAgent.getOperatingSystem().getName() : "";

                log.info("login: ip: {}, address: {}, username: {}, loginStatusEnum: {}, message: {}", ip, address, username,
                        loginStatusEnum, message);
                SysLoginInfoPO loginInfo = new SysLoginInfoPO();
                loginInfo.setId(IdGenerator.generate());
                loginInfo.setUsername(username);
                loginInfo.setIpAddress(ip);
                loginInfo.setLoginLocation(address);
                loginInfo.setBrowser(browser);
                loginInfo.setOperationSystem(os);
                loginInfo.setMsg(message);
                loginInfo.setLoginTime(LocalDateTime.now());
                loginInfo.setStatus(loginStatusEnum.getValue());
                // 插入数据
                sysLoginInfoMapper.save(loginInfo);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        });
    }
}
