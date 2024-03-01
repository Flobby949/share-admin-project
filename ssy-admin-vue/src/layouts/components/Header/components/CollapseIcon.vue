<template>
  <el-icon class="collapse-icon" @click="changeCollapse">
    <component :is="appStore.theme.isCollapse ? 'expand' : 'fold'"></component>
  </el-icon>
</template>

<script setup lang="ts">
import { useAppStoreWithOut } from '@/store/modules/app'

const appStore = useAppStoreWithOut()
const changeCollapse = () => appStore.setTheme('isCollapse', !appStore.theme.isCollapse)
</script>

<style scoped lang="less">
.collapse-icon {
  margin-right: 20px;
  font-size: 22px;
  cursor: pointer;
}
</style>
