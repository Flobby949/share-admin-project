import { SysMenu } from '@/api/interface/index'
import http from '@/api'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'

/**
 * @name 菜单模块
 */
// * 菜单列表
export const getMenuList = (params: { type: String }) => {
  return http.post<SysMenu.ResMenuList>(COMMON_ADMIN_API + '/sys/menu/list', params)
}

// * 添加菜单
export const addMenu = (params: { type: String }) => {
  return http.post(COMMON_ADMIN_API + '/sys/menu/add', params)
}

// * 修改菜单
export const editMenu = (params: { type: String }) => {
  return http.post(COMMON_ADMIN_API + '/sys/menu/edit', params)
}

// * 删除菜单
export const removeMenu = (params: { id: number }) => {
  return http.post(COMMON_ADMIN_API + '/sys/menu/remove', params)
}

// * 菜单列表
export const getFormMenuList = (params?: {}) => {
  return http.post<SysMenu.ResMenuList[]>(COMMON_ADMIN_API + '/sys/menu/form', params, {
    headers: { noLoading: true }
  })
}
