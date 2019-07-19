var listType = {};
var listDescription = {};
var costType = "";
$(document).ready(function() {
	appendHint();
	getDescriptionList();
	var id = $("#id").val();
	if(id==null||id==undefined||id==""){
		appendTr();
	}else{
		var saleDetailId = $("#saleDetailId").val()
		$.ajax({
			type : 'post',
			url : 'getDetail',
			dataType : 'json',
			data:{"saleDetailId":saleDetailId},
			success : function(data) {
				var list = data.detail;
				costType=list[0].costTypeId;
				for(var i=0;i<list.length;i++){
					appendTr();
				}
				//日期填写
				$(".Wdate").each(function(i,e) {
					var date = dateFormat(list[i].detailDate);
					$(e).val(date);
				});
				//收据金额填写
				$(".money").each(function(i,e) {
					$(e).val(list[i].money);
				});
				//费用描述填写
				$("select[name='costDescriptionId']").each(function(i,e) {
					$(e).val(list[i].costDescriptionId);
					chooseCostDescription(e);
				});
				$("input[name='day']").each(function(i,e){
					if(list[i].day!=null&&list[i].day!=undefined&&list[i].day!=""){
						$(e).val(list[i].day);
					}
				});
				
				/*出差编号赋值*/
				$("input[name='num']").each(function(i,e){
					if(list[i].num!=null&&list[i].num!=undefined&&list[i].num!=""){
						$(e).val(list[i].num);
					}
				});
				
				/*出差津贴赋值*/
				$("input[name='allowance']").each(function(i,e){
					if(list[i].allowance!=null&&list[i].allowance!=undefined&&list[i].allowance!=""){
						$(e).val(list[i].allowance);
					}
				});
				
				$("input[name='origin']").each(function(i,e){
					if(list[i].origin!=null&&list[i].origin!=undefined&&list[i].origin!=""){
						$(e).val(list[i].origin);
					}
				});
				$("input[name='destination']").each(function(i,e){
					if(list[i].destination!=null&&list[i].destination!=undefined&&list[i].destination!=""){
						$(e).val(list[i].destination);
					}
				});
				//可报销金额填写
				$(".aMoney").each(function(i,e) {
					$(e).val(list[i].amountMoney);
				});
				//项目名称填写
				$(".proj").each(function(i,e) {
					$(e).val(list[i].projectName);
				});
				//详细信息填写
				$(".info").each(function(i,e) {
					$(e).val(list[i].information);
				});
			}
		});
	}
});
//追加一条报销信息
var firstTr = true;
function appendTr(){
	var tr = $('<tr></tr>');
	var td = $('<td></td>');
	td.append('<input id="useTime" name="detailDate" type="text"'
			+'readonly="readonly" maxlength="20" class="input-medium Wdate"'
			+'onclick="WdatePicker({dateFmt:\'yyyy-MM-dd\',isShowClear:false});" />');
	td.append('<span class="warn" style="color: red"></span>');
	tr.append(td);
	td = $('<td></td>');
	td.append('<input name="money" value="" type="text" class="money" style="width: 60px" />元');
	td.append('<span class="warn" style="color: red"></span>');
	tr.append(td);
	td = $('<td></td>');
	var type = $('<select style="width:120px;" name="costTypeId" class="myselect" onchange="getDescription(this)"></select>');
	correctionType(type);
	td.append(type);
	td.append('<span class="warn" style="color: red"></span>');
	tr.append(td);
	td = $('<td></td>');
	
	var description = $('<select style="width:120px;" name="costDescriptionId" class="input-medium" onchange="chooseCostDescription(this)"></select>');
	correctionDescription(description)
	td.append(description);
	td.append('<span class="warn" style="color: red"></span>');
	var div = $('<div class="dis" style="display:none"></div>')
	div.append('<span>出差天数：</span>');
	div.append('<input type="text" name="day" onblur="inputOnBlur(this);">天');
	div.append('<span class="warn" style="color: red"></span></br>');
	
	div.append('<span>出差编号：</span>');
	div.append('<input type="text" name="num" >');
	div.append('<span class="warn" style="color: red"></span></br>');
	
	
	div.append('<span>出发地：</span>');
	div.append('<input type="text" name="origin" >');
	div.append('<span class="warn" style="color: red"></span></br>');
	div.append('<span>目的地：</span>');
	div.append('<input type="text" name="destination" >');
	div.append('<span class="warn" style="color: red"></span></br>');
	
	div.append('<span>出差津贴：</span>');
	div.append('<input type="text" name="allowance" readonly="readonly">');
	div.append('<span class="warn" style="color: red"></span>');

	td.append(div);
	tr.append(td);
	td = $('<td></td>');
	td.append('<input name="amountMoney" type="text" class="aMoney" style="width: 60px" />元');
	td.append('<span class="warn" style="color: red"></span>');
	tr.append(td);
	td = $('<td></td>');
	td.append('<input style="width:120px;" name="projectName" type="text" class="proj" />');
	td.append('<span class="warn" style="color: red"></span>');
	tr.append(td);
	td = $('<td></td>');
	td.append('<input style="width:120px;" name="information" type="text" class="info" />');
	td.append('<span class="warn" style="color: red"></span>');
	tr.append(td);
	td = $('<td></td>');
	if(firstTr){
		td.append('<input value="删除" type="button" disabled="disabled" style="width: 50px;" class="btn" />');
		firstTr=false;
	}
	else{
		td.append('<input value="删除" type="button" style="width: 50px;" class="btn" onclick="del(this)" />');
	}
	tr.append(td);
	$("#costList").append(tr);
}
//出差天数失去焦点出发事件 计算津贴
function inputOnBlur(a){
	var day = $(a).val();
	console.log($(a).parent())
	$(a).parent().find("input[name='allowance']").val(day*100);
}
//修正费用类型
function correctionType(type){
	//费用类型下拉选加载
	type.append('<option value="">请选择</option>');
	for(var i=0;i<listType.length;i++){
		type.append('<option value="'+listType[i].costTypeId+'">'+listType[i].costType+'</option>');
	}
	if(costType!=""){
		type.val(costType);
	}
}
//修正费用描述
function correctionDescription(description){
	//费用类型下拉选加载
	description.append('<option value="">请选择</option>');
	if(costType!=""){
		for(var i=0;i<listDescription.length;i++){
			if(listDescription[i].pid==costType){
				description.append('<option value="'+listDescription[i].id+'">'+listDescription[i].name+'</option>')
			}
		}
	}
}
//费用类型修改时调用该方法
function getDescription(btn){
	costType = $(btn).val();
	$("select[name='costTypeId']").each(function(){
		$(this).val(costType);
	});
	$("select[name='costDescriptionId']").each(function(){
		$(this).empty();
		$(this).append('<option value="">请选择</option>')
		for(var i=0;i<listDescription.length;i++){
			if(listDescription[i].pid==costType){
				$(this).append('<option value="'+listDescription[i].id+'">'+listDescription[i].name+'</option>')
			}
		}
	});
}
//删除当前行报销信息
function del(btn) {
	$(btn).parent().parent().remove();
}
//界面加载时执行传输费用描述
function getDescriptionList() {
	var myDate = new Date();
	$.ajax({
		type : 'post',
		async:false,
		url : 'getCostDescription',
		dataType : 'json',
		success : function(data) {
			listDescription = data.cost;
			listType = data.costType;
		}
	});
}

