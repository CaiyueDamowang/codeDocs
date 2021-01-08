<template>
  <transition name="fade">
    <div class="layui-layer-page
     layui-layer-prompt edit-content" v-show="isShow">
     <div class="layui-layer-title">请贴入代码块或任意文本</div>
     <div class="layui-layer-content">
       <textarea
        id="inputItem"
        type="text"
        class="layui-layer-input"
        v-on:keydown.enter="$event.stopPropagation()"
        :style="{'width': this.width + 'px', 'height': this.height + 'px'}"
        v-model="codes">
       </textarea>
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
  name: 'Code',
  props: ['isShow', 'width', 'height'],
  data () {
    return {
      codes: ''
    }
  },
  methods: {
    submit () {
      if (this.codes === '') {
        document.getElementById('inputItem').focus()
        this.$pop('shake', '请输入代码内容')
        return
      }
      this.$emit('addEvent', this.codes)
      setTimeout(() => {
        this.codes = ''
        this.$emit('closeEvent')
      }, 0)
    },
    cancel () {
      this.$emit('closeEvent')
      this.codes = ''
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
