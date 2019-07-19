$(document).ready(function(){
});

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
		committed=true;
		return true;
	}
	return false;
}
function stop() {
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	var position = $("input[name='position']").val();
	var reason = $("textarea[name='reason']").val();
	
	if(position==undefined||position==null||position==""){
		bool=false;
		$("#positionWarn").text("此处不能为空");
	}
	
	if(reason==undefined||reason==null||reason==""){
		bool=false;
		$("#reasonWarn").text("此处不能为空");
	}
	
	return bool;
}