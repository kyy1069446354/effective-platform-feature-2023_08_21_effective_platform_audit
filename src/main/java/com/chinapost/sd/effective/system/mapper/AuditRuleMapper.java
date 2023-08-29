package com.chinapost.sd.effective.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.po.AuditRulePO;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRuleListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.AuditRulePageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;


/**
 * 审核规则表Mapper
 *
 * @author admin
 * @since 2023-08-29
 */
@Mapper
public interface AuditRuleMapper extends BaseMapperEx<AuditRulePO> {


 Page<AuditRulePO> selectByPage(@Param("page") Page<AuditRulePO> page,
                                @Param("query") AuditRulePageQuery query);


 List<AuditRulePO> selectByList(@Param("query") AuditRuleListQuery query);

}
