var Vue = require('vue')
var VueRouter = require('vue-router')

//------------------------
var home = require('./components/home.vue')
var login = require('./components/login.vue')
var registration = require('./components/registration.vue')
var user = require('./components/user/user.vue')
var userList = require('./components/user/userList.vue')
var bill = require('./components/bill/bill.vue')
var billList = require('./components/bill/billList.vue')
var transaction = require('./components/transaction.vue')
var nav = require('./components/nav.vue')
var store = require('./store/store.js')
var HTTP = require('./components/http-common.js')
//var HTTP = http.instance;
//------------------------

Vue.use(VueRouter)

var router = new VueRouter({
   //` mode: 'history'`,
    routes: [
        {
            path: '/home',
            components: 
            {
                default:home
            },
            name: 'home',
            alias: '/'
        },
        { 
            path: '/login', 
            name: 'login',
            component: login 
        },
        { 
            path: '/registration',
            name: 'registration', 
            component: registration 
        },
        {
            path: '/allbills',
            name: 'allBills',
            component: billList
        },
        { 
            path: '/user', 
            components: {
                default: user
            },
            children: [
                { 
                    path: ':id', 
                    component: user,
                    children: [
                        { 
                            path: 'bills', 
                            component: billList,
                            children: [
                                { path: ':bill_id', name: 'bill', component: bill },
                                { path: '', name: 'billList', component: billList }
                            ]
                        },
                        { path: 'transaction', name: 'transaction', component: transaction },
                        { 
                            path: '', 
                            name: 'user',
                            components: {
                                default: user
                            }
                        }
                    ]
                },
                { 
                    path: '/userlist', 
                    name: 'userList',
                    component: userList 
                }
            ]
        }
        
    ]
})

new Vue({
    el: '#app',
    router: router,
    store,
    components: {
        home, 
        login,    
        registration, 
        user, 
        userList, 
        bill, 
        billList, 
        transaction,
        navigation:nav,
        HTTP
    }
})