<template>
  <Dialog :model-value="dialogVisible" :title="dialogProps.title" :fullscreen="dialogProps.fullscreen" :max-height="dialogProps.maxHeight" :cancel-dialog="cancelDialog">
    <div class="h-400px">
      <ProTable ref="proTable" :columns="columns" :request-api="getTableList" :init-param="initParam" :pagination="false" row-key="pkId">
        <!-- 表格 header 按钮 -->
        <template #tableHeader>
          <el-button type="primary" :icon="CirclePlus" @click="openDialog('新增')"> 新增 </el-button>
        </template>
        <!-- 表格操作 -->
        <template #operation="scope">
          <el-button type="primary" link :icon="EditPen" @click="openDialog('编辑', scope.row)"> 编辑 </el-button>
          <el-button type="danger" link :icon="Delete" @click="deleteRow(scope.row)"> 删除 </el-button>
        </template>
      </ProTable>
      <DictConfigDialog ref="refDictConfigDialog" />
    </div>

    <template #footer>
      <slot name="footer">
        <el-button @click="cancelDialog">取消</el-button>
        <el-button type="primary" @click="cancelDialog">确定</el-button>
      </slot>
    </template>
  </Dialog>
</template>

<script setup lang="ts" name="DictConfigListDialog">
import ProTable from '@/components/ProTable/index.vue'
import { ref, reactive } from 'vue'
import { Dialog } from '@/components/Dialog'
import { ColumnProps } from '@/components/ProTable/interface'
import { dictConfigList, dictConfigAdd, dictConfigEdit, dictConfigDel } from '@/api/modules/dict/dictConfig'
import { CirclePlus, Delete, EditPen } from '@element-plus/icons-vue'
import DictConfigDialog from './DictConfigDialog.vue'
import { useHandleData } from '@/hooks/useHandleData'
import { DictConfigRes } from '@/api/interface'

interface DialogProps {
  title: string
  fullscreen?: boolean
  row: any
  labelWidth?: number
  maxHeight?: number | string
  getTableList?: () => Promise<any>
}
const dialogVisible = ref(false)
const dialogProps = ref<DialogProps>({
  title: '',
  row: {},
  labelWidth: 160,
  fullscreen: true,
  maxHeight: '50vh'
})

// 接收父组件传过来的参数
const acceptParams = (params: DialogProps): void => {
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
  initParam.number = params.row.number
}

defineExpose({
  acceptParams
})

const proTable = ref()
const initParam = reactive({
  number: ''
})
// 表格配置项
const columns: ColumnProps<DictConfigRes>[] = [
  {
    prop: 'title',
    label: '名称',
    search: { el: 'input' }
  },
  {
    prop: 'value',
    label: '数据值'
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 330 }
]
const getTableList = (params: any) => {
  return dictConfigList(params.number)
}

// 打开 drawer(新增、查看、编辑)
const refDictConfigDialog = ref()
const openDialog = (title: string, row: Partial<DictConfigRes> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? dictConfigAdd : title === '编辑' ? dictConfigEdit : '',
    getTableList: proTable.value.getTableList,
    maxHeight: '100px',
    dictNumber: initParam.number
  }
  refDictConfigDialog.value.acceptParams(params)
}

// 删除
const deleteRow = async (params: any) => {
  await useHandleData(dictConfigDel, [params.pkId], `删除数据`)
  proTable.value.getTableList()
}

const cancelDialog = () => {
  dialogVisible.value = false
  dialogProps.value.row = {}
}
</script>
