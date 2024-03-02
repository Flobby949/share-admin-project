<template>
  <div class="table-box">
    <ProTable ref="proTable" title="分类列表" :columns="columns" :requestApi="getTableList" :initParam="initParam" :dataCallback="dataCallback">
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')">新增分类</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)">查看</el-button>
        <el-button type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)">编辑</el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteRow(scope.row)">删除</el-button>
      </template>
    </ProTable>
    <CategoryDialog ref="dialogRef" />
  </div>
</template>

<script setup name="SysRole" lang="tsx">
import { ref, reactive } from 'vue'
import { SysCategory } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
import { useHandleData } from '@/hooks/useHandleData'
import ProTable from '@/components/ProTable/index.vue'
import CategoryDialog from '@/views/Resource/components/CategoryDialog.vue'
import { CirclePlus, Delete, EditPen, View } from '@element-plus/icons-vue'
import { getCategoryPage, addCategory, editCategory, deleteCategory } from '@/api/modules/category'

// 获取 ProTable 元素，调用其获取刷新数据方法（还能获取到当前查询参数，方便导出携带参数）
const proTable = ref()

// 如果表格需要初始化请求参数，直接定义传给 ProTable(之后每次请求都会自动带上该参数，此参数更改之后也会一直带上，改变此参数会自动刷新表格数据)
const initParam = reactive({})

// dataCallback 是对于返回的表格数据做处理，如果你后台返回的数据不是 datalist && total 这些字段，那么你可以在这里进行处理成这些字段
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
  return getCategoryPage(newParams)
}

// 表格配置项
const columns: ColumnProps[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'title',
    label: '分类标题',
    showOverflowTooltip: true,
    search: { el: 'input' }
  },
  {
    prop: 'type',
    label: '分类',
    width: 100,
    search: { el: 'select' },
    enum: [
      { label: '网盘类型', value: 0 },
      { label: '资源类型', value: 1 }
    ],
    fieldNames: { label: 'label', value: 'value' },
    render: (scope) => {
      let type = scope.row.type === 0 ? 'success' : 'primary'
      return <el-tag type={type}>{scope.row.type == 0 ? '网盘类型' : '资源类型'}</el-tag>
    }
  },
  {
    prop: 'description',
    label: '分类描述',
    showOverflowTooltip: true
  },
  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 330 }
]

// 删除单个
const deleteRow = async (params: SysCategory.ResCategoryList) => {
  await useHandleData(deleteCategory, [params.pkId], `删除【${params.title}】分类`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<SysCategory.ResCategoryList> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? addCategory : title === '编辑' ? editCategory : '',
    getTableList: proTable.value.getTableList,
    maxHeight: '200px'
  }
  dialogRef.value.acceptParams(params)
}
</script>
