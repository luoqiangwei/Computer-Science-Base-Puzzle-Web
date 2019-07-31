<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 2018/8/5
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>计算机科学基础知识答题网 - 注册</title>
<%--    <script src="../scripts/register.js" type="text/javascript"></script>--%>
    <jsp:include page="templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="../css/register.css">
    <script type="text/javascript">
        function reloadCode() {
            var time = new Date().getTime();
            document.getElementById("imageCode").src="<c:url value='/VerifyCodeServlet?t='></c:url>" + time;
        }
    </script>
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
<h1 class="register">注册</h1>
    <div class="form-group" id="rform">
        <p class="perr">${form}</p>
        <form action="/Register" method="post">
            <input type="hidden" name="method" value="register">
            <input type="hidden" name="token" value="${token}">

            <label for="rphone">电话号码<p class="perr">${phone}</p></label>
            <input type="number" name="phone" class="form-control" placeholder="Enter phone" id="rphone" value="${rphone}"><br>

            <label for="rpassword">密码<p class="perr">${password}</p></label>
            <input type="password" name="password" class="form-control" placeholder="Password" id="rpassword" value="${rpassword}"><br>

            <label for="rrpassword">再输入一次密码</label>
            <input type="password" name="repassword" class="form-control" placeholder="Password" id="rrpassword"><br>

            <label for="rvcode">验证码<p class="perr">${vcode}</p></label>
            <input type="text" name="vcode" class="form-control" placeholder="Enter Verify Code" id="rvcode">
            <a href="javascript:reloadCode()" ID="ricode"><img id="imageCode" src="<c:url value='/VerifyCodeServlet'></c:url>" alt="Verify Code"></a><br>

            <br>
            <input type="submit" class="btn btn-primary" value="提交">
        </form>
    </div>
</div>
<jsp:include page="templete/foot.html"></jsp:include>
</body>
</html>
