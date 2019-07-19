<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>借款管理</title>
<style type="text/css">
#Borrowing {
	position: relative;
	width: 840px;
	margin: 20px auto;
}

.table {
	width: 840px;
	height: 480px;
}

#Borrowing .top {
	text-align: center;
	width: 60%;
	margin: 0 auto 20px auto;
	font-size: 26px;
	border-bottom: 1px solid rgb(40, 44, 52);
	padding-bottom: 8px;
	box-shadow: 0 0 0 2px rgb(255, 255, 255), 0 4px 0 rgb(40, 44, 52);
	/* box-shadow:0rem 0.1rem 0rem 0.2rem rgb(40,44,52); */
}

#Borrowing table {
	width: 100%;
	text-align: center;
	line-height: 80px;
	font-size: 16px;
	border: 1px solid rgb(40, 44, 52);
}

#Borrowing table td {
	line-height: 30px;
	border-top: 1px solid rgb(40, 44, 52);
	border-left: 1px solid rgb(40, 44, 52);
}

#Borrowing table tr td:first-child {
	width: 90px;
}

#Borrowing table .high td {
	line-height: 24px;
	vertical-align: middle;
	padding: 5px 10px;
	border-top: 1px solid rgb(40, 44, 52);
	border-left: 1px solid rgb(40, 44, 52);
}

#Borrowing table .high td:nth-child(2) {
	text-align: left;
	padding: 7px 8px;
}

.gray {
	background-color: #999;
}

#img {
	height: 100%;
	width: 100%;
	position: absolute;
	top: -5%;
	left: 20%;
}

img {
	height: 100%;
	width: auto;
	top: 0px;
}

.footer {
	font-size: 12px;
	color: #686868;
	margin-top: 6px;
	text-indent: 1rem;
}

.header {
	font-size: 14px;
	line-height: 18px;
	color: #686868;
	overflow: hidden;
	text-indent: 1rem;
}

.header>div {
	overflow: hidden;
}

.header>div>.font_one {
	float: left;
	font-size: 18px;
	font-weight: bold;
	color: #333;
}

.header>div>.font_two {
	margin-left: 2rem;
	float: left;
	line-height: 22px;
}

.header>div>.font_three {
	float: right;
	line-height: 22px;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	dayin();
});
function dayin(){
	bdhtml=window.document.body.innerHTML;//获取当前页的html代码  
	sprnstr="<!--startprint-->";//设置打印开始区域  
	eprnstr="<!--endprint-->";//设置打印结束区域  
	prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html  
	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html 
	window.document.body.innerHTML=prnhtml;  
	window.print();  
}
</script>
</head>
<body>
	<!--startprint-->
	<div class="table" id="Borrowing" style="display: block">
		<div class="header">
			<div>
				<span class="font_one">北京桃花岛办公系统</span> <span class="font_two">在线办公</span>
				<span class="font_three">您好,${fns:getUser().getName() }</span>
			</div>

		</div>
		<div class="top">借款申请表</div>
		<table border="0" cellspacing="0">
			<tr>
				<td class="tit">所属公司</td>
				<td colspan="3" align="center">${borrowing.ename }</td>
			</tr>
			<tr>
				<td class="gray">借款人</td>
				<td>${borrowing.name.name }</td>
				<td class="gray">所属部门</td>
				<td>${borrowing.office.name }</td>
			</tr>
			<tr>
				<td class="gray">借款金额</td>
				<td><fmt:formatNumber type="number" value="${borrowing.money}"
						maxFractionDigits="2" pattern="#0.00" />元</td>
				<td class="gray">借款日期</td>
				<td><fmt:formatDate value="${borrowing.time}" type="both"
						pattern="yyyy-MM-dd" /></td>
			</tr>
			<tr class="high">
				<td class="gray">借款原因</td>
				<td colspan="3">${borrowing.reason}</td>
			</tr>
			<tr class="high">
				<td class="gray">备注</td>
				<td colspan="3">${borrowing.notes}</td>
			</tr>
			<tr>
				<td class="gray">审批人职位</td>
				<td class="gray">审批人</td>
				<td class="gray">审批时间</td>
				<td class="gray">审批意见</td>
			</tr>
			<c:forEach items="${historyList}" var="hlist">
				<tr>
					<td>${hlist.name}</td>
					<td>${hlist.assignee}</td>
					<td>${fn:substring(hlist.endTime,0,19)}</td>
					<td>${hlist.message}</td>
				</tr>
			</c:forEach>
		</table>
		<div class="footer">Copyright &copy; 2018 桃花岛办公平台 - Powered by
			合肥办 V1.0.0</div>
		<div id="img">
			<img alt="" src="${ctxStatic}/images/oa/mark.png">
		</div>
	</div>
	<!--endprint-->
	<button onclick="dayin(1)">打 印</button>
</body>
</html>