<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目文档</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		
		function doUpload(){
			var id = $("#projectId").val();
			location.href = "${ctx}/pms/comment/uploadForm?id=" + id;
		}
	
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/project/projectDetail?id=${pmsProjectDocument.project.projectId}">基本信息</a></li>
		<li><a href="${ctx}/pms/comment/projectComment?id=${pmsProjectDocument.project.projectId}">评论</a></li>
		<li class="active"><a href="#">文档</a></li>
		<li><a href="${ctx}/pms/projectHelp/helpList?id=${pmsProjectDocument.project.projectId}">求助</a></li>
		<li><a href="${ctx}/pms/projectHelp/expenseList?id=${pmsProjectDocument.project.projectId}">费用</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsProjectDocument"
		action="${ctx}/pms/comment/projectDocument?id=${pmsProjectDocument.project.projectId}" method="post"
		class="breadcrumb form-search">
		<input type="hidden" id="projectId"  name="project.projectId" value="${pmsProjectDocument.project.projectId}">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>文档名称：</label>
				<form:input path="documentName" htmlEscape="false" maxlength="200" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input type="button" class="btn btn-primary"  onclick="javascript:window.location.href=''" value="清除"></li>
			<li class="btns"><input id="btn"  class="btn btn-primary" type="button" value="文件上传" onclick="doUpload()"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>文档名称</th>
				<th>上传人</th>
				<th>上传时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="projectDocument">
				<tr>
					<td>
						<a href="${ctx}/project/upload/projectDocumentDownload?id=${projectDocument.documentId}">${projectDocument.documentName }</a>
					</td>
					<td>
						${projectDocument.uploadBy.name }
					</td>
					<td>
						<fmt:formatDate value="${projectDocument.uploadTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>