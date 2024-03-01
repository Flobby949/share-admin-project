module.exports = {
  extends: ['@commitlint/config-conventional'],
  rules: {
    'type-enum': [
      2,
      'always',
      [
        'feat', // 新功能(feature)
        'fix', // 修补bug
        'docs', // 文档
        'style', // 代码格式修改, 注意不是 css 修改
        'refactor', // 重构(即不是新增功能，也不是修改BUG的代码)
        'perf', // 优化相关，比如提升性能、体验
        'test', // 添加测试
        'ci', // 持续集成修改
        'build', // 编译相关的修改，例如发布版本、对项目构建或者依赖的改动
        'chore', // 其他修改, 比如改变构建流程、或者增加依赖库、工具等
        'revert', // 回滚到上一个版本
        'workflow', // 工作流改进
        'types', // 类型修改
        'release' // 版本发布
      ]
    ],
    'subject-full-stop': [0, 'never'],
    'subject-case': [0, 'never']
  },
  prompt: {
    useEmoji: true,
    messages: {
      type: '选择你要提交的类型 :',
      scope: '选择一个提交范围（可选）:',
      customScope: '请输入自定义的提交范围 :',
      subject: '填写简短精炼的变更描述 :',
      body: '填写更加详细的变更描述（可选）。使用 "|" 换行 :',
      breaking: '列举非兼容性重大的变更（可选）。使用 "|" 换行 :',
      footerPrefixsSelect: '选择关联issue前缀（可选）:',
      customFooterPrefixs: '输入自定义issue前缀 :',
      footer: '列举关联issue (可选) 例如: #31, #I3244 :',
      confirmCommit: '是否提交或修改commit ?'
    },
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
  }
}
