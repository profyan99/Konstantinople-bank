<template>
    <div class="home">
         <header>
            <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                <!-- Slide One - Set the background image for this slide in the line below -->
                <div class="carousel-item active" style="background-image: url('http://placehold.it/1900x1080')">
                    <div class="carousel-caption d-none d-md-block">
                    <h3>Первый слайд</h3>
                    <p>Тут ничего((</p>
                    </div>
                </div>
                <!-- Slide Two - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url('http://placehold.it/1900x1080')">
                    <div class="carousel-caption d-none d-md-block">
                    <h3>Второй слайд</h3>
                    <p>Блин, тут тоже ничего((</p>
                    </div>
                </div>
                <!-- Slide Three - Set the background image for this slide in the line below -->
                <div class="carousel-item" style="background-image: url('http://placehold.it/1900x1080')">
                    <div class="carousel-caption d-none d-md-block">
                    <h3>Третий слайд</h3>
                    <p>Думал, что что-то изменится? Ха, наивный.</p>
                    </div>
                </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
                </a>
            </div>
            </header>

            <!-- Page Content -->
            <div class="container">

                <h1 class="my-4">Добро пожаловать в бездну технологий и обмана!</h1>


                <!-- Features Section -->
                <div class="row">
                    <div class="col-lg-6">
                        <h2>Современная банковская штука</h2>
                        <p>Актуальные технологии, фичи, конфетки, а также:</p>
                        <ul>
                            <li>
                            <strong>Банковские счета</strong>
                            </li>
                            <li>Банковские транзакции</li>
                            <li>Большая клиентская база(1 клиент)</li>
                            <li>Бесплатное использование</li>
                            <li>Чуть-чуть платные печеньки</li>
                        </ul>
                        <p>Это все даст вам на самом деле ничего полезного, бегите отсюда, пока можете. Автор этого сайта полный дурак, он крадет ваши пароли и сам съедает все печеньки!!!</p>
                    </div>
                    <div class="col-lg-6">
                        <img class="img-fluid rounded" src="images/pech.jpg" alt="">
                    </div>
                </div>
                <!-- /.row -->

                <hr>

                <!-- Call to Action Section -->
                <div class="row mb-4">
                    <div class="col-md-6 align-middle">
                        <p>Чтобы воспользоваться нашим афигенным банком, вам нада зарегаться.</p>
                    </div>
                    <div class="col-6">
                        <div class="row">
                            <div class="col-9">
                                <router-link :to="{ name: 'registration' }" class="btn btn-secondary btn-block">
                                    Регистрация
                                </router-link>
                            </div>
                            <div class="col-3">
                                <router-link :to="{ name: 'login' }" class="btn btn-primary btn-block">
                                    Войти
                                </router-link>
                            </div>
                        </div>
                    </div>
                    
                </div>

            </div>
            <!-- /.container -->

            <!-- Footer -->
            

    </div>
</template>

<script>
    module.exports = {
        name: 'home',
        data: function() {
            return {}
        }
    }
</script>

<style>
</style>
