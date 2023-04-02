import { request } from '@/utils/request'
//请求对应id号的文章信息
export function getTwitterDisplay(twitterId){
    return request({
        url : '/twitter/get_twitter_display',
        method : 'get',
        params : {
            twitterId : twitterId
        }
    })

}