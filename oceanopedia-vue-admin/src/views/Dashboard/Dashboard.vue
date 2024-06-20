<template>
    <v-app :style="{ padding: isUserLoggedIn ? '20px' : '0px', minHeight: '100vh' }">
        <v-subheader v-if="isUserLoggedIn" as="h1" class="subheading grey--text">Dashboard</v-subheader>

        <v-container>
            <v-app-bar color="rgba(0,0,0,0)" flat>
                <v-text-field label="Search ..." class="pt-5" filled prepend-inner-icon="mdi-magnify" dense solo flat background-color="grey lighten-4" rounded></v-text-field>
                <v-spacer></v-spacer>
            </v-app-bar>

            <v-row>
                <!-- Left -->
                <v-col cols="12" sm="8">
                    <v-card class="mb-5 mx-4 rounded-xl pa-4" color="grey lighten-3" flat>
                        <v-row>
                            <v-col cols="12" sm="8">
                                <v-list-item three-line>
                                    <v-list-item-content>
                                        <v-list-item-title class="text-h4 mb-1">
                                            Hey {{ user.username }}
                                        </v-list-item-title>
                                        <v-list-item-subtitle>
                                            Welcome back {{ user.username }}. We are glad you're here.<br>
                                            Inspire the best work in people, enabling them to achieve
                                        </v-list-item-subtitle>
                                        <v-list-item-subtitle>
                                            their goals.
                                        </v-list-item-subtitle>
                                    </v-list-item-content>
                                </v-list-item>
                            </v-col>
                        </v-row>
                    </v-card>

                    <v-toolbar flat color="rgba(0,0,0,0)" dense class="mt-n1 ">
                        <v-toolbar-title>Trending Articles</v-toolbar-title>
                        <v-spacer></v-spacer>
                    </v-toolbar>

                    <v-row class="mt-n5">
                        <v-col cols="12" sm="4">
                            <v-card class="rounded-xl ml-4" color="grey lighten-3" flat>
                                <v-card-title>
                                    <span class="text-h6 font-weight-light">Prototyping</span>
                                </v-card-title>

                                <v-card-text class="">
                                    A prototype is an early <br>sample, model, or release of a product built to test a..
                                </v-card-text>

                                <v-card-actions class="mt-n7">
                                    <v-list-item class="">
                                        <div color="grey darken-3">
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/1.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/3.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/4.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/2.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" color="black" size="30">
                                                <span class="caption white--text">+1</span>
                                            </v-avatar>
                                        </div>

                                        <v-row align="center" justify="end">
                                            <v-icon class="mr-1" small>
                                                fas fa-hands-wash
                                            </v-icon>
                                            <span class="caption">1.5 K</span>
                                        </v-row>
                                    </v-list-item>
                                </v-card-actions>
                            </v-card>
                        </v-col>

                        <v-col cols="12" sm="4">
                            <v-card class="rounded-xl" color="error" dark elevation="10">
                                <v-card-title>
                                    <span class="text-h6 font-weight-light">Typography</span>
                                </v-card-title>

                                <v-card-text class="">
                                    In essence, typography is the art of arranging letters and text in a way that..
                                </v-card-text>

                                <v-card-actions class="mt-n7">
                                    <v-list-item class="">
                                        <div color="grey darken-3">
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/1.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/3.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/4.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/2.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" color="white" size="30">
                                                <span class="caption black--text">+2</span>
                                            </v-avatar>
                                        </div>
                                        <v-row align="center" justify="end">
                                            <v-icon class="mr-1" small>
                                                fas fa-hands-wash
                                            </v-icon>
                                            <span class="caption">1.2 K</span>
                                        </v-row>
                                    </v-list-item>
                                </v-card-actions>
                            </v-card>
                        </v-col>

                        <v-col cols="12" sm="4">
                            <v-card class="rounded-xl mr-4" color="grey lighten-3" flat>
                                <v-card-title>
                                    <span class="text-h6 font-weight-light">Color Palette</span>
                                </v-card-title>

                                <v-card-text class="">
                                    The best way to use a scheme like this is to use one color as the primary..
                                </v-card-text>

                                <v-card-actions class="mt-n7">
                                    <v-list-item class="">
                                        <div color="grey darken-3">
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/1.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/3.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/4.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" size="30">
                                                <img src="https://cdn.vuetifyjs.com/images/lists/2.jpg" alt="John">
                                            </v-avatar>
                                            <v-avatar class="ml-n3" color="black" size="30">
                                                <span class="caption white--text">+1</span>
                                            </v-avatar>
                                        </div>
                                        <v-row align="center" justify="end">
                                            <v-icon class="mr-1" small>
                                                fas fa-hands-wash
                                            </v-icon>
                                            <span class="caption">1.2 K</span>
                                        </v-row>
                                    </v-list-item>
                                </v-card-actions>
                            </v-card>
                        </v-col>
                    </v-row>

                    <v-card class="mx-4 rounded-xl pa-6 mt-5" color="grey lighten-3" flat>
                        <v-toolbar flat color="rgba(0,0,0,0)" dense class="mt-n5">
                            <v-toolbar-title>Analytics</v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-btn-toggle v-model="text" small dense background-color="primary" dark>
                            </v-btn-toggle>
                        </v-toolbar>

                        <v-sparkline
                                :fill="true"
                                :gradient="['#f72047', '#ffd200', '#1feaea']"
                                :line-width="2"
                                :padding="8"
                                :smooth="10 || false"
                                :value="value"
                                auto-draw
                        ></v-sparkline>
                    </v-card>
                </v-col>

                <!-- Right -->
                <v-col cols="12" sm="4">
                    <v-card class="rounded-xl pa-4" color="secondary" flat>

                        <v-app-bar shaped elevation="0">
                            <v-btn fab small elevation="0">
                                <v-icon color="black">fas fa-book</v-icon>
                            </v-btn>
                            <strong class="subtitle black--text ml-2">
                                Articles Posted <span class="caption"><br>80</span>
                            </strong>
                            <v-spacer></v-spacer>
                            <v-icon color="grey" class="ml-2">fas fa-chevron-circle-right</v-icon>
                        </v-app-bar>

                        <v-app-bar shaped class="

