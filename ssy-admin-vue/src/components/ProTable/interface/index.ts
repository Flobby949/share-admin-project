import { VNode, ComponentPublicInstance } from 'vue'
import { BreakPoint, Responsive } from '@/components/Grid/interface'
import { TableColumnCtx } from 'element-plus/es/components/table/src/table-column/defaults'
import { ProTableProps } from '@/components/ProTable/index.vue'
import ProTable from '@/components/ProTable/index.vue'

export interface EnumProps {
  /** 选项框显示的文字 */
  label?: string
  /** 选项框值 */
  value?: string | number | boolean | any[]
  /** 是否禁用此选项 */
  disabled?: boolean
  /** 当 tag 为 true 时，此选择会指定 tag 显示类型 */
  tagType?: string
  /** 为树形选择时，可以通过 children 属性指定子选项 */
  children?: EnumProps[]
  [key: string]: any
}

export type TypeProps = 'index' | 'selection' | 'expand'

export type SearchType = 'input' | 'input-number' | 'select' | 'select-v2' | 'tree-select' | 'cascader' | 'date-picker' | 'time-picker' | 'time-select' | 'switch' | 'slider'

export type SearchRenderScope = {
  searchParam: { [key: string]: any }
  placeholder: string
  clearable: boolean
  options: EnumProps[]
  data: EnumProps[]
}
/** 搜索项配置 */
export type SearchProps = {
  /** 当前项搜索框的类型 */
  el?: SearchType
  /** 搜索项参数，根据 element plus 官方文档来传递，该属性所有值会透传到组件 */
  props?: any
  /** 当搜索项 key 不为 prop 属性时，可通过 key 指定 */
  key?: string
  /** 搜索项排序（从大到小） */
  order?: number
  /** 搜索项所占用的列数，默认为1列 */
  span?: number
  /** 搜索字段左侧偏移列数 */
  offset?: number
  /** 搜索项默认值 */
  defaultValue?: string | number | boolean | any[]
  /** 自定义搜索内容渲染（tsx语法） */
  render?: (scope: SearchRenderScope) => VNode
} & Partial<Record<BreakPoint, Responsive>>

export type FieldNamesProps = {
  /**
   * 指定 label 的 key 值，默认为 label
   */
  label: string
  /**
   * 指定 value 的 key 值，默认为 value
   */
  value: string
  /**
   * 指定 children 的 key 值，默认为 children
   */
  children?: string
}

export type RenderScope<T> = {
  row: T
  $index: number
  column: TableColumnCtx<T>
  [key: string]: any
}

export type HeaderRenderScope<T> = {
  $index: number
  column: TableColumnCtx<T>
  [key: string]: any
}

export interface ColumnProps<T = any> extends Partial<Omit<TableColumnCtx<T>, 'children' | 'renderCell' | 'renderHeader'>> {
  /** 是否是标签展示 */
  tag?: boolean
  /** 是否显示在表格当中 */
  isShow?: boolean
  /** 搜索项配置 */
  search?: SearchProps | undefined
  /** 枚举类型（字典） */
  enum?: EnumProps[] | ((params?: any) => Promise<any>)
  /** 当前单元格值是否根据 enum 格式化（示例：enum 只作为搜索项数据） */
  isFilterEnum?: boolean
  /** 指定 label && value && children 的 key 值 */
  fieldNames?: FieldNamesProps
  /** 自定义表头内容渲染（tsx语法） */
  headerRender?: (scope: HeaderRenderScope<T>) => VNode
  /** 自定义单元格内容渲染（tsx语法） */
  render?: (scope: RenderScope<T>) => VNode | string
  /** 多级表头 */
  _children?: ColumnProps<T>[]
}

export type ProTableInstance = Omit<InstanceType<typeof ProTable>, keyof ComponentPublicInstance | keyof ProTableProps>
