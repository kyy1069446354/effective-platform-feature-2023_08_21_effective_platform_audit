package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysLoginInfoPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysLoginInfoPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 系统访问记录Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysLoginInfoMapper extends BaseMapperEx<SysLoginInfoPO> {


    Page<SysLoginInfoPO> selectByPage(@Param("page") Page<SysLoginInfoPO> page,
                                @Param("query") SysLoginInfoPageQuery query);


    void deleteAll();
}
