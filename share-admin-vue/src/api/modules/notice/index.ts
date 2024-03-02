import { ResPage, SysNotice } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

export const getNoticePage = (params: SysNotice.ReqGetNoticeParams) => {
  return http.post<ResPage<SysNotice.ResNoticeList>>(_API + `/notice/page`, params)
}

export const addNotice = (params: SysNotice.ReqEditNoticeParams) => {
  return http.post(_API + `/notice/saveAndEdit`, params)
}

export const editNotice = (params: SysNotice.ReqEditNoticeParams) => {
  return http.post(_API + `/notice/saveAndEdit`, params)
}

export const deleteNotice = (params: number[]) => {
  return http.post(_API + `/notice/delete`, params)
}
