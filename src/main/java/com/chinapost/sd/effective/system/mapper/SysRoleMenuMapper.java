package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysRoleMenuPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 角色和菜单关联表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapperEx<SysRoleMenuPO> {


 Page<SysRoleMenuPO> selectByPage(@Param("page") Page<SysRoleMenuPO> page,
                                @Param("query") SysRoleMenuPageQuery query);


 List<SysRoleMenuPO> selectByList(@Param("query") SysRoleMenuListQuery query);


 List<SysRoleMenuPO> selectByRoleId(@Param("roleId") Long roleId);

}
