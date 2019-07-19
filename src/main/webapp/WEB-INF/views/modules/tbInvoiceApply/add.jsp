<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<title>开票申请管理</title>
<script type="text/javascript" src="${ctxStatic}/invoice/add.js"></script>
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
		<li><a href="${ctx}/tb/invoiceApply/list">开票申请表列表</a></li>
		<li class="active"><a href="#">开票申请表</a></li>		
		<li class=""><a href="${ctx}/tb/invoiceApply/list2">全体员工开票申请列表</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/invoiceApply/list3">所属区域员工开票申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="invoiceApply" name="form" enctype="multipart/form-data"
		action="${ctx}/tb/invoiceApply/toAdd" method="post" class="form-horizontal">
		<sys:message content="${message}" />
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<input type="hidden" value="${invoice.applyAddress }" name="applyAddress">
		<fieldset>
			<legend>开票申请</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">开票名称</td>
					<td colspan="6">
						<input type="text" name="taxName" value="${invoice.taxName }"> 
						<span class="warn" id="taxNameWarn" style="color: red"></span>
					</td>
					<%-- <td class="tit">纳税人识别号</td>
					<td>
						<input id="taxNumber" name="taxNumber" type="text" value="${invoice.taxNumber }">
						<span class="warn" id="taxNumberWarn" style="color: red"></span>
					</td>
					<td class="tit">地址</td>
					<td>
						<input name="address" value="${invoice.address }" type="text" />
						<span class="warn" id="addressWarn" style="color: red"></span>
					</td> --%>
				</tr>
				<%-- <tr>
					<td class="tit">电话</td>
					<td>
						<input name="phone" value="${invoice.phone }" type="text"/>
						<span class="warn"id="phoneWarn" style="color: red"></span>
					</td>
					<td class="tit">开户银行</td>
					<td>
						<input name="bank" type="text" value="${invoice.bank }">
						<span class="warn" id="bankWarn" style="color: red"></span>
					</td>
					<td class="tit">账号</td>
					<td>
						<input name="account" type="text" value="${invoice.account }">
						<span class="warn" id="accountWarn" style="color: red"></span>
					</td>
				</tr> --%>
				<tr>
					<td class="tit" >开票信息</td>
					<td colspan="6">
					<textarea name="invoiceInfo" cols=""
							rows="5" maxlength="2000"  style="width:1000px" >${invoice.invoiceInfo}</textarea>
					<span class="warn" id="invoiceInfoWarn" style="color: red"></span>
					</td> 
				</tr>
				<tr>
					<td class="tit">项目名称</td>
					<td>
						<input id="proname" name="proname" type="text" value="${invoice.proname }">
						<span class="warn" id="pronameWarn" style="color: red"></span>
					</td>
					<td class="tit">项目经理</td>
					<td><sys:treeselect id="manage" name="manage" value="${manageId}"
								labelName="user.name" labelValue="${manage}" title="项目经理"
								url="/sys/office/treeData?type=3" cssClass="required"
								allowClear="true" notAllowSelectParent="true" />
						<span class="warn" id="manageWarn" style="color: red"></span>
					</td>

					<td class="tit">办事处负责人</td>
					<td><sys:treeselect id="offPerson" name="offPerson"
								value="${offName}" labelName="user.name" labelValue="${offId}"
								title="办事处负责人" url="/sys/office/treeData?type=3"
								cssClass="required" allowClear="true"
								notAllowSelectParent="true" />
						<span class="warn" id="offPersonWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">本次开票金额</td>
					<td>
						<input id="total" name="total" type="text" value="${invoice.total }">元
						<span class="warn" id="totalWarn" style="color: red"></span>
					</td>
					<td class="tit">已开票金额</td>
					<td>
						<input id="alMoney" name="alMoney" type="text" value="${invoice.alMoney }">元
						<span class="warn" id="alMoneyWarn" style="color: red"></span>
					</td>
					<td class="tit">总计开票金额</td>
					<td>
						<input id="nowMoney" name="nowMoney" type="text" value="${invoice.nowMoney }">元
						<span class="warn" id="nowMoneyWarn" style="color: red"></span>
					</td>
				</tr>
				<!-- <tr>
					<td class="tit" colspan="7" type="center" style="background-color: #bbb;">邮寄信息</td>
				</tr> -->
				<tr>
					<td class="tit" >邮寄信息</td>
					<td colspan="6">
						<input name="maAddress" type="text" value="${invoice.maAddress }" style="width: 600px;">
						<span class="warn" id="maAddressWarn" style="color: red"></span>
					</td>
					<%-- <td class="tit">收件人</td>
					<td>
						<input name="colPerson" type="text" value="${invoice.colPerson }">
						<span class="warn" id="colPersonWarn" style="color: red"></span></td>
					<td class="tit">收件电话</td>
					<td>
						<input name="colPhone" type="text" value="${invoice.colPhone }">
						<span class="warn" id="colPhoneWarn" style="color: red"></span></td>
				</tr> --%>
				<tr>
					<td class="tit">文件上传</td>
					<td colspan="6">
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width:180px" multiple="multiple">
							<input type="button" value="确认上传" onclick = "upload()">
						 	<progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
						</form>
					</td>		
				
				<!-- 	<td class="tit">附件</td>
					<td colspan="2"><input type="file" name="file" style="width: 180px"  multiple="multiple"><span>*可选多文件*</span></td> -->
					
				</tr>
				<tr>
					<td class="tit">验收报告</td>					
					<td colspan="6">
						<input name="report" type="text" value="${invoice.report }">											 
					</td>
				</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty invoice.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty invoice.id}">
			<act:histoicFlow procInsId="${invoice.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>