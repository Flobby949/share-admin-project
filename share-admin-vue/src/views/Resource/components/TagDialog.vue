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
        <el-form-item label="标签名称" prop="title">
          <el-input v-model="dialogProps.row.title" placeholder="请输入标签名称" maxlength="200"></el-input>
        </el-form-item>
        <el-form-item label="标签描述" prop="description">
          <el-input v-model="dialogProps.row.description" :rows="3" type="textarea" placeholder="请输入标签描述" maxlength="200" />
        </el-form-item>
        <el-form-item label="是否热门" prop="isHot">
          <el-radio-group v-model="dialogProps.row.isHot">
            <el-radio :label="0" border>否</el-radio>
            <el-radio :label="1" border>是</el-radio>
          </el-radio-group>
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
  roleList?: any
}
const dialogVisible = ref(false)
const dialogProps = ref<DialogProps>({
  isView: false,
  title: '',
  row: {},
  labelWidth: 160,
  fullscreen: true,
  maxHeight: '50vh'
})

// 接收父组件传过来的参数
const acceptParams = (params: DialogProps): void => {
  if (params.row.isHot == null) {
    params.row.isHot = 0
  }
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
}

defineExpose({
  acceptParams
})

const rules = reactive({
  title: [{ required: true, message: '请输入标签名称', trigger: 'blur' }],
  isHot: [{ required: true, message: '请选择是否热门', trigger: 'blur' }],
  description: [
    { required: true, message: '请输入标签描述', trigger: 'blur' },

    { required: true, min: 1, max: 200, message: '请输入标签描述1-200字以内', trigger: 'blur' }
  ]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value?.validate(async (valid) => {
    if (!valid) return
    try {
      await dialogProps.value.api!(dialogProps.value.row)
      ElMessage.success({ message: `${dialogProps.value.title}成功！` })
      dialogProps.value.getTableList!()
      dialogVisible.value = false
      ruleFormRef.value?.resetFields()
      cancelDialog()
    } catch (error) {
      console.log(error)
    }
  })
}
const cancelDialog = () => {
  dialogVisible.value = false

  dialogProps.value.row = {}
  ruleFormRef.value?.resetFields()
}
</script>
