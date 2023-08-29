package com.chinapost.sd.effective.system.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.SysOperationLog;
import com.chinapost.sd.effective.system.domain.po.SysOperationLogPO;
import com.chinapost.sd.effective.system.domain.vo.SysOperationLogVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysOperationLogAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysOperationLogUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysOperationLogConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysOperationLogConvert {
    SysOperationLogConvert INSTANCE = Mappers.getMapper(SysOperationLogConvert.class);

    SysOperationLog po2Dto(SysOperationLogPO po);

    SysOperationLogVO dto2Vo(SysOperationLog dto);

    SysOperationLog vo2Dto(SysOperationLogVO vo);

    SysOperationLogPO dto2Po(SysOperationLog dto);

    Page<SysOperationLog> poPage2DtoPage(Page<SysOperationLogPO> poPage);

    Page<SysOperationLogVO> dtoPage2VoPage(Page<SysOperationLog> dtoPage);

    List<SysOperationLog> poList2DtoList(List<SysOperationLogPO> poList);

    List<SysOperationLogVO> dtoList2VoList(List<SysOperationLog> dtoList);

    SysOperationLog convert(SysOperationLogUpdateCommand command);

    SysOperationLog convert(SysOperationLogAddCommand command);
}
