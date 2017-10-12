import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    {
      path: '/login',
      component: resolve => require(['../components/user/Login.vue'], resolve)
    },
    {
      path: '/home',
      component: resolve => require(['../components/Home.vue'], resolve),
      children:[
        {
          path: '/',
          component: resolve => require(['../components/Index.vue'], resolve)
        },
        {
          path: '/users',
          component: resolve => require(['../components/user/Users.vue'], resolve)
        },
        {
          path: '/user/info',
          component: resolve => require(['../components/user/UserInfo.vue'], resolve)
        }
      ]
    }
  ]
})