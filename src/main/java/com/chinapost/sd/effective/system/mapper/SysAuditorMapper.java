package com.chinapost.sd.effective.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.po.SysAuditorPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;


/**
 * 审核员表Mapper
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysAuditorMapper extends BaseMapperEx<SysAuditorPO> {


 Page<SysAuditorPO> selectByPage(@Param("page") Page<SysAuditorPO> page,
                                @Param("query") SysAuditorPageQuery query);


 List<SysAuditorPO> selectByList(@Param("query") SysAuditorListQuery query);

}
