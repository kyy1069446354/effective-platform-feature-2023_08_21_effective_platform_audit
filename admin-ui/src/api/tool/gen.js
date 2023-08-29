import request from '@/utils/request'

// 查询生成表数据
export function listTable(query) {
    return request({
        url: '/tool/gen/page',
        method: 'post',
        data: query
    })
}
// 查询db数据库列表
export function listDbTable(query) {
    return request({
        url: '/tool/gen/pageDbTable',
        method: 'post',
        data: query
    })
}

// 查询表详细信息
export function getGenTable(tableId) {
    return request({
        url: '/tool/gen/get',
        method: 'post',
        data: {id: tableId}
    })
}

// 修改代码生成信息
export function updateGenTable(data) {
    return request({
        url: '/tool/gen/update',
        method: 'post',
        data: data
    })
}

// 导入表
export function importTable(data) {
    return request({
        url: '/tool/gen/importTable',
        method: 'post',
        data
    })
}

// 预览生成代码
export function previewTable(tableId) {
    return request({
        url: '/tool/gen/preview',
        method: 'post',
        data:{tableId}
    })
}

// 删除表数据
export function delTable(tableIds) {
    return request({
        url: '/tool/gen/batchDelete',
        method: 'post',
        data:{ids: tableIds}
    })
}

// 生成代码（自定义路径）
export function genCode(tableIds) {
    return request({
        url: '/tool/gen/batchDownLoad',
        method: 'post',
        data:{tableIds: tableIds}
    })
}

// 同步数据库
export function syncDb(tableId) {
    return request({
        url: '/tool/gen/syncDb/',
        method: 'post',
        data:{id : tableId}
    })
}
