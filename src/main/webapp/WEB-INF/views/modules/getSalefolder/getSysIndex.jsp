<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/><c:set var="tabmode" value="${empty cookie.tabmode.value ? '0' : cookie.tabmode.value}"/>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{height: initial !important;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
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
	</script>
	
	
	
</head>
<body>
	<div id="main">
	<input type="hidden" value="${fns:getUser().loginName}" id="loginName1"/>
	<input type="hidden" value="${fns:getUser().office.name}" id="loginName2"/>
		<div id="header" class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="brand"><span id="productName">${fns:getConfig('productName')}</span></div>
				<ul id="userControl" class="nav pull-right">
					<li><a href="http://www.taohuadaoinfo.com/" target="_blank" title="访问官网主页"><i class="icon-home"></i></a></li>
					<li id="themeSwitch" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="主题切换"><i class="icon-th-large"></i></a>
						<ul class="dropdown-menu">
							<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
							<li><a href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
						</ul>
					</li>
					<li id="userInfo" class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#" title="个人信息">您好, ${fns:getUser().name}&nbsp;<span id="notifyNum" class="label label-info hide"></span></a>
						<ul class="dropdown-menu">
							<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
							<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
							<li><a href="${ctx}/oa/oaNotify/self" target="mainFrame"><i class="icon-bell"></i>&nbsp;  我的通知 <span id="notifyNum2" class="label label-info hide"></span></a></li>
						</ul>
					</li>
					<li><a href="${ctx}/logout" title="退出登录">退出</a></li>
					<li>&nbsp;</li>
				</ul>
				
				<div class="nav-collapse">
					<ul id="menu" class="nav" style="*white-space:nowrap;float:none;">
						<c:set var="firstMenu" value="true"/>
						<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
							<c:if test="${menu.parent.id eq '1'&&menu.isShow eq '1'}">
								<li class="menu ${not empty firstMenu && firstMenu ? ' active' : ''}">
									<c:if test="${empty menu.href}">
										<a class="menu" href="javascript:" data-href="${ctx}/sys/menu/tree?parentId=${menu.id}" data-id="${menu.id}"><span>${menu.name}</span></a>
									</c:if>
									<c:if test="${not empty menu.href}">
										<a class="menu" href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" data-id="${menu.id}" target="mainFrame"><span>${menu.name}</span></a>
									</c:if>
								</li>
								<c:if test="${firstMenu}">
									<c:set var="firstMenuId" value="${menu.id}"/>
								</c:if>
								<c:set var="firstMenu" value="false"/>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
	    </div>
	    <div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left" style="overflow:scroll;overflow-x:hidden;">
				</div>
				<div id="openClose" class="close">&nbsp;</div>
				<div id="right">
					
	<script type="text/javascript" src="${ctxStatic}/tb/sale/getSaleView.js?ver=1"></script>	
				
					
					<form:form class="form-horizontal">
						<input type="hidden" name="statu" value="${getSale.statu }">
						<input type="hidden" name="user1" value="${getSale.user.id }">
						<input type="hidden" name="user2" value="${fns:getUser().id}">
						<fieldset>
							<legend>申请详情</legend>
							<table class="table-form">
								<input type="hidden" value=${getSale.id } name="getSaleId"/>
								<tr>
									<td class="tit" width="160">标题</td>
									<td colspan="6" class="tit">${getSale.reason }</td>
								</tr>
								<tr>
									<td class="tit">姓名</td>
									<td class="tit">${getSale.user.name}</td>
									<td class="tit">公司</td>
									<td class="tit">${getSale.ename}</td>
									<td class="tit">部门</td>
									<td class="tit">${getSale.office.name}</td>
									<td class="tit">报销总金额</td>
									<td class="tit" colspan="2">
									<fmt:formatNumber type="number" value="${getSale.forMoney}" maxFractionDigits="2"/>元
									</td>
									
								</tr>
				
								<tr>
									<td class="tit">日期</td>
									<td class="tit">费用类型</td>
									<td class="tit">费用描述</td>
									<td class="tit">可报销金额</td>
									<c:if test="${getSale.statu=='审核通过'||getSale.statu=='同意' }">
									<td class="tit">允许报销金额</td>
									</c:if>
									<td class="tit">项目名称</td>
									<td class="tit">详细信息</td>
								</tr>
				
								<c:forEach items="${listOther}" var="list">
									<tr>
										<td class="tit"><fmt:formatDate value="${list.detailDate}" pattern="yyyy-MM-dd"/></td>
										<td class="tit">${list.costType}</td>
										<td class="tit">
											${list.costDescription}
											<c:if test="${list.costDescriptionId =='0501' }">
												出差天数：${list.day }天
												出差编号：${list.num }
												出发地：${list.origin }
												目的地：${list.destination }
											</c:if>
										</td>
										<td class="tit">
										<fmt:formatNumber type="number" value="${list.amountMoney}" maxFractionDigits="2"/>元
										</td>
										<c:if test="${getSale.statu=='审核通过'||getSale.statu=='同意' }">
										<td class="tit">
										<fmt:formatNumber type="number" value="${list.allowMoney}" maxFractionDigits="2"/>元
										</td>
										</c:if>
										<td class="tit">${list.projectName}</td>
										<td class="tit">${list.information}</td>
									</tr>
								</c:forEach>
								
				                <tr>
									<td class="tit">附件</td>
									<td colspan="6">
										<c:if test="${not empty getSale.fileAddress }">
											<a href="${ctx}/get/sale/downloadFiel?id=${getSale.id}">附件下载</a>
										</c:if>
										<c:if test="${empty getSale.fileAddress }">
											无附件
										</c:if>
									</td>
									
								</tr>
								<c:if test="${not empty getSale.prText }">
									<tr>
										<td class="tit">行政主管审核意见</td>
										<td colspan="6">${getSale.prText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty getSale.leaderText}">
									<tr>
										<td class="tit">部门经理审核意见</td>
										<td colspan="6">${getSale.leaderText}</td>
									</tr>
								</c:if>
				
								<c:if test="${not empty getSale.leadertwoText}">
									<tr>
										<td class="tit">主管审核意见</td>
										<td colspan="6">${getSale.leadertwoText}</td>
									</tr>
								</c:if>
				
								<c:if test="${not empty getSale.managerText}">
									<tr>
										<td class="tit">研发总监审核意见</td>
										<td colspan="6">${getSale.managerText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty getSale.prthreeText }">
									<tr>
										<td class="tit">出纳审核意见</td>
										<td colspan="6">${getSale.prthreeText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty getSale.prfourText }">
									<tr>
										<td class="tit">财务主管审核意见</td>
										<td colspan="6">${getSale.prfourText}</td>
									</tr>
								</c:if>
								<!--add  -->
								<c:if test="${not empty getSale.prtwoText }">
									<tr>
										<td class="tit">财务总监审核意见</td>
										<td colspan="6">${getSale.prtwoText}</td>
									</tr>
								</c:if>
								<c:if test="${not empty getSale.prfiveText }">
									<tr>
										<td class="tit">总经理审核意见</td>
										<td colspan="6">${getSale.prfiveText}</td>
									</tr>
								</c:if>
							</table>
						</fieldset>
				
						<act:histoicFlow procInsId="${getSale.act.procInsId}" />
						<div class="form-actions">
							<input id="btnCancel" class="btn" type="button" value="返 回"
								onclick="history.go(-1)" />
						</div>
						<div id="footer" class="row-fluid">
					            Copyright &copy; 2018 桃花岛办公平台 - Powered by 合肥办 V1.0.0
							</div>
					</form:form>
				
	
				
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