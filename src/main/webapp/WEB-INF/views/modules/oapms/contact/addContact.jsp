<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>联系人添加页面</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript" src="${ctxStatic}/oapms/contact/addContact.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/oapms/contact/list">联系人列表</a></li>
		<li class="active"><a href="">联系人添加</a></li>
	</ul>
	<form:form id="addContact" modelAttribute="contact" action="${ctx}/oapms/contact/add" method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">联系人姓名:</label>
			<div class="controls">
                <input type="text"  name="customerContactName">
				<span class="help-inline"><font color="red" id="contactNameWarn"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">代号:</label>
			<div class="controls">
                <input type="text"  name="codeName">
                <span class="help-inline"><font color="red" id="codeNameWarn"></font></span>
			</div>
		</div>
		<div class="control-group"> 
			<label class="control-label">所属客户:</label>
			<div class="controls">
				<select class="input-medium" id="customerId" name="customer.customerId">
					<option value="">请选择</option>
					<c:forEach items="${customerList }" var="custo">
						<option value=${custo.customerId } <c:if test='${customerId== custo.customerId }'>  selected  </c:if>>${custo.customerName }</option>
					</c:forEach>
				</select>
				<span class="help-inline"><font color="red" id="customerIdWarn"></font></span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">联系电话:</label>
			<div class="controls">
				<input type="text"  name="phone">
				<span class="help-inline"><font color="red"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">微信:</label>
			<div class="controls">
				<input type="text"  name="weixin">
				<span class="help-inline"><font color="red" id="weixinWarn"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">邮箱:</label>
			<div class="controls">
				<input type="text"  name="email">
				<span class="help-inline"><font color="red"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">职位:</label>
			<div class="controls">
				<input type="text"  name="position">
				<span class="help-inline"><font color="red" id="positionWarn"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">生日:</label>
			<div class="controls">
				<input id="birthday" name="birthdayString" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate "
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">兴趣爱好:</label>
			<div class="controls">
				<input type="text"  name="interest">
				<span class="help-inline"><font color="red" id="interestWarn"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性格特征:</label>
			<div class="controls">
				<input type="text"  name="customerCharacter">
				<span class="help-inline"><font color="red" id="customerCharacterWarn"></font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<textarea id="note" class="input-xlarge" name="note"></textarea>
				<span class="help-inline"><font color="red"></font></span>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="submitContact()" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
