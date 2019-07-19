<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<title>采购申请管理</title>
<script type="text/javascript" src="${ctxStatic}/purchase/add.js"></script>
<script type="text/javascript">
function upload(){
	var file = $("input[name='applyAddress']").val("");
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
		<li><a href="${ctx}/tb/purchase/list">采购申请列表</a></li>
		<li class="active"><a href="#">采购申请表</a></li>		
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/purchase/list2">所属区域员工采购列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="caiwu">
			<li class="active"><a href="${ctx}/tb/purchase/list4">查看员工申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="purchase" name="form" enctype="multipart/form-data"
		action="${ctx}/tb/purchase/toAdd" method="post" class="form-horizontal" >
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<input type="hidden" value="${purchase.applyAddress }" name="applyAddress">
		<fieldset>
			<legend>采购申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
						${purchase.user.name}
					</td>
					<td class="tit">部门</td>
					<td>
						${purchase.office.name}
					</td>
				</tr>
				<tr>
					<td class="tit">物资名称</td>
					<td>
						<input id="pName" type="text" name="pName" value="${purchase.pName }">
						<span class="warn" id="pNameWarn" style="color: red"></span>
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${purchase.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">合计金额</td>
					<td>
						<input id="money" name="money" type="text" value="${purchase.money }">元
						<span class="warn" id="moneyWarn" style="color: red"></span>
					</td>
					<td class="tit">采购日期</td>
					<td>
						<input id="useTime" name="purchaseDate" type="text"
						readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${purchase.purchaseDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						
					</td>
				<tr>
					<td class="tit">文件上传</td>
					<td>
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width:180px">
							<input type="button" value="确认上传" onclick = "upload()">
						 	<progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
						</form>
					</td>					
				</tr>
				<tr>
					<td class="tit" >备注</td>
					<td colspan="3">
					<textarea name="report"
							rows="5" maxlength="2000" cssStyle="width:500px">${purchase.report}</textarea>
					
					</td> 
				</tr>
			</table>
		</fieldset>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty purchase.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty purchase.id}">
			<act:histoicFlow procInsId="${purchase.act.procInsId}" />
		</c:if>
	</form:form>
	 <br>
	<b>填写注意事项：</b><br>
		&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: red;">备注栏需写明采购目的、详情及其他相关内容。</span>
	
</body>
</html>