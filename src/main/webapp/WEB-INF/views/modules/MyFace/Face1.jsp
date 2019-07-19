<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="default"/>
	<style type="text/css">
.info {
    padding: 10px 14px 15px;
    width:180px;
    overflow: hidden;
    float: right;
 }
.info_img {
    float: left;
    margin-right: 10px;
    width: 182px;
    height: 152px;
    overflow: visible;
    position: relative;
  }
.text{
	font-size: 15px;
	color: #8f8f94;
	line-height:150%;
}
	</style>
</head>
<body>
<div style="width:70%">
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>日报主题</th>
				<th>完成情况</th>
				<th>发送人</th>
				<th>发送时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="tCheckBacklog">
				<tr>
					<td><fmt:formatDate value="${tCheckBacklog.createrTime}"
							pattern="yyyy-MM-dd" />日报</td>
					<td>${tCheckBacklog.successState}%</td>
					<td>${tCheckBacklog.sendName}</td>
					<td><fmt:formatDate value="${tCheckBacklog.createDate}"
							pattern="yyyy-MM-dd" /></td>
					<td><a
						href="${ctx}/daily/tCheckBacklog/form?profId=${tCheckBacklog.profId}">查看</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<table id="contentTable" >
		<thead>
			<tr>
				<th>标题</th>
				<th>类型</th>
				<th>状态</th>
				<th>查阅状态</th>
				<th>更新时间</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${list1}" var="oaNotify">
			<tr>
				<td><a href="${ctx}/oa/oaNotify/view?id=${oaNotify.id}">
					${fns:abbr(oaNotify.title,50)}
				</a></td>
				<td>
					${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}
				</td>
				<td>
					${fns:getDictLabel(oaNotify.status, 'oa_notify_status', '')}
				</td>
				<td>
					<c:if test="${requestScope.oaNotify.self}">
						${fns:getDictLabel(oaNotify.readFlag, 'oa_notify_read', '')}
					</c:if>
					<c:if test="${!requestScope.oaNotify.self}">
						${oaNotify.readNum} / ${oaNotify.readNum + oaNotify.unReadNum}
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${oaNotify.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
<div class="info">
		
		<lable>昨日日报列表</lable>
			<table class="altrowstable" id="alternatecolor">
				<thead>
					<tr>
						<th>日报主题</th>
						<th>完成情况</th>
						<th>发送人</th>
						<th>发送时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="tCheckBacklog">
						<tr>
							<td><fmt:formatDate value="${tCheckBacklog.createrTime}"
									pattern="yyyy-MM-dd" />日报</td>
							<td>${tCheckBacklog.successState}%</td>
							<td>${tCheckBacklog.sendName}</td>
							<td><fmt:formatDate value="${tCheckBacklog.createDate}"
									pattern="yyyy-MM-dd" /></td>
							<td><a
								href="${ctx}/daily/tCheckBacklog/form?profId=${tCheckBacklog.profId}">查看</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<lable>通知列表</lable>
			<table class="altrowstable" id="alternatecolor">
				<thead>
					<tr>
						<th>标题</th>
						<th>类型</th>
						<th>状态</th>
						<th>查阅状态</th>
						<th>更新时间</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list1}" var="oaNotify">
						<tr>
							<td><a href="${ctx}/oa/oaNotify/view?id=${oaNotify.id}">
									${fns:abbr(oaNotify.title,50)} </a></td>
							<td>${fns:getDictLabel(oaNotify.type, 'oa_notify_type', '')}
							</td>
							<td>${fns:getDictLabel(oaNotify.status, 'oa_notify_status', '')}
							</td>
							<td><c:if test="${requestScope.oaNotify.self}">
						${fns:getDictLabel(oaNotify.readFlag, 'oa_notify_read', '')}
					</c:if> <c:if test="${!requestScope.oaNotify.self}">
						${oaNotify.readNum} / ${oaNotify.readNum + oaNotify.unReadNum}
					</c:if></td>
							<td><fmt:formatDate value="${oaNotify.updateDate}"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		<div class="info_img" >
			<img src="${user.photo }" url="${user.photo }" width="140" height="140">
		</div>
		<div style="text-align:center;">
			<span><strong >头像</strong></span>
		</div>
		<div>
			<label class="text">昨日收到日报数:</label><br>
				<a href="${ctx}/daily/tCheckBacklog/list">${countYstday}
		</div>
</div>
</body>
</html>