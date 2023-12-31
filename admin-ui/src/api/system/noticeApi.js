import request from '@/utils/request';

// 查询公告列表
export function listNotice(query) {
  return request({
    url: '/system/notice/page',
    method: 'post',
    data: query,
  });
}

// 查询公告详细
export function getNotice(noticeId) {
  return request({
    url: `/system/notice/get`,
    method: 'post',
    data: {id: noticeId}
  });
}

// 新增公告
export function addNotice(data) {
  return request({
    url: '/system/notice/add',
    method: 'post',
    data,
  });
}

// 修改公告
export function updateNotice(data) {
  return request({
    url: '/system/notice/update',
    method: 'post',
    data,
  });
}

// 删除公告
export function deleteNotice(noticeId) {
  return request({
    url: `/system/notice/batchDelete`,
    method: 'post',
    data: {ids: noticeId}
  });
}
