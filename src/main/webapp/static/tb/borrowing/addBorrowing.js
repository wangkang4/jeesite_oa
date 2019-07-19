$(document).ready(function() {
	
});

var isCommitted=false;//是否已提交
//提交
function save() {
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		isCommitted = true;
		return true;
	}
	return false;
}
//销毁申请
function stop() {
	var bool = true;
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('no');
		return true;
	}
	return false;
}

//正则验证
function yanzhen(bool){
	$(".warn").text("");
	var money = $("input[name='money']").val();
	var reason = $("textarea[name='reason']").val();
	var notes = $("textarea[name='notes'").val();
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(money==undefined||money==null||money==""){
		bool=false;
		$("#moneyWarn").text("请输入借款金额");
	}else if (!reg.test(money)) {
		bool = false;
		$("#moneyWarn").text("格式错误");
	}
	if(reason==undefined||reason==null||reason==""){
		bool=false;
		$("#reasonWarn").text("请输入借款原因");
	}else if(reason.length>200){
		bool=false;
		$("#reasonWarn").text("请输入小于200字符,当前:"+reason.length+"字符");
	}
	if(notes==undefined||notes==null||notes==""){

	}else if(notes.length>200){
		bool=false;
		$("#notesWarn").text("请输入小于200字符,当前:"+notes.length+"字符");
	}
	return bool;
}

