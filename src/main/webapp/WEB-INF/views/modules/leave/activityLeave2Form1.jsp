<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>请假管理</title>
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
	//获取请假类型中的值,从而进一步判断其是否需要启用图片上传功能
	function change(){
		var val=$("#leaveType").val();
		console.log("val:"+val);
		console.log("length:"+$("#inputFile").length);
		var sameIdObj = $('*[id=remove]');
		if($("#remove").html()==null){
			
		}else{
			$("#formID").remove();
			$("#zhuyi").remove();
		}
		if($("#remove").html()==null){
			if(val==4||val==5||val==6){
				$("#upload").append('<form id="formID" enctype="multipart/form-data" action="" method="POST">'+
						' <div id="remove"><input type="file" id="inputFile" name="attach"/><span class="cutorm-style">'+
						'<span class="left-button">选择附件</span><span class="right-text" id="rightText"></span>'
						+'</span><input type="button" id="load" value="开始上传">'
						+'</form>');
				$("#upload").append('<span id="zhuyi">婚假、产假、丧假请尽量上传证明!</span>');
				$("#zhuyi").attr("style","color:red;");
			}
		}
		$("#load").click(function(){
			var file=inputFile.files[0];
			var frm=new FormData();
			frm.append("attach",file);
			$.ajax({
				"url":"upload",
				"data":frm,
				"type":"post",
				"dataType":"json",
				"processData":false,
				"contentType":false,
				"success":function(obj){
					alert(obj.data);
				}
			});
		});
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var myDate = new Date();
		var reason="请假申请-"+myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();
		$("input[name='reason']").val(reason);
		$("input[name='reason']").focus();
		$("#numDay").blur(function() {
			yanzhen(bool);
		});
	});
	function yanzhen(bool){
		var start = Date.parse($("#startTime").val());
		var end = Date.parse($("#endTime").val());
		var reg1=/^\d+(\.[05])?$/;
		
		var days = Math.abs(parseInt((end - start) / 1000 / 3600));
		var reg = /^[1-9][0-9]*$/;
		var numDay = $("#numDay").val();
		var numHour=numDay*8;
		var numHours=numDay*24;
		console.log("numHours:"+numHours);
		var type=$("#select option:checked").text();
		var useDays=$("select").val();
		var useDay=$("#useDays").val();
		alert("numHour:"+numHour)
		alert("useDays:"+useDays)
		alert("type:"+type);
		$("#numDay").next().text("");
		if("调休"==type){
			if(numHour>useDay){
				bool=false;
				$("#numDay").next().text("可用调休时间不够！");
			}
		}else if (numDay == "") {
			bool = false;
			$("#numDay").next().text("*必填信息");
		} else if (!reg1.test(numDay)) {
			bool = false;
			$("#numDay").next().text("*请假时间必须是0.5的整倍数,且按一天八小时计算");
		}/* else if(numHours > days){
			bool=false;
			$("#numDay").next().text("*请假时间不得大于结束时间和开始时间之差");
		} */
		return bool;
	}
	function save() {
		var attachAddress=$("#inputFile").val();
		$("#address").val(attachAddress);
		var bool = true;
		bool = yanzhen(bool);
		
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
	<input id="useDays" type="hidden"  value="${activityLeave2.user.useOverTimeDays}"/>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/activity/leave2/">请假列表</a></li>
		<li class="active"><a
			href="${ctx}/activity/leave2/form?id=${activityLeave2.id}"><shiro:hasPermission
					name="oa:testAudit:edit">请假${not empty activityLeave2.id?'修改':'申请'}流程</shiro:hasPermission>
				<shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission></a></li>
				<c:if test="${ not empty name}">
			<li ><a href="${ctx}/activity/leave2/allList">公司人员请假列表</a></li>
		</c:if>
	</ul>
	<form:form id="inputForm" modelAttribute="activityLeave2"
		action="${ctx}/activity/leave2/save" method="post"
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
			<legend>请假申请</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td>
						<input name="reason" type="text">
					</td>
				</tr>
				<tr>
					<td class="tit">姓名</td>
					<td><input id="user.name" name="user.id"
						value="${activityLeave2.user.id }" type="hidden"> <input
						id="userId" name="userName" type="text"
						value="${activityLeave2.user.name }" readonly="readonly"> <%-- 						<sys:treeselect id="user" name="user.id" value="${activityLeave2.user.id}" labelName="user.name" labelValue="${activityLeave2.user.name}"  --%>
						<%-- 							title="用户" url="/sys/office/treeData?type=3" cssClass="required recipient" cssStyle="width:150px"  --%>
						<%-- 							allowClear="true" notAllowSelectParent="true" smallBtn="false"/> --%>
					</td>
					<td class="tit">部门</td>
					<td>
						 <input id="office.name" name="office.id" value="${activityLeave2.office.id }" type="hidden">
						<input id="officeId" name="officeName" type="text" value="${activityLeave2.office.name }" readonly="readonly" />
						 
						 <%-- <sys:treeselect
							id="office" name="office.id" value="${activityLeave2.office.id}"
							labelName="office.name"
							labelValue="${activityLeave2.office.name}" title="部门"
							url="/sys/office/treeData?type=2" cssClass="required recipient"
							cssStyle="width:150px" allowClear="true"
							notAllowSelectParent="true" smallBtn="false" /> --%>
					</td>
				</tr>
				<tr>
					<td class="tit">开始时间</td>
					<td><input id="startTime" name="startTime"
						value="<fmt:formatDate value="${activityLeave2.startTime}" pattern="yyyy-MM-dd HH:mm:ss
						"/>"
						pattern="yyyy-MM-dd HH:mm:ss" type="text" readonly="readonly"
						maxlength="20" class="Wdate required"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
						<!-- ,minDate:'%y-%M-{%d}',maxDate:'#F{$dp.$D(\'endTime\')}' --></td>
					<td class="tit">结束时间</td>
					<td><input id="endTime" name="endTime"
						value="<fmt:formatDate value="${activityLeave2.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
						pattern="yyyy-MM-dd HH:mm:ss" type="text" readonly="readonly"
						maxlength="20" class="Wdate required"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false,minDate:'#F{$dp.$D(\'startTime\')}'});" />
						<!-- <span>结束时间为上班时间</span> --></td>
				</tr>
				<tr>
					<td class="tit">请假类型</td>
					<td><form:select id="select" path="leaveType" onchange="change()">
							<form:options items="${fns:getDictList('oa_leave_type')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select></td>
					<td class="tit">请假时间</td>
					<td>
						<!-- onclick="getLeaveDays()" --> <form:input path="days"
							htmlEscape="false" maxlength="50" id="numDay" />&nbsp;&nbsp;天 <span
						style="color: red"></span>
					</td>
					
				</tr>
				<tr >
					<td class="tit">请假原因</td>
					<td id="upload" colspan="3"><form:textarea path="notes" class="required"
							rows="5" maxlength="200" cssStyle="width:500px" /></td>
						
				</tr>
				<tr>
				
				</tr>
				<c:if
					test="${not empty activityLeave2.id && not empty activityLeave2.prText}">
					<tr>
						<td class="tit">项目经理意见</td>
						<td colspan="3">${activityLeave2.prText}</td>
					</tr>
				</c:if>
				<c:if
					test="${not empty activityLeave2.id && not empty activityLeave2.leaderText}">
					<tr>
						<td class="tit">地区领导意见</td>
						<td colspan="3">${activityLeave2.leaderText}</td>
					</tr>
				</c:if>
				<c:if
					test="${not empty activityLeave2.id && not empty activityLeave2.leadertwoText}">
					<tr>
						<td class="tit">市场部经理意见</td>
						<td colspan="3">${activityLeave2.leadertwoText}</td>
					</tr>
				</c:if>
				<c:if
					test="${not empty activityLeave2.id && not empty activityLeave2.leaderthreeText}">
					<tr>
						<td class="tit">市场部领导意见</td>
						<td colspan="3">${activityLeave2.leaderthreeText}</td>
					</tr>
				</c:if>
				<c:if
					test="${not empty activityLeave2.id && not empty activityLeave2.hrText}">
					<tr>
						<td class="tit">人事主管意见</td>
						<td colspan="3">${activityLeave2.hrText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<div class="form-actions">
			<%-- 			<shiro:hasPermission name="oa:testAudit:edit"> --%>
		
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
			<c:if test="${not empty activityLeave2.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
				</c:if>
			<%-- 			</shiro:hasPermission> --%>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty activityLeave2.id}">
			<act:histoicFlow procInsId="${activityLeave2.act.procInsId}" />
		</c:if>
		
	</form:form>
</body>
</html>