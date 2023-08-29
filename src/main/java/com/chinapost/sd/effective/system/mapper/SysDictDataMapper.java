package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysDictDataPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 字典数据表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysDictDataMapper extends BaseMapperEx<SysDictDataPO> {


 Page<SysDictDataPO> selectByPage(@Param("page") Page<SysDictDataPO> page,
                                @Param("query") SysDictDataPageQuery query);


 List<SysDictDataPO> selectByList(@Param("query") SysDictDataListQuery query);

 List<SysDictDataPO> selectByDictCode(@Param("dictCode") String dictCode);
}
