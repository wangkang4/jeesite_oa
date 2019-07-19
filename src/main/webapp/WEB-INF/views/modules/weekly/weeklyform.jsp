<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>保存成功管理</title>
	<meta name="decorator" content="default"/>
		<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${ctxStatic}/jquery/jquery-1.8.3.js"></script>
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
	
	$(function() {
// 		$("#daytime").blur(function() {
// 			var daytime = $("#daytime").val();
// 			if (daytime.length < 1) {
// 				$("#a").html("请输入周报日期！");
// 				$("#a").addClass("err");
// 				$("#daytime").focus();
// 			} else if (daytime.length >= 1) {
// 				$("#a").html("输入符合！");
// 				$("#a").removeClass("err");
// 				$("#a").addClass("ok");
// 			}
// 		});
		
		$("#daycontent").blur(function() {
			var daycontent = $("#daycontent").val();
			if (daycontent.length < 1) {
				$("#c").html("请输入周报内容！");
				$("#c").addClass("err");
				$("#daycontent").focus();
			} else if (daycontent.length >= 1) {
				$("#c").html("输入符合！");
				$("#c").removeClass("err");
				$("#c").addClass("ok");
			}
		});
		
	});
	//获取本周工作详情（日报）
// 	$(function(){
// 		$("#daytime").blur(function(){
// 			var daytime = $("#daytime").val();
// 			$.ajax({
// 				url : '/thd_oa/a/oa/weekly/getweekcontent',
// 				type : 'post',
// 				dataType : 'json',
// 				data : {"daytime" : daytime},
// 				success:function(data){
// 					$("#weeklycontent").empty();
// 					for(var i =0 ;data.length;i++){
// 						$("#weeklycontent").append(data[i].remarks+"日报:" + data[i].dayContent + "(完成度：" + data[i].performance+"%)"+"<br>");
// 					}
// 				}
// 			})
			
// 		});
// 	});
	
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
		<li><a href="${ctx}/oa/weekly/">我发出的</a></li>
		<li class="active"><a href="${ctx}/oa/weekly/form">周报修改</a></li>
	</ul><br/>
	<input id="sign" type="hidden" />
	<form:form id="searchForm" modelAttribute="weekly" action="${ctx}/oa/weekly/save" method="post" class="form-horizontal">
		<form:hidden path="id" value="${weekly.id }"/>
		<sys:message content="${message}"/>		
<!-- 		<div class="control-group"> -->
<!-- 			<label class="control-label">周报时间：</label> -->
<!-- 			<div class="controls"> -->
<!-- 				<input id="daytime" name="daytime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate " -->
<%-- 					value="<fmt:formatDate value="${weekly.daytime}" pattern="yyyy-MM-dd"/>" --%>
<!-- 					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" /> -->
<!-- 					<span id="a" class="help-inline"><font color="red">*</font>必填 </span> -->
<!-- 			</div> -->
<!-- 		</div> -->
		<div class="control-group">
			<label class="control-label"><b color="red">*</b>收报人：</label>
			<div class="controls">
				<sys:treeselectvali id="user" name="user.id" value="${weekly.user.id}" labelName="user.name" labelValue="${weekly.user.name}"
					title="用户" url="/sys/office/treeData?type=3" cssClass="required" notAllowSelectParent="true" checked="true"/>
					<span id="b" class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">本周详情：</label>
			<div class="controls">
				<span id="weeklycontent"></span>
			<c:forEach items="${tDailylist}" var="tDaily">
					<fmt:formatDate  value="${tDaily.dayTime}" pattern="yyyy-MM-dd"/>日报:${tDaily.dayContent }(完成${tDaily.performance }%)<br/>
			</c:forEach>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><b color="red">*</b>周报内容：</label>
			<div class="controls">
				<form:textarea id="daycontent" path="daycontent" value="${weekly.daycontent }" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
				<span id="c" class="help-inline"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下周计划 ：</label>
			<div class="controls">
				<form:textarea id="plancontent" path="plancontent" value="${weekly.plancontent }" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息 ：</label>
			<div class="controls">
				<form:textarea id="remark" path="remark"  htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">历史附件：</label>
			<div class="controls">
				<c:forEach items="${filelist}" var="fileModel">
					${fileModel.name }&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ctx}/oa/weekly/deletefile?id=${fileModel.id}" onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
					<br>
				</c:forEach>
			</div>
		</div>
		<div >
			<label class="control-label">附件上传：</label>
			<div class="controls">
				<form:textarea style="display:none" path="fileid"  id="fileids"/>
            	<iframe src="${ctx }/oa/weekly/fileuploads"  width="50%" height="200px" frameborder="no" border="1" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
			</div>
		</div>
		<div class="form-actions">
			<input id="btn" class="btn btn-primary" type="button"
				onclick="doSubmit()" value="保存" /> &nbsp;
			<input id="btnCancel" class="btn" type="reset" value="重置" />
		</div>
	</form:form>
	
	<script type="text/javascript">
	function doSubmit() {
		$("#sign").val(0);
// 		var daytime = $("#daytime").val();
// 		if (daytime.length < 1) {
// 			$("#a").html("请输入周报日期！");
// 			$("#a").addClass("err");
// 			$("#sign").val(0);
// 			$("#daytime").focus();
// 			return;
// 		} else if (daytime.length >= 1) {
// 			$("#a").html("输入符合！");
// 			$("#a").removeClass("err");
// 			$("#a").addClass("ok");
// 			$("#sign").val(1);
// 		}
		
		var userName = $("#userName").val();
		if (userName.length < 1) {
			$("#b").html("请输入收报人！");
			$("#b").addClass("err");
			$("#sign").val(0);
			return;
		} else if (userName.length >= 1) {
			$("#b").html("输入符合！");
			$("#b").removeClass("err");
			$("#b").addClass("ok");
			$("#sign").val(1);
		}
		
		var daycontent = $("#daycontent").val();
		if (daycontent.length < 1) {
			$("#c").html("请选择周报内容！");
			$("#c").addClass("err");
			$("#sign").val(0);
			$("#daytime").focus();
			return;
		} else if (daycontent.length >= 1) {
			$("#c").html("输入符合！");
			$("#c").removeClass("err");
			$("#c").addClass("ok");
			$("#sign").val(1);
		}

		var sign = $("#sign").val();
		if (sign == 1) {
			$("#searchForm").submit();
		}
	}
	</script>
</body>

</html>