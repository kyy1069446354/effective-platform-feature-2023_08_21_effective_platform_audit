package com.chinapost.sd.effective.system.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import com.chinapost.sd.boot.commons.id.IdGenerator;

import com.chinapost.sd.effective.system.convert.SysAuditorConvert;
import com.chinapost.sd.effective.system.domain.dto.SysAuditor;
import com.chinapost.sd.effective.system.domain.po.SysAuditorPO;
import com.chinapost.sd.effective.system.mapper.SysAuditorMapper;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorPageQuery;

/**
 * SysAuditorService类
 *
 * @author admin
 * @since 2023-08-28
 */
@Service
public class SysAuditorService {
    private final SysAuditorConvert sysAuditorConvert = SysAuditorConvert.INSTANCE;

    @Resource
    private SysAuditorMapper sysAuditorMapper;

    public SysAuditor getById(Long id){
        SysAuditorPO po = sysAuditorMapper.selectById(id);
        return sysAuditorConvert.po2Dto(po);
    }

    public Page<SysAuditor> page(SysAuditorPageQuery pageQuery){
        Page<SysAuditorPO> poPage = sysAuditorMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return sysAuditorConvert.poPage2DtoPage(poPage);
    }

    public List<SysAuditor> list(SysAuditorListQuery listQuery){
        List<SysAuditorPO> poList = sysAuditorMapper.selectByList(listQuery);
        return sysAuditorConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysAuditor sysAuditor){
        sysAuditor.setId(IdGenerator.generate());
        SysAuditorPO sysAuditorPo = sysAuditorConvert.dto2Po(sysAuditor);
        sysAuditorMapper.save(sysAuditorPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysAuditor sysAuditor){
        SysAuditorPO sysAuditorPo = sysAuditorConvert.dto2Po(sysAuditor);
        sysAuditorMapper.update(sysAuditorPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysAuditorMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysAuditorMapper.deleteBatchIds(ids);
    }
}
