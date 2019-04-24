//start file

import Vue from 'vue'

import VueResource from 'vue-resource'
Vue.use(VueResource)



import VueSession from 'vue-session'
Vue.use(VueSession)

import VueRouter from 'vue-router'
Vue.use(VueRouter)
import router from './router.js'

import { Header } from 'mint-ui'
import { Swipe, SwipeItem } from 'mint-ui'
import 'mint-ui/lib/style.css'
Vue.component(Header.name, Header)
Vue.component(Swipe.name, Swipe)
Vue.component(SwipeItem.name, SwipeItem)

import './lib/mui/css/mui.min.css'
import './lib/mui/css/icons-extra.css'

//Star rating component
import StarRating from 'vue-star-rating'
Vue.component('star-rating', StarRating)


import app from './App.vue'

var vm = new Vue({
    el: '#app',
    render: c => c(app),
    router,


})


// pass token to server with format: "Authorization: Bearer 'token"
Vue.http.interceptors.push((request, next) => {
    if (window.localStorage.getItem('token')) {
        Vue.http.headers.common['Authorization'] = 'Bearer ' + window.localStorage.getItem('token')
    }
    next((response) => {
        if (response.status === 401) {
            window.localStorage.removeItem('token');
            this.$router.push({ path: "/home" });
        }
    })
})

//http, post use json format
//Vue.http.options.emulateJSON = true;

//format for time
import moment from 'moment'
Vue.filter('dateFormat', function(datestr, pattern = "YYYY-MM-DD HH:mm:ss") {
    return moment(datestr).format(pattern)
})

window.addEventListener('beforeunload', () => {
    window.localStorage.removeItem('token');
})