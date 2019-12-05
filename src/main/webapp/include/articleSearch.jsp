<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/11/29
  Time: 上午10:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>

<div class="container">
    <div class="row">
        <div class="col-sm-3 col-sm-offset-4">
            <div class="input-group">
                <input type="text" class="form-control">
                <span class="input-group-btn">
                     <button class="btn btn-info" type="button">点击搜索</button>
                </span>
            </div>
        </div>
    </div>
    <div class="row" style="margin-top: 15px;">
        <div class="col-sm-12">
            <nav class="navbar navbar-default" role="navigation">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">搜索结果</a>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <td>标题</td>
                        <td>状态</td>
                        <td>作者</td>
                        <td>内容</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>haha</td>
                        <td>haha</td>
                        <td>wozhida</td>
                        <td>dsfd</td>
                    </tr>
                    <tr>
                        <td>haha</td>
                        <td>haha</td>
                        <td>wozhida</td>
                        <td>dcxvvfdddddddddddddddddddddddddddddddddddddddddsdffffffffffffffffffffffffffffffffffffsdgfdhfdgfdfgfgdffghdgsdlkfjhidsyuthgfsalkdfhsfd</td>
                    </tr>
                </tbody>
            </table>
            <nav aria-label="...">
                <ul class="pager">
                    <li><a href="#">上一页</a></li>
                    <li><a href="#">下一页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

