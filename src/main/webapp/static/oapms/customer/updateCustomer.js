var originalcustomerName;
$(document).ready(function(){
	originalcustomerName = $("input[name='customerName']").val();
});
function updateCustomer(){
	var bool = true;
	$("font").empty();
	var customer = $('form').serializeArray();
	var customerName = customer[1].value;
	var address = customer[2].value;
	var category = customer[3].value;
	var industry = customer[4].value;
	var office = customer[5].value;
	var area = customer[6].value;
	var saler = customer[7].value;
	var producter = customer[9].value;
	customerName = customerName.replace(/(^\s*)|(\s*$)/g, '');
	if(customerName==""||customerName==undefined||customerName==null){
		bool=false;
		$("input[name='customerName']").next().children().text("请输入客户名称");
	}else if(customerName.length>=50){
		bool=false;
		$("input[name='customerName']").next().children().text("请输入小于50个字符");
	}
	if(address.length>=200){
		bool=false;
		$("#addressWarn").text("请输入小于200个字符");
	}
	if(category==""||category==undefined||category==null){
		bool=false;
		$("#categoryWarn").text("请选择客户类别");
	}
	if(industry==""||industry==undefined||industry==null){
		bool=false;
		$("#industryWarn").text("请选择客户行业");
	}
	if(office==""||office==undefined||office==null){
		bool=false;
		$("#officeWarn").text("请选择客户所属办事处");
	}
	if(area==""||area==undefined||area==null){
		bool=false;
		$("#areaWarn").text("请选择客户所在区域");
	}
	if(saler==""||saler==undefined||saler==null){
		bool=false;
		$("#salerWarn").text("请选择销售经理");
	}
	if(producter==""||producter==undefined||producter==null){
		bool=false;
		$("#producterWarn").text("请选择产品经理");
	}
	if(bool==true){
		if(originalcustomerName==customerName){
			$("#updateCustomer").submit();
		}else{
			console.log(originalcustomerName);
			$.ajax({
				url:"judgeCustomerName",
				data:{"customerName":customerName},
				dataType:"json",
				success:function(result){
					if(result.data=="ok"){
						$("#updateCustomer").submit();
					}
					if(result.data=="error"){
						$("input[name='customerName']").next().children().text("该客户已存在");
					}
				}
			})
		}
	}
}

