<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已办任务</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
		#userName{
			width:150px;	
		}
	</style>
	
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li><a href="${ctx}/act/task/getSale/">待办报销任务</a></li>
		<li class="active"><a href="${ctx}/act/task/newHistoric/">已办任务</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/act/task/end/">完结任务</a></li>
		</shiro:hasRole>
	</ul>
	
	
	<form:form id="searchForm" modelAttribute="historyTask" action="${ctx}/act/task/newHistoric/" method="get" class="breadcrumb form-search">
		<label>申请人：</label>
			<sys:treeselect id="user" name="userId" value="${userId}" labelName="userName" labelValue="${userName}"
				title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		<label>标题：</label>
			<input type="text" name="title"  value="${titleName }" placeholder="请输入查询关键字"/>
		<label>流程类型：</label>
		<select class="input-medium" name="preceName">
			<option value=${preceName }>${preceName }</option>
			<option value="合肥研发报销">合肥研发报销</option>
			<option value="济南研发报销">济南研发报销</option>
			<option value="合肥市场报销">合肥市场报销</option>
			<option value="济南市场报销">济南市场报销</option>
			<option value="华东市场报销">华东市场报销</option>
			<option value="领导报销">领导报销</option>
			<option value="合肥预审批">合肥预审批</option>
			<option value="济南预审批">济南预审批</option>
			<option value="领导预审批">领导预审批</option>
			<option value="山西预审批">山西预审批</option>
			<option value="华东区域预审批">华东区域预审批</option>
			<option value="研发对外付款">研发对外付款</option>
			<option value="市场对外付款">市场对外付款</option>
			<option value="领导对外付款">领导对外付款</option>
			<option value="合同审批">合同申请</option>
			<option value="研发部借款">研发部借款</option>
			<option value="市场部借款">市场部借款</option>
			<option value="领导借款">领导借款</option>
			<option value="合肥研发用印">合肥研发用印</option>
			<option value="济南研发用印">济南研发用印</option>
			<option value="合肥市场用印">合肥市场用印</option>
			<option value="济南市场用印">济南市场用印</option>
			<option value="山西市场用印">山西市场用印</option>
			<option value="华东区域用印">华东区域用印</option>
			<option value="证照申请">证照申请</option>
			<option value="印章刻制">印章刻制</option>
			<option value="合肥研发请假">合肥研发请假</option>
			<option value="合肥办市场请假">合肥市场请假</option>
			<option value="济南研发请假">济南研发请假</option>
			<option value="济南办市场请假">济南市场请假</option>
			<option value="山西市场请假">山西市场请假</option>
			<option value="华东区域请假">华东区域请假</option>
			<option value="北京总部请假">北京总部请假</option>
			<option value="财务请假">财务请假</option>
			<option value="合肥研发加班">合肥研发加班</option>
			<option value="合肥市场加班">合肥市场加班</option>
			<option value="济南研发加班">济南研发加班</option>
			<option value="济南市场加">济南市场加班</option>
			<option value="山西市场加班">山西市场加班</option>
			<option value="华东区域加班">华东区域加班</option>
			<option value="北京总部加班">北京总部加班</option>
			<option value="财务加班">财务加班</option>
			<option value="采购申请">采购申请</option>
			<option value="专利申请">专利申请</option>
			<option value="开票申请">开票申请</option>
			<option value="出差申请">出差申请</option>
			<option value="团建申请">团建申请</option>
		</select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th style="text-align:center;">标题</th>
				<th style="text-align:center;">流程名</th>
				<th style="text-align:center;">完成时间</th>
				<th style="text-align:center;">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="act">
				
				<tr>
					<td style="text-align:center;">
						${act.reason }
					</td>
					<td style="text-align:center;">
						${act.prdName }
					</td>
					<td style="text-align:center;"><fmt:formatDate value="${act.cdDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td style="text-align:center;">
						<a href="${ctx}/act/task/form?
						taskId=${act.taskId}
						&taskName=${fns:urlEncode(task.name)}
						&taskDefKey=${act.taskDefKey}
						&procInsId=${act.procId} 
						&procDefId=${act.prdId}
						&status=finish">详情</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<%-- <div class="pagination">${page}</div> --%>
	<div class="pager">
	<font size="2">共 ${page.totalPageCount} 页</font> <font size="2">第  
		${page.pageNow} 页</font> <a href="${ctx}/act/task/newHistoric?pageNow=1&userId=${userId}&title=${titleName}&preceName=${preceName}">首页</a>
	<c:choose>
		<c:when test="${page.pageNow - 1 > 0}"> 
			<a href="${ctx}/act/task/newHistoric?pageNow=${page.pageNow - 1}&userId=${userId}&title=${titleName}&preceName=${preceName}">上一页</a>  
		</c:when>
		<c:when test="${page.pageNow - 1 <= 0}">
		<a href="${ctx}/act/task/newHistoric?pageNow=1&userId=${userId}&title=${titleName}&preceName=${preceName}">上一页</a> 
		</c:when>
	</c:choose>  
	
	<c:choose>
		<c:when test="${page.totalPageCount==0}">
		<a href="${ctx}/act/task/newHistoric?pageNow=${page.pageNow}&userId=${userId}&title=${titleName}&preceName=${preceName}">下一页</a>
		</c:when>
		<c:when test="${page.pageNow + 1 < page.totalPageCount}"> 
		<a href="${ctx}/act/task/newHistoric?pageNow=${page.pageNow + 1}&userId=${userId}&title=${titleName}&preceName=${preceName}">下一页</a>
		</c:when>  
		<c:when test="${page.pageNow + 1 >= page.totalPageCount}"> 
		<a href="${ctx}/act/task/newHistoric?pageNow=${page.totalPageCount}&userId=${userId}&title=${titleName}&preceName=${preceName}">下一页</a>
		</c:when> 
	</c:choose>
	<c:choose>  
            <c:when test="${page.totalPageCount==0}">  
            <a href="${ctx}/act/task/newHistoric?pageNow=${page.pageNow}&userId=${userId}&title=${titleName}&preceName=${preceName}">尾页</a> 
            </c:when>  
            <c:otherwise>
            <a href="${ctx}/act/task/newHistoric?pageNow=${page.totalPageCount}&userId=${userId}&title=${titleName}&preceName=${preceName}">尾页</a> 
            </c:otherwise>  
        </c:choose> 
        <font size="2">共 ${totalCount } 条</font>
       </div>
</body>
</html>
