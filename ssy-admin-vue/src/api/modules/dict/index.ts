import http from '@/api'
import { _API } from '@/api/axios/servicePort'

/** 字典管理API */
export const DictList = (params: any) => {
  return http.post(_API + '/dict/page ', params)
}

export const DictAdd = (params: any) => {
  return http.post(_API + '/dict/save', params)
}

export const DictEdit = (params: any) => {
  return http.post(_API + '/dict/edit ', params)
}

export const DictDel = (params: any) => {
  return http.post(_API + '/dict/remove ', params)
}
