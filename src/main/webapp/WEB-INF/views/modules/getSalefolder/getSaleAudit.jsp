<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%> 
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>报销管理</title>
<script type="text/javascript">

/* 修改按钮，将修改报销金额和修改费用描述合并成一个按钮（注：update()的else弹框失效）*/
/****************修改编号：1-1-0**************************/
function updateMoney(){
	$("select[name='costDescription'] option:selected").each(function(){
		if($(this).val()!=""){
			update();
		}
	})
	updateAllowMoney();
}
/*******************************************/

function updateCostType(btn){
	$("select[name='costType']").val($(btn).val());
	$(".select2-chosen :even").text($(btn).find("option:selected").text());
	$("select[name='costDescription']").each(function(){
		
		$(this).empty();
		$(".select2-chosen :odd").text("请选择")
		$(this).append('<option value="">请选择</option>');
	});
	getCost($(btn).val());
}
function getCost(costTypeId){
	$.ajax({
		url:"${ctx}/get/sale/getCost",
		data:{"costTypeId":costTypeId},
		type:"post",
		dataType:"json",
		success:function(data){
			costDescription = data.costDescription;
			for(var i in costDescription){
				$("select[name='costDescription']").append('<option value="'+costDescription[i].costDescriptionId+'">'+costDescription[i].costDescription+'</option>');
			}
		}
	});
}
function updateAllowMoney(){
	var bool=true;
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	$("input[name='allowMoney']").each(function(){
		if (!reg.test(this.value)) {
			bool = false;
		}
	})
	if(bool){
		$("input[name='detailId']").each(function(i,e){
			var detailId = $(this).val();
			$("input[name='allowMoney']").each(function(j,e){
				var allowMoney = $(this).val();
				var allowanceMoney=$(this).parent().parent().next().find("input[name='allowanceMoney']").val();
				if(i==j){
					$.ajax({
						url:"${ctx}/get/sale/updateAllowMoney",
						data:{"detailId":detailId,"allowMoney":allowMoney,"allowanceMoney":allowanceMoney},
						type:"post",
						dataType:"json",
						success:function(data){
							if(data.success=="ok"){
								$("input[name='allowMoney']").eq(j).val(allowMoney);
								$(this).parent().parent().next().find("input[name='allowanceMoney']").val(allowanceMoney);
							}
						}
					})
				}
			})
		})
		alertx("修改成功");
		/****************修改编号：1-1-0**************************/
		$('#flag').val('yes')
		return true;
		/*******************************************/
	}else{
		alertx("格式填写错误");
	}
}
function update(){
	var bool=true;
	$("select[name='costDescription'] option:selected").each(function(){
		if($(this).val()==""){
			bool=false;
		}
	})
	if(bool){
		$("select[name='costDescription'] option:selected").each(function(i,e){
			var costDescriptionId = $(this).val();
			var costDescription=$(this).text();
			var costTypeId = $(this).parent().parent().prev().children().last().find("option:selected").val();
			var costType = $(this).parent().parent().prev().children().last().find("option:selected").text();
			$(".costT").text(costType);
			$("input[name='detailId']").each(function(j,e){
				if(i==j){
				var detailId = $(this).val();
					$.ajax({
						url:"${ctx}/get/sale/updateCost",
						data:{"detailId":detailId,"costTypeId":costTypeId,"costType":costType,"costDescriptionId":costDescriptionId,"costDescription":costDescription},
						type:"post",
						dataType:"json",
						success:function(data){
							if(data.success=="ok"){
								$(".costD").eq(j).text(costDescription);
							}
						}
					})
				}
			})
		})
	}else{
		alertx("请填写完全");
	}
}			
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/get/sale/">报销单列表</a></li>
		<li class="active"><a href="#"> <shiro:hasPermission
					name="oa:testAudit:edit">任务处理</shiro:hasPermission> 
					<%-- 	<shiro:lacksPermission name="oa:testAudit:edit">查看</shiro:lacksPermission> --%>
		</a></li>
	</ul>
	<form:form id="inputForm" modelAttribute="getSale"
		action="${ctx}/get/sale/saveAudit" method="post"
		class="form-horizontal">
		<input type="hidden" name="taskids" value="${taskId}">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />                 
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<fieldset>
			<legend>员工报销单申请表</legend>
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="7" class="tit">${getSale.reason }</td>
				</tr>
				<tr>
					<td class="tit">姓名</td>
					<td class="tit">${getSale.user.name}</td>
					<td class="tit">公司</td>
					<td class="tit" >${getSale.ename}</td>
					<td class="tit">部门</td>
					<td class="tit" >${getSale.office.name}</td>
					<td class="tit">报销总金额</td>
					<td class="tit"><fmt:formatNumber type="number" value="${getSale.forMoney}" maxFractionDigits="2" pattern="#.00"/>元</td>
				</tr>
				<tr>
					<td class="tit">日期</td>
					<td class="tit">费用类型</td>
					<td class="tit">费用描述</td>
					<td class="tit">可报销金额</td>
					<shiro:hasRole name="caiwuzhongnan">
					<td class="tit">允许报销金额</td>
					</shiro:hasRole>
					<td class="tit">项目名称</td>
					<td class="tit" colspan="3">详细信息</td>
				</tr>
				<c:forEach items="${listOther}" var="lista">
					<c:choose>
						<c:when test="${lista.costDescriptionId !='0501' }">
							<tr>
						<td class="tit"><fmt:formatDate value="${lista.detailDate}"
								pattern="yyyy-MM-dd" /></td>
						<td class="tit"><span class="costT">${lista.costType}</span>
							<input type="hidden" name="costTypeId" value="${lista.costTypeId }">
							<shiro:hasRole name="caiwuzhongnan">
							<select name="costType" class="input-medium" onchange="updateCostType(this)">
								<option value="">请选择</option>
								<c:forEach items="${list }" var="type">
									<option value="${type.costTypeId }">${type.costType }</option>
								</c:forEach>
							</select>
							</shiro:hasRole>
						</td>
						
						<td class="tit"><span class="costD">${lista.costDescription}</span><input type="hidden" name="detailId" value="${lista.detailId }">
							<shiro:hasRole name="caiwuzhongnan">
							<select name="costDescription" class="input-medium">
								<option value="">请选择</option>
							</select>
							</shiro:hasRole>
						</td>
						<td class="tit"><fmt:formatNumber type="number" value="${lista.amountMoney}" maxFractionDigits="2" pattern="#.00"/>元</td>
						<shiro:hasRole name="caiwuzhongnan">
						<td><input name="allowMoney" type="text" value="${lista.allowMoney }"/>元</td>
						</shiro:hasRole>
						<td class="tit">${lista.projectName}</td>
						<td class="tit" colspan="3">${lista.information}</td>
					</tr>
						</c:when>
						<c:otherwise>
							<tr>
						<td class="tit" rowspan="2"><fmt:formatDate value="${lista.detailDate}"
								pattern="yyyy-MM-dd" /></td>
						<td class="tit" rowspan="2"><span class="costT">${lista.costType}</span>
							<input type="hidden" name="costTypeId" value="${lista.costTypeId }">
							<shiro:hasRole name="caiwuzhongnan">
							<select name="costType" class="input-medium" onchange="updateCostType(this)">
								<option value="">请选择</option>
								<c:forEach items="${list }" var="type">
									<option value="${type.costTypeId }">${type.costType }</option>
								</c:forEach>
							</select>
							</shiro:hasRole>
						</td>
						
						<td class="tit" ><span class="costD">${lista.costDescription}</span><input type="hidden" name="detailId" value="${lista.detailId }">
							<shiro:hasRole name="caiwuzhongnan">
							<select name="costDescription" class="input-medium">
								<option value="">请选择</option>
							</select>
							</shiro:hasRole>
								出差天数：${lista.day }天
								出差编号：${lista.num }
								出发地：${lista.origin }
								目的地：${lista.destination }
							
						</td>
						<td class="tit"><fmt:formatNumber type="number" value="${lista.amountMoney}" maxFractionDigits="2" pattern="#.00"/>元</td>
						<shiro:hasRole name="caiwuzhongnan">
						<td ><input name="allowMoney" type="text" value="${lista.allowMoney }"/>元</td>
						</shiro:hasRole>
						<td class="tit" rowspan="2">${lista.projectName}</td>
						<td class="tit" rowspan="3">${lista.information}</td>
					</tr>
					<tr>
						<td class="tit">出差津贴：${lista.allowance }</td>
						<td class="tit" ><fmt:formatNumber type="number" value="${lista.allowance }" maxFractionDigits="2" pattern="#.00"/>元</td>
						<shiro:hasRole name="caiwuzhongnan">
						<td ><input name="allowanceMoney" type="text" value="${lista.allowanceMoney }"/>元</td>
						</shiro:hasRole>
					</tr>
						</c:otherwise>
					</c:choose>
					
					
				</c:forEach>
				<%-- <tr>
					<td class="tit">报销原因</td>
					<td colspan="5">${getSale.reasonTitle }</td>
				</tr> --%>
				<%-- <c:if test="${not empty str}">  str不为空</c:if> --%>
				<tr>
					<td class="tit">附件</td>
					<td colspan="7">
						<c:if test="${not empty getSale.fileAddress }">
							<a href="${ctx}/get/sale/downloadFiel?id=${getSale.id}">附件下载</a>
						</c:if>
						<c:if test="${empty getSale.fileAddress }">
							无附件
						</c:if>
					</td>
					
				</tr>
				<c:if test="${not empty getSale.prText }">
					<tr>
						<td class="tit">行政主管审核意见</td>
						<td colspan="5">${getSale.prText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.leaderText}">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="5">${getSale.leaderText}</td>
					</tr>
				</c:if>

				<!-- add -->
				<c:if test="${not empty getSale.leadertwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="5">${getSale.leadertwoText}</td>
					</tr>
				</c:if>

				<c:if test="${not empty getSale.managerText}">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="5">${getSale.managerText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prthreeText }">
					<tr>
						<td class="tit">出纳审核意见</td>
						<td colspan="5">${getSale.prthreeText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prfourText }">
					<tr>
						<td class="tit">财务主管审核意见</td>
						<td colspan="5">${getSale.prfourText}</td>
					</tr>
				</c:if>
				<!--add  -->
				<c:if test="${not empty getSale.prtwoText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="5">${getSale.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prfiveText }">
					<tr>
						<td class="tit">总经理审核意见</td>
						<td colspan="5">${getSale.prfiveText}</td>
					</tr>
				</c:if>
				
					<tr>
						<td class="tit">您的意见</td>
						<td colspan="7"><form:textarea path="act.comment"
								 rows="5" maxlength="500" cssStyle="width:500px" />
						</td>
					</tr>
				
			</table>
		</fieldset>
		<div class="form-actions">
			
			
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="同 意" onclick="$('#flag').val('yes')" />&nbsp; 
				<input id="btnSubmit" class="btn btn-inverse" type="submit" 
					value="驳 回" onclick="$('#flag').val('no')" />&nbsp;
				<shiro:hasRole name="caiwuzhongnan">
				<!-- <input id="btnSubmit" class="btn btn-inverse" type="button" 
					value="修改费用描述" onclick="update()" />&nbsp;
				<input id="btnSubmit" class="btn btn-inverse" type="button" 
					value="修改报销金额" onclick="updateAllowMoney()" />&nbsp; -->
				<input id="btnSubmit" class="btn btn-inverse" type="submit" 
					value="修改并同意" onclick="return updateMoney()" />&nbsp;
				</shiro:hasRole> 
				<c:if test="${fns:getUser().loginName == '郭晓敏'}">
					<input id="btnSubmit" class="btn btn-inverse" type="submit" value="转至财务总监"
						onclick="$('#flag').val('doubt')" />&nbsp;
				</c:if>
			
				<input id="btnCancel"
				class="btn" type="button" value="返 回" onclick="javascript:window.location.href='${ctx}/act/task/todo'" />
		</div>
		<act:histoicFlow procInsId="${getSale.act.procInsId}" />
	</form:form>
</body>
</html>