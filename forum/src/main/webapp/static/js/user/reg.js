$(function () {
    $("#btn").click(function () {
        $("#regForm").submit();
    });

    $("#regForm").validate({
        errorElement:'span',
        errorClass:'text-error',
        rules:{
            username:{
                required:true,
                minlength:3,
                remote:"/user/validate/username"
            },
            password:{
                required:true,
                rangelength:[6,18]
            },
            repassword:{
                required:true,
                rangelength:[6,18],
                equalTo:"#password"
            },
            phone:{
                required:true,
                rangelength:[11,11],
                digits:true
            },
            email:{
                required:true,
                email:true,
                remote:"/user/validate/email"
            }
        },
        messages:{
            username:{
                required:"请输入账号",
                minlength:"最小长度三个字符",
                remote:"账号已被占用"
            },
            password:{
                required:"请输入密码",
                rangelength:"密码长度为6到18位字符"
            },
            repassword:{
                required:"请输入确认密码",
                rangelength:"密码长度为6到18位字符",
                equalTo:"两次密码不一致"
            },
            phone:{
                required:"请输入手机号码",
                rangelength:"手机号码长度为11位",
                digots:"手机号码长度为11位"
            },
            email:{
                required:"请输入电子邮箱",
                email:"电子邮箱格式不正确",
                remote:"邮箱已被占用"
            }
        },
        submitHandler:function () {
            $.ajax({
                url:"/reg",
                type:"post",
                data:$("#regForm").serialize(),
                beforeSend:function () {

                  $("#btn").text("注册中...").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state == "success"){
                        alert("注册成功，请去邮箱激活账号");
                        window.location.href ="/login";
                    }else {
                        alert(data.message);
                    }
                },
                error:function () {
                    alert("服务器异常");
                },
                complete:function () {
                    $("#btn").text("注册").removeAttr("disabled");
                }
            });
        }
    })
});
