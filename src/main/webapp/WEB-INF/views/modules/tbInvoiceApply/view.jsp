<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<meta name="decorator" content="default" />
<title>开票申请管理</title>
<script type="text/javascript">
$(document).ready(
		function() {
			check();
		});
function check(){
	
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	console.log(url)
	if(url.indexOf("view?act")>=0){
		if(statu=="审核通过"){
			console.log("1")
		}else{
		$("#btnCancel").before('<input class="btn" type="button" value="回退" onclick="rollback()">');
		console.log(2)
		}
	}
}
function rollback(){
	var msg="你确定要回退到自己节点吗";
	if(confirm(msg)==true){
		var url = window.location.href;
		var ele = url.split("&");
		var task = ele[0].split("=");
		var taskId = task[1];
		url = url.substr(0,url.indexOf("tb/"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tb/invoiceApply/list">开票申请表列表</a></li>				
		<li class=""><a href="${ctx}/tb/invoiceApply/list2">员工开票申请列表</a></li>	
		<li class="active"><a href="">申请详情</a></li>	
	</ul>
	<form:form class="form-horizontal">
		<sys:message content="${message}" />
		<input type="hidden" name="statu" value="${invoice.statu }"/>
		<div style="display:none">
			<textarea  name="text" id="test_new_line" cols="30" rows="10">${invoice.invoiceInfo}</textarea>
		</div>
		<fieldset>
			<legend>申请详情</legend>
			<table id="contentTable" class="table-form">
				<tr>
					<td class="tit">开票名称</td>
					<td colspan="5">
						${invoice.taxName }
					</td>
					<%-- <td class="tit">纳税人识别号</td>
					<td>
						${invoice.taxNumber }
					</td>
					<td class="tit">地址</td>
					<td>
						${invoice.address }
					</td> --%>
				</tr>
				<%-- <tr>
					<td class="tit">电话</td>
					<td>
						${invoice.phone }
					</td>
					<td class="tit">开户银行</td>
					<td>${invoice.bank }
					</td>
					<td class="tit">账号</td>
					<td>
						${invoice.account }
					</td>
				</tr> --%>
				
				<tr>
					<td class="tit" >开票信息</td>
					
					<td  colspan="5" style="width:100px; word-wrap:break-word;word-break:break-all;">
					<p id="result"></p>
					</td> 
				</tr>
				<tr>
					<td class="tit">项目名称</td>
					<td>
						${invoice.proname} 
					</td>
					<td class="tit">项目经理</td>
					<td>
						${invoice.manage.name} 
					</td>
					<td class="tit">办事处负责人</td>
					<td>
						${invoice.offPerson.name}
					</td>
				</tr>
				<tr>
					<td class="tit">本次开票金额</td>
					<td>
						${invoice.total} 元
					</td>					
					<td class="tit">已开票金额</td>
					<td>
						${invoice.alMoney}元 
					</td>
	
					<td class="tit">总金额</td>
					<td>
						${invoice.nowMoney}元
					</td>
				</tr>
				<tr>
					<td class="tit" >邮寄地址</td>
					<td colspan="6">${invoice.maAddress } ${invoice.colPerson } ${invoice.colPhone }</td>
					<%-- <td class="tit">收件人</td>
					<td>${invoice.colPerson }</td>
					<td class="tit">电话</td>
					<td>${invoice.colPhone }</td> --%>
				</tr>
				<tr>
					<td class="tit">附件</td>
					<td colspan="5">
					<c:if test="${not empty invoice.applyAddress }">
						<a href="${ctx}/tb/invoiceApply/download?id=${invoice.id}">附件下载</a>
					</c:if>
					<c:if test="${empty invoice.applyAddress }">
						无附件
					</c:if>
					</td>
				</tr>
				<tr>
					<td class="tit">验收报告</td>
					<td colspan="6">
					${invoice.report }											 
					</td>
				</tr>
				<c:if test="${not empty invoice.oneText }">
				<tr>
					<td class="tit">商务部长审核意见</td>
					<td colspan="3">${invoice.oneText}</td>
				</tr>
					</c:if>
				<c:if test="${not empty invoice.proneText }">
				<tr>
					<td class="tit">财务总监审核意见</td>
					<td colspan="3">${invoice.proneText}</td>
				</tr>
					</c:if>
					<c:if test="${not empty invoice.prtwoText }">
						<tr>
							<td class="tit">开票人审核意见</td>
							<td colspan="3">${invoice.prtwoText}</td>
						</tr>
						<tr>
							<td class="tit">开票时间</td>
							<td colspan="3">${invoice.invoiceDate}</td>
						</tr>
					</c:if>
			</table>
		</fieldset>
		<act:histoicFlow procInsId="${invoice.act.procInsId}" />
		<div class="form-actions">
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
	<script type="text/javascript">
		
		var newString = document.getElementById("test_new_line").value.replace(/\n/g, '_@').replace(/\r/g, '_#');
		newString = newString.replace(/_#_@/g, '<br/>');//IE7-8
		newString = newString.replace(/_@/g, '<br/>');//IE9、FF、chrome
		newString = newString.replace(/\s/g, '&nbsp;');//空格处理
		document.getElementById("result").innerHTML = newString;
			
	</script>
</body>
</html>