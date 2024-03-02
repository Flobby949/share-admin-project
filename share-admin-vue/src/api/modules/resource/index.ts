import { ResPage, SysResource } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

export const getResourcePage = (params: SysResource.ReqGetResourceParams) => {
  return http.post<ResPage<SysResource.ResResourceList>>(_API + `/resource/page`, params)
}

export const addResource = (params: SysResource.ReqEditResourceParams) => {
  return http.post(_API + `/resource/saveAndEdit`, params)
}

export const editResource = (params: SysResource.ReqEditResourceParams) => {
  return http.post(_API + `/resource/saveAndEdit`, params)
}

export const deleteResource = (params: number[]) => {
  return http.post(_API + `/resource/remove`, params)
}

export const auditResource = (params: { pkId: number; status: number; remark: string }) => {
  return http.post(_API + `/resource/audit`, params)
}
