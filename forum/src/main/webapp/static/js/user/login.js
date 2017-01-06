$(function () {
    //通过该函数获取URL中键对应的值
    function getParameterByName(name, url) {
        if (!url) {
            url = window.location.href;
        }
        name = name.replace(/[\[\]]/g, "\\$&");
        var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
            results = regex.exec(url);
        if (!results) return null;
        if (!results[2]) return '';
        return decodeURIComponent(results[2].replace(/\+/g, " "));
    }
    $("#loginBtn").click(function () {
        $("#loginForm").submit();
    });
    $("#password").keydown(function (event) {
        if(event.keyCode=="13"){
            $("#loginForm").submit();
        }
    });
    $("#loginForm").validate({
        errorElement:'span',
        errorClass:'text-error',
        rules:{
            username:{
                required:true,
                minlength:3,
            },
            password:{
                required:true,
                rangelength:[6,18]
            }
        },
        messages:{
            username:{
                required:"请输入账号",
                minlength:"账号长度不能小于3位",
            },
            password:{
                required:"请输入密码",
                rangelength:"密码长度为6到18位"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/login",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function () {
                  $("#loginBtn").text("登录中...").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state == "success"){
                        swal({title:"登录成功"},function () {
                            //通过geParameterByName(name,url)获取键对应的值

                            var url = getParameterByName("redirect");
                            if(url){
                                var hash =location.hash;//设置或返回从（#）开始的URL(锚)
                                console.log(hash);
                                if(hash != null){
                                    window.location.href = url+hash;
                                }else {
                                    window.location.href = url;
                                }
                            }else {

                                window.location.href = "/home"
                            }
                        });
                        //alert("登录成功");
                        //输入键通过该函数获取地址栏中对应的值


                    }else {
                        swal(data.message);
                    }
                },
                complete:function () {
                    $("#loginBtn").text("登录").removeAttr("disabled");
                }
            });
        }
    });
});
