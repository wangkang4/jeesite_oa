<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
<!--  	<script type="text/javascript">
	function myCheck(){
		if(document.analysisForm.alyContent.length>1 ){
			for(var i=0;i<document.analysisForm.alyContent.length;i++){
				if(document.analysisForm.alyContent[i].value==""){
					alertx("分析内容不能为空！");
					return false;
				}
			}
		}else{
			if(document.analysisForm.alyContent.value==""){
				alertx("分析内容不能为空！");
				return false;
			}
		}
		
		if(document.analysisForm.alyTime.length>1 ){
			for(var i=0;i<document.analysisForm.alyTime.length;i++){
				if(document.analysisForm.alyTime[i].value==""){
					alertx("分析时间不能为空！");
					return false;
				}
			}
		}else{
			if(document.analysisForm.alyTime.value==""){
				alertx("分析时间不能为空！");
				return false;
			}
		}
		
		return true;
	}
	</script>-->
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/customer/customerDetail?customerId=${customerId}">客户详情修改</a></li>
		<li class="active"><a href="#">客户分析修改</a></li>
	</ul><br/>
		
		<div style="text-align: center"><span style="font-size: 24px;color: blue">客户分析</span></div><br/>
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">客户分析</span>
		</div>
		<div style="text-align: right;float: right;">
			<input type="button" id="btnAdd" class="btn btn-primary" value="添加" onclick="addAnalysis()" />
			<input type="button" id="btnDel" class="btn btn-primary" value="删除" onclick="removeAnalysisTr()" />
		</div><br/>
	<form name="analysisForm" action="${ctx }/customer/customerAnalysisUpdate" method="post" class="form-horizontal" >
	<input type="hidden" name="customerId" value="${customerId }">
		<table class="table-form" id="analysisTbl">
		<thead>
			<tr>
				<td class="tit">分析人员</td>
				<td class="tit">分析内容</td>
				<td class="tit">分析时间</td>
				<!--<td class="tit">操作</td>  -->
			</tr>
		</thead>
		<tbody id="analysis">
		<c:forEach items="${customerAnalysisList }" var="cusAnalysis">
			<tr>
				<td class="tit">
					<input name="alyPerson" value="${cusAnalysis.alyPerson }" type="text" readonly="readonly">
				</td>
				<td class="tit">
					<textarea name="alyContent" rows="3" cols="" style="width: 400px">${cusAnalysis.alyContent }</textarea>
				</td>
				<td class="tit">
					<input name="alyTime" type="text" readonly="readonly" value="${cusAnalysis.alyTime }" maxlength="20" class="Wdate required"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
				<!--  
				<td class="tit">
					<a href="#">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该条履历吗？', this.href)">删除</a>
					<a href="#">更多</a>
				</td>
				-->
			</tr>
		</c:forEach>
		</tbody>
		</table><br/>	
		
		
	
		<div style="text-align: center;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="修 改"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	
	</form>
	
	<script type="text/javascript">
	function addAnalysis() {
		htmlstr = '<tr>'
			+'<td class="tit"><input name="alyPerson" value="${alyPerson }" type="text" readonly="readonly"></td>'
			+'<td class="tit"><textarea name="alyContent" rows="3" cols="" style="width: 400px"></textarea></td>'
			+'<td class="tit">'
			+'<input name="alyTime" type="text" readonly="readonly" maxlength="20" class="Wdate required"'
			+'onclick="WdatePicker({dateFmt:'
			+"'yyyy-MM-dd',"
			+'isShowClear:false})"/></td>'
			+'</tr>';
		$("#analysis").append(htmlstr);
	}

	function removeAnalysisTr() {
		$("#analysisTbl").find("tr:last").remove();
	}
	</script>
</body>
</html>