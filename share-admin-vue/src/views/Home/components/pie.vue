<script setup lang="ts" name="pie">
import type { EChartsDataType } from '@/api/modules/home'
import * as echarts from 'echarts'
import { onMounted } from 'vue'
const props = defineProps<{
  pieDataList: EChartsDataType[]
}>()

onMounted(() => {
  setTimeout(() => {
    init()
  }, 300)
})
const init = () => {
  const dom = document.querySelector('.echarts') as HTMLElement
  var myChart = echarts.init(dom)
  const option = {
    title: {
      text: '各类网盘资源占比',
      subtext: '访问占比',
      left: '56%',
      top: '45%',
      textAlign: 'center',
      textStyle: {
        fontSize: 18,
        color: '#767676'
      },
      subtextStyle: {
        fontSize: 15,
        color: '#a1a1a1'
      }
    },
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '4%',
      left: '2%',
      orient: 'vertical',
      icon: 'circle', //图例形状
      align: 'left',
      itemGap: 20,
      textStyle: {
        fontSize: 13,
        color: '#a1a1a1',
        fontWeight: 500
      },
      formatter: function (name: string) {
        let dataCopy = ''
        for (let i = 0; i < props.pieDataList.length; i++) {
          if (props.pieDataList[i].name == name && props.pieDataList[i].value >= 10000) {
            dataCopy = (props.pieDataList[i].value / 10000).toFixed(2)
            return name + '      ' + dataCopy + 'w'
          } else if (props.pieDataList[i].name == name) {
            dataCopy = props.pieDataList[i].value + ''
            return name + '      ' + dataCopy
          }
        }
        return ''
      }
    },
    series: [
      {
        type: 'pie',
        radius: ['70%', '40%'],
        center: ['57%', '52%'],
        silent: true,
        clockwise: true,
        startAngle: 150,
        data: props.pieDataList,
        labelLine: {
          length: 80,
          length2: 30,
          lineStyle: {
            width: 1
          }
        },
        label: {
          position: 'outside',
          show: true,
          formatter: '{d}%',
          fontWeight: 400,
          fontSize: 19,
          color: '#a1a1a1'
        },
        itemStyle: {
          normal: {
            color: function (params: any) {
              //自定义颜色
              var colorList = ['#feb791', '#fe8b4c', '#b898fd', '#8347fd', '#000', '#fff', '#feb791', '#fe8b4c', '#b898fd', '#8347fd', '#000', '#fff']
              return colorList[params.dataIndex]
            }
          }
        }
      }
    ]
  }
  myChart.setOption(option)
}
</script>
<template>
  <div class="echarts"> </div>
</template>

<style scoped>
.echarts {
  width: 100%;
  height: 100%;
}
</style>
