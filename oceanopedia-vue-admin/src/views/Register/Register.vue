<template>
    <div id="register-container">
        <div class="sign_up">Sign Up</div>
        <router-link to="/login">
            <div class="sign_in">Sign In</div>
        </router-link>
        <v-container>
            <span class="d-flex justify-center">
                <div v-if="this.isAvatarSet">
                    <v-avatar size="148" class="d-flex justify-center mb-6">
                        <v-img :src="this.img_prefix + this.avatar" aspect-ratio="2"></v-img>
                    </v-avatar>
                </div>
                <div v-else>
                    <v-avatar size="148" class="d-flex justify-center mb-6">
                        <v-img :src="this.img_prefix + 'group1/M00/00/02/CgAEDGZvMWqAQoDMAACf56sfwHE812.png'"></v-img>
                    </v-avatar>
                </div>
            </span>
            <v-file-input label="Select Your Avatar" truncate-length="50" @change="handleFileChange"></v-file-input>
            <v-text-field label="Username" placeholder="Username" v-model="username" outlined :rules="[v => !!v || 'Username is required']"></v-text-field>
            <v-text-field label="Password" placeholder="Password" v-model="password" outlined :rules="[v => !!v || 'Password is required']" type="password"></v-text-field>
            <v-btn color="primary" x-large dark width="100%" @click="register">Sign Up</v-btn>
        </v-container>

        <!-- Snackbars -->
        <v-snackbar v-model="uploadAvatarSnackbar" :timeout="2000">
            {{ uploadAvatarMsg }}
        </v-snackbar>
        <v-snackbar v-model="validationSnackbar" :timeout="2000" color="error">
            {{ validationMsg }}
        </v-snackbar>

    </div>
</template>


<script>
import {mapState} from 'vuex';

export default {
    name: 'Register',
    components: {},
    data() {
        return {
            username: '',
            password: '',
            avatar: 'group1/M00/00/02/CgAEDGZvMWqAQoDMAACf56sfwHE812.png',
            previousAvatar: '',
            isAvatarSet: false,

            uploadAvatarMsg: '',
            uploadAvatarSnackbar: false,

            validationMsg: '',
            validationSnackbar: false,

        };
    },
    methods: {
        register() {
            this.$api
            .register({
                username: this.username,
                password: this.password,
                avatar: this.avatar,
                type: "admin",
            })
            .then((data) => {
                if (data.data.status == 200) {
                    alert(data.data.msg);
                    this.$router.push({name: 'Login'});
                } else {
                    this.validationMsg = data.data.msg;
                    this.validationSnackbar = true;
                }
            });
        },

        uploadImageNoPrefix(file) {
            this.$api
            .uploadImageNoPrefix({file: file})
            .then((data) => {
                if (data.data.status === 200 && data.data.data) {
                    this.previousAvatar = this.avatar;
                    this.avatar = data.data.data;
                    this.uploadAvatarSnackbar = true;
                    this.uploadAvatarMsg = data.data.msg;
                    this.isAvatarSet = true;
                } else {
                    throw new Error('Failed to upload image or bad data received');
                }
            });
        },

        handleFileChange(files) {
            if (!files || files.length === 0) {
                console.log('File input was canceled');
                this.avatar = this.previousAvatar || 'group1/M00/00/02/CgAEDGZvMWqAQoDMAACf56sfwHE812.png';
                this.isAvatarSet = !!this.previousAvatar;
                return;
            }
            this.uploadImageNoPrefix(files);
        },
    },
    computed: {
        ...mapState(['img_prefix']),
    },
};
</script>


<style scoped>
#register-container {
    width: 300px;
    margin: 120px auto 0 auto;

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