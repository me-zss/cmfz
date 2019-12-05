<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/12/1
  Time: 上午10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">用户管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">用户信息</a></li>
    <li>
    <li>
        <a href="${path}/user/getEXCEL" id="outExcel">导出用户信息</a></li>
    <li>
</ul>
<script type="text/javascript" >
    function changeStatus(id,status) {
        console.log(id);
        console.log(status);
        $.post("${path}/user/changeStatus",{id:id,status:status},function (result) {
            $("#userList").trigger("reloadGrid");
        });
    }
    $(function () {

        $("#userList").jqGrid(
            {
                url : '${path}/user/findAll',
                datatype : "json",
                colNames : [ 'ID', '法号', '姓名', '性别','状态', '地址','头像','注册时间','最后登录时间','签名' ],
                colModel : [
                    {name : 'id',hidden:true},
                    {name : 'fname',align:"center",search: true},
                    {name : 'name',align:"center",search: true},
                    {name : 'sex',align:"center",search: true},
                    {name : 'status',align:"center",formatter:function (cellvalue, options, rowObject) {
                            if(cellvalue=="1"){
                                return `<a href="javascript:changeStatus('`+rowObject.id+`','2');" >展示</a>`;
                            }else{
                                return `<a href="javascript:changeStatus('`+rowObject.id+`','1');" >冻结</a>`;
                            }
                        },editable:true,edittype:"select",editoptions:{value:"1:展示;2:冻结"},search: true},
                    {name : 'loc',align:"center",search: true},
                    {name : 'img',align:"center",formatter:function (data) {
                            return "<img src='"+data+"' style='width: 100%;' />";
                        },search: false},
                    {name : 'createTime',align:"center",formatter:function (data) {
                            var date = new Date(data);
                            return  date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                        },search: false},
                    {name : 'lastOnlineTime',align:"center",formatter:function (data) {
                            var date = new Date(data);
                            return  date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                        },search: false},
                    {name : 'signature',align:"center",search: true}
                ],
                rowNum : 5,
                rowList : [ 5,10, 15, 20 ],
                pager : '#userPager',
                mtype : "post",
                viewrecords : true,
                styleUI:"Bootstrap",
                autowidth:true,
                height: 350
            });
        $("#userList").jqGrid('navGrid', '#userPager', {edit : false,add : false,del : false,search:true,edittext:"编辑",addtext:"添加",deltext:"删除"},
            {},{},{},{

                sopt : [ 'cn', 'eq', 'ne' ]
            });
    });
</script>


<div>
    <table id="userList"></table>
    <div id="userPager" style="height: 35px" ></div>
</div>