<script setup lang="ts">
import { Error } from '@/components/ErrorMessage'
import { usePermissionStore } from '@/store/modules/permission'
import { useRouter } from 'vue-router'

const { push } = useRouter()

const permissionStore = usePermissionStore()

const errorClick = () => {
  push(permissionStore.homePath)
}
</script>

<template>
  <Error type="500" @error-click="errorClick" />
</template>
