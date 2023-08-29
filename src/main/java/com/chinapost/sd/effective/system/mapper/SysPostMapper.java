package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysPostPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 岗位信息表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysPostMapper extends BaseMapperEx<SysPostPO> {


 Page<SysPostPO> selectByPage(@Param("page") Page<SysPostPO> page,
                                @Param("query") SysPostPageQuery query);


 List<SysPostPO> selectByList(@Param("query") SysPostListQuery query);

}
