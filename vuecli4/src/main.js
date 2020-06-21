import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import { postKeyValueRequest } from "./utils/api";
import { postRequest } from "./utils/api";
import { getRequest } from "./utils/api";
import { deleteRequest } from "./utils/api";
import { putRequest } from "./utils/api";


// VUE 插件添加全局功能
// 调用的时候直接 this.postRequest 就好了，不用每个文件都 import

// 这是其中一种(Vue.prototype)体现插件功能【添加实例方法到插件】的方式，其他功能的插件还有：
// 1. 【添加全局方法或者property】：如，vue-custom-element
// 2. 【添加全局资源】：指令/过滤器/过度等。如，vue-touch
// 3. 【全局混入添加组件选项】：如，vue-router
// 4. 【提供自己API及以上功能的一个库】：如，vue-router
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
