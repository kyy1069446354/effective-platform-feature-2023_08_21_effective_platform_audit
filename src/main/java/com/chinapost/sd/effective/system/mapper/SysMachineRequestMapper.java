package com.chinapost.sd.effective.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.po.SysMachineRequestPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;


/**
 * 机器审核工单表Mapper
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysMachineRequestMapper extends BaseMapperEx<SysMachineRequestPO> {


 Page<SysMachineRequestPO> selectByPage(@Param("page") Page<SysMachineRequestPO> page,
                                @Param("query") SysMachineRequestPageQuery query);


 List<SysMachineRequestPO> selectByList(@Param("query") SysMachineRequestListQuery query);

}
