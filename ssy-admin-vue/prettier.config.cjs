module.exports = {
  // 1.最大行数，默认80
  printWidth: 180,
  // 2.tab宽度
  tabWidth: 2,
  // 3.是否使用tab来缩进
  useTabs: false,
  // 4.结尾是否添加分号
  semi: false,
  // 5. vue script和style标签中是否缩进,开启可能会破坏编辑器的代码折叠
  vueIndentScriptAndStyle: false,
  // 6.使用单引号
  singleQuote: true,
  // 7.object对象中key值是否加引号（quoteProps: "<as-needed|consistent|preserve>"）as-needed只有在需求要的情况下加引号，consistent是有一个需要引号就统一加，preserve是保留用户输入的引号
  quoteProps: 'as-needed',
  // 8.在jsx文件中的引号需要单独设置
  jsxSingleQuote: false,
  // 9.object对象里面的key和value值和括号间的空格
  bracketSpacing: true,
  // 10.尾部逗号设置
  trailingComma: 'none',

  // 12.箭头函数单个参数的情况是否省略括号，默认always是总是带括号
  arrowParens: 'always',
  // 13.插入pragma到已经完成的format的文件开头
  insertPragma: false,
  // 14.格式化有特定开头编译指示的文件 比如下面两种
  requirePragma: false,
  // 15.文章换行,默认情况下会对你的markdown文件换行进行format会控制在printwidth以内
  proseWrap: 'never',
  // 16.html中的空格敏感性
  htmlWhitespaceSensitivity: 'strict',
  // 17.行尾换行符
  endOfLine: 'auto',
  // 18.range是format执行的范围，可以选执行一个文件的一部分，默认的设置是整个文件（rangeStart: <int>  rangeEnd: <int>）
  rangeStart: 0,
  rangeEnd: Infinity
  // 19.控制被引号包裹的代码是否进行格式化，默认是auto
  // embeddedLanguageFormatting: 'off'
}
// 11.jsx标签多行属性写法时，尖括号是否另起一行 jsxBracketSameLine is deprecated.
// jsxBracketSameLine: false,
