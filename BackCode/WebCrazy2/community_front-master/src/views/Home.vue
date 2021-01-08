<template>
  <div>
    <panel></panel>
    <router-view></router-view>
  </div>
</template>

<script>
import Panel from '@/components/Panel'

export default {
  name: 'home',
  components: {
    Panel
  }

}
</script>

<style lang="scss" scoped>
</style>
