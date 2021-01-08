<template>
  <div class="layui-container flex">
    <img src="../assets/404.png" width="500px" alt="">
    <p>找不到页面了QAQ</p>
    <router-link class="layui-btn" to="/">回到首页</router-link>
  </div>
</template>

<script>
export default {
  name: '404'
}
</script>

<style lang="scss" scoped>
.flex {
  width: 100%;
  min-height: 400px;
  display: flex;
  flex-flow: column nowrap;
  justify-content: center;
  align-items: center;
}

p {
  padding-bottom: 15px;
  padding-top: 10px;
  color: #ddd;
}
</style>
