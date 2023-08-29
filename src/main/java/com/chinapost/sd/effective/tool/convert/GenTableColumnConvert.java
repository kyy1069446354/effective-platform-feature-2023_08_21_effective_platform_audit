package com.chinapost.sd.effective.tool.convert;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.tool.domain.po.GenTableColumnPO;
import com.chinapost.sd.effective.tool.domain.vo.GenTableColumnVO;
import com.chinapost.sd.boot.domain.GenColumn;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;




/**
 * GenTableColumnConvert
 *
 * @author tangyang
 * @since 2023-07-14
 */
@Mapper
public interface GenTableColumnConvert {
    GenTableColumnConvert INSTANCE = Mappers.getMapper(GenTableColumnConvert.class);

    GenColumn po2Dto(GenTableColumnPO po);

    GenTableColumnVO dto2Vo(GenColumn dto);

    GenColumn vo2Dto(GenTableColumnVO vo);

    GenTableColumnPO dto2Po(GenColumn dto);

    Page<GenColumn> poPage2DtoPage(Page<GenTableColumnPO> poPage);

    Page<GenTableColumnVO> dtoPage2VoPage(Page<GenColumn> dtoPage);

    List<GenColumn> poList2DtoList(List<GenTableColumnPO> poList);

    List<GenTableColumnVO> dtoList2VoList(List<GenColumn> dtoList);

    List<GenTableColumnPO> dtoList2PoList(List<GenColumn> dtoList);

}
