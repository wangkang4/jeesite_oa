<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<script type="text/javascript" src="${ctxStatic}/tb/party/addParty.js"></script>
<title>团建管理</title>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/party/list">团建列表</a></li>
		<li class="active"><a href="">团建申请</a></li>
		<shiro:hasAnyRoles name="caiwu">
			<li class="active"><a href="${ctx}/tb/party/getAllList">员工团建申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="party"
		action="${ctx}/tb/party/save" method="post" class="form-horizontal">
		<input type="hidden" id="flag" name="act.flag">
		<input type="hidden" name="id" value=${party.id }>
		<sys:message content="${message}" />
		
		<fieldset>
			<legend>团建申请</legend>
			<table id="contentTable" class="table-form">
			<tr>
				<td class="tit">部门：</td>
				<td>
					<sys:treeselect id="office" name="officeId" 
							value="" labelName="officeName" 
							labelValue=""  title="部门" 
							url="/sys/office/treeData?type=2" cssClass="input-small" 
							allowClear="true" notAllowSelectParent="true" checked="true"/>
					<span class="warn" id="officeWarn" style="color: red"></span>
				</td>
				<td class="tit">总人数：</td>
				<td>
					<input name="count" value="">
					<span class="warn" id="countWarn" style="color: red"></span>
				</td>
			</tr>
			<tr>
				<td class="tit">计划团建时间：</td>
				<td>
					<input name="planTime" type="text"
							readonly="readonly" maxlength="20" class="input-medium Wdate"
							value=""
							onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
					<span class="warn" id="planTimeWarn" style="color: red"></span>
				</td>
				<td class="tit">计划参加人数：</td>
				<td>
					<input name="planCount" value="">
					<span class="warn" id="planCountWarn" style="color: red"></span>
				</td>
			</tr>
			<tr>
				<td class="tit">可用团建经费：</td>
				<td>
					<input name="availableFunds" value="">元
					<span class="warn" id="availableFundsWarn" style="color: red"></span>
				</td>
				<td class="tit">本次团建经费预算：</td>
				<td>
					<input name="budget" value="">元
					<span class="warn" id="budgetWarn" style="color: red"></span>
				</td>
			</tr>
			<tr>
				<td class="tit">本次团建活动方案描述：</td>
				<td colspan="3">
					<textarea rows="3" style="width:80%" name="notes"></textarea>
					<span class="warn" id="notesWarn" style="color: red"></span>
				</td>
			</tr>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		</form:form>
		1.活动经费标准<br>
		&nbsp;&nbsp;&nbsp;&nbsp;a)部门团建活动指以部门为单位组织、开展的团队建设活动。部门人数少的可与其他部门联合组织团建活动，各办事处也可以组织当地各部门进行全体员工的团建活动。<br>
		&nbsp;&nbsp;&nbsp;&nbsp;b)各部门每月活动经费按照不超过30元/人/月计算，财务部按此限额进行报销，实际费用超出此限额的，公司不予额外报销；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;c)团建经费可累计使用；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;d)若员工自动放弃参加部门团建活动，不另外折现或做其他补贴；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;e)如有新员工加入，于其正式入职的当月，即按30元/人/月增加部门团建经费；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;f)如有员工离职，于其正式离职的当月，即按30元/人/月减少部门团建经费；<br>
		2.活动经费的报销要求<br>
		&nbsp;&nbsp;&nbsp;&nbsp;a)部门团建活动经费一般采用部门先垫付后报销的方式，单次活动费用超出5000元的可由经费管理人提交OA借款流程（OA系统中会与团建活动方案审批流程自动关联）。<br>
		&nbsp;&nbsp;&nbsp;&nbsp;b)部门团建活动经费报销时需由该部门经费管理人负责提交OA申请，费用性质归属“福利费——团建费”，经费管理人需粘贴相关发票等有效票据，并附上由其部门主管领导、办事处主任签字的团建活动方案（超3000元的，需附上此方案的OA审批单），经当地行政人员审核后，交公司财务部进行报销；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;c)若团队建设活动的使用金额大于当前可用余额，超出部分由员工自行承担；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;d)团队建设经费为专项经费，不得用于其他奖励、提成、绩效等分配；<br>
		&nbsp;&nbsp;&nbsp;&nbsp;e)团队建设经费不能用于按人头进行私分，不得冒领、滥用、虚报团队建设费用，如出现上述情况，将视情节严重程度给予警告、通报批评乃至降级、开除等处罚。<br>
</body>
</html>