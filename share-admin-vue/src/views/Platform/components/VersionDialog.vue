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
        <el-form-item label="平台" prop="platform">
          <el-radio-group v-model="dialogProps.row!.platform">
            <el-radio label="ios">ios</el-radio>
            <el-radio label="安卓">安卓</el-radio>
            <el-radio label="mp">微信小程序</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="简介" prop="summary">
          <el-input v-model="dialogProps.row!.summary" placeholder="请填写版本简介" clearable></el-input>
        </el-form-item>
        <el-form-item label="最大版本" prop="maxVersion">
          <el-input v-model="dialogProps.row!.maxVersion" placeholder="请填写最大版本" clearable></el-input>
        </el-form-item>
        <el-form-item label="最小版本" prop="minVersion">
          <el-input v-model="dialogProps.row!.minVersion" placeholder="请填写最小版本" clearable></el-input>
        </el-form-item>
        <el-form-item label="更新地址" prop="url">
          <el-input v-model="dialogProps.row!.url" placeholder="请填写更新地址" clearable></el-input>
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
const acceptParams = (params: DialogProps): void => {
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
}

defineExpose({
  acceptParams
})
const rules = reactive({
  platform: [{ required: true, message: '请选择平台', trigger: 'blur' }],
  summary: [{ required: true, message: '简介不能为空', trigger: 'blur' }],
  maxVersion: [{ required: true, message: '请输入最大版本', trigger: 'blur' }],
  minVersion: [{ required: true, message: '请输入最小版本', trigger: 'blur' }],
  url: [{ required: true, message: '更新地址不能为空', trigger: 'blur' }]
})
const ruleFormRef = ref<FormInstance>()

const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      delete dialogProps.value.row['updateTime']
      delete dialogProps.value.row['createTime']
      await dialogProps.value.api!(dialogProps.value.row)
      ElMessage.success({ message: `${dialogProps.value.title}成功！` })
      dialogProps.value.getTableList!()
      dialogVisible.value = false
      ruleFormRef.value!.resetFields()
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
}
</script>
