<%--
  Created by IntelliJ IDEA.
  User: QiangweiLuo
  Date: 8/14/2018
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑学习记录</title>
    <jsp:include page="/jsp/templete/setting.html"></jsp:include>
    <link rel="stylesheet" href="/css/common/record/learningRecodes.css">
</head>
<body>
<jsp:include page="/jsp/templete/top.jsp"></jsp:include>
<div class="center">
    <div class="form-control">
        <h3>查看或修改学习记录</h3><p class="perr">${form}</p>
        <form action="/LearningRecords" method="post">
            <input type="hidden" name="method" value="update">
            <input type="hidden" name="rid" value="${editRecord.record_id}">
            <div class="form-group">
                <input type="text" class="form-control" name="title" placeholder="Enter title" value="${editRecord.title}">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="remake" placeholder="Enter remake" value="${editRecord.remarks}">
            </div>
            <div class="form-group">
                <textarea class="form-control area" name="content" placeholder="Enter content">${editRecord.content}</textarea>
            </div>
            <div class="form-group">
                <input type="submit" class="form-control btn btn-primary" value="修改">
            </div>
        </form>
    </div>
</div>
<jsp:include page="/jsp/templete/foot.html"></jsp:include>
</body>
</html>
