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
        <el-form-item label="分类名称" prop="title">
          <el-input v-model="dialogProps.row!.title" placeholder="请填写分类名称（10字以内）" clearable></el-input>
        </el-form-item>
        <el-form-item label="分类类别" prop="type">
          <!-- select -->
          <el-select v-model="dialogProps.row!.type" placeholder="请选择分类类别" clearable>
            <el-option label="网盘类型" :value="0"></el-option>
            <el-option label="资源类型" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="分类描述" prop="description">
          <el-input v-model="dialogProps.row!.description" placeholder="请填写分类描述（200字以内）" :rows="3" type="textarea" maxlength="200"></el-input>
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
}

defineExpose({
  acceptParams
})

const rules = reactive({
  title: [
    { required: true, message: '请输入分类标题', trigger: 'blur' },
    {
      min: 2,
      max: 10,
      message: '长度在 2 到 10 个字符',
      trigger: 'blur'
    }
  ],
  description: [
    { required: true, message: '请输入分类描述', trigger: 'blur' },
    {
      min: 1,
      max: 200,
      message: '长度在 1 到 200 个字符',
      trigger: 'blur'
    }
  ],
  type: [{ required: true, message: '请选择分类类型', trigger: 'blur' }]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      await dialogProps.value.api!(dialogProps.value.row)
      ElMessage.success({ message: `${dialogProps.value.title}分类成功！` })
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
