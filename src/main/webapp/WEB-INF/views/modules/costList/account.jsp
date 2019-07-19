<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>费用新增界面</title>
<meta name="decorator" content="default" />
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
	function getDetailsList(btn) {
		var costId = $(btn).val();
		$.ajax({
			type : 'post',
			url : "${ctx}/cost/costDetails/getDetailsList",
			data : {
				"costId" : costId
			},
			dataType : 'json',
			success : function(data) {
				$(btn).parent().next().children("select").empty();
				$(btn).parent().next().children("select").append(
						"<option value=''>请选择</option>");
				for (var i = 0; i < data.length; i++) {
					$(btn).parent().next().children("select").append(
							"<option value='"+data[i].detailsId+"'>"
									+ data[i].detailsName + "</option>");
				}
			},
			error : function(data) {
				alert("错误信息");
			}

		});
	}
	$(function() {
		$("#btn")
				.click(
						function() {
							$("#contentTable tbody")
									.append(
											"<tr>"
													+ "<td><input id='useTime' name='useTime1' type='text'"
													+ "readonly='readonly' maxlength='20' class='input-medium Wdate '"
													+ "value=\"<fmt:formatDate value='${now }' pattern='yyyy-MM-dd'/>\""
													+ "onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});\" />"
													+ "</td>"
													+ "<td><input id='' name='' value=''></<input></td>"
													+ "<td><select class='input-xlarge'"
													+ "onclick='getDetailsList(this)'>"
													+ "<option value=''>请选择</option>"
													+ "<c:forEach var='costList' items='${list }'>"
													+ "<option value='${costList.costId }'>${costList.costName }</option>"
													+ "</c:forEach>"
													+ "<select></td>"
													+ "<td><select name='detailsId'"+
														"class='input-xlarge'>"
													+ "<option value=''>请选择</option>"
													+ "</select></td>"
													+ "<td><input id='' name='' value='' /></td>"
													+ "<td><input id='' name='' value='' /></td>"
													+ "<td><input id='' name='' value='' /></td>"
													+ "<td><input value='删除' type='button' onclick='del(this)'/></td>"
													+ "</tr>");
						});
	});
	function insertList() {
		var acc = [];
		var count = $("tbody").children().length;
		for (var i = 0; i < count; i++) {
			var account = {};
			account['time'] = $("tbody").children("tr:eq(" + i + ")").children(
					"td:eq(0)").children("input").val();
			account['money'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(1)").children("input").val();
			account['costId'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(2)").children("select").val();
			account['costName'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(2)").children("select").find(
							"option:selected").text();

			account['detailsId'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(3)").children("select").val();
			account['detailsName'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(3)").children("select").find(
							"option:selected").text();
			account['costDescription'] = $("tbody")
					.children("tr:eq(" + i + ")").children("td:eq(4)")
					.children("input").val();
			account['amount'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(5)").children("input").val();
			account['information'] = $("tbody").children("tr:eq(" + i + ")")
					.children("td:eq(6)").children("input").val();
			acc[i] = account;

		}

		/* debugger; */
		var url = "${ctx }/cost/costList/insertList";
		/* console.log(url);
		console.log(window.location.href); */
		$.ajax({
			url : url,
			type : 'post',
			data : JSON.stringify(acc),
			dataType : 'json',
			contentType : "application/json",
			success : function(data) {
				if (data) {
					window.location.href = "${ctx }/cost/costList/list";
				}
			}

		});

	}
	function del(btn){
		$(btn).parent().parent().remove();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cost/costList/list">费用统计管理列表</a></li>
		<li class="active"><a href="${ctx}/cost/costList/toAdd">费用信息新增</a></li>
	</ul>
	<div></div>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日期</th>
				<th>收据金额</th>
				<th>费用分类</th>
				<th>详细分类</th>
				<th>费用描述</th>
				<th>可报销金额</th>
				<th>详细信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input id="useTime" name="useTime1" type="text"
					readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${now }" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				</td>
				<td><input id="" name="" value=""></<input></td>
				<td><select class="input-xlarge" onclick="getDetailsList(this)">
						<option value="">请选择</option>
						<c:forEach var="costList" items="${list }">
							<option value="${costList.costId }">${costList.costName }</option>
						</c:forEach>
						<select></td>
				<td><select name="detailsId" class="input-xlarge">
						<option value="">请选择</option>
				</select></td>
				<td><input id="" name="" value="" /></td>
				<td><input id="" name="" value="" /></td>
				<td><input id="" name="" value="" /></td>
				<td><input value="删除" type="button" onclick="del(this)"/></td>
			</tr>
		</tbody>
	</table>

	<input type="button" id="btn" value="添加" class="btn btn-primary">
	<a href="${ctx}/cost/costList/toAdd"><input type="button" id="btn"
		value="重置" class="btn btn-primary"></a>
	<div style="text-align: center">
		<input value="保存" class="btn btn-primary" onclick="insertList()">
	</div>
</body>
</html>