<template>
  <Dialog :model-value="dialogVisible" :title="dialogProps.title" :fullscreen="dialogProps.fullscreen" :max-height="dialogProps.maxHeight" :cancel-dialog="cancelDialog">
    <div :style="'width: calc(100% - ' + dialogProps.labelWidth! / 2 + 'px)'">
      <el-form
        ref="ruleFormRef"
        label-position="right"
        :label-width="dialogProps.labelWidth + 'px'"
        :rules="rules"
        :model="dialogProps.row"
        :disabled="dialogProps.isView"
        :hide-required-asterisk="dialogProps.isView"
      >
        <el-form-item label="角色名称" prop="name">
          <el-input v-model="dialogProps.row!.name" placeholder="请填写角色名称（10字以内）" clearable></el-input>
        </el-form-item>
        <el-form-item label="角色备注" prop="remark">
          <el-input v-model="dialogProps.row!.remark" placeholder="请填写角色备注（30字以内）" clearable></el-input>
        </el-form-item>
        <el-form-item label="菜单权限" prop="menuIds">
          <el-tree
            ref="treeRef"
            key="roleMenuKey"
            :data="dialogProps.treeMenuList"
            :default-checked-keys="dialogProps.row!.menuIds"
            :props="defaultProps"
            show-checkbox
            default-expand-all
            node-key="pkId"
            :check-strictly="treeStrictly"
          />
        </el-form-item>
      </el-form>
    </div>
    <template #footer>
      <slot name="footer">
        <el-button @click="cancelDialog">取消</el-button>
        <el-button type="primary" v-show="!dialogProps.isView" @click="handleSubmit">确定</el-button>
      </slot>
    </template>
  </Dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage, FormInstance } from 'element-plus'
import { Dialog } from '@/components/Dialog'
import { getRoleFormMenuList } from '@/api/modules/role'
import type Node from 'element-plus/es/components/tree/src/model/node'
import type { ElTree } from 'element-plus'

interface DialogProps {
  title: string
  isView: boolean
  fullscreen?: boolean
  row: any
  labelWidth?: number
  maxHeight?: number | string
  api?: (params: any) => Promise<any>
  getTableList?: () => Promise<any>
  treeMenuList?: any
}
const dialogVisible = ref(false)
const dialogProps = ref<DialogProps>({
  isView: false,
  title: '',
  row: {},
  labelWidth: 160,
  fullscreen: true,
  maxHeight: '500px'
})

// 接收父组件传过来的参数
const treeStrictly = ref(true)
const acceptParams = (params: DialogProps): void => {
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
  setTimeout(() => {
    treeStrictly.value = false
  }, 200)
}

defineExpose({
  acceptParams
})

// 获取菜单
const getTreeMenuList = async () => {
  const { data } = await getRoleFormMenuList()
  dialogProps.value.treeMenuList = data
}
await getTreeMenuList()

const rules = reactive({
  name: [
    { required: true, message: '请填写角色名称', trigger: 'blur' },
    { max: 10, message: '字数为2-10个字', trigger: 'blur' }
  ],
  remark: [
    { required: true, message: '请填写角色备注', trigger: 'blur' },
    { max: 30, message: '字数为2-10个字', trigger: 'blur' }
  ],
  menuIds: [
    {
      required: true,
      validator: (_rule: any, _val: string, callback: Function) => {
        let ids = getCheckeds()
        if (ids.length <= 0) {
          return callback(new Error('请选择权限'))
        }
        return callback()
      }
    }
  ]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      dialogProps.value.row.menuIds = getCheckeds()
      await dialogProps.value.api!(dialogProps.value.row)
      ElMessage.success({ message: `${dialogProps.value.title}角色成功！` })
      dialogProps.value.getTableList!()
      cancelDialog(true)
    } catch (error) {
      console.log(error)
    }
  })
}
const cancelDialog = (isClean?: boolean) => {
  dialogVisible.value = false
  let condition = ['查看', '编辑']
  if (condition.includes(dialogProps.value.title) || isClean) {
    dialogProps.value.row = {}
    ruleFormRef.value!.resetFields()
  }
  treeStrictly.value = true
}

const treeNodeClass = (_data: any, node: Node) => {
  if (node.isLeaf) return ''
  let addClass = true
  for (const key in node.childNodes) {
    if (!node.childNodes[key].isLeaf) {
      addClass = false
    }
  }
  return addClass ? 'penultimate-node' : ''
}
const defaultProps = { children: 'children', label: 'title', class: treeNodeClass }
const treeRef = ref<InstanceType<typeof ElTree>>()
const getCheckeds = () => {
  return treeRef.value!.getCheckedKeys().concat(treeRef.value!.getHalfCheckedKeys())
}
</script>

<style scoped lang="less">
:deep(.penultimate-node) {
  .el-tree-node__children {
    padding-left: 60px;
    line-height: 12px;
    white-space: pre-wrap;

    .el-tree-node {
      display: inline-block;
    }

    .el-tree-node__content {
      padding-right: 5px;
      padding-left: 5px !important;

      .el-tree-node__expand-icon {
        display: none;
      }
    }
  }
}
</style>
