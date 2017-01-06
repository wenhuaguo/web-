<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/20
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>凯盛IT-${requestScope.post.title}</title>
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/static/css/style.css">
    <link rel="stylesheet" href="/static/js/editer/styles/simditor.css">
    <link rel="stylesheet" href="/static/js/highlight/styles/solarized-dark.css">
    <style>
        body{
            background-image: url(img/bg.jpg);
        }
        .simditor .simditor-body {
            min-height: 100px;
        }
    </style>
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!--header-bar end-->

<div class="container">
    <div class="box">
        <ul class="breadcrumb" style="background-color: #fff;margin-bottom: 0px;">
            <li><a href="/login">首页</a> <span class="divider">/</span></li>
            <li class="active">${requestScope.post.node.nodename}</li>
        </ul>
        <div class="topic-head">
            <img class="img-rounded avatar" src="${requestScope.post.user.avatar}?imageView2/1/w/60/h/60" alt="">
            <h3 class="title">${requestScope.post.title}</h3>
            <p class="topic-msg muted"><a href="/set">${requestScope.post.user.username}</a><span id="showTime"></span></p>
        </div>
        <div class="topic-body">
           ${requestScope.post.content}
        <div class="topic-toolbar">
            <c:if test="${not empty sessionScope.curr_user}">
            <ul class="unstyled inline pull-left">
                <c:choose>
                    <c:when test="${not empty save}">
                        <li><a href="javascript:;" id="save">取消收藏</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:;" id="save">加入收藏</a></li>
                    </c:otherwise>
                </c:choose>
                <c:choose>
                    <c:when test="${not empty thank}">
                        <li><a href="javascript:;" id="thank">取消感谢</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="javascript:;" id="thank">感谢</a></li>
                    </c:otherwise>
                </c:choose>

                <%--两个id相等用来说明当前是当前用户发布的帖子有修改的权限，后面的post.edit表名可以修改--%>
                <c:if test="${ sessionScope.curr_user.id == post.userid and post.edit}">
                    <li><a href="/edit?postid=${post.id}">编辑</a></li>
                </c:if>

            </ul>
            </c:if>
            <ul class="unstyled inline pull-right muted">
                <li>${requestScope.post.clicknum}次点击</li>
                <li id="saveNum">${requestScope.post.savenum}人收藏</li>
                <li id="thankNum">${requestScope.post.thanknum}人感谢</li>
            </ul>
        </div>
    </div>
    <!--box end-->
<c:if test="${not empty replyList}">
    <div class="box" style="margin-top:20px;">
        <div class="talk-item muted" style="font-size: 12px">
                ${fn:length(replyList)}个回复 | 直到<span id="lastreplytime">${post.lastreplytime}</span>
        </div>
        <c:forEach items="${requestScope.replyList}" var="reply" varStatus="status">
            <div class="talk-item">
                <table class="talk-table">
                    <tr>
                            <%--定义一个喵标记当用户点击的时候转到这里--%>
                        <a name="reply${status.count}"></a>
                        <td width="50">
                            <img class="avatar" src="${reply.user.avatar}?imageView2/1/w/40/h/40" alt="">
                        </td>
                        <td width="auto">
                            <a href="" style="font-size: 12px">${reply.user.username}</a> <span style="font-size: 12px" class="reply">${reply.replytime}</span>
                            <br>
                            <p style="font-size: 14px">${reply.content}</p>
                        </td>
                        <td width="70" align="right" style="font-size: 12px">
                            <a href="javascript:;" rel="${status.count}" class="replyLink" title="回复"><i class="fa fa-reply"></i></a>&nbsp;
                            <span class="badge">${status.count}</span>
                        </td>
                    </tr>
                </table>
            </div>
        </c:forEach>
    </div>
</c:if>
    <c:choose>
        <c:when test="${not empty sessionScope.curr_user}">
            <div class="box" style="margin:20px 0px;">
                <a name="reply"></a>
                <div class="talk-item muted" style="font-size: 12px"><i class="fa fa-plus"></i> 添加一条新回复</div>
                <form action="" style="padding: 15px;margin-bottom:0px;" id="replyForm">
                    <input type="hidden" value="${requestScope.post.id}" name="postId">
                    <textarea name="content" id="editor"></textarea>
                </form>
                <div class="talk-item muted" style="text-align: right;font-size: 12px">
                    <span class="pull-left">请尽量让自己的回复能够对别人有帮助回复</span>
                    <button class="btn btn-primary" id="replyBtn">发布</button>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="talk-item muted" style="text-align: left;font-size: 16px">
                如需回复，请<a href="/login?redirect=/showPost?postId=${requestScope.post.id}#reply"><button class="btn btn-primary">登录</button></a>或<a href="/reg?redirect=/showPost?postId=${requestScope.post.id}#reply"><button class="btn btn-info">注册</button></a>之后回复
            </div>
        </c:otherwise>
    </c:choose>





