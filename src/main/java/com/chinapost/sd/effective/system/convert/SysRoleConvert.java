package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysRole;
import com.chinapost.sd.effective.system.domain.po.SysRolePO;
import com.chinapost.sd.effective.system.domain.vo.SysRoleVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysRoleUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysRoleConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysRoleConvert {
    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);

    SysRole po2Dto(SysRolePO po);

    SysRoleVO dto2Vo(SysRole dto);

    SysRole vo2Dto(SysRoleVO vo);

    SysRolePO dto2Po(SysRole dto);

    Page<SysRole> poPage2DtoPage(Page<SysRolePO> poPage);

    Page<SysRoleVO> dtoPage2VoPage(Page<SysRole> dtoPage);

    List<SysRole> poList2DtoList(List<SysRolePO> poList);

    List<SysRoleVO> dtoList2VoList(List<SysRole> dtoList);

    SysRole convert(SysRoleUpdateCommand command);

    SysRole convert(SysRoleAddCommand command);
}
