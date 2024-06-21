<template>
    <v-app style="padding: 20px">
        <v-subheader as="h1" class="subheading grey--text">Article > Detail</v-subheader>

        <div v-if="articleInfo.coverImage">
            <v-img :src="img_prefix + articleInfo.coverImage" class="white--text align-end" height="400px" style="background-color: gainsboro"></v-img>
        </div>
        <div v-else>
            <v-sheet color="lighten-4">
                <v-skeleton-loader height="400" type="image">
                </v-skeleton-loader>
            </v-sheet>
        </div>

        <!--Title-->
        <v-container class="mt-5">
            <v-row style="height: 120px;justify-content: space-between">
                <v-col cols="6" md="8" sm="6">
                    <div v-if="articleInfo.title">
                        <p class="info-head-title">
                            {{ articleInfo.title }}
                        </p>
                        <p class="title">{{ articleInfo.author }}</p>
                    </div>
                    <div v-else-if="!articleInfo.title">
                        <v-sheet color="darken-2" style="background-color: #fafafa">
                            <v-skeleton-loader height="40" type="text"></v-skeleton-loader>
                            <v-skeleton-loader class="mt-2" width="400" height="20" type="text"></v-skeleton-loader>
                        </v-sheet>
                    </div>
                </v-col>

                <v-col>
                    <v-row>
                        <v-col>
                            <div class="mt-1">
                                <div v-if="articleInfo.status==true" @click="updateItemStatusById">
                                    <v-btn color="accent" rounded x-large>
                                        <v-icon size="26">mdi-checkbox-marked-circle</v-icon>
                                        &nbsp;PUBLISHED
                                    </v-btn>
                                </div>
                                <div v-else-if="articleInfo.status!=true" @click="updateItemStatusById">
                                    <v-btn color="#dbdbdb" rounded x-large>
                                        <v-icon size="26">mdi-cancel</v-icon>
                                        &nbsp;DRAFT
                                    </v-btn>
                                </div>
                            </div>
                        </v-col>
                        <v-col>
                            <div style="float: right">
                                <v-icon size="66" color="#ababab" @click="editInfo_dialog=!editInfo_dialog">mdi-cog</v-icon>
                            </div>
                        </v-col>
                        <v-col>
                            <div style="float: right">
                                <v-icon size="66" color="error" @click="deleteArticle_dialog=!deleteArticle_dialog">mdi-trash-can-outline</v-icon>
                            </div>
                        </v-col>
                    </v-row>
                </v-col>
            </v-row>
        </v-container>


        <v-container class="mb-5 mb-5">
            <v-row>
                <!--Info-->
                <v-col cols="6" md="9" sm="6">
                    <v-row style="height: 120px">
                        <!--Views-->
                        <v-col class="info-cell" cols="6" md="5" sm="6">
                            <v-row style="margin-left: 20px;margin-top: 2px;height: 100px">
                                <v-col cols="6" md="4" sm="6">
                                    <v-icon class="mb-4" size="70" style="color: #7E7E7EFF;">mdi-eye-outline</v-icon>
                                </v-col>
                                <v-col class="mt-1" style="margin-left: 30px">
                                    <p class="info-head">
                                        {{ articleInfo.views }}
                                    </p>
                                    <p class="info-body">Views</p>
                                </v-col>
                            </v-row>
                        </v-col>

                        <!--Likes-->
                        <v-col class="info-cell" cols="6" md="5" sm="6">
                            <v-row style="margin-left: 20px;margin-top: 2px;height: 100px">
                                <v-col cols="6" md="4" sm="6">
                                    <v-icon class="mb-4" size="70" style="color: #7E7E7EFF;">mdi-thumb-up</v-icon>
                                </v-col>
                                <v-col class="mt-1" style="margin-left: 30px">
                                    <p class="info-head">
                                        {{ articleInfo.likes }}
                                    </p>
                                    <p class="info-body">Likes</p>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <v-row class="mt-5" style="height: 120px">
                        <!--Publication Date-->
                        <v-col class="info-cell" cols="6" md="10" sm="6">
                            <v-row style="margin-left: 20px;margin-top: 2px;height: 100px">
                                <v-col cols="6" md="4" sm="6">
                                    <v-icon class="mb-4" size="70" style="color: #7E7E7EFF;">mdi-clipboard-text-clock
                                    </v-icon>
                                </v-col>
                                <v-col class="mt-1" style="margin-left: 30px">
                                    <p class="info-head">
                                        {{ articleInfo.publicationDate | formatDate }}
                                    </p>
                                    <p class="info-body">Publication Date</p>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-col>
                <v-col cols="6" md="2">
                    <v-progress-circular :rotate="90" :size="230" :width="35" :value="articleInfo.views !== 0 ? parseFloat((articleInfo.likes / articleInfo.views) * 100).toFixed(2) : 0" color="#5aaeaa" style="margin-left: 30px">
                        <span style="font-size: 20px;font-weight: bold">
                            {{ articleInfo.views !== 0 ? parseFloat((articleInfo.likes / articleInfo.views) * 100).toFixed(2) : 0 }}%
                        </span>
                    </v-progress-circular>
                </v-col>
            </v-row>
        </v-container>

        <v-divider></v-divider>

        <!-- Detail Content -->
        <v-container class="mb-5">
            <v-row>
                <v-col cols="12">
                    <v-card-title>
                        <h3>Article Content</h3>
                    </v-card-title>
                    <v-card-text style="font-size: 16px">
                        {{ articleInfo.content }}
                    </v-card-text>
                </v-col>
            </v-row>
        </v-container>

        <v-dialog v-model="deleteArticle_dialog" max-width="320" persistent>
            <v-card>
                <v-card-title class="text-h5">
                    CONFIRM YOUR OPTION
                </v-card-title>
                <v-card-text>You can't revert this action once deleted.</v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="red darken-1" text @click="deleteArticle_dialog = false">
                        Cancel
                    </v-btn>
                    <v-btn color="red darken-1" text @click="deleteItemByID(),deleteArticle_dialog = false">
                        Confirm
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>


        <v-snackbar v-model="editArticleStatus_snackbar" color="" :timeout="2000">
            {{ editArticleStatus_msg }}
        </v-snackbar>


        <EditInfo_Model :articleInfo="articleInfo" :editInfo_dialog="editInfo_dialog"/>
    </v-app>
