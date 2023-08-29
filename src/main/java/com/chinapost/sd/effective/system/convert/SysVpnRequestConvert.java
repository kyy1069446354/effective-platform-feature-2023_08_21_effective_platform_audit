package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysVpnRequest;
import com.chinapost.sd.effective.system.domain.po.SysVpnRequestPO;
import com.chinapost.sd.effective.system.domain.vo.SysVpnRequestVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysVpnRequestAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysVpnRequestUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * SysVpnRequestConvert
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysVpnRequestConvert {
    SysVpnRequestConvert INSTANCE = Mappers.getMapper(SysVpnRequestConvert.class);

    SysVpnRequest po2Dto(SysVpnRequestPO po);

    SysVpnRequestVO dto2Vo(SysVpnRequest dto);

    SysVpnRequest vo2Dto(SysVpnRequestVO vo);

    SysVpnRequestPO dto2Po(SysVpnRequest dto);

    Page<SysVpnRequest> poPage2DtoPage(Page<SysVpnRequestPO> poPage);

    Page<SysVpnRequestVO> dtoPage2VoPage(Page<SysVpnRequest> dtoPage);

    List<SysVpnRequest> poList2DtoList(List<SysVpnRequestPO> poList);

    List<SysVpnRequestVO> dtoList2VoList(List<SysVpnRequest> dtoList);

    SysVpnRequest convert(SysVpnRequestUpdateCommand command);

    SysVpnRequest convert(SysVpnRequestAddCommand command);
}
