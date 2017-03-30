<#include "header.ftl">
<!-- END header -->
<!-- header slider -->
<div class="slate_gray">
    <div class="container">
        <div class="row header_news_panel">
            <!-- Tab panes -->
            <div class="col-sm-8 tab-content tab-content_mob-p0">

            <@shishuo_popular_article_tag  p=1 rows=4 count=4>

                        <#list tag_hot_article.list as tag_article>
                            <div role="tabpanel" class="tab-pane fade<#if 0==tag_article_index> in active</#if>" id="${tag_article.folder.ename}">
                                <a href="<@shishuo_article_url_tag articleId=tag_article.articleId/>"><img src="${tag_article.imgUrl}" alt="main img" class="tab-pane__img"></a>
                                <div class="header_news_text tab-pane__block">
                                    <p class="tab-pane__category yel_line"> ${tag_article.createTime?string("yyyy-MM-dd")}</p>
                                    <a class="tab-pane__title">${tag_article.title}</a>
                                    <p class="tab-pane__text">${tag_article.keyword}</p>
                                </div>
                            </div>
                        </#list>

            </@shishuo_popular_article_tag>


            </div>
            <!-- END Tab panes -->
            <!-- Nav tabs -->
            <div class="col-sm-4 news-tabs">
                <p class="news-tabs__title h2">今日热门</p>

                <ul class="news-tabs__nav nav nav-tabs shadow_text" role="tablist">
                    <@shishuo_popular_article_tag  p=1 rows=4 count=4>
                        <#list tag_hot_article.list as tag_article>

                                <li role="presentation" <#if 0==tag_article_index>class="active"</#if>>
                                    <a href="#${tag_article.folder.ename}" role="tab" data-toggle="tab">
                                        <span class="time">No.${tag_article_index+1}</span>
                                    ${tag_article.title} <span class="glyphicon glyphicon-edit" style="padding-left: 5px;" >${tag_article.author}</span>
                                        <p>${tag_article.keyword}</p>
                                    </a>
                                </li>

                        </#list>
                    </@shishuo_popular_article_tag>
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
                <@shishuo_random_article_tag  p=1 rows=10 count=100>
                    <#list tag_random_article.list as tag_article>
                        <div class="col-sm-<#if tag_article_index==0>6<#else>3</#if>">
                            <div class="thumbnail thumbnail_<#if tag_article_index==0>big<#else>small</#if>">
                                <a href="news.html" class="thumbnail__link">
                                    <img src="${tag_article.imgUrl}" <#if tag_article_index==0>height="350" width="560"<#else>height="153" width="270"</#if> alt="News">
                                </a>
                                <div class="caption thumbnail__caption">
                                    <div class="news caption__news">
                                        <p class="news__category yellow-line">${tag_article.title}</p>
                                        <a href="<@shishuo_article_url_tag articleId=tag_article.articleId/>" class="news__link">
                                            <p class="news__text">${tag_article.keyword}</p>
                                        </a>
                                    </div>
                                    <div class="posted">
                                        <span class="posted__date">${tag_article.createTime?string("EEE,MMM d,yy")}</span>
                                        <ul class="posted__icon">
                                            <li>
                                                <span>
                                                <i class="icon-comment-empty"></i>${tag_article.author}
                                            </span>
                                            </li>
                                            <li>
                                                <span>
                                                <i class="icon-eye"></i>${tag_article.viewCount}
                                            </span>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </#list>
                </@shishuo_random_article_tag>



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

<section class="wrap wrap_gray">
    <div class="container">
        <div class="row">
            <div class="col-sm-9 projects">
                <div class="projects__title">
                    <h2 class="block-title__h2">本月热搜</h2>
                </div>
                <div class="projects__row">
                    <@shishuo_popular_folder_tag>
                        <#list tag_popular_folder_list as tag_folder>
                            <div class="special special_<#if tag_folder_index%3==0>big<#else>small</#if>">
                                <img src="${tag_folder.imgUrl}" <#if tag_folder_index==0>height="311" width="551" <#else>height="311" width="285" </#if>alt="image" class="special__img">
                                <div class="special__box">
                                    <h5 class="special__category yel_line">${tag_folder.name}</h5>
                                    <a href="<@shishuo_folder_url_tag folderId=tag_folder.folderId/>" class="special__link">${tag_folder.content}</a>
                                    <p class="special__desc">${tag_folder.ename}</p>
                                </div>
                            </div>
                        </#list>
                    </@shishuo_popular_folder_tag>
                </div>

            </div>
            <div class="col-sm-3">
                <div class="block-title">
                    <h2 class="block-title__h2">热门评论</h2>
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
                                <i class="icon-reply"></i>查看
                            </a>
                        </div>
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>查看
                            </a>
                        </div>
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>查看
                            </a>
                        </div>
                        <div class="message">
                            <p class="message__time">about 5 hours ago</p>
                            <p class="message__text">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a href="#" class="message__link">
                                <i class="icon-reply"></i>查看
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>


<#include "footer.ftl">