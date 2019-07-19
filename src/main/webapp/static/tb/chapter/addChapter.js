$(document).ready(function(){
	change();
	getContract();
});
function getContract(){
	var contractId = $("#contractId").val();
	var bool = true;
	if(contractId==undefined||contractId==null||contractId==""){
		bool=false;
	}
	$.ajax({
		url:"getContract",
		dataType:"json",
		success:function(result){
			var list = result.list;
			for(var i=0;i<list.length;i++){
				var id = list[i].id;
				var contractName = list[i].contractName;
				$("select[name='contractId']").append('<option value="'+id+'">'+contractName+'</option>');
				if(bool&&contractId==id){
					$("select[name='contractId']").val(id);
					$(".select2-chosen").eq(3).text(contractName);
				}
			}
		}
	})
	
}
function change(){
	var placeOfUser = $("select[name='placeOfUser']").val();
	if(placeOfUser==2){
		$(".hidden").attr("style","");
	}else{
		$(".hidden").attr("style","display:none");
		var chapterTime = $("input[name='chapterTime']").val("");
		var returnDate = $("input[name='returnDate']").val("");
		var reasonForBorrow = $("#reasonForBorrow").val("");
	}
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
		console.log(evt.lengthComputable);
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
function save() {
	$(".warn").text("");
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	
	bool = yanzhen(bool);
	
	if (bool) {
		$('#flag').val('yes');
		isCommitted = true;
		return true;
	}
	return false;
}
function yanzhen(bool){
	var fileForChapter = $("input[name='fileForChapter']").val();
	var chapterTime = $("input[name='chapterTime']").val();
	var returnDate = $("input[name='returnDate']").val();
	var numberForFile = $("input[name='numberForFile']").val();
	var placeOfUser = $("select[name='placeOfUser']").val();
	var chapterType = $("select[name='chapterType']").val();
	var fileType = $("select[name='fileType']").val();
	var reasonForBorrow = $("#reasonForBorrow").val();
	if(fileForChapter==undefined||fileForChapter==null||fileForChapter==""){
		bool=false;
		$("#fileForChapterWarn").text("请输入用印文件名");
	}
	
	var numReg = /^[1-9]+[0-9]*$/;
	if(placeOfUser==2){
		if(!numReg.test(chapterTime)){
			bool=false;
			$("#chapterTimeWarn").text("请输入正整数");
		}
		if(chapterTime==undefined||chapterTime==null||chapterTime==""){
			bool=false;
			$("#chapterTimeWarn").text("请输入用印时间");
		}
		if(returnDate==undefined||returnDate==null||returnDate==""){
			bool=false;
			$("#returnDateWarn").text("请选择归还时间");
		}
		if((reasonForBorrow==undefined||reasonForBorrow==null||reasonForBorrow=="")&&placeOfUser==2){
			bool=false;
			$("#reasonForBorrowWarn").text("请输入外借原因");
		}
	}
	if(!numReg.test(numberForFile)){
		bool=false;
		$("#numberForFileWarn").text("请输入正整数");
	}
	if(numberForFile==undefined||numberForFile==null||numberForFile==""){
		bool=false;
		$("#numberForFileWarn").text("请输入用印文件数量");
	}
	if(placeOfUser==""){
		bool=false;
		$("#placeOfUserWarn").text("请输入用印地点");
	}
	
	if(chapterType==""){
		bool=false;
		$("#chapterTypeWarn").text("请输入用印种类");
	}
	if(fileType==""){
		
		$("#fileTypeWarn").text("请输入用印文件类型");
	}
	/**判断用章是否被借用*/
	$.ajax({
		url:"chapteruse",
		data:"chapteruse="+chapterType,
		dataType:"json",
		async:false,/*注意此时改成同步*/
		success:function(data){
			if(data.flag!=0){
				alertx(data.flagName+"正在被外借申请使用，暂不可申请此印章!");
				bool = false;
				return;
			}
		}
	})
	return bool;
}

function stop() {
	$('#flag').val('no');
	return true;
}
