<%@ page language="java" pageEncoding="utf-8" %>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>付款管理</title>
    <script type="text/javascript" src="${ctxStatic}/tb/pay/payView.js?ver=1"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="#"> <shiro:hasPermission
            name="oa:testAudit:edit">任务处理</shiro:hasPermission>

    </a></li>
</ul>
<form:form id="inputForm" modelAttribute="pay"
           action="${ctx}/tb/pay/saveAudit" method="post"
           class="form-horizontal">
<input type="hidden" name="taskids" value="${taskId}">
    <form:hidden path="id"/>
    <form:hidden path="act.taskId"/>
    <form:hidden path="act.taskName"/>
    <form:hidden path="act.taskDefKey"/>
    <form:hidden path="act.procInsId"/>
    <form:hidden path="act.procDefId"/>
    <form:hidden id="flag" path="act.flag"/>
    <sys:message content="${message}"/>
<fieldset>
    <legend>对外付款单申请表</legend>
    <table class="table-form">
        <tr>
            <td class="tit">标题</td>
            <td colspan="3">
                    ${pay.reason }
            </td>
        </tr>
        <tr>
            <td class="tit">合同名称</td>
            <td>
                <c:if test="${not empty pay.contractId }">
                    <a href="${ctx}/tb/contract/form?id=${pay.contractId}">${pay.projectName}</a>
                </c:if>
                <c:if test="${empty pay.contractId }">
                    ${pay.projectName}
                </c:if>
            </td>

            <td class="tit">合同编号</td>
            <td>
                <c:if test="${not empty pay.contractId }">
                    <a href="${ctx}/tb/contract/form?id=${pay.contractId}">${pay.projectNum}</a>
                </c:if>
                <c:if test="${empty pay.contractId }">
                    ${pay.projectNum}
                </c:if>
            </td>
        </tr>
        <tr>
            <td class="tit">合同总金额</td>
            <td>
                <fmt:formatNumber type="number" value="${pay.money}" maxFractionDigits="2" pattern="#.00"/>元
            </td>
            <td class="tit">合同签署时间</td>
            <td>
                <fmt:formatDate value="${pay.projectDate}" type="both" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <td class="tit">本次应付款金额</td>
            <td>
                <fmt:formatNumber type="number" value="${pay.payMoney}" maxFractionDigits="2" pattern="#.00"/>元
            </td>
            <td class="tit">本次最晚付款时间</td>
            <td>
                <fmt:formatDate value="${pay.lastTime}" type="both" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <td class="tit">付款类别</td>
            <td>
                <input name="payTypeBig" type="hidden" value=${pay.payTypeBig }>
            </td>
            <td class="tit">付款类型</td>
            <td>
                <input name="payTypeSmall" type="hidden" value=${pay.payTypeSmall }>
            </td>
        </tr>
        <tr>
            <td class="tit">付款方式</td>
            <td>
                <c:if test="${pay.payMethods==2 }">转账</c:if>
                <c:if test="${pay.payMethods==1 }">现金</c:if>
                <c:if test="${pay.payMethods==3 }">延期支票</c:if>
            </td>
            <td class="tit">已付款金额</td>
            <td>
                <fmt:formatNumber type="number" value="${pay.amountPaid }" maxFractionDigits="2" pattern="0.00"/>元
            </td>
        </tr>
        <tr>
            <td class="tit">收款人名称</td>
            <td>${pay.payeeName }</td>
            <td class="tit">收款人账户</td>
            <td>${pay.payeeAccount }</td>
        </tr>
        <tr>
            <td class="tit">申请人</td>
            <td>${pay.createBy.name}</td>
            <td class="tit">申请日期</td>
            <td>
                <fmt:formatDate value="${pay.createDate}" type="both" pattern="yyyy-MM-dd"/>
            </td>
        </tr>
        <tr>
            <td class="tit" colspan="2">申请人公司</td>
            <td colspan="2" align="center">
                    ${pay.ename }
            </td>
        </tr>
        <tr>
            <td class="tit">应付款情况说明</td>
            <td colspan="3">${pay.notes }</td>
        </tr>
        <tr>
            <td class="tit">附件</td>
            <td colspan="3">
                <c:if test="${not empty pay.applyAddress }">
                    <a href="${ctx}/tb/pay/download?id=${pay.id}">附件下载</a>
                </c:if>
                <c:if test="${empty pay.applyAddress }">
                    无附件
                </c:if>
            </td>
        </tr>
        <c:if test="${not empty pay.proneText }">
            <tr>
                <td class="tit">部门经理审核意见</td>
                <td colspan="3">${pay.proneText}</td>
            </tr>
        </c:if>
        <c:if test="${not empty pay.prtwoText }">
            <tr>
                <td class="tit">研发总监审核意见</td>
                <td colspan="3">${pay.prtwoText}</td>
            </tr>
        </c:if>
        <c:if test="${not empty pay.prthreeText }">
            <tr>
                <td class="tit">主管审核意见</td>
                <td colspan="3">${pay.prthreeText}</td>
            </tr>
        </c:if>
        <c:if test="${not empty pay.prsixText}">
            <tr>
                <td class="tit">商务主管意见</td>
                <td>${pay.prsixText}</td>
            </tr>
        </c:if>
        <c:if test="${not empty pay.prfourText }">
            <tr>
                <td class="tit">财务总监审核意见</td>
                <td colspan="3">${pay.prfourText}</td>
            </tr>
        </c:if>
        <c:if test="${not empty pay.prsevText }">
            <tr>
                <td class="tit">出纳审核意见</td>
                <td colspan="3">${pay.prsevText}</td>
            </tr>
        </c:if>
        <c:if test="${not empty pay.prfiveText }">
            <tr>
                <td class="tit">总经理审核意见</td>
                <td colspan="3">${pay.prfiveText}</td>
            </tr>
        </c:if>


        <tr>
            <td class="tit">您的意见</td>
            <td colspan="3"><form:textarea path="act.comment"
                                           rows="5" maxlength="500" cssStyle="width:500px"/>
            </td>
        </tr>
    </table>
</fieldset>
<div class="form-actions">
    <c:choose>
        <c:when test="${fns:getUser().loginName=='李晓萌'}">
            <input id="btnSubmit" class="btn btn-primary" type="submit"
                   value="已付款" onclick="$('#flag').val('yes')"/>&nbsp;
        </c:when>
        <c:otherwise>
            <input id="btnSubmit" class="btn btn-primary" type="submit"
                   value="同 意" onclick="$('#flag').val('yes')"/>&nbsp;
        </c:otherwise>
    </c:choose>

    <input id="btnSubmit" class="btn btn-inverse" type="submit"
           value="驳 回" onclick="$('#flag').val('no')"/>&nbsp;
    <input id="btnCancel" class="btn" type="button"
           value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'"/>
</div>
    <act:histoicFlow procInsId="${pay.act.procInsId}"/>
</form:form>
</script>
<
/body>
< /html>