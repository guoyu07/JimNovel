<#include "header.ftl">
<!-- Content-->
<style type="text/css">
    .contents .row {
        margin: 20px 0;
    }

    a {
        color: #fff;

    }

    .items {
        display: inline;
        margin: auto;
        display: block;
        float: none;
        vertical-align: top;
        margin-top: 15px;
        margin-bottom: 15px;
    }

    .items .col-md-2 {
        padding: 10px 0;
        box-shadow: 0 0 10px #888;
    }

    .items img:hover {
        -webkit-transform: scale(1.1);
        -moz-transform: scale(1.1);
        -o-transform: scale(1.1);
        transform: scale(1.1);
    }

    .items img {
        transition: transform .3s ease-out;
    }

    .items p {
        width: 370px;
        height: 100px;
        font-size: 13px;
    }

    .items .col-md-4 {
        background-color: rgba(187, 196, 200, 0.38);
        height: 249px;
    }

    .items .col-md-4 h4 {
        color: #148216;
    }

    .items .col-md-4 h3 {
        color: #f38844;
        font-weight: bold;
    }

    .ajax_load {
        margin-bottom: 20px;
    }

</style>
<div class="container">
    <div class="contents" id="contents">
        <div class="row">
            <@shishuo_article_page_tag  p=p rows=10 folderId=folderId>
                <#list tag_article_page.list as article>
                    <div class="items" >
                        <div class="col-md-2">
                            <img src="${article.smallImgUrl}" alt=""  width="200" height="229"/>
                        </div>
                        <div class="col-md-4">
                            <h3>${article.title}</h3>
                            <h4>作者：<span>${article.author}</span></h4>
                            <p>${article.keyword}</p>
                            <a href="<@shishuo_article_url_tag articleId=article.articleId/>" target="_blank"><button class="btn btn-success">点击阅读</button></a>
                            <a href="full-width.html" target="_blank"><button class="btn btn-success"><span class="glyphicon glyphicon-arrow-down"></span>阅读量：${article.viewCount}</button></a>
                            <a href="javascript:void(0)"><button id="car" data="${article.articleId}" class="btn btn-success"><span  class="glyphicon glyphicon-star"></span>加入书架</button></a>
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
    $('#car').click(function(){
        var articleId = $(this).attr('data');
        $.ajax({
            type:'GET',
            url:'addCollection.htm',
            data:{'articleId':articleId},
            dataType:'json',
            success:function(d){
                alert(d.errors.msg);
            },

        });
    });

</script>
<!-- END content-->
<#include "footer.ftl">