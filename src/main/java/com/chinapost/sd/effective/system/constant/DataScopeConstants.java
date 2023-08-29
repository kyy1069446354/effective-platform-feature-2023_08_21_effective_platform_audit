package com.chinapost.sd.effective.system.constant;

/**
 * 角色的数据权限常量类
 *
 * @author tangyang
 * @since 2023/8/3
 */
public class DataScopeConstants {
    /**
     * 全部数据权限
     */
    public static final Integer DATA_SCOPE_ALL = 1;

    /**
     * 自定数据权限
     */
    public static final Integer DATA_SCOPE_CUSTOM = 2;

    /**
     * 部门数据权限
     */
    public static final Integer DATA_SCOPE_DEPT = 3;

    /**
     * 部门及以下数据权限
     */
    public static final Integer DATA_SCOPE_DEPT_AND_CHILD = 4;

    /**
     * 仅本人数据权限
     */
    public static final Integer DATA_SCOPE_SELF = 5;
}
