import request from '@/utils/request';

// 查询部门列表
export function listDept(query) {
  return request({
    url: '/system/dept/page',
    method: 'post',
    data: query,
  });
}

// 查询部门详细
export function getDept(deptId) {
  return request({
    url: `/system/dept/get`,
    method: 'post',
    data: {id: deptId}
  });
}

// 查询部门下拉树结构
export function getDeptSelectTree() {
  return request({
    url: '/system/dept/dropdownList',
    method: 'get',
  });
}

// 新增部门
export function addDept(data) {
  return request({
    url: '/system/dept/add',
    method: 'post',
    data,
  });
}

// 修改部门
export function updateDept(data) {
  return request({
    url: '/system/dept/update',
    method: 'post',
    data,
  });
}

// 删除部门
export function deleteDept(deptId) {
  return request({
    url: `/system/dept/delete`,
    method: 'post',
    data:{id: deptId}
  });
}

// 清空部门数据
export function deleteAll() {
  return request({
    url: '/system/dept/clear',
    method: 'post',
  });
}
