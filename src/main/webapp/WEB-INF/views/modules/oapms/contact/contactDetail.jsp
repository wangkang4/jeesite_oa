<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>联系人详情页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/contact/contactDetail.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/customer/list">联系人列表</a></li>
		<li class="active"><a href="">联系人详情</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="customer" action="" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
                ${contact.customerContactName }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代号:</label>
			<div class="controls">
                ${contact.codeName }
			</div>
		</div>
		<div class="control-group"> 
			<label class="control-label">所属客户:</label>
			<div class="controls">
				${contact.customer.customerName }
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				${contact.phone }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信:</label>
			<div class="controls">
				${contact.weixin }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				${contact.email }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职位:</label>
			<div class="controls">
				${contact.position }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生日:</label>
			<div class="controls">
				<fmt:formatDate value="${contact.birthday }" pattern="yyyy-MM-dd"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">兴趣爱好:</label>
			<div class="controls">
				${contact.interest }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性格特征:</label>
			<div class="controls">
				${contact.customerCharacter }
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				${contact.note }
			</div>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="查看拜访记录" onclick="visitList()"/>
			<input id="btnCancel" class="btn" type="button" value="添加拜访记录" onclick="addVisit()"/>
		</div>
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
