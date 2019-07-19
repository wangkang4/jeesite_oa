$(function() {
	var tag = false, ox = 0, left = 0, bgleft = 0;
	$('.progress_btn').mousedown(function(e) {
		ox = e.pageX - left;
		tag = true;
	});
	$(document).mouseup(function() {
		tag = false;
	});
	$('.progress').mousemove(function(e) { // 鼠标移动
		if (tag) {
			left = e.pageX - ox;
			if (left <= 0) {
				left = 0;
			} else if (left > 200) {
				left = 200;
			}
			$('.progress_btn').css('left', left);
			$('.progress_bar').width(left);
			$('.text').html(parseInt((left / 200) * 100) + '%');
			$("#progress").val(parseInt((left / 200) * 100));
		}
	});
	$('.progress_bg').click(function(e) { // 鼠标点击
		if (!tag) {
			bgleft = $('.progress_bg').offset().left;
			left = e.pageX - bgleft;
			if (left <= 0) {
				left = 0;
			} else if (left > 200) {
				left = 200;
			}
			$('.progress_btn').css('left', left);
			$('.progress_bar').animate({
				width : left
			}, 200);
			$('.text').html(parseInt((left / 200) * 100) + '%');
			$("#progress").val(parseInt((left / 200) * 100));
		}
	});

	$("#projectName").blur(function() {
		var projectName = $("#projectName").val();
		if (projectName.length < 1) {
			$("#projectNameText").text("请输入项目名称！");
			$("#projectNameText").addClass("err");
		} else {
			$("#projectNameText").text("输入正确！");
			$("#projectNameText").removeClass("err");
			$("#projectNameText").addClass("ok");
		}
	})

	$("#money").blur(function() {
		var money = $("#money").val();
		var moneyreg = /^-?\d*\.?\d*$/;
		if (!moneyreg.test(money.trim())) {
			$("#moneyText").text("请输入正确的数字金额！");
			$("#moneyText").addClass("err");
		} else {
			$("#moneyText").text("输入正确！");
			$("#moneyText").removeClass("err");
			$("#moneyText").addClass("ok");
		}
	})

	$("#costomerAnalysis").blur(function() {
		var costomerAnalysis = $("#costomerAnalysis").val();
		if (costomerAnalysis.length > 255) {
			$("#costomerAnalysisText").text("请输入小于255字的描述！");
			$("#costomerAnalysisText").addClass("err");
		} else {
			$("#costomerAnalysisText").text("输入正确！");
			$("#costomerAnalysisText").removeClass("err");
			$("#costomerAnalysisText").addClass("ok");
		}
	})

	$("#decMakChainAnalysis").blur(function() {
		var decMakChainAnalysis = $("#decMakChainAnalysis").val();
		if (decMakChainAnalysis.length > 255) {
			$("#decMakChainAnalysisText").text("请输入小于255字的描述！");
			$("#decMakChainAnalysisText").addClass("err");
		} else {
			$("#decMakChainAnalysisText").text("输入正确！");
			$("#decMakChainAnalysisText").removeClass("err");
			$("#decMakChainAnalysisText").addClass("ok");
		}
	})

	$("#competitorsAnalysis").blur(function() {
		var competitorsAnalysis = $("#competitorsAnalysis").val();
		if (competitorsAnalysis.length > 255) {
			$("#competitorsAnalysisText").text("请输入小于255字的描述！");
			$("#competitorsAnalysisText").addClass("err");
		} else {
			$("#competitorsAnalysisText").text("输入正确！");
			$("#competitorsAnalysisText").removeClass("err");
			$("#competitorsAnalysisText").addClass("ok");
		}
	})

	$("#chancePoint").blur(function() {
		var chancePoint = $("#chancePoint").val();
		if (chancePoint.length > 255) {
			$("#projectNameText").text("请输入小于255字的描述！");
			$("#projectNameText").addClass("err");
		} else {
			$("#projectNameText").text("输入正确！");
			$("#projectNameText").removeClass("err");
			$("#projectNameText").addClass("ok");
		}
	})

	$("#problemPoint").blur(function() {
		var problemPoint = $("#problemPoint").val();
		if (problemPoint.length > 255) {
			$("#problemPointText").text("请输入小于255字的描述！");
			$("#problemPointText").addClass("err");
		} else {
			$("#problemPointText").text("输入正确！");
			$("#problemPointText").removeClass("err");
			$("#problemPointText").addClass("ok");
		}
	})

	$("#target").blur(function() {
		var target = $("#target").val();
		if (target.length > 255) {
			$("#targetText").text("请输入小于255字的描述！");
			$("#targetText").addClass("err");
		} else {
			$("#targetText").text("输入正确！");
			$("#targetText").removeClass("err");
			$("#targetText").addClass("ok");
		}
	})

	$("#marketStrategyTactics").blur(function() {
		var marketStrategyTactics = $("#marketStrategyTactics").val();
		if (marketStrategyTactics.length > 255) {
			$("#marketStrategyTacticsText").text("请输入小于255字的描述！");
			$("#marketStrategyTacticsText").addClass("err");
		} else {
			$("#marketStrategyTacticsText").text("输入正确！");
			$("#marketStrategyTacticsText").removeClass("err");
			$("#marketStrategyTacticsText").addClass("ok");
		}
	})

	$("#implementationPlan").blur(function() {
		var implementationPlan = $("#implementationPlan").val();
		if (implementationPlan.length > 255) {
			$("#implementationPlanText").text("请输入小于255字的描述！");
			$("#implementationPlanText").addClass("err");
		} else {
			$("#implementationPlanText").text("输入正确！");
			$("#implementationPlanText").removeClass("err");
			$("#implementationPlanText").addClass("ok");
		}
	})

	$("#resourceHelp").blur(function() {
		var resourceHelp = $("#resourceHelp").val();
		if (resourceHelp.length > 255) {
			$("#resourceHelpText").text("请输入小于255字的描述！");
			$("#resourceHelpText").addClass("err");
		} else {
			$("#resourceHelpText").text("输入正确！");
			$("#resourceHelpText").removeClass("err");
			$("#resourceHelpText").addClass("ok");
		}
	})

});
$(document).ready(function() {
	var status = $("#status").val();
	var progress = $("#progress").val();
	var left = 0;
	if(status!=7){
		$("#aaaa").hide();
	}else{
		left = progress * 2;
		$('.progress_btn').css('left', left);
		$('.progress_bar').animate({
			width: left
		}, 200);
		$('.text').html(progress + '%');
		$("#aaaa").show();
	}
});
function doSubmit() {
	var flag = 0;
	var projectName = $("#projectName").val();
	if (projectName.length < 1) {
		flag = 1;
		$("#projectNameText").text("请输入项目名称！");
		$("#projectNameText").addClass("err");
	}

	var money = $("#money").val();
	var moneyreg = /^-?\d*\.?\d*$/;
	if (!moneyreg.test(money.trim())) {
		flag = 1;
		$("#moneyText").text("请输入正确的数字金额！");
		$("#moneyText").addClass("err");
	}

	var costomerAnalysis = $("#costomerAnalysis").val();
	if (costomerAnalysis.length > 255) {
		flag = 1;
		$("#costomerAnalysisText").text("请输入小于255字的描述！");
		$("#costomerAnalysisText").addClass("err");
	}

	var decMakChainAnalysis = $("#decMakChainAnalysis").val();
	if (decMakChainAnalysis.length > 255) {
		flag = 1;
		$("#decMakChainAnalysisText").text("请输入小于255字的描述！");
		$("#decMakChainAnalysisText").addClass("err");
	}

	var competitorsAnalysis = $("#competitorsAnalysis").val();
	if (competitorsAnalysis.length > 255) {
		flag = 1;
		$("#competitorsAnalysisText").text("请输入小于255字的描述！");
		$("#competitorsAnalysisText").addClass("err");
	}

	var chancePoint = $("#chancePoint").val();
	if (chancePoint.length > 255) {
		flag = 1;
		$("#chancePointText").text("请输入小于255字的描述！");
		$("#projectNameText").addClass("err");
	}

	var problemPoint = $("#problemPoint").val();
	if (problemPoint.length > 255) {
		flag = 1;
		$("#problemPointText").text("请输入小于255字的描述！");
		$("#problemPointText").addClass("err");
	}

	var target = $("#target").val();
	if (target.length > 255) {
		flag = 1;
		$("#targetText").text("请输入小于255字的描述！");
		$("#targetText").addClass("err");
	}

	var marketStrategyTactics = $("#marketStrategyTactics").val();
	if (marketStrategyTactics.length > 255) {
		flag = 1;
		$("#marketStrategyTacticsText").text("请输入小于255字的描述！");
		$("#marketStrategyTacticsText").addClass("err");
	}

	var implementationPlan = $("#implementationPlan").val();
	if (implementationPlan.length > 255) {
		flag = 1;
		$("#implementationPlanText").text("请输入小于255字的描述！");
		$("#implementationPlanText").addClass("err");
	}

	var resourceHelp = $("#resourceHelp").val();
	if (resourceHelp.length > 255) {
		flag = 1;
		$("#resourceHelpText").text("请输入小于255字的描述！");
		$("#resourceHelpText").addClass("err");
	}

	var productType = $("#productType").val();
	if (productType == '') {
		flag = 1;
		$("#productTypeText").text("请选择产品种类！");
		$("#productTypeText").addClass("err");
	}

	var status = $("#status").val();
	if (status == '') {
		flag = 1;
		$("#statusText").text("请选择状态类型！");
		$("#statusText").addClass("err");
	}

	if (flag == 0) {
		$("#addForm").submit();
	}
	return false;
}

function getprogress() {
	var status = $("#status").val();
	if (status == 7) {
		$("#aaaa").show();
	} else {
		$("#aaaa").hide();
	}
	if (status == '') {
		$("#statusText").text("请输入状态分类！");
		$("#statusText").addClass("err");
	} else {
		$("#statusText").text("输入正确！");
		$("#statusText").removeClass("err");
		$("#statusText").addClass("ok");
	}
}

function testProjectType(){
	var productType = $("#productType").val();
	if (productType == '') {
		$("#productTypeText").text("请选择产品类型！");
		$("#productTypeText").addClass("err");
	} else {
		$("#productTypeText").text("输入正确！");
		$("#productTypeText").removeClass("err");
		$("#productTypeText").addClass("ok");
	}
}
