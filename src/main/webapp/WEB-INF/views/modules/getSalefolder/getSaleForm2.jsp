<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>报销单管理</title>
    <script type="text/javascript" src="${ctxStatic}/tb/sale/getSaleForm2.js?ver=5"></script>
    <script type="text/javascript">
        function upload() {
            var file = $("input[name='fileAddress']").val("");
            var formData = new FormData();
            formData.append("file", $('#file1')[0].files[0]);
            var xhr;
            if (window.XMLHttpRequest) {
                xhr = new XMLHttpRequest();
            } else {
                xhr = new ActiveXObject('Microsoft.XMLHTTP');
            }
            //进度条部分
            xhr.upload.onprogress = function (evt) {
                console.log(evt.lengthComputable);
                if (evt.lengthComputable) {
                    var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                    document.getElementById('progress').value = percentComplete;
                }
            };
            xhr.open("post", "upload1", true);
            xhr.send(formData);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var result = xhr.responseText;
                    if (result != null && result != "" && result != undefined) {
                        file.val(result);
                        alertx("上传成功");
                    } else {
                        alertx("上传失败");
                    }
                }
            }
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/get/sale/">报销列表</a></li>
    <li><a href="${ctx}/get/sale/listDraft">草稿列表</a></li>
    <li class="active"><a
            href="${ctx}/get/sale/form?id=${getSale.id}"><shiro:hasPermission
            name="oa:testAudit:edit">报销${not empty getSale.id?'修改':'申请'}流程</shiro:hasPermission>
        <shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission></a></li>
    <shiro:hasAnyRoles name="seeEmployees,caiwuzhongnan">
        <li><a href="${ctx}/get/sale/CWSubList">员工报销列表</a></li>
    </shiro:hasAnyRoles>
    <shiro:hasAnyRoles name="xingzheng">
        <li class=""><a href="${ctx}/get/sale/getSaleList2">所属区域员工申请列表</a></li>
    </shiro:hasAnyRoles>
