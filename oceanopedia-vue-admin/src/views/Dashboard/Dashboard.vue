<template>
    <v-app :style="{ padding: isUserLoggedIn ? '20px' : '0px', minHeight: '100vh' }">
        <v-subheader v-if="isUserLoggedIn" as="h1" class="subheading grey--text">Dashboard</v-subheader>
        <v-container v-if="isUserLoggedIn">
            <v-row style="justify-content: space-between">
                <v-col cols="2">
                    <v-select v-model="selectedCategory" :items="categories" label="Category"></v-select>
                </v-col>
                <AddArticle_Model v-if="isUserLoggedIn" class="mt-5"></AddArticle_Model>
                <div class="mt-5">
                    <v-pagination v-model="page" :length="pagination" class="float-right" color="primary" @input="getArticlesByCity"></v-pagination>
                </div>
            </v-row>
            <div v-if="articleList[0]!=null">
                <v-row>
                    <v-col v-for="(item, index) in articleList" :key="index" cols="12" lg="4" md="6">
                        <v-card class="mx-auto" max-width="400">
                            <v-img :src="img_prefix + item.coverImage" class="white--text align-end" height="200px"></v-img>
                            <v-card-title class="pb-0 mb-3">
                                {{ item.title }}
                            </v-card-title>
                            <v-card-subtitle class="pb-0 mb-3">
                                {{ item.author }}
                            </v-card-subtitle>
                            <v-card-text>
                                <v-row align="center" class="mx-0">
                                    <v-rating :value="calculateRating(item)" color="amber" dense half-increments readonly size="14"></v-rating>
                                    <div class="grey--text ms-4">
                                        {{ calculateRating(item).toFixed(2) }} ({{ item.views }})
                                    </div>
                                </v-row>
                            </v-card-text>
                            <v-divider class="mx-4 mt-2"></v-divider>

                            <v-card-actions>
                                <v-btn color="primary lighten-2" text :href="item.link">
                                    Explore
                                </v-btn>
                                <v-spacer></v-spacer>
                                <v-btn icon @click="toggleShow(index)">
                                    <v-icon>{{ showStates[index] ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                                </v-btn>
                            </v-card-actions>

                            <v-expand-transition>
                                <div v-show="showStates[index]">
                                    <v-divider></v-divider>
                                    <v-card-text>
                                        <div style="font-weight: bold" class="my-4 text-subtitle-1">
                                            Summary
                                        </div>
                                        {{ item.summary }}
                                    </v-card-text>
                                </div>
                            </v-expand-transition>
                        </v-card>
                    </v-col>
                </v-row>
            </div>
            <div v-else>
                <v-sheet color="darken-2" style="background-color: #fafafa;">
                    <div style="display: flex">
                        <v-skeleton-loader class="mx-auto" width="366" type="card">
                        </v-skeleton-loader>
                        <v-skeleton-loader class="mx-auto" width="366" type="card">
                        </v-skeleton-loader>
                        <v-skeleton-loader class="mx-auto" width="366" type="card">
                        </v-skeleton-loader>

                    </div>

                </v-sheet>
            </div>
        </v-container>

    </v-app>
</template>

<script>
import {mapState} from "vuex";
import AddArticle_Model from "@/views/Dashboard/AddArticle-Model/AddArticle-Model.vue";

export default {
    components: {
        AddArticle_Model
    },
    data() {
        return {
            page: 1,
            pagination: 0,
            totalCount_item: 0,
            articleList: [],
            showStates: {}
        }
    },
    methods: {
        getArticlesByCity() {
            this.$api.getArticlesByCategory({
                category: this.selectedCategory,
                page: this.page - 1,
                rows: 3
            })
            .then((data) => {
                this.articleList = []
                this.totalCount_item = data.data.cnt
                this.pagination = Math.ceil(this.totalCount_item / 3);
                this.articleList = this.articleList.concat(data.data.data)
                this.initializeShowStates();
                console.log("this.articleList")
                console.log(this.articleList)
            })
        },
        toggleShow(index) {
            this.$set(this.showStates, index, !this.showStates[index]);
        },
        initializeShowStates() {
            this.showStates = {};
            this.articleList.forEach((item, index) => {
                this.$set(this.showStates, index, true);
            });
        },
        calculateRating(item) {
            if (item.views === 0) {
                return 0;
            }
            return (item.likes / item.views) * 5;
        }
    },
    mounted() {
        this.getArticlesByCity();
    },
    computed: {
        ...mapState(['category', 'categories', 'img_prefix', 'userId']),
        selectedCategory: {
            get() {
                return this.$store.state.category;
            },
            set(value) {
                this.$store.commit('setCategory', value);
                this.getArticlesByCity();
            }
        },
        isUserLoggedIn() {
            return this.userId !== '';
        }
    }
}
</script>