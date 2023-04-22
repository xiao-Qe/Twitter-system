import { request } from '@/utils/request'
export function getListByClassifyId(classifyId){
    return request({
        url : '/twitter/get_list_by_classify_id',
        method : 'get',
        params : {
            classifyId : classifyId
        }
    })
}