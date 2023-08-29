package com.chinapost.sd.effective.system.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.dto.SysMachineRequest;
import com.chinapost.sd.effective.system.domain.po.SysMachineRequestPO;
import com.chinapost.sd.effective.system.domain.vo.SysMachineRequestVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysMachineRequestAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMachineRequestUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysMachineRequestPageQuery;


/**
 * SysMachineRequestConvert
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysMachineRequestConvert {
    SysMachineRequestConvert INSTANCE = Mappers.getMapper(SysMachineRequestConvert.class);

    SysMachineRequest po2Dto(SysMachineRequestPO po);

    SysMachineRequestVO dto2Vo(SysMachineRequest dto);

    SysMachineRequest vo2Dto(SysMachineRequestVO vo);

    SysMachineRequestPO dto2Po(SysMachineRequest dto);

    Page<SysMachineRequest> poPage2DtoPage(Page<SysMachineRequestPO> poPage);

    Page<SysMachineRequestVO> dtoPage2VoPage(Page<SysMachineRequest> dtoPage);

    List<SysMachineRequest> poList2DtoList(List<SysMachineRequestPO> poList);

    List<SysMachineRequestVO> dtoList2VoList(List<SysMachineRequest> dtoList);

    SysMachineRequest convert(SysMachineRequestUpdateCommand command);

    SysMachineRequest convert(SysMachineRequestAddCommand command);
}
