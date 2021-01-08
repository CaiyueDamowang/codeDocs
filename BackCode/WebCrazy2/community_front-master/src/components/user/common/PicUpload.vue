<template>
<!-- 上传头像 -->
          <div class="layui-form layui-form-pane layui-tab-item layui-show">
            <div class="layui-form-item">
              <div class="avatar-add">
                <p>建议尺寸168*168，支持jpg、png、gif，最大不能超过50KB</p>
                <label for="pic" class="layui-btn upload-img">
                  <i class="layui-icon">&#xe67c;</i>上传头像
                </label>
                <input id="pic" type="file" name="file"
                  accept="image/png,image/gif,image/jpg" @change="upload">
                <img :src="pic">
                <span class="loading"></span>
              </div>
            </div>
          </div>
</template>

<script>
import { uploadImg } from '@/api/content'
import config from '@/config'
import { updateUserInfo } from '@/api/user'
export default {
  name: 'picupload',
  data () {
    return {
      // 判断 userInfo & pic 是否存在
      pic: (this.$store.state.userInfo && this.$store.state.userInfo.pic)
        ? this.$store.state.userInfo.pic : '/img/cat.jpg',
      formData: ''
    }
  },
  methods: {
    // 获取准备上传的图片数据
    upload (e) {
      console.log(e)
      let file = e.target.files
      // FormData对象可以将form表单元素的name与value进行组合
      let formData = new FormData()
      if (file.length > 0) {
        formData.append('file', file[0])
        this.formData = formData
      }
      // 上传图片之后
      uploadImg(this.formData).then((res) => {
        console.log(res)
        if (res.code === 200) {
          const baseUrl = process.env.NODE_ENV === 'production'
            ? config.baseUrl.pro
            : config.baseUrl.dev
          this.pic = baseUrl + res.data
          // 更新用户基本资料
          updateUserInfo({ pic: this.pic }).then((res) => {
            if (res.code === 200) {
            // 修改全局的store内的用户基础信息
              let user = this.$store.state.userInfo
              user.pic = this.pic
              this.$store.commit('setUserInfo', user)
              this.$alert('图片上传成功')
            }
          })
          // 更新完成后将信息清空
          document.getElementById('pic').value = ''
        }
      })
    }
  }
}
</script>

<style lang="scss" scoped>
#pic {
  display: none
}
</style>
