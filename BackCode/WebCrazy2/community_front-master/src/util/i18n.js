import Vue from 'vue'
import VueI18n from 'vue-i18n'
import zh from 'vee-validate/dist/locale/zh_CN.json'

Vue.use(VueI18n)

const i18n = new VueI18n({
  locale: 'zh_CN',
  messages: {
    'zh_CN': {
      fields: {
        oldpassword: '旧密码',
        email: '邮箱',
        password: '密码',
        repass: '密码',
        name: '账号',
        username: '账号',
        code: '验证码',
        vercode: '验证码',
        nickname: '昵称',
        title: '标题',
        catalog: '分类'
      },
      validation: {
        ...zh.messages,
        required: '请输入{_field_}',
        email: '请输入正确的{_field_}!!!',
        is: '两次输入{_field_}不一致!!!',
        is_not: '请选择{_field_}'
      }
    }
  }
})

export { i18n }
