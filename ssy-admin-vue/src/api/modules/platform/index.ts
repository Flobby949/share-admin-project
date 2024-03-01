import http from '@/api'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'

/**
 * @name 协议管理
 */
export const ProtocolApi = {
  edit: (params: { content: string; type: number }) => http.post(COMMON_ADMIN_API + '/privacy/edit', params),
  info: (params: { type: number }) => http.post<protocolType>(COMMON_ADMIN_API + '/privacy/info', params)
}

/**
 * @name app版本管理
 */
export const VersionApi = {
  page: (params: any) => http.post(COMMON_ADMIN_API + '/appVersion/page', params),
  add: (params: any) => http.post(COMMON_ADMIN_API + '/appVersion/add', params),
  remove: (params: number[]) => http.post(COMMON_ADMIN_API + '/appVersion/remove', params),
  edit: (params: any) => http.post(COMMON_ADMIN_API + '/appVersion/edit', params)
}
