<template>
  <el-dialog v-model="dialogVisible" title="修改密码" width="550px" draggable>
    <el-form ref="ruleFormRef" label-position="right" :rules="rules" :label-width="'100px'" :model="dialogProps">
      <!-- <el-form-item label="手机验证码" prop="password">
        <el-input v-model="dialogProps.password" show-password type="code" placeholder="请填写手机验证码"></el-input>
      </el-form-item> -->
      <el-form-item label="新密码" prop="password">
        <el-input v-model="dialogProps.password" show-password type="password" placeholder="请填写密码"></el-input>
      </el-form-item>
      <el-form-item label="确认密码" prop="submitPassword">
        <el-input v-model="dialogProps.submitPassword" show-password type="password" placeholder="请填写密码"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确认</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { ElMessage, FormInstance } from 'element-plus'
import { changePassword } from '@/api/modules/manager'
import { useAppStoreWithOut } from '@/store/modules/app'
import { LOGIN_URL } from '@/configs/config'
import router from '@/router'
const dialogVisible = ref(false)
const openDialog = () => {
  dialogVisible.value = true
}
interface DialogProps {
  code: string
  password: string
  submitPassword: string
}
const dialogProps = ref<DialogProps>({
  code: '',
  password: '',
  submitPassword: ''
})
const rules = reactive({
  password: [
    {
      required: true,
      message: '密码不能为空',
      trigger: 'blur'
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
        if (dialogProps.value.password && dialogProps.value.submitPassword && dialogProps.value.password !== dialogProps.value.submitPassword) {
          return callback(new Error('两次输入的密码不一致'))
        }
        return callback()
      },
      trigger: ['blur']
    }
  ]
})
const ruleFormRef = ref<FormInstance>()
const appStore = useAppStoreWithOut()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      await changePassword(dialogProps.value)
      ElMessage.success({ message: `修改密码成功,请重新登录` })
      dialogVisible.value = false
      appStore.setToken('')
      router.replace(LOGIN_URL)
    } catch (error) {
      console.log(error)
    }
  })
}
defineExpose({ openDialog })
</script>
