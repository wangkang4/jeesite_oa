var category = [];
var sales=[];
var num=[];
var spending = [];
$(document).ready(function() {  
	getHistogramX();
});
function getparams(list){
	category = [];
	sales=[];
	num=[];
	spending = [];
	for(var i in list){
		category.push(list[i].category);
		num.push(list[i].num);
		sales.push(list[i].sales);
		spending.push(list[i].spending);
	}
	getHistogramY();
}
function getHistogramX(){
	url="get";
	var X = $("select[name='category']").find("option:selected").val();
	params={"X":X};
	$.ajax({
		url:url,
		dataType:"json",
		data:params,
		success:function(result){
			getparams(result.list);
		}
	});
}
function getHistogramY(){
	var Y = $("select[name='num']").find("option:selected").val();
	if(Y=="money"){
		getHighcharts(category,sales);
	}
	if(Y=="count"){
		getHighcharts(category,num);
	}
	if(Y=="forMoney"){
		getHighcharts(category,spending);
	}
}
function getHighcharts(category,num){
	var chart = {
		      type: 'column'
    };
    var title = {
       text: $("select[name='category']").find("option:selected").text()+
  	 	"-"+$("select[name='num']").find("option:selected").text()+"柱状图"   
    };
    var xAxis = {
       categories: category,
       crosshair: true
    };
    var yAxis = {
       min: 0,
       title: {
          text: $("select[name='num']").find("option:selected").text()         
       }      
    };
	var unit = "";
	if($("select[name='num']").find("option:selected").val()=="count"){
		unit="{point.y:f} 个";
	}else{
		unit="{point.y:.1f} 元";
	}
	var tooltip = {
			headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
			pointFormat: '<tr><td style="color:{series.color};padding:0">'+
				$("select[name='num']").find("option:selected").text()+
				': </td>' +
  				'<td style="padding:0"><b>'+unit+'</b></td></tr>',
	footerFormat: '</table>',
	shared: true,
	useHTML: true
	};
	var plotOptions = {
			column: {
				pointPadding: 0.2,
				borderWidth: 0
			}
	};  
	var credits = {
			enabled: false
	};
	var series= [{
		name: $("select[name='category']").find("option:selected").text(),
		data: num
	}];     
      
  	var json = {};   
	json.chart = chart; 
	json.title = title;   
	json.tooltip = tooltip;
	json.xAxis = xAxis;
	json.yAxis = yAxis;  
	json.series = series;
	json.plotOptions = plotOptions;  
	json.credits = credits;
	$('#container').highcharts(json);
}