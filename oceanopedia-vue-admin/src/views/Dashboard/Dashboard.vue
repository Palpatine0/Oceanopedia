<template>
    <v-app :style="{ padding: isUserLoggedIn ? '20px' : '0px', minHeight: '100vh' }">
        <v-subheader v-if="isUserLoggedIn" as="h1" class="subheading grey--text">Dashboard</v-subheader>

    </v-app>
</template>

<script>
import {mapState} from "vuex";

export default {
    components: {
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

<style>
* {
//outline: 1px solid red;
}
</style>