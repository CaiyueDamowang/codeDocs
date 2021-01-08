<template>
  <div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li>
            <router-link :to="{name: 'login'}">登入</router-link>
          </li>
          <li class="layui-this">注册</li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <validation-observer ref="observer" v-slot="{ validate }">
            <div class="layui-tab-item layui-show">
            <div class="layui-form layui-form-pane">
              <form method="post">
                <div class="layui-form-item">
                  <label for="L_email" class="layui-form-label">邮箱</label>
                  <validation-provider rules="required|email" name="username"  v-slot="{ errors }">
                  <div class="layui-input-inline">
                    <input
                      type="text"
                      name="username"
                      v-model="username"
                      placeholder="请输入邮箱"
                      autocomplete="off"
                      class="layui-input"
                    />
                  </div>
                  <div class="layui-form-mid layui-word-aux">将会成为您唯一的登入名</div>
                  <div class="layui-form-mid">
                    <span style="color: #c00;">{{errors[0]}}</span>
                  </div>
                  </validation-provider>
                </div>
                <div class="layui-form-item">
                  <label for="L_pass" class="layui-form-label">密码</label>
                  <validation-provider rules="required|min:6" name="password" v-slot="{ errors }">
                  <div class="layui-input-inline">
                    <input
                      type="password"
                      name="password"
                      v-model="password"
                      placeholder="请输入密码"
                      autocomplete="off"
                      class="layui-input"
                    />
                  </div>
                  <div class="layui-form-mid layui-word-aux">6到16位字符</div>
                  <div class="layui-form-mid">
                    <span style="color: #c00;">{{errors[0]}}</span>
                  </div>
                  </validation-provider>
                </div>
                <div class="layui-form-item">
                  <label for="L_repass" class="layui-form-label">确认密码</label>
                  <validation-provider :rules="{required: true,is: password}" name="repass" v-slot="{ errors }">
                  <div class="layui-input-inline">
                    <input
                      type="password"
                      name="repass"
                      v-model="repass"
                      placeholder="请确认密码"
                      autocomplete="off"
                      class="layui-input"
                    />
                  </div>
                  <div class="layui-form-mid">
                    <span style="color: #c00;">{{errors[0]}}</span>
                  </div>
                  </validation-provider>
                </div>
                <div class="layui-form-item">
                  <validation-provider rules="required|length:4" name="vercode" v-slot="{ errors }">
                  <div class="layui-row">
                  <label for="L_vercode" class="layui-form-label">验证码</label>
                  <div class="layui-input-inline">
                    <input
                      type="text"
                      name="vercode"
                      v-model="vercode"
                      placeholder="请输入验证码"
                      autocomplete="off"
                      class="layui-input"
                    />
                  </div>
                    <div>
                      <span class="svg" style="color: #c00;" @click="_getCode()" v-html="svg1"></span>
                    </div>
                  </div>
                    <div class="layui-form-mid">
                      <span style="color: #c00;">{{errors[0]}}</span>
                    </div>
                  </validation-provider>
                  </div>
                <div class="layui-form-item">
                  <button class="layui-btn" type="button" @click="validate().then
                  (submit)">立即注册</button>
                </div>
                <div class="layui-form-item fly-form-app">
                  <span>或者直接使用社交账号快捷注册</span>
                  <a
                    href
                    onclick="layer.msg('正在通过QQ登入', {icon:16, shade: 0.1, time:0})"
                    class="iconfont icon-qq"
                    title="QQ登入"
                  ></a>
                </div>
              </form>
            </div>
          </div>
          </validation-observer>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ValidationProvider, ValidationObserver } from 'vee-validate'
import { getCode, reg } from '@/api/login'
export default {
  name: 'reg',
  components: {
    ValidationProvider,
    ValidationObserver
  },
  data () {
    return {
      email: '',
      passWord: '',
      repass: '',
      vercode: '',
      svg1: ''
    }
  },
  mounted () {
    this._getCode()
  },
  methods: {
    // 接受后端图形验证码内容
    // res.data 应该是一个html标签
    _getCode () {
      let sid = this.$store.state.sid
      getCode(sid).then((res) => {
        if (res.code === 200) {
          this.svg1 = res.data
        }
      })
    },
    async submit () {
      const isValid = await this.$refs.observer.validate()
      if (!isValid) {
        return
      }
      reg({
        username: this.username,
        password: this.password,
        nickname: this.nickname,
        vercode: this.vercode,
        sid: this.$store.state.sid
      }).then((res) => {
        if (res.code === 200) {
          this.username = ''
          this.password = ''
          this.repass = ''
          this.nickname = ''
          this.vercode = ''
          requestAnimationFrame(() => {
            this.$refs.observer.reset()
          })
          // 跳转到登录界面，让用户登录
          this.$alert('注册成功')
          setTimeout(() => {
            this.$router.push('/login')
          }, 1000)
        } else {
          // 返回用户名已经注册
          this.$refs.observer.setErrors(res.msg)
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
