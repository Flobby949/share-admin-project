<template>
  <div class="table-box">
    <ProTable
      rowKey="pkId"
      ref="proTable"
      title="用户列表"
      :columns="columns"
      :requestApi="getTableList"
      :initParam="initParam"
      :dataCallback="dataCallback"
      :searchCol="{ xs: 1, sm: 2, md: 3, lg: 4, xl: 4 }"
      :row-style="{ height: '0' }"
      :cell-style="{ padding: '0px' }"
    >
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="Download" plain @click="downloadFile">导出用户</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)">查看</el-button>
        <el-button type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)">编辑</el-button>
        <el-button type="primary" link :icon="List" @click="openUserScoreDialog('积分信息', scope.row)">积分信息</el-button>
        <el-button type="primary" link :icon="Key" @click="actionUser('冻结', scope.row)" v-if="scope.row.enabled === 1">冻结用户</el-button>
        <el-button type="primary" link :icon="Key" @click="actionUser('启用', scope.row)" v-else>启用用户</el-button>
      </template>
    </ProTable>
    <UserDialog ref="dialogRef" />
    <UserScoreDialog ref="userScoreDialog" />
  </div>
</template>

<script setup lang="tsx" name="UserManager">
import { ref, reactive } from 'vue'
import { ColumnProps } from '@/components/ProTable/interface'
import ProTable from '@/components/ProTable/index.vue'
import UserDialog from './components/UserDialog.vue'
import UserScoreDialog from './components/UserScoreDialog.vue'
import { Download, View, EditPen, List, Key } from '@element-plus/icons-vue'
import { UserApi } from '@/api/modules/user'
import { ElMessageBox, ElMessage } from 'element-plus'
import { useDownload } from '@/hooks/useDownload'

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

// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
  return UserApi.page(params)
}

// 表格配置项
const columns: ColumnProps<UserType>[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'avatar',
    label: '头像',
    width: 70,
    render: (scope) => {
      return (
        <div class={['flex', 'justify-center', 'p-1']}>
          <el-avatar shape={'square'} size={30} src={scope.row.avatar} />
        </div>
      )
    }
  },
  {
    prop: 'nickname',
    showOverflowTooltip: true,
    label: '用户名',
    width: 100,
    search: { el: 'input' }
  },
  { prop: 'phone', label: '手机号', search: { el: 'input' }, width: 120 },
  { prop: 'bonus', label: '积分', width: 120, sortable: true },
  {
    prop: 'gender',
    label: '性别',

    width: 100,
    enum: [
      {
        genderLabel: '男',
        genderValue: 0
      },
      {
        genderLabel: '女',
        genderValue: 1
      }
    ],
    search: { el: 'select', props: { filterable: true } },
    fieldNames: { label: 'genderLabel', value: 'genderValue' },
    render: (scope) => {
      let type = scope.row.gender === 0 ? 'success' : 'warning'
      return <el-tag type={type}>{scope.row.gender === 0 ? '男' : '女'}</el-tag>
    }
  },
  {
    prop: 'birthday',
    label: '生日'
  },
  {
    prop: 'enabled',
    label: '状态',

    width: 100,
    render: (scope) => {
      let type = scope.row.enabled === 0 ? 'warning' : 'primary'
      return <el-tag type={type}>{scope.row.enabled === 0 ? '禁用' : '启用'}</el-tag>
    }
  },
  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 340 }
]

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<UserType> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '编辑' ? UserApi.edit : '',
    getTableList: proTable.value.getTableList,
    maxHeight: '400px'
  }
  switch (title) {
    case '查看':
      dialogRef.value.acceptParams(params)
      break
    case '编辑':
      dialogRef.value.acceptParams(params)
      break
    default:
      break
  }
}

// 用户积分
const userScoreDialog = ref()
const openUserScoreDialog = (title: string, row: UserType) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '积分信息',
    getTableList: proTable.value.getTableList,
    maxHeight: '400px'
  }
  userScoreDialog.value.acceptParams(params)
}
// 导出列表
const downloadFile = async () => {
  ElMessageBox.confirm('确认导出用户数据?', '温馨提示', { type: 'warning' }).then(() => useDownload(UserApi.export, '用户列表', proTable.value?.searchParam))
}

// 冻结用户
const actionUser = async (title: string, row: UserType) => {
  ElMessageBox.confirm(`确认${title}【${row.nickname}】用户?`, '温馨提示', { type: 'warning' }).then(async () => {
    try {
      await UserApi.freezeUser(row.pkId)
      ElMessage.success(`${title}用户成功`)
      proTable.value.getTableList()
    } catch (error) {
      ElMessage.error(`${title}用户失败`)
    }
  })
}
</script>
