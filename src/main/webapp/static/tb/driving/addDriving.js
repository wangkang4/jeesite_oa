$(document).ready(function() {
	
});

function generateCost(){
	var estimatedMiles = $("input[name='estimatedMiles']").val();
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	var reg1 = /(^[1-9]([0-9]+)?(\.[0-9]{1})?$)|(^(0){1}$)|(^[0-9]\.[0-9]$)/;
	if(estimatedMiles==null||estimatedMiles==undefined||estimatedMiles==""){
		return;
	}
	if(reg.test(estimatedMiles)){
		var estimatedCost = estimatedMiles;
		$("input[name='estimatedCost']").val(estimatedCost);
	}
}

var isCommitted=false;//是否已提交
//提交
function save() {
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		committed=true;
		return true;
	}
	return false;
}
//销毁申请
function stop() {
	var bool = true;
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('no');
		return true;
	}
	return false;
}


//正则验证
function yanzhen(bool){
	$(".warn").text("");
	var peer = $("input[name='peer']").val();//同行人员
	var transportTime = $("input[name='transportTime']").val();//用车时间
	var origin = $("input[name='origin']").val();//出发地
	var destination = $("input[name='destination']").val();//目的地
	var estimatedMiles = $("input[name='estimatedMiles']").val();//预计公里数
	var gasoline = $("input[name='gasoline']").val();//油价
	var estimatedCost = estimatedMiles;
	estimatedCost = estimatedCost.toFixed(2);
	$("input[name='estimatedCost']").val(estimatedCost);//预估费用
	var reason = $("textarea[name='reason']").val();//自驾车原因
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	var reg1 = /(^[1-9]([0-9]+)?(\.[0-9]{1})?$)|(^(0){1}$)|(^[0-9]\.[0-9]$)/;
	if(peer==null||peer==undefined||peer==""){
		bool=false;
		$("#peerWarn").text("请填写同行人员");
	}else if(peer.length>30){
		bool=false;
		$("#peerWarn").text("请填写小于30个字符");
	}
	if(transportTime==null||transportTime==undefined||transportTime==""){
		bool=false;
		$("#transportTimeWarn").text("请填写用车时间");
	}
	if(origin==null||origin==undefined||origin==""){
		bool=false;
		$("#originWarn").text("请填写出发地名称");
	}else if(origin.length>30){
		bool=false;
		$("#originWarn").text("请填写小于30个字符");
	}
	if(destination==null||destination==undefined||destination==""){
		bool=false;
		$("#destinationWarn").text("请填写目的地名称");
	}else if(destination.length>30){
		bool=false;
		$("#destinationWarn").text("请填写小于30个字符");
	}
	if(estimatedMiles==null||estimatedMiles==undefined||estimatedMiles==""){
		bool=false;
		$("#estimatedMilesWarn").text("请填写预计公里数");
	}else if(!reg1.test(estimatedMiles)){
		bool=false;
		$("#estimatedMilesWarn").text("格式错误");
	}else if(estimatedMiles.length>8){
		bool=false;
		$("#estimatedMilesWarn").text("请填写小于8个字符");
	}
	if(gasoline==null||gasoline==undefined||gasoline==""){
		bool=false;
		$("#gasolineWarn").text("请填写预计公里数");
	}else if(!reg.test(gasoline)){
		bool=false;
		$("#gasolineWarn").text("格式错误");
	}else if(gasoline.length>8){
		bool=false;
		$("#gasolineWarn").text("请填写小于8个字符");
	}
	
	return bool;
}
//追加提示
function appendHint(){
	
}
