<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="addArticle_dialog" max-width="600px" persistent>
                    <template v-slot:activator="{ on, attrs }">
                        <v-btn color="secondary" style="color: #2a566b" v-bind="attrs" v-on="on">
                            Add an Article for this Category
                        </v-btn>
                    </template>
                    <v-card>
                        <v-card-title>
                            <span class="text-h5">Add an Article</span>
                        </v-card-title>
                        <v-card-text>
                            <v-container>
                                <v-row>
                                    <v-col cols="12">
                                        <v-text-field v-model="articleInfo.title" label="Title" required></v-text-field>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-text-field v-model="articleInfo.author" label="Author" required></v-text-field>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-textarea outlined label="Content" v-model="articleInfo.content"></v-textarea>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-textarea outlined label="Summary" v-model="articleInfo.summary"></v-textarea>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                            <v-select v-model="articleInfo.status" :items="statusOptions" label="Status" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="articleInfo.category" :items="categories" label="Category" required></v-select>
                                    </v-col>
                                    <v-col cols="12">
                                        <div v-if="articleInfo.coverImage != ''">
                                            <v-img :src="img_prefix + articleInfo.coverImage" aspect-ratio="2"></v-img>
                                        </div>
                                        <v-file-input show-size truncate-length="50" @change="file => uploadImageNoPrefix(file)" label="Cover Image"></v-file-input>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-select v-model="articleInfo.tags" :items="['tag1', 'tag2', 'tag3']" label="Tags" multiple></v-select>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="addArticle_dialog = false">
                                Close
                            </v-btn>
                            <v-btn color="blue darken-1" text @click="addItem">
                                Submit
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>
            </v-row>
        </v-container>

        <v-snackbar v-model="addArticle_snackbar" :timeout="2000">
            {{ addArticle_msg }}
        </v-snackbar>
        <v-snackbar v-model="uploadArticleImage_snackbar" :timeout="2000">
            {{ uploadArticleImage_msg }}
        </v-snackbar>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    data() {
        return {
            // item
            articleInfo: {
                title: '',
                content: '',
                author: '',
                summary: '',
                status: '',
                publicationDate: '',
                updatedDate: '',
                category: this.$store.state.category,
                coverImage: '',
                tags: []
            },
            statusOptions: [
                { text: 'DRAFT', value: false },
                { text: 'PUBLISHED', value: true }
            ],
            // page vars
            uploadArticleImage_msg: '',
            uploadArticleImage_snackbar: false,
            addArticle_dialog: false,
            addArticle_msg: '',
            addArticle_snackbar: false
        }
    },
    methods: {
        addItem() {
            const now = new Date().toISOString();

            this.$api.addArticle({
                title: this.articleInfo.title,
                content: this.articleInfo.content,
                author: this.articleInfo.author,
                summary: this.articleInfo.summary,
                views: 0,
                likes: 0,
                status: this.articleInfo.status,
                publicationDate: now,
                updatedDate: now,
                category: this.articleInfo.category,
                coverImage: this.articleInfo.coverImage,
                tags: this.articleInfo.tags
            })
            .then((data) => {
                console.log(data)
                if (data.data.status == 200) {
                    this.addArticle_dialog = false;
                    this.addArticle_msg = data.data.data;
                    this.addArticle_snackbar = true
                }
            })
        },
        uploadImageNoPrefix(file) {
            this.$api.uploadImageNoPrefix({file: file})
            .then((data) => {
                if (data.data.status === 200 && data.data.data) {
                    this.articleInfo.coverImage = data.data.data;
                    this.uploadArticleImage_snackbar = true;
                    this.uploadArticleImage_msg = data.data.msg;
                } else {
                    throw new Error('Failed to upload image or bad data received');
                }
            })
        },
    },
    computed: {
        ...mapState(['categories', 'img_prefix']),
        statusAsBoolean: {
            get() {
                return this.articleInfo.status === 'PUBLISHED';
            },
            set(isPublished) {
                this.articleInfo.status = isPublished ? 'PUBLISHED' : 'DRAFT';
            }
        },
    }

}
</script>

<style scoped>
</style>