<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}</title>
<meta name="decorator" content="blank" />
<c:set var="tabmode"
	value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}" />
<style type="text/css">
#main {
	padding: 0;
	margin: 0;
}

#main .container-fluid {
	padding: 0 4px 0 6px;
}

#header {
	margin: 0 0 8px;
	position: static;
}

#header li {
	font-size: 14px;
	_font-size: 12px;
}

#header .brand {
	font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
	font-size: 26px;
	padding-left: 33px;
}

#footer {
	margin: 8px 0 0 0;
	padding: 3px 0 0 0;
	font-size: 11px;
	text-align: center;
	border-top: 2px solid #0663A2;
}

#footer, #footer a {
	color: #999;
}

#left {
	height: initial !important;
}

#left .collapse {
	position: static;
}

#userControl>li>a { /*color:#fff;*/
	text-shadow: none;
}

#userControl>li>a:hover, #user #userControl>li.open>a {
	background: transparent;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
			// 绑定菜单单击事件
			$("#menu a.menu").click(function(){
				// 一级菜单焦点
				$("#menu li.menu").removeClass("active"); 
				/* $(this).parent().addClass("active"); */
				// 左侧区域隐藏
				if ($(this).attr("target") == "mainFrame"){
					$("#left,#openClose").hide();
					wSizeWidth();
					// <c:if test="${tabmode eq '1'}"> 隐藏页签
					$(".jericho_tab").hide();
					$("#mainFrame").show();//</c:if>
					return true;
				}
				// 左侧区域显示
				$("#left,#openClose").show();
				if(!$("#openClose").hasClass("close")){
					$("#openClose").click();
				}
				// 显示二级菜单
				var menuId = "#menu-" + $(this).attr("data-id");
				if ($(menuId).length > 0){
					$("#left .accordion").hide();
					$(menuId).show();
				}else{
					// 获取二级菜单数据
					$.get($(this).attr("data-href"), function(data){
						if (data.indexOf("id=\"loginForm\"") != -1){
							alert('未登录或登录超时。请重新登录，谢谢！');
							top.location = "${ctx}";
							return false;
						}
						$("#left .accordion").hide();
						$("#left").append(data);
						// 链接去掉虚框
						$(menuId + " a").bind("focus",function() {
							if(this.blur) {this.blur()};
						});
						// 二级标题
						$(menuId + " .accordion-heading a").click(function(){
							$(menuId + " .accordion-toggle i").removeClass('icon-chevron-down').addClass('icon-chevron-right');
							if(!$($(this).attr('data-href')).hasClass('in')){
								$(this).children("i").removeClass('icon-chevron-right').addClass('icon-chevron-down');
							}
						});
						// 二级内容
						$(menuId + " .accordion-body a").click(function(){
							$(menuId + " li").removeClass("active");
							$(menuId + " li i").removeClass("icon-white");
							$(this).children("i").addClass("icon-white");
						});
						// 展现三级
						$(menuId + " .accordion-inner a").click(function(){
							var href = $(this).attr("data-href");
							if($(href).length > 0){
								return false;
							}
							return addTab($(this)); 
						});
						// 默认选中第一个菜单
						$(menuId + " .accordion-body a:first i").click();
					});
				}
				// 大小宽度调整
				wSizeWidth();
				return false;
			});
			// 初始化点击第一个一级菜单
			$("#menu a.menu:eq(1) span").click();
			$("#userInfo .dropdown-menu a").mouseup(function(){
				return addTab($(this), true);
			});
			// 鼠标移动到边界自动弹出左侧菜单
			$("#openClose").mouseover(function(){
				if($(this).hasClass("open")){
					$(this).click();
				}
			});
			function getNotifyNum(){
				$.get("${ctx}/oa/oaNotify/self/count?updateSession=0&t="+new Date().getTime(),function(data){
					var num = parseFloat(data);
					if (num > 0){
						$("#notifyNum,#notifyNum2").show().html("("+num+")");
					}else{
						$("#notifyNum,#notifyNum2").hide()
					}
				});
			}
			getNotifyNum(); 
			setInterval(getNotifyNum, ${oaNotifyRemindInterval});

			//合同类别展示
			typeTextOwn();
		});
		function addTab($this, refresh){
			$(".jericho_tab").show();
			$("#mainFrame").hide();
			$.fn.jerichoTab.addTab({
                tabFirer: $this,
                title: $this.text(),
                closeable: true,
                data: {
                    dataType: 'iframe',
                    dataLink: $this.attr('href')
                }
            }).loadData(refresh);
			return false;
		}

		//合同类别展示
		function typeTextOwn(){
			var payTypeBig = $("input[name='payTypeBig']").val();
			var payTypeSmall = $("input[name='payTypeSmall']").val();
			console.info(payTypeBig,payTypeSmall);
			var strUrl = "${ctx}/tb/pay/selectType";
			$.ajax({
				url:strUrl,
				dataType:"json",
				success:function(data){
					blist = data.blist;
					slist = data.slist;
					console.info(blist+slist);
					for(var i in blist){
						if(blist[i].id==payTypeBig){
							$("input[name='payTypeBig']").parent().append(blist[i].type);
						}
					}
					for(var i in slist){
						if(slist[i].id==payTypeSmall){
							$("input[name='payTypeSmall']").parent().append(slist[i].type);
						}
					}
				}
			})
		}
	</script>



