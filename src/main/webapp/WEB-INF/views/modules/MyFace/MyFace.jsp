<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>个人信息</title>
<meta name="decorator" content="default" />
<style type="text/css">
* {
	margin: 0px;
	padding: 0px;

	/*清队所有标签的内外边距*/
}

.clear {
	clear: both;
}

#c {
	margin: 10px;
	width: 100%;
}

#c1 {
	height: 100px;
	overflow: hidden;
}

#c2 {
	float: left;
	height: 700px;
	margin-top: 10px;
	width: 30%;
	overflow: hidden;
}

#c3 {
	float: right;
	height: 700px;
	margin-top: 10px;
	width: 70%;
	overflow: hidden;
}

#f {
	background: none repeat scroll 0 0 #CABF84;
	height: 132px;
	margin-top: 10px;
	overflow: hidden;
}

.info {
	padding: 10px 14px 15px;
	width: 180px;
	overflow: hidden;
	float: right;
}

.info_img {
	float: left;
	margin-right: 10px;
	width: 182px;
	height: 152px;
	overflow: visible;
	position: relative;
}

.text {
	font-size: 15px;
	color: #8f8f94;
	line-height: 150%;
}

.rightLabelB {
    font-size: 20px;
    color: #000;
    text-align: left;
}
.kjrk {
    width: 33%;
    height: 56px;
    display: inline-block;
    margin-bottom: 25px;
}
.c_ding_btn {
    display: inline-block;
    padding: 5px 12px;
    padding-top: 5px;
    padding-right: 12px;
    padding-bottom: 5px;
    padding-left: 12px;
    margin-bottom: 0;
    font-size: 16px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    border: 1px solid #c3c6ce;
    background: #fff;
    border-radius: 4px;
    min-width: 120px;
    color: #414141;
    outline: 0;
    text-decoration: none;
</style>
</head>
<body>
	<div id="c">
		<div id="c1" style="text-align: center;">
			<a href=""><img src="${ctxStatic }/images/oa/timg.jpg"  width="1000" height="100"/></a>
		</div>
		<div id="c2">
			<div class="info">
				<div class="info_img">
					<c:if test="${user.photo ne ''}">
						<img src="${user.photo }" url="${user.photo }" width="140"
						height="140">
					</c:if>
					<c:if test="${user.photo eq ''}">
						<img src="${ctxStatic }/images/oa/ava.jpg" width="140" height="140">
					</c:if>
				</div>
				<div style="text-align: center;">
					<span>${user.name }</span>
				</div>
				<div>
					<label>部门:</label> <label>${user.office.name}</label>
				</div>
				<div>
					<label>邮箱:</label> <label>${user.email}</label>
				</div>
				<div>
					<label>手机:</label> <label>${user.phone}</label>
				</div>

				<div>
					<label class="text">昨日收到日报数:</label> <a
						href="${ctx}/daily/tCheckBacklog/list">${countYstday}</a>
				</div>
			</div>

		</div>
		<div id="c3">
			
			<div style="margin-top:15px;">
				<span class="rightLabelB">快捷入口</span>
			</div>
			<div style="margin-top:20px;">
				<div class="kjrk">
					<a href="${ctx}/daily/tDaily/list"><div class="c_ding_btn">
						<img src="${ctxStatic }/images/oa/man.png" width="40" hight="40">
						<span>日报管理</span>
					</div><a>
				</div>
				<div class="kjrk">
					<a href="${ctx}/oa/weekly"><div class="c_ding_btn">
						<img src="${ctxStatic }/images/oa/rizhi.png" width="40" hight="40">
						<span>周报管理</span>
					</div></a>
				</div>
				<div class="kjrk">
					<a href="${ctx}/oa/costexcel"><div class="c_ding_btn">
						<img src="${ctxStatic }/images/oa/feiyong.jpg" width="40" hight="40">
						<span>费用统计</span>
					</div></a>
				</div>
			</div>
		<div style="margin-top:20px;">
				<div class="kjrk">
					<a href="${ctx}/oa/oaNotify/self"><div class="c_ding_btn">
						<img src="${ctxStatic }/images/oa/gonggao.png" width="40" hight="40">
						<span>通知公告</span>
					</div></a>
				</div>
		</div>
</div>
		<div class="clear"></div>

	</div>
</body>
</html>