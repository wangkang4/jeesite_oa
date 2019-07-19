$(function(){
				//	查询所有项目添加到option中
				$.ajax({
					"url":"getAllProject",
					"type":"post",
					"dataType":"json",
					"success":function(obj){
						for(var i=0;i<obj.length;i++){
							var op=document.createElement("option");
							op.text=obj[i];
							document.getElementById("projectName").appendChild(op);
						}
					}
				});
				
				//给项目名称选项添加change事件
				$("#projectName").change(function(){
					$("#info_project").html("");
					var projectName=$("#projectName").val();
					console.log("项目名：");
					console.log(projectName);
					$.ajax({
						"url":"getProjectId",
						"data":"projectName="+projectName,
						"type":"post",
						"dataType":"json",
						"success":function(obj){
							
						}
					});
				});
				

			$("#problemName").focus(function(){
				$("#info_problem").html("");
			});			
			
			$("#problemType").change(function(){
				$("#info_type").html("");
			});	
			
			//给上传按钮添加点击事件
			 $("#upload").click(function(){
				//找到所有的文件
				console.log(1);
 				var file=inputFile.files[0];
			//	var file= $('#attach').get(0).files[0];  ;
				console.log(file);
				//创建FormData对象
				var frm=new FormData();			
				frm.append("attach",file);
			//	console.log(frm);
				//利用JQuery的ajax方法发送formData对象
					 $.ajax({
						"url":"upload",
						"data":frm,//ajax方法发送formData对象
						"type":"post",
						"dataType":"json",
						"processData":false,  //jquery不要处理frm数据
						"contentType":false,
						"success":function(json){
							alert(json.message);
						}
					}); 			
			});  
			
			 //附件名的js
			 $("#inputFile").change(function(){
				 var index = $(this).val().lastIndexOf("\\");
		         var sFileName = $(this).val().substr((index+1));
		         $("#rightText").html(sFileName);
		         $("#attachName").val(sFileName);
			 });			 
			 		 
		});
		
		//对表单中的问题标题，项目名，问题类型进行非空验证
		function submitForm(){
			var bool=true;
			var problemName=$("#problemName").val().replace(/(^\s*)|(\s*$)/g, '');
			var projectName=$("#projectName").val();
			var problemType=$("#problemType").val();
			
			if (problemName == "" || problemName == undefined || problemName == null) {
				bool = false;
				$("#info_problem").html("&nbsp;问题标题不能为空！");
			}
			if (projectName == "--请选择--") {
				bool = false;
				$("#info_project").html("&nbsp;项目名称不能为空！");
			}
			if (problemType == "--请选择--") {
				bool = false;
				$("#info_type").html("&nbsp;问题类型不能为空！");
			}
			
			if (bool) {
				$("#addQuestion").submit();
			}
		}
			 
		