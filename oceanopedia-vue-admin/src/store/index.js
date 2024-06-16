import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        drawer: true,
        city: 'Los Angeles',
        img_prefix: 'http://111.231.19.137:8888/',
        links: [
            {icon: 'fa-lg fas fa-home', text: 'Dashboard', route: '/'},
            {icon: 'fa-lg fa-solid fa-user', text: 'Users', route: '/users'},
            // {icon: 'fa-lg fa-solid fa-flask', text: 'Test', route: '/test'},
        ],
        cities: [
            'Dallas',
            'Cape Coral',
            'Beverly Hills'
        ],
        user: {
            id: localStorage.getItem('userId') || '',
            username: localStorage.getItem('username') || '',
        }
    },
    mutations: {
        toggleDrawer(state) {
            state.drawer = !state.drawer;
        },
        setCity(state, city) {
            state.city = city;
            localStorage.setItem('city', city); // Save to local storage
        },
        setUser(state, payload) {
            state.user.id = payload.id;
            state.user.username = payload.username;
            localStorage.setItem('userId', payload.id); // Save to local storage
            localStorage.setItem('username', payload.username); // Save to local storage
        },
        clearUser(state) {
            state.user.id = '';
            state.user.username = '';
            localStorage.removeItem('userId');
            localStorage.removeItem('username');
        }
    },
    actions: {
        initializeStore({commit}) {
            if (localStorage.getItem('city')) {
                commit('setCity', localStorage.getItem('city'));
            }
            if (localStorage.getItem('userId') && localStorage.getItem('username')) {
                commit('setUser', {
                    id: localStorage.getItem('userId'),
                    username: localStorage.getItem('username')
                });
            }
        },
        setUserAction({commit}, payload) {
            commit('setUser', payload);
        },
        clearUserAction({commit}) {
            commit('clearUser');
        }
    },
    modules: {
        // You can define modules here if needed
    }
});
