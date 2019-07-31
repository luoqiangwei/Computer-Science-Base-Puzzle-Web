<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<div class="header">
    <header>
        <div class="himage">
            <a href="/index?method=index"><img height="90" width="90" src="/images/all.jpg"></a>
        </div>
        <div class="htitle">
            <h1>计算机科学基础知识答题网</h1>
        </div>
        <div class="hmenu">
            <c:if test="${userInfo != null}">
                <li>${userInfo.email }</li>
                <li><a href="/Center">个人中心</a></li>
                <li><a href="/Login?method=cancellation">注销</a></li>
            </c:if>
            <c:if test="${userInfo == null}">
                <li><a href="/jsp/login.jsp" class="mainMenu">登录</a></li>
                <li><a href="/Register?method=index" class="mainMenu">注册</a></li>
            </c:if>
        </div>
    </header>
</div>