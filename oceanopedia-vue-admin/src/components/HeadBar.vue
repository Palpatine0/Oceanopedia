<template>
    <v-app-bar app flat v-if="isUserLoggedIn">
        <v-app-bar-nav-icon  class="grey--text" @click="toggleDrawer"/>

        <v-toolbar-title class="text-uppercase grey--text">
            <span class="font-weight-light">Oceanopedia</span>
            <span>Admin</span>
        </v-toolbar-title>

        <v-spacer></v-spacer>

        <!-- dropdown menu -->
        <v-menu   offset-y>
            <template v-slot:activator="{ on }">
                <v-btn color="grey" text v-on="on">
                    <v-icon left>mdi-expand-more</v-icon>
                    <span>Menu</span>
                </v-btn>
            </template>
            <v-list>
                <v-list-item v-for="link in links" :key="link.text" :to="link.route">
                    <v-list-item-title>{{ link.text }}</v-list-item-title>
                </v-list-item>
            </v-list>
        </v-menu>

        <v-btn color="grey" text @click="signOut">
            <span>Sign Out</span>
            <v-icon right>mdi-exit-to-app</v-icon>
        </v-btn>
    </v-app-bar>
</template>

<script>
import {mapState} from 'vuex'

export default {
    name: 'HeadBar',
    data() {
        return {}
    },
    computed: {
        ...mapState(['drawer', 'links', 'userId']),
        isUserLoggedIn() {
            return this.userId !== '';
        }
    },
    methods: {
        toggleDrawer() {
            this.$store.commit('toggleDrawer');
        },
        signOut() {
            this.$store.commit('setUserId', { data: '' });
            localStorage.removeItem('userId');
            this.$router.push({ name: 'login' });
        }
    }
}
</script>
