<template>
  <div class="theme-setting">
    <i :class="'iconfont icon-zhuti'" class="toolBar-icon" @click="openDrawer"></i>
  </div>
</template>

<script setup lang="ts">
import { useEmitt } from '@/hooks/useEmitt'

const { emitter } = useEmitt()
const openDrawer = () => {
  emitter.emit('openThemeDrawer')
}
</script>
