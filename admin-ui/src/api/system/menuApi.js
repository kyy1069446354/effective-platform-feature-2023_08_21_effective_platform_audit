import request from '@/utils/request';

// 查询菜单列表
export function listMenu(query) {
  return request({
    url: '/system/menu/list',
    method: 'post',
    data: query,
  });
}

// 查询菜单详细
export function getMenu(menuId) {
  return request({
    url: `/system/menu/get`,
    method: 'post',
    data: {id: menuId}
  });
}

// 查询菜单下拉树结构
export function getMenuSelectTree() {
  return request({
    url: '/system/menu/dropdownList',
    method: 'get',
  });
}

// 新增菜单
export function addMenu(data) {
  return request({
    url: '/system/menu/add',
    method: 'post',
    data,
  });
}

// 修改菜单
export function updateMenu(data) {
  return request({
    url: '/system/menu/update',
    method: 'post',
    data,
  });
}

// 删除菜单
export function deleteMenu(menuId) {
  return request({
    url: `/system/menu/delete`,
    method: 'post',
    data: {id: menuId}
  });
}
