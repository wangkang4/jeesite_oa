<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>专利申请管理</title>

<script type="text/javascript" src="${ctxStatic}/tb/patent/add.js?ver=2"></script>
<script type="text/javascript">
	function changeType(){
		var typeName=$("#test").val();
		
		$(".warn").text("");
		if(typeName=="专利侵权奖励"){
			$(".addTr").hide();
			$("#money").val("");
			$("#money").removeAttr("readonly");
			$("#money").attr('placeholder','请输入金额');
			$("#patentTel").hide();
			$("#anniu").hide();
			$(".proportion").hide();
			var tr2 = $('<tr id="reason"></tr>');
			tr2.append('<td class="tit" >侵权情况简述</td>');
			tr2.append('<td colspan="7"><textarea rows="5" maxlength="2000" name="reason" style="width:500px">${patent.reason }</textarea><span id="reasonWarn" class="warn" style="color: red"></span></td> ');
			$(".table-form tr:last").after(tr2);
		}else{
			$(".addTr").show();
			$("#patentTel").show();
			$("#anniu").show();
			$(".proportion").show();
			$("#reason").remove();
			if(typeName=="发明受理奖励"){
				$("#money").val(900);
			}
			if(typeName=="发明受权奖励"){
				$("#money").val(2100);
			}
			if(typeName=="实用新型受理奖励"){
				$("#money").val(450);
			}
			if(typeName=="实用新型受权奖励"){
				$("#money").val(1050);
			}
			if(typeName=="外观造型受理奖励"){
				$("#money").val(360);
			}
			if(typeName=="外观造型受权奖励"){
				$("#money").val(840);
			}
		}
	};
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/patent/list">专利申请列表</a></li>
		<li class="active"><a href="#">专利申请</a></li>
		<shiro:hasAnyRoles name="xingzheng">
			<li class="active"><a href="${ctx}/tb/patent/list2">所属区域员工专利申请列表</a></li>
		</shiro:hasAnyRoles>
		<shiro:hasAnyRoles name="caiwu">
			<li class="active"><a href="${ctx}/tb/patent/list3">员工专利申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="patent" name="form"
		action="${ctx}/tb/patent/toAdd" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<fieldset>
			<legend>专利申请</legend>
			<div id="anniu">
			<input type="button" id="btn" value="添加" class="btn btn-primary" onclick="addReceptionStaff()">
			<input type="button" id="btn" value="删除" class="btn btn-primary" onclick="delReceptionStaff()">
			</div>
			<div>
			<table class="table-form">
				<tr>
					<td class="tit">部门</td>
					<td>
						${patent.office.name}
					</td>
					<td class="tit">姓名</td>
					<td>
						${patent.user.name }
					</td>
				</tr>
				<tr>
					
					<td class="tit">申请类型</td>
					<td>
					<c:if test="${empty patent.reason}">
						<select name="applyType" class="input-medium" id="test" onchange="changeType()">
							<option value="">请选择</option>
							<option value="发明受理奖励">发明受理奖励</option>
							<option value="发明受权奖励">发明受权奖励</option>
							<option value="实用新型受理奖励">实用新型受理奖励</option>
							<option value="实用新型受权奖励">实用新型受权奖励</option>
							<option value="外观造型受理奖励">外观造型受理奖励</option>
							<option value="外观造型受权奖励">外观造型受权奖励</option>
							<option value="专利侵权奖励">专利侵权奖励</option>
						</select>
						<span id="applyTypeWarn" class="warn" style="color: red"></span>
					</c:if>
					<c:if test="${not empty patent.reason}">
						<input id="test" type="text" name="applyType" value="${patent.applyType}" readonly="readonly">
						<span id="applyTypeWarn" class="warn" style="color: red"></span>
					</c:if>
					</td>
					<td class="tit">专利名称</td>
					<td>
						<input id="patentName" type="text" name="patentName" value="${patent.patentName }" >
						<span id="patentNameWarn" class="warn" style="color: red"></span>
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty patent.reason}">
					
						<tr id="patentTel">
							<td class="tit">申请号</td>
							<td>
								<input id="applyTel" type="text" name="applyTel" value="${patent.applyTel }" >
								<span id="applyTelWarn" class="warn" style="color: red"></span>
							</td>
							<td class="tit">日期</td>
							<td>
								<input id="useTime" name="applyDate" type="text"
								readonly="readonly" maxlength="20" class="input-medium Wdate"
								value="<fmt:formatDate value="${patent.applyDate}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
							</td>
						</tr>
						<tr>
							<td class="tit">奖励金额</td>
							<td colspan="6">
								<input id="money" type="text" name="money" value="" readonly="readonly" placeholder="请选中类型">
								元<span id="moneyWarn" class="warn" style="color: red"></span>
							</td>
							
						</tr>
						<tr class="proportion"><td colspan="7" style="text-align: center;font-weight:bold;">分配人员及比例</td></tr>
						<c:forEach items="${list }" var="rs"> 
								<tr class="proportion">
									<td class="tit">姓名</td>
									<td>
										<input name="person" type="text" value="${rs.person }">
										<span id="personWarn" class="warn" style="color: red"></span>
									</td>
									<td class="tit">比例</td>
									<td>
										<input name="position" type="text" value="${rs.position }">
										<span id="positionWarn" class="warn" style="color: red"></span>
									</td>
								</tr>
						</c:forEach> 
				</c:when>
					<c:otherwise>
						<tr>
							<td class="tit">奖励金额</td>
							<td colspan="6">
								<input id="money" type="text" name="money" value="${patent.money }" placeholder="请选中类型">
								元<span id="moneyWarn" class="warn" style="color: red"></span>
							</td>
						</tr>
						<tr >
							<td class="tit">侵权情况简述</td>
									<td colspan="7"><textarea  maxlength="2000" name="reason" style="width:1500px">${patent.reason }</textarea>
									<span id="reasonWarn" class="warn" style="color: red"></span></td>
						</tr>
					</c:otherwise> 
				</c:choose>
			</table>
			</div>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty patent.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty patent.id}">
			<act:histoicFlow procInsId="${patent.act.procInsId}" />
		</c:if> 
	</form:form>
</body>

</html>