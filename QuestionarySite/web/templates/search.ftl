<#ftl encoding="UTF-8">
<#include "header.ftl">

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Mane</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/main_page.css">
    <link rel="stylesheet" href="../css/search.css">
    <link rel="stylesheet" href="../css/login_signin.css">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="../js/jquery-ui-1.12.1/"></script>
    <script src="../js/bootstrap.js"></script>
    <link href="../css/top_menu.css" rel="stylesheet">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>

<body>
<@header></@header>
<div class="main-part">

    <div class="container main-part-container">
        <div class="row">
            <div class="col-md-12 main-part-container">
                <div class="search-modul">
                    <form action="/search" method="post">
                        <input type="text" name="search_text">
                        <button class="pull-right"><img src="../img/search.png" alt="search" width=15px></button>

                    </form>
                    <!--                            <a href="#" title="big" onclick="kek()">Расширенный поиск</a>-->
                    <button data-toggle="collapse" data-target="#parametr">Расширенный поиск</button>
                    <div class="sublist collapse parametr-search" id="parametr">
                        <form action="#" name="search">
                        </form>
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
            <div class="col-md-12 interviews">
        <#if interviews??>
            <#if 1 <= interviews?size>
                <#list 0..interviews?size - 1 as i>
                    <div class="<#if i%2 != 0>col-md-offset-1</#if> col-md-5">
                        <a href="">
                            <div class="concrete-interview" id="23" style="background-color: aqua">
                                <div class="question-part">
                                    <div class="col-md-12 pull-right">
                                        <a href="/interview?id=${interviews[i].getId()}" class="usual-text col-md-11">${interviews[i].getQuestion()}</a>

                                    </div>

                                    <form action="" method="">
                                        <#if interviews[i].getOptions()??>
                                            <#assign options = interviews[i].getOptions()>
                                            <#if 1 <= options?size>
                                                <#list 0..options?size - 1 as j>
                                                    <div class="var-answer">
                                                        <input type="radio" name="q${i}" id="ans${i}_${j}"><label
                                                            href="#description${i}_${j}"
                                                            data-toggle="modal"
                                                            for="ans${i}_${j}">${options[j].getAnswer()}</label>
                                                        <br>
                                                    </div>
                                                </#list>
                                            </#if>
                                        </#if>
                                        <div class="col-md-12">
                                            <button type="submit" class="ok-btn pull-right">Проголосовать</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </a>
                    </div>
                </#list>
            <#else >
            <h3>Мы ничего не нашли. Это плохо, да?</h3>
            </#if>
        </#if>
            <script>
                $(document).on("mouseover", ".concrete-interview", function () {
                    $(this).css("box-shadow", "0 0 10px rgba(0,0,0,0.5)");
                    console.log("33");
                })

                $(document).on("mouseout", ".concrete-interview", function () {
                    $(this).css("box-shadow", "none");
                })

            </script>
        <#--<div class="col-md-5">-->
        <#--<a href="#">-->
        <#--<div class="concrete-interview" style="background-color: rgb(209, 196, 233);">-->
        <#--<div class="question-part">-->
        <#--<div class="col-md-12 pull-right">-->
        <#--<a href="#" class="usual-text col-md-11 head">Любите ли вы котиков?</a>-->

        <#--</div>-->

        <#--<form action="" method="">-->
        <#--<div class="var-answer">-->
        <#--<input type="radio" name="q2" id="ans2_1"><label data-toggle="modal"-->
        <#--for="ans2_1"> У меня у-->
        <#--самого лапки</label>-->
        <#--<br>-->
        <#--</div>-->
        <#--<div class="var-answer">-->
        <#--<input type="radio" name="q2" id="ans2_2">-->
        <#--<label data-toggle="modal" for="ans2_2">Разве что мемасики с ними</label>-->
        <#--<br/>-->
        <#--</div>-->
        <#--<div class="var-answer">-->
        <#--<input type="radio" name="q2" id="ans2_3"><label data-toggle="modal"-->
        <#--for="ans2_3">Котики спасут-->
        <#--нас, мяу</label><br>-->
        <#--</div>-->
        <#--<div class="col-md-12">-->
        <#--<button type="submit" class="ok-btn pull-right">Проголосовать</button>-->
        <#--</div>-->
        <#--</form>-->


        <#--</div>-->
        <#--</div>-->
        <#--</a>-->
        <#--</div>-->
        </div>

        </div>
    </div>

</div>

</body>

</html>