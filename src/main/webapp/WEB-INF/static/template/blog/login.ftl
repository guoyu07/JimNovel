<#include "header.ftl">
<div class="login">
    <h2></h2>
    <div class="login-top">
        <h1>登陆</h1>
        <form class="loginform"
              action="${BASE_PATH}/center/login.json"
              method="post">
            <input type="text" name="email" datatype="e" placeholder="用户名" value="chenrunfa@vip.qq.com" class="inputxt">
            <input type="password" name="password" datatype="*6-15" placeholder="密码" value="123456" class="inputxt">

        <div class="forgot">
            <a href="#">忘记密码</a>
            <input type="submit" value="登陆" >
        </div>
        </form>
    </div>
    <div class="login-bottom">
        <h3>新用户 &nbsp;<a href="register.html">注册</a>&nbsp;</h3>
    </div>
</div>
<script type="text/javascript">
    $(function(){
      //  $(".loginform").Validform();  //就这一行代码！;

        var demo=$(".loginform").Validform({
            tiptype:4,
            label:".loginform",
            callback:function(data){
                if(data.result){
                    window.location.href="/index.htm";
                }
                else{
                    console.log(data);
                    alert(data.errors["password"]);
                }
            },
            ajaxPost:true
        });

    })

</script>
<#include "footer.ftl">