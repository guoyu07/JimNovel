<#include "header.ftl">
<!-- END header -->
<!-- header slider -->
<div class="slate_gray">
    <div class="container">
        <div class="row header_news_panel">
            <!-- Tab panes -->
            <div class="col-sm-8 tab-content tab-content_mob-p0">

            <@shishuo_article_page_tag folderId=1 p=1 rows="8">

                        <#list tag_article_page.list as tag_article>
                            <div role="tabpanel" class="tab-pane fade in active" id="home">
                                <a href="full-width.html"><img src="img/content/slide4.jpg" alt="main img" class="tab-pane__img"></a>
                                <div class="header_news_text tab-pane__block">
                                    <p class="tab-pane__category yel_line"> ${tag_article.createTime?string("yyyy-MM-dd")}</p>
                                    <a class="tab-pane__title">${tag_article.title}</a>
                                    <p class="tab-pane__text">${tag_article.keyword}</p>
                                </div>
                            </div>
                        </#list>

            </@shishuo_article_page_tag>



                <div role="tabpanel" class="tab-pane fade in active" id="home">
                    <a href="full-width.html"><img src="img/content/slide4.jpg" alt="main img" class="tab-pane__img"></a>
                    <div class="header_news_text tab-pane__block">
                        <p class="tab-pane__category yel_line">武侠</p>
                        <a class="tab-pane__title">三生三世十里桃花</a>
                        <p class="tab-pane__text">累世情缘，谁捡起，谁抛下，谁忘前尘，谁总牵挂。忆当时年华，谁点相思，谁种桃花。</p>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane fade" id="profile">
                    <a href="full-width.html" ><img src="img/content/slide3.jpg" alt="main img" class="tab-pane__img"></a>
                    <div class="header_news_text tab-pane__block">
                        <p class="tab-pane__category yel_line">科幻</p>
                        <a class="tab-pane__title">时间之墟</a>
                        <p class="tab-pane__text">我们的大脑只不过是用来挂记忆这件衣服的钉子而已，必须要有钉子挂住它，但衣服却不在钉子里。</p>
                    </div>
                </div>

            </div>
            <!-- END Tab panes -->
            <!-- Nav tabs -->
            <div class="col-sm-4 news-tabs">
                <p class="news-tabs__title h2">今日热门</p>
                <ul class="news-tabs__nav nav nav-tabs shadow_text" role="tablist">
                    <li role="presentation" class="active">
                        <a href="#home" role="tab" data-toggle="tab">
                            <span class="time">No.1</span>
                            三生三世十里桃花 <span class="glyphicon glyphicon-edit" style="padding-left: 5px;" >唐七公子</span>
                            <p>恩怨纠葛如浮云过,她遗憾没在最好的年华里遇上他。
                            </p>
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="#profile" role="tab" data-toggle="tab">
                            <span class="time">No.2</span>
                            时间之墟<span class="glyphicon glyphicon-edit" style="padding-left: 5px;" >宝树</span>
                            <p>灵魂仿佛涣散到无边的空间中，没有光明，没有身体，没有时间。 </p>
                        </a>
                    </li>

                </ul>
            </div>
            <!-- END Nav tabs -->
        </div>
    </div>
</div>
<!-- END header slider -->
<!-- top news-->
<section>
    <!-- top news -->
    <!-- title -->
    <div class="wrap wrap_white">
        <div class="container title">
            <h1 class="title__h1 underscore">热门书籍</h1>
        </div>
    </div>
    <!-- END title -->
    <div class="wrap wrap_gray pt20">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="thumbnail thumbnail_big">
                        <img src="img/content/news1.jpg" height="350" width="560" alt="News">
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Economy</p>
                                <a href="#" class="news__head">The dollar has broken all records of positive change in the world and in the galaxy</a>
                                <p class="news__desc">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 16:37</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>29
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>2.3k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news2.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Economy</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">Еhe world's economy is improving and good times</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 12:11</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>11
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>1.1k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news3.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Economy</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">The euro needs to everyone in a large amount</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 19:30</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>294
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>2.9k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news5.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Sport</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">Athletes are confident of victory in all competitions</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 14:34</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>58
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>8.8k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news5.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Sport</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">Athletes are confident of victory in all competitions</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 14:34</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>58
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>8.8k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news7.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Economy</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">Attention: The poor become rich, rich richer</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 11:30</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>21
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>1.9k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news9.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Business</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">The best exchange rates and motivation for you</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 12:30</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>29
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>2.3k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news9.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Business</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">The best exchange rates and motivation for you</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 12:30</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>29
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>2.3k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="thumbnail thumbnail_small">
                        <a href="news.html" class="thumbnail__link">
                            <img src="img/content/news9.jpg" height="153" width="270" alt="News">
                        </a>
                        <div class="caption thumbnail__caption">
                            <div class="news caption__news">
                                <p class="news__category yellow-line">Business</p>
                                <a href="news.html" class="news__link">
                                    <p class="news__text">The best exchange rates and motivation for you</p>
                                </a>
                            </div>
                            <div class="posted">
                                <span class="posted__date">today, 12:30</span>
                                <ul class="posted__icon">
                                    <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>29
                                            </span>
                                    </li>
                                    <li>
                                                <span>
                                                <i class="icon-eye"></i>2.3k
                                            </span>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <!-- btn load-->
        <div class="ajax_load">
            <i class="icon-arrows-cw"></i>Load more
            <svg width="128" height="40" viewBox="0 0 128 40" xmlns="http://www.w3.org/2000/svg">
                <rect x='0' y='0' fill='none' width='128' height='40'></rect>
            </svg>
        </div>
        <!-- END btn load-->
    </div>
    <!-- /container-->
