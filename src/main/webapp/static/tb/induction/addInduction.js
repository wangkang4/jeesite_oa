function upload(sign){
	var file;
	var formData = new FormData();
	var progress;
	if(sign==1){
		file = $("input[name='fileOneAddress']")
		formData.append("file",$('#file1')[0].files[0]);
		progress="progress1";
	}else if(sign==2){
		file = $("input[name='fileTwoAddress']")
		formData.append("file",$('#file2')[0].files[0]);
		progress="progress2";
	}
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
			document.getElementById(progress).value = percentComplete;
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
function save(){
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	$(".warn").text("");
	bool = yanzhen(bool);
	if (bool) {
		$('#flag').val('yes');
		committed=true;
		return true;
	}
	return false;
}
function stop(){
	$('#flag').val('no');
	return true;
}
function yanzhen(bool){
	var applyAddress = $("#applyAddressName").val();
	var applyOffice = $("#applyOfficeName").val();
	var interviewDate = $("input[name='interviewDate']").val();
	var interviewAddress = $("input[name='interviewAddress']").val();
	var employedName = $("input[name='employedName']").val();
	var trialMoney = $("input[name='trialMoney']").val();
	var positiveMoney = $("input[name='positiveMoney']").val();
	var employedOffice = $("#employedOfficeName").val();
	var employedJob = $("input[name='employedJob']").val();
	var jobLevel = $("input[name='jobLevel']").val();
	var workDate = $("input[name='workDate']").val();
	var phone = $("input[name='phone']").val();
	var positiveDate = $("input[name='positiveDate']").val();
	var contractSignedDate = $("input[name='contractSignedDate']").val();
	var contractStartDate = $("input[name='contractStartDate']").val();
	var contractEndDate = $("input[name='contractEndDate']").val();
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	if(applyAddress==null||applyAddress==undefined||applyAddress==""){
		bool=false;
		$("#applyAddressWarn").text("请输入申请地点");
	}if(applyOffice==null||applyOffice==undefined||applyOffice==""){
		bool=false;
		$("#applyOfficeWarn").text("请输入申请部门");
	}if(interviewDate==null||interviewDate==undefined||interviewDate==""){
		bool=false;
		$("#interviewDateWarn").text("请输入面试日期");
	}if(interviewAddress==null||interviewAddress==undefined||interviewAddress==""){
		bool=false;
		$("#interviewAddressWarn").text("请输入面试地点");
	}if(employedName==null||employedName==undefined||employedName==""){
		bool=false;
		$("#employedNameWarn").text("请输入录用人姓名");
	}if(trialMoney==null||trialMoney==undefined||trialMoney==""){
		bool=false;
		$("#trialMoneyWarn").text("请输入试用期工资");
	}else if(!reg.test(trialMoney)){
		bool=false;
		$("#trialMoneyWarn").text("请输入正确格式");
	}
	
	if(positiveMoney==null||positiveMoney==undefined||positiveMoney==""){
		bool=false;
		$("#positiveMoneyWarn").text("请输入转正后工资");
	}else if(!reg.test(positiveMoney)){
		bool=false;
		$("#positiveMoneyWarn").text("请输入正确格式");
	}
	
	if(employedOffice==null||employedOffice==undefined||employedOffice==""){
		bool=false;
		$("#employedOfficeWarn").text("请输入录用部门");
	}if(employedJob==null||employedJob==undefined||employedJob==""){
		bool=false;
		$("#employedJobWarn").text("请输入录用岗位");
	}if(jobLevel==null||jobLevel==undefined||jobLevel==""){
		bool=false;
		$("#jobLevelWarn").text("请输入岗位级别");
	}if(workDate==null||workDate==undefined||workDate==""){
		bool=false;
		$("#workDateWarn").text("请输入到岗日期");
	}
	var phonereg = /^[0-9]{11}$/
	if(phone==null||phone==undefined||phone==""){
		bool=false;
		$("#phoneWarn").text("请输入联系电话");
	}else if(!phonereg.test(phone)){
		bool=false;
		$("#phoneWarn").text("请输入正确格式");
	}
	if(positiveDate==null||positiveDate==undefined||positiveDate==""){
		bool=false;
		$("#positiveDateWarn").text("请输入转正日期");
	}
	if(contractSignedDate==null||contractSignedDate==undefined||contractSignedDate==""){
		bool=false;
		$("#contractSignedDateWarn").text("请输入合同签订日期");
	}
	if(contractStartDate==null||contractStartDate==undefined||contractStartDate==""){
		bool=false;
		$("#contractStartDateWarn").text("请输入合同起始日期");
	}
	if(contractEndDate==null||contractEndDate==undefined||contractEndDate==""){
		bool=false;
		$("#contractEndDateWarn").text("请输入合同结束日期");
	}
	
	return bool;
	
}