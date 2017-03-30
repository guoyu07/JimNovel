//封装ajax
function Ajax(url,data,callBack,type){
    var d=$.extend(true,data);
    $.ajax({
        type:type||'GET',
        url:url,
        data:d,
        dataType:'json',
        success:function(res){
            if(callBack)callBack(res);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {
           alert('提示','系统出错');
        },
    });
}