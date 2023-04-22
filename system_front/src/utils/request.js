import axios from 'axios'
import router from "@/router";
import {ElMessageBox} from "element-plus";
export async function request(config){
    const instance = axios.create({
        baseURL: 'http://localhost:8181',
        timeout: 5000,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    })

    //请求拦截器
    instance.interceptors.request.use(function (config){
        const token = window.localStorage.getItem('authorization');
        //判断是否有登录态token
        if(token !== undefined){
            config.headers.token = token;
        }else {
            router.push('/')
        }

        return config
    },function (error) {
        // 对请求错误做些什么
        return Promise.reject(error);
    });

    // 添加响应拦截器
    instance.interceptors.response.use(function (response) {
        // 对响应数据做点什么
        if(response.data.status === 1000){
            ElMessageBox.alert('登录失效，请重新登录', '提示', {
                confirmButtonText: 'OK',
                type: 'warning'
            }).then(() => {
                router.push('/')
            })
        }
        return response;
    }, function (error) {
        if(!error.response){
            ElMessageBox.alert('登录失效，请重新登录', '提示', {
                confirmButtonText: 'OK',
                type: 'warning'
            }).then(() => {
                router.push('/')
            })
        }
        // 对响应错误做点什么
        return Promise.reject(error);
    });

    return instance(config)
}
