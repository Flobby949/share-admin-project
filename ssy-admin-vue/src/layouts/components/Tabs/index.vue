<template>
  <div class="tabs-box">
    <div class="tabs-menu">
      <el-tabs v-model="tabsMenuValue" type="card" @tab-click="tabClick" @tab-remove="tabRemove">
        <el-tab-pane v-for="item in tabsMenuList" :key="item.path" :label="item.title" :name="item.path" :closable="tabsMenuList.length > 1">
          <template #label>
            <el-icon class="tabs-icon" v-show="item.icon && tabsIcon">
              <component :is="item.icon"></component>
            </el-icon>
            {{ item.title }}
          </template>
        </el-tab-pane>
      </el-tabs>
      <MoreButton />
    </div>
  </div>
</template>

<script setup lang="ts">
import Sortable from 'sortablejs'
import { ref, computed, watch, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAppStoreWithOut } from '@/store/modules/app'
import { useTabsStore } from '@/store/modules/tabs'
import { usePermissionStore } from '@/store/modules/permission'
import { useKeepAliveStore } from '@/store/modules/keepAlive'
import { TabsPaneContext, TabPaneName } from 'element-plus'
import MoreButton from './components/MoreButton.vue'

const route = useRoute()
const router = useRouter()
const tabStore = useTabsStore()
const permissionStore = usePermissionStore()
const appStore = useAppStoreWithOut()
const keepAliveStore = useKeepAliveStore()

const tabsMenuValue = ref(route.fullPath)
const tabsMenuList = computed(() => tabStore.tabsMenuList)
const tabsIcon = computed(() => appStore.theme.tabsIcon)

onMounted(() => {
  tabsDrop()
  initTabs()
})

// 监听路由的变化（防止浏览器后退/前进不变化 tabsMenuValue）
watch(
  () => route.fullPath,
  () => {
    if (route.meta.isFull) return
    tabsMenuValue.value = route.fullPath
    const tabsParams = {
      icon: route.meta.icon as string,
      title: route.meta.title as string,
      path: route.fullPath,
      name: route.name as string,
      close: !route.meta.isAffix
    }
    tabStore.addTabs(tabsParams)
    route.meta.isKeepAlive && keepAliveStore.addKeepAliveName(route.name as string)
  },
  { immediate: true }
)

// tabs 拖拽排序
const tabsDrop = () => {
  Sortable.create(document.querySelector('.el-tabs__nav') as HTMLElement, {
    draggable: '.el-tabs__item',
    animation: 300,
    onEnd({ newIndex, oldIndex }) {
      const tabsList = [...tabStore.tabsMenuList]
      const currRow = tabsList.splice(oldIndex as number, 1)[0]
      tabsList.splice(newIndex as number, 0, currRow)
      tabStore.setTabs(tabsList)
    }
  })
}

// 初始化需要固定的 tabs
const initTabs = () => {
  permissionStore.getFlatMenuList.forEach((item) => {
    if (item.meta.isAffix && !item.meta.isHide) {
      const tabsParams = {
        icon: item.meta.icon,
        title: item.meta.title,
        path: item.path,
        name: item.name,
        close: !item.meta.isAffix
      }
      tabStore.addTabs(tabsParams)
    }
  })
}

// Tab Click
const tabClick = (tabItem: TabsPaneContext) => {
  const fullPath = tabItem.props.name as string
  router.push(fullPath)
}

// Remove Tab
const tabRemove = (fullPath: TabPaneName) => {
  if (route.fullPath === fullPath) {
    // 判断当前关闭的Tab是否时当前路由
    // 若是，判断上一个路由是否还存在tabsMenuList中，若存在则返回上一路由
    let back = router.options.history.state.back
    let flag = tabsMenuList.value.some((item) => item.path == back)
    if (flag) router.back()
  }
  const name = tabStore.tabsMenuList.filter((item) => item.path == fullPath)[0].name || ''
  keepAliveStore.removeKeepAliveName(name)
  tabStore.removeTabs(fullPath as string, fullPath == route.fullPath)
}
</script>

<style scoped lang="less">
@import './index.less';
</style>
