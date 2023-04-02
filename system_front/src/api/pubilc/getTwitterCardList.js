import { request } from '@/utils/request'
//请求展示卡片接口
export function getTwitterCardList(){
    return request({
        url : '/twitter/get_twitter_card',
        method : 'get'
    })
}