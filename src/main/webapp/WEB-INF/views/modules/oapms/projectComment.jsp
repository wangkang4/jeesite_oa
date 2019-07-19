<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>项目评论</title>
<meta name="decorator" content="default" />
<link rel="stylesheet" href="${ctxStatic}/oapms/project/css/timeline.css">
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pms/project/projectDetail?id=${projectId}">基本信息</a></li>
		<li class="active"><a href="#">评论</a></li>
		<li><a href="${ctx}/pms/comment/projectDocument?id=${projectId}">文档</a></li>
		<li><a href="${ctx}/pms/projectHelp/helpList?id=${projectId}">求助</a></li>
		<li><a href="${ctx}/pms/projectHelp/expenseList?id=${projectId}">费用</a></li>
	</ul>
	<div class="box">
			<input type="hidden" id="projectId" value="${projectId }">
			<input type="hidden" id="userId" value="${fns:getUser().name}">
			<input type="hidden" id="count" value="${page.lastPage}" >
			<input type="hidden" id="pageSize" value="${page.pageSize }">
			<input type="hidden" id="pageNo" value="${page.pageNo }">
			<!--内容发布区域-->
			<div class="boxcenter">
				<div class="boxc_t">
					<h4>随便说点什么吧...</h4></div>
				<div class="boxc_c" contenteditable="true" id="aa"></div>
				<div class="boxc_b">
					<a href="#">发布</a>
				</div>
			</div>

			<!--时光轴线-->
			<div class="timeline">
				<div class="timeline_t">
				</div>
				<div class="nextbox">
					<c:forEach items="${page.list}" var="comment">		
						<div class='a'>
							<div class='b'>
							</div>
								<span class='time'><fmt:formatDate value="${comment.commentTime}" type="both" pattern="yyyy-MM-dd HH:mm"/>&nbsp;&nbsp;
									<span class='hour'>${comment.commentBy.name }</span>
								</span><br>
							<p style='padding:4px'>${comment.content }</p>
						</div>
					</c:forEach>
				</div>
			</div>
			<div class="more" onclick="getMoreComment()">
				加载更多
			</div>
		</div>
	
		<script type="text/javascript">
			$(function() {
				var projectId = $("#projectId").val();
				var userId = $("#userId").val();
				var count = $("#count").val();
				var dateDom = new Date();
				//获取本地时间，年月日时分
				var year = dateDom.getFullYear();
				var month = dateDom.getMonth() + 1;
				var day = dateDom.getDate();
				var hour = dateDom.getHours();
				var min = dateDom.getMinutes();
				if(0<month<10){
					month = "0" + month;
				}
				if(0<day<10){
					day = "0" +day;
				}
				$(".box").find(".boxc_b").click(function() {
					var center = $(".boxc_c").text(); //.appendTo("nextbox");
					if(center == "") {
						alert("请输入内容喔！");
						return;
					}else if(center.length>270){
						alert("你输入的太多了！");
					}

					$.ajax({
						type : 'post',
						url : "${ctx}/pms/comment/commentAdd",
						data : {
							"center" : center,
							"projectId":projectId
						},
						dataType : 'json',
						success : function(data) {
							debugger;
							if("success"==data.data){
								var boxHeight = $(".box").height();
								$(".box").height(boxHeight + 110);
								$(".nextbox").prepend("<div class='a'>" +
										"<div class='b'></div>" +
										"<span class='time'>" + year + "-" +
										month + "-" +
										day  + " "+ hour + ":" + min +"&nbsp;&nbsp;" +
										"<span class='hour'>" + userId + "</span>" +
										"</span>" +
										"<br>" +
										"<p style='padding:4px'>" + center + "</p>" +
										"</div>");
									$(".boxc_c").text("");
							}else{
								alert("插入失败");
							}
							
						}
					});
					
				});
				//alert(1);
				$(".boxc_c").keydown(function(event) {
					var len = $(".boxc_c").text().length;
					if(len > 270) {
						alert("你输入的太多了！");
					}
				});

			});
			function getMoreComment(){
				var projectId = $("#projectId").val();
				var count = $("#count").val();
				if(count=='false'){
					var pageSize = $("#pageSize").val();
					var pageNo = $("#pageNo").val();
					var pageNo1 = pageNo * pageSize; 
					
					$.ajax({
						type : 'post',
						url : "${ctx}/pms/comment/projectCommentMore",
						data : {
							"pageNo" : pageNo1,
							"id":projectId
						},
						dataType : 'json',
						success : function(data) {
							var boxHeight = $(".box").height();
							$(".box").height(boxHeight + 110*data.length);
							for (var i = 0; i < data.length; i++) {
								$(".nextbox").append("<div class='a'>" +
										"<div class='b'></div>" +
										"<span class='time'>" + data[i].remarks +
										"&nbsp;&nbsp;" +
										"<span class='hour'>" + data[i].commentBy.name + "</span>" +
										"</span>" +
										"<br>" +
										"<p style='padding:4px'>" + data[i].content + "</p>" +
										"</div>");
							}
							$("#pageNo").val(parseInt(pageNo)+1);
							if(data.length == 20){
								$("#count").val(false);
							}else{
								$("#count").val(true);
							}
						}
					});
				}
			}
				
		</script>
	
</body>
</html>