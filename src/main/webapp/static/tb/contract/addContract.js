$(document).ready(function() {
	var friendId = $("input[name='friend']").val();
	if(friendId!=null&&friendId!=undefined&&friendId!=""){
		getFriend(friendId);
	}
	var contractNum = $("input[name='contractNum']").val();
	if(contractNum!=null&&contractNum!=undefined&&contractNum!=""){
		var con = contractNum.split("-");
		if("XS"==con[1]){
			$("select[name='type']").val("XS");
			$(".select2-chosen").eq(1).text("销售类合同");
		}else if("YW"==con[1]){
			$("select[name='type']").val("YWCG");
			$(".select2-chosen").eq(1).text("业务采购类合同");
		}else if("ZC"==con[1]){
			$("select[name='type']").val("ZCCG");
			$(".select2-chosen").eq(1).text("资产采购类合同");
		}else if("QT"==con[1]){
			$("select[name='type']").val("QT");
			$(".select2-chosen").eq(1).text("其他类合同");
		}
		console.log($(".select2-chosen").length);
	}
});
function getFriend(friendId){
	$("#xiaosou").find("td").eq(0).attr("style","");
	$("#xiaosou").find("td").eq(1).attr("style","");
	$.ajax({
		url:"associated",
		dataType:"json",
		success:function(data){
			var list = data.list;
			if(list!=null&&list.length>0){
				for(var i=0;i<list.length;i++){
					if(list[i].id==friendId){
						$("#xiaosou").find("select").append("<option value="+list[i].id+" selected>"+list[i].contractName+"</option>");
						$(".select2-chosen").eq(1).text(list[i].contractName);
						console.log(list[i].contractName);
						console.log($(".select2-chosen").eq(1).text());
					}else{						
						$("#xiaosou").find("select").append("<option value="+list[i].id+">"+list[i].contractName+"</option>");
					}
				}
			}
		}
	})
}
function upload(){
	var file = $("input[name='address']")
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
	xhr.open("post","upload",true);
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
//对方公司名称填写完成后调用，获取首字母大写
function getPY(){
		$("input[name='PY']").remove();
		getZM();
}
function getZM(){
		var companyName = $("input[name='companyName']").val();
		if(companyName!=null&&companyName!=undefined&&companyName!=""){
			$.ajax({
				url:"getPY",
				type:"post",
				data:{"companyName":companyName},
				dataType:"json",
				success:function(data){
					$("input[name='companyName']").after("<input type='text' value='"+data.PY+"' name='PY'>");
				}
			})
		}
}
//修改合同类别时调用
function changeContractType(){
	var contractType = $("#contractType").val();//合同类别
	if(contractType==1){//如果为采购需要选择对应的销售
		$("#xiaosou").find("td").eq(0).attr("style","");
		$("#xiaosou").find("td").eq(1).attr("style","");
		$.ajax({
			url:"associated",
			dataType:"json",
			success:function(data){
				var list = data.list;
				if(list!=null&&list.length>0){
					for(var i=0;i<list.length;i++){
						$("#xiaosou").find("select").append("<option value="+list[i].id+">"+list[i].contractName+"</option>");
					}
				}
			}
		})
	}else{
		$("#xiaosou").find("td").eq(0).attr("style","display:none");
		$("#xiaosou").find("td").eq(1).attr("style","display:none");
		$("#xiaosou").find("select").remove();
		$("#xiaosou").find("select").append("<option value=''>请选择</option>")
	}
}
//正则验证
function yanzhen(bool){
	$(".warn").text("");
	var manager = $("#managerName").val()//项目经理
	var area = $("#areaName").val();//所属办事处
	var contractType = $("#contractType").val();//合同类别
	var friendId = $("select[name='friendId']").val();//合同类别
//	var contractNum = $("input[name='contractNum']").val();//合同编号
	var type = $("select[name='type']").val();
	var contractName = $("input[name='contractName']").val();//合同名称
	var money = $("input[name='money']").val();//合同金额
	var companyName = $("input[name='companyName']").val();//对方公司名称
	var contractLimit = $("input[name='contractLimit']").val();//合同期限
	var paymentCycle = $("input[name='paymentCycle']").val();//支付比例
	var project = $("input[name='project']").val();//项目信息
	var maintenance = $("input[name='maintenance']").val();//维保期限
	var cooperationContent = $("textarea[name='cooperationContent']").val();//主要合作内容
	var grossMargin = $("textarea[name='grossMargin']").val();//毛利及毛利率
	var settlement = $("textarea[name='settlement']").val();//结算方式
	var responsibility = $("textarea[name='responsibility']").val();//违约责任
	var other = $("textarea[name='other']").val();//其他
	
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(area==undefined||area==null||area==""){
		bool=false;
		$("#areaWarn").text("请选择合同所属办事处");
	}
	if(contractType==undefined||contractType==null||contractType==""){
		bool=false;
		$("#contractTypeWarn").text("请选择合同类别");
	}
	if(contractType==1){
		if(friendId==undefined||friendId==null||friendId==""){
			bool=false;
			$("#friendIdWarn").text("请选择对应的销售合同");
		}
	}
//	if(contractNum==undefined||contractNum==null||contractNum==""){
//		bool=false;
//		$("#contractNumWarn").text("请输入合同编号");
//	}
	if(type==undefined||type==null||type==""){
		bool=false;
		$("#contractNumWarn").text("请选择合同编号第二部分");
	}
	if(contractName==undefined||contractName==null||contractName==""){
		bool=false;
		$("#contractNameWarn").text("请输入合同名称");
	}else if(contractName.length>250){
		bool=false;
		$("#contractNameWarn").text("请输入小于250个字符");
	}
	
	if(companyName==undefined||companyName==null||companyName==""){
		bool=false;
		$("#companyNameWarn").text("请输入对方公司名称");
	}else if(companyName.length>64){
		bool=false;
		$("#companyNameWarn").text("请输入小于64个字符");
	}
	
	if(cooperationContent==undefined||cooperationContent==null||cooperationContent==""){
		bool=false;
		$("#cooperationContentWarn").text("请输入主要合作内容");
	}else if(cooperationContent.length>2000){
		bool=false;
		$("#cooperationContentWarn").text("请输入小于2000字符,当前:"+cooperationContent.length+"字符");
	}
	if(contractType!=2){
		if(money==undefined||money==null||money==""){
			bool=false;
			$("#moneyWarn").text("请输入合同金额");
		}else if (!reg.test(money)) {
			bool = false;
			$("#moneyWarn").text("格式错误");
		}
		if(grossMargin==undefined||grossMargin==null||grossMargin==""){
			bool=false;
			$("#grossMarginWarn").text("请输入毛利率及毛利率情况");
		}else if(grossMargin.length>2000){
			bool=false;
			$("#grossMarginWarn").text("请输入小于2000字符,当前:"+grossMargin.length+"字符");
		}
		if(settlement==undefined||settlement==null||settlement==""){
			bool=false;
			$("#settlementWarn").text("请输入结算方式");
		}else if(settlement.length>2000){
			bool=false;
			$("#settlementWarn").text("请输入小于2000字符,当前:"+settlement.length+"字符");
		}
		if(responsibility==undefined||responsibility==null||responsibility==""){
			bool=false;
			$("#responsibilityWarn").text("请输入违约责任");
		}else if(responsibility.length>2000){
			bool=false;
			$("#responsibilityWarn").text("请输入小于2000字符,当前:"+responsibility.length+"字符");
		}
	}else{//其他类别的合同金额，毛利，结算方式可以不填
		if(grossMargin==undefined||grossMargin==null||grossMargin==""){
			
		}else if(grossMargin.length>2000){
			bool=false;
			$("#grossMarginWarn").text("请输入小于2000字符,当前:"+grossMargin.length+"字符");
		}
		if(settlement==undefined||settlement==null||settlement==""){
			
		}else if(settlement.length>2000){
			bool=false;
			$("#settlementWarn").text("请输入小于2000字符,当前:"+settlement.length+"字符");
		}
		if(responsibility==undefined||responsibility==null||responsibility==""){

		}else if(responsibility.length>2000){
			bool=false;
			$("#responsibilityWarn").text("请输入小于2000字符,当前:"+responsibility.length+"字符");
		}
	}
	if(contractLimit==undefined||contractLimit==null||contractLimit==""){
		bool=false;
		$("#contractLimitWarn").text("请输入合同期限");
	}else if(!reg.test(contractLimit)){
		bool=false;
		$("#contractLimitWarn").text("请输入正整数");
	}
	
	if(paymentCycle==undefined||paymentCycle==null||paymentCycle==""){
		bool=false;
		$("#paymentCycleWarn").text("请输入支付比例");
	}else if(paymentCycle.length>250){
		bool=false;
		$("#paymentCycleWarn").text("请输入小于250个字符");
	}
	if(project==undefined||project==null||project==""){
		bool=false;
		$("#projectWarn").text("请输入项目信息");
	}
	if(maintenance==undefined||maintenance==null||maintenance==""){
		bool=false;
		$("#maintenanceWarn").text("请输入维保期限");
	}else if(!reg.test(maintenance)){
		bool=false;
		$("#maintenanceWarn").text("请输入正确格式");
	}
	if(other==undefined||other==null||other==""){
		
	}else if(other.length>2000){
		bool=false;
		$("#otherWarn").text("请输入小于2000字符,当前:"+other.length+"字符");
	}
	
	return bool;
}
//追加提示
function appendHint(){
	
}
