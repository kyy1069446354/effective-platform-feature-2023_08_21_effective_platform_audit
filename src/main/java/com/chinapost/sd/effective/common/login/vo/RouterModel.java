package com.chinapost.sd.effective.common.login.vo;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.StrUtil;
import com.chinapost.sd.effective.system.constant.MenuComponentEnum;
import com.chinapost.sd.effective.system.constant.MenuTypeEnum;
import com.chinapost.sd.effective.system.domain.dto.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tangyang
 */
public class RouterModel extends SysMenu {

    public RouterVO produceMultipleLevelMenuRouterVO(List<RouterVO> children) {
        RouterVO router = produceDefaultRouterVO();

        if (CollUtil.isNotEmpty(children) && Objects.equals(MenuTypeEnum.DIRECTORY.getValue(), getMenuType())) {
            router.setAlwaysShow(true);
            router.setRedirect("noRedirect");
            router.setChildren(children);
        }

        return router;
    }


    public RouterVO produceSingleLevelMenuRouterVO() {
        RouterVO router = produceDefaultRouterVO();

        router.setMeta(null);
        List<RouterVO> childrenList = new ArrayList<>();
        RouterVO children = new RouterVO();
        children.setPath(getPath());
        children.setComponent(getComponent());
        children.setName(StrUtil.upperFirst(getPath()));
        children.setMeta(new RouterVO.MetaVO(getName(), getIcon(), !getIsCache(), getPath()));
        children.setQuery(getQuery());
        childrenList.add(children);
        router.setChildren(childrenList);

        return router;
    }


    public RouterVO produceInnerLinkRouterVO() {

        RouterVO router = produceDefaultRouterVO();

        router.setMeta(new RouterVO.MetaVO(getName(), getIcon()));
        router.setPath("/");
        List<RouterVO> childrenList = new ArrayList<>();
        RouterVO children = new RouterVO();
        String routerPath = trimHttpPrefixForPath(getPath());
        children.setPath(routerPath);
        children.setComponent(MenuComponentEnum.INNER_LINK.description());
        children.setName(StrUtil.upperFirst(routerPath));
        children.setMeta(new RouterVO.MetaVO(getName(), getIcon(), getPath()));
        childrenList.add(children);
        router.setChildren(childrenList);

        return router;
    }

    public RouterVO produceDefaultRouterVO() {
        RouterVO router = new RouterVO();
        router.setHidden(!getIsVisible());
        router.setName(calculateRouteName());
        router.setPath(calculateRouterPath());
        router.setComponent(calculateComponentType());
        router.setQuery(getQuery());
        router.setMeta(new RouterVO.MetaVO(getName(), getIcon(), !getIsCache(), getPath()));
        return router;
    }


    /**
     * 获取路由名称
     * @return 路由名称
     */
    public String calculateRouteName() {
        String routerName = StrUtil.upperFirst(getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isSingleLevelMenu()) {
            routerName = StrUtil.EMPTY;
        }
        return routerName;
    }


    /**
     * 是否为单个一级菜单
     *
     * @return 结果
     */
    public boolean isSingleLevelMenu() {
        return isTopLevel() && MenuTypeEnum.MENU.getValue().equals(getMenuType()) && !getIsExternal();
    }

    /**
     * 是否为顶级内部链接菜单
     *
     * @return 结果
     */
    public boolean isTopInnerLink() {
        return isTopLevel() && isInnerLink();
    }


    /**
     * 是否为多级菜单
     *
     * @return 结果
     */
    public boolean isMultipleLevelMenu(Tree<Long> tree) {
        return MenuTypeEnum.DIRECTORY.getValue().equals(getMenuType()) && tree.hasChild();
    }


    /**
     * 获取路由地址
     * @return 路由地址
     */
    public String calculateRouterPath() {
        String routerPath = getPath();
        // 内链打开外网方式
        if (!isTopLevel() && isInnerLink()) {
            routerPath = trimHttpPrefixForPath(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (isTopLevel() && Objects.equals(MenuTypeEnum.DIRECTORY.getValue(), getMenuType()) && !getIsExternal()) {
            routerPath = "/" + getPath();
        // 非外链并且是一级目录（类型为菜单）
        } else if (isSingleLevelMenu()) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为内链组件
     *
     * @return 结果
     */
    public boolean isInnerLink() {
        return !getIsExternal() && (isHttp(getPath()) || isHttps(getPath()));
    }

    /**
     * 是否顶层目录或者菜单
     *
     * @return 结果
     */
    public boolean isTopLevel() {
        return Objects.equals(getParentId(), 0L);
    }


    /**
     * 内链域名特殊字符替换
     */
    public String trimHttpPrefixForPath(String path) {
        if (isHttp(path)) {
            return StrUtil.stripIgnoreCase(path, "http://", "");
        }
        if (isHttps(path)) {
            return StrUtil.stripIgnoreCase(path, "https://", "");
        }
        return path;
    }

    private boolean isHttps(String url) {
        return url.toLowerCase().startsWith("https:");
    }

    private boolean isHttp(String url) {
        return url.toLowerCase().startsWith("http:");
    }

    /**
     * 获取组件信息
     *
     * @return 组件信息
     */
    public String calculateComponentType() {
        String component = MenuComponentEnum.LAYOUT.description();
        if (StrUtil.isNotEmpty(getComponent()) && !isSingleLevelMenu()) {
            component = getComponent();
        } else if (isInnerLinkView()) {
            component = MenuComponentEnum.INNER_LINK.description();
        } else if (isParentView()) {
            component = MenuComponentEnum.PARENT_VIEW.description();
        }
        return component;
    }

    /**
     * 是否为inner_link_view组件
     *
     * @return 结果
     */
    public boolean isInnerLinkView() {
        return StrUtil.isEmpty(getComponent()) && !isTopLevel() && isInnerLink();
    }


    /**
     * 是否为parent_view组件
     *
     * @return 结果
     */
    public boolean isParentView() {
        return StrUtil.isEmpty(getComponent()) && !isTopLevel() &&
            MenuTypeEnum.DIRECTORY.getValue().equals(getMenuType());
    }


}
