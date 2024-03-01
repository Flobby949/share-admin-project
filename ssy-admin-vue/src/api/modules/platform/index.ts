import http from '@/api'
import { _API } from '@/api/axios/servicePort'

/**
 * @name 协议管理
 */
export const ProtocolApi = {
  edit: (params: { content: string; type: number }) => http.post(_API + '/privacy/edit', params),
  info: (params: { type: number }) => http.post<protocolType>(_API + '/privacy/info', params)
}

/**
 * @name app版本管理
 */
export const VersionApi = {
  page: (params: any) => http.post(_API + '/appVersion/page', params),
  add: (params: any) => http.post(_API + '/appVersion/add', params),
  remove: (params: number[]) => http.post(_API + '/appVersion/remove', params),
  edit: (params: any) => http.post(_API + '/appVersion/edit', params)
}
