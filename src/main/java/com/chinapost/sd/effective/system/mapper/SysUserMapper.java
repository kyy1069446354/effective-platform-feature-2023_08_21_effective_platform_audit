package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysUserPO;
import com.chinapost.sd.effective.system.domain.vo.PageSysUserVO;
import com.chinapost.sd.effective.system.domain.vo.query.SysUserPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 * 用户信息表Mapper
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysUserMapper extends BaseMapperEx<SysUserPO> {


 SysUserPO getByUserName(@Param("username") String username);


 Page<PageSysUserVO> selectByPage(@Param("page") Page<SysUserPO> page,
                                  @Param("query") SysUserPageQuery query);


 List<SysUserPO> selectAllInDataScope(@Param("deptId") Long deptId);

 SysUserPO selectOneById(@Param("id") Long id);

 List<PageSysUserVO> selectByList(@Param("query") SysUserPageQuery query);

}
