package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysMenu;
import com.chinapost.sd.effective.system.domain.po.SysMenuPO;
import com.chinapost.sd.effective.system.domain.vo.SysMenuVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysMenuAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysMenuUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysMenuConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysMenuConvert {
    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);

    SysMenu po2Dto(SysMenuPO po);

    SysMenuVO dto2Vo(SysMenu dto);

    SysMenu vo2Dto(SysMenuVO vo);

    SysMenuPO dto2Po(SysMenu dto);

    Page<SysMenu> poPage2DtoPage(Page<SysMenuPO> poPage);

    Page<SysMenuVO> dtoPage2VoPage(Page<SysMenu> dtoPage);

    List<SysMenu> poList2DtoList(List<SysMenuPO> poList);

    List<SysMenuVO> dtoList2VoList(List<SysMenu> dtoList);

    SysMenu convert(SysMenuUpdateCommand command);

    SysMenu convert(SysMenuAddCommand command);
}
