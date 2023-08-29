package com.chinapost.sd.effective.common.login.constant;

import java.util.Collections;
import java.util.Set;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/7/21
 */
public class PermissionConstants {
    public static final String ALL_PERMISSION_STRING = "*:*:*";

    public static final Set<String> ALL_PERMISSION_SET = Collections.singleton(ALL_PERMISSION_STRING);

    public static final String SUPER_ADMIN_ROLE = "admin";

    public static final String SUPER_ADMIN_USER = "admin";

    public static final Long SUPER_ADMIN_ROLE_ID = 1L;

    public static final Long SUPER_ADMIN_USER_ID = 1L;
}
