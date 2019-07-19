<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目求助添加</title>
<meta name="decorator" content="default" />
<style type="text/css">
.ok {
	color: green;
	font: normal normal 12px/28px '微软雅黑';
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
		$("#content").blur(function() {
			var content = $("#content").val();
			if (content.length > 255) {
				$("#contentText").text("请输入小于255字的描述！");
				$("#contentText").addClass("err");
			} else {
				$("#contentText").text("输入正确！");
				$("#contentText").removeClass("err");
				$("#contentText").addClass("ok");
			}
		})
	});
	function doSubmit(){
		var flag = 0;
		var status = $("#status").val();
		if (status == '') {
			flag = 1;
			$("#statusText").text("请选择状态类型！");
			$("#statusText").addClass("err");
		}
		var content = $("#content").val();
		if (content.length > 255) {
			flag = 1;
			$("#contentText").text("请输入小于255字的描述！");
			$("#contentText").addClass("err");
		}
		if(flag==0){
			$("#addForm").submit();
		}
	}
	
	function testStatus(){
		var status = $("#status").val();
		if (status == '') {
			$("#statusText").text("请输入状态分类！");
			$("#statusText").addClass("err");
		} else {
			$("#statusText").text("输入正确！");
			$("#statusText").removeClass("err");
			$("#statusText").addClass("ok");
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/projectHelp/helpList?id=${pmsProjectHelp.project.projectId}">项目求助列表</a></li>
		<li class="active"><a href="">项目求助修改</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="pmsProjectHelp"
		action="${ctx }/pms/projectHelp/helpForm" method="post"
		class="form-horizontal">
		<form:hidden path="helpId" />
		<div class="control-group">
			<label class="control-label">求助项目名称:</label>
			<div class="controls">
				<form:hidden path="project.projectId" value="${pmsProjectHelp.project.projectId}" />
				<input type="text" value="${pmsProjectHelp.project.projectName }" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">求助发起者:</label>
			<div class="controls">
				<form:hidden path="helpBy" value="${fns:getUser()}" />
				<input type="text" value="${fns:getUser().name}" htmlEscape="false" maxlength="50" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">求助描述:</label>
			<div class="controls">
				<form:textarea id="content" path="content" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
				<span id="contentText" class="help-inline"></span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">协助人员:</label>
			<div class="controls">
                <sys:treeselect id="user0" name="helper.id" value="${pmsProjectHelp.helper.id}" labelName="helper.name" labelValue="${pmsProjectHelp.helper.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">求助状态:</label>
			<div class="controls">
				<form:select id="status" path="status" class="input-medium" onchange="testStatus()">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('project_help_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span id="statusText" class="help-inline"></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>
</body>
</html>