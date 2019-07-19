var customerContact = [];
$(document).ready(function(){
	getCustomer();
});
function visitList(){
	var url = window.location.href;
	url = url.replace("toAdd","list");
	window.location.href=url;
}
function getCustomer(){
	url=window.location.href;
	url=url.replace("toAdd", "loading");
	var customerId = $("input[name='customerId']").val();
	var customerContactId = $("input[name='customerContactId']").val();
	var customerName = $("input[name='customerName']").val();
	var customerContactName = $("input[name='customerContactName']").val();
	$.ajax({
		url:url,
		dataType:"json",
		success:function(result){
			customerContact=result.contactList;
			var customer = $("select[name='customer.customerId']");
			var list =result.customerList;
			for(var i in list){
				customer.append("<option value="+list[i].customerId+" >"+list[i].customerName+"</option>");
			}
			if(customerId!=''||customerId!=undefined||customerId!=null){
				customer.val(customerId);
				$(".select2-chosen:first").text(customerName);
			}
			doSetMenus(customerContactId,customerContactName);
		},
	})
}
function doSetMenus(customerContactId,customerContactName){
	var customerId = $("select[name='customer.customerId']").val();
	var customerContactSelect = $("select[name='customerContact.customerContactId']");
	customerContactSelect.empty();
	customerContactSelect.append("<option value=''>请选择</option>");
	if(customerId!=''||customerId!=undefined||customerId!=null){
		for(var i in customerContact){
			if(customerContact[i].customer.customerId==customerId){
				customerContactSelect.append("<option value="+customerContact[i].customerContactId+" >"+customerContact[i].customerContactName+"</option>")
			}
		}
		$(".select2-chosen:last").text("请选择");
	}
	if(customerContactId!=''||customerContactId!=undefined||customerContactId!=null){
		customerContactSelect.val(customerContactId);
		$(".select2-chosen:last").text(customerContactName);
	}
}
function submitVisit(){
	var bool = true;
	$("font").empty();
	var title = $("input[name='title']").val().trim();
	var visitTime = $("input[name='visitTimeString']").val();
	var visitSummary = $("textarea[name='visitSummary']").val().trim();
	var nextPlan = $("textarea[name='nextPlan']").val().trim();
	var customerId = $("select[name='customer.customerId']").val();
	var customerContactId = $("select[name='customerContact.customerContactId']").val();
	var visitAddress = $("input[name='visitAddress']").val();
	if(title==''||title==undefined||title==null){
		bool=false;
		$("#titleWarn").text("请输入标题");
	}
	if(visitTime==''||visitTime==undefined||visitTime==null){
		bool=false;
		$("#visitTimeWarn").text("请输入时间");
	}
	if(visitSummary==''||visitSummary==undefined||visitSummary==null){
		bool=false;
		$("#visitSummaryWarn").text("请输入拜访纪要");
	}
	else if(visitSummary.length>=200){
		bool=false;
		$("#visitSummaryWarn").text("请输入小于200个字符");
	}
	if(nextPlan.length>=200){
		bool=false;
		$("#nextPlanWarn").text("请输入小于200个字符");
	}
	if(customerId==''||customerId==undefined||customerId==null){
		bool=false;
		$("#customerIdWarn").text("请选择客户");
	}
	if(customerContactId==''||customerContactId==undefined||customerContactId==null){
		bool=false;
		$("#customerContactIdWarn").text("请选择联系人");
	}
	if(bool==true){
		$("#addVisit").submit();
	}
}
