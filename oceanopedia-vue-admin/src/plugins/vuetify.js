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
                secondary: '#b0bec5',
                accent: '#2c3c5d',
                error: '#b71c1c',
                // Add your custom colors here
                background: '#f5f5f5',
            },
        },
    },
});
