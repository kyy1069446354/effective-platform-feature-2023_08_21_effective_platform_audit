package com.chinapost.sd.effective.system.service;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chinapost.sd.effective.system.convert.SysConfigConvert;
import com.chinapost.sd.effective.system.domain.dto.SysConfig;
import com.chinapost.sd.effective.system.domain.po.SysConfigPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysConfigPageQuery;
import com.chinapost.sd.effective.system.mapper.SysConfigMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * SysConfigService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysConfigService {
    private final SysConfigConvert sysConfigConvert = SysConfigConvert.INSTANCE;

    @Resource
    private SysConfigMapper sysConfigMapper;

    public SysConfig getById(Long id){
        SysConfigPO po = sysConfigMapper.selectById(id);
        return sysConfigConvert.po2Dto(po);
    }

    public Page<SysConfig> page(SysConfigPageQuery pageQuery){
        Page<SysConfigPO> poPage = sysConfigMapper.selectByPage(PageDTO.of(pageQuery.getPageNum(), pageQuery.getPageSize()), pageQuery);
        return sysConfigConvert.poPage2DtoPage(poPage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysConfig sysConfig){
        SysConfigPO sysConfigPo = sysConfigConvert.dto2Po(sysConfig);
        sysConfigMapper.save(sysConfigPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysConfig sysConfig){
        SysConfigPO sysConfigPo = sysConfigConvert.dto2Po(sysConfig);
        sysConfigMapper.update(sysConfigPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysConfigMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysConfigMapper.deleteBatchIds(ids);
    }

    public String selectConfigByKey(String configKey) {
        SysConfigPO config = sysConfigMapper.selectConfigByKey(configKey);
        if (config == null){
            return "";
        }else {
            return config.getValue();
        }
    }

    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StringUtils.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }
}
