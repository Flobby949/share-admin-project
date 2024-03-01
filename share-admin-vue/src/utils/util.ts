import { isArray } from '@/utils/is'
import { SysMenu } from '@/api/interface/index'

/**
 * @description 获取localStorage
 * @param {String} key Storage名称
 * @return string
 */
export function localGet(key: string) {
  const value = window.localStorage.getItem(key)
  try {
    return JSON.parse(window.localStorage.getItem(key) as string)
  } catch (error) {
    return value
  }
}

/**
 * @description 存储localStorage
 * @param {String} key Storage名称
 * @param {Any} value Storage值
 * @return void
 */
export function localSet(key: string, value: any) {
  window.localStorage.setItem(key, JSON.stringify(value))
}

/**
 * @description 清除localStorage
 * @param {String} key Storage名称
 * @return void
 */
export function localRemove(key: string) {
  window.localStorage.removeItem(key)
}

/**
 * @description 清除所有localStorage
 * @return void
 */
export function localClear() {
  window.localStorage.clear()
}

/**
 * @description 判断数据类型
 * @param {Any} val 需要判断类型的数据
 * @return string
 */
export function isType(val: any) {
  if (val === null) return 'null'
  if (typeof val !== 'object') return typeof val
  else return Object.prototype.toString.call(val).slice(8, -1).toLocaleLowerCase()
}

/**
 * @description 生成唯一 uuid
 * @return string
 */
export function generateUUID() {
  if (typeof crypto === 'object') {
    if (typeof crypto.randomUUID === 'function') {
      return crypto.randomUUID()
    }
    if (typeof crypto.getRandomValues === 'function' && typeof Uint8Array === 'function') {
      const callback = (c: any) => {
        const num = Number(c)
        return (num ^ (crypto.getRandomValues(new Uint8Array(1))[0] & (15 >> (num / 4)))).toString(16)
      }
      return '10000000-1000-4000-8000-100000000000'.replace(/[018]/g, callback)
    }
  }
  let timestamp = new Date().getTime()
  let performanceNow = (typeof performance !== 'undefined' && performance.now && performance.now() * 1000) || 0
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
    let random = Math.random() * 16
    if (timestamp > 0) {
      random = (timestamp + random) % 16 | 0
      timestamp = Math.floor(timestamp / 16)
    } else {
      random = (performanceNow + random) % 16 | 0
      performanceNow = Math.floor(performanceNow / 16)
    }
    return (c === 'x' ? random : (random & 0x3) | 0x8).toString(16)
  })
}

/**
 * 判断两个对象是否相同
 * @param a 要比较的对象一
 * @param b 要比较的对象二
 * @returns 相同返回 true，反之则反
 */
export function isObjectValueEqual(a: { [key: string]: any }, b: { [key: string]: any }) {
  if (!a || !b) return false
  const aProps = Object.getOwnPropertyNames(a)
  const bProps = Object.getOwnPropertyNames(b)
  if (aProps.length != bProps.length) return false
  for (let i = 0; i < aProps.length; i++) {
    const propName = aProps[i]
    const propA = a[propName]
    const propB = b[propName]
    if (!b.hasOwnProperty(propName)) return false
    if (propA instanceof Object) {
      if (!isObjectValueEqual(propA, propB)) return false
    } else if (propA !== propB) {
      return false
    }
  }
  return true
}

/**
 * @description 生成随机数
 * @param {Number} min 最小值
 * @param {Number} max 最大值
 * @return number
 */
export function randomNum(min: number, max: number): number {
  const num = Math.floor(Math.random() * (min - max) + max)
  return num
}

/**
 * @description 获取当前时间对应的提示语
 * @return string
 */
export function getTimeState() {
  // 获取当前时间
  const timeNow = new Date()
  // 获取当前小时
  const hours = timeNow.getHours()
  // 判断当前时间段
  if (hours >= 6 && hours <= 10) return `早上好 ⛅`
  if (hours >= 10 && hours <= 14) return `中午好 🌞`
  if (hours >= 14 && hours <= 18) return `下午好 🌞`
  if (hours >= 18 && hours <= 24) return `晚上好 🌛`
  if (hours >= 0 && hours <= 6) return `凌晨好 🌛`
}

/**
 * @description 获取浏览器默认语言
 * @return string
 */
