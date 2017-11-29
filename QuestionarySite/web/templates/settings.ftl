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
    <link rel="stylesheet" href="../css/change_account.css">

    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/jquery-ui-1.12.1/"></script>
    <script src="../js/bootstrap.js"></script>

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>

<body>
<@header></@header><div class="container main-part-container">
    <div class="row">
        <div class="col-md-3 interface-part">
            <div id="ava">
                <img src="<#if user.getImgPath()??>/load/${user.getImgPath()}<#else >../img/slash.png</#if>" alt="avatar" width="100%">
                <div class="change avatar">

                    <form method="post" action="/img_change" id="add_image" enctype="multipart/form-data">
                        <div class="col-md-12 input-change">
                            <!-- image-preview-filename input [CUT FROM HERE]-->
                            <div class="input-group image-preview">
                                <input type="text" class="form-control image-preview-filename" disabled="disabled">
                                <!-- don't give a name === doesn't send on POST/GET -->
                                <span class="input-group-btn">
                    <!-- image-preview-clear button -->
                    <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
                        <span class="glyphicon glyphicon-remove"></span> Удалить
                    </button>
                                    <!-- image-preview-input -->
                        <div class="btn btn-default image-preview-input">
                            <span class="glyphicon glyphicon-folder-open"></span>
                            <span class="image-preview-input-title">Изменить</span>
                            <input type="file" accept="image/png, image/jpeg, image/gif" name="profile_img" />
                            <!-- rename it -->
                        </div>
                </span>

                            </div>
                            <!-- /input-group image-preview [TO HERE]-->
                        </div>
                        <input type="submit" form="add_image">
                    </form>
                </div>

            </div>
        </div>
        <div class="col-md-9 information-part">
            <div class="information">
                <div class="general">
                    <div class="col-md-12 text-center header">
                        <label>Редактирование</label>
                    </div>
                    <div id="settings_box"></div>
                    <div class="sername col-md-12">
                        <label class="usual-text col-md-3">Ваша почта</label>
                        <input id="mail" form="change-form" type="text" oninput="checker()" name="email" value="<#if user.getEmail()??>${user.getEmail()}<#else ></#if>">
                    </div>
                    <div class="sername col-md-12">
                        <label class="usual-text col-md-3">Возраст</label>
                        <input id="age" form="change-form" type="text" oninput="checker()" name="age" value="<#if user.getAge()??>${user.getAge()}<#else >0</#if>">
                    </div>
                    <div class="sername col-md-12">
                        <label class="usual-text col-md-3">О себе</label>
                        <input form="change-form" type="text" name="about_yourself" value="<#if user.getAboutYourself()??>${user.getAboutYourself()}<#else ></#if>">

                        <form id="change-form" method="post" action="/account_settings">
                            <div class="col-md-offset-5">
                                <button type="submit" class="ok-btn">Изменить</button>
                            </div>
                        </form>

                    </div>

                    <hr />
                </div>
                <div class="dangarous">
                    <label class="col-md-12 usual-text">Если вы хотите изменить пароль, то для подтверждения нужно ввести действующий</label>
                    <div id="setings_pass_box"></div>
                    <div class="new-password">
                        <label class="usual-text col-md-3">Новый пароль</label>
                        <input form="pass-change-form" type="password"  id = "new_pass" name="newPassword" oninput="checker3();checker2()">
                    </div>
                    <div class="new-password">
                        <label class="usual-text col-md-3">Повторите пароль</label>
                        <input oninput="checker3(); checker2()" form="pass-change-form" id= "repeat_pass" type="password" name="repeatePassword">
                    </div>
                    <div class="new-password">
                        <label class="usual-text col-md-3">Старый пароль</label>
                        <input form="pass-change-form" oninput="checker3();checker2()" id="old_pass" type="password" name="lastPassword">
                    </div>
                     <div class="col-md-offset-5">
                            <button type="submit" class="ok-btn" onclick="change_pass()">Изменить</button>
                        </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    var checker = function() {
        console.log("i'm here");
        var text = document.getElementById("mail").value;
        console.log("and read " + text + "  " + text.search(/@yandex.ru|@gmail.com|@mail.ru/));
        if (text == "") {
            $("#mail").attr("style", "border: auto;");
        }
        else if ((text.search(/@yandex.ru|@gmail.com|@mail.ru/)) == -1) {
            $("#mail").attr("style", "border: 1px solid red;")
        }
        else {
            $("#mail").attr("style", "border: 1px solid green;")
        }
    }
    $(document).on('click', '#close-preview', function(){
        $('.image-preview').popover('hide');
        // Hover befor close the preview
        $('.image-preview').hover(
                function () {
                    $('.image-preview').popover('show');
                },
                function () {
                    $('.image-preview').popover('hide');
                }
        );
    });

    var checker2 = function () {
        var pass = document.getElementById("new_pass").value;
        var messageBox = $("#setings_pass_box").html("");
        if (pass.length < 6) {
            messageBox.append("<p>Слишком короткий пароль.</p>");
            $("#new_pass").attr("style", "border: 1px solid red;")
        }
        else if (pass.search(/\W/) != -1) {
            messageBox.append("<p>Пароль может содержать только латинские символы, символ '_' и цифры.</p>");
            $("#new_pass").attr("style", "border: 1px solid red;");
        }
        else {
            $("#new_pass").attr("style", "border: auto;");
        }
    };

    var checker3 = function () {
        var text = document.getElementById("new_pass").value;

        var text2 = document.getElementById("repeat_pass").value;

        var text3 = document.getElementById("old_pass")

        var messageBox = $("#setings_pass_box").html("");

        if (text != text2) {
            $("#repeat_pass").attr("style", "border: 1px solid red;");
            messageBox.append("<p>Пароли не совпадают.</p>");
        }
        else if (text === text3) {
            $("#new_pass").attr("style", "border: 1px solid red;");
            messageBox.append("<p>Старый и новый пароль не должны совпадать.</p>");
        }
        else {
            $("#new_pass").attr("style", "border: 1px solid green;");
            $("#repeat_pass").attr("style", "border: 1px solid green;");
        }
    };

    var checker4 = function () {
        console.log("Checking username");
        var messageBox = $('#settings_box').html("");
        var name = document.getElementById("username").value;
        if (name.search(/[A-Z]\W/) != -1) {
            messageBox.append("<p>Имя пользователя должно начинаться с заглавной буквы и может содержать только латинские символы, символ '_' и цифры.</p>");
            $("#username").attr("style", "border: 1px solid red;");
        }
        else {
            $("#username").attr("style", "border: auto;");
        }
    };

    var change_pass = function () {
        console.log("Changing pass");
        $.ajax({
            type: "POST",
            url: "/ajax_change_pass",
            data: {
                "newPass": $("#new_pass").val(),
                "repeatPass": $("#repeat_pass").val(),
                "oldPass": $("#old_pass").val(),
            },
            dataType: "json",
            success: function (result) {
                console.log(result);
                var messageBox = $("#setings_pass_box").html("");
                var error = false;
                if (result.pass_pattern_err) {
                    messageBox.append("<p>Пароль может содержать только латинские символы, символ '_' и цифры.");
                    $("#new_pass").attr("style", "border: 1px solid red;");
                    error = true;
                }
                if (result.pass_length_err) {
                    messageBox.append("<p>Пароль слишком короткий.</p>");
                    $("#new_pass").attr("style", "border: 1px solid red;");
                    error = true;
                }

                if (result.old_new_equal_err) {
                    messageBox.append("<p>Старый и новый пароль не должны совпадать.</p>");
                    $("#new_pass").attr("style", "border: 1px solid red;");
                    error = true;
                }
                if (result.pass_not_equal_err) {
                    messageBox.append("<p>Пароли не совпадают.</p>");
                    $("#new_pass").attr("style", "border: 1px solid red;");
                    $("#repeat_pass").attr("style", "border: 1px solid red;");
                    error = true;
                }
                if (result.wrong_pass_err) {
                    messageBox.append("<p>Старый пароль введен неверно.</p>");
                    $("#old_pass").attr("style", "border: 1px solid red;");
                }
                if (!error) {
                    window.location.href="/profile?id=${user.getId()}";
                }
            },
            error: function (jqXHR, exception) {
                if (jqXHR.status === 0) {
                    alert('No connection.\n Verify Network.');
                } else if (jqXHR.status == 404) {
                    alert('[404] Page not found.');
                } else if (jqXHR.status == 500) {
                    alert('[500] Internal server error.');
                } else if (exception === 'parsererror') {
                    alert('Requested JSON parse failed.');
                } else if (exception === 'timeout') {
                    alert('Time out error.');
                } else if (exception === 'abort') {
                    alert('Ajax request aborted.');
                } else {
                    alert('Unknown Error.\n' + jqXHR.responseText);
                }
            }
        });
    };


    $(function() {
        // Create the close button
        var closebtn = $('<button/>', {
            type:"button",
            text: 'x',
            id: 'close-preview',
            style: 'font-size: initial;',
        });
        closebtn.attr("class","close pull-right");
        // Set the popover default content
        $('.image-preview').popover({
            trigger:'manual',
            html:true,
            title: "<strong>Предпросмотр</strong>"+$(closebtn)[0].outerHTML,
            content: "There's no image",
            placement:'bottom'
        });
        // Clear event
        $('.image-preview-clear').click(function(){
            $('.image-preview').attr("data-content","").popover('hide');
            $('.image-preview-filename').val("");
            $('.image-preview-clear').hide();
            $('.image-preview-input input:file').val("");
            $(".image-preview-input-title").text("Изменить");
        });
        // Create the preview image
        $(".image-preview-input input:file").change(function (){
            var img = $('<img/>', {
                id: 'dynamic',
                width:250,
                height:200
            });
            var file = this.files[0];
            var reader = new FileReader();
            // Set preview image into the popover data-content
            reader.onload = function (e) {
                $(".image-preview-input-title").text("Изменить");
                $(".image-preview-clear").show();
                $(".image-preview-filename").val(file.name);
                img.attr('src', e.target.result);
                $(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
            }
            reader.readAsDataURL(file);
        });
    });
</script>
</body>
