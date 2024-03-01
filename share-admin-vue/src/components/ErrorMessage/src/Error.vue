<template>
  <div class="not-container">
    <img :src="errorMap[type].url" class="not-img" />
    <div class="not-detail">
      <h2>{{ type }}</h2>
      <h4>{{ errorMap[type].message }}</h4>
      <el-button type="primary" @click="btnClick">返回首页</el-button>
    </div>
  </div>
</template>

<script setup lang="ts">
import pageError from '@/assets/images/404.png'
import networkError from '@/assets/images/500.png'
import noPermission from '@/assets/images/403.png'
import { ElButton } from 'element-plus'

interface ErrorMap {
  url: string
  message: string
}

const errorMap: {
  [key: string]: ErrorMap
} = {
  '404': {
    url: pageError,
    message: '抱歉，您访问的页面不存在~🤷‍♂️🤷‍♀️'
  },
  '500': {
    url: networkError,
    message: '抱歉，您的网络不见了~🤦‍♂️🤦‍♀️'
  },
  '403': {
    url: noPermission,
    message: '抱歉，您无权访问该页面~🙅‍♂️🙅‍♀️'
  }
}

const props = defineProps({
  type: {
    type: String,
    default: '404'
  }
})

const emit = defineEmits(['errorClick'])

const btnClick = () => {
  emit('errorClick', props.type)
}
</script>

<style scoped lang="less">
@import './index.less';
</style>
