<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>团建管理</title>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="">团建列表</a></li>
    <li><a href="${ctx}/tb/party/toAdd">团建申请</a></li>
    <shiro:hasAnyRoles name="caiwu">
        <li class="active"><a href="${ctx}/tb/party/getAllList">员工团建申请列表</a></li>
    </shiro:hasAnyRoles>
</ul>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead><tr>
        <th>姓名</th>
        <th>计划团建时间</th>
        <th>计划团建人数</th>
        <th>本次团建经费预算</th>
        <th>申请时间</th>
        <th>流程状态</th>
        <th>操作</th>
    </tr></thead>
    <tbody>
    <c:forEach items="${page.list}" var="party">
        <tr>
            <td>${party.createBy.name}</td>
            <td>
                <fmt:formatDate value="${party.planTime}" type="both" pattern="yyyy-MM-dd"/>
            </td>
            <td>
                    ${party.planCount}
            </td>
            <td>
                    ${party.budget }
            </td>
            <td><fmt:formatDate value="${party.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
            <td>${party.statu}</td>
            <td>
                <a href="${ctx}/tb/party/form?id=${party.id}">详情</a>
                <c:if test="${party.status=='0'}">
                    <a href="${ctx }/tb/roll/withdraw?id=${party.id }&tableName=tb_party&view=party">撤回</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>