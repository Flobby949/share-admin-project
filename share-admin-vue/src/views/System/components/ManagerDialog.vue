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
        <el-form-item v-if="dialogProps.title !== '重置'" label="用户名" prop="username">
          <el-input v-model="dialogProps.row!.username" placeholder="请填写用户名（2-20字）" clearable></el-input>
        </el-form-item>
        <el-form-item v-if="dialogProps.title !== '重置'" label="用户头像" prop="avatar">
          <UploadImg v-model:image-url="dialogProps.row!.avatar" width="135px" height="135px" :file-size="5">
            <template #empty>
              <el-icon><Avatar /></el-icon>
              <span>请上传头像</span>
            </template>
            <template #tip> 头像大小不能超过 5M </template>
          </UploadImg>
        </el-form-item>
        <el-form-item v-if="dialogProps.title === '新增' || dialogProps.title === '重置'" label="密码" prop="password">
          <el-input v-model="dialogProps.row!.password" show-password type="password" placeholder="请填写密码"></el-input>
        </el-form-item>
        <el-form-item v-if="dialogProps.title === '新增' || dialogProps.title === '重置'" label="确认密码" prop="submitPassword">
          <el-input v-model="dialogProps.row!.submitPassword" show-password type="password" placeholder="请填写密码"></el-input>
        </el-form-item>
        <el-form-item v-if="dialogProps.title !== '重置'" label="角色" prop="roleId">
          <el-select v-model="dialogProps.row!.roleId" filterable placeholder="请选择角色" class="w-full">
            <el-option v-for="item in dialogProps.roleList" :key="item.pkId" :label="item.name" :value="item.pkId" class="isabel-option" />
          </el-select>
        </el-form-item>
        <el-form-item v-if="dialogProps.title !== '重置'" label="状态" prop="status">
          <el-radio-group v-model="dialogProps.row!.status">
            <el-radio :label="1" border>正常</el-radio>
            <el-radio :label="0" border>停用</el-radio>
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
import { getRoleList } from '@/api/modules/role'
import UploadImg from '@/components/Upload/Img.vue'
import { getManagerInfoApi } from '@/api/modules/manager'
import { useAppStoreWithOut } from '@/store/modules/app'
const appStore = useAppStoreWithOut()
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
  row: { status: 1 },
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

// 获取角色列表
const getFormRoleList = async () => {
  const { data } = await getRoleList()
  dialogProps.value.roleList = data
}
await getFormRoleList()

const rules = reactive({
  username: [
    { required: true, message: '用户名不能为空', trigger: 'blur' },
    { min: 2, max: 20, message: '字数为2-20个字' }
  ],
  // avatar: [{ required: true, message: '请上传用户头像' }],
  password: [
    {
      required: true,
      trigger: 'blur',
      message: '密码不能为空'
    },
    {
      //插入正则验证：大小写、数字、至少8位、不常用字符
      pattern: /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[._~!@#$^&*])[A-Za-z0-9._~!@#$^&*]{8,16}$/,
      message: '密码应当至少8位且含有数字、大小写字母及特殊字符'
    }
  ],
  submitPassword: [
    {
      required: true,
      validator: (_rule: any, value: string, callback: Function) => {
        if (!value) {
          return callback(new Error('确认密码不能为空'))
        }
        if (dialogProps.value.row.password && dialogProps.value.row.submitPassword && dialogProps.value.row.password !== dialogProps.value.row.submitPassword) {
          return callback(new Error('两次输入的密码不一致'))
        }
        return callback()
      },
      trigger: ['blur']
    }
  ],
  roleId: [{ required: true, message: '请选择角色', trigger: 'change' }],
  status: [{ required: true, message: '请选择状态', trigger: 'change' }]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      await dialogProps.value.api!(dialogProps.value.row).then((_) => {
        // 如果被编辑的是当前用户，则刷新
        if (dialogProps.value.row.pkId === appStore.$state.userInfo.pkId)
          getManagerInfoApi().then((res) => {
            appStore.setUserinfo(res.data)
          })
      })
      ElMessage.success({ message: `${dialogProps.value.title}管理员成功！` })
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
    dialogProps.value.row = { status: 1 }
    ruleFormRef.value!.resetFields()
  }
}
</script>

<style scoped lang="less"></style>
