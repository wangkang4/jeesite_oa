<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<title>离职申请管理</title>
<script type="text/javascript" src="${ctxStatic}/quit/add.js"></script>
<script type="text/javascript">
function upload(){
	var file = $("input[name='address']").val("");
	var formData = new FormData();
	formData.append("file",$('#file1')[0].files[0]);
	var xhr;
	if(window.XMLHttpRequest){
        xhr = new XMLHttpRequest();
    }else{
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
	xhr.open("post","upload",true);
	xhr.send(formData);
	xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = xhr.responseText;
            if(result!=null&&result!=""&&result!=undefined){
            	file.val(result);
            	alertx("上传成功");
            }else{
            	alertx("上传失败");
            }
        }
    }
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/quit/list">离职申请列表</a></li>
		<li class="active"><a href="#">离职申请表</a></li>
		<%--<c:if test="${fns:getUser().loginName=='俞跃舒' || fns:getUser().loginName=='李国强'}">
			<li class=""><a href="${ctx}/tb/quit/list2">全体员工离职列表</a></li>
		</c:if>--%>
		<shiro:hasAnyRoles name="caiwuzhongnan">
			<li class=""><a href="${ctx}/tb/quit/list4">全体员工离职列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/quit/list3">所属区域员工离职列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="quit" name="form" enctype="multipart/form-data"
		action="${ctx}/tb/quit/toAdd" method="post" class="form-horizontal" >
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" /> 
		<input type="hidden" value="${quit.address }" name="address">
		<fieldset>
			<legend>离职申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${quit.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${quit.office.name}
					</td>
				</tr>
				
				<tr>
					<td class="tit">申请离职日期</td>
					<td><fmt:formatDate value="${quit.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
					<td class="tit">拟离职日期</td>
					<td>
						<input id="useTime" name="quitDate" type="text"
						readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${quit.quitDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						
					</td>
				</tr>
				<tr>
					<td class="tit">职位</td>
					<td colspan="3">
						<input id="position" type="text" name="position" value="${quit.position}">
						<span class="warn" id="positionWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit" >离职原因</td>
					<td colspan="3">
					<textarea name="reason"
							rows="5" maxlength="2000" cssStyle="width:500px">${quit.reason}</textarea>
					<span class="warn" id="reasonWarn" style="color: red"></span>
					</td> 
				</tr>
				<tr>
					<td class="tit">文件上传</td>
					<td colspan="3">
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width:180px" multiple="multiple">
							<input type="button" value="确认上传" onclick = "upload()">
						 	<progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
						</form>
					</td>					
				
					<!-- <td class="tit">附件</td>
					<td colspan="3"><input type="file" name="file" style="width: 180px" multiple="multiple"></td> -->
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty quit.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty quit.id}">
			<act:histoicFlow procInsId="${quit.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>