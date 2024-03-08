import { ResPage, SysNotice } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

// 通知列表
export const getNoticePage = (params: SysNotice.ReqGetNoticeParams) => {
  return http.post<ResPage<SysNotice.ResNoticeList>>(_API + `/notice/page`, params)
}

// 添加通知
export const addNotice = (params: SysNotice.ReqAddNoticeParams) => {
  return http.post(_API + `/notice/saveAndEdit`, params)
}

// 编辑通知
export const editNotice = (params: SysNotice.ReqEditNoticeParams) => {
  return http.post(_API + `/notice/saveAndEdit`, params)
}

// 删除通知
export const deleteNotice = (params: number[]) => {
  return http.post(_API + `/notice/delete`, params)
}
