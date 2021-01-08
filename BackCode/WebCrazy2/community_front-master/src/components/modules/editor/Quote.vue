<template>
  <transition name="fade">
    <div class="layui-layer-page
     layui-layer-prompt edit-content" v-show="isShow">
     <div class="layui-layer-title">请输入引用</div>
     <div class="layui-layer-content">
       <input type="text" class="layui-layer-input" id="inputItem" v-model="quote">
     </div>
     <span class="layui-layer-setwin" @click="cancel()">
        <a href="javascript:void(0)" class="layui-layer-ico
        layui-layer-close layui-layer-close1"></a>
     </span>
     <div class="layui-layer-btn">
       <a class="layui-layer-btn0" @click.prevent="submit()">确定</a>
       <a class="layui-layer-btn" @click.prevent="cancel()">取消</a>
     </div>
    </div>
  </transition>
</template>

<script>
export default {
  name: 'quote',
  props: ['isShow'],
  data () {
    return {
      quote: ''
    }
  },
  methods: {
    submit () {
      if (this.quote === '') {
        document.getElementById('inputItem').focus()
        this.$pop('shake', '请输入引用内容')
        return
      }
      this.$emit('addEvent', this.quote)
      setTimeout(() => {
        this.quote = ''
        this.$emit('closeEvent')
      }, 0)
    },
    cancel () {
      this.$emit('closeEvent')
      this.quote = ''
    },
    handleBodyClick (e) {
      e.stopPropagation()
      if (typeof this.ctrl === 'undefined') { return }
      // 触发隐藏本组件的关闭事件,改变isShow
      // 判断是否点击到了非控制ICON以外+本组件的地方,没有点击控制ICON才触发关闭事件
      if (!(this.ctrl.contains(e.target) ||
      this.$refs.wrapper.contains(e.target))) {
        this.cancel()
      }
    }
  }
}
</script>

<style lang="scss" scoped>

</style>
