<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>加班管理</title>

    <style type="text/css">
        tr td {
            width: 5px;
            text-overflow: ellipsis;
            -moz-text-overflow: ellipsis;
            white-space: nowrap;
            overflow: hidden;
        }
    </style>
    <script type="text/javascript">
        //实时筛选，不用点击按钮
        setInterval(function () {
            text = $('#input').val();//获取文本框输入
            if ($.trim(text) != "") {
                $("#contentTable tr:not('#theader')").hide().filter(":contains('" + text + "')").show();
            } else {
                $('#contentTable tr').show();//当删除文本框的内容时，又重新显示表格所有内容
            }
        }, 100);

        function downloadOverTime() {
            var userId1 = $("#input").val();
            location.href = "${ctx}/work/overtime/ExExcel?userId1=" + userId1;
        }
    </script>
</head>
<body>
<input id="name" type="hidden" value="${name}"/>
<ul class="nav nav-tabs">
    <li id="list"><a href="${ctx}/work/overtime/">加班列表</a></li>
    <li><a href="${ctx}/work/overtime/form">加班申请流程</a></li>
    <li><a href="${ctx}/work/overtime/allList">人员加假列表</a></li>
    <li class="active"><a href="${ctx}/work/overtime/overTimeList2">上个月加班情况</a></li>
</ul>
<sys:message content="${message}"/>
<form:form id="searchForm" modelAttribute="overTime"
           action="" method="post"
           class="breadcrumb form-search">
    <label>关键字搜索：</label>
    <input type="text" name="" id="input" value="" placeholder="请输入查询关键字"/>
    <input type="button" class="btn btn-primary" value="导出上个月加班数据" onclick="downloadOverTime()">
</form:form>
<table style="table-layout:fixed;word-wrap:break-word;" id="contentTable"
       class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>姓名</th>
        <th>部门</th>
        <th>加班总时间</th>
        <th>加班原因</th>
        <th>加班类型</th>
        <th>申请时间</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${list}" var="overTime">
        <tr>
            <td><a href="${ctx}/work/overtime/form?id=${overTime.id}">${overTime.user.name}</a></td>
            <td>${overTime.office.name}</td>
            <td>
                <fmt:formatNumber type="number" value="${overTime.days}" maxFractionDigits="0"/>小时
            </td>
            <td><label><abbr style="text-decoration:none; border-bottom: 0 dotted;"
                             title="${overTime.notes} ">${overTime.notes}</abbr></label></td>
            <td>${overTime.overtimeType}</td>
            <td><fmt:formatDate value="${overTime.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><a id="href" href="${ctx}/work/overtime/${overTime.url}?id=${overTime.id}">${overTime.status }</a></td>
            <td>
                <a href="${ctx}/work/overtime/form?id=${overTime.id}">详情</a>
                    <%-- <a href="${ctx}/work/overtime/delete?id=${overTime.id}" onclick="return confirmx('确认要删除该申请吗？', this.href)">删除</a> --%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>