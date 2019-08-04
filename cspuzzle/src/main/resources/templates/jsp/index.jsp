<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 2018/7/18
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>计算机科学基础知识答题网-主页</title>
    <jsp:include page="templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="/css/index.css">
</head>
<body>
<jsp:include page="templete/top.jsp"></jsp:include>
    <div class="center">
        <div class="menu">
            <c:if test="${userInfo != null}">
                <c:if test="${isAdmin != null}">
                    <div class="form-group">
                        <a href="/jsp/admin/index.jsp"><button class="btn btn-light long">管理页面</button></a>
                    </div>
                </c:if>
                <c:if test="${isTea != null}">
                    <div class="form-group">
                        <a href="/jsp/teacher/bulletin/bulletin.jsp"><button class="btn btn-light long">公告发布</button></a>
                    </div>
                </c:if>
                <div class="form-group">
                    <a href="/Punch?method=findByUser"><button class="btn btn-light long">打卡</button></a>
                </div>
                <div class="form-group">
                    <a href="/LearningRecords?method=findByUser"><button class="btn btn-light long">编写日志</button></a>
                </div>
            </c:if>
        </div>
        <c:if test="${BulletinInfo != null}">
        <div class="bulletin form-control">
            <c:forEach var="item" items="${BulletinInfo}">
                <h3>${item.title}</h3><time>${item.release_time}</time><br><br>
                <p>${item.content}</p>
            </c:forEach>
        </div>
        </c:if>
    </div>
<jsp:include page="templete/foot.html"></jsp:include>
</body>
</html>
