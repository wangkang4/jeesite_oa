<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>项目信息</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="#">客户添加</a></li>
	</ul><br/>
	<div style="text-align: center"><span style="font-size: 24px;color: blue">项目详细信息</span></div>
	<span style="font-size: 14px">基本信息</span></br></br>
	<form id="cutomerForm" action="${ctx}/oaproject/pmProjectMain/saveExamine" method="post" class="form-horizontal" >
		<table class="table-form">
			<tr>
				<td class="tit">项目名称</td>
				<td>
					${pmProjectMain.projectName }
				</td>
				<td class="tit">项目编号</td>
				<td>
					${pmProjectMain.projectId }
				</td>
			</tr>
			<tr>
				<td class="tit">项目类型</td>
				<td>
					${fns:getDictLabel(pmProjectMain.projectType,'project_class','')}
				</td>
				<td class="tit">关系群</td>
				<td>
					${pmCustomerZtree.name }
				</td>			
			</tr>
			<tr>
				<td class="tit">项目行业</td>
				<td>
					${pmCustomerZtree.label }
				</td>
				<td class="tit">项目阶段</td>
				<td>
					${fns:getDictLabel(pmProjectMain.projectStage,'project_stage','')}
				</td>
			</tr>
			<tr>
				<td class="tit">项目金额</td>
				<td>
					${pmProjectMain.projectMoney }
				</td>
				<td class="tit">办事处</td>
				<td>
					<c:if test="${pmProjectMain.projectAddress == '0'}">北京总部</c:if>
					<c:if test="${pmProjectMain.projectAddress == '1'}">山东办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '2'}">安徽办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '3'}">上海办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '4'}">浙江办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '5'}">福建办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '6'}">山西办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '7'}">内蒙古办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '8'}">河北办</c:if>
					<c:if test="${pmProjectMain.projectAddress == '9'}">辽宁办</c:if>
				</td>
			</tr>
			<tr>
				<td class="tit">项目级别</td>
				<td>
					${fns:getDictLabel(projectDetailed.projectLevel,'project_level','')}
				</td>
				<td class="tit">预计合同签订时间</td>
				<td>
					<fmt:formatDate value="${projectDetailed.endTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td class="tit">附件信息</td>
				<td colspan="3">
					<textarea rows="6" cols="" style="width: 1080px" disabled="disabled"></textarea>
				</td>
			</tr>
			<tr>
				<td class="tit">项目概述</td>
				<td colspan="3">
					<textarea rows="6" cols="" style="width: 1080px" disabled="disabled">${pmProjectMain.projectSummary }</textarea>
				</td>
			</tr>
			<tr>
				<td class="tit">项目背景</td>
				<td colspan="3">
					<textarea rows="6" cols="" style="width: 1080px" disabled="disabled">${projectDetailed.projectBackground }</textarea>
				</td>
			</tr>
			<tr>
				<td class="tit">项目范围</td>
				<td colspan="3">
					<textarea rows="6" cols="" style="width: 1080px" disabled="disabled">${projectDetailed.projectRange}</textarea>
				</td>
			</tr>
			<tr>
				<td class="tit">项目目标</td>
				<td colspan="3">
					<textarea rows="6" cols="" style="width: 1080px" disabled="disabled">${projectDetailed.projectTarget }</textarea>
				</td>
			</tr>
			<tr>
				<td class="tit">其他说明</td>
				<td colspan="3">
					<textarea rows="6" cols="" style="width: 1080px" disabled="disabled">${projectDetailed.remarks }</textarea>
				</td>
			</tr>
		</table><br/>
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">团队成员</span>
		</div>
		<table class="table-form" id="resumeTbl" style="table-layout:fixed;">
		<thead>
			<tr>
				<td class="tit">赞助人</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'member' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
				<td class="tit">项目经理</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'manager' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="tit">销售人员</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'administration' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
				<td class="tit">产品部人员</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'product' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="tit">商务人员</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'research' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
				<td class="tit">研发负责人</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'warranty' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td class="tit">其他人员</td>
				<td class="tit" style="word-wrap:break-word;white-space:normal;">
					<c:forEach items="${projectMainTeam}" var="projectMainTeam">
						<c:if test="${projectMainTeam.roleName == 'logistics' }">
							${projectMainTeam.useraName}
						</c:if>
					</c:forEach>
				</td>
			</tr>
		</thead>
		</table><br/>	
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">关联项目</span>
		</div>
		<table class="table-form" id="resumeTbl">
		<thead>
			<tr>
				<td class="tit">项目名称</td>
				<td class="tit">项目编号</td>
			</tr>
		</thead>
		<tbody id="resume">
			<c:forEach items="${projectRelationList}" var="projectRelationList">
				<tr>
					<td class="tit">
						${projectRelationList.projectName}
					</td>
					<td class="tit">
						${projectRelationList.projectId}
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table><br/>	
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">竞争对手</span>
		</div>
		<table class="table-form" id="familyTbl">
		<thead>
			<tr>
				<td class="tit">单位名称</td>
				<td class="tit">竞争对手描述</td>
			</tr>
		</thead>
		<tbody id="family">
			<c:forEach items="${projectOpponentList}" var="projectOpponentList">
				<tr>
					<td class="tit">
						${projectOpponentList.opponetName }
					</td>
					<td class="tit">
						${projectOpponentList.opponetContent }
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table><br/>
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">客户信息</span>
		</div>
		<table class="table-form" id="familyTbl">
		<thead>
			<tr>
				<td class="tit">客户名称</td>
				<td class="tit">企业名称</td>
				<td class="tit">客户职位</td>
				<td class="tit">联系方式</td>
			</tr>
		</thead>
		<tbody id="family">
		<c:forEach items="${projectCustomerList}" var="projectCustomerList">
			<tr>
				<td class="tit">
					${projectCustomerList.customerName }
				</td>
				<td class="tit">
					${projectCustomerList.company }
				</td>
				<td class="tit">
					${projectCustomerList.position }
				</td>
				<td class="tit">
					${projectCustomerList.phone }
				</td>
			</tr>
		</c:forEach>
		</tbody>
		</table><br/>
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">合作单位</span>
		</div>
		<table class="table-form" id="familyTbl">
		<thead>
			<tr>
				<td class="tit">单位名称</td>
				<td class="tit">联系人</td>
				<td class="tit">职位</td>
				<td class="tit">联系方式</td>
				<td class="tit">合作模式</td>
			</tr>
		</thead>
		<tbody id="family">
			<c:forEach items="${projectCooperationList}" var="projectCooperationList">
				<tr>
					<td class="tit">
						${projectCooperationList.cooperation }
					</td>
					<td class="tit">
						${projectCooperationList.contacts }
					</td>
					<td class="tit">
						${projectCooperationList.phone }
					</td>
					<td class="tit">
						${projectCooperationList.position }
					</td>
					<td class="tit">
						${projectCooperationList.cooperationPattern }
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table><br/>
		
		<table class="table-form" id="familyTbl">
		<tr>
			<td class="tit">驳回原因</td>
			<td colspan="3">
				<textarea name="reject" id="reject" rows="6" cols="" style="width: 1080px"></textarea>
			</td>
		</tr>
		</table><br/>
		
		<input name="id" id="id" type="hidden" value="${pmProjectMain.id }"/>
		<input name="examine" id="examine" type="hidden" value=""/>
		<div style="text-align: center;">
			<input class="btn btn-primary" type="button" value = "同意" onclick="tongyi()" />
			<input class="btn btn-primary" type="button" value = "驳回" onclick="bohui()"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	<script type="text/javascript">
		function tongyi(){
			alert(1111111);
			$("#examine").val("同意");
			document.getElementById("cutomerForm").submit();
		}
		function bohui(){
			$("#examine").val("驳回");
			document.getElementById("cutomerForm").submit();
		}
	</script>
	
</body>
</html>