package com.chinapost.sd.effective.system.service;

import com.chinapost.sd.effective.common.login.constant.PermissionConstants;
import com.chinapost.sd.effective.system.convert.SysUserConvert;
import com.chinapost.sd.effective.system.domain.dto.SysUser;
import com.chinapost.sd.effective.system.domain.po.*;
import com.chinapost.sd.effective.system.domain.vo.UserProfileVO;
import com.chinapost.sd.effective.system.domain.vo.command.UpdateProfileCommand;
import com.chinapost.sd.effective.system.domain.vo.command.UpdateUserPasswordCommand;
import com.chinapost.sd.effective.system.mapper.*;
import com.chinapost.sd.boot.commons.id.IdGenerator;
import com.chinapost.sd.boot.infrastructure.context.LoginUserInfo;
import com.chinapost.sd.boot.infrastructure.error.BusinessErrorCode;
import com.chinapost.sd.boot.infrastructure.exception.BusinessException;
import com.chinapost.sd.boot.infrastructure.utils.AuthenticationUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * SysUserService类
 * </p>
 *
 * @author tangyang
 * @since 2023-07-05
 */
@Service
public class SysUserService {
    private final SysUserConvert sysUserConvert = SysUserConvert.INSTANCE;

    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private SysPostMapper sysPostMapper;
    public SysUser getByUserName(String username){
        SysUserPO po = sysUserMapper.getByUserName(username);
        return sysUserConvert.po2Dto(po);
    }

    /**
     * 返回权限字符串集合
     */
    public Set<String> getPermissions(Long id){
        Set<Long> roleIds = getRoleIds(id);
        if (roleIds.isEmpty()){
            return Collections.emptySet();
        }
        List<SysRolePO> rolePoList = sysRoleMapper.selectBatchIds(roleIds);

        for (SysRolePO sysRolePO : rolePoList) {
            if (sysRolePO.getCode().equals(PermissionConstants.SUPER_ADMIN_ROLE)){
                return PermissionConstants.ALL_PERMISSION_SET;
            }
        }

        List<SysMenuPO> menuList = sysMenuMapper.selectMenuListByUserId(id);

        return menuList.stream().map(SysMenuPO::getPerms).collect(Collectors.toSet());
    }

    public Set<Long> getRoleIds(Long id){
        List<SysUserRolePO> sysUserRolePoList = sysUserRoleMapper.selectByUserId(id);
        return sysUserRolePoList.stream().map(SysUserRolePO::getRoleId).collect(Collectors.toSet());
    }

    /**
     * 返回role的code列表
     */
    public Set<String> getRoles(Long id) {
        return getRolePoList(id).stream().map(SysRolePO::getCode).collect(Collectors.toSet());
    }

    public Set<String> getRoleNames(Long id){
        return getRolePoList(id).stream().map(SysRolePO::getName).collect(Collectors.toSet());
    }

    private List<SysRolePO> getRolePoList(Long userId){
        Set<Long> roleIds = getRoleIds(userId);
        if (roleIds.isEmpty()){
            return Collections.emptyList();
        }
        return sysRoleMapper.selectBatchIds(roleIds);
    }

    /**
     * 返回所有用户信息
     */
    public List<SysUser> getAllInDataScope(){
        LoginUserInfo loginUser = AuthenticationUtils.getLoginUser();
        List<SysUserPO> sysUserPOS = sysUserMapper.selectAllInDataScope(loginUser.getDeptId());
        return sysUserConvert.poList2DtoList(sysUserPOS);
    }

