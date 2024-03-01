<!-- 纵向布局 -->
<template>
  <el-container class="layout">
    <el-aside>
      <div class="aside-box" :style="{ width: isCollapse ? '65px' : '210px' }">
        <div class="logo flx-center">
          <img class="logo-img" src="@/assets/images/logo.svg" alt="logo" />
          <span class="logo-text" v-show="!isCollapse">geeker-admin</span>
        </div>
        <el-scrollbar>
          <el-menu :default-active="activeMenu" :collapse="isCollapse" :router="false" :unique-opened="true" :collapse-transition="false">
            <SubMenu :menuList="menuList" />
          </el-menu>
        </el-scrollbar>
      </div>
    </el-aside>
    <el-container>
      <el-header>
        <ToolBarLeft />
        <ToolBarRight />
      </el-header>
      <Main />
    </el-container>
  </el-container>
</template>

<script setup lang="ts" name="layoutVertical">
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { usePermissionStore } from '@/store/modules/permission'
import { useAppStoreWithOut } from '@/store/modules/app'
import Main from '@/layouts/components/Main/index.vue'
import ToolBarLeft from '@/layouts/components/Header/ToolBarLeft.vue'
import ToolBarRight from '@/layouts/components/Header/ToolBarRight.vue'
import SubMenu from '@/layouts/components/Menu/SubMenu.vue'

const route = useRoute()
const permissionStore = usePermissionStore()
const appStore = useAppStoreWithOut()
const isCollapse = computed(() => appStore.theme.isCollapse)
const menuList = computed(() => permissionStore.getShowMenuList)
const activeMenu = computed(() => (route.meta.activeMenu ? route.meta.activeMenu : route.path) as string)
</script>

<style scoped lang="less">
@import './index.less';
</style>
