<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>客户添加页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/customer/addCustomer.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/customer/list">客户列表</a></li>
		<li class="active"><a href="">客户添加</a></li>
	</ul>
	<form:form id="addCustomer" modelAttribute="customer" action="${ctx}/oapms/customer/add" method="post" class="form-horizontal" enctype="multipart/form-data">
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
                <input type="text" class="" name="customerName" >
				<span class="help-inline"><font color="red"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户地址:</label>
			<div class="controls">
                <input type="text" class="" name="address">
				<span class="help-inline"><font color="red" id="addressWarn"></font> </span>
			</div>
		</div>
		<div class="control-group"> 
			<label class="control-label">所属类别:</label>
			<div class="controls">
				<form:select  path="category" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_customer_category')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" id="categoryWarn"></font> </span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所属行业:</label>
			<div class="controls">
				<form:select path="industry" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_customer_industry')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" id="industryWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属办事处:</label>
			<div class="controls">
				<form:select path="office" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_customer_office')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" id="officeWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属区域:</label>
			<div class="controls">
				<form:select path="area" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_customer_area')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red" id="areaWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售经理:</label>
			<div class="controls">
				<sys:treeselect id="saler" name="saler.id" value="${user.user.id}" labelName="saler.name" labelValue="${user.office.name}"
					title="销售经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red" id="salerWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品经理:</label>
			<div class="controls">
				<sys:treeselect id="producter" name="producter.id" value="${user.user.id}" labelName="producter.name" labelValue="${user.office.name}"
					title="产品经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red" id="producterWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">相关人:</label>
			<div class="controls">
				<sys:treeselect id="persons" name="persons" value="${user.user.id}" labelName="user.name" labelValue="${user.office.name}"
					title="相关人" url="/sys/office/treeData?type=3" cssClass="required" notAllowSelectParent="true" checked="true"/>
			</div>
		</div>
		<div class="control-group" id="attachment">
			<label class="control-label">附件:</label>
			<div class="controls">
				<input type="file" name="file" value="选择">
				<%-- <textarea style="display:none" name="attachmentAddress" id="fileids"></textarea>
				<iframe src="${ctx }/pms/comment/fileuploads"  width="50%" height="50%" frameborder="no" border="1" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe> --%>	
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitCustomer()" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
