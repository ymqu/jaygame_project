const path = require('path')

//export html-webpack-plugins which generate html page in the memory.
const htmlWebpackPlugin = require("html-webpack-plugin")


//JS file, export configuration file by Node.js module operator.
module.exports = {
    mode: "development",
    entry: "./src/main.js",
    output: {
        path: path.resolve(__dirname, './dist'),
        filename: 'bundle.js'
    },
    plugins: [
        new htmlWebpackPlugin({
            template: path.resolve(__dirname, './src/index.html'), //generate memory page base on index.html
            filename: 'index.html' //new file name
        }),

    ],
    module: {
        rules: [
            { test: /\.css$/, use: ['style-loader', 'css-loader'] }, //third-party regulation
            { test: /\.less$/, use: ['style-loader', 'css-loader', 'less-loader'] },
            { test: /\.scss$/, use: ['style-loader', 'css-loader', 'sass-loader'] },
            { test: /\.jpg|png|gif|bmp|jpeg$/, use: 'url-loader?limit=7631&name=[hash:8]-[name].[ext]' },
            { test: /\.eot|svg|ttf|woff|woff2/, use: 'url-loader' },
            { test: /\.vue$/, use: 'vue-loader' },
        ]
    },
    resolve: {
        // alias: { 'vue$': 'vue/dist/vue.esm.js' }
    },

}