</div>
</div>
<!--container end-->

    <script src="/static/js/jquery-1.11.3.min.js"></script>
<script src="/static/js/editer/scripts/module.min.js"></script>
<script src="/static/js/editer/scripts/hotkeys.min.js"></script>
<script src="/static/js/editer/scripts/uploader.min.js"></script>
<script src="/static/js/editer/scripts/simditor.min.js"></script>
    <script src="/static/js/jquery.validate.min.js"></script>
    <script src="/static/js/highlight/js/highlight.pack.js"></script>
    <script src="/static/js/moment.js"></script>
<script>
    $(function(){
        <c:if test="${not empty sessionScope.curr_user}">
        var editor = new Simditor({
            textarea: $('#editor'),
            toolbar:false
            //optional options
        });
        $(".replyLink").click(function () {
            //获得连接rel属性的值在这里也就是楼数
            var count = $(this).attr("rel");
            var html = "<a href='#reply"+count+"'>#"+count+"</a>";
            editor.setValue(html +editor.getValue());
            window.location.href="#reply";
        });
        </c:if>
        $("#replyBtn").click(function () {
            $("#replyForm").submit();
        });
        $("#replyForm").validate({
            errorElement:'span',
            errorClass:'text-error',
            rules:{
                content:{required:true}
            },
            messages:{content:{required:"内容不能为空"}},
            submitHandler:function (form) {
                $.ajax({
                    url:"/reply",
                    type:"post",
                    data:$(form).serialize(),
                    beforeSend:function () {
                      $("#replyBtn").text("回复中...").attr("disabled","disabled");
                    },
                    success:function (data) {
                        editor.setValue("");
                        if(data.state == "success"){
//                            var time = moment(new Date().getTime()).fromNow();
                            <%--var html = '<div class="talk-item"> <table class="talk-table"> <tr><a name="reply${status.count}"></a> <td width="50"> <img class="avatar" src="${post.user.avatar}?imageView2/1/w/40/h/40" alt=""> </td> <td width="auto"> <a href="" style="font-size: 12px">${post.user.username}</a> <span style="font-size: 12px" class="reply">"+time+"</span><br><p style="font-size: 14px">${reply.content}</p></td> <td width="70" align="right" style="font-size: 12px"> <a href="javascript:;" rel="${status.count}" class="replyLink" title="回复"><i class="fa fa-reply"></i></a>&nbsp;<span class="badge">${status.count}</span> </td> </tr> </table> </div>';--%>
                        window.location.href= "/showPost?postId=${post.id}"
                        }

                    },
                    error:function () {
                        alert("服务器异常");
                    },
                    complete:function () {
                        $("#replyBtn").text("回复").removeAttr("disabled");
                    }
                });
            }
        });

        moment.locale("zh-cn");
        $("#showTime").text(moment("${post.createtime}").fromNow());
        $("#lastreplytime").text(moment("${requestScope.post.lastreplytime}").format("YYYY年MM月DD日 HH:mm:ss"));
        $(".reply").text(function () {
            var time = $(this).text(${reply.replytime});
            return moment(time).fromNow();
        });
        $("#thank").click(function () {
            var $this = $(this);
            var action = "";
            if($this.text() == "感谢"){
                action = "thank";
            }else if($this.text() == "取消感谢"){
                action = "remove";
            }
            $.get("/thank?postId=${post.id}&type=" + action +"").done(function (json) {
                if(json.state == "success"){
                    if(action == "thank"){
                        $this.text("取消感谢");
                    }else {
                        $this.text("感谢");
                    }
                    $("#thankNum").text(json.data.thanknum + "人感谢");
                }
            }).error(function () {
                alert("服务器异常");
            });
        });
        $("#save").click(function () {
            var $this = $(this);
            var action = "";
            if($this.text() == "加入收藏"){
                action="save";
            }else if($this.text() == "取消收藏"){
                action="delete";
            }
            $.get("/postSave?postId=${post.id}&type="+action+"").done(function (json) {
                if(json.state == "success"){
                    if(action == "save"){
                        $this.text("取消收藏");
                    }else {
                        $this.text("加入收藏");
                    }
                    $("#saveNum").text(json.data.savenum + "人收藏");
                }
            }).error(function () {
                alert("服务器异常");
            });
        });
        hljs.initHighlightingOnLoad();

    });
</script>

</body>
</html>
