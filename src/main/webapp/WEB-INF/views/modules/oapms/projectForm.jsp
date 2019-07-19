<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目修改</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/oapms/project/css/progress.css">
<script src="${ctxStatic}/oapms/project/js/projectForm.js"></script>
<script type="text/javascript">
	function getProjectccList() {
		var customerId = $("#customer").val();
		console.log(customerId);
		$.ajax({
			type : 'post',
			url : "${ctx}/pms/project/getCustomerContactList",
			data : {
				"customerId" : customerId
			},
			dataType : 'json',
			success : function(data) {
				$("#customerContact").empty();
				$("#customerContact").append("<option selected value=''>--请选择--</option>");
				for (var i = 0; i < data.length; i++) {
					$("#customerContact").append(
							"<option value='"+data[i].customerContactId+"'>"
									+ data[i].customerContactName + "</option>");
				}
			}

		});
	}	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/project/list">项目列表</a></li>
		<li class="active"><a href="">项目修改</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="pmsProject"
		action="${ctx }/pms/project/projectform" method="post"
		class="form-horizontal">
		<form:hidden path="projectId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">项目名称:</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="50" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户:</label>
			<div class="controls">
				<form:select id="customer" path="customer.customerId" class="input-xlarge" onclick="getProjectccList()">
						<form:option value=""  label="--请选择--"/>
						<c:forEach var="customer" items="${clist }">
							<form:option value="${customer.customerId }" label="${customer.customerName }"/>
						</c:forEach>
					</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户联系人:</label>
			<div class="controls">
					<form:select id="customerContact" path="customerContact.customerContactId" class="input-xlarge" >
								<form:option value=""  label="--请选择--"/> 
								<c:forEach var="customerContact" items="${cclist }"> 
									<form:option value="${customerContact.customerContactId }" label="${customerContact.customerContactName }"/> 
								</c:forEach> 
					</form:select>
			</div>
		</div>
		<div id="statusDiv" class="control-group">
			<label class="control-label">重要程度:</label>
			<div class="controls">
				<form:select id="importantDepende" path="importantDepende" class="input-medium" >
<%-- 					<form:option value="" label="请选择"/> --%>
					<form:options items="${fns:getDictList('pms_project_importantDepende')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span id="importantDependeText" class="help-inline"></span>
			</div>
		</div>
		<div id="statusDiv" class="control-group">
			<label class="control-label">目前阶段:</label>
			<div class="controls">
				<form:select path="status" class="input-medium"  onchange="getprogress()">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span id="statusText" class="help-inline"></span>
			</div>
		</div>
		
		<div id="aaaa" class='control-group'>
			<label class='control-label'>交付进度:</label>
			<div class='controls'>
				<div class='progress'>
					<div class='progress_bg'>
						<div class='progress_bar'></div>
					</div>
					<div class='progress_btn'></div>
					<div class='text'>0%</div>
				</div>
			</div>
			<input type='hidden' id='progress' name='progress' value="${pmsProject.progress }">
		</div>
		
		<div class="control-group">
			<label class="control-label">项目金额:</label>
			<div class="controls">
				<form:input path="money" htmlEscape="false" maxlength="100"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间:</label>
			<div class="controls">
					<input name="startTime" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate "
				value="<fmt:formatDate value="${pmsProject.startTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间:</label>
			<div class="controls">
					<input name="endTime" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate "
				value="<fmt:formatDate value="${pmsProject.endTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结单时间:</label>
			<div class="controls">
					<input name="statusmentTime" type="text" readonly="readonly" maxlength="20"
				class="input-medium Wdate "
				value="<fmt:formatDate value="${pmsProject.statusmentTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户分析:</label>
			<div class="controls">
				<form:textarea path="costomerAnalysis" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">决策链分析:</label>
			<div class="controls">
				<form:textarea path="decMakChainAnalysis" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竞争对手分析:</label>
			<div class="controls">
				<form:textarea path="competitorsAnalysis" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机会点:</label>
			<div class="controls">
				<form:textarea path="chancePoint" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题点:</label>
			<div class="controls">
				<form:textarea path="problemPoint" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目目标:</label>
			<div class="controls">
				<form:textarea path="target" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场策略及战术:</label>
			<div class="controls">
				<form:textarea path="marketStrategyTactics" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实施计划:</label>
			<div class="controls">
				<form:textarea path="implementationPlan" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资源求助:</label>
			<div class="controls">
				<form:textarea path="resourceHelp" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品类型:</label>
			<div class="controls">
				<form:select path="productType" class="input-medium" onchange="testProjectType()">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_project_productType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span id="productTypeText" class="help-inline"></span>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label">销售经理:</label>
			<div class="controls">
                <sys:treeselect id="user00" name="saler.id" value="${pmsProject.saler.id}" labelName="saler.name" labelValue="${pmsProject.saler.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品经理:</label>
			<div class="controls">
                <sys:treeselect id="user0" name="producter.id" value="${pmsProject.producter.id}" labelName="producter.name" labelValue="${pmsProject.producter.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">研发经理:</label>
			<div class="controls">
                <sys:treeselect id="user1" name="devloper.id" value="${pmsProject.devloper.id}" labelName="devloper.name" labelValue="${pmsProject.devloper.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">研发参与人:</label>
			<div class="controls">
                <sys:treeselect id="user2" name="persons" value="${pmsProject.persons}" labelName="user.name" labelValue="${pmsProject.personsName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" notAllowSelectParent="true" checked="true"/>
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