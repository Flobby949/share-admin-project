<!-- 💥 这里是一次性加载 LayoutComponents -->
<template>
  <component :is="LayoutComponents[layout]" />
  <ThemeDrawer />
</template>

<script setup lang="ts" name="layout">
import { computed, type Component } from 'vue'
import { LayoutType } from '@/store/interface'
import { useAppStoreWithOut } from '@/store/modules/app'
import ThemeDrawer from './components/ThemeDrawer/index.vue'
import LayoutVertical from './LayoutVertical/index.vue'
import LayoutClassic from './LayoutClassic/index.vue'
import LayoutTransverse from './LayoutTransverse/index.vue'
import LayoutColumns from './LayoutColumns/index.vue'

const LayoutComponents: Record<LayoutType, Component> = {
  vertical: LayoutVertical,
  classic: LayoutClassic,
  transverse: LayoutTransverse,
  columns: LayoutColumns
}

const appStore = useAppStoreWithOut()
const layout = computed(() => appStore.theme.layout)
</script>

<style scoped lang="less">
.layout {
  min-width: 730px;
}
</style>
