import { request } from '@/utils/request'
export  function registerUser(userName,password){
    return request({
        url : '/system/register',
        method : 'post',
        data:{
            userName,
            password
        }
    })
}
export function loginUser(userId,password){
    return request({
        url : '/system/login',
        method : 'post',
        data:{
            userId,
            password
        }
    })
}