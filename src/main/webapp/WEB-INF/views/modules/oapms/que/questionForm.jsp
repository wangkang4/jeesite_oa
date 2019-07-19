<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>项目问题</title>
		<meta name="decorator" content="default" />
		<script type="text/javascript">
			function page(n, s) {
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
				return false;
			}
			
			//给"反馈"添加点击事件，异步提交项目名，问题名和问题id
			$(function(){
				$(".feedback").click(function(){
					var data="projectName="+$(this).parent().siblings(".projName").html()+"&problemName="+$(this).parent().siblings(".probName").html()+"&id="+$(this).parent().siblings(".probId").html();
					$.ajax({
						"url":"../feedback/showQuestionFeedback",
						"data":data,
						"type":"post",
						"dataType":"json",
						"success":function(obj){
							location.href="${ctx}/pms/feedback/showFeedback";
						}
					});
				});
				
			});
		</script>
	</head>
	<body>
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx}/pms/question/showQuestionForm">项目问题列表</a></li>
			<li ><a href="${ctx}/pms/question/showAddQuestion">项目问题添加</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="questionRecord"
			action="${ctx}/pms/question/showQuestionForm" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<ul class="ul-form">
				<li><label>名称：</label>
					<input type="text"  name="projectName"  value="${projectName }">	
				</li>
				<li><label>状态：</label>
					<select name="problemState">
						<c:if test="${problemState==null}">			
							<option value="--请选择--">--请选择--</option>
							<option value="已解决">已解决</option>
							<option value="未解决">未解决</option>
						</c:if>
						<c:if test="${problemState=='未解决'}">		
							<option value="未解决">未解决</option>
							<option value="已解决">已解决</option>
						</c:if>
						<c:if test="${problemState=='已解决'}">		
							<option value="已解决">已解决</option>
							<option value="未解决">未解决</option>
						</c:if>
					</select>
					
					
				</li>
				<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
				<li class="btns"><input type="button" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除"></li>
				<li class="clearfix"></li>
			</ul>
		</form:form>
		
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>项目名称</th>
					<th>问题标题</th>
					<th>类型</th>
					<th>来源</th>
					<th>状态</th>
					<th>责任人</th>
					<th>创建时间</th>
					<th>附件</th>
					<th>操作</th>
					<th style="display: none;">问题id</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="q">
					<tr>
						<td class="projName">	
						   	${q.pms.projectName}
						</td>
						<td class="probName">
							${q.problemName}
						</td>
						<td>
							${q.problemType}
						</td>
						<td>
							${q.problemSource}
						</td>
						<td>
							${q.problemState}
						</td>
						<td>
							${q.user.name}
						</td>
						<td>
							${q.createdTime}
						</td>											
						<td ><a  href="${ctx}/pms/question/download?attachAddress=${q.attachAddress}">${q.attachId}</a></td>					
						<td><a  href="${ctx}/pms/question/showUpdateQuestion?id=${q.id}">修改</a> <a 
							href="${ctx}/pms/question/deleteQuestion?id=${q.id}"
							onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
							<a  class="feedback" href="#">反馈</a>
						</td>
						
						<td style="display: none;" class="probId">${q.id}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>

	</body>
</html>