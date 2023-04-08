import { request } from '@/utils/request'
export function getAuditList(){
    return request({
        url : '/administrator/get_audit_list',
        method : 'get'
    })
}
//获得文章展示类
export function getAdministratorShowTwitter(twitterId){
    return request({
        url : '/administrator/get_administrator_show_twitter',
        method : 'get',
        params : {
            twitterId : twitterId
        }
    })
}
//文章通过方法
export function setTwitterPass(twitterId){
    return request({
        url : '/administrator/set_state_pass',
        method : 'get',
        params : {
            twitterId : twitterId
        }
    })
}
//文章下架方法
export function setTwitterUndercarriage(twitterId,cause){
    return request({
        url : '/administrator/set_state_undercarriage',
        method : 'get',
        params : {
            twitterId : twitterId,
            cause : cause
        }
    })
}
//获得用户列表方法
export function getUserList(){
    return request({
        url : '/administrator/get_user_list',
        method : 'get'
    })
}
//删除用户方法
export function deleteUser(userId){
    return request({
        url : '/administrator/delete_user',
        method : 'get',
        params : {
            userId : userId
        }
    })
}
//修改用户密码方法
export function setUser(userId,userName,password){
    return request({
        url : '/administrator/set_user',
        method : 'post',
        data : {
            userId : userId,
            userName : userName,
            password : password
        }
    })
}