<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户详情页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/customer/customerDetail.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/customer/list">客户列表</a></li>
		<li class="active"><a href="">客户详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="customer" action="" method="post" class="form-horizontal">
		<input type="hidden" id="customerId" value=${customer.customerId }>
		<div class="control-group">
			<label class="control-label">客户名称:</label>
			<div class="controls">
                ${customer.customerName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户地址:</label>
			<div class="controls">
                ${customer.address }
			</div>
		</div>
		<div class="control-group"> 
			<label class="control-label">所属类别:</label>
			<div class="controls">
				${fns:getDictLabel(customer.category, 'pms_customer_category', '')}
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">所属行业:</label>
			<div class="controls">
				${fns:getDictLabel(customer.industry, 'pms_customer_industry', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属办事处:</label>
			<div class="controls">
				${fns:getDictLabel(customer.office, 'pms_customer_office', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属区域:</label>
			<div class="controls">
				${fns:getDictLabel(customer.area, 'pms_customer_area', '')}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">销售经理:</label>
			<div class="controls">
				${customer.saler.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品经理:</label>
			<div class="controls">
				${customer.producter.name}
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">相关人:</label>
			<div class="controls">
				${personsName}
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="查看联系人" onclick="contactList()"/>
			<input id="btnCancel" class="btn" type="button" value="添加联系人" onclick="addContact()"/>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
