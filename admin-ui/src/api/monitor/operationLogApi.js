import request from '@/utils/request';

// 查询操作日志列表
export function listOperationInfo(data) {
  return request({
    url: '/system/operationLog/page',
    method: 'post',
    data
  });
}

// 删除操作日志
export function deleteOperationLog(logId) {
  return request({
    url: `/system/operationLog/batchDelete`,
    method: 'post',
    data:{ids:logId}
  });
}

// 清空操作日志
export function deleteAll() {
  return request({
    url: '/system/operationLog/clear',
    method: 'post',
  });
}
