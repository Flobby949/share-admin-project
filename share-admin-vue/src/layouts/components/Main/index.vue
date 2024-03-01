<template>
  <Maximize v-if="theme.maximize" />
  <Tabs v-if="theme.tabs" />
  <el-main>
    <router-view v-slot="{ Component, route }">
      <transition appear name="fade-transform" mode="out-in">
        <keep-alive :max="10" :include="keepAliveName">
          <component :is="Component" :key="route.fullPath" v-if="isRouterShow" />
        </keep-alive>
      </transition>
    </router-view>
  </el-main>
  <el-footer v-if="theme.footer">
    <Footer />
  </el-footer>
</template>

<script setup lang="ts">
import { ref, onBeforeUnmount, provide, watch } from 'vue'
import { storeToRefs } from 'pinia'
import { useDebounceFn } from '@vueuse/core'
import { useAppStoreWithOut } from '@/store/modules/app'
import { useKeepAliveStore } from '@/store/modules/keepAlive'
import Maximize from './components/Maximize.vue'
import Tabs from '@/layouts/components/Tabs/index.vue'
import Footer from '@/layouts/components/Footer/index.vue'

const appStore = useAppStoreWithOut()
const { theme } = storeToRefs(appStore)

const keepAliveStore = useKeepAliveStore()
const { keepAliveName } = storeToRefs(keepAliveStore)

// 注入刷新页面方法
const isRouterShow = ref(true)
const refreshCurrentPage = (val: boolean) => (isRouterShow.value = val)
provide('refresh', refreshCurrentPage)

// 监听当前页面是否最大化，动态添加 class
watch(
  () => theme.value.maximize,
  () => {
    const app = document.getElementById('app') as HTMLElement
    if (theme.value.maximize) app.classList.add('main-maximize')
    else app.classList.remove('main-maximize')
  },
  { immediate: true }
)

// 监听布局变化，在 body 上添加相对应的 layout class
watch(
  () => theme.value.layout,
  () => {
    const body = document.body as HTMLElement
    body.setAttribute('class', theme.value.layout)
  },
  { immediate: true }
)

// 监听窗口大小变化，折叠侧边栏
const screenWidth = ref(0)
const listeningWindow = useDebounceFn(() => {
  screenWidth.value = document.body.clientWidth
  if (!theme.value.isCollapse && screenWidth.value < 1200) appStore.setTheme('isCollapse', true)
  if (theme.value.isCollapse && screenWidth.value > 1200) appStore.setTheme('isCollapse', false)
}, 100)
window.addEventListener('resize', listeningWindow, false)
onBeforeUnmount(() => {
  window.removeEventListener('resize', listeningWindow)
})
</script>

<style scoped lang="less">
@import './index.less';
</style>