mt-3" elevation="0">
                            <v-btn tile fab small elevation="0">
                                <v-icon color="black">fas fa-hands-wash</v-icon>
                            </v-btn>
                            <strong class="subtitle black--text ml-2">
                                Total Likes<span class="caption"><br>1.5k</span>
                            </strong>
                            <v-spacer></v-spacer>
                            <v-icon color="grey" class="ml-2">fas fa-chevron-circle-right</v-icon>
                        </v-app-bar>

                        <v-app-bar shaped class="mt-3" elevation="0">
                            <v-btn tile fab small elevation="0">
                                <v-icon color="black">fas fa-user-friends</v-icon>
                            </v-btn>
                            <strong class="subtitle black--text ml-2">
                                Total Users <span class="caption"><br>972</span>
                            </strong>
                            <v-spacer></v-spacer>
                            <v-icon color="grey" class="ml-2">fas fa-chevron-circle-right</v-icon>
                        </v-app-bar>

                        <div class="text-center mt-5 mb-5">
                            <v-date-picker v-model="date2" :event-color="date => date[9] % 2 ? 'red' : 'yellow'" :events="functionEvents"></v-date-picker>
                        </div>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import { mapState } from "vuex";

export default {
    components: {},
    data() {
        return {
            page: 1,
            pagination: 0,
            totalCount_item: 0,
            articleList: [],
            showStates: {},
            articleA: {},
            articleB: {},
            articleC: {},
            value: [0, 2, 5, 9, 5, 10, 3, 5, 0, 0, 1, 8, 2, 9, 0],
            date2: new Date().toISOString().substr(0, 10),
            isUserLoggedIn: false, // Assume some logic to check if the user is logged in
        };
    },
    methods: {
        functionEvents(date) {
            const [, , day] = date.split("-");
            if ([12, 17, 28].includes(parseInt(day, 10))) return true;
            if ([1, 19, 22].includes(parseInt(day, 10))) return ["red", "#00f"];
            return false;
        },
        fetchArticles() {
            this.api
            .getArticleByLikesRank({ rank: 1 })
            .then((res) => {
                this.articleA = res.data.data;
                console.log("this.articleA", this.articleA);
            })
            .catch((error) => {
                console.error("Error fetching articleA:", error);
            });

            this.api
            .getArticleByLikesRank({ rank: 2 })
            .then((res) => {
                this.articleB = res.data.data;
                console.log("this.articleB", this.articleB);
            })
            .catch((error) => {
                console.error("Error fetching articleB:", error);
            });

            this.api
            .getArticleByLikesRank({ rank: 3 })
            .then((res) => {
                this.articleC = res.data.data;
                console.log("this.articleC", this.articleC);
            })
            .catch((error) => {
                console.error("Error fetching articleC:", error);
            });
        },
    },
    mounted() {
        console.log("GOOO");
        this.fetchArticles();
    },
    computed: {
        ...mapState(["category", "categories", "img_prefix", "user"]),
    },
};
</script>

<style>
* {
    /* outline: 1px solid red; */
}
</style>