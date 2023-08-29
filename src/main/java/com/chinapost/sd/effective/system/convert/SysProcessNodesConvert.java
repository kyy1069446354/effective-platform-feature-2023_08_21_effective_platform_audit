package com.chinapost.sd.effective.system.convert;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chinapost.sd.effective.system.domain.dto.SysProcessNodes;
import com.chinapost.sd.effective.system.domain.po.SysProcessNodesPO;
import com.chinapost.sd.effective.system.domain.vo.SysProcessNodesVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysProcessNodesAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysProcessNodesUpdateCommand;
import com.chinapost.sd.effective.system.domain.vo.query.SysProcessNodesPageQuery;


/**
 * SysProcessNodesConvert
 *
 * @author admin
 * @since 2023-08-28
 */
@Mapper
public interface SysProcessNodesConvert {
    SysProcessNodesConvert INSTANCE = Mappers.getMapper(SysProcessNodesConvert.class);

    SysProcessNodes po2Dto(SysProcessNodesPO po);

    SysProcessNodesVO dto2Vo(SysProcessNodes dto);

    SysProcessNodes vo2Dto(SysProcessNodesVO vo);

    SysProcessNodesPO dto2Po(SysProcessNodes dto);

    Page<SysProcessNodes> poPage2DtoPage(Page<SysProcessNodesPO> poPage);

    Page<SysProcessNodesVO> dtoPage2VoPage(Page<SysProcessNodes> dtoPage);

    List<SysProcessNodes> poList2DtoList(List<SysProcessNodesPO> poList);

    List<SysProcessNodesVO> dtoList2VoList(List<SysProcessNodes> dtoList);

    SysProcessNodes convert(SysProcessNodesUpdateCommand command);

    SysProcessNodes convert(SysProcessNodesAddCommand command);
}
