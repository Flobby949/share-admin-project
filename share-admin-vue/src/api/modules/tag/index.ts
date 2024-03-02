import { ResPage, SysTag } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

export const getTagPage = (params: SysTag.ReqGetTagParams) => {
  return http.post<ResPage<SysTag.ResTagList>>(_API + `/tag/page`, params)
}

export const addTag = (params: SysTag.ReqEditTagParams) => {
  return http.post(_API + `/tag/saveAndEdit`, params)
}

export const editTag = (params: SysTag.ReqEditTagParams) => {
  return http.post(_API + `/tag/saveAndEdit`, params)
}

export const deleteTag = (params: number[]) => {
  return http.post(_API + `/tag/delete`, params)
}
