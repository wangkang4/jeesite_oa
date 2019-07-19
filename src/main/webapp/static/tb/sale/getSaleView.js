$(document).ready(
		function() {
			check();
			$("#name").focus();
			$("#inputForm")
					.validate(
							{
								submitHandler : function(form) {
									loading('正在提交，请稍等...');
									form.submit();
								},
								errorContainer : "#messageBox",
								errorPlacement : function(error, element) {
									$("#messageBox").text("输入有误，请先更正。");
									if (element.is(":checkbox")
											|| element.is(":radio")
											|| element.parent().is(
													".input-append")) {
										error.appendTo(element.parent()
												.parent());
									} else {
										error.insertAfter(element);
									}
								}
							});
		});
function check(){
	var url = window.location.href;
	var statu = $("input[name='statu']").val();
	var user1 = $("input[name='user1']").val();
	var user2 = $("input[name='user2']").val();
	if(url.indexOf("form?id=")>=0){
		if(user1==user2){
			$("#btnCancel").before('<input class="btn" type="button" value="撤回" onclick="withdraw()">');
		}
	}else if(statu=="已付款"){
		
	}else{
		$("#btnCancel").before('<input class="btn" type="button" value="回退" onclick="rollback()">');
	}
}
function withdraw(){
	var id = $("input[name='getSaleId']").val();
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
function rollback(){
	var msg="你确定要回退到自己节点吗";
	if(confirm(msg)==true){
		var url = window.location.href;
		alert(url)
		var ele = url.split("&");
		var task = ele[0].split("=");
		var taskId = task[1];
		url = url.substr(0,url.indexOf("get/sale"))
		window.location = url+"tb/roll/back?taskId="+taskId;
	}else{
		
	}
}