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
//点击喜欢和收藏对应方法
export function setRecommendedByLikeAndCollect(twitterId,UserId,recommended,view,like,collect,grade){
    return request({
        url : '/twitter/set_userRecommended',
        method : 'post',
        data : {
            userId : UserId,
            twitterId : twitterId,
            recommended : recommended,
            view : view,
            like : like,
            collect : collect,
            grade : grade
        }
    })
}
