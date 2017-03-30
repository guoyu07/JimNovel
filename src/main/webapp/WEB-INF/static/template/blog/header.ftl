<!DOCTYPE html>
<html lang="zxx">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <meta content="" name="description">
    <meta content="" name="keywords">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="HandheldFriendly" content="true">
    <meta content="telephone=no" name="format-detection">
    <link href="${TEMPLATE_BASE_PATH}/css/main.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${TEMPLATE_BASE_PATH}/css/login.css"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 tooltipss and media queries -->
    <!--[if lt IE 9]>
    <script src="${TEMPLATE_BASE_PATH}/js/html5shiv.js"></script>
    <script src="${TEMPLATE_BASE_PATH}/js/respond.min.js"></script>
    <![endif]-->
    <script src="${TEMPLATE_BASE_PATH}/js/jquery.min.js"></script>
    <script src="${TEMPLATE_BASE_PATH}/js/common.js"></script>

    <script src="${TEMPLATE_BASE_PATH}/js/Validform_v5.3.2_min.js"></script>

    <title>JimNovel小说网站</title>

</head>
<body>
<!-- Header -->
<header id="header" class="header">
    <div class="header__top">
        <div class="container">
            <div class="row">
                <div class="col-sm-3">
                    <div class="wrap-logo">
                        <a href="index.html" class="logo"></a>
                    </div>
                </div>
                <div class="col-sm-offset-1 col-md-offset-4 col-sm-5  col-md-5 hidden-xs">
                    <div class="col-xs-5 col-sm-5" id="user">
                        <div class="user">
                            <a href="#">
                                <img src="static/template/blog/img/userlogo-default.png" alt="" width="40" height="40"/>
                            </a>
                            <em class="active" style="color: #fff;height: 40px;">none<span class="glyphicon glyphicon-map-marker" style="padding-left:5px;"></span></em>



                        </div>
                        <div class="weather__city">
                            <em><#if SESSION_USER??>${SESSION_USER.name}<#else>请先登录</#if></em>
                        </div>
                    </div>
                    <div class="col-xs-7 col-sm-7">
                        <div class="exchange">
                            <ul>
                            <#if SESSION_USER??><li><a href="${BASE_PATH}/center/logout.htm">注销</a></li><#else>
                                <li><a href="${BASE_PATH}/center/register.htm">注册</a></li>
                                <li><a href="${BASE_PATH}/center/login.htm">登陆</a></li>
                            </#if>

                            </ul>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
    <div class="wsmenucontent overlapblackbg"></div>
    <div class="wsmenuexpandermain slideRight">
        <a id="navToggle" class="animated-arrow slideLeft">
            <span></span>
        </a>
    </div>
    <div class="header_down">
        <div class="container">
            <div class="wrapper clearfix bigmegamenu">
                <!--Main Menu HTML Code-->
                <nav class="wsmenu slideLeft clearfix">
                    <ul class="mobile-sub wsmenu-list">
                        <li class="visible-xs">
                            <form class="navbar-form mob_search" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                                <button type="submit" class="btn btn-search">
                                    <i class="icon-search"></i>
                                </button>
                            </form>
                        </li>
                        <li class="active">
                            <span class="wsmenu-click"></span>
                            <a href="/">首頁</a>
                        </li>

					<@shishuo_folder_list_tag folderId= 0>
						<#list tag_folder_list as tag_folder>
                            <li <#if tag_folder.folderId==g_folderId>class="active"</#if> >
                                <span class="wsmenu-click"></span>
                                <a href="<@shishuo_folder_url_tag folderId=tag_folder.folderId/>">${tag_folder.name}</a>
                            </li>
                        </#list>
                    </@shishuo_folder_list_tag>


                        <li class="navbar-right hidden-xs">
                            <form class="navbar-form" role="search">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                                <button type="submit" class="btn btn-search">
                                    <i class="icon-search"></i>
                                    <br/>Search
                                </button>
                            </form>
                        </li>

                    </ul>
                </nav>
                <!--Menu HTML Code-->
            </div>
        </div>
    </div>
</header>