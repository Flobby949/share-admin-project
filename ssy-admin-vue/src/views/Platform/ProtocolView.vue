<template>
  <div class="card content-box">
    <div class="mb-2 flex items-center text-sm w-[calc(100%-1rem)]">
      <el-radio-group v-model="radio" class="ml-4">
        <el-radio label="0" size="large">隐私协议</el-radio>
        <el-radio label="1" size="large">用户协议</el-radio>
        <el-radio label="2" size="large">关于我们</el-radio>
      </el-radio-group>
    </div>
    <WangEditor v-model:value="content" height="400px" />
    <el-row>
      <el-button type="primary" @click="dialogVisible = true"> 内容预览 </el-button>
      <el-button type="danger" @click="saveProtocol" v-hasPermi="['sys:protocol:edit']"> 保存 </el-button>
    </el-row>
    <el-dialog v-model="dialogVisible" title="富文本内容预览" width="80%" top="50px">
      <div class="view" v-html="content"></div>
    </el-dialog>
  </div>
</template>

<script setup lang="ts" name="protocol">
import { ref, onMounted, watch } from 'vue'
import WangEditor from '@/components/WangEditor/index.vue'
import { ProtocolApi } from '@/api/modules/platform'
const content = ref('')
const radio = ref('0')
const dialogVisible = ref(false)

watch(radio, (_) => {
  let type = parseInt(radio.value)
  ProtocolApi.info({ type }).then((res) => {
    content.value = res.data.content
  })
})

onMounted(async () => {
  let type = parseInt(radio.value)
  const { data } = await ProtocolApi.info({ type })
  content.value = data.content
})

const saveProtocol = () => {
  let type = parseInt(radio.value)
  ProtocolApi.edit({ content: content.value, type })
}
</script>

<style scoped lang="less">
.el-button {
  margin-top: 20px;
}
:deep(.el-dialog__body) {
  height: 700px !important;
  overflow: auto;
}
</style>
