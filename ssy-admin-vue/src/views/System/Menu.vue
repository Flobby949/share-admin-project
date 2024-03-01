<template>
  <div class="table-box">
    <ProTable
      v-if="refreshTree"
      ref="proTable"
      title="菜单列表"
      row-key="pkId"
      :indent="30"
      :columns="columns"
      :request-api="getTableList"
      :init-param="initParam"
      :search-col="{ xs: 1, sm: 1, md: 2, lg: 3, xl: 3 }"
      :pagination="false"
      :expandAll="expandAll"
    >
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="primary" :icon="CirclePlus" @click="openDrawer('新增')" v-hasPermi="['sys:menu:add']">新增菜单</el-button>
        <el-button @click="changeExpand(!expandAll)" :type="expandAll ? 'danger' : 'warning'">
          <span>{{ expandAll ? '收缩全部' : '展开所有' }}</span>
        </el-button>
      </template>
      <template #icon="scope">
        <el-icon :size="18" v-if="scope.row.icon">
          <component :is="scope.row.icon"></component>
        </el-icon>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('查看', scope.row)" v-hasPermi="['sys:menu:view']">查看</el-button>
        <el-button type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)" v-hasPermi="['sys:menu:edit']">编辑</el-button>
        <el-button type="danger" link :icon="Delete" @click="deleteMenu(scope.row)" v-hasPermi="['sys:menu:remove']">删除</el-button>
      </template>
    </ProTable>
    <MenuDialog ref="dialogRef" />
  </div>
</template>

<script setup lang="tsx" name="SysMenu">
import { ref, reactive, nextTick } from 'vue'
import { SysMenu } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
import { useHandleData } from '@/hooks/useHandleData'
// import { useAuthButtons } from '@/hooks/useAuthButtons'
import ProTable from '@/components/ProTable/index.vue'
import MenuDialog from '@/views/System/components/MenuDialog.vue'
import { CirclePlus, Delete, EditPen, View } from '@element-plus/icons-vue'
import { getMenuList, addMenu, editMenu, removeMenu } from '@/api/modules/menu'

// 获取 ProTable 元素，调用其获取刷新数据方法（还能获取到当前查询参数，方便导出携带参数）
const proTable = ref()
const expandAll = ref(true)
const refreshTree = ref(true)

// 如果表格需要初始化请求参数，直接定义传给 ProTable(之后每次请求都会自动带上该参数，此参数更改之后也会一直带上，改变此参数会自动刷新表格数据)
const initParam = reactive({})

// 如果你想在请求之前对当前请求参数做一些操作，可以自定义如下函数：params 为当前所有的请求参数（包括分页），最后返回请求列表接口
// 默认不做操作就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
  let newParams = { ...params }
  return getMenuList(newParams)
}

const changeExpand = (val: boolean) => {
  refreshTree.value = false
  expandAll.value = val
  nextTick(() => {
    refreshTree.value = true
  })
}

// 页面按钮权限（按钮权限既可以使用 hooks，也可以直接使用 v-auth 指令，指令适合直接绑定在按钮上，hooks 适合根据按钮权限显示不同的内容）
// const { BUTTONS } = useAuthButtons()
// 表格配置项
const columns: ColumnProps<SysMenu.ResMenuList>[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  { prop: 'title', label: '菜单名称', align: 'left', search: { el: 'input' }, width: 150 },
  {
    prop: 'type',
    label: '菜单类型',
    width: 160,
    render: (scope) => {
      return scope.row.type == 'MENU_DIR' ? (
        <el-tag type="danger">菜单目录</el-tag>
      ) : scope.row.type == 'MENU' ? (
        <el-tag type="success">菜单项</el-tag>
      ) : (
        <el-tag type="info">按钮</el-tag>
      )
    }
  },
  { prop: 'icon', label: '菜单图标', width: 120 },
  { prop: 'name', label: '菜单 name' },
  { prop: 'path', label: '路由路径', width: 200 },
  { prop: 'component', label: '组件路径', width: 200 },
  { prop: 'auth', label: '授权标识', width: 150 },
  { prop: 'sort', label: '排序(正序)', width: 130 },
  { prop: 'operation', label: '操作', width: 250, fixed: 'right' }
]

// 删除用户信息
const deleteMenu = async (params: SysMenu.ResMenuList) => {
  await useHandleData(removeMenu, { id: params.pkId }, `删除【${params.title}】菜单`)
  proTable.value.getTableList()
}

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<SysMenu.ResMenuList> = {}) => {
  let params = {
    title,
    row: { ...row },
    isView: title === '查看',
    api: title === '新增' ? addMenu : title === '编辑' ? editMenu : '',
    getTableList: proTable.value.getTableList
  }
  dialogRef.value.acceptParams(params)
}
</script>
