<template>
    <v-app :style="{ padding: isUserLoggedIn ? '20px' : '0px', minHeight: '100vh' }">
        <v-subheader as="h1" class="subheading grey--text">Banner</v-subheader>
        <Swiper :slides="swiperSlides"></Swiper>
    </v-app>
</template>

<script>
import {mapState} from "vuex";

export default {
    components: {},
    data() {
        return {
            swiperSlides: []
        };
    },
    methods: {},
    mounted() {
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