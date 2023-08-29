package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysUserRolePO;
import com.chinapost.sd.effective.system.domain.vo.query.GrantedSysUserVO;
import com.chinapost.sd.effective.system.domain.vo.query.SysRolePageGrantedQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysUserRoleListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysUserRolePageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户和角色关联表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapperEx<SysUserRolePO> {


 Page<SysUserRolePO> selectByPage(@Param("page") Page<SysUserRolePO> page,
                                @Param("query") SysUserRolePageQuery query);


 List<SysUserRolePO> selectByList(@Param("query") SysUserRoleListQuery query);

 List<SysUserRolePO> selectByUserId(@Param("userId") Long userId);

 List<SysUserRolePO>  selectByRoleId(@Param("roleId") Long roleId);

 Page<GrantedSysUserVO> selectByPageGrantedUser(@Param("page") Page<SysUserRolePO> page,
                                                @Param("query") SysRolePageGrantedQuery query);

 Page<GrantedSysUserVO> selectByPageUnGrantedUser(@Param("page") Page<SysUserRolePO> page,
                                                   @Param("query") SysRolePageGrantedQuery query);
}
