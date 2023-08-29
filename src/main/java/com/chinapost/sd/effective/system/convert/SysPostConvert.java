package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysPost;
import com.chinapost.sd.effective.system.domain.po.SysPostPO;
import com.chinapost.sd.effective.system.domain.vo.SysPostVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysPostAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysPostUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysPostConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysPostConvert {
    SysPostConvert INSTANCE = Mappers.getMapper(SysPostConvert.class);

    SysPost po2Dto(SysPostPO po);

    SysPostVO dto2Vo(SysPost dto);

    SysPost vo2Dto(SysPostVO vo);

    SysPostPO dto2Po(SysPost dto);

    Page<SysPost> poPage2DtoPage(Page<SysPostPO> poPage);

    Page<SysPostVO> dtoPage2VoPage(Page<SysPost> dtoPage);

    List<SysPost> poList2DtoList(List<SysPostPO> poList);

    List<SysPostVO> dtoList2VoList(List<SysPost> dtoList);

    SysPost convert(SysPostUpdateCommand command);

    SysPost convert(SysPostAddCommand command);
}
