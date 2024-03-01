import router from '@/router/index'
import { isType } from '@/utils/util'
import { LOGIN_URL } from '@/configs/config'
import { ElNotification } from 'element-plus'
import { useAppStoreWithOut } from '@/store/modules/app'
import { usePermissionStore } from '@/store/modules/permission'

// 引入 views 文件夹下所有 vue 文件
const modules = import.meta.glob('@/views/**/*.vue')

/**
 * 初始化动态路由
 */
export const initDynamicRouter = async () => {
  const permissionStore = usePermissionStore()
  const appStore = useAppStoreWithOut()
  try {
    // 1.获取菜单列表 && 按钮权限（可合并到一个接口获取，根据后端不同可自行改造）
    await permissionStore.getAuthMenuList()
    await permissionStore.getAuthButtonList()

    // 2.判断当前用户有没有菜单权限
    if (!permissionStore.getMenuList.length) {
      ElNotification({
        title: '无权限访问',
        message: '当前账号无任何菜单权限，请联系系统管理员！',
        type: 'warning',
        duration: 3000
      })
      appStore.setToken('')
      router.replace(LOGIN_URL)
      return Promise.reject('No permission')
    }
    const homeItem = {
      path: '/',
      redirect: permissionStore.getFlatMenuList[0].path
    }
    router.addRoute(homeItem)
    // console.log('动态路由扁平：', permissionStore.getFlatMenuList)
    // 3.添加动态路由
    permissionStore.getFlatMenuList.forEach((item: any) => {
      item.children && delete item.children
      if (item.component && isType(item.component) == 'string') {
        item.component = modules['/src/views' + item.component + '.vue']
      }
      router.addRoute('layout', item)
    })
  } catch (error) {
    // 💢 当按钮 || 菜单请求出错时，重定向到登陆页
    appStore.setToken('')
    router.replace(LOGIN_URL)
    return Promise.reject(error)
  }
}
