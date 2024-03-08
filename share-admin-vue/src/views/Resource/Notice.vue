<template>
  <div class="table-box">
    <ProTable ref="proTable" title="通知列表" :columns="columns" :requestApi="getTableList" :initParam="initParam" :dataCallback="dataCallback">
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')" v-hasPermi="['sys:notice:add']">新增通知</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)" v-hasPermi="['sys:notice:view']">查看</el-button>
        <el-button type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)" v-hasPermi="['sys:notice:edit']">编辑</el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteRow(scope.row)" v-hasPermi="['sys:notice:remove']">删除</el-button>
      </template>
    </ProTable>
    <NoticeDialog ref="dialogRef" />
  </div>
</template>

<script setup name="SysRole" lang="tsx">
import { ref, reactive } from 'vue'
import { SysNotice } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
import { useHandleData } from '@/hooks/useHandleData'
import ProTable from '@/components/ProTable/index.vue'
import NoticeDialog from '@/views/Resource/components/NoticeDialog.vue'
import { CirclePlus, Delete, EditPen, View } from '@element-plus/icons-vue'
import { getNoticePage, addNotice, editNotice, deleteNotice } from '@/api/modules/notice'
import { dictConfigList } from '@/api/modules/dict/dictConfig'

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
  return getNoticePage(newParams)
}

// 表格配置项
const columns: ColumnProps[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'title',
    label: '通知标题',
    showOverflowTooltip: true,
    search: { el: 'input' }
  },
  {
    prop: 'isTop',
    label: '是否置顶',
    width: 100,
    // enum: [
    //   { title: '是', value: 1 },
    //   { title: '否', value: 0 }
    // ],
    enum: () => dictConfigList('isTop'),
    search: { el: 'select', props: { filterable: true } },
    fieldNames: { label: 'title', value: 'value' },
    render: (scope) => {
      return <el-tag type={scope.row.isTop === 0 ? 'success' : 'warning'}>{scope.row.isTop === 1 ? '是' : '否'}</el-tag>
    }
  },
  {
    prop: 'isSwiper',
    label: '是否轮播',
    width: 100,
    // enum: [
    //   { title: '是', value: 1 },
    //   { title: '否', value: 0 }
    // ],
    enum: () => dictConfigList('isSwiper'),
    search: { el: 'select', props: { filterable: true } },
    fieldNames: { label: 'title', value: 'value' },
    render: (scope) => {
      return (
        <el-tag type={scope.row.isSwiper === 1 ? 'primary' : 'info'} effect>
          {scope.row.isSwiper === 1 ? '是' : '否'}
        </el-tag>
      )
    }
  },
  {
    prop: 'cover',
    label: '封面',
    width: 100,
    render: (scope) => {
      return (
        <div class={['flex', 'justify-center', 'p-1']}>
          {scope.row.cover ? <el-image style="width: 50px; height 50px" src={scope.row.cover} fit="cover"></el-image> : '暂无图片'}
        </div>
      )
    }
  },
  {
    prop: 'content',
    label: '通知内容'
  },
  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 330 }
]

// 删除单个
const deleteRow = async (params: SysNotice.ResNoticeList) => {
  await useHandleData(deleteNotice, [params.pkId], `删除【${params.title}】通知`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<SysNotice.ResNoticeList> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? addNotice : title === '编辑' ? editNotice : '',
    getTableList: proTable.value.getTableList
  }
  dialogRef.value.acceptParams(params)
}
</script>
