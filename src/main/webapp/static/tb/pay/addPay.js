var blist = [];
var slist = [];
$(document).ready(function(){
	changePay();
	getContract();//获取关联合同
	$.ajax({
		url:"selectType",
		dataType:"json",
		success:function(data){
			blist = data.blist;
			slist = data.slist;
			var bigType = $("input[name='bigType']").val();
			var smallType = $("input[name='smallType']").val();
			if(bigType!=null&&bigType!=undefined&&bigType!=""
				&&smallType!=null&&smallType!=undefined&&smallType!=""){
				defaultChange(bigType,smallType);
			}else{
				for(var i in blist){
					$("select[name='payTypeBig']")
					.append('<option value="'+blist[i].id+'">'+blist[i].type+'</option>');
				}
			}
		}
	})
});
var contractList="";
function getContract(){
	$.ajax({
		url:"getContract",
		dataType:"json",
		success:function(data){
			contractList = data.contract;
			var contractId = $("#contractId").val();
			for(var i in contractList){
				if(contractId==contractList[i].id){
					$("select[name='contractId']").append("<option value='"+contractList[i].id+"' selected>"+contractList[i].contractName+"</option>");
					$(".select2-chosen").eq(0).text(contractList[i].contractName);
				}else{
					$("select[name='contractId']").append("<option value='"+contractList[i].id+"'>"+contractList[i].contractName+"</option>");
				}
			}
		}
	});
}
//当关联合同选项改变时合同名称和合同标题自动改变
function chooseContract(){
	var contractId = $("select[name='contractId']").val();
	console.log(contractList);
	for(var i in contractList){
		if(contractList[i].id==contractId){
			$("input[name='projectName']").val(contractList[i].contractName);
			$("input[name='projectNum']").val(contractList[i].contractNum);
		}
	}
}
function defaultChange(bigType,smallType){
	for(var i in blist){
		if(bigType==blist[i].id){
			$("select[name='payTypeBig']")
			.append('<option selected value="'+blist[i].id+'">'+blist[i].type+'</option>');
			$(".select2-chosen").eq(2).text(blist[i].type);
		}else{
			$("select[name='payTypeBig']")
			.append('<option value="'+blist[i].id+'">'+blist[i].type+'</option>');
		}
	}
	var type = $("select[name='payTypeBig'] option:selected").val();
	for(var j in slist){
		if(type==slist[j].pid){
			if(smallType==slist[j].id){
				$("select[name='payTypeSmall']")
				.append('<option selected value="'+slist[j].id+'">'+slist[j].type+'</option>');
				$(".select2-chosen").eq(2).text(slist[j].type);
			}else{
				$("select[name='payTypeSmall']")
				.append('<option value="'+slist[j].id+'">'+slist[j].type+'</option>');
			}
		}
	}
	$("select[name='payTypeSmall']").val(smallType);
}
function changeType(){
	var type = $("select[name='payTypeBig'] option:selected").val();
	$("select[name='payTypeSmall']").empty();
	$("select[name='payTypeSmall']")
	.append('<option value="">请选择</option>');
	$(".select2-chosen").eq(2).text("请选择");
	for(var j in slist){
		if(type==slist[j].pid){
			$("select[name='payTypeSmall']")
			.append('<option value="'+slist[j].id+'">'+slist[j].type+'</option>')
		}
	}
}
function changePay(){
	var payMethods = $("select[name='payMethods']").val();
	if(payMethods=="2"){
		$("input[name='payeeAccount']").prop("type","text");
	}else{
		$("input[name='payeeAccount']").val("");
		$("input[name='payeeAccount']").prop("type","hidden");
	}
}
function upload(){
	var file = $("input[name='applyAddress']")
	var formData = new FormData();
	formData.append("file",$('#file1')[0].files[0]);
	var xhr;
	if(window.XMLHttpRequest){
        xhr = new XMLHttpRequest();
    }else{
        xhr = new ActiveXObject('Microsoft.XMLHTTP');
    }
	//进度条部分
	xhr.upload.onprogress = function (evt) {
		if (evt.lengthComputable) {
			var percentComplete = Math.round(evt.loaded * 100 / evt.total);
			document.getElementById('progress').value = percentComplete;
		}
	};
	xhr.open("post","uploadfile",true);
	xhr.send(formData);
	xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = xhr.responseText;
            if(result!=null&&result!=""&&result!=undefined){
            	file.val(result);
            	alertx("上传成功");
            }else{
            	alertx("上传失败");
            }
        }
    }
}
var isCommitted=false;//是否已提交
function save() {
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	$(".warn").text("");
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		isCommitted = true;
		return true;
	}
	return false;
}
function stop() {
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	var projectName = $("input[name='projectName']").val();
	var projectNum = $("input[name='projectNum'").val();
	var money = $("input[name='money']").val();
	var projectDate = $("input[name='projectDate']").val();
	var payMoney = $("input[name='payMoney']").val();
	var lastTime = $("input[name='lastTime']").val();
	var payTypeBig = $("select[name='payTypeBig']").val();
	var payTypeSmall = $("select[name='payTypeSmall']").val();
	var payMethods = $("select[name='payMethods']").val();
	var amountPaid = $("input[name='amountPaid']").val();
	var payeeName = $("input[name='payeeName']").val();
	var payeeAccount = $("input[name='payeeAccount']").val();
	var notes = $("textarea[name='notes']").val();
	if(projectName==undefined||projectName==null||projectName==""){
		bool=false;
		$("#projectNameWarn").text("请输入合同名称");
	}
	if(projectNum==undefined||projectNum==null||projectNum==""){
		bool=false;
		$("#projectNumWarn").text("请输入合同编号");
	}
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(!reg.test(money)){
		bool=false;
		$("#moneyWarn").text("请输入正确格式");
	}
	if(money==undefined||money==null||money==""){
		bool=false;
		$("#moneyWarn").text("请输入合同总金额");
	}
	if(projectDate==undefined||projectDate==null||projectDate==""){
		bool=false;
		$("#projectDateWarn").text("请填写合同签订日期");
	}
	if(!reg.test(money)){
		bool=false;
		$("#payMoneyWarn").text("请输入正确格式");
	}
	if(payMoney==undefined||payMoney==null||payMoney==""){
		bool=false;
		$("#payMoneyWarn").text("请输入本次应付款金额");
	}
	if(lastTime==undefined||lastTime==null||lastTime==""){
		bool=false;
		$("#lastTimeWarn").text("请填写最晚付款日期");
	}
	if(payTypeBig==undefined||payTypeBig==null||payTypeBig==""){
		bool=false;
		$("#payTypeBigWarn").text("请填写付款类别");
	}
	if(payTypeSmall==undefined||payTypeSmall==null||payTypeSmall==""){
		bool=false;
		$("#payTypeSmallWarn").text("请填写付款类型");
	}
	if(payMethods==undefined||payMethods==null||payMethods==""){
		bool=false;
		$("#payMethodsWarn").text("请填写付款方式");
	}
	if(!reg.test(amountPaid)){
		bool=false;
		$("#amountPaidWarn").text("请输入正确格式");
	}
	if(amountPaid==undefined||amountPaid==null||amountPaid==""){
		bool=false;
		$("#amountPaidWarn").text("请输入已付款金额");
	}
	if(payeeName==undefined||payeeName==null||payeeName==""){
		bool=false;
		$("#payeeNameWarn").text("请输入收款人名称");
	}
	if(payMethods=="2"){
		if(payeeAccount==undefined||payeeAccount==null||payeeAccount==""){
			bool=false;
			$("#payeeAccountWarn").text("请输入收款人账户");
		}
	}
	if(notes==undefined||notes==null||notes==""){
		bool=false;
		$("#notesWarn").text("请填写付款原因");
	}
	else if(notes.size>200){
		bool=false;
		$("#notesWarn").text("请填写小于200个字");
	}
	return bool;
}