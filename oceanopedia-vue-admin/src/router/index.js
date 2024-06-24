import Vue from 'vue'
import VueRouter from 'vue-router'
import Article from '../views/Article/Article.vue'
import Detail from '../views/Article/Detail/Detail.vue'
import Users from '../views/Users/Users.vue'
import Register from "@/views/Register/Register.vue";
import Login from '../views/Login/Login.vue'
import store from '../store'
import Dashboard from "@/views/Dashboard/Dashboard.vue";
import Banner from "@/views/Banner/Banner.vue";

Vue.use(VueRouter)

const routes = [
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },

    {
        path: '/',
        name: 'Dashboard',
        component: Dashboard
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
    {
        path: '/article',
        name: 'Article',
        component: Article
    },
    {
        path: '/banner',
        name: 'Banner',
        component: Banner
    },

]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

router.beforeEach((to, from, next) => {
    if (!store.state.user.id && to.name !== 'Login' && to.name !== 'Register') {
        next({ name: 'Login' });
    } else {
        next();
    }
});

export default router
