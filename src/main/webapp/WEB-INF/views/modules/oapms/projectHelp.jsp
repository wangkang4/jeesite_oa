<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目求助</title>
<meta name="decorator" content="default" />
<style type="text/css">  
	.limit { 
		 width: 100px; 
		 text-overflow: ellipsis; 
		 -moz-text-overflow: ellipsis;
		 white-space: nowrap; 
		 overflow: hidden; 
	}
	</style>
<script type="text/javascript">
		function page(n, s) {
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
			return false;
		}
		function helpAdd(){
			var id = $("#projectId").val();
			location.href = "${ctx}/pms/projectHelp/form?id=" + id;
		}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/project/projectDetail?id=${pmsProjectHelp.project.projectId}">基本信息</a></li>
		<li><a href="${ctx}/pms/comment/projectComment?id=${pmsProjectHelp.project.projectId}">评论</a></li>
		<li><a href="${ctx}/pms/comment/projectDocument?id=${pmsProjectHelp.project.projectId}">文档</a></li>
		<li class="active"><a href="#">求助</a></li>
		<li><a href="${ctx}/pms/projectHelp/expenseList?id=${pmsProjectHelp.project.projectId}">费用</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="pmsProjectHelp"
		action="${ctx}/pms/projectHelp/helpList?id=${pmsProjectHelp.project.projectId}" method="post"
		class="breadcrumb form-search">
		<form:hidden path="project.projectId" id="projectId" value="${pmsProjectHelp.project.projectId}" />
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<ul class="ul-form">
			<li><label>求助状态：</label>
			<form:select id="status" path="status" class="input-medium" >
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('project_help_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input type="button" class="btn btn-primary"  onclick="javascript:window.location.href=''" value="清除"></li>
			<li class="btns" style="margin-right:50px;"><input id="btn"  class="btn btn-primary" type="button" value="求助新增"
				onclick="helpAdd()" /></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>求助人</th>
				<th>求助内容</th>
				<th>求助时间</th>
				<th>协助者</th>
				<th>求助状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="projectHelp">
				<tr>
					<td>
						${projectHelp.helpBy.name }
					</td>
					<td>
						<div class="limit">
							<abbr style="text-decoration:none; border-bottom: 0 dotted;" title="${projectHelp.content }">
								${projectHelp.content }
							</abbr>
						</div>
					</td>
					<td>
						<fmt:formatDate value="${projectHelp.helpTime }" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						${projectHelp.helper.name }
					</td>
					<td>
						<c:if test="${projectHelp.status eq '1'}">
							解决中
						</c:if>
						<c:if test="${projectHelp.status ne '1'}">
							${fns:getDictLabel(projectHelp.status, 'project_help_status', '')}
						</c:if>
					</td>
					<td><a href="${ctx}/pms/projectHelp/form?helpId=${projectHelp.helpId}">修改</a> <a
						href="${ctx}/pms/projectHelp/delete?id=${projectHelp.helpId}"
						onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>