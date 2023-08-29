package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysConfigPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysConfigListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysConfigPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 参数配置表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysConfigMapper extends BaseMapperEx<SysConfigPO> {


 Page<SysConfigPO> selectByPage(@Param("page") Page<SysConfigPO> page,
                                @Param("query") SysConfigPageQuery query);


 List<SysConfigPO> selectByList(@Param("query") SysConfigListQuery query);

 SysConfigPO selectConfigByKey(@Param("configKey") String configKey);
}
