<template>
  <div class="message">
    <i :class="'iconfont icon-xiaoxi'" class="toolBar-icon" @click="openDialog"></i>
  </div>
</template>

<script setup lang="tsx">
import { useEmitt } from '@/hooks/useEmitt'

const { emitter } = useEmitt()
const openDialog = () => {
  emitter.emit('openNoticeDialog')
}
</script>

<style scoped lang="less"></style>
