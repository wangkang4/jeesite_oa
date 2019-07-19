<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>风险添加</title>
<meta name="decorator" content="default" />
<link rel="stylesheet"
	href="${ctxStatic}/oapms/project/css/progress.css">
<script src="${ctxStatic}/oapms/project/js/projectForm.js"></script>
<script type="text/javascript">
	function check() {
		console.log("执行ajax1");
		var projectName = $("#projectName").val();
		console.log(projectName);
		console.log("执行ajax2");
		$.ajax({
			"type" : "post",
			"url" : "${ctx}/pms/risk/getProjectId",
			"data" : "projectName=" + projectName,
			"dataType" : "json",
			"success" : function(data) {
				console.log("执行ajax3");
				if (data != null) {
					$("#messge font").html("项目名存在");
				} else {
					console.log("执行ajax4");
					$("#messge font").html("项目名不存在");
					$("#projectName").html("");
				}
			}
		});
	}
	$(function() {
		//先获取此select将要显示的值
		var ok = $("#clearPro").val();
		console.log("概率:" + ok);
		//获取此select所有节点的属性值
		var array = new Array(); //定义数组   
		$("#clearPro option").each(function() { //遍历所有option  
			var txt = $(this).val(); //获取option值   
			console.log("写死的option:" + txt);
			if (txt != '') {
				array.push(txt); //添加到数组中  
			}

		})
		for (var i = 0; i < array.length; i++) {
			console.log("length:" + array.length);
			console.log("pro:" + array[i]);
			if (ok == array[i]) {
				//移除第一个节点
				console.log("移除了？");
				$("#clearPro option:eq(0)").remove();
				console.log("移除成功");
				//把select的
				$("#clearPro").val(ok);
				console.log("复制成功");
				return;
			}
		}
	});
	$(function() {
		//先获取此select将要显示的值
		var ok = $("#clearInfu").val();
		console.log("概率:" + ok);
		//获取此select所有节点的属性值
		var array = new Array(); //定义数组   
		$("#clearInfu option").each(function() { //遍历所有option  
			var txt = $(this).val(); //获取option值   
			console.log("写死的option:" + txt);
			if (txt != '') {
				array.push(txt); //添加到数组中  
			}
		})
		for (var i = 0; i < array.length; i++) {
			console.log("length:" + array.length);
			console.log("pro:" + array[i]);
			if (ok == array[i]) {
				//移除第一个节点
				console.log("移除了？");
				$("#clearInfu option:eq(0)").remove();
				console.log("移除成功");
				//把select的
				$("#clearInfu").val(ok);
				console.log("复制成功");
				return;
			}
		}
	});
	$(function() {
		//先获取此select将要显示的值
		var ok = $("#clearTime").val();
		console.log("概率:" + ok);
		//获取此select所有节点的属性值
		var array = new Array(); //定义数组   
		$("#clearTime option").each(function() { //遍历所有option  
			var txt = $(this).val(); //获取option值   
			console.log("写死的option:" + txt);
			if (txt != '') {
				array.push(txt); //添加到数组中  
			}
		})
		for (var i = 0; i < array.length; i++) {
			console.log("length:" + array.length);
			console.log("pro:" + array[i]);
			if (ok == array[i]) {
				//移除第一个节点
				console.log("移除了？");
				$("#clearTime option:eq(0)").remove();
				console.log("移除成功");
				//把select的
				$("#clearTime").val(ok);
				console.log("复制成功");
				return;
			}
		}
	});
	$(function() {
		//先获取此select将要显示的值
		var ok = $("#clearType").val();
		console.log("概率:" + ok);
		//获取此select所有节点的属性值
		var array = new Array(); //定义数组   
		$("#clearType option").each(function() { //遍历所有option  
			var txt = $(this).val(); //获取option值   
			console.log("写死的option:" + txt);
			if (txt != '') {
				array.push(txt); //添加到数组中  
			}

		})
		for (var i = 0; i < array.length; i++) {
			console.log("length:" + array.length);
			console.log("pro:" + array[i]);
			if (ok == array[i]) {
				//移除第一个节点
				console.log("移除了？");
				$("#clearType option:eq(0)").remove();
				console.log("移除成功");
				//把select的
				$("#clearType").val(ok);
				console.log("复制成功");
				return;
			}
		}
	});
	$(function() {
		//先获取此select将要显示的值
		var ok = $("#clearState").val();
		console.log("概率:" + ok);
		//获取此select所有节点的属性值
		var array = new Array(); //定义数组   
		$("#clearState option").each(function() { //遍历所有option  
			var txt = $(this).val(); //获取option值   
			console.log("写死的option:" + txt);
			if (txt != '') {
				array.push(txt); //添加到数组中  
			}

		})
		for (var i = 0; i < array.length; i++) {
			console.log("length:" + array.length);
			console.log("pro:" + array[i]);
			if (ok == array[i]) {
				//移除第一个节点
				console.log("移除了？");
				$("#clearState option:eq(0)").remove();
				console.log("移除成功");
				//把select的
				$("#clearState").val(ok);
				console.log("复制成功");
				return;
			}
		}
	});
	$(function() {
		//先获取此select将要显示的值
		var ok = $("#clearAnswer").val();
		console.log("概率:" + ok);
		//获取此select所有节点的属性值
		var array = new Array(); //定义数组   
		$("#clearAnswer option").each(function() { //遍历所有option  
			var txt = $(this).val(); //获取option值   
			console.log("写死的option:" + txt);
			if (txt != '') {
				array.push(txt); //添加到数组中  
			}

		})
		for (var i = 0; i < array.length; i++) {
			console.log("length:" + array.length);
			console.log("pro:" + array[i]);
			if (ok == array[i]) {
				//移除第一个节点
				console.log("移除了？");
				$("#clearAnswer option:eq(0)").remove();
				console.log("移除成功");
				//把select的
				$("#clearAnswer").val(ok);
				console.log("复制成功");
				return;
			}
		}
	});
