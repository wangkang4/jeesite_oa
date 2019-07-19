<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>付款单管理</title>

    <script type="text/javascript" src="${ctxStatic}/tb/pay/addPay.js?ver=4"></script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx }/tb/pay/list">对外付款列表</a></li>
    <li class="active"><a href="#">对外付款申请流程</a></li>
    <shiro:hasAnyRoles name="seeEmployee,caiwuzhongnan">
        <li><a href="${ctx}/tb/pay/employeesList">员工对外付款列表</a></li>
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="xingzheng">
        <li class=""><a href="${ctx}/tb/pay/payList2">所属区域员工对外付款列表</a></li>
    </shiro:hasAnyRoles>
</ul>
<form:form id="inputForm" modelAttribute="pay" name="form"
           enctype="multipart/form-data" action="${ctx}/tb/pay/add" method="post"
           class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="act.taskId"/>
    <form:hidden path="act.taskName"/>
    <form:hidden path="act.taskDefKey"/>
    <form:hidden path="act.procInsId"/>
    <form:hidden path="act.procDefId"/>
    <form:hidden id="flag" path="act.flag"/>
    <input name="bigType" type="hidden" value="${pay.payTypeBig }">
    <input name="smallType" type="hidden" value="${pay.payTypeSmall }">
    <sys:message content="${message}"/>
    <input type="hidden" value="${pay.contractId }" id="contractId"/>
    <input type="hidden" value="${pay.applyAddress }" name="applyAddress"/>
    <fieldset>
        <legend>对外付款申请</legend>
        <div>
            <table class="table-form">
                <tr>
                    <td class="tit">关联合同</td>
                    <td colspan="3"><select name="contractId"
                                            class="input-medium" onchange="chooseContract()">
                        <option value="">请选择</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="tit">合同名称</td>
                    <td><input type="text" style="width: 200px;"
                               name="projectName" value="${pay.projectName }"> <span
                            class="warn" id="projectNameWarn" style="color: red"></span></td>
                    <td class="tit">合同编号</td>
                    <td><input type="text" style="width: 200px;"
                               name="projectNum" value="${pay.projectNum }"> <span
                            class="warn" id="projectNumWarn" style="color: red"></span></td>

                </tr>
                <tr>
                    <td class="tit">合同总金额</td>
                    <td><input type="text" style="width: 200px;" name="money"
                               value="${pay.money }">元 <span class="warn" id="moneyWarn"
                                                             style="color: red"></span></td>
                    <td class="tit">合同签署时间</td>
                    <td><input id="useTime" name="projectDate" type="text"
                               readonly="readonly" maxlength="20" class="input-medium Wdate"
                               value="<fmt:formatDate value="${pay.projectDate}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                        <span class="warn" id="projectDateWarn" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td class="tit">本次应付款金额</td>
                    <td><input type="text" style="width: 200px;" name="payMoney"
                               value="${pay.payMoney }">元 <span class="warn"
                                                                id="payMoneyWarn" style="color: red"></span></td>
                    <td class="tit">本次最晚付款时间</td>
                    <td><input id="useTime" name="lastTime" type="text"
                               readonly="readonly" maxlength="20" class="input-medium Wdate"
                               value="<fmt:formatDate value="${pay.projectDate}" pattern="yyyy-MM-dd"/>"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                        <span class="warn" id="lastTimeWarn" style="color: red"></span></td>
                </tr>
                <tr>
                    <td class="tit">付款类别</td>
                    <td><select name="payTypeBig" class="input-medium"
                                onchange="changeType()">
                        <option value="">请选择</option>
                    </select> <span class="warn" id="payTypeBigWarn" style="color: red"></span>
                    </td>
                    <td class="tit">付款类型</td>
                    <td><select name="payTypeSmall" class="input-medium">
                        <option value="">请选择</option>
                    </select> <span class="warn" id="payTypeSmallWarn" style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td class="tit">付款方式</td>
                    <td><select name="payMethods" class="input-medium"
                                onchange="changePay()">
                        <option value="">请选择</option>
                        <c:choose>
                            <c:when test="${pay.payMethods==1 }">
                                <option selected value="1">现金</option>
                                <option value="2">电汇</option>
                                <option value="3">延期支票</option>
                            </c:when>
                            <c:when test="${pay.payMethods==2 }">
                                <option value="1">现金</option>
                                <option selected value="2">电汇</option>
                                <option value="3">延期支票</option>
                            </c:when>
                            <c:when test="${pay.payMethods==3 }">
                                <option value="1">现金</option>
                                <option value="2">电汇</option>
                                <option selected value="3">延期支票</option>
                            </c:when>
                            <c:otherwise>
                                <option value="1">现金</option>
                                <option value="2">电汇</option>
                                <option value="3">延期支票</option>
                            </c:otherwise>
                        </c:choose>
                    </select> <span class="warn" id="payMethodsWarn" style="color: red"></span>
                    </td>
                    <td class="tit">已付款金额</td>
                    <td><input name="amountPaid" type="text"
                               value="${pay.amountPaid }">元 <span class="warn"
                                                                  id="amountPaidWarn" style="color: red"></span></td>
                </tr>
                <tr>
                    <td class="tit">收款人名称</td>
                    <td><input name="payeeName" type="text"
                               value="${pay.payeeName }"> <span class="warn"
                                                                id="payeeNameWarn" style="color: red"></span></td>
                    <td class="tit">收款人账户</td>
                    <td><input name="payeeAccount" type="text"
                               value="${pay.payeeAccount }"> <span class="warn"
                                                                   id="payeeAccountWarn" style="color: red"></span></td>
                </tr>
                <tr>
                    <td class="tit">申请人</td>
                    <td>${pay.createBy.name }</td>
                    <td class="tit">申请日期</td>
                    <td><fmt:formatDate value="${pay.createDate}" type="both"
                                        pattern="yyyy-MM-dd"/></td>
                </tr>
                <tr>
                    <td class="tit" colspan="2">申请人公司</td>
                    <td colspan="2" align="center"><select name="ename">
                        <option value="${pay.ename}">${pay.ename}</option>
                        <c:if test="${pay.ename=='北京桃花岛'}">
                            <option value="安徽桃花岛">安徽桃花岛</option>
                        </c:if>
                        <c:if test="${pay.ename =='安徽桃花岛'}">
                            <option value="北京桃花岛">北京桃花岛</option>
                        </c:if>
                    </select></td>
                </tr>
                <tr>
                    <td class="tit">应付款情况说明</td>
                    <td colspan="3"><form:textarea path="notes" rows="5"
                                                   maxlength="2000" cssStyle="width:500px"/> <span class="warn"
                                                                                                   id="notesWarn"
                                                                                                   style="color: red"></span>
                    </td>
                </tr>
                <tr>
                    <td class="tit">附件</td>
                    <td colspan="3">
                        <form style="margin-bottom: 0px;" action=""
                              enctype="multipart/form-data" method="post">
                            <input type="file" id="file1" name="file" style="width: 180px">
                            <input type="button" value="确认上传" onclick="upload()">
                            <progress value="0" max="100" id="progress"
                                      style="height: 20px; width: 100%"></progress>
                        </form>
                    </td>
                </tr>

        </div>
        </table>
        </div>
    </fieldset>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit"
               value="提交申请" onclick="return save()"/>&nbsp;
        <c:if test="${not empty pay.id}">
            <input id="btnSubmit2" class="btn btn-inverse" type="submit"
                   value="销毁申请" onclick="return stop()"/>&nbsp;
        </c:if>
        <input id="btnCancel" class="btn" type="button" value="返 回"
               onclick="history.go(-1)"/>
    </div>
    <c:if test="${not empty pay.id}">
        <act:histoicFlow procInsId="${pay.act.procInsId}"/>
    </c:if>
</form:form>
</body>
</html>