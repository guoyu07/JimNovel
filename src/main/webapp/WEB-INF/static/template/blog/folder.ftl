<#include "header.ftl">
<!-- Content-->
<div class="container">
    <div class="contents" id="contents">
        <div class="row">
            <@shishuo_article_page_tag  p=p rows=10 folderId=folderId>
                <#list tag_article_page.list as article>
                    <div class="items" >
                        <div class="col-md-2">
                            <img src="${article.imgUrl}" alt=""  width="200" height="229"/>
                        </div>
                        <div class="col-md-4">
                            <h3>${article.title}</h3>
                            <h4>作者：<span>${article.author}</span></h4>
                            <p>${article.keyword}</p>
                            <a href="full-width.html" target="_blank"><button class="btn btn-success">点击阅读</button></a>
                            <a href="full-width.html" target="_blank"><button class="btn btn-success"><span class="glyphicon glyphicon-arrow-down"></span>阅读量：${article.viewCount}</button></a>
                            <a href="full-width.html" target="_blank"><button class="btn btn-success"><span  class="glyphicon glyphicon-star"></span>加入书架</button></a>
                        </div>
                    </div>
                        <#if article_index%2==1><div class="clearfix"></div></#if>
                </#list>

            </@shishuo_article_page_tag>




        </div>
    </div>
    <div class="ajax_load">
        <i class="icon-arrows-cw"></i>Load more
        <svg width="128" height="40" viewBox="0 0 128 40" xmlns="http://www.w3.org/2000/svg">
            <rect x='0' y='0' fill='none' width='128' height='40'></rect>
        </svg>
    </div>
</div>
<script type="text/javascript">
    //      	$(".items .col-md-4 >button:eq(2)").on('click',function(){$(this).find('.glyphicon').toggleClass('glyphicon-star-empty')});

    //多行文本溢出显示省略号
    $('.col-md-4 p').each(function(){
        var maxwidth=125;
        if($(this).text().length>maxwidth){
            $(this).text($(this).text().substring(0,maxwidth));
            $(this).html($(this).html()+'...');
        }
    });
</script>
<!-- END content-->
<#include "footer.ftl">