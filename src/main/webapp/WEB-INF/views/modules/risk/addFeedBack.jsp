<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>添加风险反馈</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/oapms/project/css/progress.css">
     <style type="text/css">
          table tr td{
          width:250px;
         text-align:center;
          }
          table tr td .select{
          width:400px;
          
          }
     
     </style>
	<script type="text/javascript">
		$(function(){
			$("#btn").click(function(){
				$.ajax({
					"url":"updateFeedback",
					"data":$("#addFeedback").serialize(),
					"type":"post",
					"dataType":"json",
					"success":function(obj){
						alert(obj.message);
						location.href="${ctx}/pms/showQuestionForm";
					}
				});
			});						
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/risk/list">项目列表</a></li>
		<li class="active"><a href="">风险反馈</a></li>
	</ul>
	<form:form id="addFeedback" modelAttribute="RiskBack"
		action="${ctx}/pms/riskback/update" method="post"
		class="form-horizontal">
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed" style="width:800px; ">
		<tr>
			<td >风险标题</td>
			<td><input  readonly
					style="outline: none; border: 1px solid #DBDBDB;" type="text"  name="riskInfo.riskName" value="${back.riskInfo.riskName}"></td>
			<td>项目名称</td>
			<td><input readonly
					style="outline: none; border: 1px solid #DBDBDB;"  type="text" name="riskInfo.pmsPro.projectName" value="${back.riskInfo.pmsPro.projectName}"></td>
		</tr>
		<tr>
			<td >风险概率</td>
			<td><input readonly
					style="outline: none; border: 1px solid #DBDBDB;" type="text"  name="riskInfo.riskPro" value="${back.riskInfo.riskPro}"></td>
			<td>风险影响</td>
			<td><input readonly
					style="outline: none; border: 1px solid #DBDBDB;" type="text" name="riskInfo.riskInfu" value="${back.riskInfo.riskInfu}"></td>
		</tr>
		<tr>
			<td >预期发生时间</td>
			<td><input readonly
					style="outline: none; border: 1px solid #DBDBDB;" type="text"  name="riskInfo.expecteTime" value="${back.riskInfo.expecteTime}"></td>
			<td>风险类别</td>
			<td><input readonly
					style="outline: none; border: 1px solid #DBDBDB;" type="text" name="riskInfo.riskType" value="${back.riskInfo.riskType}"></td>
		</tr>
		<tr>
			<td >风险状态</td>
			<td>
					<input readonly
					style="outline: none; border: 1px solid #DBDBDB;" value="${back.riskInfo.riskState}" name="riskinfo.riskState">
			</td>
			<td>责任人</td>
			<td><input readonly
					style="outline: none; border: 1px solid #DBDBDB;" type="text" name="riskInfo.user.name" value="${back.riskInfo.user.name}"></td>
		</tr>
	
		<tr>
			<td>解决时间</td>
			<td>
				<input id="solveTime" name="solveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${back.solveTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
		</tr>
		
		
		<tr >
			<td height=60px;>反馈内容</td>
			<td colspan="3"  style="width:30px;"><textarea style="width:700px;height:70px;"  name="riskContent">${back.riskContent}</textarea></td>
		</tr>
		
				
		
	</table>
	<input type="hidden" name="id" value="${id}">
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="submit"
				value="下一步" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>
	
	
</body>
</html>