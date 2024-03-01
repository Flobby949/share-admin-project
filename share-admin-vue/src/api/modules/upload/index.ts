import { Upload } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

/**
 * @name 文件上传模块
 */
// * 图片上传
export const uploadImg = (params: FormData) => {
  return http.post<Upload.ResFileUrl>(_API + `/common/upload/img`, params, {
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

// * 视频上传
export const uploadVideo = (params: FormData) => {
  return http.post<Upload.ResFileUrl>(_API + `/file/upload/video`, params)
}
