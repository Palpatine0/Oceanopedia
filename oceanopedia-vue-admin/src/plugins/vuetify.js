import '@fortawesome/fontawesome-free/css/all.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';

Vue.use(Vuetify);

export default new Vuetify({
    icons: {
        iconfont: 'fa' || 'md',
    },
    theme: {
        themes: {
            light: {
                primary: '#2a5367',
                secondary: '#dee3e6',
                accent: '#2c3c5d',
                background: '#f5f5f5',
            },
        },
    },
});
