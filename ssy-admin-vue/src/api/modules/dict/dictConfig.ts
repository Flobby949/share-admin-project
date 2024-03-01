import http from '@/api'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'
import { DictConfigRes } from '@/api/interface/index'

/**
 * @description 字典配置列表
 * @param number 字典编号
 */
export const dictConfigList = (number: string) => {
  return http.post<DictConfigRes[]>(COMMON_ADMIN_API + '/dictConfig/page ', { number }, { headers: { noLoading: true } })
}

export const dictConfigAdd = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dictConfig/save ', params)
}

export const dictConfigEdit = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dictConfig/edit ', params)
}

export const dictConfigDel = (params: any) => {
  return http.post(COMMON_ADMIN_API + '/dictConfig/remove ', params)
}
