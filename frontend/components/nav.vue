<template>
  <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container">
            <router-link :to="{ name: 'home' }" class="navbar-brand">
                <img src="images/logo.png" alt="logo">
            </router-link>
            <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item active">
                        <router-link :to="{ name: 'home' }" class="nav-link">
                            Главная
                            <span class="sr-only">(current)</span>
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link :to="{ name: 'userList' }" class="nav-link">
                            Клиенты
                        </router-link>
                    </li>
                    <li class="nav-item" v-if="authorizated">
                        <router-link :to="{ name: 'user', params: { id: clientid } }" class="nav-link">
                            Профиль
                        </router-link>
                    </li>
                    <li class="nav-item dropdown">
                        <!--<router-link :to="{ name: 'billList' }" class="nav-link dropdown-toggle" 
                        id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Счет
                        </router-link>-->
                        <a href="#" class="nav-link dropdown-toggle" 
                        id="navbarDropdownBlog" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Счет
                        </a>
                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownBlog">
                            <router-link :to="{ name: 'user', params: { id: clientid } }" class="dropdown-item">
                                Новый счет
                            </router-link>
                            <router-link :to="{ name: 'billList', params: { id: clientid } }" class="dropdown-item">
                                Мои счета
                            </router-link>
                            <div class="dropdown-divider"></div>
                            <router-link :to="{ name: 'allBills' }" class="dropdown-item">
                                Все счета
                            </router-link>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>
<script>
module.exports = {
    data: function() {
        return{

        }
    },
    computed: {
        clientid () {
            return this.$store.getters.user;
        },
        authorizated() {
            return this.$store.getters.authorization;
        }
    }
}
</script>

