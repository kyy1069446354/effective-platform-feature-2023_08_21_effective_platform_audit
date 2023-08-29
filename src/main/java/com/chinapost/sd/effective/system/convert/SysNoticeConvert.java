package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysNotice;
import com.chinapost.sd.effective.system.domain.po.SysNoticePO;
import com.chinapost.sd.effective.system.domain.vo.SysNoticeVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysNoticeAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysNoticeUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysNoticeConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysNoticeConvert {
    SysNoticeConvert INSTANCE = Mappers.getMapper(SysNoticeConvert.class);

    SysNotice po2Dto(SysNoticePO po);

    SysNoticeVO dto2Vo(SysNotice dto);

    SysNotice vo2Dto(SysNoticeVO vo);

    SysNoticePO dto2Po(SysNotice dto);

    Page<SysNotice> poPage2DtoPage(Page<SysNoticePO> poPage);

    Page<SysNoticeVO> dtoPage2VoPage(Page<SysNotice> dtoPage);

    List<SysNotice> poList2DtoList(List<SysNoticePO> poList);

    List<SysNoticeVO> dtoList2VoList(List<SysNotice> dtoList);

    SysNotice convert(SysNoticeUpdateCommand command);

    SysNotice convert(SysNoticeAddCommand command);
}
