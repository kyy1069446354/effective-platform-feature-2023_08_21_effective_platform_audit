package com.chinapost.sd.effective.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.po.SysProcessNodesPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;


/**
 * 流程节点表Mapper
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysProcessNodesMapper extends BaseMapperEx<SysProcessNodesPO> {


 Page<SysProcessNodesPO> selectByPage(@Param("page") Page<SysProcessNodesPO> page,
                                @Param("query") SysProcessNodesPageQuery query);


 List<SysProcessNodesPO> selectByList(@Param("query") SysProcessNodesListQuery query);

}
