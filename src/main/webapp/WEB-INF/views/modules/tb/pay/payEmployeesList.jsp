<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>对外付款管理</title>
    <script type="text/javascript">
        $(document).ready(function () {

        });

        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var text = "";
            //实时筛选，不用点击按钮
            setInterval(function () {
                text = $('#input').val();//获取文本框输入
                if ($.trim(text) != "") {
                    $("#contentTable tr:not('#theader')").hide().filter(":contains('" + text + "')").show();
                } else {
                    $('#contentTable tr').show();//当删除文本框的内容时，又重新显示表格所有内容
                }
            }, 100);
        })
    </script>
</head>
<body>
<ul class="nav nav-tabs" id="list">
    <li><a href="${ctx}/tb/pay/list">对外付款列表</a></li>
    <li><a href="${ctx}/tb/pay/toAdd">对外付款申请流程</a></li>
    <li class="active"><a href="">员工对外付款列表</a></li>
    <shiro:hasAnyRoles name="xingzheng">
        <li class=""><a href="${ctx}/tb/pay/payList2">所属区域员工对外付款列表</a></li>
    </shiro:hasAnyRoles>
</ul>
<form:form id="searchForm" modelAttribute="pay"
           action="${ctx}/tb/pay/employeesList" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>起始时间:</label>
    <input id="startTime1" name="st" type="text" readonly="readonly" maxlength="20"
           class="input-medium Wdate "
           value="<fmt:formatDate value="${st }" pattern="yyyy-MM-dd"/>"
           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
    <label>结束时间:</label>
    <input id="endTime1" name="et" type="text" readonly="readonly" maxlength="20"
           class="input-medium Wdate "
           value="<fmt:formatDate value="${et }" pattern="yyyy-MM-dd"/>"
           onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
    <label>申请人:</label>
    <sys:treeselect id="createBy" name="createBy.id" value="${createBy.id}" labelName="createBy.name"
                    labelValue="${createBy.name}"
                    title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
                    notAllowSelectParent="true"/>
    <label>关键字：</label>
    <input type="text" name="" id="input" value="" placeholder="请输入查询关键字"/>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="button" class="btn btn-primary" value="清除"
           onclick="javascript:window.location.href= '${ctx}/tb/pay/employeesList'">
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>姓名</th>
        <th>合同名称</th>
        <th>本次应付款金额</th>
        <th>申请时间</th>
        <th>流程状态</th>
        <th>操作</th>
        <th>附件</th>
        <th>回执附件</th>
        <shiro:hasAnyRoles name="seeEmployee,thd_chuna">
            <th>附件上传</th>
        </shiro:hasAnyRoles>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="pay">
        <tr>
            <td>${pay.createBy.name}</td>
            <td>${pay.projectName}</td>
            <td>
                <fmt:formatNumber type="number" value="${pay.payMoney}" maxFractionDigits="2" pattern="#.00"/>元
            </td>
            <td>
                <fmt:formatDate value="${pay.createDate}" type="both" pattern="yyyy-MM-dd"/>
            </td>
            <td>${pay.statu}</td>
            <td>
                <c:choose>
                    <c:when test="${pay.statu=='驳回' }">
                        <a href="${ctx}/tb/pay/toAdd?id=${pay.id}">详情</a>
                    </c:when>
                    <c:otherwise>
                        <a href="${ctx}/tb/pay/form?id=${pay.id}">详情</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:if test="${pay.applyAddress!=null }">
                    <a href="${ctx}/tb/pay/download?id=${pay.id}&sign=2">用户附件下载</a>
                </c:if>
            </td>
            <td>
                <c:if test="${pay.invoicAddress!=null }">
                    <a href="${ctx}/tb/pay/download?id=${pay.id}&sign=1">银行流水单下载</a>
                </c:if>
            </td>
            <shiro:hasAnyRoles name="seeEmployee,thd_chuna">
                <td>
                    <form style="margin-bottom:0px;" action="${ctx}/tb/pay/upload" enctype="multipart/form-data"
                          method="post">
                        <input type="hidden" value="${pay.id}" name="id">
                        <input type="file" name="file" style="width:180px">
                        <input type="submit" value="确认上传">
                    </form>
                </td>
            </shiro:hasAnyRoles>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>