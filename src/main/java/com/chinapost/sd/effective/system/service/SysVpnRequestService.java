package com.chinapost.sd.effective.system.service;

import java.util.List;
import javax.annotation.Resource;

import com.chinapost.sd.effective.system.convert.SysVpnRequestConvert;
import com.chinapost.sd.effective.system.domain.dto.SysVpnRequest;
import com.chinapost.sd.effective.system.domain.po.SysVpnRequestPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestPageQuery;
import com.chinapost.sd.effective.system.mapper.SysVpnRequestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.boot.commons.id.IdGenerator;
/**
 * SysVpnRequestService类
 *
 * @author admin
 * @since 2023-08-28
 */
@Service
public class SysVpnRequestService {
    private final SysVpnRequestConvert sysVpnRequestConvert = SysVpnRequestConvert.INSTANCE;

    @Resource
    private SysVpnRequestMapper sysVpnRequestMapper;

    public SysVpnRequest getById(Long id){
        SysVpnRequestPO po = sysVpnRequestMapper.selectById(id);
        return sysVpnRequestConvert.po2Dto(po);
    }

    public Page<SysVpnRequest> page(SysVpnRequestPageQuery pageQuery){
        Page<SysVpnRequestPO> poPage = sysVpnRequestMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return sysVpnRequestConvert.poPage2DtoPage(poPage);
    }

    public List<SysVpnRequest> list(SysVpnRequestListQuery listQuery){
        List<SysVpnRequestPO> poList = sysVpnRequestMapper.selectByList(listQuery);
        return sysVpnRequestConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysVpnRequest sysVpnRequest){
        sysVpnRequest.setId(IdGenerator.generate());
        SysVpnRequestPO sysVpnRequestPo = sysVpnRequestConvert.dto2Po(sysVpnRequest);
        sysVpnRequestMapper.save(sysVpnRequestPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysVpnRequest sysVpnRequest){
        SysVpnRequestPO sysVpnRequestPo = sysVpnRequestConvert.dto2Po(sysVpnRequest);
        sysVpnRequestMapper.update(sysVpnRequestPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysVpnRequestMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysVpnRequestMapper.deleteBatchIds(ids);
    }
}
