package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysRolePO;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysRolePageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;


/**
 * 角色信息表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysRoleMapper extends BaseMapperEx<SysRolePO> {


 Page<SysRolePO> selectByPage(@Param("page") Page<SysRolePO> page,
                                @Param("query") SysRolePageQuery query);


 List<SysRolePO> selectByList(@Param("query") SysRoleListQuery query);

 /**
  * 根据权限字符查询
  */
 List<SysRolePO> selectByRoles(@Param("roles") Set<String> roles);
}
