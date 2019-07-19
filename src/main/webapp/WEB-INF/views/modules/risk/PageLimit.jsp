<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>风险管理</title>
<meta name="decorator" content="default" />
<style type="text/css">

	

</style>
<script type="text/javascript">
	$(document).ready(function() {
		var totalPage=$("#totalPage").val();
		var pageNum=$("#pageNum").val();
		
		console.log("totalPage:"+totalPage);
		console.log("pageNum:"+pageNum)
		
		var html='';
		if(totalPage<=5){
			for(var i=0;i<totalPage;i++){	
				html = html+'<a  class="click" class="click"">'+(i+1)+'</a>&nbsp;';	
			}
		}else{
			for(var i=0;i<5;i++){
				if(pageNum<=3){
					for(var i=0;i<totalPage-1;i++){
						console.log("当前页面小于3");
						html = html+'<a  class="click"">'+(i+1)+'</a>&nbsp;';	
					}
				}else{
						console.log("当前页面大于三");
						console.log("pageNum:"+pageNum);
						var page=parseInt(pageNum);
						console.log("page:"+page);
						var page1=page+1;
						var page2=page+2;
						console.log("page1:"+page1);
						console.log("page2:"+page2);		
					html='<a class="click"">'+(pageNum-2)+'</a>&nbsp;';	
					html +='<a class="click"">'+(pageNum-1)+'</a>&nbsp;';	
					html	+='<a class="click"">'+(pageNum)+'</a>&nbsp;';	
					html +='<a class="click"">'+page1+'</a>&nbsp;';	
					html +='<a class="click"">'+page2+'</a>&nbsp;';	
			}
		}
		
		
		}
		var str='<a id="last" class="click"" value="pageNum-1">下一页</a>';
		var str1='<span>&nbsp;&nbsp;当前${risk.pageNum}&nbsp;&nbsp;/&nbsp;&nbsp;${risk.totalPage}页,共${risk.totalRecord}条</span>';
		console.log("html:"+html);
		if(pageNum==1){
			$("#first").removeAttr("href");
			$("#first").removeAttr('onclick');
			$("#first").css("text-decoration","none");
			$("#first").css("color","black");
		}
		$("#container").append(html);
		$("#container").append(str);
		$("#container").append(str1);	
		if(pageNum==totalPage){
			console.log("totalPage2:"+totalPage);
			console.log("pageNum2:"+pageNum)
			$("#last").removeAttr("href");
			$("#last").css("text-decoration","none");
			$("#last").css("color","black");
			console.log("移除属性了？");
		}
		var riskState=$("#riskState").val();
		console.log("rsikState:"+riskState);
		if(riskState!=null){
			$("#riskState option[value='"+riskState+"']").attr("selected","selected");  
		}
		
		
		
		$("a").click(function(){
			var pageNum=$(this).html();
			var projectName=$("#projectName").val();
			var riskState=$("#riskState").val();
			console.log("ajax.pageNum:"+pageNum);
			console.log("ajax.projectName:"+projectName);
			console.log("ajax.riskState:"+riskState);
			$.ajax({
			"type"		:		"post",
			"url"			:		"${ctx}/pms/risk/limit",
			"data"		:		{"pageNum":pageNum,"projectName":projectName,"riskState":riskState},
			"dataType":		"json",
			"success":function(data){
				console.log(data);
				var json=data.risk;
				
				console.log("data.projectNum:"+data.risk);
			
				console.log("data.list:"+data.risk.list);
				var list=data.risk.list;
				console.log(list.size);
				for(var i=0;i<list.size;i++){
					console.log("data[i]:"+list[i]);
				}		
			}
			});
		
		  });
	
	
		
		
		
		
	});
	
	
</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active"><a href="">风险列表</a></li>
		<li><a href="${ctx}/pms/risk/addrisk">风险添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="PageBean"
		action="${ctx}/pms/risk/show" method="post"
		class="breadcrumb form-search">
		
		<ul class="ul-form">
			<li><label>名称：</label>
				<input id="projectName"  type="text"  name="riskInfo.pmsPro.projectName" value="${projectName}">
			</li>
			<li><label>状态：</label>
			<input id="riskState" name="riskState"type="hidden" value="${riskState}">
				<select style="width: 200px;"name="riskInfo.riskState" >
					<option value="">--请选择--</option>
					<option value="已关闭">已关闭</option>
					<option value="未关闭">未关闭</option>
				</select>
				
				
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input type="button" class="btn btn-primary" onclick="javascript:window.location.href=''" value="清除"></li>
			<li class="clearfix"></li>
		</ul>
		
		
	</form:form>
	
	<table style ="text-align: center;"id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>项目名称</th>
				<th>风险标题</th>
				<th>概率</th>
				<th>影响</th>
				<th>状态</th>
				<th>责任人</th>
				<th>创建时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${risk.list}"  var="risk">
				<tr>
					<td>	
					   	${risk.pmsPro.projectName}
					</td>
					<td>
						${risk.riskName}
					</td>
					<td>
						${risk.riskPro}
					</td>
					<td>
						${risk.riskInfu}
					</td>
					<td>
						${risk.riskState}
					</td>
					<td>
						${risk.user.name}
					</td>
					<td>	
						<fmt:formatDate value="${risk.createDate}" type="both" pattern="yyyy-MM-dd "/>
					</td>
						<td> <a
						href="${ctx}/pms/risk/delete?id=${risk.id}"
						onclick="return confirmx('确认要删除该信吗？', this.href)">删除</a>
					   <a href="${ctx}/pms/risk/updaterisk?id=${risk.id}">修改</a>
						<a href="${ctx}/pms/riskback/showback?id=${risk.id}">反馈</a>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input id="totalPage"  type="hidden" value="${risk.totalPage}">
	<input id="pageNum"  type="hidden" value="${risk.pageNum}">
	<div id="container"  class="container"  >
	<a  id="first" class="click"">上一页</a>
		
	</div>
	
	
	
</body>
</html>