package com.chinapost.sd.effective.system.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.dto.SysAuditor;
import com.chinapost.sd.effective.system.domain.po.SysAuditorPO;
import com.chinapost.sd.effective.system.domain.vo.SysAuditorVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysAuditorAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysAuditorUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysAuditorPageQuery;


/**
 * SysAuditorConvert
 *
 * @author admin
 * @since 2023-08-29
 */
@Mapper
public interface SysAuditorConvert {
    SysAuditorConvert INSTANCE = Mappers.getMapper(SysAuditorConvert.class);

    SysAuditor po2Dto(SysAuditorPO po);

    SysAuditorVO dto2Vo(SysAuditor dto);

    SysAuditor vo2Dto(SysAuditorVO vo);

    SysAuditorPO dto2Po(SysAuditor dto);

    Page<SysAuditor> poPage2DtoPage(Page<SysAuditorPO> poPage);

    Page<SysAuditorVO> dtoPage2VoPage(Page<SysAuditor> dtoPage);

    List<SysAuditor> poList2DtoList(List<SysAuditorPO> poList);

    List<SysAuditorVO> dtoList2VoList(List<SysAuditor> dtoList);

    SysAuditor convert(SysAuditorUpdateCommand command);

    SysAuditor convert(SysAuditorAddCommand command);
}