function chooseCostDescription(btn){
	if($(btn).val()=="0501"){
		$(btn).parent().find(".dis").attr("style","display:block");
	}else{
		$(btn).parent().find(".dis").attr("style","display:none");
	}
}

//存草稿
function saveDraft(){
	var bool = true;
	bool = yanzhen(bool);
	if (bool) {
		var act = $("#inputForm").attr("action");
		$("#inputForm").attr("action",act.replace("save","saveDraft"));
		return true;
	}
	return false;
}
var isCommitted=false;//是否已提交
//提交
function save() {
	var id = $("#id").val();
	if(id==null||id==undefined||id==""){
		var myDate = new Date();
		if(myDate.getDate()>25){
			alertx("请在每月的25号之前提交哦！")
			return false;
		}
	}
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
//正则验证
function yanzhen(bool){
	$(".warn").text("");
	var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
	var date = $(".Wdate").val();
	$(".Wdate").each(function() {
		$(this).next().text("");
		if (this.value == "") {
			bool = false;
			$(this).next().text("*必填信息");
		}
	});
	$(".money").each(function() {
		$(this).next().text("");
		if (this.value == "") {
			bool = false;
			$(this).next().text("*必填信息");
		} else if (!reg.test(this.value)) {
			bool = false;
			$(this).next().text("*格式错误");
		}
	});
	$(".aMoney").each(function() {
		$(this).next().text("");
		if (this.value == "") {
			bool = false;
			$(this).next().text("*必填信息");
		} else if (!reg.test(this.value)) {
			bool = false;
			$(this).next().text("*格式错误");
		}
	});
	$("select[name='costTypeId']").each(function(){
		$(this).next().text("");
		if(this.value==""){
			bool = false;
			$(this).next().text("*必填信息");
		}
	});
	var reg1=/^\d*(\.(5|0))?$/;
	$("select[name='costDescriptionId']").each(function(){
		$(this).next().text("");
		if(this.value==""){
			bool = false;
			$(this).next().text("*必填信息");
		}else if(this.value=="0501"){
			var day = $(this).next().next().find("input[name='day']").val();
			
			var num = $(this).next().next().find("input[name='num']").val();
			var allowance = $(this).next().next().find("input[name='allowance']").val();
			var origin = $(this).next().next().find("input[name='origin']").val();
			var destination = $(this).next().next().find("input[name='destination']").val();
			console.log(day);
			console.log(origin);
			console.log(destination);
			if(day==""){
				bool=false;
				$(this).next().next().find(".warn").eq(0).text("*必填信息");
			}else if (!reg1.test(day)) {
				bool=false;
				$(this).next().next().find(".warn").eq(0).text("*请填写整数或0.5的整数倍");
			}
			
			if(num==""){
				bool=false;
				$(this).next().next().find(".warn").eq(1).text("*必填信息");
			}
			if(allowance==""){
				bool=false;
				$(this).next().next().find(".warn").eq(1).text("*必填信息");
			}
			if(origin==""){
				bool=false;
				$(this).next().next().find(".warn").eq(2).text("*必填信息");
			}
			if(destination==""){
				bool=false;
				$(this).next().next().find(".warn").eq(3).text("*必填信息");
			}
		}
	});
	$("input[name='projectName']").each(function(){
		$(this).next().text("");
		if(this.value==""){
			bool = false;
			$(this).next().text("*必填信息");
		}
	})
	$("input[name='information']").each(function(){
		$(this).next().text("");
		if(this.value==""){
			bool = false;
			$(this).next().text("*必填信息");
		}
		else if (this.value.length > 180) {
			bool = false;
			$(this).next().text("*字体过长");
		}
	})
	return bool;
}
//日期转换
function dateFormat(longTypeDate){  
	 var dateTypeDate = "";  
	 var date = new Date();  
	 date.setTime(longTypeDate);  
	 dateTypeDate += date.getFullYear(); //年  
	 dateTypeDate += "-" + getMonth(date); //月  
	 dateTypeDate += "-" + getDay(date); //日  
	 return dateTypeDate; 
	} 
//返回 01-12 的月份值  
function getMonth(date){  
	 var month = "";  
	 month = date.getMonth() + 1; //getMonth()得到的月份是0-11  
	 if(month<10){  
	  month = "0" + month;  
	 }  
	 return month;  
}  
//返回01-30的日期  
function getDay(date){  
	 var day = "";  
	 day = date.getDate();  
	 if(day<10){  
	  day = "0" + day;  
	 }  
	 return day;  
} 
//追加费用类型提示
function appendHint(){
	var tr = $('<tr></tr>');
	tr.append('<td>一级分类</td>');
	tr.append('<td>二级分类</td>');
	tr.append('<td>分类说明</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td rowspan="6">办公费用</td>');
	tr.append('<td>办公用品</td>');
	tr.append('<td>包括纸张、笔、名片、餐巾纸、IT耗材（硒鼓、油墨、配件更换）、光盘、鼠标、U盘、键盘等费用；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>低值易耗品</td>');
	tr.append('<td>单价在2000元以下的办公家具、电器、工具、文件柜等；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>图书资料费</td>');
	tr.append('<td>购买技术、管理、学习等书籍（包括光盘）费用；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>快递费</td>');
	tr.append('<td>办公用途的普通邮寄、快递等费用；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>饮用水</td>');
	tr.append('<td>员工饮用水费用；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>其他办公费用</td>');
	tr.append('<td>其他办公费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>通讯费</td>');
	tr.append('<td>座机及网络费</td>');
	tr.append('<td>座机及网络费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>市内交通费用</td>');
	tr.append('<td>市内交通费</td>');
	tr.append('<td>指在办公当地发生的交通费，不同于出差发生的交通费</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td rowspan="5">汽车使用费</td>');
	tr.append('<td>汽油费</td>');
	tr.append('<td>汽油费</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>过路过桥费</td>');
	tr.append('<td>过路过桥费</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>汽车维修费</td>');
	tr.append('<td>汽车维修费</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>汽车保险费</td>');
	tr.append('<td>汽车保险费</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>其他汽车费用</td>');
	tr.append('<td>按规定支出的其他汽车费用,如停车费、验车费等。</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td rowspan="3">差旅费</td>');
	tr.append('<td>城际交通费</td>');
	tr.append('<td>出差发生的城际间火车、飞机、自驾车交通费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>住宿费</td>');
	tr.append('<td>出差过程中发生的住宿费；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>其他差旅费用</td>');
	tr.append('<td>出差中发生的其他费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>维修费</td>');
	tr.append('<td>其他维修费</td>');
	tr.append('<td>除汽车维修之外的修理费用，如办公设备的维修等；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>会务费</td>');
	tr.append('<td>会务费</td>');
	tr.append('<td>指公司内、外部会议的费用；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>市场推广费</td>');
	tr.append('<td>市场推广费</td>');
	tr.append('<td>市场推广发生的费用；</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>业务招待费</td>');
	tr.append('<td>业务招待餐饮费</td>');
	tr.append('<td>招待餐饮费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>培训费</td>');
	tr.append('<td>培训费</td>');
	tr.append('<td>内、外部培训费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td rowspan="4">福利费</td>');
	tr.append('<td>员工加班餐费</td>');
	tr.append('<td>员工加班工作餐</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>下午茶</td>');
	tr.append('<td>下午茶费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>团建费</td>');
	tr.append('<td>部门团费活动发生的费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>其他福利费用</td>');
	tr.append('<td>其他福利费用</td>');
	$("#hint").append(tr);
	tr = $('<tr></tr>');
	tr.append('<td>其他费用</td>');
	tr.append('<td>其他费用</td>');
	tr.append('<td>指允许员工报销的上述费用类别之外的费用</td>');
	$("#hint").append(tr);
}
