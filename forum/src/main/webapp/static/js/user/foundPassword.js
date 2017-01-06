$(function () {
    //change(data|fn)当元素的值发生改变时触发该事件，该事件仅适用于（文本域text filed和textarea 和select元素）
    $("#type").change(function () {
        //获取触发该事件元素的值
        var value =  $(this).val();
        //进行判断获得元素的值是什么
        if("email" == value){
            $("#typename").text("邮箱找回");
        }else if("phone" == value){
            $("#typename").text("手机找回");
        }
    });
    $("#foundPasswordBtn").click(function () {
        $("#foundPasswordForm").submit();
    });
    $("#foundPasswordForm").validate({
        errorElement:'span',
        errorClass:'text-danger',
        rules:{
            value:{
                required:true
            }
        },
        messages:{
            value:{
                required:"必填项"
            }
        },
        submitHandler:function (form) {
            $.ajax({
                url:"/foundPassword",
                type:"post",
                data:$(form).serialize(),
                beforeSend:function () {
                  $("#foundPasswordBtn").text("提交中...").attr("disabled","disabled");
                },
                success:function (data) {
                    //获取客户端选择找回方式值进行判断是哪种方式然后进行提示性操作
                    var type = $("#type").val();
                    if(data.state == "success"){
                        if("email" == type){
                            alert("请查收邮件进行操作");
                        }else {
                            //TODO phone
                        }

                    }else{
                        alert(data.message);
                    }
                },
                error:function () {
                    alert("服务器异常");
                },
                complete:function () {
                    $("#foundPasswordBtn").text("提交").removeAttr("disabled");
                }
            });
        }
    });
});
