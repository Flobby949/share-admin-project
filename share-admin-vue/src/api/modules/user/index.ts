import http from '@/api'
import { _API } from '@/api/axios/servicePort'

/**
 * @name 用户管理模块
 */
export const UserApi = {
  // 用户列表
  page: (params: any) => http.post(_API + '/user/page', params),
  // 编辑用户
  edit: (params: any) => http.post(_API + '/user/edit', params),
  // 导出用户
  export: (params: any) =>
    http.post(_API + '/user/export', params, {
      responseType: 'blob'
    }),
  // 查询用户
  findAllUser: () => http.get(_API + '/user/list'),
  // 用户积分日志
  getUserPriceLog: (userId: number) => http.get(_API + '/user/bonus/list?userId=' + userId),
  // 冻结用户
  freezeUser: (userId: number) => http.post(_API + '/user/enabled?userId=' + userId)
}