</ul>
<form:form id="inputForm" modelAttribute="getSale" name="form1"
           action="${ctx}/get/sale/save" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <form:hidden path="act.taskId"/>
    <form:hidden path="act.taskName"/>
    <form:hidden path="act.taskDefKey"/>
    <form:hidden path="act.procInsId"/>
    <form:hidden path="act.procDefId"/>
    <form:hidden id="flag" path="act.flag"/>
    <form:hidden id="saleDetailId" path="saleDetailId"/>
    <input type="hidden" value="${getSale.fileAddress}" name="fileAddress">
    <input type="hidden" name="sNum" value="${getSale.sNum }"/>
    <sys:message content="${message}"/>
    <fieldset>
        <legend>报销申请</legend>
        <input type="button" id="btn" value="添加" onclick="appendTr()" class="btn btn-primary">
        <table id="contentTable" class="table-form">
            <tr>
                <td class="tit">姓名</td>
                <td colspan="2"><input id="user.name" name="user.id"
                                       value="${getSale.user.id }" type="hidden"> <input
                        id="userId" name="userName" type="text" readonly="true"
                        value="${getSale.user.name }"></td>
                <td class="tit">公司</td>
                <td>
                    <select name="ename">
                        <option value="${getSale.ename}">${getSale.ename}</option>
                        <c:if test="${getSale.ename =='北京桃花岛'}">
                            <option value="安徽桃花岛">安徽桃花岛</option>
                        </c:if>
                        <c:if test="${getSale.ename =='安徽桃花岛'}">
                            <option value="北京桃花岛">北京桃花岛</option>
                        </c:if>
                    </select>

                </td>
                <td class="tit">部门</td>
                <td colspan="2">
                    <input id="office.name" name="office.id" value="${getSale.office.id}" type="hidden">
                    <input id="officeId" name="officeName" type="text" value="${getSale.office.name }"
                           readonly="readonly"/>
                </td>
            </tr>
            <tr>
                <th>日期</th>
                <th>收据金额</th>
                <th>费用类型</th>
                <th>费用描述</th>
                <th>可报销金额</th>
                <th>项目名称</th>
                <th>详细信息</th>
                <th>操作</th>

            </tr>
            <tbody id="costList">

            </tbody>


            <c:if test="${not empty getSale.fileAddress }">
                <tr>
                    <td class="tit">文件</td>
                    <td colspan="7">
                        <a href="${ctx}/get/sale/downloadFiel?id=${getSale.id}">附件下载</a>
                    </td>
                </tr>
            </c:if>
            <tr>
                <td class="tit">
                    <c:if test="${not empty getSale.fileAddress }">可选新文件上传</c:if>

                    <c:if test="${empty getSale.fileAddress }">文件上传</c:if>
                </td>
                <td colspan="7">
                    <form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
                        <input type="file" id="file1" name="file" style="width:180px" multiple="multiple">
                        <input type="button" value="确认上传" onclick="upload()">
                        <progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
                    </form>
                </td>
            </tr>
            <c:if test="${not empty getSale.prText }">
                <tr>
                    <td class="tit">行政主管审核意见</td>
                    <td colspan="3">${getSale.prText}</td>
                </tr>
            </c:if>
            <c:if test="${not empty getSale.leaderText}">
                <tr>
                    <td class="tit">部门经理审核意见</td>
                    <td colspan="3">${getSale.leaderText}</td>
                </tr>
            </c:if>
            <c:if test="${not empty getSale.leadertwoText}">
                <tr>
                    <td class="tit">主管审核意见</td>
                    <td colspan="3">${getSale.leadertwoText}</td>
                </tr>
            </c:if>

            <c:if test="${not empty getSale.managerText}">
                <tr>
                    <td class="tit">研发总监审核意见</td>
                    <td colspan="3">${getSale.managerText}</td>
                </tr>
            </c:if>
            <c:if test="${not empty getSale.prthreeText }">
                <tr>
                    <td class="tit">出纳审核意见</td>
                    <td colspan="3">${getSale.prthreeText}</td>
                </tr>
            </c:if>
            <c:if test="${not empty getSale.prfourText }">
                <tr>
                    <td class="tit">财务主管审核意见</td>
                    <td colspan="3">${getSale.prfourText}</td>
                </tr>
            </c:if>
            <!--add  -->
            <c:if test="${not empty getSale.prtwoText }">
                <tr>
                    <td class="tit">财务总监审核意见</td>
                    <td colspan="3">${getSale.prtwoText}</td>
                </tr>
            </c:if>
            <c:if test="${not empty getSale.prfiveText }">
                <tr>
                    <td class="tit">总经理审核意见</td>
                    <td colspan="5">${getSale.prfiveText}</td>
                </tr>
            </c:if>
        </table>
    </fieldset>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit"
               value="提交申请" onclick="return save()"/>&nbsp;
        <c:if test="${not empty getSale.id}">
            <input id="btnSubmit2" class="btn btn-inverse" type="submit"
                   value="销毁申请" onclick="return stop()"/>&nbsp;
        </c:if>
        <c:if test="${empty getSale.prText}">
            <input id="btnSubmit3" class="btn btn-primary" type="submit"
                   value="存草稿" onclick="return saveDraft()"/>&nbsp;
        </c:if>
        <input id="btnCancel" class="btn" type="button" value="返 回"
               onclick="history.go(-1)"/>
    </div>
    <c:if test="${not empty getSale.id && getSale.status eq 1}">
        <act:histoicFlow procInsId="${getSale.act.procInsId}"/>
    </c:if>
</form:form>
1）项目名称，不得为空，市场营销人员、研发人员一定要填写，所发生费用确定无指定项目的，填：无；<br>
2）出差天数的计算（以车票所载时间为准）：<br>
&nbsp;&nbsp;&nbsp;&nbsp;去程：出差当日10：00以前出发按1天；出差当日10：00以后出发按半天；<br>
&nbsp;&nbsp;&nbsp;&nbsp;回程：出差当日14：00以前返回按半天；出差当日14：00以后返回按1天；<br>
3）详细信息：不得为空，对具体费用情况加以说明；<br>
4）差旅费报销为1公里1元，附件处上传具体行程
<table id="hint" border="1" cellspacing="0" align="center" width="100%"></table>
</body>

</html>