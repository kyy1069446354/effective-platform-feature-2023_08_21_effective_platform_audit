package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chinapost.sd.effective.system.convert.SysNoticeConvert;
import com.chinapost.sd.effective.system.domain.dto.SysNotice;
import com.chinapost.sd.effective.system.domain.po.SysNoticePO;
import com.chinapost.sd.effective.system.domain.vo.query.SysNoticeListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysNoticePageQuery;
import com.chinapost.sd.effective.system.mapper.SysNoticeMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * SysNoticeService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysNoticeService {
    private final SysNoticeConvert sysNoticeConvert = SysNoticeConvert.INSTANCE;

    @Resource
    private SysNoticeMapper sysNoticeMapper;

    public SysNotice getById(Long id){
        SysNoticePO po = sysNoticeMapper.selectById(id);
        return sysNoticeConvert.po2Dto(po);
    }

    public Page<SysNotice> page(SysNoticePageQuery pageQuery){
        Page<SysNoticePO> poPage = sysNoticeMapper.selectByPage(PageDTO.of(pageQuery.getPageNum(), pageQuery.getPageSize()), pageQuery);
        return sysNoticeConvert.poPage2DtoPage(poPage);
    }

    public List<SysNotice> list(SysNoticeListQuery listQuery){
        List<SysNoticePO> poList = sysNoticeMapper.selectByList(listQuery);
        return sysNoticeConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysNotice sysNotice){
        sysNotice.setId(IdGenerator.generate());
        SysNoticePO sysNoticePo = sysNoticeConvert.dto2Po(sysNotice);
        sysNoticeMapper.save(sysNoticePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysNotice sysNotice){
        SysNoticePO sysNoticePo = sysNoticeConvert.dto2Po(sysNotice);
        sysNoticeMapper.update(sysNoticePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysNoticeMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysNoticeMapper.deleteBatchIds(ids);
    }
}
