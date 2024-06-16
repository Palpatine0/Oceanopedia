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
        userId: localStorage.getItem('userId') || '', // Initialize userId from localStorage
    },
    mutations: {
        toggleDrawer(state) {
            state.drawer = !state.drawer;
        },
        setCity(state, city) {
            state.city = city;
            localStorage.setItem('city', city); // Save to local storage
        },
        setUserId(state, payload) {
            state.userId = payload.data;
            localStorage.setItem('userId', payload.data); // Save to local storage
        }
    },
    actions: {
        initializeStore({commit}) {
            if (localStorage.getItem('city')) {
                commit('setCity', localStorage.getItem('city'));
            }
            if (localStorage.getItem('userId')) {
                commit('setUserId', {data: localStorage.getItem('userId')});
            }
        },
        setUserIdAction(context, payload) {
            context.commit('setUserId', payload);
        }
    },
    modules: {
        // You can define modules here if needed
    }
});
