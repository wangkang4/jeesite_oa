<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html lang="en">
	<head>
<!-- 		<meta name="decorator" content="default"/> -->
		<meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
		<meta charset="utf-8">
		<title>jQuery File Upload</title>
		<meta name="description" content="File Upload widget with multiple file selection, drag&amp;drop support, progress bars, validation and preview images, audio and video for jQuery. Supports cross-domain, chunked and resumable file uploads and client-side image resizing. Works with any server-side platform (PHP, Python, Ruby on Rails, Java, Node.js, Go etc.) that supports standard HTML form file uploads.">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${ctxStatic}/fileload1/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctxStatic}/fileload1/css/blueimp-gallery.min.css">
		<link rel="stylesheet" href="${ctxStatic}/fileload1/css/fileupload.css">
		<link rel="stylesheet" href="${ctxStatic}/fileload1/css/fileupload-ui.css">
		<noscript><link rel="stylesheet" href="${ctxStatic}/fileload1/css/fileupload-noscript.css"></noscript>
		<noscript><link rel="stylesheet" href="${ctxStatic}/fileload1/css/fileupload-ui-noscript.css"></noscript>
	</head>
<body>
<div class="container">
	<!--
    <h2 class="lead">Basic Plus UI version</h2>
    <ul class="nav nav-tabs">
        <li><a href="basic.html">Basic</a></li>
        <li><a href="basic-plus.html">Basic Plus</a></li>
        <li class="active"><a href="index.html">Basic Plus UI</a></li>
        <li><a href="angularjs.html">AngularJS</a></li>
        <li><a href="jquery-ui.html">jQuery UI</a></li>
    </ul>
    <br />
    -->
    <form id="fileupload" action="${ctx}/upload/upload2" method="POST" enctype="multipart/form-data">
        <noscript><input type="hidden" name="redirect" value="${ctx}/upload/upload2"></noscript>
        <div class="row fileupload-buttonbar">
            <div class="col-lg-7">
                <span class="btn btn-success fileinput-button">
                    <span>添加文件</span>
                    <input type="file" name="files[]" multiple>
                </span>
                <button type="submit" class="btn btn-primary start">
                    <span>开始上传</span>
                </button>
                <span class="fileupload-loading"></span>
            </div>
            <div class="col-lg-5 fileupload-progress fade" >
                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                </div>
            </div>
        </div>
        <table role="presentation" class="table table-striped" style="margin-top:-40px;"><tbody class="files"></tbody></table>
    </form>
</div>
<div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
    <div class="slides"></div>
    <h3 class="title"></h3>
    <a class="prev">‹</a>
    <a class="next">›</a>
    <a class="close">×</a>
    <a class="play-pause"></a>
    <ol class="indicator"></ol>
