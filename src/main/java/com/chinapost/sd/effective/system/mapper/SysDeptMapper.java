package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysDeptPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDeptPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import com.chinapost.sd.boot.infrastructure.spi.IDataScope;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 部门表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysDeptMapper extends BaseMapperEx<SysDeptPO> {

   List<SysDeptPO> selectByList(@Param("query") SysDeptListQuery query);


   List<SysDeptPO> selectAll();

   SysDeptPO selectOneById(@Param("id") Long id);

   /**
    * 查询自己数据权限内的数据
    */
   List<SysDeptPO> selectByListInDataScope(@Param("dataScope")IDataScope dataScope);

   Page<SysDeptPO> selectByPage(@Param("page") Page<SysDeptPO> page,
                                @Param("query") SysDeptPageQuery query);


   void deleteAll();
}
