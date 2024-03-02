<template>
  <Dialog
    :model-value="dialogVisible"
    :title="dialogProps.title"
    :fullscreen="dialogProps.fullscreen"
    :max-height="dialogProps.maxHeight"
    :cancel-dialog="cancelDialog"
    width="900px"
  >
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
        <el-form-item label="通知标题" prop="title">
          <el-input v-model="dialogProps.row!.title" placeholder="请输入通知标题" clearable></el-input>
        </el-form-item>
        <el-form-item label="是否轮播" prop="isSwiper">
          <el-radio-group v-model="dialogProps.row!.isSwiper">
            <el-radio :label="0" border>否</el-radio>
            <el-radio :label="1" border>是</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="轮播图" prop="cover" v-if="dialogProps.row!.isSwiper === 1">
          <UploadImg v-model:image-url="dialogProps.row!.cover" width="135px" height="135px" :file-size="5">
            <template #empty>
              <el-icon><Avatar /></el-icon>
              <span>请上传轮播图</span>
            </template>
            <template #tip> 轮播图大小不能超过 5M </template>
          </UploadImg>
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <WangEditor v-model:value="dialogProps.row!.content" height="200px" />
        </el-form-item>

        <el-form-item label="是否置顶" prop="isTop">
          <el-radio-group v-model="dialogProps.row!.isTop">
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
import UploadImg from '@/components/Upload/Img.vue'
import WangEditor from '@/components/WangEditor/index.vue'
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
  if (params.row.isTop == null) {
    params.row.isTop = 0
  }
  if (params.row.isSwiper == null) {
    params.row.isSwiper = 0
  }
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
}

defineExpose({
  acceptParams
})

const rules = reactive({
  title: [
    { required: true, message: '请输入通知标题', trigger: 'blur' },
    {
      min: 2,
      max: 10,
      message: '长度在 2 到 10 个字符',
      trigger: 'blur'
    }
  ],
  content: [
    { required: true, message: '请输入通知内容', trigger: 'blur' },
    {
      min: 1,
      max: 200,
      message: '长度在 1 到 200 个字符',
      trigger: 'blur'
    }
  ],
  isTop: [{ required: true, message: '请选择是否置顶', trigger: 'blur' }],
  isSwiper: [{ required: true, message: '请选择是否轮播', trigger: 'blur' }]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
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

<style scoped lang="less"></style>
