<template>
  <div class="tool-bar-lf">
    <CollapseIcon id="collapseIcon" />
    <Breadcrumb id="breadcrumb" v-if="appStore.theme.breadcrumb" />
  </div>
</template>

<script setup lang="ts">
import { useAppStoreWithOut } from '@/store/modules/app'
import CollapseIcon from './components/CollapseIcon.vue'
import Breadcrumb from './components/Breadcrumb.vue'
const appStore = useAppStoreWithOut()
</script>

<style scoped lang="less">
.tool-bar-lf {
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  white-space: nowrap;
}
</style>
