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
        <el-form-item label="上级菜单" prop="parentId">
          <el-select v-model="dialogProps.row!.parentId" filterable placeholder="请选择" class="w-full">
            <el-option
              v-for="item in dialogProps.menuList"
              :key="item.pkId"
              :label="item.title"
              :value="item.pkId"
              class="isabel-option"
              :disabled="item.pkId == dialogProps.row!.pkId"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <el-radio-group v-model="dialogProps.row!.type">
            <el-radio label="MENU_DIR" border>菜单目录</el-radio>
            <el-radio label="MENU" border>菜单项</el-radio>
            <el-radio label="BUTTON" border>按钮</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单名称" prop="title">
          <el-input v-model="dialogProps.row!.title" placeholder="请填写菜单名称" clearable></el-input>
        </el-form-item>
        <el-form-item label="菜单name" prop="name">
          <el-input v-model="dialogProps.row!.name" placeholder="请填写菜单name" clearable></el-input>
        </el-form-item>

        <el-form-item v-if="dialogProps.row!.type !== 'BUTTON'" label="路由路径" prop="path">
          <el-input v-model="dialogProps.row!.path" placeholder="请填写路由路径" clearable></el-input>
        </el-form-item>
        <el-form-item v-if="dialogProps.row!.type !== 'BUTTON'" label="菜单图标" prop="icon">
          <SelectIcon v-model:icon-value="dialogProps.row!.icon" />
        </el-form-item>
        <el-form-item v-if="dialogProps.row!.type === 'MENU'" label="打开方式" prop="openType">
          <el-radio-group v-model="dialogProps.row!.openType">
            <el-radio label="TAB" border>选项卡</el-radio>
            <el-radio label="URL" border>链接</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="dialogProps.row!.type !== 'BUTTON'" label="是否显示" prop="hide">
          <el-radio-group v-model="dialogProps.row!.hide">
            <el-radio :label="false" border>显示</el-radio>
            <el-radio :label="true" border>隐藏</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item v-if="dialogProps.row!.openType === 'TAB' && dialogProps.row!.type === 'MENU'" label="组件路径" prop="component">
          <el-input v-model="dialogProps.row!.component" placeholder="请填写组件路径">
            <template #prepend>@/src/views</template>
            <template #append>.vue</template>
          </el-input>
        </el-form-item>
        <el-form-item v-if="dialogProps.row!.openType === 'URL' && dialogProps.row!.type === 'MENU'" label="链接地址" prop="url">
          <el-input v-model="dialogProps.row!.url" placeholder="请填写链接的URL地址" clearable></el-input>
        </el-form-item>
        <el-form-item v-if="dialogProps.row!.type === 'BUTTON'" label="授权标识" prop="auth">
          <el-input
            v-model="dialogProps.row!.auth"
            placeholder="授权标识（多个用逗号分隔，如：sys:menu:list,sys:menu:save）"
            clearable
            :disabled="dialogProps.title == '编辑'"
          ></el-input>
        </el-form-item>
        <el-form-item label="菜单排序" prop="sort">
          <el-input v-model="dialogProps.row!.sort" type="number" placeholder="请填写菜单排序"></el-input>
        </el-form-item>
        <el-form-item label="缓存" prop="keepalive">
          <el-radio-group v-model="dialogProps.row!.keepalive">
            <el-radio :label="true" border>开启</el-radio>
            <el-radio :label="false" border>关闭</el-radio>
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
import { Dialog } from '@/components/Dialog'
import { getFormMenuList } from '@/api/modules/menu'
import { ElMessage, FormInstance } from 'element-plus'
import SelectIcon from '@/components/SelectIcon/index.vue'
interface DialogProps {
  title: string
  isView: boolean
  fullscreen?: boolean
  row: any
  labelWidth?: number
  maxHeight?: number | string
  api?: (params: any) => Promise<any>
  getTableList?: () => Promise<any>
  menuList?: any
}
const dialogVisible = ref(false)
const dialogProps = ref<DialogProps>({
  isView: false,
  title: '',
  row: { type: 'MENU', openType: 'TAB', keepalive: false, hide: false, sort: 0 },
  labelWidth: 160,
  fullscreen: true,
  maxHeight: '500px'
})

// 接收父组件传过来的参数
const acceptParams = (params: DialogProps): void => {
  params.row = { ...dialogProps.value.row, ...params.row }
  dialogProps.value = { ...dialogProps.value, ...params }
  dialogVisible.value = true
  getSelectMenuList()
}

