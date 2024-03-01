# 管理平台

## 运行环境

`node 18.12.0+`(推荐使用 nvm 管理 node 版本)

[pnpm 8.6.1+](https://pnpm.io/zh/installation)(npm 也可以，这个装依赖比较快，使用 nvm 的话用 pnpm 官网的全局安装)

## 浏览器支持

本地开发推荐使用 Chrome 最新版浏览器。

由于 Vue 3 不再支持 IE11，本项目也不支持 IE。

Chrome Edge Firefox Safari

## IDEA 推荐

`VSCode` 如果你使用的 IDE 是 vscode 的话，可以安装以下工具来提高开发效率及代码格式化：

- `Iconify IntelliSense` - Iconify 图标插件
- `windicss IntelliSense` - windicss 提示插件
- `I18n-ally` - i18n 插件
- `Volar` - vue 开发必备
- `ESLint` - 脚本代码检查
- `Prettier` - 代码格式化
- `Stylelint` - css 格式化
- `DotENV` - .env 文件 高亮

#代码获取

## 运行

安装依赖之前确保项目处在 git 环境（有.git）

安装依赖 `npm install` 或 `pnpm install`

本地运行 `npm/pnpm run dev`

打包 `npm/pnpm run build-pro`

命令详情看 `package.json`

## 登录账号

- 超级管理员，账号 admin \ 密码 admin
- 测试账号，账号 dong \ 密码 Dong@1234

## 目录结构

```
.
├── .github # github workflows 相关
├── .husky # husky 配置
├── .vscode # vscode 配置
├── public # 静态资源
├── src # 项目代码
│   ├── api # api接口管理
│   ├── assets # 静态资源
│   ├── components # 公用组件
│   ├── directives # 指令
│   ├── enums # 枚举
│   ├── hooks # 常用hooks
│   ├── layout # 布局组件
│   ├── locales # 语言文件
│   ├── plugins # 外部插件
│   ├── router # 路由配置
│   ├── store # 状态管理
│   ├── styles # 全局样式
│   ├── utils # 全局工具类
│   ├── views # 路由页面
│   ├── App.vue # 入口vue文件
│   ├── main.ts # 主入口文件
│   └── permission.ts # 路由拦截
├── types # 全局类型
├── .env # 通用配置
├── .env.dev # 打包到开发环境 环境变量配置
├── .env.pro # 打包到生产环境 环境变量配置
├── .env.test # 打包到测试环境 环境变量配置
├── .eslintignore # eslint 跳过检测配置
├── .eslintrc.js # eslint 配置
├── .gitignore # git 跳过配置
├── .prettierignore # prettier 跳过检测配置
├── .stylelintignore # stylelint 跳过检测配置
├── commitlint.config.js # git commit 提交规范配置
├── index.html # 入口页面
├── package.json
├── .postcssrc.js # postcss 配置
├── prettier.config.js # prettier 配置
├── README.md #  README
├── stylelint.config.js # stylelint 配置
├── tsconfig.json # typescript 配置
├── vite.config.ts # vite 配置
└── windi.config.ts # windicss 配置
```

## 一些封装组件

- [ProTable](https://juejin.cn/post/7166068828202336263/#heading-14) - Table 增强组件

## Git 提交规范

    types: [
      { value: 'feat', name: 'feat:    ✨ 新增功能', emoji: '✨' },
      { value: 'fix', name: 'fix:    🐛 修复缺陷', emoji: '🐛' },
      { value: 'docs', name: 'docs:    📝 文档更新', emoji: '📝' },
      { value: 'style', name: 'style:    🎨 代码格式', emoji: '🎨' },
      { value: 'refactor', name: 'refactor:    ♻️ 代码重构', emoji: '♻️' },
      { value: 'perf', name: 'perf:    ⚡️  优化性能、体验', emoji: '⚡️' },
      { value: 'test', name: 'test:    ✅ 测试相关', emoji: '✅' },
      { value: 'ci', name: 'ci:    🎡 持续集成', emoji: '🎡' },
      { value: 'build', name: 'build:    📦️ 构建相关', emoji: '📦️' },
      { value: 'chore', name: 'chore:    🔨 其他修改', emoji: '🔨' },
      { value: 'revert', name: 'revert:    ⏪️ 回退代码', emoji: '⏪️' },
      { value: 'workflow', name: 'workflow:   🔧 工作流改进', emoji: '🔧' },
      { value: 'types', name: 'types:   ✏️ 类型修改', emoji: '✏️' },
      { value: 'release', name: 'release:   🔖 版本发布', emoji: '🔖' }
    ]
