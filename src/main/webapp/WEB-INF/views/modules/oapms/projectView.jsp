<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目修改</title>
<meta name="decorator" content="default" />
		<style>
			.progress {
				position: relative;
				height:40px;
				width: 200px;
				margin-left: 5px;
			}
			
			.progress_bg {
				height: 10px;
				border: 1px solid #ddd;
				border-radius: 5px;
				overflow: hidden;
 				background-color: #f2f2f2; 
			}
			
			.progress_bar {
 				background: #5FB878; 
				width: 0;
				height: 10px;
				border-radius: 5px;
			}
			
			.progress_btn {
				width: 20px;
				height: 20px;
				border-radius: 5px;
				position: absolute;
 				background: #fff; 
				left: 0px;
				margin-left: -10px;
				top: -5px;
				cursor: pointer;
				border: 1px #ddd solid;
				box-sizing: border-box;
			}
			
			.progress_btn:hover {
				border-color: #F7B824;
			}
		</style>
			
<script type="text/javascript">

	$(document).ready(function() {
		var status = $("#status").val();
		var progress = $("#progress").val();
		var left = 0;
		if(status!=7){
			$("#aaaa").hide();
		}else{
			left = progress * 2;
			console.log(progress+' ++++ '+left);
			$('.progress_btn').css('left', left);
			$('.progress_bar').animate({
				width: left
			}, 200);
			$('.text').html(progress + '%');
		}
	});
	function doSubmit() {
		var progress = $(".progress").val();
		console.log(progress);
		$("#addForm").submit();
		return false;
	}
	
	function getprogress(){
		var status = $("#status").val();
		if(status==7){
			$("#aaaa").show();
		}else{
			$("#aaaa").hide();
		}
	}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">基本信息</a></li>
		<li><a href="${ctx}/pms/comment/projectComment?id=${pmsProject.projectId}">评论</a></li>
		<li><a href="${ctx}/pms/comment/projectDocument?id=${pmsProject.projectId}">文档</a></li>
		<li><a href="${ctx}/pms/projectHelp/helpList?id=${pmsProject.projectId}">求助</a></li>
		<li><a href="${ctx}/pms/projectHelp/expenseList?id=${pmsProject.projectId}">费用</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="pmsProject"
		action="${ctx }/pms/project/projectform" method="post"
		class="form-horizontal">
		<form:hidden path="projectId"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">项目名称:</label>
			<div class="controls">
				<form:input path="projectName" htmlEscape="false" maxlength="50" readonly="true" class="required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户:</label>
			<div class="controls">
				<form:input path="customer.customerName" htmlEscape="false" maxlength="100" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户联系人:</label>
			<div class="controls">
				<form:input path="customerContact.customerContactName" htmlEscape="false" maxlength="100" readonly="true"/>
			</div>
		</div>
		<div id="statusDiv" class="control-group">
			<label class="control-label">目前阶段:</label>
			<div class="controls">
				<form:select path="status" class="input-medium"  readonly="true" onchange="getprogress()">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_project_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<form:input path="money" htmlEscape="false" maxlength="100" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">开始时间:</label>
			<div class="controls">
					<input name="startTime" type="text" readonly="true" maxlength="20"
				class="input-medium Wdate "
				value="<fmt:formatDate value="${pmsProject.startTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间:</label>
			<div class="controls">
					<input name="endTime" type="text" readonly="true" maxlength="20"
				class="input-medium Wdate "
				value="<fmt:formatDate value="${pmsProject.endTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结单时间:</label>
			<div class="controls">
					<input name="statusmentTime" type="text" readonly="true" maxlength="20"
				class="input-medium Wdate "
				value="<fmt:formatDate value="${pmsProject.statusmentTime}" pattern="yyyy-MM-dd"/>"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户分析:</label>
			<div class="controls">
				<form:textarea path="costomerAnalysis" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">决策链分析:</label>
			<div class="controls">
				<form:textarea path="decMakChainAnalysis" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">竞争对手分析:</label>
			<div class="controls">
				<form:textarea path="competitorsAnalysis" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">机会点:</label>
			<div class="controls">
				<form:textarea path="chancePoint" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题点:</label>
			<div class="controls">
				<form:textarea path="problemPoint" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目目标:</label>
			<div class="controls">
				<form:textarea path="target" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">市场策略及战术:</label>
			<div class="controls">
				<form:textarea path="marketStrategyTactics" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实施计划:</label>
			<div class="controls">
				<form:textarea path="implementationPlan" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">资源求助:</label>
			<div class="controls">
				<form:textarea path="resourceHelp" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" readonly="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品类型:</label>
			<div class="controls">
				<form:select path="productType" class="input-medium" readonly="true">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('pms_project_productType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	
</body>
</html>