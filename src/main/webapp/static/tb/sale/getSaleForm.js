var listDescription = {};
$(document).ready(function() {
	getDescriptionList();
	cremove();
	ready();
	appendHint();
//	appendDis();
	$("select[name='costDescriptionId']").each(function(){
		chooseCostDescription(this);
	});
});
//function appendDis(){
//	$("select[name='costDescriptionId']").each(function(){
//		$(this).parent().append('<div class="dis" style="display:none">'
//		+'<span >出差天数</span>'
//		+'<input type="text" name="day" >天'
//		+'<span class="warn" style="color: red"></span>'
//	+'</div>');
//	});
//}
function chooseCostDescription(btn){
	if($(btn).val()=="0501"){
		console.log($(btn).parent().find(".dis").html());
		$(btn).parent().find(".dis").attr("style","display:block");
//		$(btn).parent().append("<span>出差天数</span>")
//		$(btn).parent().append("<input type='text' name='day' >天");
//		$(btn).parent().append("<span class='warn' style='color: red'></span>");
	}else{
		$(btn).parent().find(".dis").attr("style","display:none");
	}
}
//费用小类改变时调用该方法
//预审批关联取消，以后提交的时候打印纸质单即可
/*function chooseSpecial(spe){
	$(spe).parent().parent().parent().prev().children().eq(-1).children().eq(8).remove();
	$(spe).parent().parent().children("td").eq(8).remove();
	var costDescriptionId = $(spe).val();
	if(costDescriptionId.substring("0","2")=="06"){
		$(spe).parent().parent().parent().prev().children().eq(-1).append('<th>对应预审批</th>');
		var td = $("<td></td>");
		var select = $("<select name='tbMoneyId'></select>")
		select.append("<option value=''>请选择</option>")
		if(costDescriptionId=="0602"){
			$.ajax({
				url:"business",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.list.length;i++){
						select.append("<option value='"+data.list[i].id+"-"+data.list[i].reason+"-"+data.list[i].money+"'>"+data.list[i].reason+"-"+data.list[i].money+"元"+"</option>")
					}
				}
			});
		}else{
			$.ajax({
				url:"special",
				dataType:"json",
				success:function(data){
					for(var i=0;i<data.list.length;i++){
						select.append("<option value='"+data.list[i].id+"-"+data.list[i].reason+"-"+data.list[i].money+"'>"+data.list[i].reason+"-"+data.list[i].money+"元"+"</option>")
					}
				}
			});
		}
		td.append(select);
		td.append('<span class="warn" style="color: red"></span>');
		$(spe).parent().parent().append(td);
	}else{
		
	}
}*/
//准备方法
function ready() {
	$("#inputForm")
			.validate(
					{
						submitHandler : function(form) {
							loading('正在提交，请稍等...');
							form.submit();
						},
						errorContainer : "#messageBox",
						errorPlacement : function(error, element) {
							$("#messageBox").text("输入有误，请先更正。");
							if (element.is(":checkbox")
									|| element.is(":radio")
									|| element.parent().is(
											".input-append")) {
								error.appendTo(element.parent()
										.parent());
							} else {
								error.insertAfter(element);
							}
						}
					});
}
//页面格式移除
function cremove(){
	$(".select2-offscreen").removeClass('select2-offscreen');
	$(".select2-container").removeClass('myselect');
}
//界面加载时执行传输费用描述
function getDescriptionList() {
	var myDate = new Date();
	$.ajax({
		type : 'post',
		async:false,
		url : 'costDescription',
		dataType : 'json',
		success : function(data) {
			listDescription = data;
		}
	});
	
}
//费用大类改变时调用方法
function getDescription(btn) {
	var costTypeId = $(btn).val();
	var costType=$(btn).find("option:selected").text();
	var costDescription = $(btn).parent().next().children("select").val();
	for(var i=0;i<$("select:even").size();i++){
		$("select:even").val(costTypeId);
	}
	$("select:odd").empty();
	$("select:odd").append(
	"<option value=''>请选择</option>");
	for ( var prop in listDescription) {
		if (prop == costTypeId) {
			$.each(listDescription[prop], function(i, item) {
				if (item.costDescription == costDescription) {
					$(btn).parent().next().children("select").append(
							$('<option/>', {
								value : item.costDescriptionId,
								text : item.costDescription,
								selected : true
							}));
				} else {
					$("select:odd").append(
							$('<option/>', {
								value : item.costDescriptionId,
								text : item.costDescription
							}));
				}
			});
		}
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
//删除按钮
function del(btn) {
	$(btn).parent().parent().remove();
}
//正则验证
function yanzhen(bool){
	var myDate = new Date();
	var id = $("#id").val();
	if(id==null||id==undefined||id==""){
		if(myDate.getDate()>25){
			alertx("请在每月的25号之前提交哦！")
			return false;
		}
	}
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
			if(day==""){
				bool=false;
				$(this).next().next().find(".warn").text("*必填信息");
			}else if (!reg1.test(day)) {
				bool=false;
				$(this).next().next().find(".warn").text("*格式错误");
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
	/*$("select[name='tbMoneyId']").each(function(){
		$(this).next().text("");
		if(this.value==""){
			bool = false;
			$(this).next().text("*必填信息");
		}
	})*/
	return bool;
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
