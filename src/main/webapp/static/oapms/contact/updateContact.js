$(document).ready(function(){
	
});
function updateContact(){
	var bool = true;
	$("font").empty();
	var contactName = $("input[name='customerContactName']").val().replace(/(^\s*)|(\s*$)/g, '');
	var codeName = $("input[name='codeName']").val().replace(/(^\s*)|(\s*$)/g, '');
	var customerId = $("#customerId").val();
	var phone = $("input[name='phone']").val().replace(/(^\s*)|(\s*$)/g, '');
	var weixin = $("input[name='weixin']").val().replace(/(^\s*)|(\s*$)/g, '');
	var email = $("input[name='email']").val().replace(/(^\s*)|(\s*$)/g, '');
	var position = $("input[name='position']").val().replace(/(^\s*)|(\s*$)/g, '');
	var interest = $("input[name='interest']").val().replace(/(^\s*)|(\s*$)/g, '');
	var customerCharacter = $("input[name='customerCharacter']").val().replace(/(^\s*)|(\s*$)/g, '');
	var note = $("#note").val().replace(/(^\s*)|(\s*$)/g, '');
	if(contactName==""||contactName==undefined||contactName==null){
		bool=false;
		$("#contactNameWarn").text("请输入姓名");
	}else if(contactName.length>=10){
		bool=false;
		$("#contactNameWarn").text("请输入小于10个字符");
	}
	if(codeName.length>=10){
		bool=false;
		$("#codeNameWarn").text("请输入小于10个字符");
	}
	if(customerId==""||customerId==undefined||customerId==null){
		bool=false;
		$("#customerIdWarn").text("请选择客户");
	}
	var phonereg=/^[1][0-9]{10}$/;  
	if(phone!=""&&phone!=undefined&&phone!=null&&!phonereg.test(phone)){
		bool=false;
		$("input[name='phone']").next().children().text("手机号码格式不对");
	}
	if(weixin.length>=20){
		bool=false;
		$("#weixinWarn").text("请输入小于20个字符");
	}
	var emailreg= /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,5}$/;
	if(email!=""&&email!=undefined&&email!=null&&!emailreg.test(email)){
		bool=false;
		$("input[name='email']").next().children().text("邮箱格式不对");
	}
	if(position.length>=20){
		bool=false;
		$("#positionWarn").text("请输入小于20个字符");
	}
	if(interest.length>=200){
		bool=false;
		$("#interestWarn").text("请输入小于200个字符");
	}
	if(customerCharacter.length>=200){
		bool=false;
		$("#customerCharacterWarn").text("请输入小于200个字符");
	}
	if(note!=""&&note!=undefined&&note!=null&&note.length>200){
		bool=false;
		$("#note").next().children().text("请输入小于200个字符");
	}
	if(bool==true){
		$("#updateContact").submit();
	}
}