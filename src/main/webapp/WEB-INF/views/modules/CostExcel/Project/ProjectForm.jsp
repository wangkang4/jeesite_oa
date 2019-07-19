<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存成功管理</title>
	<meta name="decorator" content="default"/>
	<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"
	type="text/javascript"></script>
<style type="text/css">
.ok {
	color: green;
	font: normal normal 12px/28px '微软雅黑';
	padding: 0px 5px;
}

b {
	color: red;
	font: normal normal 15px/28px '微软雅黑';
	padding: 0px 5px;
}

.err {
	color: red;
	font: normal normal 12px/28px '微软雅黑';
	padding: 0px 5px;
}
</style>			
			
	<script type="text/javascript">
	
	$(function() {
		$("#projectName").blur(function() {
			var projectName = $("#projectName").val();
			if (projectName.length < 1) {
				$("#a").html("请输入客户名称！");
				$("#a").addClass("err");
				$("#projectName").focus();
			} else if (projectName.length >= 1) {
				$("#a").html("输入符合！");
				$("#a").removeClass("err");
				$("#a").addClass("ok");
			}
		});
		$("#projectClientId").blur(function() {
			var projectClientId = $("#projectClientId").val();
			if (projectClientId.length < 1) {
				$("#b").html("请选择项目所属客户名称！");
				$("#b").addClass("err");
				$("#projectClientId").focus();
			} else if (projectClientId.length >= 1) {
				$("#b").html("输入符合！");
				$("#b").removeClass("err");
				$("#b").addClass("ok");
			}
		});
	});
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oa/client/project/list">客户管理列表</a></li>
		<li class="active"><a href="${ctx}/oa/client/project/form">客户修改</a></li>
	</ul><br/>
	<input id="sign" type="hidden" />
	<form:form id="searchForm" modelAttribute="project" action="${ctx}/oa/client/project/update" method="post" class="form-horizontal">
		<form:hidden path="id" value="${project.id }"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">项目名称：</label>
			<div class="controls">
				<form:input id="projectName" path="projectName" value="${project.projectName }"/>
				<span id="a" class="help-inline"><font color="red">*</font>必填 </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目所属客户名称：</label>
			<div class="controls">
				<select id="projectClientId" name="client.id">
					<option value="${project.client.id }">${project.client.clientName }</option>
					<c:forEach var="client" items="${clientList }">
						<option value="${client.id }">${client.clientName }</option>
					</c:forEach>
				</select>
				<span id="b" class="help-inline"><font color="red">*</font>必填 </span>
			</div>
		</div>			
			<div class="control-group">
			<label class="control-label">项目负责人:</label>
			<div class="controls">
                <sys:treeselect id="projectManager" name="projectManager.id" value="${project.projectManager.id}" labelName="projectManager.name" labelValue="${project.projectManager.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目金额：</label>
			<div class="controls">
				<form:input path="projectMoney" value="${project.projectMoney }"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>
	
	<script type="text/javascript">
	function doSubmit() {
		$("#sign").val(0);
		var projectName = $("#projectName").val();
		if (projectName.length < 1) {
			$("#a").html("请输入客户名称！");
			$("#a").addClass("err");
			$("#sign").val(0);
			$("#projectName").focus();
			return;
		} else if (projectName.length >= 1) {
			$("#a").html("输入符合！");
			$("#a").removeClass("err");
			$("#a").addClass("ok");
			$("#sign").val(1);
		}
		
		var projectClientId = $("#projectClientId").val();
		if (projectClientId.length < 1) {
			$("#b").html("请输入项目所属客户名称！");
			$("#b").addClass("err");
			$("#sign").val(0);
			$("#projectClientId").focus();
			return;
		} else if (projectClientId.length >= 1) {
			$("#b").html("输入符合！");
			$("#b").removeClass("err");
			$("#b").addClass("ok");
			$("#sign").val(1);
		}
		
		var sign = $("#sign").val();
		if (sign == 1) {
			$("#searchForm").submit();
		}
	}
	</script>
</body>

</html>