<template>
  <div v-show="isShow">
    <div class="alert">
      <div class="flex">{{msg}}</div>
      <div v-if="type === 'alert'">
        <div class="btn success" @click="close()">确定</div>
      </div>
      <div v-else class="space-round">
        <div class="btn cancel" @click="cancelEvent()">取消</div>
        <div class="btn success" @click="successEvent()">确定</div>
      </div>
    </div>
    <div class="mask" @click="closeMask()"></div>
  </div>
</template>

<script>
/* eslint-disable camelcase */
/* eslint-enable camelcase */
export default {
  props: {
    type: {
      type: String,
      default: 'alert'
    },
    msg: {
      type: String,
      default: ''
    },
    isShow: {
      type: Boolean,
      default: false
    },
    success: {
      type: Function,
      default: () => { console.log('点击了success') }
    },
    cancel: {
      type: Function,
      default: () => { console.log('点击了cancel') }
    }
  },
  methods: {
    close () {
      this.isShow = false
    },
    closeMask () {
      if (this.type === 'alert') {
        this.close()
      }
    },
    cancelEvent () {
      this.cancel()
      this.close()
    },
    successEvent () {
      this.success()
      this.close()
    }
  }
}
</script>

<style lang="scss" scoped>
$btn-main: #009688;
$btn-dark: darken($btn-main,6%);
$btn-cancel:#EDEDED;
$btn-deep: darken($btn-cancel,6%);

.alert {
  width: 300px;
  height: 150px;
  position: fixed;
  background: #fff;
  border-radius: 6px;
  left: 50%;
  top: 50%;
  margin-left: -150px;
  margin-top: -75px;
  padding: 20px 10px;
  box-shadow: 0 5px 8px rgba(0, 0, 0, 0.05);
  z-index: 9999;
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
  align-items: center;
}

.flex {
  flex:1;
  justify-content: center;
  align-items: center;
  display: flex;
}

.space-round {
  display: flex;
  flex-flow: row nowrap;
  justify-content: space-between;
  align-items: center;
  widows: 100%;
  padding: 0 10px;
}

.btn {
  width: 105px;
  height: 32;
  text-align: center;
  line-height: 32px;
  border-radius: 6px;
  &.success {
    background: $btn-main;
    color: #fff;
    &:hover {
      background: $btn-dark;
    }
  }
  &.cancel {
    margin-right: 30px;
    background: $btn-cancel;
    color: #333;
    &:hover {
      background: $btn-deep;
    }
  }
}

.mask {
  position: fixed;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.4);
  left: 0;
  top: 0;
  overflow: hidden;
  z-index: 2000;
}
</style>
