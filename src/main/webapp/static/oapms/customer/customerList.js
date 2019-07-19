function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#addForm").submit();
	return false;
}
//增加客户
function toAdd(){
	window.location.href="toAdd";
}
//修改客户
function updateCustomer(btn){
	var customerId = $(btn).next().val();
	url=window.location.href;
	url=url.replace("list", "judgeToUpdate");
	params={"customerId":customerId};
	$.ajax({
		url:url,
		data:params,
		dataType:"json",
		success:function(result){
			if(result.data=="ok"){
				window.location.href="toUpdate?customerId="+customerId;
			}
			if(result.data=="error"){
				alertx("你无权修改！");
			}
		}
	})
}
//删除客户
function deleteCustomer(btn){
	url=window.location.href;
	url=url.replace("list", "deleteCustomer");
	params={"customerId":$(btn).prev().val()};
	$.ajax({
		url:url,
		data:params,
		dataType:"json",
		success:function(result){
			if(result.data=="ok"){
				location.reload();
			}
			if(result.data=="error"){
				alertx("你无权删除！");
			}
		}
	})
}
//查看客户详情
function customerDetail(btn){
	var customerId = $(btn).prev().prev().val()
	url=window.location.href;
	url=url.replace("list", "toCustomerDetail?customerId=");
	url=url+customerId;
	window.location.href = url;
}