<template>
  <div class="maximize" @click="exitMaximize">
    <el-icon><Close /></el-icon>
  </div>
</template>

<script setup lang="ts">
import { useAppStoreWithOut } from '@/store/modules/app'

const appStore = useAppStoreWithOut()
const exitMaximize = () => {
  appStore.setTheme('maximize', false)
}
</script>

<style scoped lang="less">
.maximize {
  position: fixed;
  top: -25px;
  right: -25px;
  z-index: 999;
  width: 52px;
  height: 52px;
  cursor: pointer;
  background-color: var(--el-color-info);
  border-radius: 50%;
  opacity: 0.7;

  &:hover {
    background-color: var(--el-color-info-dark-2);
  }

  .el-icon {
    position: relative;
    top: 68%;
    left: 32%;
    font-size: 16px;
    color: #ffffff;
    transform: translate(-50%, -50%);
  }
}
</style>
