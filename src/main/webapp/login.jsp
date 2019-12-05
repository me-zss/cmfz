<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <c:set var="path" value="${pageContext.request.contextPath}" scope="page"/>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="${path}/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${path}/assets/img/favicon.png">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Login Page - Now UI Kit by Creative Tim</title>
<%--    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />--%>
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
<%--    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />--%>
    <!-- CSS Files -->
    <link href="${path}/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${path}/assets/css/now-ui-kit.css?v=1.1.0" rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="${path}/assets/css/demo.css" rel="stylesheet" />
    <!-- Canonical SEO -->
    <link rel="canonical" href="" />
    <!--  Social tags      -->
    <meta name="keywords" content="">
    <meta name="description" content="">
</head>

<body class="login-page sidebar-collapse">
<!-- Navbar -->
<nav class="navbar navbar-expand-lg bg-primary fixed-top navbar-transparent " color-on-scroll="400">
    <div class="container">

        <div class="navbar-translate">
            <a class="navbar-brand" href="#" rel="tooltip" data-placement="bottom">
                持明法洲-后台管理系统
            </a>
            <button class="navbar-toggler navbar-toggler" type="button" data-toggle="collapse" data-target="#navigation" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-bar bar1"></span>
                <span class="navbar-toggler-bar bar2"></span>
                <span class="navbar-toggler-bar bar3"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse justify-content-end" data-nav-image="${path}/assets/img/blurred-image-1.jpg">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="javascript:void();">权利越大，责任越大</a>
                </li>


            </ul>
        </div>
    </div>
</nav>
<!-- End Navbar -->
<div class="page-header" filter-color="orange">
    <div class="page-header-image" style="background-image:url(${path}/assets/img/login.jpg)"></div>
    <div class="container">
        <div class="col-md-4 content-center">
            <div class="card card-login card-plain">
                <form class="form" method="" action="">
                    <div class="header header-primary text-center">
                        <div class="logo-container">
                            <img src="${path}/assets/img/now-logo.png" alt="">
                        </div>
                    </div>
                    <div class="content">
                        <span id="errorMsg" class="text-center text-danger" style="font-size: 15px;" ></span>
                        <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons users_circle-08"></i>
                                </span>
                            <input type="text" id="nameInput" class="form-control" placeholder="请输入用户名">
                        </div>
                        <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                    <i class="now-ui-icons text_caps-small"></i>
                                </span>
                            <input type="password"  id="passwdInput" placeholder="请输入密码" class="form-control"  />
                        </div>
                        <div class="input-group form-group-no-border input-lg">
                                <span class="input-group-addon">
                                   <img id="codeImg" src="${path}/security/getCode" />
                                </span>
                            <input type="text"  id="codeInput" placeholder="请输入验证码" class="form-control"  />
                        </div>
                    </div>
                    <div class="footer text-center">
                        <a href="javascript:void();" id="loginBtn" class="btn btn-primary btn-round btn-lg btn-block">登录</a>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <footer class="footer">
        <div class="container">
            <nav>
                <ul>
                    <li>
                        <a href="javascript:void();">
                            Creative Tim
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void();">
                            About Us
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void();">
                            Blog
                        </a>
                    </li>
                    <li>
                        <a href="javascript:void();">
                            MIT License
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="copyright">
                &copy;
                <script>
                    document.write(new Date().getFullYear())
                </script>, Designed by Shunshun. Coded by zshunshun
            </div>
        </div>
    </footer>
</div>
</body>
<!--   Core JS Files   -->
<script src="${path}/assets/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="${path}/assets/js/core/popper.min.js" type="text/javascript"></script>
<script src="${path}/assets/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="${path}/assets/js/plugins/bootstrap-switch.js"></script>
<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
<script src="${path}/assets/js/plugins/nouislider.min.js" type="text/javascript"></script>
<!--  Plugin for the DatePicker, full documentation here: https://github.com/uxsolutions/bootstrap-datepicker -->
<script src="${path}/assets/js/plugins/bootstrap-datepicker.js" type="text/javascript"></script>
<!-- Share Library etc -->
<script src="${path}/assets/js/plugins/jquery.sharrre.js" type="text/javascript"></script>
<!-- Control Center for Now Ui Kit: parallax effects, scripts for the example pages etc -->
<script src="${path}/assets/js/now-ui-kit.js?v=1.1.0" type="text/javascript"></script>
<script type="text/javascript">
    function clearMsg() {
        $("#errorMsg").text("");
    }
    $(function () {
        $("#nameInput").blur(clearMsg);
        $("#passwdInput").blur(clearMsg);
        $("#codeInput").blur(clearMsg);
        $("#loginBtn").click(function () {
            let username = $("#nameInput").val();
            let password = $("#passwdInput").val();
            let checkCode = $("#codeInput").val();
            $.post('${path}/manager/login',{username:username,password:password,checkCode,checkCode},function (result) {
                if(result.status=='ok'){
                    window.location.href='${path}/index.jsp';
                }else if(result.status=='error'){
                    $("#errorMsg").text("登录失败！用户名或密码错误！");
                }else{
                    $("#errorMsg").text("登录失败！验证码错误！");
                }
            });
        });
        $("#codeImg").click(function () {
            $("#codeImg").attr("src","${path}/security/getCode?a="+Math.random());
        });
    });
</script>
</html>