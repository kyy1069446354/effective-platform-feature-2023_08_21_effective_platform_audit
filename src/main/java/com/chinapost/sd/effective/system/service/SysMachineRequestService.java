package com.chinapost.sd.effective.system.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import com.chinapost.sd.boot.commons.id.IdGenerator;

import com.chinapost.sd.effective.system.convert.SysMachineRequestConvert;
import com.chinapost.sd.effective.system.domain.dto.SysMachineRequest;
import com.chinapost.sd.effective.system.domain.po.SysMachineRequestPO;
import com.chinapost.sd.effective.system.mapper.SysMachineRequestMapper;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestPageQuery;

/**
 * SysMachineRequestService类
 *
 * @author admin
 * @since 2023-08-28
 */
@Service
public class SysMachineRequestService {
    private final SysMachineRequestConvert sysMachineRequestConvert = SysMachineRequestConvert.INSTANCE;

    @Resource
    private SysMachineRequestMapper sysMachineRequestMapper;

    public SysMachineRequest getById(Long id){
        SysMachineRequestPO po = sysMachineRequestMapper.selectById(id);
        return sysMachineRequestConvert.po2Dto(po);
    }

    public Page<SysMachineRequest> page(SysMachineRequestPageQuery pageQuery){
        Page<SysMachineRequestPO> poPage = sysMachineRequestMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return sysMachineRequestConvert.poPage2DtoPage(poPage);
    }

    public List<SysMachineRequest> list(SysMachineRequestListQuery listQuery){
        List<SysMachineRequestPO> poList = sysMachineRequestMapper.selectByList(listQuery);
        return sysMachineRequestConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysMachineRequest sysMachineRequest){
        sysMachineRequest.setId(IdGenerator.generate());
        SysMachineRequestPO sysMachineRequestPo = sysMachineRequestConvert.dto2Po(sysMachineRequest);
        sysMachineRequestMapper.save(sysMachineRequestPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysMachineRequest sysMachineRequest){
        SysMachineRequestPO sysMachineRequestPo = sysMachineRequestConvert.dto2Po(sysMachineRequest);
        sysMachineRequestMapper.update(sysMachineRequestPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysMachineRequestMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysMachineRequestMapper.deleteBatchIds(ids);
    }
}
