package com.chinapost.sd.effective.tool.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.tool.domain.po.GenTablePO;
import com.chinapost.sd.effective.tool.domain.vo.PageGenTableVO;
import com.chinapost.sd.effective.tool.domain.vo.query.GenTablePageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 代码生成业务表Mapper
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Mapper
public interface GenTableMapper extends BaseMapperEx<GenTablePO> {


 Page<PageGenTableVO> selectByPage(@Param("page") Page<GenTablePO> page,
                                   @Param("query") GenTablePageQuery query);

 List<GenTablePO> selectByList(@Param("query") GenTablePageQuery query);


}
