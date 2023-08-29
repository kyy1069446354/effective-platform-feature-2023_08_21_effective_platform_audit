package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysLoginInfo;
import com.chinapost.sd.effective.system.domain.po.SysLoginInfoPO;
import com.chinapost.sd.effective.system.domain.vo.SysLoginInfoVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysLoginInfoAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysLoginInfoUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysLoginInfoConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysLoginInfoConvert {
    SysLoginInfoConvert INSTANCE = Mappers.getMapper(SysLoginInfoConvert.class);

    SysLoginInfo po2Dto(SysLoginInfoPO po);

    SysLoginInfoVO dto2Vo(SysLoginInfo dto);

    SysLoginInfo vo2Dto(SysLoginInfoVO vo);

    SysLoginInfoPO dto2Po(SysLoginInfo dto);

    Page<SysLoginInfo> poPage2DtoPage(Page<SysLoginInfoPO> poPage);

    Page<SysLoginInfoVO> dtoPage2VoPage(Page<SysLoginInfo> dtoPage);

    List<SysLoginInfo> poList2DtoList(List<SysLoginInfoPO> poList);

    List<SysLoginInfoVO> dtoList2VoList(List<SysLoginInfo> dtoList);

    SysLoginInfo convert(SysLoginInfoUpdateCommand command);

    SysLoginInfo convert(SysLoginInfoAddCommand command);
}
