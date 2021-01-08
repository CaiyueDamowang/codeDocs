<template>
<div class="layui-container">
   <div class="layui-row layui-col-space15">
     <div class="layui-col-md8">
       <list></list>
     </div>
     <div class="layui-col-md4">
       <hot-list></hot-list>
       <ads></ads>
       <links></links>
     </div>
   </div>
 </div>
</template>

<script>
import List from '@/components/contents/List'
import HotList from '@/components/sidebar/HotList'
import Links from '@/components/sidebar/Links'
import Ads from '@/components/sidebar/Ads'
export default {
  name: 'template1',
  components: {
    List,
    HotList,
    Links,
    Ads
  }
}
</script>

<style lang="stylus" scoped>

</style>
