<template>
  <div class="layui-container fly-marginTop">
    <div class="fly-panel fly-panel-user" pad20>
      <div class="layui-tab layui-tab-brief" lay-filter="user">
        <ul class="layui-tab-title">
          <li>
            <router-link :to="{name: 'login'}">登入</router-link>
          </li>
          <li class="layui-this">
            找回密码
            <!--重置密码-->
          </li>
        </ul>
        <div class="layui-form layui-tab-content" id="LAY_ucm" style="padding: 20px 0;">
          <div class="layui-tab-item layui-show">
            <div class="layui-form layui-form-pane">
              <form method="post">
                <div class="layui-form-item">
                  <label for="L_email" class="layui-form-label">用户名</label>
                  <validation-provider rules="required|min:6" name="username" v-slot="{ errors }">
                  <div class="layui-input-inline">
                    <input
                      type="username"
                      name="username"
                      v-model="username"
                      placeholder="请输入邮箱"
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
                  <validation-provider rules="required|length:4" name="code" v-slot="{ errors }">
                  <div class="layui-row">
                  <label for="L_vercode" class="layui-form-label">验证码</label>
                  <div class="layui-input-inline">
                    <input
                      type="text"
                      name="code"
                      v-model="code"
                      placeholder="请输入验证码"
                      autocomplete="off"
                      class="layui-input"
                    />
                  </div>
                    <div class>
                      <span class="svg" style="color: #c00;" @click="_getCode()" v-html="svg"></span>
                    </div>
                  </div>
                  <div class="layui-form-mid">
                    <span style="color: #c00;">{{errors[0]}}</span>
                  </div>
                  </validation-provider>
                </div>
                <div class="layui-form-item">
                  <button type="button" class="layui-btn"  @click="submit()">提交</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ValidationProvider } from 'vee-validate'
import { getCode, forget } from '@/api/login'
export default {
  name: 'forget',
  components: {
    ValidationProvider
  },
  data () {
    return {
      username: '',
      svg: '',
      code: ''
    }
  },
  mounted () {
    this._getCode()
  },
  methods: {
    // 接受后端图形验证码内容
    _getCode () {
      let sid = this.$store.state.sid
      getCode(sid).then((res) => {
        if (res.code === 200) {
          this.svg = res.data
        }
      })
    },
    submit () {
      forget({
        username: this.username,
        code: this.code
      }).then((res) => {
        if (res.code === 200) {
          this.$alert('注册码发送成功，请查收邮件！')
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>
