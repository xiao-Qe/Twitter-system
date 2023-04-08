import { request } from '@/utils/request'
//获取喜欢列表
export function getLikeList(){
    return request({
        url : '/user/get_like_list',
        method : 'get'
    })
}
//获取收藏列表
export function getCollectList(){
    return request({
        url : '/user/get_collect_list',
        method : 'get'
    })
}