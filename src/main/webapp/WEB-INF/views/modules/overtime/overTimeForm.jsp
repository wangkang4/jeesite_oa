<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>加班管理</title>
<script type="text/javascript">
	$(document).ready(
			
			
			function() {
				$("#name").focus();
				$("#inputForm")
						.validate(
								{
									submitHandler : function(form) {
										loading('正在提交，请稍等...');
										form.submit();
									},
									errorContainer : "#messageBox",
									errorPlacement : function(error, element) {
										$("#messageBox").text("输入有误，请先更正。");
										if (element.is(":checkbox")
												|| element.is(":radio")
												|| element.parent().is(
														".input-append")) {
											error.appendTo(element.parent()
													.parent());
										} else {
											error.insertAfter(element);
										}
									}
								});
			});
</script>

<script type="text/javascript">
	$(document).ready(function() {
		var myDate = new Date();
		var reason="加班申请-"+myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		$("input[name='reason']").val(reason);
		$("input[name='reason']").focus();
		$("#numDay").blur(function() {
			yanzhen(bool);
		});
	});
	
	
	function yanzhen(bool){
		var reg = /^[1-9][0-9]*$/;
		
		var numDay = $("#numDay").val();
	/* 	var leader=$("select[name='leader']").val(); */
		var officeName=$("input[name='officeName']").val();
		var overtimeType=$("select[name='overtimeType']").val();
		
		var start = Date.parse($("#startTime").val());
		var end = Date.parse($("#endTime").val());
		var days = Math.abs(parseInt((end - start) / 1000 / 3600));
		$("#numDay").next().text("");
		/* $("#leaderWarn").text(""); */
		/* if(leader=='请选择'&&officeName=='研发中心'){
			bool = false;
			$("#leaderWarn").text("*请选择上级审批人");
		} */
		if(overtimeType=='请选择'){
			bool = false;
			$("#overtimeTypeWarn").text("*请选择加班类型");
		}
		if (numDay == "") {
			bool = false;
			$("#numDay").next().text("*必填信息");
		}else if (!reg.test(numDay)){
			bool=false;
			$("#numDay").next().text("*只允许正数");
		} else if(numDay > days){
			bool=false;
			$("#numDay").next().text("*加班时间不得大于结束时间和开始时间之差");
		};		
		return bool
	}
	
	function save() {
		var bool = true;
		bool = yanzhen(bool);
		var list = $("#inputForm").serializeArray();		
		if (bool) {
			$('#flag').val('yes');
			return true;
		}
		return false;
	}

	function stop() {
		var bool = true;
		bool = yanzhen(bool);
		if (bool) {
			$('#flag').val('no');
			return true;
		}
		return false;
	}
</script>
</head>

<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/work/overtime/">加班列表</a></li>
		<li class="active"><a
			href="${ctx}/work/overtime/form?id=${overTime.id}"><shiro:hasPermission
					name="oa:testAudit:edit">加班${not empty overTime.id?'修改':'申请'}流程</shiro:hasPermission>
				<shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="overTime"
		action="${ctx}/work/overtime/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>加班申请</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td>
						<input name="reason" type="text">
					</td>
					<td class="tit">姓名</td>
					<td><input id="user.name" name="user.id"
						value="${overTime.user.id }" type="hidden"> <input
						id="userId" name="userName" type="text"
						value="${overTime.user.name }" readonly="readonly"> <%-- 						<sys:treeselect id="user" name="user.id" value="${activityLeave2.user.id}" labelName="user.name" labelValue="${activityLeave2.user.name}"  --%>
						<%-- 							title="用户" url="/sys/office/treeData?type=3" cssClass="required recipient" cssStyle="width:150px"  --%>
						<%-- 							allowClear="true" notAllowSelectParent="true" smallBtn="false"/> --%>
					</td>
				</tr>
				<tr>
					<td class="tit">开始时间</td>
					<td><input id="startTime" name="startTime"
						value="<fmt:formatDate value="${overTime.startTime}" pattern="yyyy-MM-dd HH:mm:ss
						"/>"
						pattern="yyyy-MM-dd HH:mm:ss" type="text" readonly="readonly"
						maxlength="20" class="Wdate required"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
					</td>
					<td class="tit">结束时间</td>
					<td><input id="endTime" name="endTime"
						value="<fmt:formatDate value="${overTime.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						pattern="yyyy-MM-dd HH:mm:ss" type="text" readonly="readonly"
						maxlength="20" class="Wdate required"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}'});" />
					</td>
				</tr>
				<tr>
					<td class="tit">加班总时间</td>
					<td>
						<!-- onclick="getLeaveDays()" --> <form:input path="days"
							htmlEscape="false" maxlength="50" id="numDay" />&nbsp;&nbsp;小时 
						<span style="color: red"></span>
					</td>
					<td class="tit">部门</td>
					<td>
						<input id="office.name" name="office.id" value="${overTime.office.id}" type="hidden">
						<input id="officeId" name="officeName" type="text" value="${overTime.office.name}" readonly="readonly" />
											
					</td>
				</tr>
				<%-- <c:if test="${overTime.office.name=='研发中心'}">
					<tr>
					<td class='tit'>请选择上级审批人</td>
					<td>
						<select name="leader" class="input-medium" id="test" o>
							<option value="请选择">请选择</option>
							<option value="傅立秦">傅立秦</option>
							<option value="王超">王超</option>
							<option value="杨连群">杨连群</option>
							<option value="柏涛">柏涛</option>
							<option value="陈岩">陈岩</option>
							<option value="王丙磊">王丙磊</option>
							<option value="苏绍清">苏绍清</option>
							<option value="李冠卿">李冠卿</option>
						</select>
						<span id="leaderWarn" class="warn" style="color: red"></span>
					</td>
				</tr>
				</c:if> --%>
				<tr>
					<td class='tit'>加班类型</td>
					<td>
						<select name="overtimeType" class="input-medium" id="test" o>
							<option value="请选择">请选择</option>
							<option value="日常加班">日常加班</option>
							<!-- <option value="周末加班">周末加班</option> -->
							<option value="假日加班">节假日加班</option>
						</select>
						<span id="overtimeTypeWarn" class="warn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">加班原因</td>
					<td colspan="3"><form:textarea path="notes" class="required"
							rows="5" maxlength="200" cssStyle="width:500px" /></td>
				</tr>
				<c:if test="${not empty overTime.id && not empty overTime.prText}">
					<tr>
						<td class="tit">项目经理意见</td>
						<td colspan="3">${overTime.prText}</td>
					</tr>
				</c:if>
				<c:if
					test="${not empty overTime.id && not empty overTime.leaderText}">
					<tr>
						<td class="tit">地区领导意见</td>
						<td colspan="3">${overTime.leaderText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty overTime.id && not empty overTime.leadertwoText}">
					<tr>
						<td class="tit">总经理意见</td>
						<td colspan="3">${overTime.leadertwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty overTime.id && not empty overTime.hrText}">
					<tr>
						<td class="tit">人事主管意见</td>
						<td colspan="3">${overTime.hrText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<%-- 			<shiro:hasPermission name="oa:testAudit:edit"> --%>
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty overTime.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<%-- 			</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty overTime.id}">
			<act:histoicFlow procInsId="${overTime.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>