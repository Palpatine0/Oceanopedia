<template>
    <v-container>
        <v-row justify="center">
            <v-dialog v-model="localDialog" max-width="600px" persistent>
                <v-card>
                    <v-card-title>
                        <span class="text-h5">Edit Banners</span>
                    </v-card-title>
                    <v-card-text>
                        <v-container>
                            <v-row>
                                <v-col cols="12" v-for="(banner, index) in banners_new" :key="index">
                                    <v-img :src="img_prefix + banner" aspect-ratio="2"></v-img>
                                    <v-file-input
                                            show-size
                                            truncate-length="50"
                                            @change="file => uploadImageNoPrefix(file, `banner${index + 1}`)"
                                    ></v-file-input>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card-text>
                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="localDialog = false">
                            Close
                        </v-btn>
                        <v-btn color="blue darken-1" text @click="updateBanners">
                            Submit
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </v-row>

        <v-snackbar v-model="updateSnackbar" :timeout="2000">
            {{ updateMsg }}
        </v-snackbar>

        <v-snackbar v-model="uploadSnackbar" :timeout="2000">
            {{ uploadMsg }}
        </v-snackbar>
    </v-container>
</template>

<script>
import {mapState} from "vuex";

export default {
    props: {
        banners: {
            type: Array,
            required: true
        },
        editBanner_dialog: {
            type: Boolean,
            required: true
        }
    },
    data() {
        return {
            banners_new: ['', '', ''],
            updateMsg: '',
            updateSnackbar: false,
            uploadMsg: '',
            uploadSnackbar: false,
            localDialog: this.editBanner_dialog,
            banner1: '',
            banner2: '',
            banner3: '',
        };
    },
    watch: {
        editBanner_dialog(newVal) {
            this.localDialog = newVal;
            if (newVal) {
                this.assignImgUrls();
            }
        },
        localDialog(newVal) {
            if (!newVal) {
                this.$emit('update:editBanner_dialog', false);
            }
        }
    },
    methods: {
        updateBanners() {
            console.log("this.banners_new before update:", this.banners_new);
            this.$api.updateBanners({
                banner1: this.banners_new[0],
                banner2: this.banners_new[1],
                banner3: this.banners_new[2],
                bannerX: this.banners_new[0]
            }).then(data => {
                if (data.data.status === 200) {
                    this.updateMsg = data.data.msg;
                    this.updateSnackbar = true;
                    this.localDialog = false;
                }
            });
        },
        uploadImageNoPrefix(file, imgKey) {
            this.$api.uploadImageNoPrefix({
                file: file
            }).then(data => {
                if (data.data.status === 200 && data.data.data) {
                    const index = parseInt(imgKey.replace('banner', '')) - 1;
                    this.banners_new[index] = data.data.data;
                    this.uploadMsg = "Image uploaded successfully!";
                    this.uploadSnackbar = true;
                } else {
                    this.uploadMsg = "Server error, try again.";
                    this.uploadSnackbar = true;
                }
            });
        },
        assignImgUrls() {
            if (this.banners) {
                this.banners_new = this.banners.map(banner => banner || '');
            }
        }
    },
    computed: {
        ...mapState(['img_prefix'])
    }
};
</script>

<style scoped>
</style>