<template>
    <v-app style="padding: 20px">
        <v-subheader as="h1" class="subheading grey--text">Article</v-subheader>
        <v-app-bar color="rgba(0,0,0,0)" flat>
            <v-text-field label="Search ..." class="pt-5" filled prepend-inner-icon="mdi-magnify" dense solo flat background-color="grey lighten-4" rounded v-model="searchQuery" @input="handleSearch">
            </v-text-field>
        </v-app-bar>
        <v-list v-if="searchResults.length > 0" class="article-search-results-list" elevation="10">
            <v-list-item v-for="result in searchResults" :key="result.id">
                <v-list-item-content >
                    <a :href="result.link" style="display: flex; text-decoration: none">
                        <v-icon style="margin-left: 8px">mdi-magnify</v-icon>
                        <v-list-item-title class="article-search-results-title" style="margin-left: 4px">{{ result.title }}</v-list-item-title>
                    </a>
                </v-list-item-content>
            </v-list-item>
        </v-list>
        <v-container v-if="isUserLoggedIn">
            <v-row style="justify-content: space-between">
                <v-col cols="6" md="10" sm="6" style="display: flex;">
                    <v-col cols="6" md="3" sm="6">
                        <v-select v-model="selectedCategory" :items="categories" label="Category"></v-select>
                    </v-col>
                    <v-col cols="6" md="9" sm="6">
                        <AddArticle_Model v-if="isUserLoggedIn" class="mt-5"></AddArticle_Model>
                    </v-col>
                </v-col>


                <div md="2" style="margin-top: 30px">
                    <v-pagination v-model="page" :length="pagination" class="float-right" color="primary" @input="getArticlesByCity"></v-pagination>
                </div>
            </v-row>

            <div v-if="articleList[0]!=null">
                <v-row>
                    <v-col v-for="(item, index) in articleList" :key="index" cols="12" lg="4" md="6">
                        <v-card class="mx-auto" max-width="400">
                            <v-img :src="img_prefix + item.coverImage" class="white--text align-end" height="200px">
                                <div style="margin-bottom: 140px;margin-left: 14px">
                                    <v-icon v-if="item.status==true" size="36" color="accent">mdi-checkbox-marked-circle</v-icon>
                                    <v-icon v-else size="36" color="error">mdi-cancel</v-icon>
                                </div>
                            </v-img>
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
                        <v-skeleton-loader class="mx-auto" width="366" type="image,  article,actions">
                        </v-skeleton-loader>
                        <v-skeleton-loader class="mx-auto" width="366" type="image,  article,actions">
                        </v-skeleton-loader>
                        <v-skeleton-loader class="mx-auto" width="366" type="image,  article,actions">
                        </v-skeleton-loader>
                    </div>
                </v-sheet>
            </div>
        </v-container>

    </v-app>
</template>

<script>
import {mapState} from "vuex";
import AddArticle_Model from "@/views/Article/AddArticle-Model/AddArticle-Model.vue";

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
            showStates: {},
            searchQuery: '', // Add this data property
            searchResults: [], // Add this data property to store search results
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
        },
        handleSearch() {
            if (this.searchQuery.length > 2) {
                this.$api.searchByKeyWord({ content: this.searchQuery })
                .then(data => {
                    this.searchResults = data.data.data; // Store the search results
                })
                .catch(error => {
                    console.error("There was an error fetching the search results:", error);
                });
            }
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

<style>
* {
//outline: 1px solid red;
}
.article-search-results-list {
    top: 110px;
    width: 1140px;
    position: absolute;
    z-index: 10;
    margin-left: 16px;
    margin-top: -16px;
    border-top-left-radius: 25px !important;
    border-top-right-radius: 25px !important;
    border-bottom-left-radius: 25px !important;
    border-bottom-right-radius: 25px !important;
    overflow: hidden; /* Ensures the rounded corners stay constant */
}
.article-search-results-title {
    color: dimgrey;
    text-decoration: none; /* Corrected property name */
}

</style>