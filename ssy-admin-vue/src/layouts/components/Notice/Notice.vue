<template>
  <Dialog
    :model-value="dialogVisible"
    :title="dialogProps.title"
    :fullscreen="dialogProps.fullscreen"
    :max-height="dialogProps.maxHeight"
    width="65%"
    :cancel-dialog="cancelDialog"
  >
    <div class="table-box !h-500px">
      <ProTable ref="proTable" title="通知列表" :columns="columns" :initParam="initParam" :dataCallback="dataCallback">
        <!-- 表格操作 -->
        <template #operation="scope">
          <el-button type="primary" link :icon="View" @click="scope.row">查看</el-button>
        </template>
      </ProTable>
    </div>
  </Dialog>
</template>

<script setup lang="tsx">
import { ref, reactive } from 'vue'
import { Dialog } from '@/components/Dialog'
import ProTable from '@/components/ProTable/index.vue'
import { View } from '@element-plus/icons-vue'
// import { Notice } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
import { useEmitt } from '@/hooks/useEmitt'
// import { getNoticePage } from '@/api/modules/notice'

const { emitter } = useEmitt()
const dialogVisible = ref(false)
emitter.on('openNoticeDialog', () => (dialogVisible.value = true))

// 打开主题设置
interface DialogProps {
  title: string
  fullscreen?: boolean
  maxHeight?: number | string
}
const dialogProps = ref<DialogProps>({
  title: '通知列表',
  fullscreen: true,
  maxHeight: '500px'
})
const initParam = reactive({})
// const getTableList = (params: any) => {
//   let newParams = { ...params }
//   return []
// }
const dataCallback = (data: any) => {
  return {
    list: data.list,
    total: data.total
  }
}
// // 表格配置项
const columns: ColumnProps<any>[] = []

const cancelDialog = () => {
  dialogVisible.value = false
}
</script>

<style scoped lang="less"></style>
