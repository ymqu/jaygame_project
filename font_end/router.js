import VueRouter from 'vue-router';

import HomeContainer from './components/tabbar/HomeContainer.vue'
import AboutContainer from './components/tabbar/AboutContainer.vue';
import CommunityContainer from './components/tabbar/CommunityContainer.vue'
import GameContainer from './components/tabbar/GameContainer.vue'
import NewsContainer from './components/tabbar/NewsContainer.vue'
import UserProfile from './components/user/UserProfile.vue'
import UserLogin from './components/user/UserLogin.vue'
import UserRegistration from './components/user/UserRegistration.vue'
import GameDetail from './components/game/gameDetail.vue'
import Search from './components/search/SearchContainer.vue'


var router = new VueRouter({
    routes: [
        { path: '/home', component: HomeContainer },
        { path: '/about', component: AboutContainer },
        { path: '/community', component: CommunityContainer },
        { path: '/game', component: GameContainer },
        { path: '/news', component: NewsContainer },
        { path: '/user/profile', component: UserProfile },
        // {
        //     path: '/user/profile',
        //     component: UserProfile,
        //     meta: { requiresAuth: true },
        //     beforeEnter: (to, from, next) => { guard(to, from, next) }
        // },
        { path: '/user/login', component: UserLogin },
        { path: '/user/registration', component: UserRegistration },

        { path: '/game/gameDetail', component: GameDetail },
        { path: '/search', component: Search },



    ],
    linkActiveClass: 'mui-active',

})

// const guard = function(to, from, next) {
//     let token = window.localStorage.getItem('token')
//     console.log('token :', token);
//     if (to.matched.some(record => record.meta.requiresAuth) && (!token || token === null)) {
//         next({
//             path: '/user/login',
//             query: { redirect: to.fullPath }
//         })
//     } else {
//         next()
//         console.log('next() :', next());
//     }
// }


export default router