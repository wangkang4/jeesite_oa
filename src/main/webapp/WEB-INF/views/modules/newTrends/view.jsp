<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>动态发布</title>
<style type="text/css">
.top{
	width: 80%;
	margin: 0 auto;
	background-color: #F5F5F5;
	padding: 50px 0;
}
.bottom{
	position: absolute;
	right: 20%;
}
.address{
	position: absolute;
	left: 20%;
}
.div{
	width: 20px;
	height: 50px;
}

</style>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/newTrends/list">最新动态</a></li>				
		<li class="active"><a href="">动态详情</a></li>	
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		
		<div class="top">
			
				<div style="width:80%;height:50px;margin: 0 auto;text-align:center;font-weight:bold;font-size: 20px;">
					${newTrends.title}
				</div>
				<div style="display:none">
					<textarea  name="text" id="test_new_line" cols="30" rows="10">${newTrends.text }</textarea>
				</div>
				<div id="result" style="width: 80%;margin: 0 auto;">
				</div>
				<c:if test="${not empty newTrends.address }">
					<div class="address">
						<a href="${ctx}/newTrends/download?id=${newTrends.id}">相关附件</a>
					</div>
				</c:if>
				<div class="bottom">
					<p>作者：${newTrends.user.name }</p>
					<p>时间：<fmt:formatDate value="${newTrends.date}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></p>
				</div>
				<div style="width:80%;height:60px;margin:0 auto;background-color: #F5F5F5;"></div>
				
		</div>
	</form:form>
	<script type="text/javascript">
		var newString = $("#test_new_line").val();
		document.getElementById("result").innerHTML = newString;
			
	</script>
</body>
</html>