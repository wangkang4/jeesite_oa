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
	var signetName = $("input[name='signetName']").val();
	var notes = $("textarea[name='notes']").val();
	if(signetName==undefined||signetName==null||signetName==""){
		bool=false;
		$("#signetNameWarn").text("请输入要刻制的印章名称");
	}
	if(signetName.length>16){
		bool=false;
		$("#signetNameWarn").text("刻制印章名称不得超过16个字");
	}
	if(notes==undefined||notes==null||notes==""){
		bool=false;
		$("#notesWarn").text("请填写申请刻制原因");
	}
	else if(notes.length>200){
		bool=false;
		$("#notesWarn").text("请填写小于200个字");
	}
	return bool;
}