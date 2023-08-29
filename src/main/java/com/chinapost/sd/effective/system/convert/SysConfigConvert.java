package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysConfig;
import com.chinapost.sd.effective.system.domain.po.SysConfigPO;
import com.chinapost.sd.effective.system.domain.vo.SysConfigVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysConfigAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysConfigUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysConfigConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysConfigConvert {
    SysConfigConvert INSTANCE = Mappers.getMapper(SysConfigConvert.class);

    SysConfig po2Dto(SysConfigPO po);

    SysConfigVO dto2Vo(SysConfig dto);

    SysConfig vo2Dto(SysConfigVO vo);

    SysConfigPO dto2Po(SysConfig dto);

    Page<SysConfig> poPage2DtoPage(Page<SysConfigPO> poPage);

    Page<SysConfigVO> dtoPage2VoPage(Page<SysConfig> dtoPage);

    List<SysConfig> poList2DtoList(List<SysConfigPO> poList);

    List<SysConfigVO> dtoList2VoList(List<SysConfig> dtoList);

    SysConfig convert(SysConfigUpdateCommand command);

    SysConfig convert(SysConfigAddCommand command);
}
