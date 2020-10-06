## 前端

### 小程序开发文档

[小程序框架概览](https://developers.weixin.qq.com/miniprogram/dev/reference/api/App.html)

#### 1. 小程序应用结构设计，每个页面都有一个共同的`frame`，页面具体详情在frame中编写`views`

固定的UI框架和具体页面的业务解耦，当提供了固定的UI，后续的页面开发只需要在当前view组件中专注逻辑即可

- 包含navbar组件 ✅
- 包含toolbar ✅
  - 自定义动态组件样式
- 包含页面视图组件 ✅
  - 自定义组件会继承外部html结构的font color属性
  - 组件样式文件有作用域，页面样式不能应用在组件样式内

- 组件采用slot - name 形式插槽嵌套 ✅
  - 主要包含 modal(弹窗) views(页面组件) loading (加载组件)

- 编写公共UI组件 ✅

- 是否采用npm构建 ❌

> 关于自定义组件，会有一些样式的坑 [具体查看](https://developers.weixin.qq.com/miniprogram/dev/framework/runtime/env.html)

> 组件构造函数文档[自定义组件文档](https://developers.weixin.qq.com/miniprogram/dev/reference/api/Component.html)

----

#### 2. 小程序初期设计时就应该考虑到性能问题

小程序普遍都存在交互卡顿，页面加载慢的问题，初始化缓慢，资源加载速度慢的问题

[小程序初始渲染缓存工作原理](https://developers.weixin.qq.com/miniprogram/dev/framework/view/initial-rendering-cache.html)

优化方案：
 - 骨架屏或者各个页面采用骨架的思路优化体验
 - 资源加载优化： 压缩， 预请求..
 - 性能优化： 懒加载 节流 不阻塞 懒加载 静态结点细粒度 分包预加载
 - 数据缓存： 缓存到本地
 - 性能检测： `this.setUpdatePerformanceListener`

-----

#### 3. 小程序组件化开发

#####  3.1 小程序为什么要组件化？

###### - 组件更容易被复用: 前端流行的组件化，是为了部分的逻辑能够更好地被重用。（do not repeat 原则)

###### - 组件更专注局部的逻辑: 组件能够封装内部逻辑，向外提供友好的接口，将逻辑内聚在组件内，外部组件不需要关注内部的具体实现。

###### - 组件更容易被组合: 相比于面向对象的思想，组件能够像积木一样，不用继承不需要的属性，也可以组合其他组件的属性。（类思想里利用寄生式继承达到目的）

#####  - 在小程序中，每次新页面的加载都会导致复用的组件重载，项目采用单页面的思路，减少重载次数和时间
 
> 在小程序里面用组件化的模式，方便了全局状态管理和减少页面重载的时间

> 但是也带来了不好维护路由和历史页面的问题

##### 3.2 小程序如何实现组件化

###### - 组件方案采用 `frame` + `pageComponet`。iframe是指在多个页面抽离公共的样式和逻辑，多个页面共用这个骨架，pageComponent(页面级组件)是每个页面的具体实现封装在组件中，嵌套在骨架中

###### - 组件通信 ❌ 

暂时无理想方案

###### - 全局状态管理 ❌ 

待选方案
1. mapStateToProps 和 mapActionToProps 的方案

2. 组件管理

###### 3.3 微信小程序组件文档
[Component构造API](https://developers.weixin.qq.com/miniprogram/dev/reference/api/Component.html)
[自定义组件文档](https://developers.weixin.qq.com/miniprogram/dev/framework/custom-component/component.html)

要注意组件的生命周期和页面的生命周期:
组件的生命周期 `onload` 在 组件的 `attached` 之后，也就是可以在 组件的这个周期安全的初始化状态

#### 4. 小程序开发配置

右上详情 -> 开启选项

- ES6 -> ES5 ✅
- 增强编译 ✅  (支持async/await)（支持跨文件共享辅助函数）
- 上传代码样式自动补全 ✅
- 上传代码压缩混淆 ✅
- 自动运行体验评分 ✅
- 不校验域名 ✅
- 使用新的编译模块 ✅
- 以shadow-root形式展示自定义组件 （可选）
- 启用多核心编译 （可选）
- 启用自定义处理命令 ❌

#### 5. 小程序目录结构

```
|____template             模板
|____components           公共组件
| |____navbar               顶部菜单
| |
| |____frame                单页骨架
| |
| |____window               导航栏
|
|____views                具体页面组件
|
|____static               静态资源
| |____images
|
|____pages                小程序单页应用入口文件
| |____index
|   |____index.wxml
|   |____index.js
|   |____index.wxss
|   |____index.json
|
|____utils                工具函数
| |____util.js
|
|____app.js               小程序app启动文件
|____app.wxss
|____app.json
|____project.config.json
|____sitemap.json
```

#### 6. 采用CSS3的flex布局
- 详见flex.html
##### 6.1 What

- Flex 是"弹性布局"，用来为盒状模型提供最大的灵活性。任何一个容器都可以指定为 Flex 布局。
- 容器默认存在两根轴：水平的主轴（main axis）和垂直的交叉轴（cross axis）。

```
.box{
  display: flex;
}
```
注意，设为 Flex 布局以后，子元素的float、clear和vertical-align属性将失效。

##### 6.2 box的属性
- 一共6个属性：
  ·flex-direction
  ·flex-wrap
  ·flex-flow
  ·justify-content
  ·align-items
  ·align-content

##### 6.3 item的属性
- 一共6个属性：
  ·order
  ·flex-grow
  ·flex-shrink
  ·flex-basis
  ·flex
  ·align-self


