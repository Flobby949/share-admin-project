import type { App, Directive, DirectiveBinding } from 'vue'
import { intersection } from 'lodash-es'
import { isArray } from '@/utils/is'
import { usePermissionStoreWithOut } from '@/store/modules/permission'

const permissionStore = usePermissionStoreWithOut()

// 全部权限
const all_permission = ['*.*.*']
const hasPermission = (value: string | string[]): boolean => {
  const permissions = permissionStore.buttonList as string[]
  if (!value) {
    throw new Error('请设置操作权限')
  }
  if (!isArray(value)) {
    return permissions?.includes(value as string)
  }
  if (all_permission[0] === permissions[0]) {
    return true
  }
  return (intersection(value, permissions) as string[]).length > 0
}
function hasPermi(el: Element, binding: DirectiveBinding) {
  const value = binding.value

  const flag = hasPermission(value)
  if (!flag) {
    el.parentNode?.removeChild(el)
  }
}
const mounted = (el: Element, binding: DirectiveBinding<any>) => {
  hasPermi(el, binding)
}

const permiDirective: Directive = {
  mounted
}

export const setupPermissionDirective = (app: App<Element>) => {
  app.directive('hasPermi', permiDirective)
}

export default permiDirective