</div>
<script id="template-upload" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-upload fade">
        <td>
            <span class="preview"></span>
        </td>
			<input type="text" id="fileid" value="{%=file.id%}">
        <td>
            <p class="name">{%=file.name%}</p>
            {% if (file.error) { %}
                <div><span class="label label-danger">错误</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
			<span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (!o.files.error && !i && !o.options.autoUpload) { %}
                <button class="btn btn-primary start">
                    <i >开始</i>
                </button>
            {% } %}
            {% if (!i) { %}
                <button class="btn btn-warning cancel">
                    <i>取消</i>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<!-- The template to display files available for download -->
<!-- {% if (file.thumbnailUrl) { %} -->
<!--                     <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a> -->
<!--                 {% } %} -->
<script id="template-download" type="text/x-tmpl">
{% for (var i=0, file; file=o.files[i]; i++) { %}
    <tr class="template-download fade">
        <td>
            <span class="preview">
            </span>
        </td>
        <td>
            <p class="name">
                {% if (file.url) { %}
                    <span>{%=file.name%}</span>
                {% } else { %}
                    <span>{%=file.name%}</span>
                {% } %}
            </p>
            {% if (file.error) { %}
                <div><span class="label label-danger">错误</span> {%=file.error%}</div>
            {% } %}
        </td>
        <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
        </td>
        <td>
            {% if (file.deleteUrl) { %}
                <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" {% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                    <i onclick="doDelete('{%=file.id%}')">删除</i>
                </button>
            {% } else { %}
                <button class="btn btn-warning cancel">
                    <i>取消</i>
                </button>
            {% } %}
        </td>
    </tr>
{% } %}
</script>
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js"></script>
<script src="${ctxStatic}/fileload1/js/vendor/jquery.ui.widget.js"></script>
<script src="${ctxStatic}/fileload1/js/tmpl.min.js"></script>
<script src="${ctxStatic}/fileload1/js/load-image.min.js"></script>
<script src="${ctxStatic}/fileload1/js/canvas-to-blob.min.js"></script>
<script src="${ctxStatic}/fileload1/js/jquery.blueimp-gallery.min.js"></script>
<script src="${ctxStatic}/fileload1/js/jquery.iframe-transport.js"></script>
<script src="${ctxStatic}/fileload1/js/fileupload.js"></script>
<script src="${ctxStatic}/fileload1/js/fileupload-process.js"></script>
<script src="${ctxStatic}/fileload1/js/fileupload-image.js"></script>
<script src="${ctxStatic}/fileload1/js/fileupload-validate.js"></script>
<script src="${ctxStatic}/fileload1/js/fileupload-ui.js"></script>
<script type="text/javascript">
	/*
		注： 
			1. 事件触发顺序：fileupload.js[_onAdd]
							->fileupload-ui.js[add]
							->fileupload-process.js[process]
							->fileupload-image.js[loadImageMetaData]
							->fileupload-image.js[loadImage]
							->fileupload-image.js[resizeImage]
							->fileupload-image.js[saveImage]
							->fileupload-image.js[saveImageMetaData]
							->fileupload-image.js[resizeImage]
							->fileupload-image.js[setImage]
							->fileupload-validate.js[validate]
							->fileupload-process.js[process]
							->fileupload-ui.js[_renderUpload]----注： 回到ui.js add 方法
							->...这里ui.js里的方法都是在之前add方法里面调用的
							->fileupload-ui.js[_transition]
							
	*/
	var uploader = $('#fileupload');
	var url = window.location.href;
	id = url.substring(url.indexOf("=")+1);
	alert(id);
	$(function () {
		'use strict';
		uploader.fileupload({
			'url': "${ctx}/project/upload/upload2",
			'method': 'POST',
			'autoUpload': false,
			'limitMultiFileUploads': 2, // 限定最多两个文件
			'limitConcurrentUploads': 1, // 限定同时上传N个文件
			'maxFileSize': 50 * 1024 * 1024,
			'maxNumberOfFiles': 40,
// 			'acceptFileTypes': /(\.|\/)(gif|jpe?g|png|xls|xlsx|doc|docx|ppt|pptx|txt|pdf)$/i,
        	'previewMaxWidth': 50,
        	'previewMaxHeight': 50,
        	
		});
// 		uploader.bind('fileuploadfailed', function (e, data) {
// 			console.info("failed");
//             //eva.p(data);
// 		});
// 		uploader.bind('fileuploadadded', function (e, data) {
// 			console.info("added");
// 			//alert('my-add');
// 			//if(!data.files.valid) {
// 				//uploader.find('.files .cancel').click();
// 			//}
// 		});
// 		uploader.bind('fileuploadchange', function (e, data) {
// 			console.info("change");
// 			//alert('my-change');
// 			//uploader.find('.files').empty();
// 		});
		uploader.bind('fileuploaddone', function(e, data) {
			console.info("done1");
			console.info(data.result.files[0].name);
			$("#fileids",window.parent.document).append(data.result.files[0].id+"|");
 			for(var i = 0 ; i < data.result.files.length ; i++){
 				console.info(data.result.files[i].url);
 				alert(data.result.files[i].id);
 				$.ajax({
 					url:"${ctx}/project/upload/upload1",
 					data:{projectId:id,documentId:data.result.files[i].id},
 					dataType:"json",
 					success:function(){
 						
 					}
 				})
 			}
			alert('上传成功');
		});
	});
	function doDelete(fileid){
		$.ajax({
			type : "post",
			url : "${ctx}/project/upload/deleteupload",
			data : {"fileid":fileid},
			dataType : "json",
			success:function(data){
				if("success"==data.files){
					console.info("delete success");
				}else{
					console.info("delete fail");	
				}
			}
		});
	}
</script>

</body> 
</html>