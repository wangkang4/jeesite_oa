$(document).ready(function(){
	start();
	getType();
	
});
function getType(){
	url=window.location.href;
	url=url.replace("toAdd", "getChapterType");
	var chapterType1 = $("#chapterType1").val();
	var fileType1 = $("#fileType1").val();
	var chapterType = chapterType1.split(",");
	var fileType = fileType1.split(",");
	$.ajax({
		url:"getChapterType",
		dataType:"json",
		success:function(result){
			var chapterList = result.chapterType;
			console.log(chapterList);
			for(var i=0;i<chapterType.length;i++){
				for(var j=0;j<chapterList.length;j++){
					if(chapterType[i]==chapterList[j].id){
						$("#chapterType").append("<span style='white-space:pre'>"+chapterList[j].type+"    "+"</span>");
					}
				}
			}
			var fileList = result.fileType;
			for(var i=0;i<fileType.length;i++){
				for(var j=0;j<fileList.length;j++){
					if(fileType[i]==fileList[j].id){
						$("#fileType").append("<span style='white-space:pre'>"+fileList[j].type+"    "+"</span>");
					}
				}
			}
		}
	})
}
function start() {
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
}


