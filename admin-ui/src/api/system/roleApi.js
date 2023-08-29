import request from '@/utils/request';

export function listAllRoles() {
  return request({
    url: '/system/role/list',
    method: 'post',
    data: {},
  });
}

// 查询角色列表
export function listRole(data) {
  return request({
    url: '/system/role/page',
    method: 'post',
    data
  });
}

// 查询角色详细
export function getRole(roleId) {
  return request({
    url: `/system/role/get`,
    method: 'post',
    data:{id: roleId}
  });
}

// 新增角色
export function addRole(data) {
  return request({
    url: '/system/role/add',
    method: 'post',
    data,
  });
}

// 修改角色
export function updateRole(data) {
  return request({
    url: '/system/role/update',
    method: 'post',
    data,
  });
}

// 角色数据权限
export function changeDataScope(data) {
  return request({
    url: `/system/role/changeDataScope`,
    method: 'post',
    data
  });
}

// 角色状态修改
export function changeRoleStatus(roleId, status) {
  const data = {
    roleId,
    status,
  };
  return request({
    url: `/system/role/changeStatus`,
    method: 'post',
    data,
  });
}

// 删除角色
export function deleteRole(roleIds) {
  return request({
    url: `/system/role/batchDelete`,
    method: 'post',
    data:{ids: roleIds}
  });
}

// 查询角色已授权用户列表
export function getRoleAssignedUserList(query) {
  return request({
    url: `/system/role/pageGranted`,
    method: 'post',
    data: query,
  });
}

// 查询角色未授权用户列表
export function getRoleUnassignedUserList(query) {
  return request({
    url: `/system/role/pageUnGranted`,
    method: 'post',
    data: query,
  });
}

// 批量取消用户授权角色
export function deleteRoleOfSomeUser(data) {
  return request({
    url: `/system/role/cancelGrant`,
    method: 'post',
    data,
  });
}

// 授权用户选择
export function addRoleOfAllUser(data) {
  return request({
    url: `/system/role/grant`,
    method: 'post',
    data,
  });
}
