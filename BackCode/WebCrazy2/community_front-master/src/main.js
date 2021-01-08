import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import filters from '@/util/filter'
import directives from '@/util/directives'

import '@/util/veevalidate-i18n'
import Alert from './components/modules/alert/index'
import Pop from './components/modules/pop/index'

Vue.use(Alert)
Vue.use(Pop)

Object.keys(filters).forEach((key) => {
  Vue.filter(key, filters[key])
})

Object.keys(directives).forEach((key) => {
  Vue.directive(key, directives[key])
})

Vue.config.productionTip = false

axios.defaults.baseURL =
  process.env.NODE_ENV !== 'production'
    ? 'http://localhost:3000'
    : 'http://your.domain.com'

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
