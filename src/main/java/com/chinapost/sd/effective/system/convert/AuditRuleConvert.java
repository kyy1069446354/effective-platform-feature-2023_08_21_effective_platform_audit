package com.chinapost.sd.effective.system.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.dto.AuditRule;
import com.chinapost.sd.effective.system.domain.po.AuditRulePO;
import com.chinapost.sd.effective.system.domain.vo.AuditRuleVO;
import com.chinapost.sd.effective.system.domain.vo.command.AuditRuleAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.AuditRuleUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRulePageQuery;


/**
 * AuditRuleConvert
 *
 * @author admin
 * @since 2023-08-30
 */
@Mapper
public interface AuditRuleConvert {
    AuditRuleConvert INSTANCE = Mappers.getMapper(AuditRuleConvert.class);

    AuditRule po2Dto(AuditRulePO po);

    AuditRuleVO dto2Vo(AuditRule dto);

    AuditRule vo2Dto(AuditRuleVO vo);

    AuditRulePO dto2Po(AuditRule dto);

    Page<AuditRule> poPage2DtoPage(Page<AuditRulePO> poPage);

    Page<AuditRuleVO> dtoPage2VoPage(Page<AuditRule> dtoPage);

    List<AuditRule> poList2DtoList(List<AuditRulePO> poList);

    List<AuditRuleVO> dtoList2VoList(List<AuditRule> dtoList);

    AuditRule convert(AuditRuleUpdateCommand command);

    AuditRule convert(AuditRuleAddCommand command);
}
