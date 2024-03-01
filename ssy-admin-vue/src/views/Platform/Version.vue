<template>
  <div class="table-box">
    <ProTable ref="proTable" title="版本管理" :columns="columns" :requestApi="VersionApi.page" :initParam="initParam" :dataCallback="dataCallback">
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')" v-hasPermi="['platform:version:add']">新增</el-button>
      </template>

      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)" v-hasPermi="['platform:version:view']">查看</el-button>
        <el-button type="warning" link :icon="EditPen" @click="openDrawer('编辑', scope.row)" v-hasPermi="['platform:version:edit']">编辑</el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteRowData(scope.row)" v-hasPermi="['platform:version:remove']">删除</el-button>
      </template>
    </ProTable>
    <VersionDialog ref="dialogRef" />
  </div>
</template>

<script setup lang="tsx" name="version">
import { ref, reactive } from 'vue'
import { ColumnProps } from '@/components/ProTable/interface'
import ProTable from '@/components/ProTable/index.vue'
import { Delete, CirclePlus, View, EditPen } from '@element-plus/icons-vue'
import { VersionApi } from '@/api/modules/platform'
import { useHandleData } from '@/hooks/useHandleData'
import VersionDialog from './components/VersionDialog.vue'

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

// 表格配置项
const columns: ColumnProps<any>[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  { prop: 'platform', label: '平台' },
  { prop: 'summary', label: '简介', width: 200 },
  { prop: 'maxVersion', label: '最大版本' },
  { prop: 'minVersion', label: '最小版本' },
  {
    prop: 'url',
    label: '更新地址',
    width: 200,
    render: (scope) => {
      return <el-link type="primary">{scope.row.url}</el-link>
    }
  },
  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 230 }
]

// 删除数据
const deleteRowData = async (params) => {
  await useHandleData(VersionApi.remove, [params.pkId], `删除【${params.name}】`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<any> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? VersionApi.add : title === '编辑' ? VersionApi.edit : '',
    getTableList: proTable.value.getTableList,
    maxHeight: title === '重置' ? '100px' : '300px'
  }
  dialogRef.value.acceptParams(params)
}
</script>

<style scoped lang="less"></style>
