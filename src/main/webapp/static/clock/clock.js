$(document).ready(function(){
	
});
function page(n, s) {
	$("#pageNo").val(n);
	$("#pageSize").val(s);
	$("#searchForm").submit();
	return false;
}


function syn(){
	var url = window.location.href;
	url = url.replace("clock/checkin/checkin","wechat/token/sign");
	$.ajax({
		url:url,
		data:{"type":"sign"},
		dataType:"json",
		type:"post",
		success:function(data){
			if(data.result=="success"){
				window.location.reload(true);
			}else{
				alertx("已同步最新数据");
			}
		}
	})
}