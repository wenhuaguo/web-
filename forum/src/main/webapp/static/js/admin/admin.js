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
    $("#password").keydown(function (event) {
        if(event.keyCode=="13"){
            $("#adminForm").submit();
        }
    });
    $("#adminBtn").click(function () {
        $("#adminForm").submit();
    });
    $("#adminForm").validate({
        errorElement:'span',
        errorClass:'text-danger',
        rules:{
            adminname:{
                required:true,
                
            },
            password:{
                required:true,
                rangelength:[6,18]
            }
        },
        messages:{
            adminname:{
                required:"账号不能为空",

            },
            password:{
                required:"密码不能为空",
                rangelength:"密码长度必须为6到18位"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/admin/login",
                type:"post",
                data:$(form).serialize(),
                success:function (json) {
                    if(json.state == "success"){
                        var url = getParameterByName("redirect");
                        if(url){
                            window.location.href = url;
                        }else {
                            window.location.href = "/admin/home";
                        }

                    }else {
                        swal(json.message);
                    }
                },
                error:function () {
                    swal("服务器异常");
                }
            });
        }
    });
});
