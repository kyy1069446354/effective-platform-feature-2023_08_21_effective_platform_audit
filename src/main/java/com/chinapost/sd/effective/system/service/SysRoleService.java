package com.chinapost.sd.effective.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chinapost.sd.effective.system.convert.SysRoleConvert;
import com.chinapost.sd.effective.system.domain.dto.SysRole;
import com.chinapost.sd.effective.system.domain.po.SysRoleMenuPO;
import com.chinapost.sd.effective.system.domain.po.SysRolePO;
import com.chinapost.sd.effective.system.domain.po.SysUserRolePO;
import com.chinapost.sd.effective.system.domain.vo.query.SysRoleListQuery;
import com.chinapost.sd.effective.system.domain.vo.query.SysRolePageQuery;
import com.chinapost.sd.effective.system.mapper.SysRoleMapper;
import com.chinapost.sd.effective.system.mapper.SysRoleMenuMapper;
import com.chinapost.sd.effective.system.mapper.SysUserRoleMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * SysRoleService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysRoleService {
    private final SysRoleConvert sysRoleConvert = SysRoleConvert.INSTANCE;

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    public SysRole getById(Long id){
        SysRolePO po = sysRoleMapper.selectById(id);
        SysRole sysRole = sysRoleConvert.po2Dto(po);
        sysRole.setDeptIds(Arrays.stream(po.getDeptIdSet().split(","))
                .filter(StringUtils::isNotEmpty)
                .map(String::trim)
                .map(Long::valueOf)
                .collect(Collectors.toSet()));

        List<SysRoleMenuPO> sysRoleMenuPoList = sysRoleMenuMapper.selectByRoleId(sysRole.getId());
        Set<Long> menuIds = sysRoleMenuPoList.stream().map(SysRoleMenuPO::getMenuId).collect(Collectors.toSet());
        sysRole.setMenuIds(menuIds);
        return sysRole;
    }

    public Page<SysRole> page(SysRolePageQuery pageQuery){
        Page<SysRolePO> poPage = sysRoleMapper.selectByPage(PageDTO.of(pageQuery.getPageNum(), pageQuery.getPageSize()), pageQuery);
        return sysRoleConvert.poPage2DtoPage(poPage);
    }

    public List<SysRole> list(SysRoleListQuery listQuery){
        List<SysRolePO> poList = sysRoleMapper.selectByList(listQuery);
        return sysRoleConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysRole sysRole){
        sysRole.setId(IdGenerator.generate());
        SysRolePO sysRolePo = sysRoleConvert.dto2Po(sysRole);
        sysRoleMapper.save(sysRolePo);

        if (CollectionUtils.isEmpty(sysRole.getMenuIds())){
            return;
        }
        for (Long menuId : sysRole.getMenuIds()) {
            SysRoleMenuPO sysRoleMenu = new SysRoleMenuPO();
            sysRoleMenu.setId(IdGenerator.generate());
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(sysRole.getId());
            sysRoleMenuMapper.save(sysRoleMenu);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysRole sysRole){
        SysRolePO sysRolePo = sysRoleConvert.dto2Po(sysRole);
        sysRoleMapper.update(sysRolePo);

        List<SysRoleMenuPO> sysRoleMenuPoList = sysRoleMenuMapper.selectByRoleId(sysRole.getId());
        Set<Long> oldMenuIds = sysRoleMenuPoList.stream().map(SysRoleMenuPO::getMenuId).collect(Collectors.toSet());
        Set<Long> newMenuIds = sysRole.getMenuIds();


        if (newMenuIds == null){
            newMenuIds = Collections.emptySet();
        }

        Collection<Long> toAdd = CollectionUtils.subtract(newMenuIds, oldMenuIds);
        Collection<Long> toDelete = CollectionUtils.subtract(oldMenuIds, newMenuIds);

        if (!toDelete.isEmpty()){
            List<SysRoleMenuPO> toDeletePoList = sysRoleMenuPoList.stream()
                    .filter(po -> toDelete.contains(po.getMenuId()))
                    .collect(Collectors.toList());
            sysRoleMenuMapper.delete(toDeletePoList);
        }
        if (!toAdd.isEmpty()){
            for (Long menuId : toAdd) {
                SysRoleMenuPO sysRoleMenu = new SysRoleMenuPO();
                sysRoleMenu.setId(IdGenerator.generate());
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenu.setRoleId(sysRole.getId());
                sysRoleMenuMapper.save(sysRoleMenu);
            }
        }


    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysRoleMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysRoleMapper.deleteBatchIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeUserStatus(Long roleId, Integer status) {
        SysRolePO sysRolePo = new SysRolePO();
        sysRolePo.setStatus(status);
        sysRolePo.setId(roleId);
        sysRoleMapper.update(sysRolePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeDataScope(Long roleId, Integer dataScope, List<Long> deptIds) {
        SysRolePO sysRolePo = new SysRolePO();
        sysRolePo.setDataScope(dataScope);
        if(CollectionUtils.isEmpty(deptIds)){
            sysRolePo.setDeptIdSet("");
        }else {
            sysRolePo.setDeptIdSet(StringUtils.join( deptIds, ","));
        }
        sysRolePo.setId(roleId);
        sysRoleMapper.update(sysRolePo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void grant(Long roleId, List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)){
            return;
        }
        for (Long userId : userIds) {
            SysUserRolePO sysUserRole = new SysUserRolePO();
            sysUserRole.setId(IdGenerator.generate());
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            sysUserRoleMapper.save(sysUserRole);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void cancelGrant(Long roleId, List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)){
            return;
        }
        List<SysUserRolePO> sysUserRoleList = sysUserRoleMapper.selectByRoleId(roleId);
        Set<Long> toDelete = sysUserRoleList.stream()
                .filter(po -> userIds.contains(po.getUserId()))
                .map(SysUserRolePO::getId).collect(Collectors.toSet());
        if (toDelete.isEmpty()){
            return;
        }
        sysUserRoleMapper.deleteBatchIds(toDelete);
    }
}