export function getBrowserLang() {
  const browserLang = navigator.language ? navigator.language : navigator.browserLanguage
  let defaultBrowserLang = ''
  if (browserLang.toLowerCase() === 'cn' || browserLang.toLowerCase() === 'zh' || browserLang.toLowerCase() === 'zh-cn') {
    defaultBrowserLang = 'zh'
  } else {
    defaultBrowserLang = 'en'
  }
  return defaultBrowserLang
}

/**
 * @description 递归查询当前路由所对应的路由
 * @param {Array} menuList 所有菜单列表
 * @param {String} path 当前访问地址
 * @return array
 */
export async function filterCurrentRoute(menuList: Menu.MenuOptions[], path: string) {
  let result = {}
  for (const item of menuList) {
    if (item.path === path) return item
    if (item.children) {
      const res = await filterCurrentRoute(item.children, path)
      if (Object.keys(res).length) result = res
    }
  }
  return result
}

/**
 * @description 扁平化数组对象(主要用来处理路由菜单)
 * @param {Array} menuList 所有菜单列表
 * @return array
 */
export function getParseMenuArr(menuList: SysMenu.ResMenuList[]): Menu.MenuOptions[] {
  const newMenuList: Menu.MenuOptions[] = []
  for (let index = 0; index < menuList.length; index++) {
    const element = menuList[index]
    const menuItem: Menu.MenuOptions = {
      path: element.path,
      name: element.name,
      component: element.component === '' ? undefined : element.component,
      redirect: element.children.length > 0 ? element.children[0].path : undefined,
      meta: {
        icon: element.icon,
        title: element.title,
        isLink: element.openType === 'URL' ? element.url : undefined,
        isHide: element.hide ?? false,
        isAffix: false,
        isKeepAlive: element.keepalive
      },
      children: element.children.length > 0 ? getParseMenuArr(element.children) : undefined
    }
    newMenuList.push(menuItem)
  }
  return newMenuList
}

/**
 * @description 扁平化数组对象(主要用来处理路由菜单)
 * @param {Array} menuList 所有菜单列表
 * @return array
 */
export function getFlatArr(menuList: Menu.MenuOptions[]) {
  const newMenuList: Menu.MenuOptions[] = JSON.parse(JSON.stringify(menuList))
  return newMenuList.reduce((pre: Menu.MenuOptions[], current: Menu.MenuOptions) => {
    let flatArr = [...pre, current]
    if (current.children) flatArr = [...flatArr, ...getFlatArr(current.children)]
    return flatArr
  }, [])
}

/**
 * @description 使用递归，过滤需要缓存的路由
 * @param {Array} menuList 所有菜单列表
 * @param {Array} cacheArr 缓存的路由菜单 name ['**','**']
 * @return array
 * */
export function getKeepAliveRouterName(menuList: Menu.MenuOptions[], keepAliveArr: string[] = []) {
  menuList.forEach((item) => {
    item.meta.isKeepAlive && item.name && keepAliveArr.push(item.name)
    item.children?.length && getKeepAliveRouterName(item.children, keepAliveArr)
  })
  return keepAliveArr
}

/**
 * @description 使用递归，过滤出需要渲染在左侧菜单的列表（剔除 isHide == true 的菜单）
 * @param {Array} menuList 所有菜单列表
 * @return array
 * */
export function getShowMenuList(menuList: Menu.MenuOptions[]) {
  const newMenuList: Menu.MenuOptions[] = JSON.parse(JSON.stringify(menuList))
  return newMenuList.filter((item) => {
    item.children?.length && (item.children = getShowMenuList(item.children))
    return !item.meta?.isHide
  })
}

/**
 * @description 使用递归，过滤出当前路径匹配的面包屑地址
 * @param {String} path 当前访问地址
 * @param {Array} menuList 所有菜单列表
 * @returns array
 */
export function getCurrentBreadcrumb(path: string, menuList: Menu.MenuOptions[]) {
  const tempPath: Menu.MenuOptions[] = []
  try {
    const getNodePath = (node: Menu.MenuOptions) => {
      tempPath.push(node)
      if (node.path === path) throw new Error('Find IT!')
      if (node.children?.length) node.children.forEach((item) => getNodePath(item))
      tempPath.pop()
    }
    menuList.forEach((item) => getNodePath(item))
  } catch (e) {
    return tempPath
  }
}

