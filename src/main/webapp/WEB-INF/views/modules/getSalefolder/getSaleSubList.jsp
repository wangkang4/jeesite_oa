<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default"/>
<title>报销管理</title>
	<script type="text/javascript">
			function page(n,s){
				$("#pageNo").val(n);
				$("#pageSize").val(s);
				$("#searchForm").submit();
	        	return false;
	        }
			function downloadGetSale(){
				var startTime1 = $("#startTime1").val();
				var endTime1 = $("#endTime1").val();
				var userId1 = $("#userId1").val();
				location.href = "${ctx}/get/sale/ExExcel?startTime1="+startTime1
						+ "&endTime1="+endTime1
						+ "&userId1="+userId1;
			}



	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			var text = "";
			//实时筛选，不用点击按钮
			setInterval(function(){
				text = $('#input').val();//获取文本框输入
				if($.trim(text) != ""){
					$("#contentTable tr:not('#theader')").hide().filter(":contains('"+text+"')").show();
				}else{
					$('#contentTable tr').show();//当删除文本框的内容时，又重新显示表格所有内容
				}
			},100);
		})
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		 <li><a href="${ctx}/get/sale/">报销列表</a></li>
		 <li><a href="${ctx}/get/sale/listDraft">草稿列表</a></li>
		 <li><a href="${ctx}/get/sale/form">报销申请流程</a></li>
		 <li class="active"><a href="">员工报销列表</a></li>
		 <shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/get/sale/getSaleList2">所属区域员工申请列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="searchForm" modelAttribute="getSale"
		action="${ctx}/get/sale/CWSubList" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>起始时间:</label>
		<input id="startTime1" name="st" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${st }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>结束时间:</label>
		<input id="endTime1" name="et" type="text" readonly="readonly" maxlength="20"
			class="input-medium Wdate "
			value="<fmt:formatDate value="${et }" pattern="yyyy-MM-dd"/>"
			onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>报销人:</label>	
			<sys:treeselect id="user" name="user.id" value="${userId}" labelName="user.name" labelValue="${userName}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
		<label>关键字：</label>
		<input type="text" name="" id="input" value="" placeholder="请输入查询关键字"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" class="btn btn-primary" value="清除" onclick="javascript:window.location.href= '${ctx}/get/sale/CWSubList'">

		<input type="button" class="btn btn-primary" value="导出" onclick="downloadGetSale()">
		&nbsp;&nbsp;&nbsp;&nbsp;


	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>公司</th>
				<th>部门</th>
				<th>标题</th>
				<th>报销总金额</th>
				<th>申请时间</th>
				<th>查看</th>
				<th>附件</th>
				<shiro:hasRole name="seeEmployee">
					<th>附件上传</th>
				</shiro:hasRole>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="getSale">
			<tr>
				<td>${getSale.user.name}</td>
				<td>${getSale.ename}</td>
				<td>${getSale.office.name}</td>
				<td>${getSale.reason }</td>
				<td>
					<fmt:formatNumber type="number" value="${getSale.forMoney}" maxFractionDigits="2"/>元
				</td>
				<%--  <td>${getSale.reasonTitle}</td>  --%>
				<td><fmt:formatDate value="${getSale.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>
                    <a href="${ctx}/get/sale/form?id=${getSale.id}">详情</a>
				</td>
				<td>
					<c:if test="${getSale.address!=null }">
						<a href="${ctx}/get/sale/download?id=${getSale.id}">银行流水单下载</a>
					</c:if>
				</td>
				<shiro:hasRole name="seeEmployee">
				<td>
					<form style="margin-bottom:0px;" action="${ctx}/get/sale/upload" enctype="multipart/form-data" method="post">
						<input type="hidden" value="${getSale.id}" name="id">
						<input type="file" name="file" style="width:180px">
						<input type="submit" value="确认上传">
					</form>
				</td>
				</shiro:hasRole>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>