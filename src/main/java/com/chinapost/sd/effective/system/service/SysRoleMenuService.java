package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chinapost.sd.effective.system.convert.SysRoleMenuConvert;
import com.chinapost.sd.effective.system.domain.dto.SysRoleMenu;
import com.chinapost.sd.effective.system.domain.po.SysRoleMenuPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleMenuPageQuery;
import com.chinapost.sd.effective.system.mapper.SysRoleMenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * SysRoleMenuService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysRoleMenuService {
    private final SysRoleMenuConvert sysRoleMenuConvert = SysRoleMenuConvert.INSTANCE;

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    public SysRoleMenu getById(Long id){
        SysRoleMenuPO po = sysRoleMenuMapper.selectById(id);
        return sysRoleMenuConvert.po2Dto(po);
    }

    public Page<SysRoleMenu> page(SysRoleMenuPageQuery pageQuery){
        Page<SysRoleMenuPO> poPage = sysRoleMenuMapper.selectByPage(PageDTO.of(pageQuery.getPageNum(), pageQuery.getPageSize()), pageQuery);
        return sysRoleMenuConvert.poPage2DtoPage(poPage);
    }

    public List<SysRoleMenu> list(SysRoleMenuListQuery listQuery){
        List<SysRoleMenuPO> poList = sysRoleMenuMapper.selectByList(listQuery);
        return sysRoleMenuConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysRoleMenu sysRoleMenu){
        SysRoleMenuPO sysRoleMenuPo = sysRoleMenuConvert.dto2Po(sysRoleMenu);
        sysRoleMenuMapper.save(sysRoleMenuPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleMenu sysRoleMenu){
        SysRoleMenuPO sysRoleMenuPo = sysRoleMenuConvert.dto2Po(sysRoleMenu);
        sysRoleMenuMapper.update(sysRoleMenuPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysRoleMenuMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysRoleMenuMapper.deleteBatchIds(ids);
    }
}
