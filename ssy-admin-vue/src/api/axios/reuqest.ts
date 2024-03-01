import axios, { AxiosInstance, AxiosError, AxiosRequestConfig, AxiosResponse, AxiosRequestHeaders } from 'axios'
import { useFullScreenLoading } from '@/hooks/useFullScreenLoading'
import { ResultEnum, ContentTypeEnum } from './enum'
import { ElMessage } from 'element-plus'
import { useAppStoreWithOut } from '@/store/modules/app'
import router from '@/router'
import { LOGIN_URL } from '@/configs/config'
const { showFullScreenLoading, hideFullScreenLoading } = useFullScreenLoading()

const config = {
  // 默认地址请求地址，可在 .env 开头文件中修改
  baseURL: import.meta.env.VITE_API_URL as string,
  // 设置超时时间（10s）
  timeout: ResultEnum.TIMEOUT as number,
  // 跨域时候允许携带凭证
  withCredentials: true,
  headers: {
    'Content-Type': ContentTypeEnum.JSON
  }
}

class RequestHttp {
  service: AxiosInstance
  public constructor(config: AxiosRequestConfig) {
    // 实例化axios
    this.service = axios.create(config)

    /**
     * @description 请求拦截器
     * 客户端发送请求 -> [请求拦截器] -> 服务器
     * token校验(JWT) : 接受服务器返回的token,存储到vuex/pinia/本地储存当中
     */
    this.service.interceptors.request.use(
      (config: AxiosRequestConfig) => {
        const appStore = useAppStoreWithOut()
        // * 如果当前请求不需要显示 loading,在 api 服务中通过指定的第三个参数: { headers: { noLoading: true } }来控制不显示loading，参见loginApi
        config.headers!.noLoading || showFullScreenLoading()
        return {
          ...config,
          headers: { ...config.headers, Authorization: appStore.token } as AxiosRequestHeaders
        }
      },
      (error: AxiosError) => {
        return Promise.reject(error)
      }
    )

    /**
     * @description 响应拦截器
     *  服务器换返回信息 -> [拦截统一处理] -> 客户端JS获取到信息
     */
    this.service.interceptors.response.use(
      (response: AxiosResponse) => {
        response.data
        const { data } = response
        const appStore = useAppStoreWithOut()
        // * 在请求结束后，并关闭请求 loading
        hideFullScreenLoading()
        // * 登陆失效（code == 599）
        if (data.code == ResultEnum.OVERDUE) {
          ElMessage.error(data.msg)
          appStore.setToken('')
          router.replace(LOGIN_URL)
          return Promise.reject(data)
        }
        // * 全局错误信息拦截（防止下载文件得时候返回数据流，没有code，直接报错）
        if (data && data.code == ResultEnum.ERROR) {
          ElMessage.error(data.msg)
          return Promise.reject(data)
        }
        // * 成功请求（在页面上除非特殊情况，否则不用处理失败逻辑）
        return data
      },
      async (error: AxiosError) => {
        const { response } = error
        hideFullScreenLoading()
        // 请求超时单独判断，因为请求超时没有 response
        if (error.message.indexOf('timeout') !== -1) {
          ElMessage.error('请求超时！请您稍后重试')
        }
        // 根据响应的错误状态码，做不同的处理
        if (response) {
          ElMessage.error('请求失败！请您稍后重试')
        }
        // 服务器结果都没有返回(可能服务器错误可能客户端断网)，断网处理:可以跳转到断网页面
        if (!window.navigator.onLine) {
          router.replace('/500')
        }
        return Promise.reject(error)
      }
    )
  }
}

export default new RequestHttp(config)
