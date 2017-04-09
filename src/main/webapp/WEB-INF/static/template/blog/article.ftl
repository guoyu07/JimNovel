<#include "header.ftl">
<style type="text/css">
    .news__caption{
        display: inline;
        font-size: 14px;
    }
    a.news__caption:hover{
        color:#f49253;
    }

</style>

请问
<div class="wrap wrap_white">
    <div class="container title">
        <h1 class="title__h1 underscore">${article.title}</h1>
    </div>
</div>
<!-- END title -->
<div class="wrap wrap_gray pt20">
    <div class="container">
        <div class="row">
            <div class="wrap-thumbnail">
                <div class="thumbnail">
                    <div class="row" style="padding: 30px 0">
                        <div class="col-lg-2 col-md-3 col-sm-4"  >
                            <img class="img-thumbnail" src="${article.smallImgUrl}" style="height: 157px;width: 120px;margin-left: 60px;" />
                        </div>
                        <div class="col-lg-10 col-md-9 " >
                            <div class="col-lg-8  col-sm-12 col-xs-12   mt5" >
                                <p><strong>作者：</strong><a href="#">${article.author}</a></p>
                                <p><strong>简介：</strong><span>${article.keyword}</span></p>
                            </div>
                            <div class="col-lg-2 col-md-3 "></div>

                        </div>
                    </div>
                    <ul class="main-list" style="padding: 0 5%;">
                        <@shishuo_chapter_list_tag  articleId=articleId>
                            <#list tag_chapter_list as chapter>
                                <li> <a href="<@shishuo_chapter_url_tag articleId=article.articleId chapterId=chapter.chapterId/>" class="articleTitle">${chapter.chapterTitle}</a> </li>
                            </#list>
                        </@shishuo_chapter_list_tag>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- END content-->
</div>
<#include "footer.ftl">