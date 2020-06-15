import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// VUE 插件 - 方便调用
// 调用的时候直接 this.postKeyValueRequest 就好了，不用每个文件都 import
import { postKeyValueRequest } from "./utils/api";
import { postRequest } from "./utils/api";
import { getRequest } from "./utils/api";
import { deleteRequest } from "./utils/api";
import { putRequest } from "./utils/api";

Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.postRequest = postRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.putRequest = putRequest;

Vue.config.productionTip = false
Vue.use(ElementUI);

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
