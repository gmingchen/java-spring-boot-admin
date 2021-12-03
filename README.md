<p align="center">
  <a href="https://spring.io/projects/spring-boot">
    <img src="https://img.shields.io/badge/springboot-2.5.1-brightgreen.svg" alt="springboot">
  </a>
  <a href="https://www.mysql.com/">
    <img src="https://img.shields.io/badge/mysql-8.0.23-brightgreen.svg" alt="mysql">
  </a>
  <a href="https://mp.baomidou.com/">
    <img src="https://img.shields.io/badge/mybatis--plus-3.4.2-brightgreen.svg" alt="mysql">
  </a>
  <a href="https://shiro.apache.org/">
    <img src="https://img.shields.io/badge/shiro-1.7.1-brightgreen.svg" alt="shiro">
  </a>
</p>

[java-admin-base](https://github.com/gmingchen/java-admin-base) 是 [vue3-element-plus-admin](https://github.com/gmingchen/vue3-element-plus-admin) 中 [base-refactoring](https://github.com/gmingchen/vue3-element-plus-admin/tree/base-refactoring) 分支的基于 __`java`__ 的 __`springboot`__的基础功能后端代码,目前完整版后端代码 __`暂未开源`__。后期完善之后可能 __`会开源完整后端代码`__。

若该项目有帮助到你可以翻到底部 __`添加作者微信/QQ`__，如果使用中遇到什么问题可以在交流群中说明你遇到的问题，大佬们都会积极的给你解决。

`若Github项目下载代码过慢或失败，可以翻到底部，关注公众号获取基础版` [base-refactoring](https://github.com/gmingchen/vue3-element-plus-admin/tree/base-refactoring) 分支的前后端代码

### 简介

[vue3-element-plus-admin](https://github.com/gmingchen/vue3-element-plus-admin) 是一个管理后台基础功能框架，基于 [vue3](https://github.com/vuejs/vue-next) 、 [element-plus](https://github.com/element-plus/element-plus) 和 [typescript](https://github.com/microsoft/TypeScript) 实现。内置了 i18n 国际化，动态路由，权限验证。

**__`注：`__ 由于国内项目使用国际化功能的比较少，所以主分支没有添加国际化功能，如有需要可以参考 [composition-api](https://github.com/1240235512/vue3/tree/composition-api) 分支。**

- [开发文档](https://gmingchen.github.io/vue3-element-plus-admin-doc/)
- [在线预览(暂时不可用)](https://gmingchen.github.io/vue3-element-plus-admin/index.html)
- [开发文档(备用-服务器比较low，访问有点慢！)](http://frame.gumingchen.icu/docs/)
- [在线预览(备用-服务器比较low，访问有点慢！)](http://frame.gumingchen.icu)

**默认 master 分支默认使用 composition-api ，若需要 class 风格 + Typescript 请使用[class-style](https://github.com/1240235512/vue3/tree/class-style)分支，若需要 composition-api + Typescript + I18n 请使用[composition-api](https://github.com/1240235512/vue3/tree/composition-api)分支，若只需要 基础版本 请使用[base-refactoring](https://github.com/1240235512/vue3/tree/base-refactoring)分支，但它们不会和 master 保持同步更新**

### 分支
- [master](https://github.com/gmingchen/vue3-element-plus-admin/tree/master) 轻量版本：持续维护分支
- [base-refactoring](https://github.com/gmingchen/vue3-element-plus-admin/tree/base-refactoring) 精简版本：基于 RBAC 的权限功能-仅包含 `菜单管理`、`角色管理`、`用户管理` 三个模块
- [composition-api](https://github.com/1240235512/vue3/tree/composition-api) 内置__`Type Script`__、__`I18n`__
- [class-style](https://github.com/1240235512/vue3/tree/class-style) 使用 class 风格开发，内置__`Type Script`__、__`I18n`__
- __`说明:`__ 其他分支是作者开发时使用的分支, 由于 [element-plus](https://github.com/element-plus/element-plus) 版本迭代的原因，项目中间重构过，布局也同步改为 `flex` 布局。 所以 [composition-api](https://github.com/1240235512/vue3/tree/composition-api) 和 [class-style](https://github.com/1240235512/vue3/tree/class-style) 版本比较旧一点，建议这两个分支作为参考学习。 [master](https://github.com/gmingchen/vue3-element-plus-admin/tree/master) 和 [base-refactoring](https://github.com/gmingchen/vue3-element-plus-admin/tree/base-refactoring) 这两个分支是比较新的，可以放心使用。

### 功能模块

- [X] 角色管理
- [X] 账户管理
- [X] 操作日志
- [X] 菜单管理

### 项目结构

```bash
vue3-src
├─slipper-backstage Controller模块
│
├─slipper-common 通用模块
│
├─slipper-core 核心模块
│
├─slipper-service 服务层模块
│
└─slipper-shiro shiro权限控制模块
```

### 广告

![领红包](http://oss.gumingchen.icu/image/red-envelopes.jpg)

### 联系方式

<table>
  <tr align="center">
    <td>公众号</td>
    <td>QQ交流群</td>
    <td>微信交流群</td>
    <td>微信</td>
    <td>QQ</td>
  </tr>
  <tr>
    <td>
      <img src="http://oss.gumingchen.icu/image/official-account-qr-code.jpg" width="200px" title="公众号" alt="公众号:loafer-man" />
    </td>
    <td>
      <img src="http://oss.gumingchen.icu/image/qq-group-qr-code.jpg" width="200px" title="QQ交流群" alt="QQ交流群:124371554" />
    </td>
    <td>
      <img src="http://oss.gumingchen.icu/image/wechat-group-qr-code.jpg" width="200px" title="微信交流群" alt="微信交流群:124371554" />
    </td>
    <td>
      <img src="http://oss.gumingchen.icu/image/wechat-qr-code-1.jpg" width="200px" title="微信" alt="微信:Gy1240235512" />
    </td>
    <td>
      <img src="http://oss.gumingchen.icu/image/qq-qr-code.jpg" width="200px" title="QQ" alt="QQ:1240235512" />
    </td>
  </tr>
</table>

### 其它开源项目

[vue3-element-plus-admin](https://github.com/gmingchen/vue3-element-plus-admin)

是一个管理后台基础功能框架，基于 [vue3](https://github.com/vuejs/vue-next) 、 [element-plus](https://github.com/element-plus/element-plus) 和 [typescript](https://github.com/microsoft/TypeScript) 实现。内置了 i18n 国际化，动态路由，权限验证。-[私活神器]

[im-vue](https://github.com/gmingchen/im-vue)

是一个即时聊天系统，基于 [vue3](https://github.com/vuejs/vue-next) 、 [element-plus](https://github.com/element-plus/element-plus) 实现。内置了好友私聊功能。