</template>

<script>
import {mapState} from "vuex";
import Vue from 'vue';
import EditInfo_Model from "@/views/Article/Detail/EditInfo-Model/EditInfo-Model.vue";

Vue.filter('formatDate', function (value) {
    if (!value) return '';
    return value.split('T')[0];
});

export default {
    name: "Article",
    components: {
        EditInfo_Model
    },
    data() {
        return {
            // page vars
            articleInfo: {},
            title: '',

            editArticleStatus_msg: false,
            editArticleStatus_snackbar: false,

            deleteArticle_dialog: false,

            editInfo_dialog: false,
        };
    },
    methods: {
        deleteItemByID() {
            this.$api.deleteArticleByID({id: this.$route.params.id})
            .then(data => {
                if (data.data.status == 200) {
                    setTimeout(() => {
                        window.location.reload();
                    }, 1200);
                }
            });
        },
        updateItemStatusById() {
            this.$api.updateArticleStatusById({
                id: this.articleInfo.id,
                status: !this.articleInfo.status
            })
            .then((data) => {
                if (data.data.status == 200) {
                    this.editArticleStatus_msg = data.data.msg;
                    this.editArticleStatus_snackbar = true
                    setTimeout(() => {
                        window.location.reload();
                    }, 1200);
                }
            })
        }
    },
    mounted() {
        this.$api.getArticleByID({id: this.$route.params.id})
        .then(data => {
            this.articleInfo = data.data;
            this.title = data.data.title;
        });
    },
    computed: {
        ...mapState(['category', 'img_prefix']),
    }
}
</script>
<style scoped>
.center_h {
    justify-content: center;
    align-items: center;
    display: flex;
    margin: 0 auto;
}

.info-head-title {
    font-weight: 700;
    font-size: 32px;
    line-height: 32px;
}

.info-head {
    font-weight: 700;
    font-size: 38px;
    line-height: 32px;
    color: #494949FF;
}

.info-body {
    color: rgb(117, 46, 46);
    text-transform: none;
    font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif !important;
    font-size: 24px;
    line-height: 32px;
    font-weight: 100;
    position: relative;
    bottom: 20px;
    color: #494949FF;
}

.info-cell {
    background-color: #dee3e6;
    border-radius: 10px;
    height: 120px;
    margin-left: 10px;
}

.info-cell div div {
    height: 100px;
}


.title {
    color: rgb(108, 108, 112);
    text-transform: none;
    font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif !important;
    font-size: 20px;
    line-height: 32px;
    font-weight: 100;
    position: relative;
    bottom: 10px;
}


.edit-btn {
    display: block;
    width: 90%;
    height: 40px !important;
}

::v-deep .v-skeleton-loader > * {
    height: 100%;
    display: flex;
    flex-direction: column;
}

::v-deep .v-skeleton-loader .v-skeleton-loader__bone {
    flex-grow: 1;
}

* {
//outline: 1px solid red;
}
</style>