import { ResPage, SysTag } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

// 标签列表
export const getTagPage = (params: SysTag.ReqGetTagParams) => {
  return http.post<ResPage<SysTag.ResTagList>>(_API + `/tag/page`, params)
}

// 添加标签
export const addTag = (params: SysTag.ReqAddTagParams) => {
  return http.post(_API + `/tag/saveAndEdit`, params)
}

// 编辑标签
export const editTag = (params: SysTag.ReqEditTagParams) => {
  return http.post(_API + `/tag/saveAndEdit`, params)
}

// 删除标签
export const deleteTag = (params: number[]) => {
  return http.post(_API + `/tag/delete`, params)
}
