<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>用印申请管理</title>
<script type="text/javascript" src="${ctxStatic}/tb/chapter/addChapter.js"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/chapter/list">用印列表</a></li>
		<li class="active"><a href="#">用印申请流程</a></li>
		<shiro:hasRole name="caiwuzhongnan">
		<li><a href="${ctx}/tb/chapter/employeesList">员工用印列表</a></li>
		</shiro:hasRole>
		<shiro:hasAnyRoles name="xingzheng">
			<li class=""><a href="${ctx}/tb/chapter/list2">所属区域员工用印列表</a></li>
		</shiro:hasAnyRoles>
	</ul>
	<form:form id="inputForm" modelAttribute="chapter" name="form" enctype="multipart/form-data"
		action="${ctx}/tb/chapter/add" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="act.taskId" />
		<form:hidden path="act.taskName" />
		<form:hidden path="act.taskDefKey" />
		<form:hidden path="act.procInsId" />
		<form:hidden path="act.procDefId" />
		<form:hidden id="flag" path="act.flag" />
		<sys:message content="${message}" />
		<input type="hidden" value="${chapter.contractId }" id="contractId">
		<input type="hidden" value="${chapter.address }" name="address">
		<fieldset>
			<legend>用印申请</legend>
			<input type="hidden" id="chapterType1" value="${chapter.chapterType }">
			<input type="hidden" id="fileType1" value="${chapter.fileType }">
			<div>
			<table class="table-form">
				<tr>
					<td class="tit">申请人</td>
					<td>
                		<input id="user.name" name="user.id"
						value="${chapter.user.id }" type="hidden"> 
						<input id="userId" name="userName" type="text" readonly="true"
						value="${chapter.user.name }">
					</td>
					<td class="tit">部门</td>
					<td>
						<input id="office.name" name="office.id" value="${chapter.office.id}" type="hidden">
						<input id="officeId" name="officeName" type="text" value="${chapter.office.name }" readonly="readonly" />
					</td>
				</tr>
				<tr>
					<td class="tit">用印文件名</td>
					<td>
						<input type="text" name="fileForChapter" value="${chapter.fileForChapter }">
						<span class="warn" id="fileForChapterWarn" style="color: red"></span>
					</td>
					<td class="tit">申请日期</td>
					<td><fmt:formatDate value="${chapter.applyDate}" type="both" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr style="" class="hidden">
					<td class="tit">用印时间</td>
					<td>
						<input type="text" name="chapterTime" value="${chapter.chapterTime }">天
						<span class="warn" id="chapterTimeWarn" style="color: red"></span>
					</td>
					<td class="tit">归还日期</td>
					<td>
						<input id="useTime" name="returnDate" type="text"
						readonly="readonly" maxlength="20" class="input-medium Wdate"
						value="<fmt:formatDate value="${chapter.returnDate}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
						<span class="warn" id="returnDateWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit" >用印文件数量</td>
					<td>
						<input type="text" name="numberForFile" value="${chapter.numberForFile }">
						<span class="warn" id="numberForFileWarn" style="color: red"></span>
					</td>
					<td class="tit" >使用地点</td>
					<td>
						<form:select  path="placeOfUser" class="input-medium" onchange="change()">
							<form:option value="" label="请选择"/>
							<form:options items="${fns:getDictList('chapter_place')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
						<span class="warn" id="placeOfUserWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">用印种类</td>
					<td>
						<select name="chapterType" class="input-medium" multiple="multiple">
							<option value="">请选择</option>
							<c:forEach items="${chapterList}" var="chapterType">
								<c:choose>
									<c:when test="${chapter.chapterType==chapterType.id }">
										<option value="${chapterType.id }" selected>${chapterType.type }</option>
									</c:when>
									<c:otherwise>
										<option value="${chapterType.id }">${chapterType.type }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<span class="warn" id="chapterTypeWarn" style="color: red"></span>
					</td>
					<td class="tit" >用印文件类型</td>
					<td>
						<select name="fileType" class="input-medium">
							<option value="">请选择</option>
							<c:forEach items="${fileList}" var="file">
								<c:choose>
									<c:when test="${chapter.fileType==file.id }">
										<option value="${file.id }" selected>${file.type }</option>
									</c:when>
									<c:otherwise>
										<option value="${file.id }">${file.type }</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
						<span class="warn" id="fileTypeWarn" style="color: red"></span>
					</td>
				</tr>
				<tr style="" class="hidden">
					<td class="tit" >外借原因</td>
					<td colspan="3">
						<form:textarea path="reasonForBorrow" 
							rows="5" maxlength="2000" cssStyle="width:500px;height:50px" />
						<span class="warn" id="reasonForBorrowWarn" style="color: red"></span>
					</td>
				</tr>
				<tr>
					<td class="tit">文件上传</td>
					<td>
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width:180px">
							<input type="button" value="确认上传" onclick = "upload()">
						 	<progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
						</form>
					</td>
					<td class="tit">对应合同</td>
					<td>
						<select name="contractId" class="input-medium">
							<option value="">请选择</option>
						</select>
					</td>
				</tr>
				</table>
				
			</div>
			</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="提交申请" onclick="return save()" />&nbsp;
				<c:if test="${not empty chapter.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="submit"
					value="销毁申请" onclick="return stop()" />&nbsp;
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
		<c:if test="${not empty chapter.id}">
			<act:histoicFlow procInsId="${chapter.act.procInsId}" />
		</c:if>
	</form:form>
</body>
</html>