<#ftl encoding="UTF-8">
<#include "header.ftl">

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Mane</title>

    <link rel="stylesheet" href="../css/main_page.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <link href="../css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="../js/jquery-ui-1.12.1/"></script>
    <script src="../js/bootstrap.js"></script>
    <link href="../css/top_menu.css" rel="stylesheet">
    <link rel="stylesheet" href="../css/login_signin.css">
    <link href="../css/description.css" rel="stylesheet">
    <link href="../css/concrete-interview.css" rel="stylesheet">

    <!-- Website Font style -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

</head>

<body>
<@header></@header>

<div class="main-part">

    <div class="container main-part-container">
        <div class="row">
            <div class="col-md-12 main-part-container">
                <div class="concrete-interview">
                    <div class="question">
                        <div class="question-part">
                            <div class="col-md-12 pull-right">
                                <a href="#" class="usual-text col-md-11 head">${interview.getQuestion()}</a>
                                <#if user?? && interview.getOwnerId() == user.getId()>
                                <div class="col-md-1 dropdown">
                                    <img src="img/menu.png" width="15px" class="dropdown-toggle pull-right" data-toggle="dropdown">
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="#">Заархивировать</a></li>
                                        <li><a href="#">Удалить</a></li>
                                    </ul>
                                </div>
                                </#if>

                            </div>

                            <form action="" method="">
                            <#if interview.getOptions()??>
                                <#assign options = interview.getOptions()>
                                <#if 1 <= options?size>
                                    <#list 0..options?size - 1 as j>
                                        <div class="var-answer">
                                            <input type="radio" name="q" id="ans${j}"><label
                                                href="#description${j}"
                                                data-toggle="modal"
                                                for="ans${j}">${options[j].getAnswer()}</label>
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
                    <#if options?? && 1 <= options?size>
                        <#list 0..options?size - 1 as j>
                            <div id="description${j}" class="modal">
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

                </div>
                <div class="comments" id="comments2">
                <#if interview.getCommentaries()??>
                    <#assign comments = interview.getCommentaries()>
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
                    <#if user??>
                    <div class="myComment">
                        <div class="col-md-1 ava">
                            <div class="thumbnail">
                                <img class="img-responsive user-photo"
                                        src=<#if user?? && (user.getImgPath()??)>/load/${user.getImgPath()}<#else>https://ssl.gstatic.com/accounts/ui/avatar_2x.png</#if>>
                            </div>
                            <!-- /thumbnail -->
                        </div>
                        <textarea form="leave_comment_form" name="content" class="col-md-11" rows="3"
                                  placeholder="Оставьте комментарий..."></textarea>
                        <div>
                            <form id="leave_comment_form" method="post" action="/add_my_comment?id=${interview.getId()}">
                                <button type="submit" class="ok-btn">Добавить</button>
                            </form>
                        </div>
                    </div>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>