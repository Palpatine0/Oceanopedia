import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        drawer: true,
        category: 'Ocean Life',
        img_prefix: 'http://111.231.19.137:8888/',
        links: [{icon: 'fa-lg fas fa-home', text: 'Dashboard', route: '/'}, {
            icon: 'fa-lg fa-solid fa-user',
            text: 'Users',
            route: '/users'
        }, // {icon: 'fa-lg fa-solid fa-flask', text: 'Test', route: '/test'},
        ],
        categories: ['Ocean Life', 'Ecosystems', 'Planet Ocean'],
        user: {
            id: localStorage.getItem('userId') || '', username: localStorage.getItem('username') || '',
        }
    }, mutations: {
        toggleDrawer(state) {
            state.drawer = !state.drawer;
        },
        setCategory(state, category) {
            state.category = category;
            localStorage.setItem('category', category);
        },
        setUser(state, payload) {
            state.user.id = payload.id;
            state.user.username = payload.username;
            state.user.avatar = payload.avatar;
            localStorage.setItem('userId', payload.id);
            localStorage.setItem('username', payload.username);
            localStorage.setItem('avatar', payload.avatar);
        },
        clearUser(state) {
            state.user.id = '';
            state.user.username = '';
            state.user.avatar = '';
            localStorage.removeItem('userId');
            localStorage.removeItem('username');
            localStorage.removeItem('avatarusername');
        }
    }, actions: {
        initializeStore({commit}) {
            if (localStorage.getItem('category')) {
                commit('setCategory', localStorage.getItem('category'));
            }
            if (localStorage.getItem('userId') && localStorage.getItem('username')) {
                commit('setUser', {
                    id: localStorage.getItem('userId'),
                    username: localStorage.getItem('username'),
                    avatar: localStorage.getItem('avatar')
                });
            }
        }, setUserAction({commit}, payload) {
            commit('setUser', payload);
        }, clearUserAction({commit}) {
            commit('clearUser');
        }
    }, modules: {
        // You can define modules here if needed
    }
});
