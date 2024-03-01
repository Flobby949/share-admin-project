/* themeProp */
export type LayoutType = 'vertical' | 'classic' | 'transverse' | 'columns'
export type AssemblySizeType = 'large' | 'default' | 'small'

export type LanguageType = 'zh' | 'en' | null

export interface KeepAliveState {
  keepAliveName: string[]
}

export interface ThemeProps {
  layout: LayoutType
  primary: string
  isDark: boolean
  isGrey: boolean
  isCollapse: boolean
  isWeak: boolean
  breadcrumb: boolean
  breadcrumbIcon: boolean
  tabs: boolean
  tabsIcon: boolean
  footer: boolean
  maximize: boolean
  asideInverted: boolean
}

export interface TabsMenuProps {
  icon: string
  title: string
  path: string
  name: string
  close: boolean
}

/* TabsState */
export interface TabsState {
  tabsMenuList: TabsMenuProps[]
}

/* GlobalState */
export interface AppState {
  token: string
  userInfo: any
  assemblySize: AssemblySizeType
  theme: ThemeProps
}

export interface PermissionState {
  routeName: string
  homePath: string
  buttonList: string[]
  menuList: Menu.MenuOptions[]
}

// locale
export interface Language {
  el: Recordable
  name: string
}

export interface LocaleDropdownType {
  lang: LocaleType
  name?: string
  elLocale?: Language
}

export interface UserinfoType {
  avatar: string
  createTime: any
  pkId: number
  roleId: number
  status: number
  username: string
}
