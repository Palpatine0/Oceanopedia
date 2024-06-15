module.exports = {
    devServer: {
        port: 3013,
        proxy: {
            '/api': {
                target: 'http://localhost:4007',
                changeOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    transpileDependencies: [
        'vuetify'
    ]
}
