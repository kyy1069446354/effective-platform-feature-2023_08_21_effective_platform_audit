package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysDict;
import com.chinapost.sd.effective.system.domain.po.SysDictPO;
import com.chinapost.sd.effective.system.domain.vo.SysDictVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDictUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * SysDictConvert
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Mapper
public interface SysDictConvert {
    SysDictConvert INSTANCE = Mappers.getMapper(SysDictConvert.class);

    SysDict po2Dto(SysDictPO po);

    SysDictVO dto2Vo(SysDict dto);

    SysDict vo2Dto(SysDictVO vo);

    SysDictPO dto2Po(SysDict dto);

    Page<SysDict> poPage2DtoPage(Page<SysDictPO> poPage);

    Page<SysDictVO> dtoPage2VoPage(Page<SysDict> dtoPage);

    List<SysDict> poList2DtoList(List<SysDictPO> poList);

    List<SysDictVO> dtoList2VoList(List<SysDict> dtoList);

    SysDict convert(SysDictUpdateCommand command);

    SysDict convert(SysDictAddCommand command);
}
