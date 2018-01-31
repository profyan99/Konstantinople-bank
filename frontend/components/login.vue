<template>
<div class="col-2 login offset-5 voffset">
    <div>
            <h2>Authorization</h2>
    </div>
    <div>
        <form>
            <div class="form-group">
                <label for="inputLogin">Login</label>
                <input v-model="login" type="text" class="form-control" id="inputLogin" placeholder="Enter login">
            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">Password</label>
                <input v-model="password" type="password" class="form-control" aria-describedby="loginHelp" id="exampleInputPassword1" placeholder="Password">
                <small id="loginHelp" class="form-text text-muted">{{error}}</small>
            </div>
            <button @click.prevent="onLogin" class="btn btn-primary">Submit</button>
        </form>
    </div>
</div>
</template>


<script>
var axios = require('./http-common.js')
var qs = require('qs');


module.exports = {
    name:'login',
    data: function() {
        return {
            login: '',
            password:'',
            error:'',
            req: {}
        }
    },
    methods: {
        onLogin: function () {
            if(this.login === '' || this.password === '') {
                this.error = 'Fields must not be empty';
            }
            else {
               /* this.$store.dispatch('login', 
                {
                    username: this.login,
                    password: this.password
                })*/
                axios.post('/login', qs.stringify(
                {
                    username: this.login,
                    password: this.password
                }))
                .then(response => {
                    if(response.status == 200) {
                        this.$store.commit('set', { type: 'userid', items: response.data });
                        this.$store.commit('set', { type: 'authorizated', items: true });
                        this.$router.push('home');
                        console.log("[DEBUG] Success, ID: ", this.$store.getters.user);
                    }
                    else if(response.status == 401) {
                        this.error = 'Bad login or password';
                    }
                    else {
                        this.error = 'Unexpected error. Please contact with administration of this website';
                    }

                })
                .catch(e => {
                    this.error = 'Unable to connect to server';
                })
            }
        }
    }
}
</script>