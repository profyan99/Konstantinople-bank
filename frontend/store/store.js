var Vue = require('vue')
var Vuex = require('vuex')
var axios = require('../components/http-common.js')
var qs = require('qs');

Vue.use(Vuex)

module.exports = new Vuex.Store({
    state: {
      	userid: '',
      	authorizated: false
    },
    getters: {
		user(state) {
			return state.userid;
		},
		authorization(state) {
			return state.authorizated;
		}
    },
    mutations: {
		set(state, { type, items }) {
			state[type] = items
		}
    },
    actions: {
      	login({ commit }, data) { 

		  	return axios.post('/login', qs.stringify({
				/*headers: {
					'Content-Type': 'application/x-www-form-urlencoded'
				},*/
				data
			}));
      	}  
    }
})
