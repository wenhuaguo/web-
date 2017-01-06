$(function () {
    //外部的js不能获取服务端传来的数据因为外部js加载一次之后会存储在本地缓存当中，内部js必须每次向服务端进行读取数据所有客户获取服务端的数据
    //判断用户是否登录，如果不登录进行轮询，登录了之后进行轮行
    var longin = $("#islogin").text();
    if(longin == "1"){
        //也就是用户登录之后进行轮询
        //然后进行轮询
        setInterval(loadcount,1000*100);
    }
    //用轮询不停的向服务器发出请求定时的更新通知中心的未读消息数
    //1.定义一个函数轮询的规则
    var loadcount = function () {
        $.post("/notify",function (json) {
            //考虑json.data > 0;才进行替换因为想的目的是等于0 的消息不进行显示
            if(json.state == "success" && json.data > 0){
                $("#span").text(json.data);
            }
        });
    }
    //进入各个页面每刷新一次就向服务端发送请求
    loadcount();

});
