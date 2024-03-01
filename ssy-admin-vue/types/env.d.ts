// 项目配置类型
/// <reference types="vite/client" />

declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  // eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/ban-types
  const component: DefineComponent<{}, {}, any>
  export default component
}

interface ImportMetaEnv {
  // 项目标题
  readonly VITE_APP_TITLE: string
  // 项目端口
  readonly VITE_PORT: number
  // 项目是否open
  readonly VITE_OPEN: boolean
  // 接口前缀
  readonly VITE_API_URL: string
  // 打包后路径
  readonly VITE_BASE_PATH: string
  //输出路径
  readonly VITE_OUT_DIR: string
  // 是否删除debugger
  readonly VITE_DROP_DEBUGGER: String
  // 是否删除console
  readonly VITE_DROP_CONSOLE: String
  // 是否删除sourcemap
  readonly VITE_SOURCEMAP: String
}

declare interface ImportMeta {
  readonly env: ImportMetaEnv
}
