$(document).ready(
			function() {
				$("#name").focus();
				typeText();
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
function typeText(){
	var payTypeBig = $("input[name='payTypeBig']").val();
	var payTypeSmall = $("input[name='payTypeSmall']").val();
	$.ajax({
		url:"selectType",
		dataType:"json",
		success:function(data){
			blist = data.blist;
			slist = data.slist;
			for(var i in blist){
				if(blist[i].id==payTypeBig){
					$("input[name='payTypeBig']").parent().append(blist[i].type);
				}
			}
			for(var i in slist){
				if(slist[i].id==payTypeSmall){
					$("input[name='payTypeSmall']").parent().append(slist[i].type);
				}
			}
		}
	})
}