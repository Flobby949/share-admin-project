<template>
  <el-drawer v-model="drawerVisible" title="布局设置" size="300px">
    <!-- 布局切换 -->
    <el-divider class="divider" content-position="center">
      <el-icon><Notification /></el-icon>
      布局切换
    </el-divider>
    <div class="layout-box mb30">
      <el-tooltip effect="dark" content="纵向" placement="top" :show-after="200">
        <div :class="['layout-item layout-vertical', { 'is-active': theme.layout == 'vertical' }]" @click="setLayout('vertical')">
          <div class="layout-dark"></div>
          <div class="layout-container">
            <div class="layout-light"></div>
            <div class="layout-content"></div>
          </div>
          <el-icon v-if="theme.layout == 'vertical'"><CircleCheckFilled /></el-icon>
        </div>
      </el-tooltip>
      <el-tooltip effect="dark" content="经典" placement="top" :show-after="200">
        <div :class="['layout-item layout-classic', { 'is-active': theme.layout == 'classic' }]" @click="setLayout('classic')">
          <div class="layout-dark"></div>
          <div class="layout-container">
            <div class="layout-light"></div>
            <div class="layout-content"></div>
          </div>
          <el-icon v-if="theme.layout == 'classic'"><CircleCheckFilled /></el-icon>
        </div>
      </el-tooltip>
      <el-tooltip effect="dark" content="横向" placement="top" :show-after="200">
        <div :class="['layout-item layout-transverse', { 'is-active': theme.layout == 'transverse' }]" @click="setLayout('transverse')">
          <div class="layout-dark"></div>
          <div class="layout-content"></div>
          <el-icon v-if="theme.layout == 'transverse'"><CircleCheckFilled /></el-icon>
        </div>
      </el-tooltip>
      <el-tooltip effect="dark" content="分栏" placement="top" :show-after="200">
        <div :class="['layout-item layout-columns', { 'is-active': theme.layout == 'columns' }]" @click="setLayout('columns')">
          <div class="layout-dark"></div>
          <div class="layout-light"></div>
          <div class="layout-content"></div>
          <el-icon v-if="theme.layout == 'columns'"><CircleCheckFilled /></el-icon>
        </div>
      </el-tooltip>
    </div>

    <!-- 全局主题 -->
    <el-divider class="divider" content-position="center">
      <el-icon><ColdDrink /></el-icon>
      全局主题
    </el-divider>
    <div class="theme-item">
      <span>主题颜色</span>
      <el-color-picker v-model="theme.primary" :predefine="colorList" @change="changePrimary" />
    </div>
    <div class="theme-item">
      <span>暗黑模式</span>
      <SwitchDark />
    </div>
    <div class="theme-item">
      <span>灰色模式</span>
      <el-switch v-model="theme.isGrey" @change="changeGreyOrWeak('grey', !!$event)" />
    </div>
    <div class="theme-item">
      <span>色弱模式</span>
      <el-switch v-model="theme.isWeak" @change="changeGreyOrWeak('weak', !!$event)" />
    </div>
    <div class="theme-item mb40">
      <span>
        侧边栏反转色
        <el-tooltip effect="dark" content="该属性目前只在纵向布局模式下生效" placement="top">
          <el-icon><QuestionFilled /></el-icon>
        </el-tooltip>
      </span>
      <el-switch v-model="theme.asideInverted" :disabled="theme.layout !== 'vertical'" @change="setAsideTheme" />
    </div>

    <!-- 界面设置 -->
    <el-divider class="divider" content-position="center">
      <el-icon><Setting /></el-icon>
      界面设置
    </el-divider>
    <div class="theme-item">
      <span>折叠菜单</span>
      <el-switch v-model="theme.isCollapse" />
    </div>
    <div class="theme-item">
      <span>面包屑</span>
      <el-switch v-model="theme.breadcrumb" />
    </div>
    <div class="theme-item">
      <span>面包屑图标</span>
      <el-switch v-model="theme.breadcrumbIcon" />
    </div>
    <div class="theme-item">
      <span>标签栏</span>
      <el-switch v-model="theme.tabs" />
    </div>
    <div class="theme-item">
      <span>标签栏图标</span>
      <el-switch v-model="theme.tabsIcon" />
    </div>
    <div class="theme-item">
      <span>页脚</span>
      <el-switch v-model="theme.footer" />
    </div>
  </el-drawer>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useTheme } from '@/hooks/useTheme'
import { useAppStoreWithOut } from '@/store/modules/app'
import { LayoutType } from '@/store/interface'
import { DEFAULT_PRIMARY } from '@/configs/config'
import { useEmitt } from '@/hooks/useEmitt'
import { storeToRefs } from 'pinia'
import SwitchDark from '@/components/SwitchDark/index.vue'

const { changePrimary, changeGreyOrWeak, setAsideTheme } = useTheme()
const { emitter } = useEmitt()
const appStore = useAppStoreWithOut()
const { theme } = storeToRefs(appStore)
// 预定义主题颜色
const colorList = [DEFAULT_PRIMARY, '#daa96e', '#0c819f', '#409eff', '#27ae60', '#ff5c93', '#e74c3c', '#fd726d', '#f39c12', '#9b59b6']

// 设置布局方式
const setLayout = (val: LayoutType) => {
  appStore.setTheme('layout', val)
  setAsideTheme()
}

// 打开主题设置
const drawerVisible = ref(false)
emitter.on('openThemeDrawer', () => (drawerVisible.value = true))
</script>

<style scoped lang="less">
@import './index.less';
</style>
