<template>
    <v-app style="padding: 20px">
        <v-subheader as="h1" class="subheading grey--text">Dashboard > Article</v-subheader>
        <v-img :src="img_prefix + article.coverImage" class="white--text align-end" height="400px"></v-img>


        <!--Title-->
        <v-container class="mt-5 mb-2">
            <v-row style="height: 90px">
                <v-col style="height: 100%">
                    <p class="info-head-title">
                        {{ article.title }}
                    </p>
                    <p class="title">{{ article.author }}</p>
                </v-col>
            </v-row>
        </v-container>


        <!--Info-->
        <v-container class="mt-5 mb-5">
            <v-row style="height: 90px">

                <!--Publication Date-->
                <v-col class="info-cell" cols="6" md="5" sm="6" >
                    <v-row style="padding-top: 20px">
                        <v-col cols="6" md="4" sm="6">
                            <p class="info-body">Publication Date</p>
                        </v-col>
                        <v-col cols="6" md="8" sm="6">
                            <p class="info-head">
                                {{ article.publicationDate | formatDate }}
                            </p>
                        </v-col>
                    </v-row>
                </v-col>

                <!--Views-->
                <v-col class="info-cell" cols="6" md="3" sm="6">
                    <v-row style="padding-top: 20px">
                        <v-col cols="6" md="4" sm="6">
                            <v-icon size="56" style="color: white;display: inline-block">mdi-checkbox-marked-circle</v-icon>
                            <p class="info-body">Views</p>
                        </v-col>
                        <v-col cols="6" md="8" sm="6">
                            <p class="info-head" >
                                {{ article.views }}
                            </p>
                        </v-col>
                    </v-row>
                </v-col>

                <!--Likes-->
                <v-col class="info-cell" cols="6" md="3" sm="6">
                    <v-row style="padding-top: 20px">
                        <v-col cols="6" md="4" sm="6">
                            <p class="info-body">Likes</p>
                        </v-col>
                        <v-col cols="6" md="8" sm="6">
                            <p class="info-head">
                                {{ article.likes }}
                            </p>
                        </v-col>
                    </v-row>
                </v-col>
            </v-row>
        </v-container>

        <v-container class="mt-5" style="float: right;display: flex">
            <div v-if="article.status='PUBLISHED'">
                <v-chip class="ma-2" close-icon="mdi-delete" color="accent" text-color="white">
                    <v-icon size="16">mdi-checkbox-marked-circle</v-icon>
                    &nbsp;PUBLISHED
                </v-chip>
            </div>
            <div v-else-if="article.status='DRADT'">
                <v-chip class="ma-2">
                    DRADT
                </v-chip>
            </div>
        </v-container>

        <v-container style="display: flex">
            <v-col cols="12" md="9" sm="6">
                <v-row>
                    <v-col class="info-cell" cols="6" md="4" sm="6">
                        <v-icon>fa-city</v-icon>
                        <span>{{ article.category }}</span>
                    </v-col>
                    <v-col class="info-cell" cols="6" md="4" sm="6">
                        <v-icon>mdi-tag</v-icon>
                        <span>{{ article.tags.join(', ') }}</span>
                    </v-col>
                </v-row>
            </v-col>

            <!--            <v-col cols="12" md="3" sm="6" style="margin-top: -80px">
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
        </v-container>

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
    font-size: 32px;
    line-height: 32px;
    color: #fff;
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

.info-body {
    color: rgb(0, 0, 0);
    text-transform: none;
    font-family: "Roboto Thin", "Helvetica Neue UltraLight", Tahoma, Geneva, sans-serif !important;
    font-size: 24px;
    line-height: 32px;
    font-weight: 100;
    position: relative;
    bottom: 20px;
    color: white;
}

.info-cell {
    background-color: #5aaaab;
    margin: 4px;
    border-radius: 10px;
}



.edit-btn {
    display: block;
    width: 90%;
    height: 40px !important;
}

* {
//outline: 1px solid red;
}
</style>
