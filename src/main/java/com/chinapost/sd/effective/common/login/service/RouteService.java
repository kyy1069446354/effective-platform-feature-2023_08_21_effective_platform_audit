package com.chinapost.sd.effective.common.login.service;

import cn.hutool.core.lang.tree.Tree;
import com.chinapost.sd.effective.common.login.convert.LoginConvert;
import com.chinapost.sd.effective.common.login.vo.RouterModel;
import com.chinapost.sd.effective.common.login.vo.RouterVO;
import com.chinapost.sd.effective.system.domain.po.SysMenuPO;
import com.chinapost.sd.effective.system.service.SysMenuService;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/10
 */
@Service
public class RouteService {
    @Autowired
    private SysMenuService sysMenuService;
    private final LoginConvert loginConvert = LoginConvert.INSTANCE;

    public List<RouterVO> getRouterTree(LoginUserInfo loginUser) {
        List<Tree<Long>> trees = sysMenuService.buildMenuTree(loginUser);
        return buildRouterTree(trees);
    }




    public List<RouterVO> buildRouterTree(List<Tree<Long>> menuTree) {
        List<RouterVO> routers = new LinkedList<>();
        if (CollectionUtils.isEmpty(menuTree)){
            return routers;
        }

        for (Tree<Long> tree : menuTree) {
            RouterVO routerDTO;

            Object entity = tree.get("entity");

            if (entity != null) {
                RouterModel model = loginConvert.convert((SysMenuPO) entity);

                routerDTO = model.produceDefaultRouterVO();

                if(model.isMultipleLevelMenu(tree)) {
                    routerDTO = model.produceMultipleLevelMenuRouterVO(buildRouterTree(tree.getChildren()));
                }

                if(model.isSingleLevelMenu()) {
                    routerDTO = model.produceSingleLevelMenuRouterVO();
                }

                if(model.isTopInnerLink()) {
                    routerDTO = model.produceInnerLinkRouterVO();
                }

                routers.add(routerDTO);
            }

        }


        return routers;
    }


}
