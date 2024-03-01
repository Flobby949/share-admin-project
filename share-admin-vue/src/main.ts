// 引入windi css
import '@/plugins/windi.css'

// 导入全局的svg图标
import '@/plugins/svgIcon'
// 初始化多语言
import { setupI18n } from '@/plugins/vueI18n'

// 引入element-plus
import { setupElementPlus } from '@/plugins/elementPlus'

// 引入全局样式
import '@/styles/index.less'

// 引入状态管理
import { setupPinia } from '@/store'

// 引入vue router
import { setupRouter } from '@/router'

// 全局组件
import { setupGlobCom } from '@/components'

// 权限
import { setupPermission } from '@/directives'

import { createApp } from 'vue'
import App from './App.vue'

const setupAll = async () => {
  const app = createApp(App)

  await setupI18n(app)

  setupElementPlus(app)

  setupGlobCom(app)

  setupPinia(app)

  setupRouter(app)

  setupPermission(app)

  app.mount('#app')
}

setupAll()
