package com.chinapost.sd.effective.system.service;

import com.chinapost.sd.effective.common.login.constant.PermissionConstants;
import com.chinapost.sd.effective.system.constant.DataScopeConstants;
import com.chinapost.sd.effective.system.domain.po.SysRolePO;
import com.chinapost.sd.effective.system.mapper.SysRoleMapper;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.spi.IDataScope;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import com.chinapost.sd.boot.infrastructure.utils.SpringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 类
 *
 * @author tangyang
 * @since 2023/8/3
 */
public class DataScopeHandler {

    public static class DataScope implements IDataScope{
        String dataScope;

        public DataScope(String dataScope) {
            this.dataScope = dataScope;
        }

        @Override
        public String getDatScope() {
            return dataScope;
        }
    }

    private static SysRoleMapper sysRoleMapper;

    public static IDataScope createDataScope(){
        return createDataScope("", "", "");
    }

    public static IDataScope createDataScope(String deptAlias){
        return createDataScope(deptAlias, "", "");
    }

    public static IDataScope createDataScope(String deptAlias, String userAlias){
        return createDataScope(deptAlias, userAlias, "");
    }

    /**
     *  如果dataScope是DATA_SCOPE_SELF，需要指定userAlias 只能看自己数据的表的别名
     *  和userIdColumn 只能看自己数据的表的用户id所在字段
     *
     * @param deptAlias 部门表的别名
     * @param userAlias 只能看自己数据的表的别名
     * @param userIdColumn 只能看自己数据的表的用户id所在字段
     * @return IDataScope
     */
    public static IDataScope createDataScope(String deptAlias, String userAlias, String userIdColumn){
        // 获取当前的用户
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        String deptAliasStr = deptAlias;
        if (StringUtils.isNotEmpty(deptAliasStr)){
            deptAliasStr = deptAliasStr + "." ;
        }

        String userIdColumnStr = userIdColumn;
        if (StringUtils.isEmpty(userIdColumnStr)){
            userIdColumnStr = "user_id";
        }
        if (StringUtils.isNotEmpty(userAlias)){
            userIdColumnStr = userAlias + "." + userIdColumnStr;
        }

        String dataScopeSql = buildDataScopeSql(loginUser, deptAliasStr, userIdColumnStr);
        return new DataScope(dataScopeSql);
    }


    private static String buildDataScopeSql(LoginUserInfo loginUser, String deptAlias, String userIdColumn){
        if (loginUser == null){
            // 不查询任何数据
            return String.format("%sid = 0", deptAlias);
        }
        if (loginUser.getIsAdmin() || loginUser.getRoles().contains(PermissionConstants.SUPER_ADMIN_ROLE)){
            // 如果是超级管理员，则不过滤数据
            return "";
        }
        if (sysRoleMapper == null){
            sysRoleMapper = SpringUtils.getBean(SysRoleMapper.class);
        }

        List<SysRolePO> roles = sysRoleMapper.selectByRoles(loginUser.getRoles());
        Set<Integer> dataScopeSet = roles.stream().map(SysRolePO::getDataScope).collect(Collectors.toSet());
        if (dataScopeSet.contains(DataScopeConstants.DATA_SCOPE_ALL)){
            // 如果是所有权限，则不过滤数据
            return "";
        }
        StringBuilder dataScopeSql = new StringBuilder();
        for (Integer dataScope : dataScopeSet) {
            if (DataScopeConstants.DATA_SCOPE_DEPT.equals(dataScope)){
                dataScopeSql.append(String.format("or %sid = %d ", deptAlias, loginUser.getDeptId()));
            }else if (DataScopeConstants.DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope)) {
                dataScopeSql.append(String.format(
                        "or %sid = %s or %sancestors like '%%%d%%' ",
                        deptAlias, loginUser.getDeptId(),deptAlias , loginUser.getDeptId()));
            }else if (DataScopeConstants.DATA_SCOPE_SELF.equals(dataScope)) {
                dataScopeSql.append(String.format("or %s = %d ", userIdColumn, loginUser.getUserId()));
            }
        }
        if (dataScopeSql.length() > 3) {
            dataScopeSql.delete(0,3).insert(0, "(").append(")");
            return dataScopeSql.toString();
        }
        // 不查询任何数据
        return String.format("%sid = 0", deptAlias);
    }
}
