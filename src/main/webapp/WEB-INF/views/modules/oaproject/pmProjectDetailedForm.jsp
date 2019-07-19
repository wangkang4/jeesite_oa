<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>单表管理</title>
	<meta name="decorator" content="default"/>
	
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
	<script type="text/javascript">
		var listDescription11 = {};
		$(document).ready(function() {
			$.ajax({
				type : 'post',
				async:false,
				url : '${ctx}/oaproject/pmProjectMain/getProjectMain',
				dataType : 'json',
				success : function(data) {
					listDescription11 = data;
				},
				error : function(data) {
					alert("错误信息");
				}
			});
		});
	
		function getProjiectName(btn){
			var costTypeId = $(btn).val();
			var costType=$(btn).find("option:selected").val();
			$.each(listDescription11[1], function(i, item) {
				if(item.id == costType){
					$(btn).parent().next().children("input").val(item.projectId);
				}
			});
		}
		$(function() {
			$("#btn").click(
				function(){
					$("#tbody").append(
						"<tr>"
							+"<td>"
								+"<select name='otherProjiectId' class='myselect' onchange='getProjiectName(this)'>"
									+"<option value=''>请选择</option>"
									+"<c:forEach var='projectMainList' items='${projectMainList }'>"
										+"<option value='${projectMainList.id }'>${projectMainList.projectName}</option>"
									+"</c:forEach>"
								+"</select>"
							+"</td>"
							+"<td>"
								+"<input type='text' readonly='readonly'/>"
							+"</td>"
							+ "<td><input value='删除' type='button' onclick='del(this)' style='width:50px;' class='btn'/></td>"
						+"</tr>");
				}
			);
		});
		$(function() {
			$("#opponetbtn").click(
				function(){
					$("#opponetbody").append(
						"<tr>"
							+"<td>"
								+"<input name='opponetName' id='opponetName' type='text' maxlength='20' class='input-xlarge ' />"
							+"</td>"
							+"<td>"
								+"<input name='opponetContent' id='opponetContent' type='text' maxlength='20' class='input-xlarge' />"
							+"</td>"
							+ "<td><input value='删除' type='button' onclick='opponetdel(this)' style='width:50px;' class='btn'/></td>"
						+"</tr>");
				}
			);
		});
		
		
	function del(btn) {
		$(btn).parent().parent().remove();
	}
	function opponetdel(btn) {
		$(btn).parent().parent().remove();
	}
	
	</script>
	<script type="text/javascript">
	var listDescription = {};
	$(document).ready(function() {
		$.ajax({
			type : 'post',
			async:false,
			url : '${ctx}/oaproject/pmProjectDetailed/getCustomer',
			dataType : 'json',
			success : function(data) {
				listDescription = data;
			},
			error : function(data) {
				alert("错误信息");
			}
		});
	});

	function getCustomerName(btn){
		var costTypeId = $(btn).val();
		var costType=$(btn).find("option:selected").val();
		$.each(listDescription[1], function(i, item) {
			if(item.id == costType){
				$(btn).parent().next().children("input").val(item.company);
				$(btn).parent().next().next().children("input").val(item.position);
				$(btn).parent().next().next().next().children("input").val(item.phone);
			}
		});
	}
	$(function() {
		$("#cooperationbtn").click(
			function(){
				$("#cooperationbody").append(
					"<tr>"
						+"<td>"
							+"<input name='cooperation' id='cooperation' type='text'  />"
						+"</td>"
						+"<td>"
							+"<input name='contacts' id='contacts' type='text'   />"
						+"</td>"
						+"<td>"
							+"<input name='iphone' id='iphone' type='text'   />"
						+"</td>"
						+"<td>"
							+"<input name='position' id='position' type='text'   />"
						+"</td>"
						+"<td>"
							+"<input name='cooperationPattern' id='cooperationPattern' type='text'   />"
						+"</td>"
						+ "<td><input value='删除' type='button' onclick='cooperationdel(this)' style='width:50px;' class='btn'/></td>"
					+"</tr>");
			}
		);
	});
	
	$(function() {
		$("#customerbtn").click(
			function(){
				$("#customerbody").append(
					"<tr>"
						+"<td>"
							+"<select name='customerId' class='myselect' onchange='getCustomerName(this)'>"
								+"<option value=''>请选择</option>"
								+"<c:forEach var='customerInfoList' items='${customerInfoList }'>"
									+"<option value='${customerInfoList.id }'>${customerInfoList.customerName}</option>"
								+"</c:forEach>"
							+"</select>"
						+"</td>"
						+"<td>"
							+"<input type='text' maxlength='20' class='input-xlarge ' readonly='readonly'/>"
						+"</td>"
						+"<td>"
							+"<input type='text' maxlength='20' class='input-xlarge ' readonly='readonly'/>"
						+"</td>"
						+"<td>"
							+"<input type='text' maxlength='20' class='input-xlarge ' readonly='readonly'/>"
						+"</td>"
						+ "<td><input value='删除' type='button' onclick='customerdel(this)' style='width:50px;' class='btn'/></td>"
					+"</tr>");
			}
		);
	});
	
	function cooperationdel(btn) {
		$(btn).parent().parent().remove();
	}
	function customerdel(btn) {
		$(btn).parent().parent().remove();
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
	<li><a href="${ctx}/oaproject/pmProjectMain/">项目列表</a></li>
		<li class="active"><a href="${ctx}/oaproject/pmProjectMain/form">项目添加</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="pmProjectDetailed" action="${ctx}/oaproject/pmProjectDetailed/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<table class="table-form">
			<tr>
				<td><label class="control-label">项目名称：</label></td>
				<td>
					${projectMain.projectName }
	 				<input type="hidden" id="projectId" name="projectId" value = "${projectMain.id }" />
 				</td>
 				<td><label class="control-label">项目编号：</label></td>
 				<td>${projectMain.projectId }</td>
			</tr>
			<tr>
				<td><label class="control-label">项目类型：</label></td>
				<td>${fns:getDictLabel(pmProjectMain.projectType,'project_class','')}</td>
				<td><label class="control-label">项目行业：</label></td>
				<td>${projectMain.projectIndustry }</td>
			</tr>
			<tr>
				<td><label class="control-label">项目级别：</label></td>
				<td><form:select path="projectLevel" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('project_level')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select></td>
				<td><label class="control-label">预计合同签订时间：</label></td>
				<td><input name="endTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${pmProjectDetailed.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
			</tr>
		</table>
		
		<legend>关联项目</legend>
		<input type="button" id="btn" value="添加" class="btn btn-primary">
		<table id="contentTable" class="table-form">
			<tr>
				<td class="tit">项目名称</td>
				<td class="tit">项目编号</td>
				<td>操作</td>
			</tr>
			<tbody id="tbody">
				<c:choose>
					<c:when test="${empty projectRelationList }">
						<tr>
							<td>
								<select name="otherProjiectId" class="myselect" onchange="getProjiectName(this)" style="width: 220px;">
									<option value="">请选择</option>
									<c:forEach var="projectMainList" items="${projectMainList }">
										<option value="${projectMainList.id }">${projectMainList.projectName}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input type="text" readonly="readonly"/>
							</td>
							<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${projectRelationList}" var="projectRelationList" varStatus="status">
							<tr>
								<td>
									<select name="otherProjiectId" class="myselect" onchange="getProjiectName(this)" style="width: 220px;">
										<option value="">请选择</option>
										<c:forEach var="projectMainList" items="${projectMainList }">
											<c:if test="${projectRelationList.otherProjectId == projectMainList.id}">
												<option selected="selected" value="${projectMainList.id }">${projectMainList.projectName}</option>
											</c:if>
											<c:if test="${projectRelationList.otherProjectId != projectMainList.id}">
												<option value="${projectMainList.id }">${projectMainList.projectName}</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="text" value="${projectRelationList.projectId }" readonly="readonly"/>
								</td>
								<c:if test="${status.index != 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' onclick='del(this)' /></td>
								</c:if>
								<c:if test="${status.index == 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>				
				</c:choose>
			</tbody>
		</table>
		
		<legend>竞争对手</legend>
		<input type="button" id="opponetbtn" value="添加" class="btn btn-primary">
		<table id="contentTable" class="table-form">
			<tr>
				<td class="tit">单位名称</td>
				<td class="tit">竞争对手描述</td>
				<td>操作</td>
			</tr>
			<tbody id="opponetbody">
				<c:choose>
					<c:when test="${empty projectOpponentList }">
						<tr>
							<td>
								<input name="opponetName" id="opponetName" type="text" maxlength="20" class="input-xlarge " />
							</td>
							<td>
								<input name="opponetContent" id="opponetContent" type="text" maxlength="20" class="input-xlarge " />
								<!-- <input type="file" name="opponetContent"> -->
							</td>
							<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${projectOpponentList}" var="projectOpponentList" varStatus="status">
							<tr>
								<td>
									<input name="opponetName" id="opponetName" type="text" maxlength="20" class="input-xlarge" value="${projectOpponentList.opponetName}" />
								</td>
								<td>
									<input name="opponetContent" id="opponetContent" type="text" maxlength="20" class="input-xlarge" value="${projectOpponentList.opponetContent}" />
								</td>
								<c:if test="${status.index == 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
								</c:if>
								<c:if test="${status.index != 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' onclick='opponetdel(this)' /></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<legend>客户信息</legend>
		<input type="button" id="customerbtn" value="添加" class="btn btn-primary">
		<table id="contentTable" class="table-form">
			<tr>
				<td class="tit">客户名称</td>
				<td class="tit">企业名称</td>
				<td class="tit">客户职位</td>
				<td class="tit">联系方式</td>
				<td>操作</td>
			</tr>
			<tbody id="customerbody">
				<c:choose>
					<c:when test="${empty projectCustomerList }">
						<tr>
							<td>
								<select name="customerId" class="myselect" onchange="getCustomerName(this)" style="width: 220px;">
									<option value="">请选择</option>
									<c:forEach var="customerInfoList" items="${customerInfoList}">
										<option value="${customerInfoList.id}">${customerInfoList.customerName}</option>
									</c:forEach>
								</select>
							</td>
							<td>
								<input type="text" maxlength="20" class="input-xlarge " readonly="readonly" />
							</td>
							<td>
								<input type="text" maxlength="20" class="input-xlarge " readonly="readonly" />
							</td>
							<td>
								<input type="text" maxlength="20" class="input-xlarge " readonly="readonly" />
							</td>
							<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${projectCustomerList}" var="projectCustomerList" varStatus="status"> 
							<tr>
								<td>
									<select name="customerId" class="myselect" onchange="getCustomerName(this)">
										<option value="">请选择</option>
										<c:forEach var="customerInfoList" items="${customerInfoList}">
											<c:if test="${projectCustomerList.id ==  customerInfoList.id}">
												<option selected="selected" value="${customerInfoList.id}">${customerInfoList.customerName}</option>
											</c:if>
											<c:if test="${projectCustomerList.id !=  customerInfoList.id}">
												<option value="${customerInfoList.id}">${customerInfoList.customerName}</option>
											</c:if>
										</c:forEach>
									</select>
								</td>
								<td>
									<input type="text" maxlength="20" class="input-xlarge " value="${projectCustomerList.company }" readonly="readonly" />
								</td>
								<td>
									<input type="text" maxlength="20" class="input-xlarge " value="${projectCustomerList.position }" readonly="readonly" />
								</td>
								<td>
									<input type="text" maxlength="20" class="input-xlarge " value="${projectCustomerList.phone }" readonly="readonly" />
								</td>
								<c:if test="${status.index == 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
								</c:if>
								<c:if test="${status.index != 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' onclick='cooperationdel(this)' /></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		
		<legend>合作单位</legend>
		<input type="button" id="cooperationbtn" value="添加" class="btn btn-primary">
		<table id="contentTable" class="table-form">
			<tr>
				<td class="tit">单位名称</td>
				<td class="tit">联系人</td>
				<td class="tit">职位</td>
				<td class="tit">联系方式</td>
				<td class="tit">合作模式</td>
				<td>操作</td>
			</tr>
			<tbody id="cooperationbody">
				<c:choose>
					<c:when test="${empty projectCooperationList }">
						<tr>
							<td>
								<input name="cooperation" id="cooperation" type="text"   />
							</td>
							<td>
								<input name="contacts" id="contacts" type="text"   />
							</td>
							<td>
								<input name="iphone" id="iphone" type="text"  />
							</td>
							<td>
								<input name="position" id="position" type="text"   />
							</td>
							<td>
								<input name="cooperationPattern" id="cooperationPattern" type="text"  />
							</td>
							<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${projectCooperationList }" var="projectCooperationList" varStatus="status">
							<tr>
								<td>
									<input name="cooperation" id="cooperation" type="text"  value="${projectCooperationList.cooperation }" />
								</td>
								<td>
									<input name="contacts" id="contacts" type="text"  value="${projectCooperationList.contacts }"/>
								</td>
								<td>
									<input name="iphone" id="iphone" type="text"  value="${projectCooperationList.phone }"/>
								</td>
								<td>
									<input name="position" id="position" type="text"  value="${projectCooperationList.position }" />
								</td>
								<td>
									<input name="cooperationPattern" id="cooperationPattern" type="text" value="${projectCooperationList.cooperationPattern }" />
								</td>
								<c:if test="${status.index == 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' disabled="disabled" /></td>
								</c:if>
								<c:if test="${status.index != 0 }">
									<td><input value='删除' type='button' style='width:50px;' class='btn' onclick='cooperationdel(this)' /></td>
								</c:if>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		<legend>项目信息描述</legend>
		<div class="control-group">
			<label class="control-label">项目背景：</label>
			<div class="controls">
				<form:textarea path="projectBackground" htmlEscape="false" rows="4" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目范围：</label>
			<div class="controls">
				<form:textarea path="projectRange" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目目标：</label>
			<div class="controls">
				<form:textarea path="projectTarget" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">假设与约束：</label> -->
<!-- 			<div class="controls"> -->
<%-- 				<form:textarea path="projectConstraint" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/> --%>
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label">其他说明：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>