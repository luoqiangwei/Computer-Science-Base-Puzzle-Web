<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 8/13/2018
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>打卡</title>
    <jsp:include page="/jsp/templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="/css/common/punch/punch.css">
    <script type="text/javascript" src="/js/common/punch/punch.js"></script>
    <script type="text/javascript">onload = getLocation;</script>
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
    <div class="form-control">
        <h3>打卡</h3>
        <form action="/Punch" method="post">
            <input type="hidden" name="method" value="punch">
            <input type="hidden" name="location" value="">
            <div class="form-group">
                <input type="text" name="remake" class="form-control" placeholder="Enter your remake">
            </div>
            <div class="form-group">
                <input type="submit" id="sub" class="btn btn-light" value="打卡" disabled="disabled">
            </div>
        </form>
    </div><br>
    <c:forEach items="${userPtc}" var="item">
        <div class="form-control">
            <label>打卡IP:${item.punch_ip}</label><br>
            <label>打卡地点:${item.punch_location}</label><br>
            <label>打卡备注:${item.remarks}</label><br>
            <label>打卡日期:${item.punch_date}</label>
        </div><br>
    </c:forEach>
</div>
<jsp:include page="/jsp/templete/foot.html"></jsp:include>
</body>
</html>
