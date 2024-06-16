import Vue from 'vue'
import VueRouter from 'vue-router'
import Dashboard from '../views/Dashboard/Dashboard.vue'
import Item from '../views/Dashboard/Item/Item.vue'
import Users from '../views/Users/Users.vue'
import Register from "@/views/Register/Register.vue";
import Login from '../views/Login/Login.vue'
import store from '../store'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Dashboard',
        component: Dashboard
    },
    {
        path: '/item/:id',
        name: 'Details',
        component: Item
    },
    {
        path: '/users',
        name: 'Users',
        component: Users
    },
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
