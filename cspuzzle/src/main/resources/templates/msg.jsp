<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 2018/8/5
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>计算机科学基础知识答题网 - 消息</title>
    <jsp:include page="templates/setting.html"></jsp:include>
    <meta http-equiv="refresh" content="1; URL=${toUrl}">
</head>
<body>
<jsp:include page="templates/top.html"></jsp:include>
<div class="center">
    <h1>${msg}</h1>
</div>
<jsp:include page="templates/foot.html"></jsp:include>
</body>
</html>
