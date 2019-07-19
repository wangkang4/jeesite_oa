+<%@ page contentType="text/html;charset=UTF-8" %>
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
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oaproject/pmProjectMain/">项目列表</a></li>
		<li class="active"><a href="${ctx}/oaproject/pmProjectMain/form">项目添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmProjectMain" action="${ctx}/oaproject/pmProjectMain/savepeople" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		
		<div class="control-group">
			<label class="control-label">赞助人：</label>
			<div class="controls">
				<sys:treeselect id="member" name="member.id" value="${pmProjectMain.member.id}" labelName="member.name" labelValue="${pmProjectMain.member.name}"
 					title="赞助人" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div>
			
		 <div class="control-group">
 		 	<input type = "hidden" name="projectId" value="${pmProjectMain.id}" />
			<label class="control-label">项目经理：</label>
			<div class="controls">
				<sys:treeselect id="manager" name="manager.id" value="${pmProjectMain.manager.id}" labelName="manager.name" labelValue="${pmProjectMain.manager.name}"
					title="项目经理" url="/sys/office/treeData?type=3"   allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div> 
		
		
		 <div class="control-group">
			<label class="control-label">销售人员：</label>
			<div class="controls">
				<sys:treeselect id="administration" name="administration.id" value="${pmProjectMain.administration.id}" labelName="administration.name" labelValue="${pmProjectMain.administration.name}"
					title="销售人员" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">产品部人员：</label>
			<div class="controls">
				<sys:treeselect id="product" name="product.id" value="${pmProjectMain.product.id}" labelName="product.name" labelValue="${pmProjectMain.product.name}"
					title="产品部成员" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">商务人员：</label>
			<div class="controls">
				<sys:treeselect id="research" name="research.id" value="${pmProjectMain.research.id}" labelName="research.name" labelValue="${pmProjectMain.research.name}"
 					title="商务人员" url="/sys/office/treeData?type=3" allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">研发负责人：</label>
			<div class="controls">
				<sys:treeselect id="warranty" name="warranty.id" value="${pmProjectMain.warranty.id}" labelName="warranty.name" labelValue="${pmProjectMain.warranty.name}"
					title="研发负责人" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		
	<%-- 	<div class="control-group">
			<label class="control-label">商务成员：</label>
			<div class="controls">
				<sys:treeselect id="business" name="business.id" value="${pmProjectMain.business.id}" labelName="business.name" labelValue="${pmProjectMain.business.name}"
					title="商务成员" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div>  
		 --%>
		<div class="control-group">
			<label class="control-label">其他人员：</label>
			<div class="controls">
				<sys:treeselect id="logistics" name="logistics.id" value="${pmProjectMain.logistics.id}" labelName="logistics.name" labelValue="${pmProjectMain.logistics.name}"
 					title="其他人员" url="/sys/office/treeData?type=3"  allowClear="true" notAllowSelectParent="true" checked = "true"/>
			</div>
		</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>