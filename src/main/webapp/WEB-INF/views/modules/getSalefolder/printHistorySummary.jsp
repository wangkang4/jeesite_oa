<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<meta charset="utf-8" />
<meta name="decorator" content="default" />
<title>打印报销</title>
<style type="text/css">
       
        #sale {
          position:relative;
          width: 840px;
          margin:20px auto;
        }
        
        #sale .top{
          text-align: center;
          width: 60%;
          margin:0 auto;
          font-size: 28px;

          border-bottom:1px solid rgb(40,44,52);
          padding-bottom: 8px;
          box-shadow:0 0 0 2px rgb(255,255,255),
                     0 4px 0 rgb(40,44,52);
          /* box-shadow:0rem 0.1rem 0rem 0.2rem rgb(40,44,52); */
        }

        #sale .two{
          margin-top: 20px;
          line-height: 30px;
          font-size: 16px;
        }

        #sale .two>span{
          display: inline-block;
          text-align: center;
          width: 150px;
        }
        #sale .two>.name{
          display: inline-block;
          text-align: center;
          width: 430px;
        }

        #sale table{
          width: 100%;
          text-align: center;
          /*line-height: 80px;*/
          font-size: 14px;
          border:1px solid rgb(40,44,52);
        }
        #sale table td{
          padding:4px;
          line-height: 20px;
          border-top:1px solid rgb(40,44,52);
          border-left:1px solid rgb(40,44,52);
          width: 20%;
        }
       
        #sale table tr>td:last-child{
          border-right:1px solid rgb(40,44,52);
        }
        #sale table tr:last-child>td{
          border-bottom:1px solid rgb(40,44,52);
        }
        #sale .bottom>span{
          display: inline-block;
          width: 24%;
        }

        #sale table tr .black{
    			width: 20%;
    			background-color:#999;
		        text-align:center;
    		}
    		
    	#sale table tr>.his{
    			background-color:#999;
    		}
       
		 #sale table tr>.proc{
		 		line-height:20px;
    			width: 20%;
    			font-size:14px;
		        text-align:center;
    		}
        #sale .bottom{
          line-height: 48rem;
          font-size: 16px;
        }
        #img{
   	height:100%;
   	width:100%;
   	position:absolute;
   	top:-5%;
   	left:20%;
   }
   img{
   	height:100%;
   	width:auto;
   	top:0px;
   }
   .footer{
   		font-size: 12px;
   		color: #686868;
   		margin-top: 6px;
   		text-indent: 1rem;
   }
   .header{
   		font-size: 14px;
   		line-height: 18px;
   		color: #686868;
   		overflow: hidden;
   		text-indent: 1rem;
   }
   .header>div{
   		overflow: hidden;
   		
   }
   .header>div>.font_one{
   		float: left;
   		font-size: 18px;
   		font-weight: bold;
   		color: #333;
   }
   .header>div>.font_two{
   		margin-left: 2rem;
   		float: left;
   		line-height: 22px;
   }
   .header>div>.font_three{
   		float: right;
   		line-height: 22px;
   }
</style>
<script type="text/javascript">
$(document).ready(function() {
	dayin();
});
function dayin(){
	bdhtml=window.document.body.innerHTML;//获取当前页的html代码  
	sprnstr="<!--startprint-->";//设置打印开始区域  
	eprnstr="<!--endprint-->";//设置打印结束区域  
	prnhtml=bdhtml.substring(bdhtml.indexOf(sprnstr)+18); //从开始代码向后取html  
	prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));//从结束代码向前取html 
	window.document.body.innerHTML=prnhtml;
	window.print();  
	window.document.body.innerHTML=bdhtml; 
}
</script>
</head>
<body>
<!--startprint-->
	<div class="table" id="sale" style="display:block">
		<div class="header">
			<div>
				<span class="font_one">北京桃花岛办公系统</span>
				<span class="font_two">在线办公</span>
				<span class="font_three">您好,${fns:getUser().getName() }</span>
			</div>
			
		</div>
        <div class="top">
            	（${getSale.costType }）费用报销单
        </div>
        <div class="two">
            <span class="name" style="text-indent:1rem;text-align:left;">单位：北京桃花岛信息技术有限公司</span>
            <span><fmt:formatDate value="${getSale.createDate}" pattern="yyyy年MM月dd日"/></span>
            <span>第${fn:substring(getSale.reason,0,10)}号</span>
        </div>
        <table border="0" cellspacing="0">
          <tr>
			<td class="proc his">日期</td>
			<td class="proc his">费用描述</td>
			<td class="proc his">可报销金额</td>
			<td class="proc his">项目名称</td>
			<td class="proc his">详细信息</td>
		  </tr>
		  <c:forEach items="${listOther}" var="list">
			<tr>
				<td class="proc"><fmt:formatDate value="${list.detailDate}" pattern="yyyy-MM-dd"/></td>
				<td class="proc">${list.costDescription}</td>
				<td class="proc">${list.amountMoney}元</td>
				<td class="proc">${list.projectName}</td>
				<td class="proc">${list.information}</td>
			</tr>
		  </c:forEach>
          <tr>
          	  <td class="black">附单据张数</td>
              <td style="width:19%;text-align:left;text-indent:2rem;">
              	${listOther.size() }
              </td>
              <td class="black">金额</td>
              <td colspan="2" style="text-align:left;text-indent:2rem;">
				<fmt:formatNumber type="number" value="${getSale.forMoney}" maxFractionDigits="2" pattern="#.00"/>元
              </td>
          </tr>
          <tr>
              <td class="black">
                  <span>申请人</span>
              </td>
              <td style="text-align:left;text-indent:2rem;" colspan="1">${getSale.user.name}</td>
              <td class="black">
                  <span>所属公司、部门</span>
              </td>
              <td style="text-align:left;text-indent:2rem;" colspan="2">${getSale.ename} ${getSale.office.name}</td>
          </tr>
          <tr>
          	<td class="proc his">审批人职位</td>
          	<td class="proc his">审批人</td>
          	<td class="proc his">审批时间</td>
          	<td class="proc his" colspan="2">审批意见</td>
          </tr>
          <c:forEach items="${historyList}" var="hlist">
					<tr>
						<td class="proc">${hlist.name}</td>
						<td class="proc">${hlist.assignee}</td>
						<td class="proc">${fn:substring(hlist.endTime,0,19)}</td>
						<td class="proc" colspan="2">${hlist.message}</td>
					</tr>
				</c:forEach>
        </table>
        <div class="footer">
	            Copyright &copy; 2018 桃花岛办公平台 - Powered by 合肥办 V1.0.0
		</div>
	    <div id="img">
	    	<img alt="" src="${ctxStatic}/images/oa/mark.png">
	    </div>
    </div>
	<!--endprint--> 
</body>

</html>