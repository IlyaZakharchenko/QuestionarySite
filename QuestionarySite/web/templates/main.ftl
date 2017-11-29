<#ftl encoding="UTF-8"/>
<#include "header.ftl">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Mane</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" href="../css/main_page.css">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <link href="../css/bootstrap.css" rel="stylesheet" type="text/css">
    <script src="../js/jquery-ui-1.12.1/"></script>
    <script src="../js/bootstrap.js"></script>
    <link href="../css/login_signin.css" rel="stylesheet" type="text/css">
    <link href="../css/top_menu.css" rel="stylesheet" type="text/css">


    <!-- Website Font style -->
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>

<body>
<@header></@header>

<#--<#if open_signin??>
<script>
    (function () {
        $('#sign-in-modal').modal().show();
    })();
</script>
</#if>-->

<div class="main-part">
    <div class="container main-part-container">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner">

                <div class="item active">
                    <img src="img/online-interview.jpg" alt="interview" style="width:100%; height: 550px;">
                    <div class="carousel-caption">
                        <!--          -->
                    </div>
                </div>

                <div class="item">
                    <img src="img/online_interview2.jpg" alt="interview" style="width:100%; height: 550px;">
                    <div class="carousel-caption">
                        <!--          -->
                    </div>
                </div>

                <div class="item">
                    <img src="img/online-interview4.png" alt="interview" style="width:100%; height: 550px;">
                    <div class="carousel-caption">
                        <!--        -->
                    </div>
                </div>

            </div>

            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <script>
        $(document).ready(function () {
            $('#myCarousel').carousel({
                interval: 50;
        })
        });
    </script>
    <div class="container main-part-container">
        <div class="row">
            <div class="col-md-12 main-part-container">
                <div class="search-modul">
                    <form action="/search" id="search" method="post">
                        <input type="text" name="search_text">
                        <button class="pull-right"><img src="img/search.png" alt="search" width=15px></button>

                    </form>
                    <!--                            <a href="#" title="big" onclick="kek()">Расширенный поиск</a>-->
                    <button data-toggle="collapse" data-target="#parametr">Расширенный поиск</button>
                    <div class="sublist collapse parametr-search" id="parametr">
                        <p>Выберете темы:</p>
                        <div class="col-md-3">
                            <ul>
                                <li><input form="search" type="checkbox" name="work" id="1">
                                    <label for="1">Работа</label></li>
                                <li><input form="search" type="checkbox" name="car" id="2">
                                    <label for="2">Машины</label></li>

                            </ul>
                        </div>
                        <div class="col-md-3">
                            <ul>
                                <li><input form="search" type="checkbox" name="scool" id="3">
                                    <label for="3">Школа</label></li>
                                <li><input form="search" type="checkbox" name="education" id="4">
                                    <label for="4">Образование</label></li>
                            </ul>
                        </div>
                        <div class="col-md-3">
                            <ul>
                                <li><input form="search" type="checkbox" name="technic" id="5">
                                    <label for="5">Техника</label></li>
                                <li><input form="search" type="checkbox" name="ethernet" id="6">
                                    <label for="6">Интернет</label></li>
                            </ul>
                        </div>
                        <div class="col-md-3">
                            <ul>
                                <li><input form="search" type="checkbox" name="politic" id="7">
                                    <label for="7">Политика</label></li>
                                <li><input form="search" type="checkbox" name="fashion" id="8">
                                    <label for="8">Мода</label></li>
                            </ul>
                        </div>
                        <p>Анонимность</p>
                        <select form="search">
                            <option value="none">Не имеет значения</option>
                            <option value="selected">Анонимный</option>
                            <option value="no selected">Не анонимный</option>

                        </select>

                        <div class="done col-md-12">
                            <input form="search" type="checkbox" id="done-interview">
                            <label for="done-interview">Пройденные</label>
                        </div>

                        <div class="col-md-12">
                            <button form="search" type="submit" class="pull-right ok-button">Поиск</button>
                        </div>
                    </div>

                </div>


            </div>
            <p class="them-name text-center">Вы можете пройти опросы на множество тем</p>
            <div class="thems">


                <div class="col-md-3 them-column">
                    <form method="post" action="/search?work=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()" style="background-color: bisque;"><p class="text-center">
                                Работа</p></div>
                        </a>
                    </form>

                    <form method="post" action="/search?car=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()" style="background-color: #80DEEA;"><p class="text-center">
                                Машины</p></div>
                        </a>
                    </form>
                </div>


                <div class="col-md-3 them-column">
                    <form method="post" action="/search?scool=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()" style="background-color: #D1C4E9;"><p class="text-center">
                                Школа</p></div>
                        </a>
                    </form>

                    <form method="post" action="/search?education=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()" style="background-color: #B9F6CA;"><p class="text-center">
                                Образование</p></div>
                        </a>
                    </form>
                </div>
                <div class="col-md-3 them-column">
                    <form method="post" action="/search?technic=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()"
                                 style="background-color: #FFCCBC;"><p class="text-center">Техника</p></div>
                        </a>
                    </form>

                    <form method="post" action="/search?ethernet=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()"
                                 style="background-color: #FFECB3;"><p class="text-center">Интернет</p></div>
                        </a>
                    </form>
                </div>
                <div class="col-md-3 them-column">
                    <form method="post" action="/search?politic=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()"
                                 style="background-color: #F3E5F5;"><p class="text-center">Политика</p></div>
                        </a>
                    </form>

                    <form method="post" action="/search?fashion=true">
                        <a href="javascript:" onclick="parentNode.submit()">
                            <div id="concrete-them1" class="concrete-them" onmouseover="mouseon()"
                                 onmouseout="mouseoff()"
                                 style="background-color: bisque;"><p class="text-center">Мода</p></div>
                        </a>
                    </form>
                </div>

                <script>
                    $('.concrete-them').mouseover(function () {

                        $(this).css("box-shadow", "0 0 10px rgba(0,0,0,0.5)");
                        console.log(23)
                    });

                    $('.concrete-them').mouseout(function () {
                        $(this).css("box-shadow", "none");
                    })


                </script>

            </div>
            <p class="them-name text-center">Создать свой собственный опрос </p>
            <div class="image-create">
                <div class="create">
                    <!--                        <img src="img/checklist.jpg">-->
                    <button onclick="window.location.href='/create'">Создать опрос</button>
                </div>
            </div>
            <div class="about-us">
                <p></p>
            </div>
        </div>
    </div>
</div>
</body>

</html>