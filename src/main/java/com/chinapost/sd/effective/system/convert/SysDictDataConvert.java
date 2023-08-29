package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysDictData;
import com.chinapost.sd.effective.system.domain.po.SysDictDataPO;
import com.chinapost.sd.effective.system.domain.vo.SysDictDataVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDataAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictDataUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * SysDictDataConvert
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Mapper
public interface SysDictDataConvert {
    SysDictDataConvert INSTANCE = Mappers.getMapper(SysDictDataConvert.class);

    SysDictData po2Dto(SysDictDataPO po);

    SysDictDataVO dto2Vo(SysDictData dto);

    SysDictData vo2Dto(SysDictDataVO vo);

    SysDictDataPO dto2Po(SysDictData dto);

    Page<SysDictData> poPage2DtoPage(Page<SysDictDataPO> poPage);

    Page<SysDictDataVO> dtoPage2VoPage(Page<SysDictData> dtoPage);

    List<SysDictData> poList2DtoList(List<SysDictDataPO> poList);

    List<SysDictDataVO> dtoList2VoList(List<SysDictData> dtoList);

    SysDictData convert(SysDictDataUpdateCommand command);

    SysDictData convert(SysDictDataAddCommand command);
}
