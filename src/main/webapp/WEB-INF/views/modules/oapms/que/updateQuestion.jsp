<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>项目问题修改</title>
		<meta name="decorator" content="default" />
		<link rel="stylesheet" href="${ctxStatic}/oapms/question/css/addQuestion.css">
		<script type="text/javascript" src="${ctxStatic}/oapms/question/js/addQuestion.js"></script>
	</head>
	<body>
		<ul class="nav nav-tabs">
			<li><a href="${ctx}/pms/question/showQuestionForm">项目问题列表</a></li>
			<li class="active"><a href="">项目问题修改</a></li>
		</ul>
		<form:form id="addQuestion" modelAttribute="QuestionRecord"
			action="${ctx }/pms/question/updateQuestion" method="post"
			class="form-horizontal"  onsubmit="return checkForm()">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed" style="width:800px; ">
			<tr>
				<td style="text-align:center;">问题标题</td>
				<td><input type="text"  id="problemName" name="problemName" value="${question.problemName}" onblur="checkProblemName()"><font id="info_problem" style="color:red;"></font></td>
				<td style="text-align:center;">项目名称</td>
				<td><input type="text"  readonly="readonly"  id="projectName" name="projectName" value="${question.pms.projectName}" ></td>
			</tr>
			<tr>
				<td style="text-align:center;">问题类型</td>
				<td><select style="width:215px;" name="problemType" id="problemType" >
						<option value="管理问题">管理问题</option>
						<option value="服务问题">服务问题</option>
						<option value="体验问题">体验问题</option>
				</select></td>
				<td style="text-align:center;">问题来源</td>
				<td>
				<select style="width:215px;" name="problemSource" id="problemSource">
					   <option value="市场调查">市场调查</option>
						<option value="网上浏览">网上浏览</option>
						<option value="亲身体验">亲身体验</option>
				</select>
				
				</td>
			</tr>
			<tr>
				<td style="text-align:center;">问题状态</td>
				<td>
				  <select style="width:215px;" name="problemState" id="problemState">
						<option value="已解决">已解决</option>
						<option value="未解决">未解决</option>
				</select>
				</td>
				<td style="text-align:center;">责任人</td>
				<td><sys:treeselect id="user00" name="leaderId" value="${user.id}" labelName="saler.name" labelValue="${question.user.name }"
						title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/></td>
			</tr>
		
			<tr>
				<td style="text-align:center;">提出时间</td>
				<td>
					<input id="beginDate" name="submitTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${question.submitTime}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				
				</td>
				<td style="text-align:center;">到期时间</td>
				<td>
					<input id="endDate" name="dueTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${question.dueTime}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</td>
			</tr>
			
			<tr>
				<td style="text-align:center;">严重程度</td>
				<td>
				   <select style="width:215px;" name="problemUrgent" id="problemUrgent">
					<option value="紧急">紧急</option>
					<option value="一般">一般</option>
					<option value="不重要">不重要</option>
				</select>		
				</td>
				
			</tr>
			<tr >
				<td style="text-align:center;height=60px;">详细描述</td>
				<td colspan="3"  style="width:30px;"><textarea rows="6" cols="6"  name="problemDescribe" >${question.problemDescribe}</textarea></td>
			</tr>
			<tr>
				<td style="text-align:center;height=60px;">附件</td>
				<td colspan="3" style="width:30px;">
	   				<form id="formID" enctype="multipart/form-data"  action="" method="POST">							
						<div class="inputFileWrapper">
							<label for="inputFile">
				        		<input type="file" id="inputFile"  name="attach"/>
					         	<span class="custorm-style">
						            <span class="left-button">选择附件</span>
						             <span class="right-text" id="rightText">${question.attachId}</span>
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
				<input id="btn" class="btn btn-primary" type="submit"
					 value="提交" /> &nbsp;
				<input id="btnCancel" class="btn" type="reset" value="重置" />
			</div>
		</form:form>
		
		<input  type="hidden" value="${question.problemType}" id="type"/>
		<input  type="hidden" value="${question.problemSource}" id="source"/>
		<input  type="hidden" value="${question.problemState}" id="state"/>
		<input  type="hidden" value="${question.problemUrgent}" id="urgent"/>
	</body>
</html>