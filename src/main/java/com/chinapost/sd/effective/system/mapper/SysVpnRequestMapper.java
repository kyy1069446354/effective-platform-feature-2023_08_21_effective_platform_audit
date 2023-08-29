package com.chinapost.sd.effective.system.mapper;

import java.util.List;

import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import com.chinapost.sd.effective.system.domain.po.SysVpnRequestPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysVpnRequestPageQuery;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * vpn审核工单表Mapper
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysVpnRequestMapper extends BaseMapperEx<SysVpnRequestPO> {


 Page<SysVpnRequestPO> selectByPage(@Param("page") Page<SysVpnRequestPO> page,
                                @Param("query") SysVpnRequestPageQuery query);


 List<SysVpnRequestPO> selectByList(@Param("query") SysVpnRequestListQuery query);

}
