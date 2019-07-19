<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>采购申请管理</title>
    <script type="text/javascript">
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs" id="list">
    <li class="active"><a href="${ctx}/tb/purchase/list">采购申请列表</a></li>
    <li class=""><a href="${ctx}/tb/purchase/add">采购申请表</a></li>
    <%-- <li class=""><a href="${ctx}/tb/purchase/list2">员工开票申请列表</a></li>	 --%>
    <shiro:hasAnyRoles name="xingzheng">
        <li class=""><a href="${ctx}/tb/purchase/list2">所属区域员工采购列表</a></li>
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="caiwu">
        <li class="active"><a href="${ctx}/tb/purchase/list4">查看员工申请列表</a></li>
    </shiro:hasAnyRoles>
</ul>
<form:form id="searchForm" modelAttribute="purchase"
           action="" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>申请人</th>
        <th>部门</th>
        <th>物资名称</th>
        <th>申请时间</th>
        <th>附件</th>
        <th>流程状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="purchase">
        <tr>
            <td>${purchase.user.name}</td>
            <td>${purchase.office.name}</td>
            <td>${purchase.pName}</td>
            <td><fmt:formatDate value="${purchase.createDate}" type="both" pattern="yyyy-MM-dd"/></td>
            <td>
                <c:if test="${not empty purchase.applyAddress }">
                    <a href="${ctx}/tb/purchase/download?id=${purchase.id}">附件下载</a>
                </c:if>
                <c:if test="${empty purchase.applyAddress }">
                    无附件
                </c:if>
            </td>
            <td>${purchase.statu}</td>

            <td>
                <c:choose>
                    <c:when test="${purchase.statu=='驳回'}">
                        <a href="${ctx}/tb/purchase/add?id=${purchase.id}">详情</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${ctx}/tb/purchase/view?id=${purchase.id}">详情</a>
                    </c:otherwise>
                </c:choose>
                <c:if test="${empty purchase.prtwoText && empty purchase.prthreeText || purchase.statu=='驳回'}">
                    <a href="${ctx }/tb/roll/withdraw?id=${purchase.id }&tableName=tb_purchase&view=purchase">撤回</a>
                </c:if>
                    <%-- <c:choose>
                        <c:when test="${purchase.proneText!=null || purchase.prtwoText!=null || purchase.prthreeText!=null}">
                               <c:choose>
                                <c:when test="${purchase.statu=='驳回'}">
                                       <a href="${ctx}/tb/purchase/back?id=${purchase.id}">销毁申请</a>
                                </c:when>
                                <c:otherwise>
                                    不可撤销
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <a href="${ctx}/tb/purchase/back?id=${purchase.id}">销毁申请</a>
                        </c:otherwise>
                    </c:choose> --%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>