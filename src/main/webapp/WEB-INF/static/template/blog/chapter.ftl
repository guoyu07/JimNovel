<#include "header.ftl">
<div class="wrapper">
    <article class="container articles">
        <div class="row">
            <div class="col-sm-4 col-md-3 right_sidebar hidden-xs hidden-sm" data-spy="affix" data-offset-top="112" data-offset-bottom="80">
                <div class="news-tabs">
                    <p class="news-tabs__title h2">${article.title}</p>
                    <ul class="news-tabs__nav nav nav-tabs shadow_text" role="tablist">
                        <@shishuo_chapter_list_tag  articleId=article.articleId>
                            <#list tag_chapter_list as chapters>
                                <li role="presentation" <#if chapters.chapterTitle==chapter.chapterTitle>class="active"</#if>>
                                    <a href="<@shishuo_chapter_url_tag articleId=article.articleId chapterId=chapters.chapterId/>">
                                        ${chapters.chapterTitle}
                                    </a>
                                </li>
                            </#list>
                        </@shishuo_chapter_list_tag>
                    </ul>
                </div>
            </div>
            <div class="col-sm-12 col-md-9 p0 wrap-headline">
                <img src="${folder.imgUrl}" alt="img" class="wrap-headline__img">
                <div class="headline clearfix">
                    <div class="headline__data">

                        <a href="<@shishuo_folder_url_tag folderId=folder.folderId/>" class="headline__category headline__category_orange">${folder.name}</a>
                    </div>

                </div>
            </div>
            <div class="col-sm-12 col-md-9 article_text">
                <div class="current">
                    <h1 class="text-center yel_line current__title">${article.title}</h1>
                    <h3 class="text-center ">${chapter.chapterTitle}</h3>
                    <p class="current__text">${chapter.content}</p>
                </div>
            </div>
            <div class="col-sm-12 col-md-9 tags">
                <p>标签:</p>
                <ul>
                    <li>
                        <a href="#" title="World" class="font">${folder.name}</a>
                    </li>
                </ul>
            </div>
            <div class="col-sm-9 col-md-8 col-lg-6 comments">
                <p class="comments__title">评论区</p>
                <div class="comments__media">
                    <div class="media-middle">
                        <i class="media-object" style="background-image: url('img/content/comment.png')"></i>
                        <div class="comm_info">
                            <h4 class="media-heading">Maria</h4>
                            <span class="time">today, 12:30</span>
                        </div>
                    </div>
                    <p class="media-body">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
                </div>

                <div class="comments__form">
                    <form action="#" method="POST">
                        <div class="form-group">
                            <input type="text" style="width: 200px;" class="form-control" placeholder="Your name">
                        </div>
                        <div class="form-group">
                            <textarea name="text" id="input" class="form-control" rows="7" required="required"></textarea>
                        </div>
                        <button type="submit" class="btn btn-comment">发送</button>
                    </form>
                </div>
            </div>
        </div>
    </article>
</div>
<#include "footer.ftl">