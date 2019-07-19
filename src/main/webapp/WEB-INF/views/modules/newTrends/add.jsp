<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
<meta name="decorator" content="default" />
<title>发布动态</title>
<script type="text/javascript" src="${ctxStatic}/newTrends/add.js?ver=1"></script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/newTrends/list2">我的发布</a></li>
		<li class="active"><a href="#">发布动态</a></li>		
		
	</ul>
	<form:form id="inputForm" modelAttribute="newTrends" name="form" enctype="multipart/form-data"
		action="${ctx}/newTrends/toAdd" method="post" class="form-horizontal" >
		<form:hidden path="id" />
		
		<fieldset>
			<legend>发布动态</legend>
			<table id="contentTable" class="table-form">
				<div class="control-group">
					<label class="control-label">标题：</label>
					<div class="controls">
						<form:input path="title" htmlEscape="false" maxlength="200"
							class="input-xlarge required" />
						<span class="warn" id="titleWarn" style="color: red"></span>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">正文:</label>
					<div class="controls">
						<form:textarea id="text" htmlEscape="true" path="text"
							rows="4" maxlength="200" class="input-xxlarge" />
						<sys:ckeditor replace="text"/>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">附件:</label>
					<div class="controls">
						<form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
							<input type="file" id="file1" name="file" style="width:180px">
						<input type="button" value="确认上传" onclick = "upload()">
						 <progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
						</form>
						<input name="address" type="hidden">
					</div>
				</div>
			</table>
		</fieldset>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit"
				value="发布" onclick="return save()" />&nbsp;
			<c:if test="${not empty newTrends.id}">
				<input id="btnSubmit2" class="btn btn-inverse" type="button"
					value="销毁发布" onclick="del()" />&nbsp;
			</c:if>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
		</div>
		<script type="text/javascript">
			function del(){
				window.location.href="${ctx}/newTrends/del?id=${newTrends.id}"
			}
			
			var newString = $("#text").text();
			document.getElementById("text").innerHTML = newString;
				
		</script>
		</script>
	</form:form>
</body>
</html>