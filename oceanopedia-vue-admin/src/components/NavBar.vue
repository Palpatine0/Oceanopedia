<template>
    <v-navigation-drawer :value="drawer && isUserLoggedIn" app dark color="primary" width="300">
        <v-row class="align-center mt-5">
            <v-col class="mt-5 d-flex flex-column align-center" cols="12">
                <v-avatar size="100">
                    <img alt="User Avatar" :src="this.img_prefix +user.avatar">
                </v-avatar>
                <p class="white--text subheading mt-1">
                    <b>{{ user.username }}</b>
                </p>
            </v-col>
        </v-row>

        <v-row align="center" justify="center">
            <v-list width="300">
                <v-list-item-group color="grey lighten-5">
                    <v-list-item v-for="(item, i) in links" :key="i" link @click="navigateTo(item.route)">
                        <v-list-item-icon>
                            <v-icon size="16" v-text="item.icon"></v-icon>
                        </v-list-item-icon>
                        <v-list-item-content>
                            <v-list-item-title v-text="item.text"></v-list-item-title>
                        </v-list-item-content>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-row>
    </v-navigation-drawer>
</template>

<script>
import {mapState} from 'vuex'

export default {
    data() {
        return {};
    },
    methods: {
        navigateTo(route) {
            this.$router.push(route);
        }
    },
    computed: {
        ...mapState(['drawer', 'links', 'img_prefix', 'user']),
        isUserLoggedIn() {
            return this.user.id !== '';
        },
    },
};
</script>
