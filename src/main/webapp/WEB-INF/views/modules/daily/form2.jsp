<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="decorator" content="default" />
<title>日报列表</title>

<style type="text/css">
.ok {
	color: green;
	font: normal normal 12px/28px '微软雅黑';
	padding: 0px 5px;
}

b {
	color: red;
	font: normal normal 15px/28px '微软雅黑';
	padding: 0px 5px;
}

.err {
	color: red;
	font: normal normal 12px/28px '微软雅黑';
	padding: 0px 5px;
}
</style>

<script type="text/javascript">
	function doSubmit() {
		$("#sign").val(0);
		var performance = $("#performance").val().trim();
		var reg = "^(0|[1-9][0-9]?|100)$";
		if (performance.length == 0) {
			$("#a").html("请输入工作完成情况！");
			$("#a").addClass("err");
			$("#sign").val(0);
			$("#performance").focus();
			return;
		} else if (performance.match(reg)) {
			$("#a").html("输入符合！");
			$("#a").removeClass("err");
			$("#a").addClass("ok");
		} else if (!performance.match(reg)) {
			$("#a").html("请输入0-100的数字！");
			$("#a").addClass("err");
			$("#sign").val(0);
			$("#performance").focus();
			return;
		}

		var userName = $("#userName").val();
		if (userName.length < 1) {
			$("#b").html("请选择收报人！");
			$("#b").addClass("err");
			$("#sign").val(0);
			return;
		} else if (userName.length >= 1) {
			$("#b").html("输入符合！");
			$("#b").removeClass("err");
			$("#b").addClass("ok");
			$("#sign").val(1);
		}

		var dayContent = $("#dayContent").val();
		if (dayContent.length < 1) {
			$("#c").html("请输入日报内容！");
			$("#c").addClass("err");
			$("#sign").val(0);
			$("#dayContent").focus();
			return;
		} else if (dayContent.length >= 1) {
			$("#c").html("输入符合！");
			$("#c").removeClass("err");
			$("#c").addClass("ok");
			$("#sign").val(1);
		}

		var planContent = $("#planContent").val();
		if (planContent.length < 1) {
			$("#d").html("请输入明日计划！");
			$("#d").addClass("err");
			$("#sign").val(0);
			$("#planContent").focus();
			return;
		} else if (planContent.length >= 1) {
			$("#d").html("输入符合！");
			$("#d").removeClass("err");
			$("#d").addClass("ok");
			$("#sign").val(1);
		}

		var sign = $("#sign").val();
		if (sign == 1) {
			$("#searchForm").submit();
		}
	}

	$(function() {
		$("#performance").blur(function() {
			var performance = $("#performance").val().trim();
			var reg = "^(0|[1-9][0-9]?|100)$";
			if (performance.length == 0) {
				$("#a").html("请输入工作完成情况！");
				$("#a").addClass("err");
				$("#performance").focus();
			} else if (performance.match(reg)) {
				$("#a").html("输入符合！");
				$("#a").removeClass("err");
				$("#a").addClass("ok");
			} else if (!performance.match(reg)) {
				$("#a").html("请输入0-100的数字！");
				$("#a").addClass("err");
				$("#performance").focus();
			}
		});

		$("#dayContent").blur(function() {
			var dayContent = $("#dayContent").val();
			if (dayContent.length < 1) {
				$("#c").html("请输入日报内容！");
				$("#c").addClass("err");
				$("#dayContent").focus();
			} else if (dayContent.length >= 1) {
				$("#c").html("输入符合！");
				$("#c").removeClass("err");
				$("#c").addClass("ok");
			}
		});

		$("#planContent").blur(function() {
			var planContent = $("#planContent").val();
			if (planContent.length < 1) {
				$("#d").html("请输入明日计划！");
				$("#d").addClass("err");
				$("#planContent").focus();
			} else if (planContent.length >= 1) {
				$("#d").html("输入符合！");
				$("#d").removeClass("err");
				$("#d").addClass("ok");
			}
		});
	});

	function vali(userName) {
		if (userName.length < 1) {
			$("#b").html("请选择收报人！");
			$("#b").addClass("err");
		} else if (userName.length >= 1) {
			$("#b").html("输入符合！");
			$("#b").removeClass("err");
			$("#b").addClass("ok");
		}
	}
</script>

</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/daily/tDaily/list">我发出的</a></li>
		<li class="active"><a href="${ctx}/daily/tDaily/form?id=${tDaily.id }">日报修改</a></li>
	</ul>
    <input id="sign" type="hidden" />
	<form:form id="searchForm" modelAttribute="tDaily"
		action="${ctx}/daily/tDaily/update" method="post"
		class="breadcrumb form-search">
		<form:input path="id" type="hidden" value="${tDaily.id }"  />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th colspan="4"
						style="text-align: center; font-size: 18px; height: 30px;">日报修改</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height: 40px">
<!-- 					<td style="text-align: center">日报时间</td> -->
<!-- 					<td><input id="dayTime" name="dayTime" type="text" -->
<!-- 						readonly="readonly" maxlength="20" class="input-medium Wdate" -->
<!-- 						style="width: 163px;" -->
<%-- 						value="<fmt:formatDate value="${tDaily.dayTime}" pattern="yyyy-MM-dd"/>" --%>
<!-- 						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" /></td> -->
					<td style="text-align: center"><b>*</b>工作进度(%)</td>
					<td><form:input path="performance"/><span
						id="a"></span></td>
				</tr>
				<tr>
					<td style="text-align: center"><b>*</b>发给谁</td>
					<td><sys:treeselectvali id="user" name="user.id" value=""
							labelName="user.name" labelValue="${tDaily.remarks}" title="用户"
							url="/sys/office/treeData?type=3" checked="true"
							cssStyle="width:150px" allowClear="true"
							notAllowSelectParent="true" /><span
						id="b"></span></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td style="text-align: center"><b>*</b>今日工作内容</td>
					<td colspan="3"><form:textarea path="dayContent"
							htmlEscape="false" rows="4" maxlength="255" style="width:80%"
							class="input-xxlarge " /><span
						id="c"></span></td>
				</tr>
				<tr>
					<td style="text-align: center"><b>*</b>明日工作计划</td>
					<td colspan="3"><form:textarea path="planContent"
							htmlEscape="false" rows="4" maxlength="255" style="width:80%"
							class="input-xxlarge " /><span
						id="d"></span></td>
				</tr>
				<tr>
					<td style="text-align: center">备注</td>
					<td colspan="3"><form:textarea path="remark"
							htmlEscape="false" rows="4" maxlength="255" style="width:80%"
							class="input-xxlarge " /></td>
				</tr>
			</tbody>
		</table>

		<div class="form-actions" style="float:right;clear:both;margin-right:45%">
			<input id="btn" class="btn btn-primary" type="button"  style="margin-right:50px"
				onclick="doSubmit()" value="发送" /> &nbsp; <input id="btnCancel" class="btn"
				type="button" value="返 回" onclick="history.go(-1)" />
		</div>
	</form:form>

</body>
</html>