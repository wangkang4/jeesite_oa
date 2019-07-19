<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta name="decorator" content="default" />
<title>报销汇总图</title>
<script src="${ctxStatic}/expenseStatistical/highcharts.js"></script>
<script src="${ctxStatic}/expenseStatistical/exporting.js"></script>
<script src="${ctxStatic}/expenseStatistical/data.js"></script>
<script src="${ctxStatic}/expenseStatistical/drilldown.js"></script>
<script src="${ctxStatic}/expenseStatistical/highcharts-zh_CN.js"></script>
</head>
<body>
	<form:form id="expenseStatistical" modelAttribute=""
		action="" method="post"
		class="breadcrumb form-search">
		<ul class="ul-form">
			<li>
				<label><abbr style="text-decoration:none; border-bottom: 0 dotted;" title="所有批准时间大于起止时间(当天0时0分0秒)的数据">起止时间</abbr>:</label>
				<input id="startTime1" name="startTime1" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${startTime1 }" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<label>报销人:</label>	
					<input id="userId1" type="hidden" value="${userId }">
					<sys:treeselect id="user" name="user.id" value="${userId}" labelName="user.name" labelValue="${userName}"
							title="用户" url="/sys/office/treeData?type=3" cssClass="required"  allowClear="true" notAllowSelectParent="true"/>
			</li>
			<li>
				<label><abbr style="text-decoration:none; border-bottom: 0 dotted;" title="所有批准时间小于结束时间(当天23时59分59秒)的数据">结束时间</abbr>:</label>
				<input id="endTime1" name="endTime1" type="text" readonly="readonly" maxlength="20"
					class="input-medium Wdate "
					value="<fmt:formatDate value="${endTime1 }" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				<label>部门:</label>
					<input id="officeId1" type="hidden" value="${officeId }">
					<sys:treeselect id="office" name="office.id" value="${officeId}" labelName="office.name" labelValue="${officeName}"
							title="部门" url="/sys/office/treeData?type=2" cssClass="required" notAllowSelectParent="true"/>
				&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary"
					type="button" onclick="get()" value="查询" />
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-primary" value="清除" onclick="clearMap()">
			</li>
		</ul>
	</form:form>
	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		<script>
			var url="expenseStatistical";
			$(document).ready(function(){
				$.post(url,function(result){
					getMap(result);
				});
			});
			function clearMap(){
				location.reload();
			}
			function get(){
				var param={startTime1:$("#startTime1").val(),
						endTime1:$("#endTime1").val(),
						userId:$("#userId").val(),
						officeId:$("#officeId").val()};
				$.post(url,param,function(result){
					getMap(result);
				});
			}
			function getMap(result){
				$(function() {
					var data = [];
					for(var key in result.data){
						var data1={};
						data1.name=key;
						data1.drilldown=key;
						data1.y=result.data[key];
						data.push(data1);
					}
					var serise = [];
					for(var key in result.serise){
						var serise1={};
						serise1.name=key;
						serise1.id=key;
						serise1.data=[];
						for(var key1 in result.serise[key]){
							serise1.data.push([key1,result.serise[key][key1]]);
						}
						serise.push(serise1);
					}
					Highcharts.chart('container', {
						chart: {
							type: 'column'
						},
						title: {
							text: '报销统计'
						},
						xAxis: {
							type: 'category'
						},
						yAxis: {
							title: {
								text: '金额(元)'
							}
						},
						legend: {
							enabled: false
						},
						plotOptions: {
							series: {
								borderWidth: 0,
								dataLabels: {
									enabled: true,
									format: '{point.y:.1f}'
								}
							}
						},
						tooltip: {
							headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
							pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}</b><br/>'
						},
						series: [{
							name: '报销类型',
							colorByPoint: true,
							data: data
						}],
						drilldown: {
							series: serise
						}
					});
				});
			}
		</script>
</body>
</html>