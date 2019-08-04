<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 2018/8/10
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>404 Error</title>
    <jsp:include page="/jsp/templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="/css/error/error.css">
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
    <div class="error404">
        <c:if test="${userInfo == null}">
            <h1>我们找不到这个页面了... 您可以登入试试</h1>
        </c:if>
        <c:if test="${userInfo != null}">
            <h1>我们找不到这个页面了...</h1>
        </c:if>
    </div>
</div>
<jsp:include page="/jsp/templete/foot.html"></jsp:include>
</body>
</html>
