<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>预批管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/chapter/chapterAudit.js"></script>
<script type="text/javascript">
$(document).ready(
		function() {
			check();
		});
function check(){
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	if(url.indexOf("form?act")>=0){
		if(statu=="审核通过"){
			
		}else{
		$("#btnCancel").before('<input class="btn" type="button" value="回退" onclick="rollback()">');
		}
	}
}
function rollback(){
	var msg="你确定要回退到自己节点吗";
	if(confirm(msg)==true){
		var url = window.location.href;
		var ele = url.split("&");
		var task = ele[0].split("=");
		var taskId = task[1];
		url = url.substr(0,url.indexOf("tb/chapter"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/chapter/list">用印列表</a></li>
		<li class="active"><a
			href="">申请详情</a></li>
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${chapter.statu }">
		<fieldset>
			<legend>申请详情</legend>
			<input type="hidden" id="chapterType1" value="${chapter.chapterType }">
			<input type="hidden" id="fileType1" value="${chapter.fileType }">
			<table class="table-form">
				<tr>
					<td class="tit">标题</td>
					<td colspan="3">${chapter.reason }</td>
				</tr>
				<tr>
					<td class="tit">申请人</td>
					<td>${chapter.user.name }</td>
					<td class="tit">部门</td>
					<td>${chapter.office.name }</td>
				</tr>
				<tr>
					<td class="tit">用印文件名</td>
					<td>${chapter.fileForChapter }
						
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${chapter.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit">用印时间</td>
					<td>
						<c:if test="${chapter.chapterTime !='' }">
							${chapter.chapterTime }天
						</c:if>
					</td>
					<td class="tit">归还日期</td>
					<td><fmt:formatDate value="${chapter.returnDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td class="tit" >用印文件数量</td>
					<td>${chapter.numberForFile }个</td>
					<td class="tit" >使用地点</td>
					<td>${fns:getDictLabel(chapter.placeOfUser, 'chapter_place', '')}</td>
				</tr>
				<tr>
					<td class="tit">用印种类</td>
					<td>
						<c:forEach items="${chapterList}" var="chapterType">
								<c:if test="${fn:contains(chapter.chapterType,chapterType.id)}">
									${chapterType.type }、   
								</c:if>
						</c:forEach>
					</td>
					<td class="tit" >用印文件类型</td>
					<td>
						<c:forEach items="${fileList}" var="file">
								<c:if test="${chapter.fileType==file.id }">
									${file.type }
								</c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="tit" >外借原因</td>
					<td colspan="3">
						${chapter.reasonForBorrow }
					</td>
				</tr>
				<tr>
					<td class="tit">文件下载</td>
					<td>
						<c:if test="${not empty chapter.address }">
							<a href="${ctx}/tb/chapter/download?id=${chapter.id}">用印文件下载</a>
						</c:if>
					</td>
					<td class="tit">对应合同</td>
					<td>
						<c:if test="${not empty chapter.contractId }">
							<a href="${ctx}/tb/contract/form?id=${chapter.contractId }">对应合同详情</a>
						</c:if>
					</td>
				</tr>
				<c:if test="${not empty chapter.proneText }">
					<tr>
						<td class="tit">部门经理审核意见</td>
						<td colspan="3">${chapter.proneText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty chapter.prtwoText }">
					<tr>
						<td class="tit">研发总监审核意见</td>
						<td colspan="3">${chapter.prtwoText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty chapter.prthreeText }">
					<tr>
						<td class="tit">主管审核意见</td>
						<td colspan="3">${chapter.prthreeText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty chapter.prsixText }">
					<tr>
						<td class="tit">商务主管审核意见</td>
						<td colspan="3">${chapter.prsixText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty chapter.prfourText }">
					<tr>
						<td class="tit">财务总监审核意见</td>
						<td colspan="3">${chapter.prfourText}</td>
					</tr>
				</c:if>
				<c:if test="${not empty chapter.prfiveText }">
					<tr>
						<td class="tit">印章管理人审核意见</td>
						<td colspan="3">${chapter.prfiveText}</td>
					</tr>
				</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${chapter.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>