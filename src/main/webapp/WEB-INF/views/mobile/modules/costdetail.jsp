<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="initial-scale=1.0,user-scalable=0,minimum-scale=1.0, maximum-scale=1.0,width=device-width">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>费用详情</title>
<style type="text/css">

.cha-result {
	padding-top: 15px;
	padding-bottom: 15px;
}

.cha-content-padded {
	padding: 0 10px;
	background-color: #fff;
}

body, .aui-content {
	background-color: #fff;
	padding:0px;
	margin:0px;
}

p {
	margin-top: 10px;
	margin-bottom: 10px;
	padding-left:12px;
	padding-right:12px;
	font-size: 15px;
	color: #8f8f94;
	line-height:150%;
	display: block;
/* 	-webkit-margin-before: 0.5em;  */
/*  	-webkit-margin-after: 0.5em;  */
/*  	-webkit-margin-start: 0px;  */
/*  	-webkit-margin-end: 0px;  */
}

.aui-content { -webkit-overflow-scrolling:touch;
	margin-bottom: 15px;
}

.aui-flex-col {
	-webkit-box-orient: horizontal;
	-webkit-box-direction: normal;
	-webkit-flex-direction: row;
	-ms-flex-direction: row;
	flex-direction: row;
}

.aui-flex-col, .aui-flex-row {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-webkit-flex-wrap: wrap;
	-ms-flex-wrap: wrap;
	flex-wrap: wrap;
}

.aui-flex-item-3 {
	color: #A8A8A8;
	float: left;
	width: 25%;
}

.aui-flex-item-9 {
	color: black;
	float: left;
	width: 72%;
}

.black {
	width: 3%;
}

.lines {
	width: 100%;
	height: 1px;
	padding: 0px;
	background-color: #F0F0F0;
	overflow: hidden;
}
/* span { */
/*     font-weight: 400; */
/* } */
</style>
</head>
<body>
	<div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">客户名称</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.clientName }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">时间</span><span class="black"></span><span
				class="aui-flex-item-9"><fmt:formatDate
					value="${costRecondExcel.useTime}" pattern="yyyy-MM-dd" /></span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">所属部门</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.typeName }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">申请人</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.createrBy.name }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">项目名称</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.projectName }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">项目金额(万元)</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.projectMoney } </span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">说明</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.conment }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">差旅费（含油费）</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.travelExpense }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">餐费</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.mealMoney }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">文化礼品（自购）</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.culturalgiftsPerson }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">其他费用</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.otherMoney }</span>
		</p>
		<div class="lines"></div>
		<p class="aui-flex-col">
			<span class="aui-flex-item-3">文化礼品（公司）</span><span class="black"></span><span
				class="aui-flex-item-9">${costRecondExcel.culturalgiftsCompeny }</span>
		</p>
		<div class="lines"></div>
		
	</div>
</body>
</html>