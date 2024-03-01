<!-- 💥 这里是异步加载 LayoutComponents -->
<template>
  <suspense>
    <template #default>
      <component :is="LayoutComponents[layout]" />
    </template>
    <template #fallback>
      <Loading />
    </template>
  </suspense>
  <ThemeDrawer />
  <Notice />
</template>

<script setup lang="ts" name="layoutAsync">
import { computed, defineAsyncComponent, type Component } from 'vue'
import { LayoutType } from '@/store/interface'
import { useAppStoreWithOut } from '@/store/modules/app'
import Loading from '@/components/Loading/index.vue'
import ThemeDrawer from './components/ThemeDrawer/index.vue'
import Notice from './components/Notice/Notice.vue'

const LayoutComponents: Record<LayoutType, Component> = {
  vertical: defineAsyncComponent(() => import('./LayoutVertical/index.vue')),
  classic: defineAsyncComponent(() => import('./LayoutClassic/index.vue')),
  transverse: defineAsyncComponent(() => import('./LayoutTransverse/index.vue')),
  columns: defineAsyncComponent(() => import('./LayoutColumns/index.vue'))
}

const appStore = useAppStoreWithOut()
const layout = computed(() => appStore.theme.layout)
</script>

<style scoped lang="less">
.layout {
  min-width: 730px;
}
</style>
