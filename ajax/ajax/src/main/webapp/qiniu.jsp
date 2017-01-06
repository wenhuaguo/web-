<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Acer
  Date: 2016/12/10
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="http://up-z1.qiniu.com/" method="post" enctype="multipart/form-data">
    <input type="hidden" name="token" value="${token}">
    <input type="hidden" name="x:userid" value="1001">
    <input type="file" name="file">
    <button id="btn">上传</button>
</form>
<c:if test="${not empty fileName}">
    <img src="http://ohwsqq8z2.bkt.clouddn.com/${fileName}" alt="">
</c:if>
</body>
</html>
