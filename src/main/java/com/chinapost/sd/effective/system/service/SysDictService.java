package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chinapost.sd.effective.system.convert.SysDictConvert;
import com.chinapost.sd.effective.system.domain.dto.SysDict;
import com.chinapost.sd.effective.system.domain.po.SysDictPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysDictPageQuery;
import com.chinapost.sd.effective.system.mapper.SysDictMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * SysDictService类
 *
 * @author tangyang
 * @since 2023-07-10
 */
@Service
public class SysDictService {
    private final SysDictConvert sysDictConvert = SysDictConvert.INSTANCE;

    @Resource
    private SysDictMapper sysDictMapper;

    public SysDict getById(Long id){
        SysDictPO po = sysDictMapper.selectById(id);
        return sysDictConvert.po2Dto(po);
    }

    public Page<SysDict> page(SysDictPageQuery pageQuery){
        Page<SysDictPO> poPage = sysDictMapper.selectByPage(pageQuery.toPage(), pageQuery);
        return sysDictConvert.poPage2DtoPage(poPage);
    }

    public List<SysDict> list(SysDictListQuery listQuery){
        List<SysDictPO> poList = sysDictMapper.selectByList(listQuery);
        return sysDictConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysDict sysDict){
        sysDict.setId(IdGenerator.generate());
        SysDictPO sysDictPo = sysDictConvert.dto2Po(sysDict);
        sysDictMapper.save(sysDictPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysDict sysDict){
        SysDictPO sysDictPo = sysDictConvert.dto2Po(sysDict);
        sysDictMapper.update(sysDictPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysDictMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysDictMapper.deleteBatchIds(ids);
    }
}
