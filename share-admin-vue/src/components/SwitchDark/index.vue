<template>
  <ElSwitch v-model="theme.isDark" @change="onAddDarkChange" inline-prompt active-color="#0a0a0a" inactive-color="#dcdfe6" :active-icon="Sunny" :inactive-icon="Moon" />
</template>

<script setup lang="ts" name="SwitchDark">
import { computed } from 'vue'
import { useAppStoreWithOut } from '@/store/modules/app'
import { Sunny, Moon } from '@element-plus/icons-vue'
import { useTheme } from '@/hooks/useTheme'
const appStore = useAppStoreWithOut()

const { switchDark } = useTheme()

const theme = computed(() => appStore.theme)

const onAddDarkChange = () => {
  switchDark()
}
</script>
