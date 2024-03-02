<template>
  <div class="table-box">
    <ProTable ref="proTable" title="标签列表" :columns="columns" :requestApi="getTableList" :initParam="initParam" :dataCallback="dataCallback">
      <!-- 表格 header 按钮 -->
      <template #tableHeader="scope">
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')">新增标签</el-button>
        <el-button type="danger" :icon="Delete" plain :disabled="!scope.isSelected" @click="deleteBatch(scope.selectedListIds)">批量删除标签</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)">查看</el-button>
        <el-button type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)">编辑</el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteRow(scope.row)">删除</el-button>
      </template>
    </ProTable>
    <TagDialog ref="dialogRef" />
  </div>
</template>

<script setup lang="tsx" name="SysRole">
import { ref, reactive } from 'vue'
import { SysTag } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
import { useHandleData } from '@/hooks/useHandleData'
import ProTable from '@/components/ProTable/index.vue'
import TagDialog from '@/views/Resource/components/TagDialog.vue'
import { CirclePlus, Delete, EditPen, View } from '@element-plus/icons-vue'
import { getTagPage, addTag, editTag, deleteTag } from '@/api/modules/tag'
import { ElMessage, ElMessageBox } from 'element-plus'
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
  return getTagPage(newParams)
}

// 表格配置项
const columns: ColumnProps[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'title',
    label: '标签名称',
    search: { el: 'input' }
  },
  {
    prop: 'isHot',
    label: '是否热门',
    width: 100,
    enum: [
      { label: '否', value: 0 },
      { label: '是', value: 1 }
    ],
    search: {
      el: 'select'
    },
    fieldNames: { label: 'label', value: 'value' },
    render: (scope) => {
      let type = scope.row.isHot === 1 ? 'success' : 'info'
      return (
        <el-tag type={type} effect>
          {scope.row.isHot === 1 ? '是' : '否'}
        </el-tag>
      )
    }
  },
  {
    prop: 'description',
    label: '标签描述'
  },

  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 330 }
]

// 删除单个
const deleteRow = async (params: SysTag.ResTagList) => {
  await useHandleData(deleteTag, [params.pkId], `删除【${params.title}】标签`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<SysTag.ResTagList> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? addTag : title === '编辑' ? editTag : '',
    getTableList: proTable.value.getTableList,
    maxHeight: '200px'
  }
  dialogRef.value.acceptParams(params)
}

// 批量删除
const deleteBatch = async (ids: number[]) => {
  if (ids.length === 0) {
    ElMessageBox.alert('请选择需要删除的标签', '提示', { type: 'warning' })
    return
  }
  ElMessageBox.confirm('是否确认删除选中的标签？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    try {
      await deleteTag(ids)
      ElMessage.success('删除成功')
      proTable.value?.clearSelection()
      proTable.value?.getTableList()
    } catch (error) {
      console.log(error)
      ElMessage.error('删除失败')
    }
  })
}
</script>
