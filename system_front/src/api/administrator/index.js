import { request } from '@/utils/request'
export function getAuditList(){
    return request({
        url : '/administrator/get_audit_list',
        method : 'get'
    })
}