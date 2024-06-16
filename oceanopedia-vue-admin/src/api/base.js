// base.js
const base = {
    baseUrl: "/api",

    // file
    getBanner: '/file/getBanner',
    uploadImageNoPrefix: '/file/uploadImageNoPrefix',

    // trendy
    getTrendy: '/trendy/getTrendy',

    // recommendation
    getRecommendation: "/recommendation/getRecommendation",

    //item
    getItemByID: '/item/getItemByID',
    addItem: '/item/addItem',
    deleteItemByID: '/item/deleteItemByID',
    getItemsByCity: '/item/getItemsByCity',
    updateItemStatusById: '/item/updateItemStatusById',
    updateItemInfoById: '/item/updateItemInfoById',
    updateItemShowcasesById: '/item/updateItemShowcasesById',


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
    getUser: '/user/getUsers',
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