<script setup lang="ts">
import { Error } from '@/components/ErrorMessage'
import { usePermissionStoreWithOut } from '@/store/modules/permission'
import { useRouter } from 'vue-router'

const { push } = useRouter()

const permissionStore = usePermissionStoreWithOut()

const errorClick = () => {
  push(permissionStore.homePath)
}
</script>

<template>
  <Error type="403" @error-click="errorClick" />
</template>
