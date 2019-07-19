<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />

<title>excel上传</title>
<link rel="stylesheet" href="${ctxStatic}/css/excel_form.css">
<script type="text/javascript"
	src="${ctxStatic}/jquery-validation/1.11.0/lib/jquery.form.js"></script>

<script type="text/javascript">

	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function doSubmit() {
		var upfile = $("#upfile").val();
		var flag = false;
		var arr = new Array("xlsx", "xls");
		var index = upfile.lastIndexOf(".");
		var ext = upfile.substring(index + 1, upfile.length);
		for (var i = 0; i < arr.length; i++) {
			if (ext == arr[i]) {
				flag = true;
				break;
			}
		}
		if (upfile != null) {
			if (flag) {
				$("#a").html("");
				$("#a").removeClass("err");
				$("#a").addClass("ok");
// 				parent.showMask();
// 				loading('<img style="margin-left:-27px;" src="${ctxStatic}/images/loading1.gif"/>');
				loading();
// 				$("#insertForm").submit();	
				$("#insertForm").ajaxSubmit({ 
				    type: 'post', 
				    url: '${ctx}/oa/costexcel/uploadExcel', 
	                contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	                error: function(request) {
	                    alert("上传失败！请重新上传！");
	                    closeLoading();
	                    window.location.href="${ctx}/oa/costexcel/uploadexcelform"
	                },
	                success: function(data) {
	                	if(data == "success"){
	                		window.location.href="${ctx}/oa/costexcel/list";
	                	}else{
	                		alert(data);
	                		closeLoading();
	                	}
	                }
				  }); 
			} else {
				$("#a").html("请选择.xls或.xlsx文件！");
				$("#a").addClass("err");
			}
		} else {
			$("#a").html("请选择.xls或.xlsx文件！");
			$("#a").addClass("err");
		}
	}
</script>

</head>
<body>
	<div class="main">
		<h3>Excel上传</h3>
		<form:form id="insertForm" enctype="multipart/form-data"
			action="${ctx}/oa/costexcel/uploadExcel" method="post" class="form-search">
			<table id="contentTable"
				class="table table-striped table-bordered table-condensed bg-color">

				<tbody>
					<tr class="tr1">
						<td class="left-td"><label>上报人员：</label></td>
						<td class="right-td" style="text-align:left;">${user.name }</td>
					</tr>
					<tr>
						<td class="left-td"><label>上传文件：</label></td>
						<td class="right-td" style="text-align:left;"><input id="upfile" type="file"
							name="upfile"><span id="a"></span></td>
					</tr>
					<tr>
						<td colspan="2" class="bottom-tddd"><input id="btnSubmit"
							class="btn btn-primary" type="button" value="提交"
							onclick="doSubmit()" /></td>
					</tr>
				</tbody>
			</table>
		</form:form>
	</div>
</body>
</html>