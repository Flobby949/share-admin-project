import type { RouteRecordRaw } from 'vue-router'
/**
 * staticRouter(静态路由)
 */
export const staticRouter: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/Login/Login.vue'),
    meta: {
      title: '登录页'
    }
  },
  {
    path: '/layout',
    name: 'layout',
    // component: () => import('@/layouts/index.vue'),
    component: () => import('@/layouts/indexAsync.vue'),
    children: []
  }
]

/**
 * errorRouter(错误页面路由)
 */
export const errorRouter = [
  {
    path: '/403',
    name: '403',
    component: () => import('@/views/Error/403.vue'),
    meta: {
      hidden: true,
      title: '403',
      noTagsView: true
    }
  },
  {
    path: '/404',
    name: '404',
    component: () => import('@/views/Error/404.vue'),
    meta: {
      hidden: true,
      title: '404',
      noTagsView: true
    }
  },
  {
    path: '/500',
    name: '500',
    component: () => import('@/views/Error/500.vue'),
    meta: {
      hidden: true,
      title: '500',
      noTagsView: true
    }
  },
  // Resolve refresh page, route warnings
  {
    path: '/:pathMatch(.*)*',
    component: () => import('@/views/Error/404.vue')
  }
]
