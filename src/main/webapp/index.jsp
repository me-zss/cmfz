<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/11/26
  Time: 下午2:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<html>
<head>
    <c:set scope="page" value="${pageContext.request.contextPath}" var="path"/>
    <title>Title</title>

    <link rel="stylesheet" href="${path}/tboot/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/tboot/css/back.css">
    <link rel="stylesheet" href="${path}/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <link rel="stylesheet" href="${path}/jqgrid/css/jquery-ui.css">
    <%--<script src="${path}/assets/js/core/jquery.3.2.1.min.js"></script>--%>
    <script src="${path}/tboot/js/jquery-2.2.1.min.js"></script>
    <script src="${path}/tboot/js/bootstrap.min.js"></script>
    <script src="${path}/jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
    <script src="${path}/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <script src="${path}/tboot/js/ajaxfileupload.js"></script>
    <script charset="utf-8" src="${path}/kindeditor/kindeditor-all-min.js"></script>
    <script charset="utf-8" src="${path}/kindeditor/lang/zh-CN.js"></script>
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <%--<link rel="stylesheet" href="${path}/boot/css/bootstrap.min.css">
    <link rel =“ stylesheet” href =“${path}/boot/css/ui.jqgrid-bootstrap.css”>
    <link rel =“ stylesheet” href =“${path}/boot/css/ui.jqgrid.css”>--%>
   <%-- <link rel="stylesheet" href="${path}/boot/css/back.css">--%>
    <style type="text/css">
        body{
            padding-bottom: 0;
            margin-bottom: 0;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- 左边栏 -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
        </div>

        <!-- 右边栏 -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="javascript:void(0);">欢迎：${manager.username}</a></li>
                <li><a href="javascript:void(0);" id="logout" >退出登录<span class="sr-only">(current)</span></a></li>

            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2" >
            <!--创建基本手风琴-->
            <div class="panel-group" id="accordion">

                <!--第一个面板-->
                <div class="panel panel-default">
                    <!--面板标题-->
                    <div class="panel-heading" role="tab" id="headingOne">

                        <h4 class="panel-title">
                            <!--data-toggle collapse 折叠效果 -->
                            <a data-toggle="collapse"  href="#collapseOne" data-parent="#accordion" >
                                用户管理
                            </a>
                        </h4>

                    </div>
                    <!--面板内容-->
                    <div id="collapseOne" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/users.jsp');">用户列表</a></li>
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/UserEcharts.jsp');">注册趋势图</a></li>
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/UserMap.jsp');">地理分布图</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--第二个面板-->
                <div class="panel panel-default">
                    <!--面板标题-->
                    <div class="panel-heading" role="tab" id="headingTwo">

                        <h4 class="panel-title">
                            <!--data-toggle collapse 折叠效果 -->
                            <a data-toggle="collapse"  href="#collapseTwo" data-parent="#accordion" >
                                上师管理
                            </a>
                        </h4>

                    </div>
                    <!--面板内容-->
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="">上师列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--第三个面板-->
                <div class="panel panel-default">
                    <!--面板标题-->
                    <div class="panel-heading" role="tab" id="headingThree">

                        <h4 class="panel-title">
                            <!--data-toggle collapse 折叠效果 -->
                            <a data-toggle="collapse"  href="#collapseThree" data-parent="#accordion" >
                                文章管理
                            </a>
                        </h4>

                    </div>
                    <!--面板内容-->
                    <div id="collapseThree" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/article.jsp');">文章列表</a></li>
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/articleSearch.jsp');">文章搜索</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--第四个面板-->
                <div class="panel panel-default">
                    <!--面板标题-->
                    <div class="panel-heading" role="tab" id="headingFour">

                        <h4 class="panel-title">
                            <!--data-toggle collapse 折叠效果 -->
                            <a data-toggle="collapse"  href="#collapseFour" data-parent="#accordion" >
                                专辑管理
                            </a>
                        </h4>

                    </div>
                    <!--面板内容-->
                    <div id="collapseFour" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/chapter.jsp');">专辑列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!--第五个面板-->
                <div class="panel panel-default">
                    <!--面板标题-->
                    <div class="panel-heading" role="tab" id="headingFive">

                        <h4 class="panel-title">
                            <!--data-toggle collapse 折叠效果 -->
                            <a data-toggle="collapse"  href="#collapseFive" data-parent="#accordion" >
                                轮播图管理
                            </a>
                        </h4>

                    </div>
                    <!--面板内容-->
                    <div id="collapseFive" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="list-group">
                                <li class="list-group-item"><a href="javascript:$('#contentBody').load('${path}/include/banner.jsp');">轮播图列表</a></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div id="contentBody" class="col-sm-10" >

        </div>
    </div>
</div>
<br>
<div class="panel panel-default text-center" style="height:50px;width:100%;clear:both;margin-top:-50px;margin-bottom: 0;">
    <div class="panel-footer" >@周顺顺 zshunshun2018@gmail.com</div>
</div>

</body>

<%--<script src="${path}/boot/js/jquery-3.4.1.min.js"></script>
<script src="${path}/boot/js/bootstrap.min.js"></script>
<script src="${path}/boot/js/jquery.jqGrid.min.js"></script>
<script src="${path}/boot/js/grid.locale-cn.js"></script>
<script src="${path}/boot/js/ajaxfileupload.js"></script>--%>

<div class="modal fade" id="myModal"  role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 750px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalTitle">Modal title</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="for1">
                    <input name="id" type="text" hidden id="idInput" />
                    <div class="form-group">
                        <label  class="col-sm-2 control-label" style="width: 8%;text-align: left" >标题</label>
                        <div class="col-sm-10">
                            <input type="text" id="titleInput" name="title" class="form-control" placeholder="请输入标题">
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label" style="width: 8%;text-align: left">状态</label>
                        <div class="col-sm-10">
                            <select name="status" id="statusSelect" class="form-control">
                                <option value="0">展示</option>
                                <option value="1">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label  class="col-sm-2 control-label" style="width: 8%;text-align: left">上师</label>
                        <div class="col-sm-10">
                            <select name="userId" id="guruSelect" class="form-control">
                                <option value="0">通用文章</option>
                            </select>
                        </div>
                    </div>
                    <textarea id="editor_id" name="contents" style="width:700px;height:300px;">
                     </textarea>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="subBtn" class="btn btn-primary">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    KindEditor.ready(function(K) {
        $("#for1")[0].reset();
        let options={
            uploadJson : '${path}/article/upload',
            fileManagerJson : '${path}/article/findAllImage',
            allowFileManager : true,
            afterBlur:function () {
                this.sync();
            }
        };
        window.editor = K.create('#editor_id',options);
    });
    $(function () {
        $("#contentBody").load('${path}/include/home.jsp');
        $("#logout").click(function () {
            $.post('${path}/manager/logout','',function (result) {
                alert("您已退出系统！");
                window.location.href='${path}/login.jsp';
            },'json');
        });
        $("#subBtn").click(function () {

            $.post('${path}/article/addOrUpdate',$('#for1').serialize(),function (result) {
                //隐藏模态框
                //重新载入列表
                $("#for1")[0].reset();
                $('#myModal').modal('toggle');
                $("#articleList").trigger("reloadGrid");
            });
        });
    });
</script>
</html>
