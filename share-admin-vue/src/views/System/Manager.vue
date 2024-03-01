<template>
  <div class="table-box">
    <ProTable ref="proTable" title="管理员列表" :columns="columns" :requestApi="getTableList" :initParam="initParam" :dataCallback="dataCallback">
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')" v-hasPermi="['sys:manager:add']">新增管理员</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)" v-hasPermi="['sys:manager:view']">查看</el-button>
        <el-button type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)" v-hasPermi="['sys:manager:edit']">编辑</el-button>
        <el-button type="warning" link :icon="EditPen" @click="openDrawer('重置', scope.row)" v-hasPermi="['sys:manager:reset-psw']">重置密码</el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteAccount(scope.row)" v-hasPermi="['sys:manager:remove']">删除</el-button>
      </template>
    </ProTable>
    <ManagerDialog ref="dialogRef" />
  </div>
</template>

<script setup lang="tsx" name="SysManager">
import { ref, reactive } from 'vue'
import { SysManager } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
import { useHandleData } from '@/hooks/useHandleData'
import { useAuthButtons } from '@/hooks/useAuthButtons'
import ProTable from '@/components/ProTable/index.vue'
import ManagerDialog from '@/views/System/components/ManagerDialog.vue'
import { CirclePlus, Delete, EditPen, View } from '@element-plus/icons-vue'
import { getManagerPage, addManager, editManager, deleteManager } from '@/api/modules/manager'
import { getRoleList } from '@/api/modules/role'

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
  return getManagerPage(newParams)
}

// 页面按钮权限（按钮权限既可以使用 hooks，也可以直接使用 v-auth 指令，指令适合直接绑定在按钮上，hooks 适合根据按钮权限显示不同的内容）
const { BUTTONS } = useAuthButtons()

// 表格配置项
const columns: ColumnProps<SysManager.ResManagerList>[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'avatar',
    label: '头像',
    width: 120,
    // 使用 render 自定义表格内容
    render: (scope) => {
      return <el-avatar shape={'square'} size={27} src={scope.row.avatar} />
    }
  },
  {
    prop: 'username',
    label: '用户名',
    search: { el: 'input' }
  },
  {
    prop: 'roleId',
    tag: true,
    label: '角色',
    enum: getRoleList,
    // search: { el: 'select', props: { filterable: true } },
    fieldNames: { label: 'name', value: 'pkId' }
  },
  {
    prop: 'status',
    label: '状态',
    render: (scope) => {
      return (
        <>
          {BUTTONS.value.status ? (
            <el-switch model-value={scope.row.status} active-text={scope.row.status ? '启用' : '禁用'} active-value={1} inactive-value={0} />
          ) : (
            <el-tag type={scope.row.status ? 'success' : 'danger'}>{scope.row.status ? '启用' : '禁用'}</el-tag>
          )}
        </>
      )
    }
  },
  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 330 }
]

// 删除用户信息
const deleteAccount = async (params: SysManager.ResManagerList) => {
  await useHandleData(deleteManager, [params.pkId], `删除【${params.username}】用户`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<SysManager.ResManagerList> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? addManager : title === '编辑' || title === '重置' ? editManager : '',
    getTableList: proTable.value.getTableList,
    maxHeight: title === '重置' ? '100px' : '500px'
  }
  dialogRef.value.acceptParams(params)
}
</script>
