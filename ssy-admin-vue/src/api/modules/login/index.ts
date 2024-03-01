import { Login, SysMenu } from '@/api/interface/index'
import http from '@/api'
import { COMMON_ADMIN_API } from '@/api/axios/servicePort'

/**
 * @name 登录模块
 */
// * 用户登录
export const loginApi = (params: Login.ReqLoginForm) => {
  return http.post<Login.ResLogin>(COMMON_ADMIN_API + '/sys/auth/login', params, {
    headers: { noLoading: true }
  }) // 正常 post json 请求  ==>  application/json
}

// export const captchaApi = () => {
//   return http.post<Login.ResLoginCaptcha>(COMMON_ADMIN_API + '/sys/auth/captcha', {}, { headers: { noLoading: true } }) // 正常 post json 请求  ==>  application/json
// }

// * 用户退出登录
export const logoutApi = () => {
  return http.post(COMMON_ADMIN_API + '/sys/auth/logout')
}

// * 获取按钮权限
export const getAuthButtonListApi = () => {
  return http.post<string[]>(COMMON_ADMIN_API + '/sys/menu/button', {}, { headers: { noLoading: true } })
}

// * 获取菜单列表
export const getAuthMenuListApi = () => {
  return http.post<SysMenu.ResMenuList[]>(COMMON_ADMIN_API + '/sys/menu/nav', {}, { headers: { noLoading: true } })
}
