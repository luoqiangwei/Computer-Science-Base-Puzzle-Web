<%--
  Created by IntelliJ IDEA.
  User: OVEA
  Date: 7/31/2019
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>计算机科学基础知识答题网 - 验证</title>
    <jsp:include page="templates/setting.html"></jsp:include>
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
    <h1 class="register">账号验证</h1>
    <div class="form-group" id="rform">
        <p class="perr">${form}</p>
        <form action="/Register" method="post">
            <input type="hidden" name="method" value="check">
            <input type="hidden" name="token" value="${token}">

            <label for="rverifycode">验证码（验证码已经自动发送）<p class="perr">${verifycode}</p></label>
            <input type="password" name="verifycode" class="form-control" placeholder="Enter verify code" id="rverifycode" value="${rverifycode}"><br>
            <br>
            <input type="submit" class="btn btn-primary" value="提交">
        </form>
    </div>
</div>
<jsp:include page="templates/foot.html"></jsp:include>
</body>
</html>
