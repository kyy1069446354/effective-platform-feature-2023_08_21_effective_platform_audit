package com.chinapost.sd.effective.system.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import com.chinapost.sd.boot.commons.id.IdGenerator;

import com.chinapost.sd.effective.system.convert.AuditRuleConvert;
import com.chinapost.sd.effective.system.domain.dto.AuditRule;
import com.chinapost.sd.effective.system.domain.po.AuditRulePO;
import com.chinapost.sd.effective.system.mapper.AuditRuleMapper;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRuleListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRulePageQuery;

/**
 * AuditRuleService类
 *
 * @author admin
 * @since 2023-08-30
 */
@Service
public class AuditRuleService {
    private final AuditRuleConvert auditRuleConvert = AuditRuleConvert.INSTANCE;

    @Resource
    private AuditRuleMapper auditRuleMapper;

    public AuditRule getById(Long id){
        AuditRulePO po = auditRuleMapper.selectById(id);
        return auditRuleConvert.po2Dto(po);
    }

    public Page<AuditRule> page(AuditRulePageQuery pageQuery){
        Page<AuditRulePO> poPage = auditRuleMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return auditRuleConvert.poPage2DtoPage(poPage);
    }

    public List<AuditRule> list(AuditRuleListQuery listQuery){
        List<AuditRulePO> poList = auditRuleMapper.selectByList(listQuery);
        return auditRuleConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(AuditRule auditRule){
        auditRule.setId(IdGenerator.generate());
        AuditRulePO auditRulePo = auditRuleConvert.dto2Po(auditRule);
        auditRuleMapper.save(auditRulePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(AuditRule auditRule){
        AuditRulePO auditRulePo = auditRuleConvert.dto2Po(auditRule);
        auditRuleMapper.update(auditRulePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        auditRuleMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        auditRuleMapper.deleteBatchIds(ids);
    }
}
