<template>
    <div id="login-container">
        <router-Link to="/login">
            <div class="sign_in">
                Sign In
            </div>
        </router-Link>

        <div class="sign_up">
            Sign Up
        </div>

        <v-container>
            <v-text-field label="Username" placeholder="Username" v-model="username" outlined></v-text-field>
            <v-text-field label="Password" placeholder="Password" v-model="password" outlined></v-text-field>
            <v-btn color="primary" x-large dark width="100%" @click="register">
                Sign Up
            </v-btn>
        </v-container>
    </div>
</template>


<script>
export default {
    name: 'Register',
    components: {},
    data() {
        return {
            username: '',
            password: '',
        }
    },
    methods: {
        register() {
            this.$api.register({
                username: this.username,
                password: this.password
            })
            .then(data => {
                if (data.data.status == 200) {
                    alert(data.data.msg)
                    this.$router.push({ name: 'Login' });
                } else {
                    alert(data.data.msg)
                }
            })
        },

        uploadImageNoPrefix(file, imgKey) {
            this.$api.uploadImageNoPrefix({file: file})
            .then((data) => {
                console.log(data)
                if (data.data.status === 200 && data.data.data) {
                    this.item.imgs[imgKey] = data.data.data;
                    this.uploadItemShowcases_add_snackbar = true;
                    this.uploadItemShowcases_add_msg = data.data.msg;
                } else {
                    throw new Error('Failed to upload image or bad data received');
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

    .sign_in {
        position: absolute;
        top: 5px;
        right: 5px;
        float: right;
        color: #2a5367;
        font-weight: bold;
    }

    .sign_up {
        position: relative;
        width: 134px;
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