<template>
    <v-app style="padding: 20px">
        <v-subheader as="h1" class="subheading grey--text">Users</v-subheader>
        <v-container class="my-5">
            <v-row style="justify-content: right">
                <div>
                    <v-pagination v-model="page" :length="pagination" class="float-right" color="primary" @input="getUsers"></v-pagination>
                </div>
            </v-row>
            <v-row>
                <v-col v-for="user in users" :key="user.id" cols="12" lg="3" md="4" sm="6">
                    <v-card class="d-flex flex-column align-center justify-center ma-3" >
                        <v-avatar class="grey lighten-2 mt-5" size="100">
                            <v-img :src="img_prefix+user.avatar"></v-img>
                        </v-avatar>
                        <v-card-text class="text-center">
                            <div class="subheading">{{ user.username }}</div>
                            <div class="grey--text">{{ user.phone }}</div>
                            <div hidden>{{ user.id }}</div>
                        </v-card-text>
                        <v-card-actions style="width: 100%; display: flex; justify-content: space-between">
                            <v-btn color="grey" text>
                                <v-icon left small>mdi-message</v-icon>
                                Message
                            </v-btn>
                            <v-btn color="grey" text @click="setDeleteId(user.id),snackbar = true">
                                <v-icon>mdi-trash-can-outline</v-icon>
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>

        <!--Snackbars-->
        <v-snackbar v-model="snackbar" :multi-line="true" color="secondary">
            <span style="color: #2a566b;">Confirm your option.</span>
            <template v-slot:action="{ attrs }">
                <v-btn color="red" text v-bind="attrs" @click="snackbar = false,deleteUser()">
                    Confirm
                </v-btn>
                <v-btn text v-bind="attrs" @click="snackbar = false">
                    <span style="color: #2a566b;">Cancel</span>
                </v-btn>
            </template>
        </v-snackbar>
    </v-app>
</template>

<script>

import {mapState} from "vuex";

export default {
    data() {
        return {
            snackbar: false,
            userId_delete: '',
            page: 1,
            pagination: 0,
            totalCountItem: 0,
            users: [],
        }
    },
    methods: {
        getUsers() {
            this.$api.getUsers({
                page: this.page - 1,
                rows: 8
            })
            .then(data => {
                console.log("data")
                console.log(data.data.cnt)
                this.users = data.data.data
                this.totalCountItem = data.data.cnt
                this.pagination = Math.ceil(this.totalCountItem / 8)
            })
        },
        setDeleteId(id) {
            this.userId_delete = id;
        },
        deleteUser() {
            console.log(this.userId_delete);
            this.$api.deleteUserById({
                id: this.userId_delete
            })
            .then(data => {
                if (data.status == 200) {
                    window.location.reload();
                }
            })
        }
    },
    mounted() {
        this.getUsers()
    },
    computed: {
        ...mapState(['img_prefix']),
    },
}
</script>

<style>
* {
    /* outline: 1px solid red; */
}
</style>