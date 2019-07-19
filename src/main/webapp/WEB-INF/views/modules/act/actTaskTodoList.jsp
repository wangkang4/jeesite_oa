  <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务</title>
	<style type="text/css">
		#userName{
			width:150px;	
		}
	</style>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//获取当前页的URL
			var url = window.location.href;
			if(url.endsWith("?click")){
				//.cli是在本页面的"任务办理"的class.
				// attr("href")表示获取当前标签，即".cli :first" 的 href属性
				var ahref = $(".cli :first").attr("href");
				if(ahref!=null&&ahref!=undefined&&ahref!=""){
					window.location.href = ahref;
				}
			}
		});
		/**
		 * 签收任务
		 */
		function claim(taskId) {
			$.get('${ctx}/act/task/claim' ,{taskId: taskId}, function(data) {
				if (data == 'true'){
		        	top.$.jBox.tip('签收完成');
		            location = '${ctx}/act/task/todo/';
				}else{
		        	top.$.jBox.tip('签收失败');
				}
		    });
		}
		/**
		 * 批量同意或者驳回
		 */
		function agree(flag){
			var tasks = "";
			$(".agree:checked").each(function(){
				tasks = tasks + $(this).val()+"?";
			});
			$.ajax({
				url:"${ctx}/act/task/agree",
				data:{"tasks":tasks,"flag":flag},
				dataType:"json",
				success:function(data){
					if(data.result=="ok"){
						location = '${ctx}/act/task/todo/';
						alertx("批量处理成功");
					}else if(data.result=="error"){
						alertx("请选择需要批量同意的数据");
					}
				}
			});
		}
		
		var text = "";
		//实时筛选，不用点击按钮,setInterval()方法可按照指定的周期（以毫秒计）来调用函数或计算表达式。此处为100毫秒
		setInterval(function(){
			text = $('#input').val();//获取文本框输入
			if($.trim(text) != ""){
				//隐藏表中所有内容 ("#contentTable tr:not('#theader')").hide()
				//显示表中含有text中输入内容 filter(":contains('"+text+"')").show();
				$("#contentTable tr:not('#theader')").hide().filter(":contains('"+text+"')").show();
			}else{
				$('#contentTable tr').show();//当删除文本框的内容时，又重新显示表格所有内容
			}
		},100);
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li><a href="${ctx}/act/task/getSale/">待办报销任务</a></li>
		<li><a href="${ctx}/act/task/newHistoric/">已办任务</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/act/task/end/">完结任务</a></li>
		</shiro:hasRole>
		<%-- <c:if test="${fns:getUser().loginName=='郭晓敏'
			||fns:getUser().loginName=='李晓萌'
			||fns:getUser().loginName=='高会敏'}">
		<li><a href="${ctx}/act/task/end/">完结任务</a></li>
		</c:if> --%>
		<!-- <li><a href="${ctx}/act/task/process/">新建任务</a></li> -->
	</ul>
	<form:form id="searchForm" modelAttribute="act" action="${ctx}/act/task/todo/" method="get" class="breadcrumb form-search">
		<div>
			<label>流程类型：&nbsp;</label>
			<form:select path="procDefKey" class="input-medium">
				<form:option value="all" label="全部流程"/>
				<form:options items="${fns:getDictList('act_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			<label>申请人：</label>
			<sys:treeselect id="user" name="userId" value="${uId}" labelName="userName" labelValue="${uName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			<label>标题：</label>
			<input  class="input-medium" style="width:100px" name="title" value="${title }">
			<label>金额：</label>
			<input type="text" name="" id="input" value="" placeholder="请输入查询关键字"/>
			<label>创建时间：</label>
			<input id="beginDate" style="width:100px;" name="beginDate"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.beginDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
			<input id="endDate" style="width:100px;" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${act.endDate}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			&nbsp;<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href='${ctx}/act/task/todo?userId=clear'">
			&nbsp;<input type="button" class="btn btn-primary" value="批量同意" onclick="agree('yes')"/>
			&nbsp;<input type="button" class="btn btn-primary" value="批量驳回" onclick="agree('no')"/>
		</div>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
			   <!--  <th>姓名</th>
			    <th>部门</th> -->
			    <th style="text-align:center;">选择</th>
				<th style="text-align:center;">标题</th>
				<th style="text-align:center;">当前环节</th>
				<!-- <th>任务内容</th> -->
				<th style="text-align:center;">金额/时间</th>
				<th style="text-align:center;">流程名称</th>
				<th style="text-align:center;">流程版本</th>
				<th style="text-align:center;">创建时间</th>
				<th style="text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="act">
				<c:set var="task" value="${act.task}" />
				<c:set var="vars" value="${act.vars}" />
				<c:set var="procDef" value="${act.procDef}" />
				<%-- <c:set var="procExecUrl" value="${act.procExecUrl}" /> --%>
				<c:set var="status" value="${act.status}" />
				<tr>
				   	<td style="text-align:center;">
				   		<input type="checkbox" class="agree" value="${task.id},${task.taskDefinitionKey},${task.processInstanceId},${task.processDefinitionId}">
				   	</td>
					<td style="text-align:center;">
						<c:if test="${empty task.assignee}">
							<a href="javascript:claim('${task.id}');" title="签收任务">${fns:abbr(not empty act.vars.map.title ? act.vars.map.title : task.id, 60)}</a>
						</c:if>
						<c:if test="${not empty task.assignee}">
							<a href="${ctx}/act/task/form?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">${fns:abbr(not empty vars.map.title ? vars.map.title : task.id, 60)}</a>
						</c:if>
					</td>
					<td style="text-align:center;">
						<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">${task.name}</a>
					</td>
					<%--<td>${task.description}</td> --%>
					<td style="text-align:center;">
						<c:if test="${not empty vars.map.days }">
							${vars.map.days }小时
						</c:if>
						<c:if test="${not empty vars.map.day }">
							${vars.map.day/8 }天
						</c:if>
						<c:if test="${not empty vars.map.chapterTime }">
							${vars.map.chapterTime }天
						</c:if>
						<c:if test="${not empty vars.map.forMoney }">
							<fmt:formatNumber type="number" value="${vars.map.forMoney } " maxFractionDigits="2" pattern="#.00"/>元
						</c:if>
						<c:if test="${not empty vars.map.money }">
							<fmt:formatNumber type="number" value="${vars.map.money } " maxFractionDigits="2" pattern="#.00"/>元
						</c:if>
						<c:if test="${not empty vars.map.payMoney }">
							<fmt:formatNumber type="number" value="${vars.map.payMoney } " maxFractionDigits="2" pattern="#.00"/>元
						</c:if>
						<c:if test="${not empty vars.map.total }">
							<fmt:formatNumber type="number" value="${vars.map.total } " maxFractionDigits="2" pattern="#.00"/>元
						</c:if>
					</td>
					<td style="text-align:center;">${procDef.name}</td>
					<td style="text-align:center;"><b title='流程版本号'>V: ${procDef.version}</b></td>
					<td style="text-align:center;"><fmt:formatDate value="${task.createTime}" type="both"/></td>
					<td>
						<c:if test="${empty task.assignee}">
							<a href="javascript:claim('${task.id}');">签收任务</a>
						</c:if>
						<c:if test="${not empty task.assignee}">
							<a class="cli" href="${ctx}/act/task/form?taskId=${task.id}&taskName=${fns:urlEncode(task.name)}&taskDefKey=${task.taskDefinitionKey}&procInsId=${task.processInstanceId}&procDefId=${task.processDefinitionId}&status=${status}">任务办理</a>
						</c:if>
						<shiro:hasPermission name="act:process:edit">
							<c:if test="${empty task.executionId}">
								<a href="${ctx}/act/task/deleteTask?taskId=${task.id}&reason=" onclick="return promptx('删除任务','删除原因',this.href);">删除任务</a>
							</c:if>
						</shiro:hasPermission>
						<a target="_blank" href="${pageContext.request.contextPath}/act/diagram-viewer?processDefinitionId=${task.processDefinitionId}&processInstanceId=${task.processInstanceId}">跟踪</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
