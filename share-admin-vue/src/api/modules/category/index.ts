import { ResPage, SysCategory } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

export const getCategoryPage = (params: SysCategory.ReqGetCategoryParams) => {
  return http.post<ResPage<SysCategory.ResCategoryList>>(_API + `/category/page`, params)
}

export const addCategory = (params: SysCategory.ReqEditCategoryParams) => {
  return http.post(_API + `/category/saveAndEdit`, params)
}

export const editCategory = (params: SysCategory.ReqEditCategoryParams) => {
  return http.post(_API + `/category/saveAndEdit`, params)
}

export const deleteCategory = (params: number[]) => {
  return http.post(_API + `/category/delete`, params)
}
