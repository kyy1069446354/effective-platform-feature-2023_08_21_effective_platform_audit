package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chinapost.sd.effective.system.convert.SysDictDataConvert;
import com.chinapost.sd.effective.system.domain.dto.SysDictData;
import com.chinapost.sd.effective.system.domain.po.SysDictDataPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictDataPageQuery;
import com.chinapost.sd.effective.system.mapper.SysDictDataMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * SysDictDataService类
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Service
public class SysDictDataService {
    private final SysDictDataConvert sysDictDataConvert = SysDictDataConvert.INSTANCE;

    @Resource
    private SysDictDataMapper sysDictDataMapper;

    public List<SysDictData> getByDictCode(String dictCode) {
        List<SysDictDataPO> poList = sysDictDataMapper.selectByDictCode(dictCode);
        return sysDictDataConvert.poList2DtoList(poList);
    }

    public SysDictData getById(Long id){
        SysDictDataPO po = sysDictDataMapper.selectById(id);
        return sysDictDataConvert.po2Dto(po);
    }

    public Page<SysDictData> page(SysDictDataPageQuery pageQuery){
        Page<SysDictDataPO> poPage = sysDictDataMapper.selectByPage(PageDTO.of(pageQuery.getPageNum(), pageQuery.getPageSize()), pageQuery);
        return sysDictDataConvert.poPage2DtoPage(poPage);
    }

    public List<SysDictData> list(SysDictDataListQuery listQuery){
        List<SysDictDataPO> poList = sysDictDataMapper.selectByList(listQuery);
        return sysDictDataConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysDictData sysDictData){
        sysDictData.setId(IdGenerator.generate());
        SysDictDataPO sysDictDataPo = sysDictDataConvert.dto2Po(sysDictData);
        sysDictDataMapper.save(sysDictDataPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysDictData sysDictData){
        SysDictDataPO sysDictDataPo = sysDictDataConvert.dto2Po(sysDictData);
        sysDictDataMapper.update(sysDictDataPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysDictDataMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysDictDataMapper.deleteBatchIds(ids);
    }
}
