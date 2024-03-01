<template>
  <el-form ref="loginFormRef" :model="loginForm" :rules="loginRules" size="large">
    <el-form-item prop="username">
      <el-input :prefix-icon="User" v-model="loginForm.username" placeholder="用户名"> </el-input>
    </el-form-item>
    <el-form-item prop="password">
      <el-input :prefix-icon="Lock" type="password" v-model="loginForm.password" placeholder="密码" show-password autocomplete="new-password"> </el-input>
    </el-form-item>
    <!-- <el-form-item prop="captcha">
      <el-input :prefix-icon="Postcard" maxlength="5" type="text" placeholder="验证码" v-model="loginForm.captcha" class="vertify_code" auto-complete="false">
        <template #suffix>
          <img :src="captchaUrl" @click="initCaptcha" class="vertify_img" />
        </template>
      </el-input>
    </el-form-item> -->
  </el-form>
  <div class="login-btn">
    <el-button :icon="CircleClose" round @click="resetForm(loginFormRef)" size="large">重置</el-button>
    <el-button :icon="UserFilled" round @click="login(loginFormRef)" size="large" type="primary" :loading="loading"> 登录 </el-button>
  </div>
</template>

<script setup lang="ts">
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { Login } from '@/api/interface/index'
import { ElNotification } from 'element-plus'
import { loginApi } from '@/api/modules/login'
import { useAppStoreWithOut } from '@/store/modules/app'
import { useTabsStoreWithOut } from '@/store/modules/tabs'
import { getTimeState } from '@/utils/util'
import { initDynamicRouter } from '@/router/modules/dynamicRouter'
import { CircleClose, UserFilled, Lock, User } from '@element-plus/icons-vue'
import { usePermissionStoreWithOut } from '@/store/modules/permission'
import { getManagerInfoApi } from '@/api/modules/manager'
import type { ElForm } from 'element-plus'
// import md5 from "js-md5";

const { push } = useRouter()
const tabsStore = useTabsStoreWithOut()
const appStore = useAppStoreWithOut()
const permissionStore = usePermissionStoreWithOut()
// 定义 formRef（校验规则）
type FormInstance = InstanceType<typeof ElForm>
const loginFormRef = ref<FormInstance>()
const loginRules = reactive({
  username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
  password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
  // captcha: [{ required: true, message: '验证码不能为空', trigger: 'blur' }]
})

const loading = ref(false)
// const captchaUrl = ref('')
// const loginForm = reactive<Login.ReqLoginForm>({ username: '', password: '', key: '', captcha: '' })
const loginForm = reactive<Login.ReqLoginForm>({ username: '', password: '' })
const login = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate(async (valid) => {
    if (!valid) return
    loading.value = true
    try {
      // 1.执行登录接口
      const { data } = await loginApi(loginForm)
      appStore.setToken(data.access_token)
      // 获取登录用户信息
      const { data: userinfo } = await getManagerInfoApi()
      appStore.setUserinfo(userinfo)
      // 2.添加动态路由
      await initDynamicRouter()

      // 3.清除上个账号的 tab 信息
      tabsStore.closeMultipleTab()
      // 4.跳转到首页
      push(permissionStore.homePath)
      ElNotification({
        title: getTimeState(),
        message: '欢迎登录 geeker-admin',
        type: 'success',
        duration: 3000
      })
    } finally {
      loading.value = false
      // initCaptcha()
    }
  })
}

// resetForm
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
//点击图片更换验证码
// const initCaptcha = async () => {
//   const captchaData = await captchaApi()
//   console.log('d', captchaData)
//   captchaUrl.value = captchaData.data.image
//   loginForm.key = captchaData.data.key
// }

onMounted(() => {
  // initCaptcha()
  // 监听enter事件（调用登录）
  document.onkeydown = (e: any) => {
    e = window.event || e
    if (e.code === 'Enter' || e.code === 'enter' || e.code === 'NumpadEnter') {
      if (loading.value) return
      login(loginFormRef.value)
    }
  }
})
</script>

<style scoped lang="less">
@import '../index.less';

.vertify_img {
  width: 100px;
  height: 100%;
  padding-left: 10px;
  cursor: pointer;
  border-left: 1px solid var(--el-border-color);
}
</style>
