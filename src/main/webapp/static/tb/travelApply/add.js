$(document).ready(function(){
	var rs = $("textarea[name='reason']").text();
	if($(".table-form tr").length<=7){
		addReceptionStaff()
	}
});
function addReceptionStaff(){
	
	var tr = $("<tr class='addTr'></tr>");
	tr.append('<td class="tit">日期</td>');
	tr.append('<td><input name="planDate" type="text"><span id="planDateWarn" class="warn" style="color: red"></span></td>');
	tr.append('<td class="tit">客户名称</td>');
	tr.append('<td><input name="customerName" type="text"><span id="customerNameWarn" class="warn" style="color: red"></span></td>');
	tr.append('<td class="tit">工作内容</td>');
	tr.append('<td><input name="content" type="text"><span id="contentWarn" class="warn" style="color: red"></span></td>');
	
	$(".table-form tr").eq(-3).after(tr);
}
function delReceptionStaff(){
	if($(".table-form tr").length<=8){
		alertx("至少要有一个人员");
	}else{
		$(".table-form tr").eq(-3).remove();
	}
}
var isCommitted=false;//是否已提交
function save() {
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	$(".warn").text("");
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		isCommitted = true;
		return true;
	}
	return false;
}
function stop() {
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	$(".warn").text("");
	var startAddress = $("input[name='startAddress']").val();//出发地
	var endAddress = $("input[name='endAddress']").val();//目的地
	var project = $("input[name='project']").val();//项目名称
	var startDate = $("input[name='startDate']").val();//出差开始日期
	var endDate = $("input[name='endDate']").val();//出差结束日期
	var traffic = $("select[name='traffic']").val();//交通工具
	var person = $("input[name='person']").val();//同行人员
	var planDate = $("input[name='planDate']").val();//日期
	var customerName = $("input[name='customerName']").val();//客户名称
	var content = $("input[name='content']").val();//工作内容
	/*var summary = $("textarea[name='summary']").val();//出差总结*/
	var oneCost = $("input[name='costOne']").val();//
	var twoCost = $("input[name='costTwo']").val();//
	var threeCost = $("input[name='costThree']").val();//
	var fourCost = $("input[name='costFour']").val();//
	var allCost = $("input[name='allCost']").val();//
	var exp = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(!exp.test(oneCost)||!exp.test(twoCost)||!exp.test(threeCost)||!exp.test(fourCost)){
		bool=false;
		$("#costWarn").text("费用预算中金额不能留空！且填写正确的金额格式！");
	}
	
	if(startAddress==null||startAddress==undefined||startAddress==""){
		bool=false;
		$("#startAddressWarn").text("请填写出发地");
	}else if(startAddress.length>30){
		bool=false;
		$("#startAddressWarn").text("请填写小于30个字符");
	}
	
	if(endAddress==null||endAddress==undefined||endAddress==""){
		bool=false;
		$("#endAddressWarn").text("请填写目的地");
	}else if(endAddress.length>30){
		bool=false;
		$("#startAddressWarn").text("请填写小于30个字符");
	}	
	if(project==null||project==undefined||project==""){
		bool=false;
		$("#projectWarn").text("请填写项目名称");
	}else if(project.length>30){
		bool=false;
		$("#projectWarn").text("请填写小于30个字符");
	}else{
		if(!project.match("^[a-zA-Z0-9_\u4e00-\u9fa5]+$")){
			bool=false;
			$("#projectWarn").text("请勿输入特殊字符");
			//$("input[name='project']").val("");
			}
		/*project = project.replace(/[\-\_\,\.\!\|\~\`\(\)\#\@\%\-\+\=\/\'\$\%\^\&\*\{\}\:\;\"\L\<\>\?\\]/g, ''); 
		alert(project);*/
	}
	
	if(startDate==null||startDate==undefined||startDate==""){
		bool=false;
		$("#startDateWarn").text("请填写出差开始日期");
	}
	
	if(endDate==null||endDate==undefined||endDate==""){
		bool=false;
		$("#endDateWarn").text("请填写出差结束日期");
	}
	
	if(traffic==null||traffic==undefined||traffic==""){
		bool=false;
		$("#trafficWarn").text("请填写出交通工具");
	}
	
	if(person==null||person==undefined||person==""){
		bool=false;
		$("#personWarn").text("请填写同行人员");
	}
	
	if(planDate==null||planDate==undefined||planDate==""){
		bool=false;
		$("#planDateWarn").text("请填写日期");
	}
	
	if(customerName==null||customerName==undefined||customerName==""){
		bool=false;
		$("#customerNameWarn").text("请填写客户名称");
	}

	if(content==null||content==undefined||content==""){
		bool=false;
		$("#contentWarn").text("请填写工作内容");
	}
	
	/*if(summary==null||summary==undefined||summary==""){
		bool=false;
		$("#summaryWarn").text("请填写工作总结");
	}*/
	if(allCost==null||allCost==undefined||allCost==""||allCost=="0.0"){
		bool=false;
		$("#allCostWarn").text("此处不能为空！请点击“合计”！");
	}
	
	return bool;
}