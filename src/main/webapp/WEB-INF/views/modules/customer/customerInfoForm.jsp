<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>客户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">	
	function myCheck(){
		if(cutomerForm.customerName.value==''){
			alertx('客户姓名不能为空！');
			cutomerForm.customerName.focus();
			return false;
		}
		if(cutomerForm.customerBrithday.value==''){
			alertx('出生日期不能为空！');
			cutomerForm.customerBrithday.focus();
			return false;
		}
		if(cutomerForm.company.value==''){
			alertx('就职单位不能为空！');
			cutomerForm.company.focus();
			return false;
		}
		if(cutomerForm.position.value==''){
			alertx('职位不能为空！');
			cutomerForm.position.focus();
			return false;
		}
		if(cutomerForm.sex.value==''){
			alertx('性别不能为空！');
			cutomerForm.sex.focus();
			return false;
		}
		if(cutomerForm.hobby.value==''){
			alertx('兴趣爱好不能为空！');
			cutomerForm.hobby.focus();
			return false;
		}
		if(cutomerForm.phone.value==''){
			alertx('联系电话不能为空！');
			cutomerForm.phone.focus();
			return false;
		}
		if(cutomerForm.adress.value==''){
			alertx('联系地址不能为空！');
			cutomerForm.adress.focus();
			return false;
		}
		if(cutomerForm.remarks.value==''){
			alertx('备注不能为空！');
			cutomerForm.remarks.focus();
			return false;
		}
		
		
		if(document.cutomerForm.customerCompany.length>1 ){
			for(var i=0;i<document.cutomerForm.customerCompany.length;i++){
				if(document.cutomerForm.customerCompany[i].value==""){
					alertx("单位名称不能为空！");
					return false;
				}
			}
		}else{
			if(document.cutomerForm.customerCompany.value==""){
				alertx("单位名称不能为空！");
				return false;
			}
		}
		
		if(document.cutomerForm.customerPosition.length>1 ){
			for(var i=0;i<document.cutomerForm.customerPosition.length;i++){
				if(document.cutomerForm.customerPosition[i].value==""){
					alertx("职位名称不能为空！");
					return false;
				}
			}
		}else{
			if(document.cutomerForm.customerPosition.value==""){
				alertx("职位名称不能为空！");
				return false;
			}
		}
		
		if(document.cutomerForm.positionTime.length>1 ){
			for(var i=0;i<document.cutomerForm.positionTime.length;i++){
				if(document.cutomerForm.positionTime[i].value==""){
					alertx("任职时间不能为空！");
					return false;
				}
			}
		}else{
			if(document.cutomerForm.positionTime.value==""){
				alertx("任职时间不能为空！");
				return false;
			}
		}
		
		if(document.cutomerForm.familyName.length>1 ){
			for(var i=0;i<document.cutomerForm.familyName.length;i++){
				if(document.cutomerForm.familyName[i].value==""){
					alertx("家庭成员姓名不能为空！");
					return false;
				}
			}
		}else{
			if(document.cutomerForm.familyName.value==""){
				alertx("家庭成员姓名不能为空！");
				return false;
			}
		}
		
		if(document.cutomerForm.familyBirthday.length>1 ){
			for(var i=0;i<document.cutomerForm.familyBirthday.length;i++){
				if(document.cutomerForm.familyBirthday[i].value==""){
					alertx("出生日期不能为空！");
					return false;
				}
			}
		}else{
			if(document.cutomerForm.familyBirthday.value==""){
				alertx("出生日期不能为空！");
				return false;
			}
		}
		
		if(document.cutomerForm.relationship.length>1 ){
			for(var i=0;i<document.cutomerForm.relationship.length;i++){
				if(document.cutomerForm.relationship[i].value==""){
					alertx("关系不能为空！");
					return false;
				}
			}
		}else{
			if(document.cutomerForm.relationship.value==""){
				alertx("关系不能为空！");
				return false;
			}
		}
		return true;
	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/dict/">客户添加</a></li>
	</ul><br/>
	<div style="text-align: center"><span style="font-size: 24px;color: blue">客户添加</span></div>
	<span style="font-size: 14px">基本信息</span>
	<form name="cutomerForm" action="${ctx }/customer/customerInfoSave" method="post" class="form-horizontal" onSubmit="return myCheck()" >
		<table class="table-form">
			<tr>
				<td class="tit">客户姓名</td>
				<td>
					<input name="customerName" type="text">
				</td>
				<td class="tit">部门</td>
				<td>
					<select name='parentId' id="'parentId'" class="input-xlarge required">
						<c:forEach  items="${ztreeInfoList}" var="item"  >
					       <option value="${item.parentId}" >${item.nodeName }</option>
					    </c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tit">就职单位</td>
				<td>
					<input name="company" type="text">
				</td>
				<td class="tit">职位</td>
				<td>
					<input name="position" type="text">
				</td>			
			</tr>
			<tr>
				<td class="tit">性别</td>
				<td>
					<select name="sex" style="width: 220px">
						<option value="">---请选择---</option>
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</td>
				<td class="tit">兴趣爱好</td>
				<td>
					<input name="hobby" type="text">
				</td>
			</tr>
			<tr>
				<td class="tit">联系电话</td>
				<td>
					<input name="phone" type="text">
				</td>
				<td class="tit">联系地址</td>
				<td>
					<input name="adress" type="text">
				</td>
			</tr>
			<tr>
				<td class="tit">出生日期</td>
				<td>
					<input name="customerBrithday" type="text" readonly="readonly" maxlength="20" class="Wdate required"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
			</tr>
			<tr>
				<td class="tit">备注</td>
				<td colspan="3">
					<textarea name="remarks" rows="6" cols="" style="width: 1080px"></textarea>
				</td>
			</tr>
		</table><br/>
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">履&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</span>
		</div>
		<div style="text-align: right;float: right;">
			<input type="button" id="btnAdd" class="btn btn-primary" value="添加" onclick="addResume()" />
			<input type="button" id="btnDel" class="btn btn-primary" value="删除" onclick="removeResumeTr()" />
		</div>
		<table class="table-form" id="resumeTbl">
		<thead>
			<tr>
				<td class="tit">单位名称</td>
				<td class="tit">职位名称</td>
				<td class="tit">任职时间</td>
				<!--<td class="tit">操作</td>  -->
			</tr>
		</thead>
		<tbody id="resume">
			<tr>
				<td class="tit">
					<input name="customerCompany" type="text">
				</td>
				<td class="tit">
					<input name="customerPosition" type="text">
				</td>
				<td class="tit">
					<input name="positionTime" type="text">
				</td>
				<!--  
				<td class="tit">
					<a href="#">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该条履历吗？', this.href)">删除</a>
					<a href="#">更多</a>
				</td>-->
			</tr>
		</tbody>
		</table><br/>	
		
		<div style="text-align: left;float: left;">
			<span style="font-size: 14px">社会关系</span>
		</div>
		<div style="text-align: right;float: right;">
			<input type="button" id="btnAdd" class="btn btn-primary" value="添加" onclick="addFamily()" />
			<input type="button" id="btnDel" class="btn btn-primary" value="删除" onclick="removeFamilyTr()" />
		</div>
		<table class="table-form" id="familyTbl">
		<thead>
			<tr>
				<td class="tit">姓名</td>
				<td class="tit">出生日期</td>
				<td class="tit">关系</td>
				<!--<td class="tit">操作</td>  -->
			</tr>
		</thead>
		<tbody id="family">
			<tr>
				<td class="tit">
					<input name="familyName" type="text">
				</td>
				<td class="tit">
					<input name="familyBirthday" type="text" readonly="readonly" maxlength="20" class="Wdate required"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				</td>
				<td class="tit">
					<input name="relationship" type="text">
				</td>
				<!--  
				<td class="tit">
					<a href="#">修改</a>
					<a href="#" onclick="return confirmx('确认要删除该家庭成员信息吗？', this.href)">删除</a>
					<a href="#">更多</a>
				</td>-->
			</tr>
		</tbody>
		</table><br/>
		
		<div style="text-align: center;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form>
	
	<script type="text/javascript">
	function addFamily() {
		htmlstr = '<tr>'
			+'<td class="tit"><input name="familyName" type="text"></td>'
			+'<td class="tit">'
			+'<input id="familyBrithday" name="familyBrithday" type="text" readonly="readonly" maxlength="20" class="Wdate required"'
			+'onclick="WdatePicker({dateFmt:"yyyy-MM-dd",isShowClear:false})"/></td>'
			+'<td class="tit"><input name="relationship" type="text"></td>'
			+'</tr>';
		$("#family").append(htmlstr);
	}
	
	function addResume() {
		htmlstr = '<tr>'
			+'<td class="tit"><input name="customerCompany" type="text"></td>'
			+'<td class="tit"><input name="customerPosition" type="text"></td>'
			+'<td class="tit"><input name="positionTime" type="text"></td>'
			+'</tr>';
		$("#resume").append(htmlstr);
	}
	
	function removeResumeTr() {
		$("#resumeTbl").find("tr:last").remove();
	}
	
	function removeFamilyTr() {
		$("#familyTbl").find("tr:last").remove();
	}
	</script>
</body>
</html>