import type { App } from 'vue'
import { LOGIN_URL } from '@/configs/config'
import { useAppStoreWithOut } from '@/store/modules/app'
import { usePermissionStoreWithOut } from '@/store/modules/permission'
import { createRouter, createWebHashHistory } from 'vue-router'
import { initDynamicRouter } from '@/router/modules/dynamicRouter'
import { useNProgress } from '@/hooks/useNProgress'
import { staticRouter, errorRouter } from '@/router/modules/staticRouter'

const { start, done } = useNProgress()

// 路由对象
const router = createRouter({
  history: createWebHashHistory(),
  routes: [...staticRouter, ...errorRouter],
  strict: true,
  // 刷新时，滚动条位置还原
  scrollBehavior: () => ({ left: 0, top: 0 })
})

const whiteList = ['/login', '/share-note'] // 不重定向白名单
/**
 * @description 路由拦截 beforeEach
 * */
router.beforeEach(async (to, from, next) => {
  const usepermissionStore = useAppStoreWithOut()
  const permissionStore = usePermissionStoreWithOut()
  // 1.NProgress 开始
  start()

  // 2.动态设置标题
  const title = import.meta.env.VITE_APP_TITLE
  document.title = to.meta.title ? `${to.meta.title} - ${title}` : title

  // 有 Token 就在当前页面，没有 Token 重置路由到登陆页
  if (!usepermissionStore.token) {
    if (whiteList.indexOf(to.path) !== -1) {
      next()
      return
    } else {
      next(`/login?redirect=${to.path}`) // 否则全部重定向到登录页
      return
    }
  }
  // 3.判断是访问登陆页
  if (to.path === LOGIN_URL) {
    resetRouter()
    next(from.fullPath)
    return
  } else {
    permissionStore.setRouteName(to.name as string)
    if (!permissionStore.getMenuList.length) {
      await initDynamicRouter()
      return next({ ...to, replace: true })
    }
    next()
    return
  }
})

export const resetRouter = (): void => {
  const permissionStore = usePermissionStoreWithOut()
  permissionStore.getFlatMenuList.forEach((route) => {
    const { name } = route
    if (name && router.hasRoute(name)) router.removeRoute(name)
  })
}

/**
 * @description 路由跳转错误
 * */
router.onError((error) => {
  done()
  console.warn('路由错误', error.message)
})

/**
 * @description 路由跳转结束
 * */
router.afterEach(() => {
  done()
})

export const setupRouter = (app: App<Element>) => {
  app.use(router)
}

export default router
