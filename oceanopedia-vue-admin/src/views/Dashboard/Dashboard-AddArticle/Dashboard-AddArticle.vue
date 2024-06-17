<template>
    <div>
        <v-container>
            <v-row justify="center">
                <v-dialog v-model="addItem_dialog" max-width="600px" persistent>
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
                                        <v-text-field v-model="item.title" label="Title" required></v-text-field>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-text-field v-model="item.author" label="Author" required></v-text-field>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-textarea outlined label="Content" v-model="item.content"></v-textarea>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-textarea outlined label="Summary" v-model="item.summary"></v-textarea>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="item.status" :items="['DRAFT', 'PUBLISHED']" label="Status" required></v-select>
                                    </v-col>
                                    <v-col cols="12" md="6" sm="6">
                                        <v-select v-model="item.category" :items="categories" label="Category" required></v-select>
                                    </v-col>
                                    <v-col cols="12">
                                        <div v-if="item.coverImage != ''">
                                            <v-img :src="img_prefix + item.coverImage" aspect-ratio="2"></v-img>
                                        </div>
                                        <v-file-input show-size truncate-length="50" @change="file => uploadImageNoPrefix(file)" label="Cover Image"></v-file-input>
                                    </v-col>
                                    <v-col cols="12">
                                        <v-select v-model="item.tags" :items="['tag1', 'tag2', 'tag3']" label="Tags" multiple></v-select>
                                    </v-col>
                                </v-row>
                            </v-container>
                        </v-card-text>
                        <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="blue darken-1" text @click="addItem_dialog = false">
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
        <v-snackbar v-model="addItem_snackbar" :timeout="2000">
            {{ addItem_msg }}
        </v-snackbar>
        <v-snackbar v-model="uploadItemShowcases_add_snackbar" :timeout="2000">
            {{ uploadItemShowcases_add_msg }}
        </v-snackbar>
    </div>
</template>

<script>
import { mapState } from "vuex";

export default {
    data() {
        return {
            // item
            item: {
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
            // page vars
            uploadItemShowcases_add_msg: '',
            uploadItemShowcases_add_snackbar: false,
            addItem_dialog: false,
            addItem_msg: '',
            addItem_snackbar: false
        }
    },
    methods: {
        addItem() {
            const now = new Date().toISOString();

            this.$api.addArticle({
                title: this.item.title,
                content: this.item.content,
                author: this.item.author,
                summary: this.item.summary,
                views: 0,
                likes: 0,
                status: this.item.status,
                publicationDate: now,
                updatedDate: now,
                category: this.item.category,
                coverImage: this.item.coverImage,
                tags: this.item.tags
            })
            .then((data) => {
                console.log(data)
                if (data.data.status == 200) {
                    this.addItem_dialog = false;
                    this.addItem_msg = data.data.data;
                    this.addItem_snackbar = true
                }
            })
        },
        uploadImageNoPrefix(file) {
            this.$api.uploadImageNoPrefix({file: file})
            .then((data) => {
                if (data.data.status === 200 && data.data.data) {
                    this.item.coverImage = data.data.data;
                    this.uploadItemShowcases_add_snackbar = true;
                    this.uploadItemShowcases_add_msg = data.data.msg;
                } else {
                    throw new Error('Failed to upload image or bad data received');
                }
            })
        },
    },
    computed: {
        ...mapState(['categories', 'img_prefix']),
    }
}
</script>

<style scoped>
</style>