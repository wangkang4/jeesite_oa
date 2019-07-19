<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ page  import="java.util.List" %>
<html>
<head>
<title>风险添加</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/oapms/project/css/progress.css">
<script src="${ctxStatic}/oapms/project/js/projectForm.js"></script>
<script type="text/javascript">
	function submitAdd(){
		var bool=true;
		var riskName=$("#riskName").val();
		console.log("riskName:"+riskName);
		if(riskName =="" || riskName==undefined || riskName==null){
			console.log("进入判断了吗");
			bool=false;
			$("#riskName").val("请输入项目名");
			$("#riskName").css("color","red");
		}
		console.log("bool:"+bool);
		if(bool ==true){
			console.log("提交了吗");
			$("#addForm").submit();
			console.log("提交成功");
		}	
		console.log("最后");
	}
	
	
	
</script>
     <style type="text/css">
          table tr td{
          width:250px;
         text-align:center;
          }
          table tr td .select{
          width:400px;          
          }
     </style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/risk/list">风险列表</a></li>
		<li class="active"><a href="">风险添加</a></li>
	</ul>
	<form:form id="addForm" modelAttribute="RiskInfo"
		action="${ctx }/pms/risk/add" method="post"
		class="form-horizontal">
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed" style="width:800px; ">
		<tr>
			<td >风险标题</td>
			<td><input placeholder="请输入项目名"  id="riskName" type="text"  name="riskName"  value="${risk.riskName}"></td>
			<td>项目名称</td>
			<td id="messge"><select style="width:215px;" id="projectName"  name="projectName" >
			<option value=""   >--请选择--</option>	
						<%
							List<String> list=(List<String>)request.getAttribute("list");
						for(int i=0;i<list.size();i++){
							out.println("<option  value="+list.get(i)+">"+list.get(i)+"</option>");
						}
						%>
			</select></td>
		</tr>
		<tr>
			<td>风险概率</td>
			<td><select style="width:215px;" name="riskPro" >
				<option  value="${risk.riskPro}">${risk.riskPro}</option>
				<option value="极低">极低</option>
					<option value="低">低</option>
					<option value="中">中</option>
					<option value="高">高</option>
					<option value="极高">极高</option>
			</select></td>
			<td>风险影响</td>
			<td>
			<select style="width:215px;" name="riskInfu">
			<option value="${risk.riskInfu}"> ${risk.riskInfu} </option>
				<option value="极低">极低</option>
					<option value="低">低</option>
					<option value="中">中</option>
					<option value="高">高</option>
					<option value="极高">极高</option>
			</select>
			</td>
		</tr>
		<tr>
			<td>预期发生时间</td>
			<td>
			  <select style="width:215px;" name="expecteTime">
					 <option value="${risk.expecteTime}">${risk.expecteTime}</option>
					<option value="极远">极远</option>
					<option value="远">远</option>
					<option value="中">中</option>
					<option value="近">近</option>
					<option value="极近">极近</option>
				</select>
			</td>
			<td>风险类别</td>
			<td>
			   <select style="width:215px;" name="riskType">
			   <option value="${risk.riskType}">${risk.riskType}</option>
				<option value="技术风险">技术风险</option>
					<option value="市场风险">市场风险</option>
					<option value="成本风险">成本风险</option>
					<option value="进度风险">进度风险</option>
					<option value="管理风险">管理风险</option>
			</select>		
			</td>
		</tr>
		<tr>
			<td>风险状态</td>
			<td>
			   <select style="width:215px;" name="riskState">
			   <option value="${risk.riskDescrible}">${risk.riskState}</option>
				<option value="已关闭">已关闭</option>
					<option value="未关闭">未关闭</option>
			</select>
			</td>
			<td>责任人</td>
			<td>
                <sys:treeselect id="user00" name="responseId" value="${user.id}" labelName="saler.name" labelValue="${risk.user.name}"
					title="用户" url="/sys/office/treeData?type=3"   allowClear="true" notAllowSelectParent="true"/>
			</td>
		</tr>
		<tr>
			<td>开始时间</td>
			<td>
				<input id="beginDate" name="startTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${risk.startTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			
			</td>
			<td>到期时间</td>
			<td>
				<input id="endDate" name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${risk.endTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</td>
		</tr>
		<tr >
			<td height=60px;>详细描述</td>
			<td colspan="3"  style="width:30px;"><textarea style="width:100px;height:100px;" name="riskDescrible"> ${risk.riskDescrible}</textarea></td>
		</tr>
		<tr>
			<td height=60px>缓解和应对措施</td>
			<td colspan="3" style="width:30px;"><input type="text" style="height:60px; width:500px;" name="solveProgramme"  value=${risk.solveProgramme}></td>
		</tr>
		<tr>
			<td>应对策略</td>
			<td>
			   <select style="width:215px;" name="riskAnswer">
			   <option value="${risk.riskDescrible}">${risk.riskDescrible}</option>
				<option value="预防">预防</option>
					<option value="缓解">缓解</option>
					<option value="转移">转移</option>
					<option value="接受">接受</option>
					<option value="备用">备用</option>
			</select>		
			</td>
		</tr>
	</table>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button" onclick="return submitAdd()"
				value="下一步" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	
	</form:form>
	
	
</body>
</html>