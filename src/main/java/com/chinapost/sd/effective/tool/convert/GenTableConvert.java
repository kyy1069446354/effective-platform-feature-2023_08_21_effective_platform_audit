package com.chinapost.sd.effective.tool.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.tool.domain.po.GenTablePO;
import com.chinapost.sd.effective.tool.domain.vo.GenTableVO;
import com.chinapost.sd.boot.domain.GenTable;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;




/**
 * GenTableConvert
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Mapper
public interface GenTableConvert {
    GenTableConvert INSTANCE = Mappers.getMapper(GenTableConvert.class);

    GenTable po2Dto(GenTablePO po);

    GenTableVO dto2Vo(GenTable dto);

    GenTable vo2Dto(GenTableVO vo);

    GenTablePO dto2Po(GenTable dto);

    Page<GenTable> poPage2DtoPage(Page<GenTablePO> poPage);

    Page<GenTableVO> dtoPage2VoPage(Page<GenTable> dtoPage);

    List<GenTable> poList2DtoList(List<GenTablePO> poList);

    List<GenTableVO> dtoList2VoList(List<GenTable> dtoList);

    List<GenTablePO> dtoList2PoList(List<GenTable> dtoList);






}
