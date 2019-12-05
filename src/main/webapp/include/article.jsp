<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/11/28
  Time: 下午7:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<c:set scope="page" var="path" value="${pageContext.request.contextPath}"/>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">文章管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">文章列表</a></li>
    <li>
    <li >
        <a href="javascript:showEditor();">添加文章</a></li>
    <li>
</ul>

<div>
    <table id="articleList"></table>
    <div id="articlePager" style="height: 35px" ></div>
</div>

<script type="text/javascript">
    function showEditor() {
        $("#for1")[0].reset();
        setGuru('');
        $("#modalTitle").text("添加文章");
        KindEditor.html("#editor_id","");
        $('#myModal').modal('toggle');
    }
    function setGuru(id) {
        $("#guruSelect").empty();
        let $op1=$("<option/>").attr('value',"0").text("通用文章");
        $("#guruSelect").append($op1);
        $.post('${path}/guru/findAll','',function (result) {
            for (let i=0;i<result.length;i++) {
                let $op;
                if(result[i].id==id){
                    $op = $("<option/>").attr('value',result[i].id).text(result[i].fname).attr('selected','selected');
                }else{
                    $op = $("<option/>").attr('value',result[i].id).text(result[i].fname);

                }
                $("#guruSelect").append($op);
            }
        },'json');

    }
    function showTable(id) {
        let data = $("#articleList").jqGrid("getRowData",id);
        $("#titleInput").val(data.title);
        $("#idInput").val(data.id);
        setGuru(data.userId)
        let status=data.status=='展示'?0:1;
        $("#statusSelect").val(status);
        $("#editor_id").val(data.contents);
        $("#modalTitle").text("预览文章");
        KindEditor.html("#editor_id",data.contents);

        $('#myModal').modal('toggle');
    }
    $(function () {
        $("#articleList").jqGrid(
            {
                url : '${path}/article/findAll',
                datatype : "json",
                colNames : [ 'ID', '标题', '状态','作者id','作者', '上传时间', '发布时间','内容',"操作" ],
                colModel : [
                    {name : 'id',hidden:true},
                    {name : 'title',align:"center",editable:true,editrules:{required:true}},
                    {name : 'status',align:"center",formatter:function (data) {
                            if(data=="0"){
                                return "展示";
                            }else{
                                return "不展示";
                            }
                        },editable:true,edittype:"select",editoptions:{value:"0:展示;1:不展示"}},
                    {name : 'userId',hidden:true},
                    {name : 'author',align:"center",editable:true,editrules:{required:true},formatter:function (data) {
                            if(data==""){
                                return "通用文章";
                            }else{
                                return data;
                            }
                        }},
                    {name : 'createTime',align:"center",formatter:function (data) {
                            var date = new Date(data);
                            return  date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
                        }},
                    {name : 'releaseTime',align:"center",formatter:function (data) {
                            var date = new Date(data);
                            return  date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
                        }},
                    {name : 'contents',hidden:true},
                    {
                        name: "haha", formatter: function (cellvalue, options, rowObject) {
                            let url = rowObject.url;
                            let fileName = rowObject.fileName;
                            return `<a class="btn btn-lg" href="javascript:showTable('`+rowObject.id+`');"><span class="glyphicon glyphicon-th-list"></span></a>`;
                        }, search: false,align:"center"
                    }
                ],
                rowNum : 5,
                rowList : [ 5,10, 15, 20 ],
                pager : '#articlePager',
                mtype : "post",
                viewrecords : true,
                styleUI:"Bootstrap",
                autowidth:true,
                multiselect:true,
                height:300,
                editurl:"${path}/article/edit"
            });
        $("#articleList").jqGrid('navGrid', '#articlePager', {edit : false,add : false,del : true,search:false,edittext:"编辑",addtext:"添加",deltext:"删除"},
            {},
            {},
            {
                closeAfterDel:true
            });
    });
</script>

