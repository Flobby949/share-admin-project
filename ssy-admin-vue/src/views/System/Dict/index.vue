<template>
  <div class="table-box">
    <ProTable ref="proTable" :columns="columns" :request-api="getTableList" :init-param="initParam" :data-callback="dataCallback" row-key="pkId">
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="CirclePlus" @click="openDialog('新增')">新增</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="EditPen" @click="openDialog('编辑', scope.row)"> 编辑 </el-button>
        <el-button type="primary" link :icon="Setting" @click="openDialogDictConfigList('字典配置', scope.row)"> 字典配置 </el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteRow(scope.row)"> 删除 </el-button>
      </template>
    </ProTable>
    <DictDialog ref="dialogRef" />
    <DictConfigListDialog ref="dialogRefDicConfigList" />
  </div>
</template>

<script setup lang="ts" name="Dict">
import ProTable from '@/components/ProTable/index.vue'
import { ref, reactive } from 'vue'
import { ColumnProps } from '@/components/ProTable/interface'
import { useHandleData } from '@/hooks/useHandleData'
import DictDialog from './components/DictDialog.vue'
import DictConfigListDialog from './components/DictConfigListDialog.vue'
import { CirclePlus, Delete, EditPen, Setting } from '@element-plus/icons-vue'
import { DictList, DictAdd, DictEdit, DictDel } from '@/api/modules/dict'

// 获取 ProTable 元素，调用其获取刷新数据方法（还能获取到当前查询参数，方便导出携带参数）
const proTable = ref()

// 如果表格需要初始化请求参数，直接定义传给 ProTable(之后每次请求都会自动带上该参数，此参数更改之后也会一直带上，改变此参数会自动刷新表格数据)
const initParam = reactive({})

// dataCallback 是对于返回的表格数据做处理，如果你后台返回的数据不是 list && total 这些字段，那么你可以在这里进行处理成这些字段
const dataCallback = (data: any) => {
  return {
    list: data.list,
    total: data.total
  }
}

// 如果你想在请求之前对当前请求参数做一些操作，可以自定义如下函数：params 为当前所有的请求参数（包括分页），最后返回请求列表接口
// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
  let newParams = { ...params }
  return DictList(newParams)
}

// 表格配置项
const columns: ColumnProps<any>[] = [
  { type: 'index', label: '序号', fixed: 'left', width: 60 },
  {
    prop: 'title',
    label: '字典名称',
    search: { el: 'input' }
  },
  {
    prop: 'number',
    label: '字典编号'
  },
  { prop: 'description', label: '描述' },
  { prop: 'operation', label: '操作', fixed: 'right', width: 330 }
]

// 删除字典信息
const deleteRow = async (params: any) => {
  await useHandleData(DictDel, [params.pkId], `删除数据`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDialog = (title: string, row: Partial<any> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? DictAdd : title === '编辑' ? DictEdit : '',
    getTableList: proTable.value.getTableList,
    maxHeight: '200px'
  }
  dialogRef.value.acceptParams(params)
}

const dialogRefDicConfigList = ref()
const openDialogDictConfigList = (title: string, row: Partial<any> = {}) => {
  let params = {
    title,
    row: { ...row },
    getTableList: proTable.value.getTableList,
    maxHeight: 500
  }
  dialogRefDicConfigList.value.acceptParams(params)
}
</script>
