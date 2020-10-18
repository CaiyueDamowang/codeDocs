//vue - esmodule
export default {
  name: 'Coponent',
  data() {
    return {

    }
  }
}
export const a = 1

module.exports = {
    themeConfig: {
      // 添加导航栏
      nav: [
        { text: '首页', link: '/' },
        { text: '前端', link: '/前端/' },
        { text: '后端', link: '/后端/' },
        {
          text: 'github',
          // 这里是下拉列表展现形式。
          items: [
            { text: 'focus-outside', link: 'https://github.com/TaoXuSheng/focus-outside' },
            { text: 'stylus-converter', link: 'https://github.com/TaoXuSheng/stylus-converter' },
          ]
        }
      ],
      // 为以下路由添加侧边栏
      sidebar: ['/', '/git', '/vue']
    }
  }


