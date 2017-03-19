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

    <title>Home</title>

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
                <div class="col-sm-offset-2 col-md-offset-5 col-sm-6 col-md-4 hidden-xs">
                    <div class="col-xs-4 col-sm-5">
                        <div class="weather">
                            <div class="weather__temperature">
                                <span class="glyphicon glyphicon-user"></span>
                                <em class="active">用户名</em>


                            </div>
                            <div class="weather__city">
                                <em>none</em>
                                <div class="weather__city__list">
                                    <ul>
                                        <li class="active">
                                            <a href="#">读者  come in</a>
                                        </li>
                                        <li>
                                            <a href="#">后台  come in </a>
                                        </li>
                                        <li>
                                            <a href="#">作家 come in</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6 col-sm-7">
                        <div class="exchange">
                            <ul>
                                <li><a href="register.html">注册</a></li>
                                <li><a href="login.html">登陆</a></li>
                                <li><a href="bookshelf.html"><span class="glyphicon glyphicon-align-left"></span>我的书架</a></li>
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
                            <a href="index.html">首頁</a>
                        </li>
					<@shishuo_folder_list_tag folderId= 0>
						<#list tag_folder_list as tag_folder>
                            <li class="active">
                                <span class="wsmenu-click"></span>
                                <a href="index.html">${tag_folder.name}</a>
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
                        <li>
                            <div class="visible-xs col-sm-offset-5 col-sm-4">
                                <div class="col-sm-5">
                                    <div class="weather">
                                        <div class="weather__temperature">
                                            <span class="glyphicon glyphicon-user"></span>
                                            <em class="active">用户名</em>
                                        </div>
                                        <div class="weather__city">
                                            <em>none</em>
                                            <div class="weather__city__list">
                                                <ul>
                                                    <li class="active">
                                                        <a href="#">读者  come in</a>
                                                    </li>
                                                    <li>
                                                        <a href="#">后台  come in </a>
                                                    </li>
                                                    <li>
                                                        <a href="#">作家 come in</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-7">
                                    <div class="exchange">
                                        <ul>
                                            <li><a href="#">注册</a></li>
                                            <li><a href="#">登陆</a></li>
                                            <li><a href="#"><span class="glyphicon glyphicon-align-left"></span>我的书架</a></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </nav>
                <!--Menu HTML Code-->
            </div>
        </div>
    </div>
</header>