defineExpose({
  acceptParams
})
// 获取data
const getSelectMenuList = async () => {
  const { data } = await getFormMenuList()
  dialogProps.value.menuList = data
}

const pathValidate = function (_rule: any, value: any, callback: (arg0?: Error | undefined) => void) {
  if (!value && dialogProps.value.row.type !== 'BUTTON') {
    return callback(new Error('请填写路由路径'))
  }
  callback()
}
const iconValidate = function (_rule: any, value: any, callback: (arg0?: Error | undefined) => void) {
  if (!value && dialogProps.value.row.type !== 'BUTTON') {
    return callback(new Error('请选择菜单图标'))
  }
  callback()
}
const openTypeValidate = function (_rule: any, value: any, callback: (arg0?: Error | undefined) => void) {
  if (!value && dialogProps.value.row.type === 'MENU') {
    return callback(new Error('请选择打开方式'))
  }
  callback()
}
const componentValidate = function (_rule: any, value: any, callback: (arg0?: Error | undefined) => void) {
  if (!value && dialogProps.value.row.type === 'MENU' && dialogProps.value.row.openType === 'TAB') {
    return callback(new Error('请填写组件路径'))
  }
  callback()
}
const urlValidate = function (_rule: any, value: any, callback: (arg0?: Error | undefined) => void) {
  if (!value && dialogProps.value.row.type === 'MENU' && dialogProps.value.row.openType === 'URL') {
    return callback(new Error('请填写链接地址'))
  }
  callback()
}
const authValidate = function (_rule: any, value: any, callback: (arg0?: Error | undefined) => void) {
  if (!value && dialogProps.value.row.type === 'BUTTON') {
    return callback(new Error('请填写授权标识'))
  }
  callback()
}

const rules = reactive({
  parentId: [{ required: true, message: '请选择上级菜单', trigger: 'change' }],
  type: [{ required: true, message: '请选择菜单类型', trigger: 'change' }],
  name: [{ required: true, message: '请填写菜单name', trigger: 'blur' }],
  title: [{ required: true, message: '请填写菜单名称', trigger: 'blur' }],
  path: [{ required: true, validator: pathValidate, trigger: 'blur' }],
  icon: [{ required: true, validator: iconValidate, trigger: 'blur' }],
  openType: [{ required: true, validator: openTypeValidate, trigger: 'change' }],
  component: [{ required: true, validator: componentValidate, trigger: 'blur' }],
  url: [{ required: true, validator: urlValidate, trigger: 'blur' }],
  auth: [{ required: true, validator: authValidate, trigger: 'blur' }],
  sort: [{ required: true, message: '请填写菜单排序', trigger: 'blur' }],
  keepalive: [{ required: true, message: '请选择是否缓存', trigger: 'change' }],
  hide: [{ required: true, message: '请选择是否显示', trigger: 'change' }]
})
const ruleFormRef = ref<FormInstance>()
const handleSubmit = () => {
  ruleFormRef.value!.validate(async (valid) => {
    if (!valid) return
    try {
      if (dialogProps.value.row!.type === 'BUTTON') {
        dialogProps.value.row.path = ''
        dialogProps.value.row.icon = ''
        dialogProps.value.row.openType = ''
      }
      if (dialogProps.value.row!.type !== 'MENU') {
        dialogProps.value.row.openType = ''
      } else {
        if (dialogProps.value.row!.openType == 'TAB') {
          dialogProps.value.row.url = ''
        } else if (dialogProps.value.row!.openType == 'URL') {
          dialogProps.value.row.component = ''
        }
      }
      // 菜单的显隐 & 缓存
      let flag = dialogProps.value.row!.keepalive ? 0 : 1
      dialogProps.value.row!.keepalive = flag
      let flag2 = dialogProps.value.row!.hide ? 0 : 1
      dialogProps.value.row!.hide = flag2
      await dialogProps.value.api!(dialogProps.value.row)
      ElMessage.success({ message: `${dialogProps.value.title}菜单成功！` })
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
    dialogProps.value.row = { type: 'MENU', openType: 'TAB', keepalive: false, hide: false, sort: 0 }
    ruleFormRef.value!.resetFields()
  }
}
</script>
<style lang="less" scoped>
.isabel-option {
  white-space: pre;
}
</style>
