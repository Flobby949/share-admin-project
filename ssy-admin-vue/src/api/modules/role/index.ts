import { ResPage, SysRole, SysMenu } from '@/api/interface/index'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'
import http from '@/api'

/**
 * @name 角色管理模块
 */
// * 获取角色分页列表
export const getRolePage = (params: SysRole.ReqGetRoleParams) => {
  return http.post<ResPage<SysRole.ResRoleList>>(COMMON_ADMIN_API + `/sys/role/page`, params)
}

// * 获取角色列表
export const getRoleList = () => {
  return http.post<SysRole.ResRoleList>(COMMON_ADMIN_API + `/sys/role/list`)
}

// * 新增角色
export const addRole = (params: SysRole.ReqEditRoleParams) => {
  return http.post(COMMON_ADMIN_API + `/sys/role/add`, params)
}

// * 编辑角色
export const editRole = (params: SysRole.ReqEditRoleParams) => {
  return http.post(COMMON_ADMIN_API + `/sys/role/edit`, params)
}

// * 删除角色
export const deleteRole = (params: number[]) => {
  return http.post(COMMON_ADMIN_API + `/sys/role/remove`, params)
}

// * 角色表单菜单列表
export const getRoleFormMenuList = (params?: {}) => {
  return http.post<SysMenu.ResMenuList[]>(COMMON_ADMIN_API + '/sys/role/menu', params, {
    headers: { noLoading: true }
  })
}
