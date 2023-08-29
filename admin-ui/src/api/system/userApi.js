import request from '@/utils/request';
import { parseStrEmpty } from '@/utils/common';

// 查询用户列表
export function listUser(data) {
  return request({
    url: '/system/user/page',
    method: 'post',
    data
  });
}

// 查询用户详细
export function getUser(userId) {
  return request({
    url: `/system/user/get`,
    method: 'post',
    data: {id: userId}
  });
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/user/add',
    method: 'post',
    data,
  });
}

// 修改用户
export function updateUser(data) {
  return request({
    url: '/system/user/update',
    method: 'post',
    data,
  });
}

// 删除用户
export function deleteUser(userIds) {
  return request({
    url: `/system/user/batchDelete`,
    method: 'post',
    data:{ids: userIds}
  });
}

// 用户密码重置
export function resetUserPassword(userId, password) {
  const data = {
    userId,
    password,
  };
  return request({
    url: `/system/user/resetPassword`,
    method: 'post',
    data,
  });
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status,
  };
  return request({
    url: `/system/user/changeStatus`,
    method: 'post',
    data,
  });
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get',
  });
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'post',
    data,
  });
}

// 用户密码重置
export function updateUserPassword(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword,
  };
  return request({
    url: '/system/user/profile/password',
    method: 'post',
    data,
  });
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    method: 'post',
    data,
  });
}
