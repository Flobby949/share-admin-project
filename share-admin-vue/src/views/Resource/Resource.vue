<template>
  <div class="table-box">
    <ProTable ref="proTable" title="资源列表" :columns="columns" :requestApi="getTableList" :initParam="initParam" :dataCallback="dataCallback">
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="EditPen" v-hasPermi="['sys:resource:audit']" @click="openDrawer('审批', scope.row)" v-if="scope.row.status == 0">审批</el-button>
      </template>
    </ProTable>
    <ResourceDialog ref="dialogRef" />
  </div>
</template>

<script setup lang="tsx" name="SysManager">
import { ref, reactive } from 'vue'
import { SysResource } from '@/api/interface'
import { ColumnProps } from '@/components/ProTable/interface'
// import { useHandleData } from '@/hooks/useHandleData'
import ProTable from '@/components/ProTable/index.vue'
import ResourceDialog from '@/views/Resource/components/ResourceDialog.vue'
import { EditPen } from '@element-plus/icons-vue'
import { getResourcePage, auditResource } from '@/api/modules/resource'
import { UserApi } from '@/api/modules/user'
import { dictConfigList } from '@/api/modules/dict/dictConfig'

// 查询所有用户
const userList = ref<any[]>([])
const getUserList = async () => {
  let { data } = (await UserApi.findAllUser()) as any
  data.forEach((item: any) => {
    const info = {
      value: item.pkId,
      label: item.nickname
    }
    userList.value.push(info)
  })
}
getUserList()

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
  return getResourcePage(newParams)
}

// 表格配置项
const columns: ColumnProps<SysResource.ResResourceList>[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'title',
    label: '资源标题',
    search: { el: 'input' }
  },
  {
    prop: 'author',
    label: '作者',
    showOverflowTooltip: true,
    search: { el: 'select' },
    enum: userList.value,
    fieldNames: { label: 'label', value: 'value' }
  },
  {
    prop: 'diskType',
    label: '网盘分类',
    width: 200
  },
  {
    prop: 'resTypeList',
    label: '资源分类',
    showOverflowTooltip: true,
    width: 300,
    render: (scope) => {
      return scope.row.resTypeList.map((item: string) => {
        return (
          <el-tag type="success" class="mr-2" effect="dark">
            {item}
          </el-tag>
        )
      })
    }
  },
  {
    prop: 'tagList',
    label: '标签',
    showOverflowTooltip: true,
    width: 300,
    render: (scope) => {
      let tagList = scope.row.tagList
      return (
        <div>
          {tagList.map((item: string) => {
            return (
              <el-tag type="info" class="mr-2" effect="dark">
                {item}
              </el-tag>
            )
          })}
        </div>
      )
    }
  },
  {
    prop: 'isTop',
    label: '是否置顶',
    width: 100,
    search: { el: 'select', props: { filterable: true } },
    // enum: [
    //   { title: '是', value: 1 },
    //   { title: '否', value: 0 }
    // ],
    enum: () => dictConfigList('isTop'),
    fieldNames: { label: 'title', value: 'value' },
    render: (scope) => {
      return (
        <el-tag type={scope.row.isTop === 0 ? 'success' : 'warning'} effect round>
          {scope.row.isTop === 1 ? '是' : '否'}
        </el-tag>
      )
    }
  },
  {
    prop: 'price',
    label: '积分'
  },
  {
    prop: 'likeNum',
    label: '点赞数'
  },
  {
    prop: 'detail',
    showOverflowTooltip: true,
    label: '详情'
  },
  {
    prop: 'downloadUrl',
    showOverflowTooltip: true,
    label: '下载链接'
  },
  {
    prop: 'status',
    label: '审核状态',
    width: 150,
    search: { el: 'select', props: { filterable: true } },
    fieldNames: { label: 'title', value: 'value' },
    // enum: [
    //   { title: '待审核', value: 0 },
    //   { title: '审核通过', value: 1 },
    //   { title: '审核不通过', value: 2 }
    // ],
    enum: () => dictConfigList('status'),
    render: (scope) => {
      let type = scope.row.status === 0 ? 'warning' : scope.row.status === 1 ? 'success' : 'danger'
      return <el-tag type={type}>{scope.row.status === 0 ? '待审核' : scope.row.status === 1 ? '审核通过' : '审核不通过'}</el-tag>
    }
  },
  {
    width: 200,
    prop: 'remark',
    showOverflowTooltip: true,
    label: '审核结果描述'
  },
  {
    prop: 'createTime',
    label: '创建时间',
    width: 200
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 100 }
]

// 打开 drawer(新增、查看、编辑)
const dialogRef = ref()
const openDrawer = (title: string, row: Partial<SysResource.ResResourceList> = {}) => {
  let params = {
    title,
    row: { ...row },
    api: auditResource,
    getTableList: proTable.value.getTableList,
    maxHeight: '150px'
  }
  dialogRef.value.acceptParams(params)
}
</script>
