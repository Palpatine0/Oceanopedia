<template>
    <v-app>
        <v-subheader as="h1" class="subheading grey--text">Banner</v-subheader>

        <!-- Swiper Section -->
        <v-container>
            <Swiper :slides="banners"/>
        </v-container>

        <!-- Call-to-Action Section -->
        <v-container class="my-5">
            <v-row>
                <v-col cols="12" md="8">
                    <h2 class="headline">Effortless Banner Management for Maximum Impact</h2>
                    <p>
                        Streamline banner creation and management with our intuitive tools. Design, schedule, and monitor banners to engage your audience effectively. Our platform ensures your messages stand out and drive results. Register now to elevate your brand's impact.
                    </p>
                </v-col>
                <v-col md="1"></v-col>
                <v-col md="2" class="mt-8">
                    <v-btn color="accent" x-large @click="editBanner_dialog = true" rounded>
                        Manage The Banners
                    </v-btn>
                </v-col>
            </v-row>
        </v-container>

        <edit-banner-model :banners="banners" :editBanner_dialog="editBanner_dialog" @banners-updated="getBanners"/>
    </v-app>
</template>

<script>
import { mapState } from "vuex";
import Swiper from "@/components/Swiper.vue";
import EditBannerModel from "@/views/Banner/editBanner-Model/editBanner-Model.vue";

export default {
    components: {
        Swiper,
        EditBannerModel
    },
    data() {
        return {
            editBanner_dialog: false,
            banners: []
        };
    },
    methods: {
        getBanners() {
            this.$api.getBanners().then(data => {
                if (data.data.status === 200) {
                    this.banners = data.data.results;
                } else {
                    alert('Banner unavailable');
                }
            });
        }
    },
    created() {
        this.getBanners();
    },
    computed: {
        ...mapState(["category", "categories", "img_prefix", "user"])
    }
};
</script>

<style>
.swiper-slide img {
    width: 100%;
    height: auto;
    border-radius: 10px;
}
</style>
