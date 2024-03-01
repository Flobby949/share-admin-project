<template>
  <el-config-provider :size="assemblySize" :button="buttonConfig">
    <router-view />
  </el-config-provider>
</template>

<script setup lang="ts">
import { reactive, computed } from 'vue'
import { useAppStoreWithOut } from '@/store/modules/app'
import { useTheme } from '@/hooks/useTheme'

// init theme
const { initTheme } = useTheme()
initTheme()

const appStore = useAppStoreWithOut()

// element assemblySize
const assemblySize = computed(() => appStore.assemblySize)

// element button config
const buttonConfig = reactive({ autoInsertSpace: false })
</script>
