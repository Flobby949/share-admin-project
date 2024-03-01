import { defineStore } from 'pinia'
import { pinia } from '../index'
import { PermissionState } from '../interface'
import { getAuthButtonListApi, getAuthMenuListApi } from '@/api/modules/login'
import { getFlatArr, getKeepAliveRouterName, getShowMenuList, getAllBreadcrumbList, getParseMenuArr } from '@/utils/util'

export const usePermissionStore = defineStore('permission', {
  state: (): PermissionState => ({
    routeName: '',
    homePath: '',
    // 按钮权限列表
    buttonList: [],
    // menuList 作为动态路由，不会做持久化存储
    menuList: []
  }),
  getters: {
    // 按钮权限列表
    getButtonList: (state) => state.buttonList,
    // 后端返回的菜单列表 ==> 这里没有经过任何处理
    getMenuList: (state) => state.menuList,
    // 后端返回的菜单列表 ==> 左侧菜单栏渲染，需要去除 isHide == true
    getShowMenuList: (state) => getShowMenuList(state.menuList),
    // 扁平化之后的一维数组路由，主要用来添加动态路由
    getFlatMenuList: (state) => getFlatArr(state.menuList),
    // 所有面包屑导航列表
    getBreadcrumbList: (state) => getAllBreadcrumbList(state.menuList),
    // 需要缓存的菜单 name，用作页面 keepAlive
    getKeepAliveRouter: (state) => getKeepAliveRouterName(state.menuList)
  },
  actions: {
    // getAuthButtonList
    async getAuthButtonList() {
      const { data } = await getAuthButtonListApi()
      this.buttonList = data
    },
    // getAuthMenuList
    async getAuthMenuList() {
      const { data } = await getAuthMenuListApi()
      this.menuList = getParseMenuArr(data)
      this.homePath = this.menuList[0]?.path
    },
    // setRouteName
    async setRouteName(name: string) {
      this.routeName = name
    }
  }
})

export const usePermissionStoreWithOut = () => {
  return usePermissionStore(pinia)
}
