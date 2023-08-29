package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysDictPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 字典类型表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysDictMapper extends BaseMapperEx<SysDictPO> {


 Page<SysDictPO> selectByPage(@Param("page") Page<SysDictPO> page,
                                @Param("query") SysDictPageQuery query);


 List<SysDictPO> selectByList(@Param("query") SysDictListQuery query);

}
