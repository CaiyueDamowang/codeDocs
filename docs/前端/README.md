## 前端

### 2020/09/28

1. 小程序应用结构设计，每个页面都有一个共同的`frame`，页面具体详情在frame中编写`views`

固定的UI框架和具体页面的业务解耦，当提供了固定的UI，后续的页面开发只需要在当前view组件中专注逻辑即可

- 包含navbar组件 ✅
- 包含toolbar ✅
  - 自定义动态组件样式
- 包含页面视图组件 ✅
  - 自定义组件会继承外部html结构的font color属性
  - 组件样式文件有作用域，页面样式不能应用在组件样式内
> 关于自定义组件，会有一些样式的坑 [具体查看](https://developers.weixin.qq.com/miniprogram/dev/framework/runtime/env.html)

> 组件构造函数文档[自定义组件文档](https://developers.weixin.qq.com/miniprogram/dev/reference/api/Component.html)
- 组件采用slot - name 形式插槽嵌套 ✅
  - 主要包含 modal(弹窗) views(页面组件)
- 编写公共UI组件 ✅

- 是否采用npm构建 ❌

----

2. 小程序初期设计时就应该考虑到性能问题

小程序普遍都存在交互卡顿，页面加载慢的问题，初始化缓慢，资源加载速度慢的问题

[小程序初始渲染缓存工作原理](https://developers.weixin.qq.com/miniprogram/dev/framework/view/initial-rendering-cache.html)

优化方案：
 - 骨架屏或者各个页面采用骨架的思路优化体验
 - 资源加载优化： 压缩， 预请求..
 - 性能优化： 懒加载 节流 不阻塞 懒加载
 - 数据缓存： 缓存到本地

-----

 3. 小程序组件化开发

  3.1 程序为什么要组件化？

- 组件更容易被复用: 前端流行的组件化，是为了部分的逻辑能够更好地被重用。（do not repeat 原则)

- 组件更专注局部的逻辑: 组件能够封装内部逻辑，向外提供友好的接口，将逻辑内聚在组件内，外部组件不需要关注内部的具体实现。

- 组件更容易被组合: 相比于面向对象的思想，组件能够像积木一样，不用继承不需要的属性，也可以组合其他组件的属性。（类思想里利用寄生式继承达到目的）
 
 3.2 小程序如何实现组件化

- 组件方案采用 `iframe` + `页面级组件`
    iframe是指在多个页面抽离公共的样式和逻辑，多个页面共用这个骨架

    页面级组件是每个页面的具体实现封装在组件中，嵌套在骨架中

- 组件通信 ❌
    暂时无理想方案

- 全局状态管理 ❌
    暂时无理想方案

3.3 微信小程序组件文档
[Component构造API]（https://developers.weixin.qq.com/miniprogram/dev/reference/api/Component.html）
[自定义组件文档](https://developers.weixin.qq.com/miniprogram/dev/framework/custom-component/component.html)

### 2020/09/27

原型图、配色方案
