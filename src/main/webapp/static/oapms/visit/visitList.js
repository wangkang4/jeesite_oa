//删除拜访记录
function deleteVisit(btn){
	url=window.location.href;
	url=url.replace("list", "deleteVisit");
	visitId = $(btn).prev().val();
	params={"visitId":visitId};
	$.get(url,params,function(result){
		if(result.data=="ok"){
			alertx("删除成功！");
			window.location.href=url=url.replace("deleteVisit", "list");
		}
		if(result.data=="error"){
			alertx("只有添加人可以删除！");
		}
	})
}
function toAddVisit(){
	var url = window.location.href;
	url = url.replace("list","toAdd");
	window.location.href=url;
}
