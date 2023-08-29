package com.chinapost.sd.effective.system.convert;

import cn.hutool.core.util.HexUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.domain.dto.ImportSysDept;
import com.chinapost.sd.effective.system.domain.dto.SysDept;
import com.chinapost.sd.effective.system.domain.po.SysDeptPO;
import com.chinapost.sd.effective.system.domain.vo.ImportSysDeptVO;
import com.chinapost.sd.effective.system.domain.vo.SysDeptTreeNodeVO;
import com.chinapost.sd.effective.system.domain.vo.SysDeptVO;
import com.chinapost.sd.effective.system.domain.vo.command.SysDeptAddCommand;
import com.chinapost.sd.effective.system.domain.vo.command.SysDeptUpdateCommand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


/**
 * <p>
 * SysDeptConvert
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Mapper
public interface SysDeptConvert {
    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);

    SysDept po2Dto(SysDeptPO po);

    SysDeptVO dto2Vo(SysDept dto);

    SysDept vo2Dto(SysDeptVO vo);

    default ImportSysDept vo2Dto(ImportSysDeptVO vo){
        ImportSysDept dept = new ImportSysDept();
        if (vo.getId().contains("A")){
            dept.setId(HexUtil.hexToLong(vo.getId()));
        }else {
            dept.setId(Long.valueOf(vo.getId()));
        }
        if (vo.getParentId() == null || vo.getParentId().isEmpty()){
            dept.setParentId(0L);
        } else if (vo.getParentId().contains("A")){
            dept.setParentId(HexUtil.hexToLong(vo.getParentId()));
        }else {
            dept.setParentId(Long.valueOf(vo.getParentId()));
        }
        dept.setCode(vo.getCode());
        dept.setName(vo.getName());
        return dept;
    }

    SysDeptPO dto2Po(SysDept dto);

    SysDeptTreeNodeVO po2Vo(SysDeptPO po);

    Page<SysDept> poPage2DtoPage(Page<SysDeptPO> poPage);

    Page<SysDeptVO> dtoPage2VoPage(Page<SysDept> dtoPage);

    List<SysDept> poList2DtoList(List<SysDeptPO> poList);

    List<SysDeptVO> dtoList2VoList(List<SysDept> dtoList);

    List<SysDeptTreeNodeVO> poList2VoList(List<SysDeptPO> poList);

    List<ImportSysDept> voList2DtoList(List<ImportSysDeptVO> voList);

    SysDept convert(SysDeptUpdateCommand command);

    SysDept convert(SysDeptAddCommand command);


}
