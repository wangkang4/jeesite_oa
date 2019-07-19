<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
    <meta name="decorator" content="default"/>
    <title>员工基本信息</title>
    <script type="text/javascript">
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
    <script type="text/javascript">
        $(document).ready(function(){
            var text = "";
            //实时筛选，不用点击按钮
            setInterval(function(){
                text = $('#input').val();//获取文本框输入
                if($.trim(text) != ""){
                    $("#contentTable tr:not('#theader')").hide().filter(":contains('"+text+"')").show();
                }else{
                    $('#contentTable tr').show();//当删除文本框的内容时，又重新显示表格所有内容
                }
            },100);
        })
    </script>
    <script type="text/javascript">
        function upload(){
            var file = $("input[name='applyAddress']").val("");
            var formData = new FormData();
            formData.append("file",$('#file1')[0].files[0]);
            var xhr;
            if(window.XMLHttpRequest){
                xhr = new XMLHttpRequest();
            }else{
                xhr = new ActiveXObject('Microsoft.XMLHTTP');
            }
            //进度条部分
            xhr.upload.onprogress = function (evt) {
                console.log(evt.lengthComputable);
                if (evt.lengthComputable) {
                    var percentComplete = Math.round(evt.loaded * 100 / evt.total);
                    document.getElementById('progress').value = percentComplete;
                }
            };
            xhr.open("post","upload",true);
            xhr.send(formData);
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var result = xhr.responseText;
                    if(result!=null&&result!=""&&result!=undefined){
                        file.val(result);
                        alertx("上传成功");
                    }else{
                        alertx("上传失败");
                    }
                }
            }
        }
    </script>
</head>
<body>
<form:form id="searchForm" modelAttribute="oversee"
           action="${ctx}/tb/oversee/seeList" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <label>关键字</label>
    <%--<sys:treeselect id="createBy" name="createBy.id" value="${oversee.createBy.id}" labelName="createBy.name"
                    labelValue="${oversee.createBy.name}"
                    title="用户" url="/sys/office/treeData?type=3" cssClass="required" allowClear="true"
                    notAllowSelectParent="true"/>--%>
    <input type="text" name="" id="input" value="" placeholder="请输入查询关键字"/>
    <input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
    <input type="button" class="btn btn-primary" value="清除"
    <%--   seeList这里跳转的是Controller@RequestMapping("seeList")        --%>
           onclick="javascript:window.location.href= '${ctx}/tb/oversee/seeList'">
    <fieldset>
        <legend>测试</legend>
        <table id="contentTable" class="table-form">
            <tr>
                <td class="tit">文件上传</td>
                <td>
                    <form style="margin-bottom:0px;" action="" enctype="multipart/form-data" method="post">
                        <input type="file" id="file1" name="file" style="width:180px">
                        <input type="button" value="确认上传" onclick = "upload()">
                        <progress value="0" max="100" id="progress" style="height: 20px; width: 100%"></progress>
                    </form>
                </td>
            </tr>
            <tr>
                <td class="tit" >备注</td>
                <td colspan="3">
					<textarea name="report"
                              rows="5" maxlength="2000" cssStyle="width:500px">${purchase.report}</textarea>

                </td>
            </tr>
        </table>
    </fieldset>
</form:form>
<ul class="nav nav-tabs" id="list">
    <sys:message content="${message}"/>
    <table id="contentTable" class="table table-striped table-bordered table-condensed">
        <thead>
        <tr>
            <th>部门</th>
            <th>员工编号</th>
            <th>登录名</th>
            <th>名字</th>
            <th>邮箱</th>
            <th>角色</th>
            <th>手机号</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="oversee">
            <tr>
                <td>${oversee.officeName}</td>
                <td>${oversee.userId}</td>
                <td>${oversee.loginName}</td>
                <td>${oversee.userName}</td>
                <td>${oversee.email}</td>
                <td>${oversee.role}</td>
                <td>${oversee.phone}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pagination">${page}</div>
</body>
</html>