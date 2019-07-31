<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 8/13/2018
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学习日志</title>
    <jsp:include page="/jsp/templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="/css/common/record/learningRecodes.css">
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
    <div class="form-control">
        <h3>编写学习记录</h3><p class="perr">${form}</p>
        <form action="/LearningRecords" method="post">
            <input type="hidden" name="method" value="addRecord">
            <div class="form-group">
                <input type="text" class="form-control" name="title" placeholder="Enter title">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="remake" placeholder="Enter remake">
            </div>
            <div class="form-group">
                <textarea class="form-control area" name="content" placeholder="Enter content"></textarea>
            </div>
            <div class="form-group">
                <input type="submit" class="form-control btn btn-primary" value="提交">
            </div>
        </form>
    </div><br>
    <c:forEach items="${userLrc}" var="item">
    <div class="form-group">
        <a href="/LearningRecords?method=edit&rid=${item.record_id}">
            <button class="btn btn-light form-control">
                <h3>${item.title}</h3><time>${item.release_date}</time><br>
                <%--<h6>${item.remarks}</h6><br>--%>
                <%--<p>${item.content}</p>--%>
            </button>
        </a>
    </div><br>
    </c:forEach>
</div>
<jsp:include page="/jsp/templete/foot.html"></jsp:include>
</body>
</html>