    public SysUser getById(Long id){
        SysUserPO po = sysUserMapper.selectById(id);
        SysUser sysUser = sysUserConvert.po2Dto(po);
        sysUser.setRoleIds(getRoleIds(id));
        return sysUser;
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(SysUser sysUser){
        sysUser.setId(IdGenerator.generate());
        SysUserPO sysUserPo = sysUserConvert.dto2Po(sysUser);
        sysUserPo.setPassword(AuthenticationUtils.encryptPassword(sysUserPo.getPassword()));
        Set<Long> roleIds = sysUser.getRoleIds();
        if (!CollectionUtils.isEmpty(roleIds)){
            for (Long roleId : roleIds) {
                SysUserRolePO userRolePo = new SysUserRolePO();
                userRolePo.setId(IdGenerator.generate());
                userRolePo.setUserId(sysUser.getId());
                userRolePo.setRoleId(roleId);
                sysUserRoleMapper.save(userRolePo);
            }
            if (roleIds.contains(PermissionConstants.SUPER_ADMIN_ROLE_ID)){
                sysUserPo.setIsAdmin(true);
            }
        }
        sysUserMapper.save(sysUserPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(SysUser sysUser){
        List<SysUserRolePO> oldUserRolePoList = sysUserRoleMapper.selectByUserId(sysUser.getId());
        Set<Long> oldRoleIds = oldUserRolePoList.stream().map(SysUserRolePO::getRoleId).collect(Collectors.toSet());
        Set<Long> newRoleIds = sysUser.getRoleIds();
        if (newRoleIds == null){
            newRoleIds = Collections.emptySet();
        }

        Collection<Long> toAdd = CollectionUtils.subtract(newRoleIds, oldRoleIds);
        Collection<Long> toDelete = CollectionUtils.subtract(oldRoleIds, newRoleIds);

        if (!toDelete.isEmpty()){
            List<SysUserRolePO> toDeletePoList = oldUserRolePoList.stream()
                    .filter(po -> toDelete.contains(po.getRoleId()))
                    .collect(Collectors.toList());
            sysUserRoleMapper.delete(toDeletePoList);
        }
        if (!toAdd.isEmpty()){
            for (Long roleId : sysUser.getRoleIds()) {
                SysUserRolePO userRolePo = new SysUserRolePO();
                userRolePo.setId(IdGenerator.generate());
                userRolePo.setUserId(sysUser.getId());
                userRolePo.setRoleId(roleId);
                sysUserRoleMapper.save(userRolePo);
            }
        }

        SysUserPO sysUserPo = sysUserConvert.dto2Po(sysUser);
        sysUserPo.setIsAdmin(newRoleIds.contains(PermissionConstants.SUPER_ADMIN_ROLE_ID));
        sysUserMapper.update(sysUserPo);

    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id){
        sysUserMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void batchDelete(List<Long> ids){
        sysUserMapper.deleteBatchIds(ids);
    }


    public void resetPassword(Long userId, String password) {
        SysUserPO sysUserPo = new SysUserPO();
        sysUserPo.setPassword(AuthenticationUtils.encryptPassword(password));
        sysUserPo.setId(userId);
        sysUserMapper.update(sysUserPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void changeUserStatus(Long userId, Integer status) {
        SysUserPO sysUserPo = new SysUserPO();
        sysUserPo.setStatus(status);
        sysUserPo.setId(userId);
        sysUserMapper.update(sysUserPo);
    }

    public UserProfileVO getUserProfile(Long userId) {
        SysUserPO userPo = sysUserMapper.selectById(userId);
        UserProfileVO userProfile = sysUserConvert.po2Vo(userPo);
        if (userPo.getDeptId() != null && userPo.getDeptId() > 0){
            SysDeptPO deptPO = sysDeptMapper.selectById(userPo.getDeptId());
            if (deptPO != null){
                userProfile.setDeptName(deptPO.getName());
            }
        }
        if (userPo.getPostId() != null && userPo.getPostId() > 0){
            SysPostPO postPo = sysPostMapper.selectById(userPo.getPostId());
            if (postPo != null){
                userProfile.setPostName(postPo.getName());
            }
        }
        Set<String> roleNames = getRoleNames(userId);
        userProfile.setRoleNames(String.join(",", roleNames));
        return userProfile;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserProfile(UpdateProfileCommand command) {
        SysUserPO userPo = new SysUserPO();
        userPo.setId(command.getUserId());
        userPo.setSex(command.getSex());
        userPo.setEmail(command.getEmail());
        userPo.setNickName(command.getNickName());
        userPo.setPhoneNumber(command.getPhoneNumber());
        sysUserMapper.update(userPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePasswordBySelf(UpdateUserPasswordCommand command) {
        SysUserPO userPo = sysUserMapper.selectById(command.getUserId());
        if (!AuthenticationUtils.matchesPassword(command.getOldPassword(), userPo.getPassword())) {
            throw new BusinessException(BusinessErrorCode.USER_PASSWORD_IS_NOT_CORRECT);
        }

        if (AuthenticationUtils.matchesPassword(command.getNewPassword(), userPo.getPassword())) {
            throw new BusinessException(BusinessErrorCode.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD);
        }
        SysUserPO newUser = new SysUserPO();
        newUser.setId(userPo.getId());
        newUser.setPassword(AuthenticationUtils.encryptPassword(command.getNewPassword()));
        sysUserMapper.update(newUser);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserAvatar(Long userId, String avatarUrl) {
        SysUserPO userPo = new SysUserPO();
        userPo.setId(userId);
        userPo.setAvatar(avatarUrl);
        sysUserMapper.update(userPo);
    }
}
