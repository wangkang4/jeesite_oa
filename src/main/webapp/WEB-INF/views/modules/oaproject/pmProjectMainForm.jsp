<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
		var listDescription = {};
		$(document).ready(function() {
			$.ajax({
				type : 'post',
				async:false,
				url : '${ctx}/customer/pmCustomerZtree/getCustomerZtree',
				dataType : 'json',
				success : function(data) {
					listDescription = data;
				},
				error : function(data) {
					alert("错误信息");
				}
			});
		});
	
		function getCustomerZtree(btn){
			var costTypeId = $(btn).val();
			var costType=$(btn).find("option:selected").val();
			$.each(listDescription[1], function(i, item) {
				if(item.id == costType){
					$("#projectIndustry").children("input").val(item.label);
				}
			});
		}
	</script>
	<script type="text/javascript">
		function del(id){
			$.ajax({
				url:"${ctx}/oaproject/pmProjectMain/projectDocumentDel?id="+id,
				type:"POST",
				dataType:"json",
				success:function(data){
					alert(data["1"]);
			    }
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oaproject/pmProjectMain/">项目列表</a></li>
		<li class="active"><a href="${ctx}/oaproject/pmProjectMain/form">项目添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmProjectMain" action="${ctx}/oaproject/pmProjectMain/save" method="post" class="form-horizontal" enctype="multipart/form-data" >
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
				
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="200" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">项目编号：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:input path="projectId" htmlEscape="false" maxlength="200" class="input-xlarge required"/> --%>
<!-- 				<span class="help-inline"><font color="red">*</font> </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">项目类型：</label>
			<div class="controls">
				<form:select path="projectType" class="input-xlarge required" style="width: 284px;">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_class')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">关系群：</label>
			<div class="controls">
				<select name=projectCunstomer class="myselect" onchange="getCustomerZtree(this)" style="width: 284px;">
					<option value="">请选择</option>
					<c:forEach var="pmCustomerZtreeList" items="${pmCustomerZtreeList}">
						<c:if test="${pmCustomerZtreeList.id == pmProjectMain.projectCustomer}">
							<option selected="selected" value="${pmCustomerZtreeList.id}">${pmCustomerZtreeList.name}</option>
						</c:if>
						<c:if test="${pmCustomerZtreeList.id != pmProjectMain.projectCustomer}">
							<option value="${pmCustomerZtreeList.id}">${pmCustomerZtreeList.name}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目行业：</label>
			<div class="controls" id="projectIndustry">
				<form:input path="projectIndustry" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目阶段：</label>
			<div class="controls">
				<form:select path="projectStage" class="input-xlarge" style="width: 284px;">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_stage')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目金额：</label>
			<div class="controls">
				<form:input path="projectMoney" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">办事处：</label>
			<div class="controls" >
				<%-- <sys:treeselect id="projectAddress"   name="projectAddress.id" value="${pmProjectMain.projectAddress.id}" labelName="projectAddress.name" labelValue="${pmProjectMain.projectAddress.name}" 
				title="办事处" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true"/> --%>
				<select id="projectAddress" name="projectAddress" class="input-xlarge" style="width: 284px;">
					<option value="0">北京总部</option>
					<option value="1">山东办</option>
					<option value="2">安徽办</option>
					<option value="3">上海办</option>
					<option value="4">浙江办</option>
					<option value="5">福建办</option>
					<option value="6">山西办</option>
					<option value="7">内蒙古办</option>
					<option value="8">河北办</option>
					<option value="9">辽宁办</option>
				</select>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">项目概述：</label>
			<div class="controls">
				<form:textarea path="projectSummary" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件上传：</label>
			<div class="controls">
				<input type="file" name="files[]" multiple>
			</div>
		</div>
		
		<c:if test="${not empty documentList}">
		<div class="control-group">
			<label class="control-label">附件信息</label>
			<c:forEach  items="${documentList}" var="item" varStatus="status" >
				<div class="controls">
					<a href="${ctx}/oaproject/pmProjectMain/projectDocumentDownload?id=${item.id}">${item.documentName}</a>
					<a href="javascript:del('${item.id}')">x</a>
				</div>
			</c:forEach>
		</div>
		</c:if>
		
		<c:if test="${not empty pmProjectMain.reject}">
		<div class="control-group">
			<label class="control-label">驳回意见</label>
			<div class="controls">
				<form:textarea path="reject" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge " value="${pmProjectMain.reject}"/>
			</div>
		</div>
		</c:if>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>