<%--
  Created by IntelliJ IDEA.
  User: chenkungang
  Date: 2019/5/8
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>员工基本信息</title>
</head>
<body>
<ul class="nav nav-tabs" id="list">
    <li><a href="${ctx}/tb/companyEmployeesList/ddddd">员工列表</a></li>
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead><tr>
            <th>id</th>
            <th>company_id</th>
            <th>office_id</th>
            <th>login_name</th>
            <th>name</th>
            <th>email</th>
            <th>phone</th>
        </tr></thead>
        <tbody>
        <c:forEach items="${page.list}" var="test">
            <tr>
                <td>${test.id}</td>
                <td>${test.company_id}</td>
                <td>${test.office_id}</td>
                <td>${test.login_name}</td>
                <td>${test.name}</td>
                <td>${test.email}</td>
                <td>${test.phone}</td>
            </tr>
        </c:forEach>
        </tbody>


    </table>
    <div class="pagination">${page}</div>
</body>
</html>