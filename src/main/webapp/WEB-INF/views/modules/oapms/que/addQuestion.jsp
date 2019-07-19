<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>项目问题添加</title>
		<meta name="decorator" content="default" />
		<link rel="stylesheet" href="${ctxStatic}/oapms/question/css/addQuestion.css">
		<script type="text/javascript" src="${ctxStatic}/oapms/question/js/addQuestion.js"></script>
	</head>
	<body>
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/pms/question/showQuestionForm">项目问题列表</a></li>
			<li class="active"><a href="${ctx}/pms/question/showAddQuestion">项目问题添加</a></li>
		</ul>
		<form:form id="addQuestion" modelAttribute="QuestionRecord"
			action="${ctx }/pms/question/addQuestion" method="post"
			class="form-horizontal"  >
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed" style="width:800px; ">
			<tr>
				<td >问题标题</td>
				<td><input type="text"  id="problemName" name="problemName" ><font id="info_problem" style="color:red;"></font></td>
				<td>项目名称</td>
				<td>
					<select style="width:215px;" name="projectName" id="projectName" >
						<option value="--请选择--" >--请选择--</option>
					</select>
					<font id="info_project" style="color:red;"></font>
				</td>
			</tr>
			<tr>
				<td>问题类型</td>
				<td><select style="width:215px;" name="problemType" id="problemType" >
							<option value="--请选择--">--请选择--</option>
							<option value="管理问题">管理问题</option>
							<option value="服务问题">服务问题</option>
							<option value="体验问题">体验问题</option>
						</select>
						<font id="info_type" style="color:red;"></font>
				</td>
				<td>问题来源</td>
				<td>
				<select style="width:215px;" name="problemSource">
						<option value="--请选择--">--请选择--</option>
					   <option value="市场调查">市场调查</option>
						<option value="网上浏览">网上浏览</option>
						<option value="亲身体验">亲身体验</option>
				</select>
				
				</td>
			</tr>
			<tr>
				<td>问题状态</td>
				<td>
				  <select style="width:215px;" name="problemState">
				 		<option value="--请选择--">--请选择--</option>
						<option value="已解决">已解决</option>
						<option value="未解决">未解决</option>
				</select>
				</td>
				<td>责任人</td>
				<td><sys:treeselect id="user00" name="leaderId" value="${user.id}" labelName="saler.name" labelValue="${question.user.name }"
						title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/></td>
			</tr>
		
			<tr>
				<td>提出时间</td>
				<td>
					<input id="beginDate" name="submitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${question.submitTime}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				
				</td>
				<td>到期时间</td>
				<td>
					<input id="endDate" name="dueTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${question.dueTime}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
			
			<tr>
				<td>严重程度</td>
				<td>
				   <select style="width:215px;" name="problemUrgent">
				    <option value="--请选择--">--请选择--</option>
					<option value="紧急">紧急</option>
					<option value="一般">一般</option>
					<option value="不重要">不重要</option>
				</select>		
				</td>
				
			</tr>
			<tr >
				<td height=60px;>详细描述</td>
				<td colspan="3"  style="width:30px;"><textarea rows="6" cols="6"  name="problemDescribe" >${question.problemDescribe}</textarea></td>
			</tr>
			<tr>
				<td height=60px>附件</td>
				<td colspan="3" style="width:30px;">
	   				<form id="formID" enctype="multipart/form-data"  action="" method="POST">							
						<div class="inputFileWrapper">
							<label for="inputFile">
				        		<input type="file" id="inputFile" name="attach"/>
					         	<span class="custorm-style">
						            <span class="left-button">选择附件</span>
						             <span class="right-text" id="rightText"></span>
					         	</span>
			  				 </label>
			  				</div>
			  				<input type="button" id="upload" value="开始上传">
	   				</form>
				</td>
			</tr>	
			<tr style="display: none;">
				<td><input type="text"  value=""  id="attachName" name="attachId"/></td>
			</tr>					
			
		</table>
			<div class="">
				<input id="btn" class="btn btn-primary" type="button"  onclick="return submitForm()"
					 value="提交" /> &nbsp;
				<input id="btnCancel" class="btn" type="reset" value="重置" />
			</div>
		</form:form>
			
	</body>
</html>