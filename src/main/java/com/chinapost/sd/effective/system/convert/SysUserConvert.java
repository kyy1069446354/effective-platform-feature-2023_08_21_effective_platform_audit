package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysUser;
import com.chinapost.sd.effective.system.domain.po.SysUserPO;
import com.chinapost.sd.effective.system.domain.vo.GetSysUserVO;
import com.chinapost.sd.effective.system.domain.vo.PageSysUserVO;
import com.chinapost.sd.effective.system.domain.vo.UserProfileVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysUserAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysUserUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysUserConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysUserConvert {
    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);

    SysUser po2Dto(SysUserPO po);

    GetSysUserVO dto2Vo(SysUser dto);

    SysUser vo2Dto(PageSysUserVO vo);

    SysUserPO dto2Po(SysUser dto);

    Page<SysUser> poPage2DtoPage(Page<SysUserPO> poPage);

    Page<PageSysUserVO> dtoPage2VoPage(Page<SysUser> dtoPage);

    List<SysUser> poList2DtoList(List<SysUserPO> poList);

    List<PageSysUserVO> dtoList2VoList(List<SysUser> dtoList);

    SysUser convert(SysUserUpdateCommand command);

    SysUser convert(SysUserAddCommand command);

    UserProfileVO po2Vo(SysUserPO po);
}