</head>
<body>
	<div id="main">
		<input type="hidden" value="${fns:getUser().loginName}"
			id="loginName1" /> <input type="hidden"
			value="${fns:getUser().office.name}" id="loginName2" />
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand">
					<span id="productName">${fns:getConfig('productName')}</span>
				</div>
				<ul id="userControl" class="nav pull-right">
					<li><a href="http://www.taohuadaoinfo.com/" target="_blank"
						title="访问官网主页"><i class="icon-home"></i></a></li>
					<li id="themeSwitch" class="dropdown"><a
						class="dropdown-toggle" data-toggle="dropdown" href="#"
						title="主题切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictList('theme')}" var="dict">
								<li><a href="#"
									onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li>
							</c:forEach>
							<li><a
								href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
						</ul></li>
					<li id="userInfo" class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#" title="个人信息">您好,
							${fns:getUser().name}&nbsp;<span id="notifyNum"
							class="label label-info hide"></span>
					</a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i
									class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i
									class="icon-lock"></i>&nbsp; 修改密码</a></li>
							<li><a href="${ctx}/oa/oaNotify/self" target="mainFrame"><i
									class="icon-bell"></i>&nbsp; 我的通知 <span id="notifyNum2"
									class="label label-info hide"></span></a></li>
						</ul></li>
					<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>

				<div class="nav-collapse">
					<ul id="menu" class="nav"
						style="*white-space: nowrap; float: none;">
						<c:set var="firstMenu" value="true" />
						<c:forEach items="${fns:getMenuList()}" var="menu"
							varStatus="idxStatus">
							<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
								<li
									class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}">
									<c:if test="${empty menu.href}">
										<a class="menu" href="javascript:"
											data-href="${ctx}/sys/menu/tree?parentId=${menu.id}"
											data-id="${menu.id}"><span>${menu.name}</span></a>
									</c:if> <c:if test="${not empty menu.href}">
										<a class="menu"
											href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}"
											data-id="${menu.id}" target="mainFrame"><span>${menu.name}</span></a>
									</c:if>
								</li>
								<c:if test="${firstMenu}">
									<c:set var="firstMenuId" value="${menu.id}" />
								</c:if>
								<c:set var="firstMenu" value="false" />
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left" style="overflow: scroll; overflow-x: hidden;">
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">

					<script type="text/javascript"
						src="${ctxStatic}/tb/pay/payView.js?ver=1"></script>
					<form:form class="form-horizontal">
						<input type="hidden" name="statu" value="${pay.statu }">
						<fieldset>
							<legend>申请详情</legend>
							<table class="table-form">
								<tr>
									<td class="tit">标题</td>
									<td colspan="3">${pay.reason }</td>
								</tr>
								<tr>
									<td class="tit">合同名称</td>
									<td>${pay.projectName}</td>

									<td class="tit">合同编号</td>
									<td>${pay.projectNum}</td>
								</tr>
								<tr>
									<td class="tit">合同总金额</td>
									<td><fmt:formatNumber type="number" value="${pay.money}"
											maxFractionDigits="2" pattern="#0.00" />元</td>
									<td class="tit">合同签署时间</td>
									<td><fmt:formatDate value="${pay.projectDate}" type="both"
											pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td class="tit">本次应付款金额</td>
									<td><fmt:formatNumber type="number"
											value="${pay.payMoney}" maxFractionDigits="2" pattern="#0.00" />元
									</td>
									<td class="tit">本次最晚付款时间</td>
									<td><fmt:formatDate value="${pay.lastTime}" type="both"
											pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td class="tit">付款类别</td>
									<td><input name="payTypeBig" type="hidden"
										value=${pay.payTypeBig }></td>
									<td class="tit">付款类型</td>
									<td><input name="payTypeSmall" type="hidden"
										value=${pay.payTypeSmall }></td>
								</tr>
								<tr>
									<td class="tit">付款方式</td>
									<td><c:if test="${pay.payMethods==2 }">电汇</c:if> <c:if
											test="${pay.payMethods==1 }">现金</c:if> <c:if
											test="${pay.payMethods==3 }">延期支票</c:if></td>
									<td class="tit">已付款金额</td>
									<td><fmt:formatNumber type="number"
											value="${pay.amountPaid }" maxFractionDigits="2"
											pattern="0.00" />元</td>
								</tr>
								<tr>
									<td class="tit">收款人名称</td>
									<td>${pay.payeeName }</td>
									<td class="tit">收款人账户</td>
									<td>${pay.payeeAccount }</td>
								</tr>
								<tr>
									<td class="tit">申请人</td>
									<td>${pay.createBy.name}</td>
									<td class="tit">申请日期</td>
									<td><fmt:formatDate value="${pay.createDate}" type="both"
											pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<td class="tit" colspan="2">申请人公司</td>
									<td colspan="2" align="center">${pay.ename }</td>
								</tr>
								<tr>
									<td class="tit">因付款情况说明</td>
									<td colspan="3">${pay.notes }</td>
								</tr>

								<c:if test="${not empty pay.proneText }">
									<tr>
										<td class="tit">部门经理意见</td>
										<td>${pay.proneText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty pay.prtwoText}">
									<tr>
										<td class="tit">研发总监意见</td>
										<td>${pay.prtwoText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty pay.prthreeText}">
									<tr>
										<td class="tit">主管意见</td>
										<td>${pay.prthreeText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty pay.prsixText}">
									<tr>
										<td class="tit">商务主管意见</td>
										<td>${pay.prsixText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty pay.prfourText}">
									<tr>
										<td class="tit">财务总监意见</td>
										<td>${pay.prfourText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty pay.prsevText }">
									<tr>
										<td class="tit">总经理意见</td>
										<td>${pay.prsevText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty pay.prfiveText}">
									<tr>
										<td class="tit">出纳</td>
										<td>${pay.prfiveText}</td>
									</tr>
								</c:if>
							</table>
						</fieldset>

						<act:histoicFlow procInsId="${pay.act.procInsId}" />
						<div class="form-actions">
							<input class="btn" type="button" value="打 印"
								onclick="tiaozhuan();" /> <input id="btnCancel" class="btn"
								type="button" value="返 回" onclick="history.go(-1)" /> <input
								class="btn" type="button" value="作废" onclick="zuofei();" />
						</div>
						<div id="footer" class="row-fluid">Copyright &copy; 2018
							桃花岛办公平台 - Powered by 合肥办 V1.0.0</div>
					</form:form>
					<script type="text/javascript">
						function zuofei(){
							var msg="您确定作废此条付款内容？";
							if(confirm(msg)==true){
								window.location="${ctx}/tb/pay/back?id=${pay.id}"
							}else{
								
							}
						}
						
					</script>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			frameObj.height((strs[0] < minHeight ? minHeight : strs[0]) - headerObj.height() - footerObj.height() - (strs[1] < minWidth ? 42 : 28));
			$("#openClose").height($("#openClose").height() - 5);// <c:if test="${tabmode eq '1'}"> 
			$(".jericho_tab iframe").height($("#right").height() - tabTitleHeight); // </c:if>
			wSizeWidth();
		}
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		}
		function openCloseClickCallBack(b){
			$.fn.jerichoTab.resize();
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>