var isCommitted=false;//是否已提交
function save() {	
	if(isCommitted){
		alertx("请不要重复提交");
		return false;
	}
	var bool = true;
	bool = yanzhen(bool);
	if(bool){
		committed=true;
		return true;
	}
	return false;
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
function yanzhen(bool){
	$(".warn").text("");
	var title = $("#title").val();
	if(title==undefined||title==null||title==""){
		bool=false;
		$("#titleWarn").text("请填写标题");
	}
	return bool;
}