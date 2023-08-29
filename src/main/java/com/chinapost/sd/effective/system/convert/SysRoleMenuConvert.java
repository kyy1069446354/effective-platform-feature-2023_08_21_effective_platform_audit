package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysRoleMenu;
import com.chinapost.sd.effective.system.domain.po.SysRoleMenuPO;
import com.chinapost.sd.effective.system.domain.vo.SysRoleMenuVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleMenuAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleMenuUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysRoleMenuConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysRoleMenuConvert {
    SysRoleMenuConvert INSTANCE = Mappers.getMapper(SysRoleMenuConvert.class);

    SysRoleMenu po2Dto(SysRoleMenuPO po);

    SysRoleMenuVO dto2Vo(SysRoleMenu dto);

    SysRoleMenu vo2Dto(SysRoleMenuVO vo);

    SysRoleMenuPO dto2Po(SysRoleMenu dto);

    Page<SysRoleMenu> poPage2DtoPage(Page<SysRoleMenuPO> poPage);

    Page<SysRoleMenuVO> dtoPage2VoPage(Page<SysRoleMenu> dtoPage);

    List<SysRoleMenu> poList2DtoList(List<SysRoleMenuPO> poList);

    List<SysRoleMenuVO> dtoList2VoList(List<SysRoleMenu> dtoList);

    SysRoleMenu convert(SysRoleMenuUpdateCommand command);

    SysRoleMenu convert(SysRoleMenuAddCommand command);
}
