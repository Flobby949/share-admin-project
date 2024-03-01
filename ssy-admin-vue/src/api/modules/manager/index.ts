import { ResPage, SysManager } from '@/api/interface/index'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'
import http from '@/api'

/**
 * @name 用户管理模块
 */
// * 获取用户列表
export const getManagerPage = (params: SysManager.ReqGetManagerParams) => {
  return http.post<ResPage<SysManager.ResManagerList>>(COMMON_ADMIN_API + `/sys/manager/page`, params)
}

// * 新增用户
export const addManager = (params: SysManager.ReqEditManagerParams) => {
  return http.post(COMMON_ADMIN_API + `/sys/manager/add`, params)
}

// * 编辑用户
export const editManager = (params: SysManager.ReqEditManagerParams) => {
  return http.post(COMMON_ADMIN_API + `/sys/manager/edit`, params)
}

// * 删除用户
export const deleteManager = (params: number[]) => {
  return http.post(COMMON_ADMIN_API + `/sys/manager/remove`, params)
}

// * 修改密码
export const changePassword = (params: SysManager.ReqEditPasswordParams) => {
  return http.post(COMMON_ADMIN_API + `/sys/manager/changePassword`, params)
}

// 获取管理员信息
export const getManagerInfoApi = () => {
  return http.post<any>(COMMON_ADMIN_API + '/sys/manager/getManagerInfo', {}, { headers: { noLoading: true } })
}
