<template>
    <v-app style="padding: 20px">
        <v-subheader as="h1" class="subheading grey--text">Dashboard > Article</v-subheader>

        <div v-if="article.coverImage">
            <v-img :src="img_prefix + article.coverImage" class="white--text align-end" height="400px" style="background-color: gainsboro"></v-img>
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
                <v-col cols="6" md="6" sm="6">
                    <div v-if="article.title">
                        <p class="info-head-title">
                            {{ article.title }}
                        </p>
                        <p class="title">{{ article.author }}</p>
                    </div>
                    <div v-else-if="!article.title">
                        <v-sheet color="darken-2" style="background-color: #fafafa">
                            <v-skeleton-loader height="40" type="text"></v-skeleton-loader>
                            <v-skeleton-loader class="mt-2" width="400" height="20" type="text"></v-skeleton-loader>
                        </v-sheet>
                    </div>
                </v-col>

                <v-col cols="6" md="2" sm="6">
                    <div v-if="article.status==true">
                        <v-btn color="accent" rounded x-large>
                            <v-icon size="26">mdi-checkbox-marked-circle</v-icon>
                            &nbsp;PUBLISHED
                        </v-btn>
                    </div>
                    <div v-else-if="article.status!=true">
                        <v-btn color="#dbdbdb" rounded x-large>
                            <v-icon size="26">mdi-cancel</v-icon>
                            &nbsp;DRAFT
                        </v-btn>
                    </div>
                </v-col>
                <v-col cols="6" md="2" sm="6">
                    <div style="float: right">
                        <v-icon size="66">mdi-cog</v-icon>
                    </div>
                </v-col>
            </v-row>
        </v-container>


        <v-container class="mb-5 mb-5">
            <v-row>
                <!--Info-->
                <v-col cols="6" md="10" sm="6">
                    <v-row style="height: 120px">
                        <!--Views-->
                        <v-col class="info-cell" cols="6" md="5" sm="6">
                            <v-row style="margin-left: 20px;margin-top: 2px;height: 100px">
                                <v-col cols="6" md="4" sm="6">
                                    <v-icon class="mb-4" size="70" style="color: #7E7E7EFF;">mdi-eye-outline</v-icon>
                                </v-col>
                                <v-col class="mt-1" style="margin-left: 30px">
                                    <p class="info-head">
                                        {{ article.views }}
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
                                        {{ article.likes }}
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
                                        {{ article.publicationDate | formatDate }}
                                    </p>
                                    <p class="info-body">Publication Date</p>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                </v-col>
                <v-col cols="6" md="2">
                    <v-progress-circular :rotate="90" :size="230" :width="35" :value="article.views !== 0 ? parseFloat((article.likes / article.views) * 100).toFixed(2) : 0" color="#5aaeaa" style="margin-left: -66px">
                        <span style="font-size: 20px;font-weight: bold">
                            {{ article.views !== 0 ? parseFloat((article.likes / article.views) * 100).toFixed(2) : 0 }}%
                        </span>
                    </v-progress-circular>
                </v-col>
            </v-row>
        </v-container>

        <v-divider></v-divider>

        <!-- Article Content -->
        <v-container class="mb-5">
            <v-row>
                <v-col cols="12">
                    <v-card-title>
                        <h3>Article Content</h3>
                    </v-card-title>
                    <v-card-text style="font-size: 16px">
                        {{ article.content }}
                    </v-card-text>
                </v-col>
            </v-row>
        </v-container>


        <!--        <v-col cols="12" md="3" sm="6" style="margin-top: -80px">
                    <v-card class="mx-auto center_h" max-width="344" outlined
                            style="border-radius: 14px;flex-direction: column">
                        <v-btn class="edit-btn mt-5 mb-4" color="#156ff6" dark style="height: 50px!important;"
                               @click="statusUpdate_dialog=!statusUpdate_dialog">
                            Edit Status
                        </v-btn>
                        <v-btn class="edit-btn mb-2" color="#156ff6" dark outlined
                               @click="infoUpdate_dialog=!infoUpdate_dialog">
                            Edit Info
                        </v-btn>
                        <v-btn class="edit-btn mb-5" color="#156ff6" dark outlined
                               @click="showcasesUpdate_dialog=!showcasesUpdate_dialog">
                            Edit Showcases
                        </v-btn>
                    </v-card>
                    <v-card class="mx-auto center_h mt-3 mb-3" max-width="344" outlined
                            style="border-radius: 14px;flex-direction: column;border-color: #ffa9ac">
                        <v-btn class="edit-btn mt-3 mb-3" color="red" dark outlined
                               @click="itemDelete_dialog=!itemDelete_dialog">
                            <b>DELETE</b>
                        </v-btn>
                    </v-card>
                </v-col>-->

        <!--        <ItemEditStatus :item-info="article" :statusUpdate_dialog="statusUpdate_dialog" />-->
        <!--        <ItemEditInfo :infoUpdate_dialog="infoUpdate_dialog" :item-info="article" />-->
        <!--        <ItemEditShowcases :item-info="article" :showcasesUpdate_dialog="showcasesUpdate_dialog" />-->

        <v-dialog v-model="itemDelete_dialog" max-width="320" persistent>
            <v-card>
                <v-card-title class="text-h5">
                    CONFIRM YOUR OPTION
                </v-card-title>
                <v-card-text>You can't revert this action once deleted.</v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="red darken-1" text @click="itemDelete_dialog = false">
                        Cancel
                    </v-btn>
                    <v-btn color="red darken-1" text @click="deleteItemByID(),itemDelete_dialog = false">
                        Confirm
                    </v-btn>
                </v-card-actions>
            </v-card>

        </v-dialog>

    </v-app>
</template>

<script>
// import ItemEditStatus from "@/views/Dashboard/Article/Item-editStatus/Item-editStatus.vue";
// import ItemEditInfo from "@/views/Dashboard/Article/Item-editInfo/Item-editInfo.vue";
// import ItemEditShowcases from "@/views/Dashboard/Article/Item-editShowcases/Item-editShowcases.vue";
import {mapState} from "vuex";
import Vue from 'vue';

Vue.filter('formatDate', function (value) {
    if (!value) return '';
    return value.split('T')[0];
});

export default {
    name: "Article",
    components: {
        // ItemEditStatus,
        // ItemEditInfo,
        // ItemEditShowcases
    },
    data() {
        return {
            // page vars
            article: {},
            title: '',

            statusUpdate_dialog: false,
            infoUpdate_dialog: false,
            showcasesUpdate_dialog: false,
            itemDelete_dialog: false
        };
    },
    methods: {
        commasNumber(x) {
            return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
        },
        deleteItemByID() {
            this.$api.deleteArticleByID({id: this.$route.params.id})
            .then(data => {
                if (data.data.status == 200) {
                    window.history.back();
                }
            });
        }
    },
    mounted() {
        this.$api.getArticleByID({id: this.$route.params.id})
        .then(data => {
            this.article = data.data;
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