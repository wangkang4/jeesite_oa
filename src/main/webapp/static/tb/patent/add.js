$(document).ready(function(){
	var rs = $("textarea[name='reason']").text();
	if($(".table-form tr").length<=5){
		addReceptionStaff()
	}
	if(rs==undefined||rs==null||rs==""){
		
	}else{
		$(".addTr").remove();
		$("#anniu").hide();
	}
});
function addReceptionStaff(){
	
	if($("#test").val()==""){
		alertx("请先选择申请类型！");
	}
	if($("#test").val()=="专利侵权奖励"){
		$(".addTr").hide();
	}else{
		$(".addTr").show();
	}
	var tr = $("<tr class='addTr'></tr>");
	tr.append('<td class="tit">姓名</td>');
	tr.append('<td><input name="person" type="text"><span id="personWarn" class="warn" style="color: red"></span></td>');
	tr.append('<td class="tit">比例</td>');
	tr.append('<td><input name="position" type="text"><span id="positionWarn" class="warn" style="color: red"></span></td>');
	$(".table-form tr:last").after(tr);
}
function delReceptionStaff(){
	if($(".table-form tr").length<=6){
		alertx("至少要有一个人员");
	}else{
		$(".table-form tr").eq(-1).remove();
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
	
	var applyType = $("#test").val();
	var patentName = $("input[name='patentName']").val();
	
	if(applyType==undefined||applyType==null||applyType==""){
		bool=false;
		$("#applyTypeWarn").text("此处不能为空");
	}
	if(patentName==undefined||patentName==null||patentName==""){
		bool=false;
		$("#patentNameWarn").text("此处不能为空");
	}
	
	/*是否为专利侵权验证判断*/
	if($("#test").val()!="专利侵权奖励"){
		var applyTel = $("input[name='applyTel']").val();
		var person = $("input[name='person']").val();
		var position = $("input[name='position']").val();
		if(applyTel==undefined||applyTel==null||applyTel==""){
			bool=false;
			$("#applyTelWarn").text("此处不能为空");
		}
		if(person==undefined||person==null||person==""){
			bool=false;
			$("#personWarn").text("此处不能为空");
		}
		if(position==undefined||position==null||position==""){
			bool=false;
			$("#positionWarn").text("此处不能为空");
		}
	}else{
		var reason = $("textarea[name='reason']").val();
		var money = $("input[name='money']").val();
		if(reason==undefined||reason==null||reason==""){
			bool=false;
			$("#reasonWarn").text("此处不能为空");
		}else if(reason.length>2000){
			bool=false;
			$("#reasonWarn").text("请输入小于2000个字符");
		}
		var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
		if(money==undefined||money==null||money==""){
			bool=false;
			$("#moneyWarn").text("请输入总计金额");
		}else if(!reg.test(money)){
			bool=false;
			$("#moneyWarn").text("请输入正确格式");
		}
	}
	
	return bool;
}