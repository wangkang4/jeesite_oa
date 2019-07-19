<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>费用统计</title>
<style type="text/css">
#contentTable thead tr th {
	text-align: center;
}

#contentTable tbody tr td {
	text-align: center;
}
</style>
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function doForm() {
		location.href = "${ctx}/oa/costexcel/uploadexcelform";
	}

	function doService() {
		location.href = "${ctx}/oa/costexcel/downexcelcopy";
	}

	function dodown() {
		var type = $("#type").val();
		var clientId = $("#clientId").val();
		var projectId = $("#projectId").val();
		var conment = $("#conment").val();
		var startTime1 = $("#startTime1").val();
		var endTime1 = $("#endTime1").val();
		var userId1 = $("#userId1").val();
		var offieId1 = $("#offieId1").val();
// 		alert(" type:"+type+" clientId:"+clientId+" projectId:"+projectId+" conment:"+conment+" startTime1"+startTime1+" endTime1:"
// 				+endTime1+" userId1:"+userId1+" offieId1:"+offieId1+">>>");
// 		location.href = "${ctx}/oa/costexcel/ExExcel?str=" + type;
		location.href = "${ctx}/oa/costexcel/ExExcel?type=" + type 
				+ "&clientId="+clientId
				+ "&projectId="+projectId
				+ "&conment="+conment
				+ "&startTime1="+startTime1
				+ "&endTime1="+endTime1
				+ "&userId1="+userId1
				+ "&offieId1="+offieId1;

	}
	
	function getProjectList() {
		var clientId = $("#clientId").val();
		$.ajax({
			type : 'post',
			url : "${ctx}/oa/costexcel/getProjectList",
			data : {
				"clientId" : clientId
			},
			dataType : 'json',
			success : function(data) {
				$("#projectId").empty();
				$("#projectId").append("<option value=''>--请选择--</option>");
				for (var i = 0; i < data.length; i++) {
					$("#projectId").append(
							"<option value='"+data[i].id+"'>"
									+ data[i].projectName + "</option>");
				}
			}

		});
	}
	
	function resetForm(){
		location.reload();
	}
</script>

</head>
<body>
	<div style="width: 98%; border: 1px solid #eee; margin: 10px auto;">
		<ul class="nav nav-tabs">
			<li class="active"><a href="${ctx }/oa/costexcel/list">查阅列表</a></li>
			<li><a href="${ctx }/oa/costexcel/costmessagefrom">费用新增</a></li>
		</ul>
		<form:form id="searchForm" modelAttribute="costRecondExcel"
			action="${ctx}/oa/costexcel/list" method="post"
			class="breadcrumb form-search">
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />
			<ul class="ul-form">
				<li>
					<label>类型：</label> 
					<form:select id="type" path="type"
						class="input-xlarge">
<%-- 						<c:if test="${costRecondExcel.type eq null }" ><form:option value="" label="---请选择---" /></c:if> --%>
<%-- 						<c:if test="${costRecondExcel.type eq 1 }"><form:option value="1" label="销售" /></c:if>  --%>
<%-- 						<c:if test="${costRecondExcel.type eq 2 }"><form:option value="2" label="技术" /></c:if>  --%>
<%-- 						<c:if test="${costRecondExcel.type eq 3 }"><form:option value="3" label="行政" /></c:if> --%>
						<form:option value=""  label="--请选择--"/>
						<form:option value="1" label="销售" />
						<form:option value="2" label="技术" />
						<form:option value="3" label="行政" />
					</form:select> 
					<label>客户名称：</label>
					<form:select id="clientId" path="clientId" class="input-xlarge" onclick="getProjectList()">
