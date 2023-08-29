import request from '@/utils/request';

export function listAllPost() {
  return request({
    url: '/system/post/list',
    method: 'post',
    data: {},
  });
}
// 查询岗位列表
export function listPost(query) {
  return request({
    url: '/system/post/page',
    method: 'post',
    data: query,
  });
}

// 查询岗位详细
export function getPost(postId) {
  return request({
    url: `/system/post/get`,
    method: 'post',
    data: {id: postId}
  });
}

// 新增岗位
export function addPost(data) {
  return request({
    url: '/system/post/add',
    method: 'post',
    data,
  });
}

// 修改岗位
export function updatePost(data) {
  return request({
    url: '/system/post/update',
    method: 'post',
    data,
  });
}

// 删除岗位
export function deletePost(postId) {
  return request({
    url: `/system/post/batchDelete`,
    method: 'post',
    data:{ids: postId}
  });
}
