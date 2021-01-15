import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from "./store";
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue';

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import '@/assets/css/main.css'

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
