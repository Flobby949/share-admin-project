import { defineStore } from 'pinia'
import { pinia } from '../index'
import { TabsState, TabsMenuProps } from '@/store/interface'
import { TABS_WHITE_LIST } from '@/configs/config'
import { usePiniaPersist } from '@/hooks/usePiniaPersist'
import router from '@/router/index'

// TabsStore
export const useTabsStore = defineStore({
  id: 'TabsState',
  state: (): TabsState => ({
    tabsMenuList: []
  }),
  getters: {},
  actions: {
    // Add Tabs
    async addTabs(tabItem: TabsMenuProps) {
      // not add tabs white list
      if (TABS_WHITE_LIST.includes(tabItem.path)) {
        return
      }
      if (this.tabsMenuList.every((item) => item.path !== tabItem.path)) {
        this.tabsMenuList.push(tabItem)
      }
    },
    // Remove Tabs
    async removeTabs(tabPath: string, isCurrent: boolean = true) {
      const tabsMenuList = this.tabsMenuList
      if (isCurrent) {
        tabsMenuList.forEach((item, index) => {
          if (item.path !== tabPath) return
          const nextTab = tabsMenuList[index + 1] || tabsMenuList[index - 1]
          if (!nextTab) return
          router.push(nextTab.path)
        })
      }
      this.tabsMenuList = tabsMenuList.filter((item) => item.path !== tabPath)
    },
    // Close MultipleTab
    async closeMultipleTab(tabsMenuValue?: string) {
      this.tabsMenuList = this.tabsMenuList.filter((item) => {
        return item.path === tabsMenuValue || !item.close
      })
    },
    // Set Tabs
    async setTabs(tabsMenuList: TabsMenuProps[]) {
      this.tabsMenuList = tabsMenuList
    },
    // Set Tabs Title
    async setTabsTitle(title: string) {
      const nowFullPath = location.hash.substring(1)
      this.tabsMenuList.forEach((item) => {
        if (item.path == nowFullPath) item.title = title
      })
    }
  },
  persist: usePiniaPersist('TabsState')
})

export const useTabsStoreWithOut = () => {
  return useTabsStore(pinia)
}
