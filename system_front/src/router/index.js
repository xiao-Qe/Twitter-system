import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import User from '../views/User'
import Administrator from '../views/Administrator'
import Twitter from '../views/show/Twitter'


const routes = [
  {
    path: '/',
    name: 'home',
    component: Login
  },
  {
    path: '/user',
    name: 'user',
    component: User,
    redirect: '/user/recommend',
    children:[
      //  推荐
      {
        path: 'recommend',
        component: () => import('../views/Recommend.vue'),
        prop:true
      },
      //  分类展示
      {
        path: 'athletics',
        component: () => import('../views/classify/ClassifiedDisplay')
      },
      {
        path: 'amusement',
        component: () => import('../views/classify/ClassifiedDisplay')
      },
      {
        path: 'study',
        component: () => import('../views/classify/ClassifiedDisplay')
      },
      {
        path: 'technology',
        component: () => import('../views/classify/ClassifiedDisplay')
      },
      {
        path: 'currentEvents',
        component: () => import('../views/classify/ClassifiedDisplay')
      },
    //  我的页面
      {
        path: 'essay',
        component: () => import('../views/myself/myself')
      },
      {
        path: 'like',
        component: () => import('../views/myself/myself')
      },
      {
        path: 'collect',
        component: () => import('../views/myself/myself')
      }
    ]
  },
  {
    path: '/administrator',
    name: 'administrator',
    component: Administrator,
    children:[
      {
        path: 'recommend',
        component: () => import('../views/Recommend.vue')
      }
    ]
  },
  {
    path: '/twitter',
    name: 'twitter',
    component: () => import('../views/show/Twitter'),
    props:true
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
