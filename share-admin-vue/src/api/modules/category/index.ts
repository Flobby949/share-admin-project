import { ResPage, SysCategory } from '@/api/interface/index'
import { _API } from '@/api/axios/servicePort'
import http from '@/api'

// 分类列表
export const getCategoryPage = (params: SysCategory.ReqGetCategoryParams) => {
  return http.post<ResPage<SysCategory.ResCategoryList>>(_API + `/category/page`, params)
}

// 添加分类
export const addCategory = (params: SysCategory.ReqAddCategoryParams) => {
  return http.post(_API + `/category/saveAndEdit`, params)
}

// 编辑分类
export const editCategory = (params: SysCategory.ReqEditCategoryParams) => {
  return http.post(_API + `/category/saveAndEdit`, params)
}

// 删除分类
export const deleteCategory = (params: number[]) => {
  return http.post(_API + `/category/delete`, params)
}
