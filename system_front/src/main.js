import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'
import * as Icons from '@element-plus/icons-vue'
import vuex from '@vue/cli-plugin-vuex'
import { createPinia } from 'pinia'

const app = createApp(App)
const pinia = createPinia()
for (let i in Icons) {
    app.component(i, Icons[i])
}

app.use(router).use(ElementPlus).use(pinia).use(vuex).mount('#app')