<%--  						<c:if test="${costRecondExcel.clientName ne '' }">  --%>
<%--  							<option value="${costRecondExcel.clientId }">${costRecondExcel.clientName }</option>  --%>
<%--  						</c:if> --%>
						<form:option value=""  label="--请选择--"/>
						<c:forEach var="client" items="${clientList }">
						                           <!-- 下方的label相当于当前option下拉框选项的具体值 -->
							<form:option value="${client.id }" label="${client.clientName }"/>
						</c:forEach>
					</form:select>
					<label>项目名称：</label>
						<select id="projectId" name="projectId" class="input-xlarge" >
							<c:if test="${costRecondExcel.projectId ne '' }"> 
 								<option value="${costRecondExcel.projectId }">${costRecondExcel.projectName }</option>  
 							</c:if> 
					</select>
						</li>
				<li>
					<label>说明：</label>
						<input id="conment" name="conment" value="${costRecondExcel.conment }" type="text">
					
					<label>起始时间：</label>
						<input id="startTime1" name="startTime1" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${costRecondExcel.startTime }" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
					<label>结束时间：</label>
						<input id="endTime1" name="endTime1" type="text" readonly="readonly"
						maxlength="20" class="input-medium Wdate "
						value="<fmt:formatDate value="${costRecondExcel.endTime }" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></li>
				<li>
					<input id="userId1" name="userId1" type="hidden" value="${costRecondExcel.userId}" />
					<input id="offieId1" name="offieId1" type="hidden" value="${costRecondExcel.offieId}" />
					<label>人员：</label>
						<sys:treeselect id="userId" name="userId" value="${costRecondExcel.userId}" labelName="user.name" labelValue="${costRecondExcel.userName}"
						title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
					<label>部门：</label>
						<sys:treeselect id="offieId" name="offieId" value="${costRecondExcel.offieId}" labelName="office.name" labelValue="${costRecondExcel.offieName}"
						title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
			<input id="btnSubmit" style="margin-left: 50px;"
					class="btn btn-primary" type="submit" value="查询" />
					&nbsp;&nbsp;<input type="button" class="btn btn-primary" value="清除" onclick="resetForm()"></li>
					<br>
				<li class="btns" style="float: right;"><input id="btn"
					class="btn btn-primary" type="button" value="批量上传"
					onclick="doForm()" /> 			
					<input id="btn"  class="btn btn-primary" type="button" value="批量导出" onclick="dodown()" /> 
					<input id="btn" class="btn btn-primary" type="button" value="模板下载"
					onclick="doService()" /></li>

			</ul>
		</form:form>
		<sys:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>客户名称</th>
					<th>项目名称</th>
					<th>项目金额（万元）</th>
					<th>使用时间</th>
					<th>差旅费</th>
					<th>餐费</th>
					<th>文化礼品（自购）</th>
					<th>其他金额</th>
					<th>文化礼品（公司）</th>
					<th>说明</th>
					<th>类型</th>
					<th>申报者</th>
					<th>功能</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="costRecondExcel">
					<tr>
						<td>${costRecondExcel.clientName }</td>
						<td>${costRecondExcel.projectName }</td>
						<td>${costRecondExcel.projectMoney }</td>
						<td><fmt:formatDate value="${costRecondExcel.useTime }"
								pattern="yyyy-MM-dd" /></td>
						<td>${costRecondExcel.travelExpense }</td>
						<td>${costRecondExcel.mealMoney }</td>
						<td>${costRecondExcel.culturalgiftsPerson }</td>
						<td>${costRecondExcel.otherMoney }</td>
						<td>${costRecondExcel.culturalgiftsCompeny }</td>
						<td>${costRecondExcel.conment }</td>
						<td><c:if test="${costRecondExcel.type eq 1 }">销售</c:if> <c:if
								test="${costRecondExcel.type eq 2 }">技术</c:if> <c:if
								test="${costRecondExcel.type eq 3 }">行政</c:if></td>
						<td>${costRecondExcel.createrBy.name }</td>
						<td><c:if test="${costRecondExcel.haveSee eq 0 }">
								<a href="${ctx}/oa/costexcel/costmessagefrom?id=${costRecondExcel.id }">修改</a>
								<a href="${ctx}/oa/costexcel/costmessagedelete?id=${costRecondExcel.id }"
									onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
								<c:if test="${flag eq 1 }">
									<a href="${ctx}/oa/costexcel/haveSee?haveSee=1&id=${costRecondExcel.id }">锁定</a>
								</c:if>
							</c:if>
							<c:if test="${costRecondExcel.haveSee eq 1 }">
								<c:if test="${flag eq 1 }">
									<a href="${ctx}/oa/costexcel/haveSee?haveSee=0&id=${costRecondExcel.id }" >解锁</a>
								</c:if>
								<c:if test="${flag eq 0 }">
									已锁定
								</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page}</div>
	</div>
</body>
</html>