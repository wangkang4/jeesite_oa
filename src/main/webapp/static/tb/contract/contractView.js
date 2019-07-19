$(document).ready(
		function() {
			check();
		});
function check(){
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	if(url.indexOf("form?act")>=0){
		if(statu=="审核通过"){
			
		}else{
		$("#btnCancel").before('<input class="btn" type="button" value="回退" onclick="rollback()">');
		}
	}
}
function rollback(){
	var msg="你确定要回退到自己节点吗";
	if(confirm(msg)==true){
		var url = window.location.href;
		var ele = url.split("&");
		var task = ele[0].split("=");
		var taskId = task[1];
		url = url.substr(0,url.indexOf("tb/"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}
function withdraw(){
	var id = $("input[name='contractId']").val();
	$.ajax({
		url:"withdraw",
		dataType:"json",
		data:{"id":id},
		success:function(result){
			if(result.data=="error"){
				alertx("已审批，无法撤回");
			}
			if(result.data=="ok"){
				window.location.href="list";
				alertx("撤回成功");
			}
		}
	});
}
function abandoned(){
	var id = $("input[name='contractId']").val();
	var abandon = $("textarea[name='abandon']").val();
	if(abandon==null||abandon==undefined||abandon==""){
		alertx("请填写作废原因");
	}else{
		$.ajax({
			url:"abandon",
			dataType:"json",
			data:{"id":id,"abandon":abandon},
			success:function(result){
				window.location.href="list";
			}
		});
	}
}