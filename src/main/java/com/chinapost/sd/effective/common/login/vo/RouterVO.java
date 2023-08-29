package com.chinapost.sd.effective.common.login.vo;

import lombok.Data;

import java.util.List;

/**
 * 路由配置信息
 *
 * @author tangyang
 * @since 2023/7/7
 */
@Data
public class RouterVO {

    /**
     * 路由名字
     */
    private String name;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 是否隐藏路由，当设置 true 的时候该路由不会再侧边栏出现
     */
    private Boolean hidden;

    /**
     * 重定向地址，当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
     */
    private String redirect;

    /**
     * 组件地址
     */
    private String component;

    /**
     * 路由参数：如 {"id": 1, "name": "abc"}
     */
    private String query;

    /**
     * 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
     */
    private Boolean alwaysShow;

    /**
     * 其他元素
     */
    private MetaVO meta;

    /**
     * 子路由
     */
    private List<RouterVO> children;

    @Data
    public static class MetaVO{
        /**
         * 设置该路由在侧边栏和面包屑中展示的名字
         */
        private String title;

        /**
         * 设置该路由的图标，对应路径src/assets/icons/svg
         */
        private String icon;

        /**
         * 设置为true，则不会被 <keep-alive>缓存
         */
        private Boolean noCache;

        /**
         * 内链地址（http(s)://开头）
         */
        private String link;

        public MetaVO(String title, String icon) {
            this.title = title;
            this.icon = icon;
        }


        public MetaVO(String title, String icon, String link) {
            this.title = title;
            this.icon = icon;
            this.link = link;

        }

        public MetaVO(String title, String icon, boolean noCache, String link) {
            this.title = title;
            this.icon = icon;
            this.noCache = noCache;
            this.link = link;
        }
    }


}
