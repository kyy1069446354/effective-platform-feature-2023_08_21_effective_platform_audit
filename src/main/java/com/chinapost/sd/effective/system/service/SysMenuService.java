package com.chinapost.sd.effective.system.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.chinapost.sd.effective.system.constant.MenuTypeEnum;
import com.chinapost.sd.effective.system.convert.SysMenuConvert;
import com.chinapost.sd.effective.system.domain.dto.SysMenu;
import com.chinapost.sd.effective.system.domain.po.SysMenuPO;
import com.chinapost.sd.effective.system.domain.vo.query.SysMenuListQuery;
import com.chinapost.sd.effective.system.mapper.SysMenuMapper;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * SysMenuService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysMenuService {
    private final SysMenuConvert sysMenuConvert = SysMenuConvert.INSTANCE;

    @Resource
    private SysMenuMapper sysMenuMapper;



    public List<Tree<Long>> getDropdownList(LoginUserInfo loginUser) {
        List<SysMenuPO> menuPoList =
                loginUser.getIsAdmin() ? sysMenuMapper.listAll() : sysMenuMapper.selectMenuListByUserId(loginUser.getUserId());

        return buildMenuTreeSelect(menuPoList);
    }

    /**
     * 构建前端所需要树结构
     *
     * @param menus 菜单列表
     * @return 树结构列表
     */
    public List<Tree<Long>> buildMenuTreeSelect(List<SysMenuPO> menus) {
        TreeNodeConfig config = new TreeNodeConfig();
        //默认为id可以不设置
        config.setIdKey("menuId");
        return TreeUtil.build(menus, 0L, config, (menu, tree) -> {
            // 也可以使用 tree.setId(dept.getId());等一些默认值
            tree.setId(menu.getId());
            tree.setParentId(menu.getParentId());
            tree.putExtra("label", menu.getName());
        });
    }


    public List<Tree<Long>> buildMenuTree(LoginUserInfo loginUser) {
        List<SysMenuPO> allMenus;
        if (loginUser.getIsAdmin()) {
            allMenus = sysMenuMapper.listAll();
        } else {
            allMenus = sysMenuMapper.selectMenuListByUserId(loginUser.getUserId());
        }

        List<SysMenuPO> noButtonMenus = allMenus.stream()
                .filter(menu -> !MenuTypeEnum.BUTTON.getValue().equals(menu.getMenuType()))
                .collect(Collectors.toList());

        TreeNodeConfig config = new TreeNodeConfig();
        //默认为id可以不设置
        config.setIdKey("menuId");

        return TreeUtil.build(noButtonMenus, 0L, config, (menu, tree) -> {
            // 也可以使用 tree.setId(dept.getId());等一些默认值
            tree.setId(menu.getId());
            tree.setParentId(menu.getParentId());
            tree.setWeight(menu.getOrderNum());
            tree.putExtra("entity", menu);
        });
    }

    public SysMenu getById(Long id){
        SysMenuPO po = sysMenuMapper.selectById(id);
        return sysMenuConvert.po2Dto(po);
    }

    public List<SysMenu> list(SysMenuListQuery listQuery){
        List<SysMenuPO> poList = sysMenuMapper.selectByList(listQuery);
        return sysMenuConvert.poList2DtoList(poList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysMenu sysMenu){
        sysMenu.setId(IdGenerator.generate());
        SysMenuPO sysMenuPo = sysMenuConvert.dto2Po(sysMenu);
        sysMenuMapper.save(sysMenuPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenu sysMenu){
        SysMenuPO sysMenuPo = sysMenuConvert.dto2Po(sysMenu);
        sysMenuMapper.update(sysMenuPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysMenuMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysMenuMapper.deleteBatchIds(ids);
    }


}
