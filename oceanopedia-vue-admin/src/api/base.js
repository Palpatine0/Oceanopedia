// base.js
const base = {
    baseUrl: "/api",

    // multimedia
    getBanners: '/multimedia/getBanners',
    updateBanners: '/multimedia/updateBanners',
    uploadImageNoPrefix: '/multimedia/uploadImageNoPrefix',

    //article
    getArticleByID: '/article/getArticleByID',
    addArticle: '/article/addArticle',
    deleteArticleByID: '/article/deleteArticleByID',
    getArticlesByCategory: '/article/getArticlesByCategory',
    updateArticleStatusById: '/article/updateArticleStatusById',
    updateArticleInfoById: '/article/updateArticleInfoById',
    updateArticleCoverImageById: '/article/updateArticleCoverImageById',
    getArticleByLikesRank: '/article/getArticleByLikesRank',
    getRecentArticleViews: '/article/getRecentArticleViews',

    // search
    searchByKeyWord: "/search/searchByKeyWord",

    // user
    login: '/user/login',
    register: '/user/register',
    getUsers: '/user/getUsers',
    deleteUserById: '/user/deleteUserById',
    getUserByUsername: '/user/getUserByUsername',
    getUserById: '/user/getUserById',

}

export default base;