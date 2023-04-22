import { request } from '@/utils/request'
export function getRecommend(){
    return request({
        url : '/twitter/recommend',
        method : 'get'
    })
}