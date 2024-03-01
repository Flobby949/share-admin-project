import http from '@/api'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'

/**
 * @name 用户管理模块
 */
export const UserApi = {
  page: (params: any) => http.post(COMMON_ADMIN_API + '/user/page', params),
  // add: (params: any) => http.post(COMMON_ADMIN_API + '/user/add', params),
  // remove: (params: number[]) => http.post(COMMON_ADMIN_API + '/user/remove', params),
  edit: (params: any) => http.post(COMMON_ADMIN_API + '/user/edit', params),
  export: (params: any) =>
    http.post(COMMON_ADMIN_API + '/user/export', params, {
      responseType: 'blob'
    })
}
