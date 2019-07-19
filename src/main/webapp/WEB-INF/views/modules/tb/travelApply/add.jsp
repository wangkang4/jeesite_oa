<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>出差申请管理</title>

<script type="text/javascript" src="${ctxStatic}/tb/travelApply/add.js?ver=2"></script>

<script type="text/javascript">

	function HJ(){
		var One = parseFloat($("#one").val());
		var Two = parseFloat($("#two").val());
		var Three = parseFloat($("#three").val());
		var Four = parseFloat($("#four").val());
		var heji=One+Two+Three+Four
		$("#all").val(heji);
	}
	
	
	function time(){
		var s1=$("input[name='startDate']").val();
		var s2=$("input[name='endDate']").val();
		s1 = new Date(s1.replace(/-/g, "/"));
		s2 = new Date(s2.replace(/-/g, "/"));
		var days = s2.getTime() - s1.getTime();
		var time = parseInt(days / (1000 * 60 * 60 * 24));
		$("input[name='day']").val(time);
	}
	

</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/travelApply/list">出差申请列表</a></li>
		<li class="active"><a href="#">出差申请</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/travelApply/list2">所属区域员工出差申请列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="caiwu">
			<li class="active"><a href="${ctx}/tb/travelApply/listCaiWu">所有员工出差申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="travelApply" name="form"
		action="${ctx}/tb/travelApply/toAdd" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" /> 
		<fieldset>
			<legend>出差申请</legend>
			<div>
			<input type="button" id="btn" value="添加" class="btn btn-primary" onclick="addReceptionStaff()">
			<input type="button" id="btn" value="删除" class="btn btn-primary" onclick="delReceptionStaff()">
			</div>
			<div>
			<table class="table-form">
				<tr>
					<td class="tit">部门</td>
					<td>
						${travelApply.office.name }
					</td>
					<td class="tit">姓名</td>
					<td>
						${travelApply.user.name }
					</td>
					<td class="tit">申请时间</td>
					<td>
						<fmt:formatDate value="${travelApply.createDate}" type="both" pattern="yyyy-MM-dd"/>
					</td>
				</tr>
				<tr>
					<td class="tit">出发地</td>
					<td>
						<input name="startAddress" type="text" value="${travelApply.startAddress }">
						<span id="startAddressWarn" class="warn" style="color: red"></span>
					</td>
					<td class="tit">目的地</td>
					<td>
						<input name="endAddress" type="text" value="${travelApply.endAddress }">
						<span id="endAddressWarn" class="warn" style="color: red"></span>
					</td>
					<td class="tit">项目名称</td>
					<td>
						<input name="project" type="text" value="${travelApply.project }" >
						<span id="projectWarn" class="warn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">出差开始日期</td>
					<td>
						<input id="useTime" name="startDate" type="text"
						readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${travelApply.startDate }" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span id="startDateWarn" class="warn" style="color: red"></span>
					</td>
					<td class="tit">出差结束日期</td>
					<td>
						<input id="useTime" name="endDate" type="text"
						readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${travelApply.endDate }" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" onblur="time();"/>
						<span id="endDateWarn" class="warn" style="color: red"></span>
					</td>
					<td colspan="5">
					   <input name="day" type="text" value="${travelApply.day }" readonly="readonly">天
					</td>
				</tr>
				<tr>
					<td class="tit">交通工具</td>
						<td>
						<select name="traffic" class="input-medium" id="test" onchange="time();">
							<option value="">请选择</option>
							<option value="公车">公车</option>
							<option value="自驾车">自驾车</option>
							<option value="汽车">汽车</option>
							<option value="火车">火车</option>
							<option value="高铁">高铁</option>
							<option value="飞机">飞机</option>
							<option value="其他">其他</option>
						</select>
						<span id="trafficWarn" class="warn" style="color: red"></span>
					</td>
					<td class="tit">同行人员</td>
					<td colspan="5">
						<input name="person" type="text" style="width:500px" value="${travelApply.person }" onblur="time();">
						<span id="personWarn" class="warn" style="color: red"></span>
					</td>
				</tr>
					<tr><td colspan="7" style="text-align: center;font-weight:bold;">行程计划</td></tr>
					<c:forEach items="${list }" var="rs"> 
							<tr>
								<td class="tit">日期</td>
								<td>
									<input name="planDate" type="text" value="${rs.planDate }">
									<span id="planDateWarn" class="warn" style="color: red"></span>
								</td>
								<td class="tit">客户名称</td>
								<td>
									<input name="customerName" type="text" value="${rs.customerName }">
									<span id="customerNameWarn" class="warn" style="color: red"></span>
								</td>
								<td class="tit">工作内容</td>
								<td>
									<input name="content" type="text" value="${rs.content}">
									<span id="contentWarn" class="warn" style="color: red"></span>
								</td>
							</tr>
					</c:forEach>
					<tr>
						<td class="tit">费用预算</td>
						<td colspan="5">
							城际交通费：<input id="one" name="costOne" type="text" value="${travelApply.costOne }" onblur="HJ();">元; 
							住宿费：<input id="two" name="costTwo" type="text" value="${travelApply.costTwo }" onblur="HJ();">元;
							差旅补助：<input id="three" name="costThree" type="text" value="${travelApply.costThree}" onblur="HJ();">元;
							其他：<input id="four" name="costFour" type="text" value="${travelApply.costFour }" onblur="HJ();">元; 
							<span id="costWarn" class="warn" style="color: red"></span>
							<input type="button" value="合计" onclick="HJ();"/>
							<input id="all" name="allCost" type="text" value="${travelApply.allCost }">元 
							<span id="allCostWarn" class="warn" style="color: red"></span>
						</td>
					</tr>
					<tr style="display: none;">
						<td class="tit" >出差总结</td>
						<td colspan="5">
						<textarea name="summary"
								rows="5" maxlength="2000" style="width:1500px">${travelApply.summary}</textarea>
						<span id="summaryWarn" class="warn" style="color: red"></span>
						</td> 
					</tr>
			</table>
			</div>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty travelApply.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty travelApply.id}">
			<act:histoicFlow procInsId="${travelApply.act.procInsId}" />
		</c:if>
	</form:form>
</body>

</html>