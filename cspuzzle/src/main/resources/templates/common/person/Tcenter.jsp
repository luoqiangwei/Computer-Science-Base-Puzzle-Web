<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 2018/8/10
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>个人中心</title>
    <jsp:include page="/jsp/templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="/css/common/person/tcenter.css">
    <script type="text/javascript" src="/js/common/person/Tcenter.js"></script>
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
    <div class="base" <c:if test="${TCType == 1}">style="display: none;" </c:if>>
        <h3>基本信息</h3>
        <div class="form-group">
            <p class="perr">${form}</p>
            <form action="/TeacherCenter" method="post">
                <input type="hidden" name="method" value="baseInfoChange">
                <div class="form-group">
                    <label for="cemail">电子邮箱</label>
                    <input type="email" name="email" class="form-control" id="cemail" value="${userInfo.email}" disabled="disabled"><br>
                </div>
                <div class="form-group">
                    <label>学号<p class="perr" id="serro">${sid}</p></label>
                    <input type="text" oninput="SidCheck();" name="sid" class="form-control" id="csid" value="${userInfo.staff_id}" placeholder="Your Staff ID"><br>
                </div>
                <div class="form-group">
                    <label>姓名</label>
                    <input type="text" name="name" class="form-control" id="cname" value="${userInfo.name}" placeholder="Your name"><br>
                </div>
                <div class="form-group">
                    <label>电话号码<p class="perr" id="perro">${phome}</p></label>
                    <input type="text" oninput="phoneNumberCheck();"  name="phoneNumber" class="form-control" id="cPhoneNumber" value="${userInfo.phone_number}" placeholder="Your Phone Number"><br>
                </div>
                <div class="form-group">
                    <label>QQ<p class="perr" id="qqerro">${qq}</p> </label>
                    <input type="text" oninput="qqCheck();" name="qq" class="form-control" id="cqq" value="${userInfo.qq}" placeholder="Your QQ"><br>
                </div>
                <div class="form-group">
                    <label>性别</label>
                    <select class="form-control" name="sex" id="csex">
                        <option <c:if test="${userInfo.sex == 'true'}">selected</c:if> value="true">男</option>
                        <option <c:if var="result" test="${userInfo.sex == 'false'}">selected</c:if> value="false">女</option>
                    </select><br>
                </div>
                <div class="form-group">
                    <input type="submit" onclick="clearAll();" class="btn btn-primary" value="修改基本信息">
                </div>
            </form>
        </div>
    </div>
    <div class="passwd" <c:if test="${TCType == 1}">style="display: block;" </c:if>>
        <h3>密码修改</h3>
        <div class="form-group">
            <p class="perr">${pform}</p>
            <form action="/TeacherCenter" method="post">
                <input type="hidden" name="method" value="passwdChange">
                <div class="form-group">
                    <label>旧密码<p class="perr">${opwd}</p></label>
                    <input type="password" name="opwd" class="form-control" id="copwd"><br>
                </div>
                <div class="form-group">
                    <label>新密码<p class="perr">${pwd}</p></label>
                    <input type="password" name="pwd" class="form-control" id="cpwd"><br>
                </div>
                <div class="form-group">
                    <label>再次输入新密码<p class="perr">${rpwd}</p></label>
                    <input type="password" name="rpwd" class="form-control" id="crpwd"><br>
                </div>
                <div class="form-group">
                    <input type="submit" class="btn btn-danger" value="修改密码">
                </div>
            </form>
        </div>
    </div>
    <div class="change">
        <button id="changeMode" onclick="<c:if test='${TCType != 1}'>changeToPass();</c:if><c:if test='${TCType == 1}'>changeToBase();</c:if>" class="btn btn-dark"><c:if test="${TCType != 1}">更改密码</c:if><c:if test="${TCType == 1}">更改基本信息</c:if></button>
    </div>
</div>
<jsp:include page="/jsp/templete/foot.html"></jsp:include>
</body>
</html>
