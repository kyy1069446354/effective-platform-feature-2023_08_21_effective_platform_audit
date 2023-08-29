package com.chinapost.sd.effective.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.po.SysNoticePO;
import com.chinapost.sd.effective.system.domain.vo.query.SysNoticeListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysNoticePageQuery;
import com.chinapost.sd.boot.infrastructure.base.BaseMapperEx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 通知公告表Mapper
 *
 * @author tangyang
 * @since 2023-07-11
 */
@Mapper
public interface SysNoticeMapper extends BaseMapperEx<SysNoticePO> {


 Page<SysNoticePO> selectByPage(@Param("page") Page<SysNoticePO> page,
                                @Param("query") SysNoticePageQuery query);


 List<SysNoticePO> selectByList(@Param("query") SysNoticeListQuery query);

}
