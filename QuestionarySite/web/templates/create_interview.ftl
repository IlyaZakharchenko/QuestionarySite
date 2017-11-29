<#ftl encoding="UTF-8">
<#include "header.ftl">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Mane</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
    <link rel="stylesheet" href="../css/top_menu.css">
    <link rel="stylesheet" href="../css/login_signin.css">
    <link rel="stylesheet" href="../css/create_question.css">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/jquery-ui-1.12.1/"></script>
    <script src="../js/bootstrap.js"></script>

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>

<body>

<@header></@header>

<div class="main-part">
    <div class="container">
        <div class="row">
            <div class="main-part-container">
                <p class="name-field"><label>Вопрос</label></p>
                <form action="/create" method="post" id="create">

                </form>
                <input form="create" class="col-md-8" type="text" name="question-name">
                <p class="name-field col-md-12"><label>Варианты ответа</label></p>
                <p class="col-md-12">Пустые варианты ответа не будут добавлены</p>
                <div id="answer1" class="answer">
                    <input form="create" oninput="" name="var1" type="text" class="var col-md-8">

                    <!--                        spizjeno-->
                    <div class="input-group image-preview col-md-1" id="preview1">
                        <button class="add-description col-md-12" href="#description1" data-toggle="modal" id="des1">Описание..</button>
                    </div>


                </div>



                <div id="description1" class="modal">
                    <div class="container-description container">
                        <div class="row">
                            <p>Добавление описания</p>
                            <#--<form action="">-->
                                <textarea form="create" id="description-text" placeholder="Введите описание" name="description1" class="col-md-12" rows="20"></textarea>
                                <p>Добавить ссылки</p>
                                <div id="links1" class="links-add">
                                    <input form="create" type="text" oninput="" name="link1_1" id="link1_1" placeholder="ссылки" class="col-md-12 add-link adds_1">
                                </div>
                                <button class="ok-btn pull-right" data-dismiss="modal" type="button">Добавить</button>
                            <#--</form>-->
                        </div>
                    </div>

                </div>


                <div class="col-md-12 check">
                    <p class="usual-text col-md-4">Выберете способ проверки</p>
                    <select form="create" name="typeCheck" class="col-md-8">
                        <option>Cookie</option>
                        <option selected>Без проверки</option>
                    </select>
                </div>
                <div class="can-comment col-md-12">
                    <p class="col-md-4 usual-text">Выберете кто сможет комментировать</p>
                    <select form="create" name="canComment" class="col-md-8">
                        <option>Пользователи</option>
                        <option>Все</option>
                    </select>
                </div>
                <div class="can-comment col-md-12">
                    <p class="col-md-4 usual-text">Выберете тему</p>
                    <select form="create" name="theme" class="col-md-8">
                        <option selected>Без темы</option>
                        <option>Работа</option>
                        <option>Машины</option>
                        <option>Школа</option>
                        <option>Образование</option>
                        <option>Техника</option>
                        <option>Интернет</option>
                        <option>Политика</option>
                        <option>Мода</option>
                    </select>
                </div>
                <div class="can-comment col-md-12">
                    <p class="col-md-4 usual-text">Анонимный</p>
                    <select form="create" name="anonim" class="col-md-8">
                        <option>Да</option>
                        <option>Нет</option>
                    </select>
                </div>


                <!--                   делаем вид, что забыли про это???-->



                <div class="create-btn">
                    <button form="create" type="submit" class="create pull-right ok-btn">Создать</button>
                </div>


            </div>
        </div>
    </div>
</div>

<script>
    var func = function addLinkScript() {
        func = addLinkScript;
        var item_des = $(this).attr("id").replace("link", "").split("_");
        var num_des = parseInt(item_des[1]) + 1;
        console.log(item_des);
        $(".adds_" + item_des[0]).last().after("<input form='create' type='text' name='link" + item_des[0] + "_" + num_des + "'  id='link" + item_des[0] + "_" + num_des + "' placeholder='ссылки' class='col-md-12 add-link adds_" + item_des[0] + "'>");


        $(this).unbind("input");
        console.log("i remov and add input link");
        $(".adds_" + item_des[0]).last().on("input", func);
        //                                $(".var").last().on("input", ter);
    }


    $('.add-link').bind("input", func);


    var item = 1;


    $('.var').bind("input", function ter() {
        item++;
        console.log(item);
        $(".answer").last().after("<div id='answer" + item + "' class='answer'>" +
                "<input oninput='' form='create' name='var" + item + "' type='text' class='var col-md-8'>" +
                "<div class='input-group image-preview col-md-1' id='preview" + item + "'>" +
                "<button class='add-description col-md-12' href='#description" + item + "' data-toggle='modal' id='des2'>Описание..</button>" +
                "</div>" +
                "</div>" +
                "<div id='description" + item + "' class='modal'>" +
                "<div class='container-description container'>" +
                "<div class='row'>" +
                "<p>add description</p>" +
//                "<form action=''>" +
                "<textarea form='create' id='description-text' placeholder='Введите описание'" + "name='description" + item + "' class='col-md-12' rows='20'></textarea>" +
                "<p>Добавить ссылки</p>" +
                "<div id='links" + item + "' class='links-add'>" +
                "<input form='create' type='text' oninput='' name='link"+item+"_1' id='link" + item + "_1' placeholder='ссылки' class='col-md-12 add-link adds_" + item + "'></div>" +
                "<button data-dismiss='modal' class='ok-btn pull-right' type='button'>Добавить</button>" +
//                </form>
                "</div></div></div>");


        $(this).unbind("input");
        console.log("i remov");
        $(".var").last().on("input", ter);
        $(".adds_" + item).last().on("input", func);
        console.log("oop");
    })


</script>



</body>

</html>