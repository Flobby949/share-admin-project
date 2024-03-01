<template>
  <el-dialog
    :model-value="modelValue"
    :width="width"
    :fullscreen="isFullscreen"
    destroy-on-close
    lock-scroll
    draggable
    :close-on-click-modal="false"
    @close="cancelDialog"
    :top="marginTop"
  >
    <template #header>
      <div class="flex justify-between">
        <slot name="title">
          {{ props.title }}
        </slot>
        <Icon
          v-if="props.fullscreen"
          class="mr-18px cursor-pointer is-hover mt-2px z-10"
          :icon="isFullscreen ? 'zmdi:fullscreen-exit' : 'zmdi:fullscreen'"
          color="var(--el-color-info)"
          @click="toggleFull"
        />
      </div>
    </template>
    <el-scrollbar :style="'height: ' + dialogHeight">
      <slot></slot>
    </el-scrollbar>

    <template v-if="slots.footer" #footer>
      <slot name="footer"></slot>
    </template>
  </el-dialog>
</template>

<script setup lang="ts">
import { ref, unref, watch, nextTick, useSlots } from 'vue'
import VueTypes from 'vue-types'
import { isNumber } from '@/utils/is'
const props = defineProps({
  width: VueTypes.string.def('50%'),
  modelValue: VueTypes.bool.def(false),
  title: VueTypes.string.def(''),
  fullscreen: VueTypes.bool.def(true),
  maxHeight: VueTypes.oneOfType([String, Number]).def('500px'),
  cancelDialog: VueTypes.func.def(),
  marginTop: VueTypes.string.def('15vh')
})

const slots = useSlots()
const isFullscreen = ref(false)

const toggleFull = () => {
  isFullscreen.value = !unref(isFullscreen)
}

const dialogHeight = ref(isNumber(props.maxHeight) ? `${props.maxHeight}px` : props.maxHeight)

watch(
  () => isFullscreen.value,
  async (val: boolean) => {
    await nextTick()
    if (val) {
      const windowHeight = document.documentElement.offsetHeight
      dialogHeight.value = `${windowHeight - 55 - 60 - 63}px`
    } else {
      dialogHeight.value = isNumber(props.maxHeight) ? `${props.maxHeight}px` : props.maxHeight
    }
  },
  {
    immediate: true
  }
)
watch(
  () => props.maxHeight,
  async (val: String | Number) => {
    await nextTick()
    if (val) {
      dialogHeight.value = isNumber(props.maxHeight) ? `${props.maxHeight}px` : props.maxHeight
    } else {
      dialogHeight.value = isNumber(props.maxHeight) ? `${props.maxHeight}px` : props.maxHeight
    }
  },
  {
    immediate: true
  }
)
</script>
<style lang="less">
.@{elNamespace}-dialog__header {
  margin-right: 0 !important;
  border-bottom: 1px solid var(--isabel-border-color) !important;
}

.@{elNamespace}-dialog__footer {
  // border-top: none !important;
  box-shadow: var(--el-box-shadow);
}

.is-hover {
  &:hover {
    color: var(--el-color-primary) !important;
  }
}

.dark {
  .@{elNamespace}-dialog__header {
    border-bottom: 1px solid var(--isabel-bg-color) !important;
  }

  .@{elNamespace}-dialog__footer {
    box-shadow: var(--el-box-shadow);
  }
}

.isabel-option {
  white-space: pre;
}
</style>
