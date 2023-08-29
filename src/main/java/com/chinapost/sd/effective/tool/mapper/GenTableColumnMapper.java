package com.chinapost.sd.effective.tool.mapper;

import com.chinapost.sd.effective.tool.domain.po.GenTableColumnPO;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 代码生成业务表字段Mapper
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Mapper
public interface GenTableColumnMapper extends BaseMapperEx<GenTableColumnPO> {
   List<GenTableColumnPO> selectByTableId(Long id);

   void deleteByTableIds(List<Long> tableIds);
}
