// index.js
import base from "./base"
import qs from "querystring"
import axios from 'axios'


const api = {

    // multimedia
    getBanner() {
        return axios.get(base.baseUrl + base.getBanner)
    },
    uploadImageNoPrefix(file) {
        return axios.post(base.baseUrl + base.uploadImageNoPrefix, file, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
    },

    //
    getRecommendation(params) {
        return axios.get(base.baseUrl + base.getRecommendation + "?city=" + params.city);
    },
    hotProduct(params) {
        return axios.get(base.baseUrl + base.getTrendy + "?city=" + params.city);
    },

    // search
    searchByKeyWord(params) {
        return axios.get(base.baseUrl + base.searchByKeyWord + "?" + qs.stringify(params));
    },
    searchByCity(params) {
        return axios.get(base.baseUrl + base.searchByCity + "?" + qs.stringify(params));
    },
    adminSearchByCity(params) {
        return axios.get(base.baseUrl + base.adminSearchByCity + "?" + qs.stringify(params));
    },

    //
    buytime(params) {
        return axios.get(base.baseUrl + base.getBuyTime + '?id=' + params.id);
    },
    buyaction(params) {
        return axios.get(base.baseUrl + base.buyAction + "?" + qs.stringify(params));
    },

    // item
    getArticleByID(params) {
        return axios.get(base.baseUrl + base.getArticleByID + "?" + qs.stringify(params));
    },
    addArticle(params) {
        return axios.post(base.baseUrl + base.addArticle, qs.stringify(params));
    },
    deleteArticleByID(params) {
        return axios.delete(base.baseUrl + base.deleteArticleByID + "?" + qs.stringify(params));
    },
    getArticlesByCategory(params) {
        return axios.get(base.baseUrl + base.getArticlesByCategory + "?" + qs.stringify(params));
    },
    updateArticleStatusById(params) {
        return axios.post(base.baseUrl + base.updateArticleStatusById + "?" + qs.stringify(params));
    },
    updateArticleInfoById(params) {
        return axios.post(base.baseUrl + base.updateArticleInfoById + "?" + qs.stringify(params));
    },
    updateArticleCoverImageById(params) {
        return axios.post(base.baseUrl + base.updateArticleCoverImageById + "?" + qs.stringify(params));
    },


    // comment
    addComment(params) {
        return axios.post(base.baseUrl + base.feedback, qs.stringify(params));
    },
    getComment(params) {
        return axios.get(base.baseUrl + base.comment + "?" + qs.stringify(params));
    },


    // user
    login(params) {
        return axios.post(base.baseUrl + base.login, qs.stringify(params));
    },
    register(params) {
        return axios.post(base.baseUrl + base.register, qs.stringify(params));
    },
    deleteUserById(params) {
        return axios.post(base.baseUrl + base.deleteUserById, qs.stringify(params));
    },
    getUsers(params) {
        return axios.get(base.baseUrl + base.getUsers+ "?" + qs.stringify(params));
    },
    getUserByUsername(params) {
        return axios.get(base.baseUrl + base.getUserByUsername + "?" + qs.stringify(params));
    },
    getUserById(params) {
        return axios.get(base.baseUrl + base.getUserById + "?" + qs.stringify(params));
    },

    // order
    getOrder(params) {
        return axios.get(base.baseUrl + base.order + "?" + qs.stringify(params));
    },


}

export default api;