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
        <el-form-item label="审核状态" prop="status">
          <el-radio-group v-model="dialogProps.row!.status">
            <el-radio :label="1" border>审核通过</el-radio>
            <el-radio :label="2" border>审核不通过</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="审核描述" prop="remark">
          <el-input v-model="dialogProps.row.remark" type="textarea" :rows="3" :disabled="dialogProps.isView" placeholder="请输入审核意见长度需要小于200字" />
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
  row: { status: 0, remark: '' },
  labelWidth: 160,
  fullscreen: true,
  maxHeight: '500px'
})

// 接收父组件传过来的参数
const acceptParams = (params: DialogProps): void => {
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
}

defineExpose({
  acceptParams
})

const rules = reactive({
  status: [
    { required: true, message: '请选择是否通过审核', trigger: 'blur' },
    {
      validator: (rule: any, value: any, callback: any) => {
        if (value === 1 || value === 2) {
          callback()
        } else {
          callback(new Error('请选择是否通过审核'))
        }
      },
      trigger: 'blur'
    }
  ],
  remark: [
    { required: true, message: '请输入审核描述', trigger: 'blur' },
    {
      min: 3,
      max: 200,
      message: '长度在 3 到 200 个字符',
      trigger: 'blur'
    }
  ]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      await dialogProps.value.api!(dialogProps.value.row).then((_) => {
        ElMessage.success({ message: `${dialogProps.value.title}成功！` })
        dialogProps.value.getTableList!()
        dialogVisible.value = false
        ruleFormRef.value!.resetFields()
      })
    } catch (error) {
      console.log(error)
    }
  })
}
const cancelDialog = () => {
  dialogVisible.value = false
  ruleFormRef.value!.resetFields()
}
</script>

<style scoped lang="less"></style>
