var isCommitted=false;//是否已提交
function save(){
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	$(".warn").text("");
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		committed=true;
		return true;
	}
	return false;
}
function stop(){
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	var office=$("#officeId").val();//部门
	var count = $("input[name='count']").val();//总人数
	var planTime = $("input[name='planTime']").val();//计划时间
	var planCount = $("input[name='planCount']").val();//计划人数
	var availableFunds = $("input[name='availableFunds'").val();//可用经费
	var budget = $("input[name='budget']").val();//本次预算
	var notes = $("textarea[name='notes'").val();//团建方案描述
	var reg1 = /^[0-9]*[1-9][0-9]*$/;
	var reg2 = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(isBlank(office)){
		bool=false;
		$("#officeWarn").text("请选择部门");
	}else if(judgeLength(office,2000)){
		bool=false;
		$("#officeWarn").text("选择部门过多");
	}
	if(isBlank(count)){
		bool=false;
		$("#countWarn").text("请输入总人数")
	}else if(judgeLength(count,4)){
		bool=false;
		$("#countWarn").text("请输入小于4位字符")
	}else if(!reg1.test(count)){
		bool=false;
		$("#countWarn").text("请输入大于0的正整数")
	}
	if(isBlank(planTime)){
		bool=false;
		$("#planTimeWarn").text("请输入计划团建时间")
	}
	if(isBlank(planCount)){
		bool=false;
		$("#planCountWarn").text("请输入计划参加人数")
	}else if(judgeLength(planCount,4)){
		bool=false;
		$("#planCountWarn").text("请输入小于4位字符")
	}else if(!reg1.test(planCount)){
		bool=false;
		$("#planCountWarn").text("请输入大于0的正整数")
	}
	if(isBlank(availableFunds)){
		bool=false;
		$("#availableFundsWarn").text("请输入可用团建经费")
	}else if(!reg2.test(availableFunds)){
		bool=false;
		$("#availableFundsWarn").text("请输入正确格式")
	}
	if(isBlank(budget)){
		bool=false;
		$("#budgetWarn").text("请输入本次团建经费预算")
	}else if(!reg2.test(budget)){
		bool=false;
		$("#budgetWarn").text("请输入正确格式")
	}
	if(isBlank(notes)){
		bool=false;
		$("#notesWarn").text("请输入活动方案描述")
	}else if(judgeLength(notes,2000)){
		bool=false;
		$("#notesWarn").text("请输入小于2000位字符")
	}
	return bool;
	
}
function isBlank(str){
	if(str==undefined||str==null||str==""){
		return true;
	}else if(str.replace(/(^\s*)|(\s*$)/g, "")==""){
		return true;
	}else {
		return false;
	}
}
function judgeLength(str,num){
	if(str.length>num){
		return true;
	}else{
		return false;
	}
}
$(document).ready(function(){
	console.log("%c**************  $$          $$  &&&&&&&       \n" +
				  "**************  $$          $$  &&     &&&    \n" +
				  "      **        $$          $$  &&       &&&  \n" +
				  "      **        $$          $$  &&        &&& \n" +
				  "      **        $$$$$$$$$$$$$$  &&         &&&\n" +
				  "      **        $$$$$$$$$$$$$$  &&         &&&\n" +
				  "      **        $$          $$  &&        &&& \n" +
				  "      **        $$          $$  &&       &&&  \n" +
				  "      **        $$          $$  &&     &&&    \n" +
				  "      **        $$          $$  &&&&&&&       ","color:red");
	
});