/**
 * @description 双重递归找出所有面包屑存储到 pinia/vuex 中
 * @param {Array} menuList 所有菜单列表
 * @returns array
 */
export function getAllBreadcrumbList(menuList: Menu.MenuOptions[]) {
  const handleBreadcrumbList: { [key: string]: any } = {}
  const loop = (menuItem: Menu.MenuOptions) => {
    if (menuItem?.children?.length) menuItem.children.forEach((item) => loop(item))
    handleBreadcrumbList[menuItem.path] = getCurrentBreadcrumb(menuItem.path, menuList)
  }
  menuList.forEach((item) => loop(item))
  return handleBreadcrumbList
}

/**
 * @description 格式化表格单元格默认值(el-table-column)
 * @param {Number} _row 行
 * @param {Number} _col 列
 * @param {String} callValue 当前单元格值
 * @return string
 * */
export function defaultFormat(_row: number, _col: number, callValue: any) {
  // 如果当前值为数组,使用 / 拼接（根据需求自定义）
  if (isArray(callValue)) return callValue.length ? callValue.join(' / ') : '--'
  return callValue ?? '--'
}

/**
 * @description 处理无数据情况
 * @param {String} callValue 需要处理的值
 * @return string
 * */
export function formatValue(callValue: any) {
  // 如果当前值为数组,使用 / 拼接（根据需求自定义）
  if (isArray(callValue)) return callValue.length ? callValue.join(' / ') : '--'
  return callValue ?? '--'
}

/**
 * @description 处理 prop 为多级嵌套的情况(列如: prop:user.name)
 * @param {Object} row 当前行数据
 * @param {String} prop 当前 prop
 * @return any
 * */
export function handleRowAccordingToProp(row: { [key: string]: any }, prop: string) {
  if (!prop.includes('.')) return row[prop]
  prop.split('.').forEach((item) => {
    row = row[item] ?? '--'
  })
  return row
}

/**
 * @description 处理 prop，当 prop 为多级嵌套时 ==> 返回最后一级 prop
 * @param {String} prop 当前 prop
 * @return string
 * */
export function handleProp(prop: string) {
  const propArr = prop.split('.')
  if (propArr.length == 1) return prop
  return propArr[propArr.length - 1]
}

/**
 * @description 根据枚举列表查询当需要的数据（如果指定了 label 和 value 的 key值，会自动识别格式化）
 * @param {String} callValue 当前单元格值
 * @param {Array} enumData 枚举列表
 * @param {String} type 过滤类型（目前只有 tag）
 * @return string
 * */
export function filterEnum(callValue: any, enumData: { [key: string]: any } | undefined, searchProps?: { [key: string]: any }, type?: string): string {
  const value = searchProps?.value ?? 'value'
  const label = searchProps?.label ?? 'label'
  let filterData: any = {}
  if (Array.isArray(enumData)) filterData = enumData.find((item: any) => item[value] === callValue)
  if (type == 'tag') return filterData?.tagType ? filterData.tagType : ''
  return filterData ? filterData[label] : '--'
}

/**
 * 获取当前日期
 * @returns
 */
export function getCurrentDate() {
  const dateT = new Date()
  return dateT.getFullYear() + '/' + (dateT.getMonth() + 1).toString().padStart(2, '0') + '/' + dateT.getDate().toString().padStart(2, '0') + ' '
}
/**
 * 获取当前时间
 * @returns
 */
export function getCurrentTime() {
  const dateT = new Date()
  return dateT.getHours().toString().padStart(2, '0') + ':' + dateT.getMinutes().toString().padStart(2, '0') + ' '
}
// 获取路径参数
// @params:url
// @return:参数
export function getURLParams(url: any) {
  const theRequest = new Object()
  if (url.indexOf('?') != -1) {
    const str = url.substr(url.indexOf('?') + 1)
    const strs = str.split('&')
    for (let i = 0; i < strs.length; i++) {
      theRequest[strs[i].split('=')[0]] = decodeURIComponent(strs[i].split('=')[1])
    }
  }
  return theRequest
}
/**
 * 格式化当前日期
 * @returns
 */
export function getFormatCurrentDate(date: any) {
  const dateT = date
  return dateT.getFullYear() + '-' + (dateT.getMonth() + 1).toString().padStart(2, '0') + '-' + dateT.getDate().toString().padStart(2, '0')
}
