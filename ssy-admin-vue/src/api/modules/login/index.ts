import { Login, SysMenu } from '@/api/interface/index'
import http from '@/api'
import { _API } from '@/api/axios/servicePort'

/**
 * @name 登录模块
 */
// * 用户登录
export const loginApi = (params: Login.ReqLoginForm) => {
  return http.post<Login.ResLogin>(_API + '/sys/auth/login', params, {
    headers: { noLoading: true }
  }) // 正常 post json 请求  ==>  application/json
}

// export const captchaApi = () => {
//   return http.post<Login.ResLoginCaptcha>(_API + '/sys/auth/captcha', {}, { headers: { noLoading: true } }) // 正常 post json 请求  ==>  application/json
// }

// * 用户退出登录
export const logoutApi = () => {
  return http.post(_API + '/sys/auth/logout')
}

// * 获取按钮权限
export const getAuthButtonListApi = () => {
  return http.post<string[]>(_API + '/sys/menu/button', {}, { headers: { noLoading: true } })
}

// * 获取菜单列表
export const getAuthMenuListApi = () => {
  return http.post<SysMenu.ResMenuList[]>(_API + '/sys/menu/nav', {}, { headers: { noLoading: true } })
}
