$(document).ready(function(){
});

var isCommitted=false;//是否已提交
//提交
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
	var title = $("input[name='title']").val();
	var useDate = $("input[name='useDate']").val();
	var returnDate = $("input[name='returnDate']").val();
	var type = $("select[name='type']").val();
	var notes = $("textarea[name='notes']").val();
	if(useDate==undefined||useDate==null||useDate==""){
		bool=false;
		$("#useDateWarn").text("请填写使用日期");
	}
	if(returnDate==undefined||returnDate==null||returnDate==""){
		bool=false;
		$("#returnDateWarn").text("请填写归还日期");
	}
	if(type==""){
		bool=false;
		$("#typeWarn").text("请选择证件种类");
	}
	if(notes==undefined||notes==null||notes==""){
		bool=false;
		$("#notesWarn").text("请填写证件用途");
	}
	else if(notes.length>200){
		bool=false;
		$("#notesWarn").text("请填写小于200个字");
	}
	return bool;
}