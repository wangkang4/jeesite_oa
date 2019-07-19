$(document).ready(function(){
	if($(".table-form tr").length<=6){
		addReceptionStaff()
	}
});
function addReceptionStaff(){
	var tr = $("<tr></tr>");
	tr.append('<td class="tit">被招待人员</td>');
	tr.append('<td><input name="person" type="text"><span class="warn" style="color: red"></span></td>');
	tr.append('<td class="tit">职务</td>')
	tr.append('<td><input name="position" type="text"></td>');
	$(".table-form tr:last").before(tr);
}
function delReceptionStaff(){
	if($(".table-form tr").length<=5){
		alertx("至少要有一个被招待人员");
	}else{
		$(".table-form tr").eq(-2).remove();
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
	var tbDate = $("input[name='tbDate']").val();
	var persons = $("input[name='persons']").val();
	var place = $("input[name='place']").val();
	var money = $("input[name='money']").val();
	var person = $("input[name='person']");
	var notes = $("textarea[name='notes']").val();
	if(tbDate==undefined||tbDate==null||tbDate==""){
		bool=false;
		$("#tbDateWarn").text("请填写申请日期");
	}
	if(persons==undefined||persons==null||persons==""){
		bool=false;
		$("#personsWarn").text("请填写陪同人员");
	}
	if(place==undefined||place==null||place==""){
		bool=false;
		$("#placeWarn").text("请填写用餐地点");
	}
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(!reg.test(money)){
		bool=false;
		$("#moneyWarn").text("请输入正确格式");
	}
	if(money==undefined||money==null||money==""){
		bool=false;
		$("#moneyWarn").text("请输入用餐预估金额");
	}
	person.each(function(){
		if($(this).val()==undefined||$(this).val()==null||$(this).val()==""){
			bool=false;
			$(this).next().text("请输入被招待人员名单");
		}
	});
	if(notes==undefined||notes==null||notes==""){
		bool=false;
		$("#notesWarn").text("请填写招待事由");
	}
	else if(notes.size>200){
		bool=false;
		$("#notesWarn").text("请填写小于200个字");
	}
	return bool;
}