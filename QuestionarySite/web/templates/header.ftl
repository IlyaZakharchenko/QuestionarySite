<#macro header>

<div class="top-menu">
    <div class="header">
        <div class="container top-container" id="top-header">
            <div class="row">
                <div class="col-md-2 text-center">
                    <a href="#" class="logo" title="Inservey">Inservey</a>
                </div>
                <div class="col-md-6">

                    <nav>
                        <ul class="list-inline pull-left menus-item">
                            <li class="<#--<#if cur_page == "">active </#if>-->"><a href="#" title="link">Главная</a>
                            </li>
                            <li class="<#--<#if cur_page == "">active </#if>-->"><a href="#" title="link">Популярное</a>
                            </li>
                            <li class="<#--<#if cur_page == "">active </#if>-->"><a href="#" title="link">Создать
                                опрос</a></li>
                            <li class="<#if cur_page == "about">active </#if>"><a href="/about" title="link">О сайте</a>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-2 log-in menus-item">
                    <#if user??>
                        <span>${user.username}</span>
                        <a href="" title="exit" onclick="exit()">Выйти</a>
                    <#else >
                        <a href="#sign-in-modal" title="log in" data-toggle="modal">Вход</a>
                        <img src="img/slash.png" width="6px">
                        <a href="#register-modal" title="registr" data-toggle="modal">Регистрация</a>
                    </#if>
                </div>
                <div class="col-md-2 text-center">
                    <form action="#" class="top-search">
                        <input type="text">
                        <button class="top-button-search"><img src="img/search.png" alt="search" width=100%></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div id="sign-in-modal" class="modal">
        <div class="modal-dialog">
            <div class="loginmodal-container">
                <h1>Вход в личный кабинет</h1><br>
                <div id="message_box"></div>
                <form method="post" id="signin_form" action="#">
                    <input id="signin_login" type="text" name="user" oninput="checker5()" placeholder="Логин">
                    <input id="signin_password" type="password" name="pass" oninput="checker6()" placeholder="Пароль">
                    <button id="signin_btn" name="login" type="button" onclick="signin()" class="btn btn-primary btn-lg btn-block login-button">Войти</button>
                </form>

                <div class="login-help">
                    <a href="#register-modal" title="registr" data-toggle="modal" data-dismiss="modal">Регистрация</a>
                    <!--                        - <a href="#">Забыли пароль</a>-->
                    <p><input type="checkbox" name="remember" id="rem"> <label for="rem" style="font-weight: lighter"
                                                                               class="remember-lab">Запомнить
                        меня</label></p>
                </div>
            </div>
        </div>
    </div>
    <div id="register-modal" class="modal">
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="#">
                <div id="msg_box_signup"></div>
                <div class="form-group">
                    <label for="name" class="cols-sm-2 control-label">Имя пользователя</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="name" id="name" oninput="checker4()"
                                   placeholder="Введите имя пользователя"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="cols-sm-2 control-label">Ваш Email</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
                            <input oninput="checker()" type="text" class="form-control" name="email" id="mail"
                                   placeholder="Введите Email"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="password" class="cols-sm-2 control-label">Пароль</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input type="password" oninput="checker2()" class="form-control" name="password" id="pass1"
                                   placeholder="Введите пароль"></div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="confirm" class="cols-sm-2 control-label">Подтвердите пароль</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
                            <input oninput="checker3()" type="password" class="form-control" name="confirm" id="pass2"
                                   placeholder="Подтвердите пароль"/>
                        </div>
                    </div>
                </div>

                <div class="form-group ">
                    <button type="button" onclick="signup()" class="btn btn-primary btn-lg btn-block login-button">
                        Регистрация
                    </button>
                </div>
                <div class="login-register">
                    <a href="#sign-in-modal" title="log in" data-toggle="modal" data-dismiss="modal">Вход</a>
                </div>
            </form>
        </div>
    </div>
    <script>

        var signin = function () {
            console.log("Signing In");
            $.ajax({
                type: "POST",
                url: "/ajax_check_login",
                data: {
                    "username": $("#signin_login").val(),
                    "password": $("#signin_password").val(),
                    "remember_me": $("#rem").is(":checked")
                },
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    var messageBox = $("#message_box").html("");
                    var error = false;
                    if (result.error_msg) {
                        messageBox.append("<p>Неверный логин или пароль.</p>")
                        error = true;
                    }
                    if (result.username_pattern_err) {
                        messageBox.append("<p>Имя пользователя должно начинаться с заглавной буквы и может содержать только латинские символы, символ '_' и цифры.");
                        $("#name").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (result.pass_pattern_err) {
                        messageBox.append("<p>Пароль может содержать только латинские символы, символ '_' и цифры.");
                        $("#pass1").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (!error) {
                        window.location.reload();
                        console.log("Reloading page");
                    }
                },
                error: function (jqXHR, exception) {
                    console.log("So");
                    if (jqXHR.status === 0) {
                        alert('No connection.\n Verify Network.');
                    } else if (jqXHR.status === 404) {
                        alert('[404] Page not found.');
                    } else if (jqXHR.status === 500) {
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

        var signup = function () {
            console.log("Signing Up");
            $.ajax({
                type: "POST",
                url: "/ajax_sign_up",
                data: {
                    "username": $("#name").val(),
                    "email": $("#mail").val(),
                    "password1": $("#pass1").val(),
                    "password2": $("#pass2").val()
                },
                dataType: "json",
                success: function (result) {
                    console.log(result);
                    var messageBox = $("#msg_box_signup").html("");
                    var error = false;
                    if (result.email_pattern_err) {
                        messageBox.append("<p>Email должен оканчиваться на @gmail.com, @yandex.ru или @mail.ru.</p>");
                        $("#mail").attr("style", "border: 1px solid red;");
                        error = true;
                    }

                    if (result.email_exists_err) {
                        messageBox.append("<p>Email занят.</p>");
                        $("#mail").attr("style", "border: 1px solid red;");
                        error = true;
                    }

                    if (result.username_pattern_err) {
                        messageBox.append("<p>Имя пользователя должно начинаться с заглавной буквы и может содержать только латинские символы, символ '_' и цифры.");
                        $("#name").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (result.pass_pattern_err) {
                        messageBox.append("<p>Пароль может содержать только латинские символы, символ '_' и цифры.");
                        $("#pass1").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (result.user_exists_err) {
                        messageBox.append("<p>Пользователь с таким именем уже существует.</p>");
                        $("#name").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (result.pass_length_err) {
                        messageBox.append("<p>Пароль слишком короткий.</p>");
                        $("#pass1").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (result.pass_not_equal_err) {
                        messageBox.append("<p>Пароли не совпадают.</p>");
                        $("#pass1").attr("style", "border: 1px solid red;");
                        $("#pass2").attr("style", "border: 1px solid red;");
                        error = true;
                    }
                    if (!error) {
                        window.location.reload();
                        console.log("Reloading page");
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

        var exit = function () {
            $.ajax({
                url: "/ajax_exit",
                data: {},
                dataType: "json",
                success: function (result) {
                    window.location.reload();
                },
                error: function (jqXHR, exception) {
                    if (jqXHR.status === 0) {
                        alert('No connection.\n Verify Network.');
                    }
                    else if (jqXHR.status === 404) {
                        alert('[404] Page not found.');
                    } else if (jqXHR.status === 500) {
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
                },
            });
        }

        var checker = function () {
            var text = document.getElementById("mail").value;
            if (text == "") {
                $("#mail").attr("style", "border: auto;");
            }
            else if ((text.search(/@yandex.ru|@gmail.com|@mail.ru/)) == -1) {
                $("#mail").attr("style", "border: 1px solid red;")
            }
            else {
                $("#mail").attr("style", "border: 1px solid green;")
            }
        };

        var checker2 = function () {
            var pass = document.getElementById("pass1").value;
            var messageBox = $('#msg_box_signup').html("");
            if (pass.length < 6) {
                messageBox.append("<p>Слишком короткий пароль.</p>");
                $("#pass1").attr("style", "border: 1px solid red;")
            }
            else if (pass.search(/\W/) != -1) {
                messageBox.append("<p>Пароль может содержать только латинские символы, символ '_' и цифры.</p>");
                $("#pass1").attr("style", "border: 1px solid red;");
            }
            else {
                $("#pass1").attr("style", "border: auto;");
            }
        };

        var checker3 = function () {
            var text = document.getElementById("pass1").value;

            var text2 = document.getElementById("pass2").value;

            if (text2 == "") {
                $("#pass2").attr("style", "border: auto;");
            }
            else if (text != text2) {
                $("#pass2").attr("style", "border: 1px solid red;")
            }
            else {
                $("#pass2").attr("style", "border: 1px solid green;")
            }
        };

        var checker4 = function () {
            console.log("Checking username");
            var messageBox = $('#msg_box_signup').html("");
            var name = document.getElementById("name").value;
            if (name.search(/[A-Z]\W/) != -1) {
                messageBox.append("<p>Имя пользователя должно начинаться с заглавной буквы и может содержать только латинские символы, символ '_' и цифры.</p>");
                $("#name").attr("style", "border: 1px solid red;");
            }
            else {
                $("#name").attr("style", "border: auto;");
            }
        };

        var checker5 = function () {
            console.log("Checking username");
            var messageBox = $('#message_box').html("");
            var name = document.getElementById("signin_login").value;
            if (name.search(/[A-Z]\W/) != -1) {
                messageBox.append("<p>Имя пользователя должно начинаться с заглавной буквы и может содержать только латинские символы, символ '_' и цифры.</p>");
                $("#signin_login").attr("style", "border: 1px solid red;");
            }
            else {
                $("#signin_login").attr("style", "border: auto;");
            }
        };

        var checker6 = function () {
            console.log("Checking username");
            var messageBox = $('#message_box').html("");
            var name = document.getElementById("signin_password").value;
            if (name.search(/\W/) != -1) {
                messageBox.append("<p>Пароль может содержать только латинские символы, символ '_' и цифры.</p>");
                $("#signin_password").attr("style", "border: 1px solid red;");
            }
            else {
                $("#signin_password").attr("style", "border: auto;");
            }
        };

    </script>
</div>

</#macro>