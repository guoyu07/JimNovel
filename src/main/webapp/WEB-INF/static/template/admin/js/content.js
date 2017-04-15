
//对ajax进行统一封装  对session失效的情况和其他系统错误统一弹出提示框
function Ajax(url,data,callBack,type){
    var d=$.extend(true,data,{});
    $.ajax({
        type:type||'GET',
        url:url,
        data:d,
        dataType:'json',
        success:function(res){
            if(res.code==201){
                showMsg('登陆已超时,请重新登陆');
                window.location.href='login.html';
            }
            if(callBack)callBack(res);
        },
        error:function(XMLHttpRequest, textStatus, errorThrown) {

        },
    });
}

function showMsg(msg){

    layer.alert(msg, {
        skin: 'layui-layer-lan',
        shift: 4 //动画类型
    });
}

function comfirmMsg(msg){
    layer.alert(msg, {
        skin: 'layui-layer-molv' //样式类名
        ,closeBtn: 0
    }, function(){
        self.location.reload();
    });
}