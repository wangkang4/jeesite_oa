<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户修改页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/customer/updateCustomer.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="">客户修改</a></li>
	</ul>
	
	<form:form id="updateCustomer" modelAttribute="customer" action="${ctx}/oapms/customer/update" method="post" class="form-horizontal">
	<form:hidden path="customerId"/>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
                <input type="text" class="" name="customerName" value=${customer.customerName }>
				<span class="help-inline"><font color="red"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户地址:</label>
			<div class="controls">
                <input type="text" class="" name="address" value=${customer.address }>
				<span class="help-inline"><font color="red" id="addressWarn"></font> </span>
			</div>
		</div>
		<div class="control-group"> 
			<label class="control-label">所属类别:</label>
			<div class="controls">
				<form:select path="category" class="input-medium">
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
				<sys:treeselect id="saler" name="saler.id" value="${customer.saler.id}" labelName="saler.name" labelValue="${customer.saler.name}"
					title="销售经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red" id="salerWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品经理:</label>
			<div class="controls">
				<sys:treeselect id="producter" name="producter.id" value="${customer.producter.id}" labelName="producter.name" labelValue="${customer.producter.name}"
					title="产品经理" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
				<span class="help-inline"><font color="red" id="producterWarn"></font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">相关人:</label>
			<div class="controls">
				<sys:treeselect id="persons" name="persons" value="${personsId}" labelName="user.name" labelValue="${personsName}"
					title="相关人" url="/sys/office/treeData?type=3" cssClass="required" notAllowSelectParent="true" checked="true"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="updateCustomer()" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
