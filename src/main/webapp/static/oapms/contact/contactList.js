$(document).ready(function(){
	if("1"==$("#thinkgem").val()){
		$("#addForm").append("<input type='button' class='btn btn-primary' onclick='downContact()' value='导出'>");
	}
});
function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#addForm").submit();
	return false;
}
//修改联系人
function updateContact(btn){
	var customerContactId = $(btn).next().val();
	window.location.href="toUpdate?customerContactId="+customerContactId;
}
//删除联系人
function deleteContact(btn){
	url=window.location.href;
	url = url.split("?")[0];
	url=url.replace("list", "deleteContact?customerContactId=");
	customerContactId=$(btn).prev().val();
	window.location.href = url+customerContactId;
}
//查看联系人详情
function contactDetail(btn){
	var customerContactId = $(btn).prev().prev().val()
	url=window.location.href;
	url = url.split("?")[0];
	url=url.replace("list", "toContactDetail?customerContactId=");
	url=url+customerContactId;
	window.location.href = url;
}