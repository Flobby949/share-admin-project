<script setup lang="ts">
import * as echarts from 'echarts/core'
import 'echarts-wordcloud'
import { onMounted } from 'vue'
import type { EChartsDataType } from '@/api/modules/home'
const props = defineProps<{
  keyWordList: EChartsDataType[]
}>()

onMounted(() => {
  setTimeout(() => {
    init()
  }, 300)
})

const init = () => {
  const dom = document.querySelector('.wordCloud') as HTMLElement
  var myChart = echarts.init(dom)
  const option = {
    series: [
      {
        type: 'wordCloud',

        // 要绘制云的形状,默认是 circle，可选的参数有 cardioid 、 diamond 、 triangle-forward 、 triangle 、 star
        shape: 'circle',

        // 保持maskImage的纵横比或1:1的形状
        // 从echarts-wordcloud@2.1.0开始支持该选项
        keepAspect: false,

        // 左/上/宽/高/右/下用于字云的定位
        // 默认放置在中心，大小为75% x 80%。
        left: 'center',
        top: 'center',
        width: '100%',
        height: '100%',
        right: null,
        bottom: null,

        // 数据中的值将映射到的文本大小范围。
        // 默认值为最小12px，最大60px。
        sizeRange: [12, 60],

        // 文字旋转范围和步进程度。文本将通过rotationStep 45在[- 90,90]范围内随机旋转
        rotationRange: [-90, 90],
        rotationStep: 45,

        // 网格大小(以像素为单位)，用于标记画布的可用性
        // 网格大小越大，单词之间的间隔就越大
        gridSize: 8,

        // 设置为true允许文字部分地绘制在画布之外。
        // 允许画比画布大的字
        // 从echarts-wordcloud@2.1.0开始支持该选项
        drawOutOfBound: false,

        // 如果字体太大，无法显示文本，是否缩小文本。如果设置为false，则文本将不被渲染。如果设置为true，文本将被缩小。
        shrinkToFit: false,

        // 是否执行布局动画。
        //当单词较多时禁用会导致UI阻塞。
        layoutAnimation: true,

        // 全局文本样式
        textStyle: {
          fontFamily: 'sans-serif',
          fontWeight: 'bold',
          // Color可以是回调函数或颜色字符串
          color: function () {
            // 任意颜色
            return 'rgb(' + [Math.round(Math.random() * 160), Math.round(Math.random() * 160), Math.round(Math.random() * 160)].join(',') + ')'
          }
        },
        emphasis: {
          focus: 'self',
          textStyle: {
            textShadowBlur: 10,
            textShadowColor: '#333'
          }
        },
        data: props.keyWordList
      }
    ]
  }
  console.log(props.keyWordList, 'keyWordList')

  myChart.setOption(option)
}
</script>

<template>
  <div class="wordCloud"></div>
</template>

<style lang="less" scoped>
.wordCloud {
  height: 100%;
  width: 100%;
}
</style>
