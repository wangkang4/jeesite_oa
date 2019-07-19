<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>报销管理</title>
<script type="text/javascript"
	src="${ctxStatic}/tb/sale/getSaleView.js?ver=1"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/get/sale/">报销列表</a></li>
		<li class="active"><a
			href="${ctx}/get/sale/form/?procInsId=${getSale.procInsId}&id=${getSale.id}">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<input type="hidden" name="statu" value="${getSale.statu }">
		<input type="hidden" name="user1" value="${getSale.user.id }">
		<input type="hidden" name="user2" value="${fns:getUser().id}">
		<sys:message content="${message}" />
		<fieldset>
			<legend>申请详情</legend>
			<table class="table-form">
				<input type="hidden" value=${getSale.id } name="getSaleId">
				<tr>
					<td class="tit" width="160">标题</td>
					<td colspan="7" class="tit">${getSale.reason }</td>
				</tr>
				<tr>
					<td class="tit">姓名</td>
					<td class="tit">${getSale.user.name}</td>
					<td class="tit">公司</td>
					<td class="tit">${getSale.ename}</td>
					<td class="tit">部门</td>
					<td class="tit">${getSale.office.name}</td>
					<td class="tit">报销总金额</td>
					<td class="tit"><fmt:formatNumber type="number"
							value="${getSale.forMoney}" maxFractionDigits="2" />元</td>
					<%-- <td class="tit">津贴总金</td>
					<td>${getSale.sumAllowance}</td> --%>
				</tr>

				<tr>
					<td class="tit">日期</td>
					<td class="tit">费用类型</td>
					<td class="tit">费用描述</td>
					<td class="tit">可报销金额</td>
					<c:if test="${getSale.statu=='审核通过'||getSale.statu=='同意' }">
						<td class="tit">允许报销金额</td>
					</c:if>
					<td class="tit">项目名称</td>
					<td class="tit" colspan="3">详细信息</td>
				</tr>

				<c:forEach items="${listOther}" var="list">
					<c:choose>
						<c:when test="${list.costDescriptionId !='0501' }">
							<tr>
								<td class="tit"><fmt:formatDate value="${list.detailDate}"
										pattern="yyyy-MM-dd" /></td>
								<td class="tit">${list.costType}</td>
								<td class="tit">${list.costDescription}</td>
								<td class="tit"><fmt:formatNumber type="number"
										value="${list.amountMoney}" maxFractionDigits="2" />元</td>
								<c:if test="${getSale.statu=='审核通过'||getSale.statu=='同意' }">
									<td class="tit"><fmt:formatNumber type="number"
											value="${list.allowMoney}" maxFractionDigits="2" />元</td>
								</c:if>
								<td class="tit">${list.projectName}</td>
								<td class="tit" colspan="4">${list.information}</td>
							</tr>

						</c:when>
						<c:otherwise>
							<tr>
								<td class="tit" rowspan="2"><fmt:formatDate
										value="${list.detailDate}" pattern="yyyy-MM-dd" /></td>
								<td class="tit" rowspan="2">${list.costType}</td>
								<td class="tit">${list.costDescription} 出差天数：${list.day }天
									出差编号：${list.num } 出发地：${list.origin } 目的地：${list.destination }

								</td>
								<td class="tit"><fmt:formatNumber type="number"
										value="${list.amountMoney}" maxFractionDigits="2" />元</td>
								<c:if test="${getSale.statu=='审核通过'||getSale.statu=='同意' }">
									<td class="tit"><fmt:formatNumber type="number"
											value="${list.allowMoney}" maxFractionDigits="2" />元</td>
								</c:if>
								<td class="tit" rowspan="2">${list.projectName}</td>
								<td class="tit" rowspan="2" colspan="3">${list.information}</td>
							</tr>
							<tr>
								<td class="tit">出差津贴：${list.allowance }</td>

								<td class="tit">${list.allowance }元</td>
								<c:if test="${getSale.statu=='审核通过'||getSale.statu=='同意' }">
									<td class="tit">${list.allowanceMoney }元</td>
								</c:if>
							</tr>

						</c:otherwise>
					</c:choose>

				</c:forEach>


				<%--  <tr>
                <td class="tit">报销原因</td>
					<td colspan="6" style="white-space:normal;width:50%;">${getSale.reasonTitle}</td>
                </tr> --%>
				<tr>
					<td class="tit">附件</td>
					<td colspan="7"><c:if test="${not empty getSale.fileAddress }">
							<a href="${ctx}/get/sale/downloadFiel?id=${getSale.id}">附件下载</a>
						</c:if> <c:if test="${empty getSale.fileAddress }">
							无附件
						</c:if></td>

				</tr>
				<c:if test="${not empty getSale.prText }">
					<tr>
						<td class="tit">行政主管审核意见</td>
						<td colspan="7">${getSale.prText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.leaderText}">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="7">${getSale.leaderText}</td>
					</tr>
				</c:if>

				<!-- add -->
				<c:if test="${not empty getSale.leadertwoText}">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="7">${getSale.leadertwoText}</td>
					</tr>
				</c:if>

				<c:if test="${not empty getSale.managerText}">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="7">${getSale.managerText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prthreeText }">
					<tr>
						<td class="tit">出纳审核意见</td>
						<td colspan="7">${getSale.prthreeText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prfourText }">
					<tr>
						<td class="tit">财务主管审核意见</td>
						<td colspan="7">${getSale.prfourText}</td>
					</tr>
				</c:if>
				<!--add  -->
				<c:if test="${not empty getSale.prtwoText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="7">${getSale.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty getSale.prfiveText }">
					<tr>
						<td class="tit">总经理审核意见</td>
						<td colspan="7">${getSale.prfiveText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>

		<act:histoicFlow procInsId="${getSale.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" /> <a class="btn"
				href="${ctx}/get/sale/getSysIndex/?procInsId=${getSale.procInsId}&id=${getSale.id}"
				target="_blank">页面打印</a>
		</div>
	</form:form>
	<script type="text/javascript">
		function printIndex(){
			window.open("${ctx}/get/sale/getSysIndex/?procInsId=${getSale.procInsId}&id=${getSale.id}");
			//window.location.href="${ctx}/get/sale/getSysIndex/?procInsId=${getSale.procInsId}&id=${getSale.id}";
		}
	</script>
</body>
</html>