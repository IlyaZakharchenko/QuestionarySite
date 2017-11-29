<#ftl encoding="UTF-8"/>
<#include "header.ftl">
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Mane</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../css/top_menu.css" type="text/css">
    <link rel="stylesheet" href="../css/login_signin.css" type="text/css">
    <link rel="stylesheet" href="../css/yourself_account.css" type="text/css">
    <link rel="stylesheet" href="../css/description.css" type="text/css">

    <script src="../js/jquery-3.2.1.min.js"></script>
    <script src="../js/jquery-ui-1.12.1/"></script>
    <script src="../js/bootstrap.js"></script>

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css"
          type="text/css">

</head>

<body>
<@header></@header>
<div class="main-part-container container">
    <div class="row">
        <div class="interface-part col-md-3">
            <div class="interface">
                <img src="<#if owner.getImgPath()??>/load/${owner.getImgPath()}<#else>../img/slash.png</#if>"
                     class="col-md-12">
                <hr/>
                <p><a href="#" class="col-md-12 text-center"><#if cur_page == "my_profile">Мои опросы<#else>Созданные
                    опросы</#if></a></p>
            <#if cur_page == "my_profile">
                <hr/>
                <p><a href="#" class="col-md-12 text-center">Заархивированные опросы</a></p>
            </#if>
                <hr/>
                <p><a href="#" class="col-md-12 text-center">Пройденные опросы</a></p>
            <#if cur_page == "my_profile">
                <hr/>
                <p><a href="/create" class="col-md-12 text-center">Создать опрос</a></p>
                <hr/>
                <p><a href="/account_settings" class="col-md-12 text-center">Редактировать</a></p>
                <hr/>
            </#if>
            </div>
        </div>
        <div class="content-part col-md-9">
            <div class="information">
                <div class="information-part">
                    <p class="name usual-text">${owner.getUsername()}</p>
                    <p class="age usual-text">Возраст: ${owner.getAge()} лет</p>
                    <p class="interes usual-text">О
                        себе: <#if owner.getAboutYourself()??>${owner.getAboutYourself()}<#else></#if></p>

                </div>
            </div>
            <div class="questions-part">
                <!--                   Вот эту часть нужно генерить с овер довига условиями)) Спец в варинтах ответа перебраны все возможные комбинации-->
            <#if owner.getInterviews()??>
                <#assign interviews = owner.getInterviews()>
                <#if 1 <= interviews?size>
                    <#list 0..interviews?size - 1 as i>
                        <div class="question">
                            <div class="question-part result">
                                <div class="col-md-12 pull-right">
                                    <a href="/interview?id=${interviews[i].getId()}" class="usual-text col-md-11">${interviews[i].getQuestion()}</a>
                                    <#if cur_page == "my_profile">
                                    <div class="col-md-1 dropdown">
                                        <img src="../img/menu.png" width="15px" class="dropdown-toggle pull-right"
                                             data-toggle="dropdown">
                                        <ul class="dropdown-menu" role="menu">
                                            <li><a href="#">Заархивировать</a></li>
                                            <li><a href="#">Удалить</a></li>
                                        </ul>
                                    </div>
                                    </#if>

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
                                <a data-toggle="collapse" data-target="#comments${i}" class="col-md-8">Комментарии(<#if interviews[i].getCommentaries()??>${interviews[i].getCommentaries()?size}<#else >0</#if>)</a>

                            </div>
                            <#if options?? && 1 <= options?size>
                                <#list 0..options?size - 1 as j>
                                    <div id="description${i}_${j}" class="modal">
                                        <div class="container-description container">
                                            <div class="row">
                                                <p class="head">${options[j].getAnswer()}</p>
                                                <p class="desc">${options[j].getDescription()}</p>

                                                <div class="links">
                                                    <#if options[j].getLinks()??>
                                                        <#assign links = options[j].getLinks()>
                                                        <#if 1 <= links?size>
                                                            <#list 0..links?size - 1 as k>
                                                                <a href="${links[k]}"
                                                                   class="concrete-link">${links[k]}</a><br/>
                                                            </#list>
                                                        </#if>
                                                    </#if>
                                                </div>
                                                <div class="done">
                                                    <button class="pull-right ok-btn" data-dismiss="modal">Проголосовать
                                                    </button>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                </#list>
                            </#if>
                        </div>
                        <div class="sublist collapse comments" id="comments${i}">
                            <#if interviews[i].getCommentaries()??>
                                <#assign comments = interviews[i].getCommentaries()>
                                <#if 1 <= comments?size>
                                    <#list 0.. comments?size - 1 as p>
                                        <div class="row">
                                            <div class="col-md-1">
                                                <div class="thumbnail">
                                                    <img class="img-responsive user-photo"
                                                         src="<#if comments[p].getUser()?? && (comments[p].getUser().getImgPath()??)>/load/${comments[p].getUser().getImgPath()}<#else>https://ssl.gstatic.com/accounts/ui/avatar_2x.png</#if>"
                                                         width="45px"
                                                         height="45px">
                                                </div>
                                                <!-- /thumbnail -->
                                            </div>
                                            <!-- /col-sm-1 -->

                                            <div class="col-md-11">
                                                <div class="panel panel-default">
                                                    <div class="panel-heading">
                                                        <a href="/profile?id=${comments[p].getUserId()}"><strong><#if comments[p].getUser()?? && (comments[p].getUser().getUsername())??>${comments[p].getUser().getUsername()}<#else>Аноним</#if></strong></a>
                                                        <#--<span class="text-muted"> 5 дней назад</span>-->
                                                    </div>
                                                    <div class="panel-body">
                                                        <#if comments[p].getContent()??>${comments[p].getContent()}<#else></#if>
                                                    </div>
                                                    <!-- /panel-body -->
                                                </div>
                                                <!-- /panel panel-default -->
                                            </div>
                                            <!-- /col-sm-5 -->
                                        </div>
                                    </#list>
                                </#if>
                            </#if>

                            <div class="myComment">
                                    <div class="col-md-1 ava">
                                        <div class="thumbnail">
                                            <img class="img-responsive user-photo"
                                                    src=<#if user?? && (user.getImgPath()??)>/load/${user.getImgPath()}<#else>https://ssl.gstatic.com/accounts/ui/avatar_2x.png</#if>>
                                        </div>
                                        <!-- /thumbnail -->
                                    </div>
                                    <textarea form="leave_comment_form${i}" name="content" class="col-md-11" rows="3"
                                              placeholder="Оставьте комментарий..."></textarea>
                                    <div>
                                        <form id="leave_comment_form${i}" method="post" action="/add_my_comment?id=${interviews[i].getId()}">
                                        <button type="submit" class="ok-btn">Добавить</button>
                                        </form>
                                    </div>
                            </div>
                        </div>
                    </#list>
                </#if>
            </#if>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $(".dropdown").hover(
                function () {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true, true).slideDown("fast");
                    $(this).toggleClass('open');
                },
                function () {
                    $('.dropdown-menu', this).not('.in .dropdown-menu').stop(true, true).slideUp("fast");
                    $(this).toggleClass('open');
                }
        );
    });
</script>
</body>