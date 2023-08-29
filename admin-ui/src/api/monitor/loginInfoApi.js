import request from '@/utils/request';

// 查询登录日志列表
export function listLoginInfo(data) {
  console.log(data)
  return request({
    url: '/system/logininfo/page',
    method: 'post',
    data
  });
}

// 删除登录日志
export function deleteLoginInfo(infoId) {
  return request({
    url: `/system/logininfo/batchDelete`,
    method: 'post',
    data:{ids: infoId}
  });
}

// 清空登录日志
export function deleteAll() {
  return request({
    url: '/system/logininfo/clear',
    method: 'post',
  });
}
