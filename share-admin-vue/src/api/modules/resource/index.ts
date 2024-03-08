import { ResPage, SysResource } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

// 资源列表
export const getResourcePage = (params: SysResource.ReqGetResourceParams) => {
  return http.post<ResPage<SysResource.ResResourceList>>(_API + `/resource/page`, params)
}

// 审核资源
export const auditResource = (params: { pkId: number; status: number; remark: string }) => {
  return http.post(_API + `/resource/audit`, params)
}
