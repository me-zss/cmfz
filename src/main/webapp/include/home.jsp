<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<c:set var="path" scope="page" value="${pageContext.request.contextPath}"/>
<div class="jumbotron" style="height: 70px;padding-top: 5px;" >
    <h3>欢迎使用持明法洲后台管理系统！</h3>
</div>

<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="${path}/include/img/a.jpg" alt="..." style="height: 80%;width: 100%;" >
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="${path}/include/img/d.jpeg" alt="..." style="height: 80%;width: 100%;">
            <div class="carousel-caption">
            </div>
        </div>
        <div class="item">
            <img src="${path}/include/img/g.jpg" alt="..." style="height: 80%;width: 100%;">
            <div class="carousel-caption">
            </div>
        </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>