</script>

<style type="text/css">
table tr td {
	width: 250px;
	text-align: center;
}

table tr td .select {
	width: 400px;
}
</style>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/risk/list">风险列表</a></li>
		<li class="active"><a href="">风险修改</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="RiskInfo"
		action="${ctx }/pms/risk/update?id=${risk.id}" method="post"
		class="form-horizontal">
		<input name="responseName" type="hidden" value="${risk.user.name}">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed"
			style="width: 800px;">
			<tr>
				<td>风险标题</td>
				<td><input type="text" name="riskName" value="${risk.riskName}"></td>
				<td>项目名称</td>
				<td id="messge">&nbsp;<input readonly
					style="outline: none; border: 1px solid #DBDBDB;" id="projectName"
					name="projectName"
					value="&nbsp;&nbsp;&nbsp;${risk.pmsPro.projectName}"></td>
			</tr>
			<tr>
				<td>风险概率</td>
				<td><select id="clearPro" class="clear" style="width: 215px;"
					name="riskPro">
						<option value="${risk.riskPro}">${risk.riskPro}</option>
						<option value="极低">极低</option>
						<option value="低">低</option>
						<option value="中">中</option>
						<option value="高">高</option>
						<option value="极高">极高</option>
				</select></td>
				<td>风险影响</td>
				<td><select id="clearInfu" class="clear" style="width: 215px;"
					name="riskInfu">
						<option value="${risk.riskInfu}">${risk.riskInfu}</option>
						<option value="极低">极低</option>
						<option value="低">低</option>
						<option value="中">中</option>
						<option value="高">高</option>
						<option value="极高">极高</option>
				</select></td>
			</tr>
			<tr>
				<td>预期发生时间</td>
				<td><select id="clearTime" class="clear" style="width: 215px;"
					name="expecteTime">
						<option value="${risk.expecteTime}">${risk.expecteTime}</option>
						<option value="极远">极远</option>
						<option value="远">远</option>
						<option value="中">中</option>
						<option value="近">近</option>
						<option value="极近">极近</option>
				</select></td>
				<td>风险类别</td>
				<td><select id="clearType" class="clear" style="width: 215px;"
					name="riskType">
						<option value="${risk.riskType}">${risk.riskType}</option>
						<option value="技术风险">技术风险</option>
						<option value="市场风险">市场风险</option>
						<option value="成本风险">成本风险</option>
						<option value="进度风险">进度风险</option>
						<option value="管理风险">管理风险</option>
				</select></td>
			</tr>
			<tr>
				<td>风险状态</td>
				<td><select id="clearState" class="clear" style="width: 215px;"
					name="riskState">
						<option value="${risk.riskDescrible}">${risk.riskState}</option>
						<option value="已关闭">已关闭</option>
						<option value="未关闭">未关闭</option>
				</select></td>
				<td>责任人</td>
				<td><sys:treeselect id="user00" name="responseName1"
						value="${risk.user.id}" labelName="saler.name"
						labelValue="${risk.user.name}" title="用户"
						url="/sys/office/treeData?type=3" allowClear="true"
						notAllowSelectParent="true" /></td>

			</tr>
			<tr>
				<td>开始时间</td>
				<td><input id="beginDate" name="startTime" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate"
					style="width: 163px;"
					value="<fmt:formatDate value="${risk.startTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></td>
				<td>到期时间</td>
				<td><input id="endDate" name="endTime" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate"
					style="width: 163px;"
					value="<fmt:formatDate value="${risk.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></td>
			</tr>
			<tr>
				<td height=60px;>详细描述</td>
				<td colspan="3" style="width: 30px;"><textarea rows="10"
						cols="400" name="riskDescrible"> ${risk.riskDescrible}</textarea></td>
			</tr>
			<tr>
				<td height=60px>缓解和应对措施</td>
				<td colspan="3" style="width: 30px;"><input type="text"
					style="height: 60px; width: 500px;" name="solveProgramme"
					value=${risk.solveProgramme}></td>
			</tr>
			<tr>
				<td>应对策略</td>
				<td><select id="clearAnswer" style="width: 215px;"
					class="clear" name="riskAnswer">
						<option value="${risk.riskAnswer}">${risk.riskAnswer}</option>
						<option value="预防">预防</option>
						<option value="缓解">缓解</option>
						<option value="转移">转移</option>
						<option value="接受">接受</option>
						<option value="备用">备用</option>
				</select></td>
			</tr>
		</table>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="submit" value="下一步" />
			&nbsp; <input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>


</body>
</html>