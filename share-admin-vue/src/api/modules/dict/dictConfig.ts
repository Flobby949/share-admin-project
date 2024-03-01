import http from '@/api'
import { _API } from '@/api/axios/servicePort'
import { DictConfigRes } from '@/api/interface/index'

/**
 * @description 字典配置列表
 * @param number 字典编号
 */
export const dictConfigList = (number: string) => {
  return http.post<DictConfigRes[]>(_API + '/dictConfig/page ', { number }, { headers: { noLoading: true } })
}

export const dictConfigAdd = (params: any) => {
  return http.post(_API + '/dictConfig/save ', params)
}

export const dictConfigEdit = (params: any) => {
  return http.post(_API + '/dictConfig/edit ', params)
}

export const dictConfigDel = (params: any) => {
  return http.post(_API + '/dictConfig/remove ', params)
}
