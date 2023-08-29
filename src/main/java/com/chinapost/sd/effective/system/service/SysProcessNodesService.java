package com.chinapost.sd.effective.system.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import com.chinapost.sd.boot.commons.id.IdGenerator;

import com.chinapost.sd.effective.system.convert.SysProcessNodesConvert;
import com.chinapost.sd.effective.system.domain.dto.SysProcessNodes;
import com.chinapost.sd.effective.system.domain.po.SysProcessNodesPO;
import com.chinapost.sd.effective.system.mapper.SysProcessNodesMapper;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesPageQuery;

/**
 * SysProcessNodesService类
 *
 * @author admin
 * @since 2023-08-28
 */
@Service
public class SysProcessNodesService {
    private final SysProcessNodesConvert sysProcessNodesConvert = SysProcessNodesConvert.INSTANCE;

    @Resource
    private SysProcessNodesMapper sysProcessNodesMapper;

    public SysProcessNodes getById(Long id){
        SysProcessNodesPO po = sysProcessNodesMapper.selectById(id);
        return sysProcessNodesConvert.po2Dto(po);
    }

    public Page<SysProcessNodes> page(SysProcessNodesPageQuery pageQuery){
        Page<SysProcessNodesPO> poPage = sysProcessNodesMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return sysProcessNodesConvert.poPage2DtoPage(poPage);
    }

    public List<SysProcessNodes> list(SysProcessNodesListQuery listQuery){
        List<SysProcessNodesPO> poList = sysProcessNodesMapper.selectByList(listQuery);
        return sysProcessNodesConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysProcessNodes sysProcessNodes){
        sysProcessNodes.setId(IdGenerator.generate());
        SysProcessNodesPO sysProcessNodesPo = sysProcessNodesConvert.dto2Po(sysProcessNodes);
        sysProcessNodesMapper.save(sysProcessNodesPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysProcessNodes sysProcessNodes){
        SysProcessNodesPO sysProcessNodesPo = sysProcessNodesConvert.dto2Po(sysProcessNodes);
        sysProcessNodesMapper.update(sysProcessNodesPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysProcessNodesMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysProcessNodesMapper.deleteBatchIds(ids);
    }
}
