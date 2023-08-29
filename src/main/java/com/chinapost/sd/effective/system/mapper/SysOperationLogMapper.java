package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysOperationLogPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysOperationLogPageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 操作日志记录Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysOperationLogMapper extends BaseMapperEx<SysOperationLogPO> {


 Page<SysOperationLogPO> selectByPage(@Param("page") Page<SysOperationLogPO> page,
                                @Param("query") SysOperationLogPageQuery query);


 List<SysOperationLogPO> selectByList(@Param("query") SysOperationLogListQuery query);
 void deleteAll();

}
