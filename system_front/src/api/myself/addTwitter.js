import { request } from '@/utils/request'
//请求大类分类接口
export function getSupperId(){
    return request({
        url : '/twitter/get_super_id',
        method : 'get'
    })
}
//请求细分类接口
export function getClassifyBySuperId(superId){
    return request({
        url : '/twitter/get_classify_by_super_id',
        method : 'get',
        params: {
                superId : superId
        }
    })
}
//请求添加文章接口
export function addTwitter(authorId,title,classifyId,type,blurb,content){
    return request({
        url : '/myself/add_twitter',
        method : 'post',
        data : {
            authorId : authorId,
            title : title,
            classifyId : classifyId,
            type : type,
            blurb : blurb,
            content : content
        }
    })
}
//获取修改类
export function getUpdateTwitter(twitterId){
    return request({
        url : '/user/get_update_twitter',
        method : 'get',
        params : {
            twitterId : twitterId
        }
    })
}
//修改文章方法
export function setTwitter(twitterId,authorId,type,title,blurb,content,classifyId){
    return request({
        url : '/user/set_twitter',
        method : 'post',
        data : {
            id : twitterId,
            authorId : authorId,
            type : type,
            title : title,
            blurb : blurb,
            content : content,
            classifyId : classifyId
        }
    })
}