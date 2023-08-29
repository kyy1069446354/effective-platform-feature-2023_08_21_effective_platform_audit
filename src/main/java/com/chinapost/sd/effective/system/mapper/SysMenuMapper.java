package com.chinapost.sd.effective.system.mapper;

import com.chinapost.sd.effective.system.domain.po.SysMenuPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysMenuListQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 菜单权限表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysMenuMapper extends BaseMapperEx<SysMenuPO> {


 List<SysMenuPO> selectByList(@Param("query") SysMenuListQuery query);

 List<SysMenuPO> listAll();

 List<SysMenuPO> selectMenuListByUserId(@Param("userId") Long userId);
}
