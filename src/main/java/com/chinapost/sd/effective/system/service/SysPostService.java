package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chinapost.sd.effective.system.convert.SysPostConvert;
import com.chinapost.sd.effective.system.domain.dto.SysPost;
import com.chinapost.sd.effective.system.domain.po.SysPostPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysPostPageQuery;
import com.chinapost.sd.effective.system.mapper.SysPostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * SysPostService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysPostService {
    private final SysPostConvert sysPostConvert = SysPostConvert.INSTANCE;

    @Resource
    private SysPostMapper sysPostMapper;

    public SysPost getById(Long id){
        SysPostPO po = sysPostMapper.selectById(id);
        return sysPostConvert.po2Dto(po);
    }

    public Page<SysPost> page(SysPostPageQuery pageQuery){
        Page<SysPostPO> poPage = sysPostMapper.selectByPage(PageDTO.of(pageQuery.getPageNum(), pageQuery.getPageSize()), pageQuery);
        return sysPostConvert.poPage2DtoPage(poPage);
    }

    public List<SysPost> list(SysPostListQuery listQuery){
        List<SysPostPO> poList = sysPostMapper.selectByList(listQuery);
        return sysPostConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysPost sysPost){
        SysPostPO sysPostPo = sysPostConvert.dto2Po(sysPost);
        sysPostMapper.save(sysPostPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysPost sysPost){
        SysPostPO sysPostPo = sysPostConvert.dto2Po(sysPost);
        sysPostMapper.update(sysPostPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysPostMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysPostMapper.deleteBatchIds(ids);
    }
}
