import http from '@/api'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'

/** 字典管理API */
export const DictList = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dict/page ', params)
}

export const DictAdd = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dict/save', params)
}

export const DictEdit = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dict/edit ', params)
}

export const DictDel = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dict/remove ', params)
}
