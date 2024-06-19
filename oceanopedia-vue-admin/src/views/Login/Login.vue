<template>
    <div id="login-container">
        <router-Link to="/register">
            <div class="sign_up">
                Sign Up
            </div>
        </router-Link>

        <div class="sign_in">
            Sign In
        </div>

        <v-container>
            <v-text-field label="Username" placeholder="Username" v-model="username" outlined></v-text-field>
            <v-text-field label="Password" placeholder="Password" v-model="password" outlined type="password"></v-text-field>
            <v-btn color="primary" x-large dark width="100%" @click="login">
                Sign In
            </v-btn>
        </v-container>
    </div>
</template>

<script>
import {mapActions} from 'vuex'

export default {
    name: 'Login',
    data() {
        return {
            username: '',
            password: '',
        }
    },
    methods: {
        ...mapActions(['setUserAction']),
        login() {
            this.$api.login({
                username: this.username,
                password: this.password
            })
            .then(data => {
                if (data.data.status == 200) {
                    this.$api.getUserByUsername({
                        username: this.username,
                    })
                    .then(userData => {
                        this.setUserAction({
                            id: userData.data.data.id,
                            username: userData.data.data.username,
                            avatar: userData.data.data.avatar
                        });
                        this.$router.push({ name: 'Detail' });
                    });
                    alert(data.data.msg);
                } else {
                    alert(data.data.msg);
                }
            })
        },
    },
}
</script>

<style scoped>
#login-container {
    width: 300px;
    margin: 160px auto 0 auto;

    * {
        font-family: Arial;
    }

    .sign_up {
        position: absolute;
        top: 5px;
        right: 5px;
        float: right;
        font-weight: bold;
        color: #2a5367;
    }

    .sign_in {
        position: relative;
        width: 118px;
        height: 40px;
        margin: 0 auto;
        margin-bottom: 10px;
        font-size: 35px;
        font-weight: bold;
        display: block;
        color: #2a5367;
    }
}
</style>
