<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
			<title>修改问题反馈</title>
			<meta name="decorator" content="default" />
			<style type="text/css">
			      table tr td .select{
			         width:400px;
			          
			        }
			     
			     </style>
			<script type="text/javascript">
				$(function(){
					//提交按钮添加点击事件
					$("#btn").click(function(){
						$.ajax({
							"url":"updateFeedback",
							"data":$("#addFeedback").serialize(),
							"type":"post",
							"dataType":"json",
							"success":function(obj){
								alert(obj.message);
								location.href="${ctx}/pms/question/showQuestionForm";
							}
						});
					});		
					
					//给问题名，项目名的input框赋值
					var projectName=$("#projectName").html();
					var problemName=$("#problemName").html();
					$("#probName").val(problemName);
					$("#projName").val(projectName);
				});
			</script>
	</head>
	<body>
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/pms/question/showQuestionForm">项目问题列表</a></li>
			<li class="active"><a href="">修改问题反馈</a></li>
		</ul>
		<form:form id="addFeedback" modelAttribute="QuestionFeedback"
			action="" method="post"
			class="form-horizontal">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed" style="width:800px; ">
			<tr>
				<td style="width:215px;">问题标题</td>
				<td><input type="text"  id="probName" readonly="readonly" name="questionRecord.problemName" value="${feedback.questionRecord.problemName}" ></td>
				<td style="width:115px;">项目名称</td>
				<td><input type="text"  id="projName" readonly="readonly" name="questionRecord.pms.projectName" value="${feedback.questionRecord.pms.projectName}"></td>
			</tr>
		
			<tr>
				<td style="width:215px;">解决时间</td>
				<td>
					<input id="beginDate" name="solveTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${feedback.solveTime}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</tr>
			
			
			<tr style="width:215px;">
				<td height=60px;>解决方案</td>
				<td colspan="3"  style="width:30px;"><textarea style="width:700px;height:70px;" name="solveProgramme">${feedback.solveProgramme}</textarea></td>
			</tr>
			
			<tr style="width:215px;">
				<td height=60px;>其他说明</td>
				<td colspan="3"  style="width:30px;"><textarea style="width:700px;height:70px;" name="remarks">${feedback.remarks}</textarea></td>
			</tr>						
			<tr style="display: none;">
				<td id="projectName">${feedbackProjectName }</td>
				<td id="problemName">${feedbackProblemName }</td>
			</tr>
			<tr>
				<td><input type="hidden" value="${problemId}" name="problemId"/></td>
			</tr>
		</table>
			<div class="">
				<input id="btn" class="btn btn-primary" type="button"  value="提交" /> &nbsp;			
				<input id="btnCancel" class="btn" type="reset" value="重置" />
			</div>
		</form:form>
	</body>
</html>