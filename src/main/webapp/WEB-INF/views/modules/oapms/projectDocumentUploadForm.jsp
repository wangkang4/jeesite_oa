<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />

<title>文件上传</title>
<style>
	.aaaa {
				height:500px;
			}
</style>
<script type="text/javascript">

	function doSubmit() {
		var id = $("#projectId").val();
		var files = $("#fileids").text();
		console.log(id+"++++"+files);
		location.href = "${ctx}/pms/comment/uploadfiles?id=" + id
											+ "&files=" + files;
	}
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/comment/projectDocument?id=${id}">文档</a></li>
		<li class="active"><a href="#">文档上传</a></li>
	</ul>
	<form:form id="insertForm" enctype="multipart/form-data"
			action="${ctx}/oa/costexcel/uploadExcel" method="post" class="form-horizontal">
			<input type="hidden" id="projectId" value="${id}">
			<div class="control-group">
				<label class="control-label">上传人员:</label>
				<div class="controls">
					${fns:getUser().name}
				</div>
			</div>
			<div class="aaaa">
				<label class="control-label">上传文件:</label>
				<div class="aaaa">
					<textarea style="display:none" name="fileid" id="fileids"></textarea>
					<iframe src="${ctx }/pms/comment/fileuploads?id=${id}"  width="50%" height="100%" frameborder="no" border="1" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>	
				</div>
			</div>
			<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
		</div>
	</form:form>
</body>
</html>