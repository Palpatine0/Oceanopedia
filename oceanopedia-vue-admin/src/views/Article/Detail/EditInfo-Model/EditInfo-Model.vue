<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="editInfo_dialog" max-width="600px" persistent>
                    <v-card>
                        <v-card-title>
                            <span class="text-h5">Article Info</span>
                        </v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12">
                                        <v-text-field v-model="articleInfo.title" label="Title" required></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="8" sm="6">
                                        <v-text-field v-model="articleInfo.author" label="Author" required></v-text-field>
                                    </v-col>
                                    <v-col cols="12" md="4" sm="6">
                                        <v-select v-model="articleInfo.category" :items="categories" label="Category" required></v-select>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-textarea outlined label="Content" v-model="articleInfo.content"></v-textarea>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-textarea outlined label="Summary" v-model="articleInfo.summary"></v-textarea>
                                    </v-col>
                                    <v-col cols="12">
                                        <div v-if="articleInfo.coverImage != ''">
                                            <v-img :src="img_prefix + articleInfo.coverImage" aspect-ratio="2"></v-img>
                                        </div>
                                        <v-file-input show-size truncate-length="50" @change="file => uploadImageNoPrefix(file, 'coverImage')" label="Cover Image"></v-file-input>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-select v-model="articleInfo.tags" :items="tagsOptions" label="Tags" multiple></v-select>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="editInfo_dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="updateArticleInfoById">
                                Submit
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </v-container>

        <v-snackbar v-model="editArticleInfo_snackbar" :timeout="2000">
            {{ editArticleInfo_msg }}
        </v-snackbar>

        <v-snackbar v-model="uploadArticleCoverImage_snackbar" :timeout="2000">
            {{ uploadArticleCoverImage_msg }}
        </v-snackbar>

        <v-snackbar v-model="editArticleInfoError_snackbar" :timeout="2000" color="error">
            {{ editArticleInfoError_msg }}
        </v-snackbar>

    </div>
</template>

<script>
import {mapState} from "vuex";

export default {
    data() {
        return {
            // page vars
            editArticleInfo_msg: '',
            editArticleInfo_snackbar: false,

            uploadArticleCoverImage_msg: '',
            uploadArticleCoverImage_snackbar: false,

            editArticleInfoError_msg: '',
            editArticleInfoError_snackbar: false,

            tagsOptions: ['tag1', 'tag2', 'tag3'],
        }
    },
    methods: {
        updateArticleInfoById() {
            this.$api.updateArticleInfoById({
                id: this.articleInfo.id,
                title: this.articleInfo.title,
                content: this.articleInfo.content,
                author: this.articleInfo.author,
                views: this.articleInfo.views,
                likes: this.articleInfo.likes,
                publicationDate: this.articleInfo.publicationDate,
                summary: this.articleInfo.summary,
                status: this.articleInfo.status,
                category: this.articleInfo.category,
                coverImage: this.articleInfo.coverImage,
                tags: this.articleInfo.tags,
            })
            .then((data) => {
                if (data.data.status === 200) {
                    this.editArticleInfo_msg = data.data.msg;
                    this.editArticleInfo_snackbar = true;
                    this.editInfo_dialog = false;
                    setTimeout(() => {
                        window.location.reload();
                    }, 1200);
                } else {
                    throw new Error(data.data.error);
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 500) {
                    this.editArticleInfoError_msg = error.response.data.error;
                    this.editArticleInfoError_snackbar = true;
                }
            });
        },
        uploadImageNoPrefix(file) {
            this.$api.uploadImageNoPrefix({file: file})
            .then((response) => {
                const data = response.data;
                if (data.status === 200 && data.data) {
                    this.articleInfo.coverImage = data.data;
                    this.uploadArticleCoverImage_msg = data.msg;
                    this.uploadArticleCoverImage_snackbar = true;
                } else {
                    this.editArticleInfoError_msg = data.error || 'An error occurred';
                    this.editArticleInfoError_snackbar = true;
                }
            })
            .catch((error) => {
                if (error.response && error.response.status === 500) {
                    this.editArticleInfoError_msg = error.response.data.error || 'Server error';
                    this.editArticleInfoError_snackbar = true;
                }
            });
        },
        assignImgUrls() {
            if (this.articleInfo && this.articleInfo.coverImage) {
                this.articleInfo.coverImage = this.articleInfo.coverImage || '';
            }
        },
    },
    props: ['editInfo_dialog', 'articleInfo'],

    computed: {
        ...mapState(['categories', 'img_prefix']),
    },
    created() {
        this.assignImgUrls();
    },
}
</script>

<style scoped>

</style>