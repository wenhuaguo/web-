$(function () {
    $("#saveBtn").click(function () {
        $("#passwordForm").submit();
    });
    $("#passwordForm").validate({
        errorElement:'span',
        errorClass:'text-danger',
        rules:{
            password:{
                required:true,
                rangelength:[6,18]
            },
            repassword:{
                required:true,
                rangelength:[6,18],
                equalTo:"#password"
            }

        },
        messages:{
            password:{
                required:"请输入新密码",
                rangelength:"密码长度必须为6到18位"
            },
            repassword:{
                required:"请输入确认密码",
                rangelength:"密码长度必须为6到18位",
                equalTo:"两次密码不一致"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/foundpassword/newpassword",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function () {
                  $("#saveBtn").text("保存中...").attr("disabled","disabled");
                },
                success:function (data) {
                    if(data.state == "success"){
                        alert("密码修改成功，请重新登录");
                        window.location.href = "/login";
                    }else if(data.state == "error"){
                        alert(data.message);
                    }
                },
                error:function () {
                    alert("服务器异常");
                },
                complete:function () {
                    $("#saveBtn").text("保存").removeAttr("disabled");
                }
            });
        }
    });
});
