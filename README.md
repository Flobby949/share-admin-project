# 资源分享管理平台

## share-admin-api

管理系统后端

## share-admin-vue

管理系统前端

### 启动前端
使用 VSCode 打开`share-admin-vue`, 根据 README 说明安装推荐的插件，然后安装依赖后运行。
```markdown
安装依赖 npm install 或 pnpm install

本地运行 npm run dev 或 pnpm run dev
```
打开浏览器来到管理后台的登录页，下面是默认的超管账号
```markdown
- 超级管理员，账号 admin 密码 admin
```
### 基本功能说明
此管理后台的前端使用的框架来自 Github 上的开源项目`Geeker-Admin`，基于 Vue3.3 + Vite4 + TS + Pinia + Element-Plus。

[Geeker-Admin | 一款简单易用的中后台模版](https://docs.spicyboy.cn/)

将其简化了项目结构（删除非必需页面)，以及联调了后端的基本接口。

可以查看它的在线预览，需要添加回来的页面或组件，去 Geeker-Admin 仓库复制回来即可。可以查看它的源码了解使用方法，特别是`ProTable`超级表格组件，下面给出了框架开发者 HalseySpicy 整理的组件使用文档，当然，后续的教程里也会说明如何使用。

[Geeker-Admin | 组件文档](https://docs.spicyboy.cn/components/proTable.html)

### 需要掌握的基础知识

- 本项目基于 Vue3.3、Vite4、TS、Pinia、Element-Plus、windicss 开发，并全部采用了的单文件组件 `＜script setup＞`写法。
- 建议您在开发前先学习以下内容，提前了解和学习这些知识，会对项目理解非常有帮助：
   - [Vue3 文档](https://cn.vuejs.org/guide/introduction.html)
   - [Vue-Router 文档](https://router.vuejs.org/zh/guide/)
   - [Vite 文档](https://cn.vitejs.dev/guide/)
   - [TypeScript 文档](https://www.typescriptlang.org/zh/docs/)
   - [Pinia 文档](https://pinia.web3doc.top/introduction.html)
   - [Element-Plus 文档](https://element-plus.org/zh-CN/component/button.html)
   - [ES6 教程](https://es6.ruanyifeng.com/)
   - [Windi CSS 文档](https://windicss.org/utilities/)  （也可以查看 tailwindcss 的文档来使用 windicss）
### 菜单管理
在系统管理的菜单管理中，可以新增动态路由到数据库中，需要注意的是，当菜单类型为【菜单项】时，菜单 name 必须为对应的 vue 文件名称，否则 vue 的 keep-alive 缓存组件对该页面不生效。

![image.png](https://cdn.nlark.com/yuque/0/2024/png/22673215/1709088319756-7a26456b-81dd-4fb9-96c9-14950de3dae4.png#averageHue=%23acb366&clientId=u6e790756-88d7-4&from=paste&height=671&id=u37afa169&originHeight=671&originWidth=982&originalType=binary&ratio=1&rotation=0&showTitle=false&size=110415&status=done&style=shadow&taskId=u0f289b9d-b760-493b-8ec6-ad6db84b190&title=&width=982)

当菜单类型为【按钮】时，我们配置的授权标识就可以用在按钮组件，若用户所绑定的角色没有获取相关授权，则页面上不会显示该按钮，

![image.png](https://cdn.nlark.com/yuque/0/2024/png/22673215/1709088833562-699a1f81-eceb-4252-b57e-8970fc50b2e9.png#averageHue=%23fefefe&clientId=u6e790756-88d7-4&from=paste&height=406&id=ua4e9a466&originHeight=406&originWidth=1166&originalType=binary&ratio=1&rotation=0&showTitle=false&size=67080&status=done&style=shadow&taskId=u2cabf2e3-c10e-4531-a0e3-87f2b61e30b&title=&width=1166)

使用自定义指令`v-hasPermi`给按钮绑定权限

```vue
<!-- 表格操作 -->
<el-button v-hasPermi="['sys:manager:view']" type="primary" link :icon="View" @click="openDrawer('查看', scope.row)">查看</el-button>
<el-button v-hasPermi="['sys:manager:edit']" type="primary" link :icon="EditPen" @click="openDrawer('编辑', scope.row)">编辑</el-button>
<el-button v-hasPermi="['sys:manager:reset-psw']" type="warning" link :icon="EditPen" @click="openDrawer('重置', scope.row)">重置密码</el-button>
<el-button v-hasPermi="['sys:manager:remove']" type="danger" link :icon="Delete" @click="deleteAccount(scope.row)">删除</el-button>
```
然后在角色管理里给角色绑定权限。

![image.png](https://cdn.nlark.com/yuque/0/2024/png/22673215/1709089078369-eadfb6a9-c662-4c37-915d-c0fc6d68eae6.png#averageHue=%23d5d5d5&clientId=u6e790756-88d7-4&from=paste&height=536&id=u29434f0b&originHeight=536&originWidth=949&originalType=binary&ratio=1&rotation=0&showTitle=false&size=48130&status=done&style=shadow&taskId=ua59b2488-ef49-4442-8124-6ed708771b8&title=&width=949)

当然，后端也需要对对应的 API 请求做权限校验，以管理员的删除权限举例，给`SysMangerController`的 delete 方法一个`PreAuthorize`注解即可。

```java
@PreAuthorize("hasAuthority('sys:manager:remove')")
@PostMapping("remove")
public Result<String> delete(@RequestBody List<Integer> idList) {}
```

### 字典管理
在【系统管理/字典管理】中新增字典，字典编号一般输入英文，然后在操作栏里对该字典进行配置，字典的 key 为名称（string类型），value 为 数据值（int 类型），如下图所示。

![image.png](https://cdn.nlark.com/yuque/0/2024/png/22673215/1709131434442-c5e22d18-61f0-43f1-8ac6-8ca1d5fb506a.png#averageHue=%23fefefe&clientId=u6f328f91-5116-4&from=paste&height=630&id=u4590b2d9&originHeight=1260&originWidth=1418&originalType=binary&ratio=2&rotation=0&showTitle=false&size=76620&status=done&style=shadow&taskId=u933d0486-66a6-4bbb-9120-95ec103ab12&title=&width=709)

配置字典后，就可以在其他页面使用接口获取字典列表，例如在管理员管理里添加一列性别，表格配置项如下，
```typescript
// Manager.vue
import { dictConfigList } from '@/api/modules/dict/dictConfig'
// 表格配置项
const columns: ColumnProps<SysManager.ResManagerList>[] = [
    {
    prop: 'gender',
    label: '性别',
    width: 80,
    // enum: [
    //   { title: '男', value: 1 },
    //   { title: '女', value: 0 }
    // ],
    enum: () => dictConfigList('gender'),
    fieldNames: { label: 'title', value: 'value' },
    search: { el: 'select', props: { filterable: true } }
  },
]
```
`enum`属性定义枚举类型，可以直接写数组，也可以请求接口获取，`fieldNames`属性指定 label && value && children 的 key 值（该接口返回的字典 key 字段名叫做 title，所以需要将 label 指向 title），`search`属性指定搜索框的类型。

![image.png](https://cdn.nlark.com/yuque/0/2024/png/22673215/1709134531417-c999ae19-5959-4bd8-bfe3-fa6dba7810a4.png#averageHue=%23fdfcfc&clientId=u6f328f91-5116-4&from=paste&height=414&id=ub218ce5d&originHeight=1102&originWidth=1558&originalType=binary&ratio=2&rotation=0&showTitle=false&size=174838&status=done&style=shadow&taskId=u62b91edf-51b0-4d70-a4dc-6b5eaa093a1&title=&width=585)

### 使用 ProTable 组件
> ProTable 组件使用属性透传，支持` el-table`  && `el-table-column`  所有属性、事件、方法的调用，

- [Table 表格｜Table 属性 | Element Plus](https://element-plus.org/zh-CN/component/table.html#table-%E5%B1%9E%E6%80%A7)
- [Table 表格| Table-column 属性 | Element Plus](https://element-plus.org/zh-CN/component/table.html#table-column-%E5%B1%9E%E6%80%A7)
- [ProTable文档 ｜ 二次封装 el-table](https://juejin.cn/post/7166068828202336263#heading-14)

> ProTable 的一些主要的列配置项可直接在 `src/components/ProTable/index.vue`文件里看`interface ProTableProps`的属性注释。

### 举个例子
要建一个用户管理的页面，`UserManager.vue`  ,代码如下，<br />其中 `ProTable` 组件通常需要传递的属性有`ref` 、`colums` 、`requestApi`、 `dataCallback` 。

- `rowKey` 属性指定行数据的 key，默认为 id，若不是id则需要自己单独配置，如示例所示的`rowKey="pkId"` ，也可以去修改 ProTable 组件的 rowKey 默认值进行全局修改。
- 如果暂时没有接口数据，那么可以把属性`requestApi` 换成`data` ,可传入静态 table data 数据。
- 属性`cell-style` 来自 element-plus 的 Table 组件的属性，通过组件的属性透传来达到效果。
- `columns` 表格配置项，具体用法查看上面的 ProTable 文档。
```vue
<template>
  <div class="table-box">
    <ProTable
      rowKey="pkId"
      ref="proTable"
      :columns="columns"
      :requestApi="getTableList"
      :dataCallback="dataCallback"
      :cell-style="{ padding: '0px' }"
    >
    </ProTable>
  </div>
</template>
<script setup lang="tsx">
import { ref, reactive } from 'vue'
import { ColumnProps } from '@/components/ProTable/interface'
import ProTable from '@/components/ProTable/index.vue'
import { View } from '@element-plus/icons-vue'
// ...省略import
// 获取 ProTable 元素，调用其暴露的方法（如获取刷新数据方法，还能获取到当前查询参数）
const proTable = ref()

// dataCallback 是对于返回的表格数据做处理，如果你后台返回的数据不是 list && total 这些字段，那么你可以在这里进行处理成这些字段
const dataCallback = (data: any) => {
  return {
    list: data.list,
    total: data.total
  }
}
// 若无需修改 params 就直接在 ProTable 组件上绑定	:requestApi="getUserList"
const getTableList = (params: any) => {
  return UserApi.page(params)
}

// 表格配置项
const columns: ColumnProps<UserType>[] = [
  { type: 'selection', fixed: 'left', width: 60 },
  {
    prop: 'avatar',
    label: '头像',
    width: 70,
    // 自定义单元格内容渲染（tsx语法）
    render: (scope) => {
      return (
        <div class={['flex', 'justify-center', 'p-1']}>
          <el-avatar shape={'square'} size={30} src={scope.row.avatar} />
        </div>
      )
    }
  },
  { prop: 'account', label: '账号' },
  {
    prop: 'gender',
    label: '性别',
    sortable: true,
    enum: [
      {
        genderLabel: '男',
        genderValue: 0
      },
      {
        genderLabel: '女',
        genderValue: 1
      }
    ],
    search: { el: 'select', props: { filterable: true } },
    fieldNames: { label: 'genderLabel', value: 'genderValue' },
    render: (scope) => {
      let type = scope.row.gender === 0 ? 'success' : 'warning'
      return <el-tag type={type}>{scope.row.gender === 0 ? '男' : '女'}</el-tag>
    }
  },
  {
    prop: 'date',
    label: '日期',
    search: {
      el: 'date-picker',
      props: { type: 'datetime', format: 'YYYY-MM-DD', valueFormat: 'YYYY-MM-DD', placeholder: '请选择日期' }
    }
  },
  { prop: 'operation', label: '操作', fixed: 'right', width: 340 }
]
</script>
```

![](https://cdn.jsdelivr.net/gh/wardendon/wiki-image@main/img/image-20240301001419605.png#id=EFfIr&originHeight=601&originWidth=1260&originalType=binary&ratio=1&rotation=0&showTitle=false&status=done&style=none&title=)

得到一个基本的表单页面后，接下来添加一些操作按钮，即添加插槽。
```vue
<template>
  <div class="table-box">
    <ProTable>
      <!-- 表格 header 按钮 -->
      <template #tableHeader>
        <el-button type="success" :icon="CirclePlus" plain @click="openDrawer('新增')">新增</el-button>
        <el-button type="primary" :icon="Download" plain @click="downloadFile">导出</el-button>
      </template>
      <!-- 表格操作 -->
      <template #operation="scope">
        <el-button type="primary" link :icon="View" @click="openDrawer('编辑', scope.row)">编辑</el-button>
      </template>
    </ProTable>
  </div>
</template>
<script setup lang="tsx">
import { Download, View, CirclePlus } from '@element-plus/icons-vue'
// 打开 drawer(新增、查看、编辑)
const openDrawer = (title: string, row: Partial<UserType> = {}) => {
  //...关于打开抽屉或者模态框，仿照管理员管理写就行了，或者根据geeker-admin源码进行仿写。
}
// 导出列表
const downloadFile = async () => {
  //...
}
</script>
```
