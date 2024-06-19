import Vue from 'vue'
import VueRouter from 'vue-router'
import Article from '../views/Article/Article.vue'
import Detail from '../views/Article/Detail/Detail.vue'
import Users from '../views/Users/Users.vue'
import Register from "@/views/Register/Register.vue";
import Login from '../views/Login/Login.vue'
import store from '../store'

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'login',
        component: Login
    },
    {
        path: '/register',
        name: 'register',
        component: Register
    },
    {
        path: '/',
        name: 'Article',
        component: Article
    },
    {
        path: '/detail/:id',
        name: 'Detail',
        component: Detail
    },
    {
        path: '/users',
        name: 'Users',
        component: Users
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    if (!store.state.user.id && to.name !== 'login' && to.name !== 'register') {
        next({ name: 'login' });
    } else {
        next();
    }
});

export default router