</section>
<!-- /top news -->
<section class="wrap wrap_gray">
    <div class="container">
        <div class="row">
            <div class="col-sm-9 projects">
                <div class="projects__title">
                    <h2 class="block-title__h2">本月热搜</h2>
                </div>
                <div class="projects__row">
                    <div class="special special_big">
                        <img src="img/content/spec1.jpg" height="311" width="551" alt="image" class="special__img">
                        <div class="special__box">
                            <h5 class="special__category yel_line">Special project</h5>
                            <a href="#" class="special__link">Lorem ipsum dolor sit amet</a>
                            <p class="special__desc">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                        </div>
                    </div>
                    <div class="special special_small">
                        <img src="img/content/spec2.jpg" height="311" width="285" alt="image" class="special__img">
                        <div class="special__box">
                            <h5 class="special__category yel_line">Special project</h5>
                            <a href="#" class="special__link">Lorem ipsum dolor</a>
                            <p class="special__desc">Duis aute irure dolor in reprehenderit in voluptate velit.</p>
                        </div>
                    </div>
                </div>
                <div class="projects__row">
                    <div class="special special_small">
                        <img src="img/content/spec3.jpg" height="311" width="285" alt="image" class="special__img">
                        <div class="special__box">
                            <h5 class="special__category yel_line">Special project</h5>
                            <a href="#" class="special__link">Lorem ipsum dolor</a>
                            <p class="special__desc">Duis aute irure dolor in reprehenderit in voluptate velit.</p>
                        </div>
                    </div>
                    <div class="special special_big">
                        <img src="img/content/spec4.jpg" height="311" width="551" alt="image" class="special__img">
                        <div class="special__box">
                            <h5 class="special__category yel_line">Special project</h5>
                            <a href="#" class="special__link">Lorem ipsum dolor sit amet</a>
                            <p class="special__desc">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                        </div>
                    </div>
                </div>
                <!-- banner -->
                <div class="banner">
                    <img src="img/content/banner.jpg" height="221" width="850" alt="image" class="banner__img">
                    <div class="banner__box">
                        <p class="banner__text first-text">广告位</p>
                        <p class="banner__text second-text">出租</p>
                        <a href="#" class="banner__link">进入了解</a>
                    </div>
                </div>
                <!-- END banner -->
            </div>
            <div class="col-sm-3">
                <div class="block-title">
                    <h2 class="block-title__h2">热门推荐</h2>
                </div>
                <div class="twitter">
                    <div class="twitter__header">
                        <p class="twitter__header__name">@barclee</p>
                        <p class="twitter__header__text">Hello, world</p>
                    </div>
                    <div class="twitter__body">
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>Reply
                            </a>
                        </div>
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>Reply
                            </a>
                        </div>
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>Reply
                            </a>
                        </div>
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>Reply
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section class="wrap wrap_gray">
    <div class="container">
        <div class="row">
            <div class="col-sm-9">
                <div class="row">
                    <div class="block-title col-sm-12">
                        <h2 class="block-title__h2">编辑推荐
                            <a class="block-title__view-all" href="#" data-hover="查看全部">查看全部</a>
                        </h2>
                    </div>
                    <div class="col-sm-4">
                        <div class="thumbnail thumbnail_small">
                            <a href="news.html" class="thumbnail__link">
                                <img src="img/content/news10.jpg" height="153" width="270" alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">Policy</p>
                                    <a href="news.html" class="news__link">
                                        <p class="news__text">CEO who jacked up price of AIDS pill to $750 faces major backlash</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">today, 12:30</span>
                                    <ul class="posted__icon">
                                        <li>
                                                    <span>
                                                    <i class="icon-comment-empty"></i>29
                                                </span>
                                        </li>
                                        <li>
                                                    <span>
                                                    <i class="icon-eye"></i>4.4k
                                                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="thumbnail thumbnail_small">
                            <a href="news.html" class="thumbnail__link">
                                <img src="img/content/news11.jpg" height="153" width="270" alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">Policy</p>
                                    <a href="news.html" class="news__link">
                                        <p class="news__text">CEO who jacked up price of AIDS pill to $750 faces major backlash</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">today, 11:30</span>
                                    <ul class="posted__icon">
                                        <li>
                                                    <span>
                                                    <i class="icon-comment-empty"></i>22
                                                </span>
                                        </li>
                                        <li>
                                                    <span>
                                                    <i class="icon-eye"></i>5.3k
                                                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="wrap-article">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="article">
                                        <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                        <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                        <p class="article__time">today, 16:04</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wrap-article">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="article">
                                        <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                        <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                        <p class="article__time">today, 16:04</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wrap-article">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="article">
                                        <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                        <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                        <p class="article__time">today, 16:04</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="wrap-article">
                            <div class="row">
                                <div class="col-sm-12">
                                    <div class="article">
                                        <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                        <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                        <p class="article__time">today, 16:04</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="block-title col-sm-12">
                        <h2 class="block-title__h2">本周强推
                            <a class="block-title__view-all" href="#" data-hover="查看全部">查看全部</a>
                        </h2>
                    </div>
                    <div class="col-sm-4">
                        <div class="thumbnail thumbnail_small">
                            <a href="news.html" class="thumbnail__link">
                                <img src="img/content/news12.jpg" height="153" width="270" alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">People</p>
                                    <a href="news.html" class="news__link">
                                        <p class="news__text">CEO who jacked up price of AIDS pill to $750 faces major backlash</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">today, 11:31</span>
                                    <ul class="posted__icon">
                                        <li>
                                                    <span>
                                                    <i class="icon-comment-empty"></i>21
                                                </span>
                                        </li>
                                        <li>
                                                    <span>
                                                    <i class="icon-eye"></i>2.1k
                                                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="thumbnail thumbnail_small">
                            <a href="news.html" class="thumbnail__link">
                                <img src="img/content/news13.jpg" height="153" width="270" alt="News">
                            </a>
                            <div class="caption thumbnail__caption">
                                <div class="news caption__news">
                                    <p class="news__category yellow-line">Sport</p>
                                    <a href="news.html" class="news__link">
                                        <p class="news__text">CEO who jacked up price of AIDS pill to $750 faces major backlash</p>
                                    </a>
                                </div>
                                <div class="posted">
                                    <span class="posted__date">today, 10:30</span>
                                    <ul class="posted__icon">
                                        <li>
                                                    <span>
                                                    <i class="icon-comment-empty"></i>22
                                                </span>
                                        </li>
                                        <li>
                                                    <span>
                                                    <i class="icon-eye"></i>2.1k
                                                </span>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4">
                        <div class="row wrap-article">
                            <div class="col-sm-12">
                                <div class="article">
                                    <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                    <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                    <p class="article__time">today, 13:11</p>
                                </div>
                            </div>
                        </div>
                        <div class="row wrap-article">
                            <div class="col-sm-12">
                                <div class="article">
                                    <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                    <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                    <p class="article__time">today, 13:11</p>
                                </div>
                            </div>
                        </div>
                        <div class="row wrap-article">
                            <div class="col-sm-12">
                                <div class="article">
                                    <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                    <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                    <p class="article__time">today, 12:03</p>
                                </div>
                            </div>
                        </div>
                        <div class="row wrap-article">
                            <div class="col-sm-12">
                                <div class="article">
                                    <i class="article__icon" style="background-image:url('img/content/circle.png');"></i>
                                    <a href="#" class="article__text">Lorem ipsum dolor sit amet, consectetur adipisc</a>
                                    <p class="article__time">today, 11:04</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="block-title">
                    <h2 class="block-title__h2">热门作品</h2>
                </div>
                <div class="wrap-redaction wrap-redaction_white">
                    <div class="redaction redaction_line">
                        <p class="redaction__category yel_line">Policy</p>
                        <a class="redaction__title">Lorem ipsum dolor sit amet, consectetur</a>
                        <p class="redaction__text">Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris, quis nostrud ullamco laboris</p>
                        <p class="redaction__time">today, 16:35</p>
                    </div>
                    <div class="redaction redaction_line">
                        <p class="redaction__category yel_line">Policy</p>
                        <a class="redaction__title">Lorem ipsum dolor sit amet, consectetur</a>
                        <p class="redaction__text">Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris, quis nostrud ullamco laboris</p>
                        <p class="redaction__time">today, 16:35</p>
                    </div>
                    <div class="redaction redaction_line">
                        <p class="redaction__category yel_line">Policy</p>
                        <a class="redaction__title">Lorem ipsum dolor sit amet, consectetur</a>
                        <p class="redaction__text">Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris, quis nostrud ullamco laboris</p>
                        <p class="redaction__time">today, 16:35</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<#include "footer.ftl">