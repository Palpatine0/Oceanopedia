// base.js
const base = {
    baseUrl: "/api",

    // multimedia
    getBanners: '/multimedia/getBanners',
    updateBanners: '/multimedia/updateBanners',
    uploadImageNoPrefix: '/multimedia/uploadImageNoPrefix',

    // trendy
    getTrendy: '/trendy/getTrendy',

    // recommendation
    getRecommendation: "/recommendation/getRecommendation",

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


    // comment
    comment: '/comment/getComment',
    feedback: "/comment/addComment",


    // search
    searchByKeyWord: "/search/searchByKeyWord",
    searchByCity: "/search/searchByCity",
    adminSearchByCity: "/search/adminSearchByCity",

    // user
    login: '/user/login',
    register: '/user/register',
    getUsers: '/user/getUsers',
    deleteUserById: '/user/deleteUserById',
    getUserByUsername: '/user/getUserByUsername',
    getUserById: '/user/getUserById',

    // buyAction
    buyAction: "/buyAction/buyAction",

    // buytime
    getBuyTime: '/buyTime/getBuyTime',

    // order
    order: "/order/getOrders"
}

export default base;