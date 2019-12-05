<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/11/27
  Time: 上午11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>

<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">轮播图管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">轮播图信息</a></li>
    <li>
    <li>
        <a href="${path}/banner/getEXCEL" id="outExcel">导出轮播图信息</a></li>
    <li>
    <li>
        <a href="${path}/banner/getEXCELModal" id="outExcelModel">导出轮播图模板</a></li>
    <li>
    <li>
        <a href="javascript:importExcel();" id="inExcel">导入轮播图信息</a></li>
    <li>
</ul>
<script type="text/javascript" >
    $(function () {

        $("#bannerList").jqGrid(
            {
                url : '${path}/banner/findAll',
                datatype : "json",
                colNames : [ 'ID', '名称', '图片', '描述', '链接','创建日期','状态' ],
                colModel : [
                    {name : 'id',hidden:true},
                    {name : 'name',align:"center",editable:true,editrules:{required:true}},
                    {name : 'url',align:"center",formatter:function (data) {
                            return "<img src='"+data+"' style='width: 100%;' />";
                        },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"}},
                    {name : 'description',align:"center",editable:true,editrules:{required:true}},
                    {name : 'href',align:"center",editable:true,editrules:{required:true}},
                    {name : 'createDate',align:"center",formatter:function (data) {
                            var date = new Date(data);
                            return  date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                        }},
                    {name : 'status',align:"center",formatter:function (data) {
                            if(data=="1"){
                                return "展示";
                            }else{
                                return "冻结";
                            }
                        },editable:true,edittype:"select",editoptions:{value:"1:展示;2:冻结"}}
                ],
                rowNum : 5,
                rowList : [ 5,10, 15, 20 ],
                pager : '#bannerPager',
                mtype : "post",
                viewrecords : true,
                styleUI:"Bootstrap",
                autowidth:true,
                height: 350,
                multiselect:true,
                editurl:"${path}/banner/edit"
            });
        $("#bannerList").jqGrid('navGrid', '#bannerPager', {edit : true,add : true,del : true,search:false,edittext:"编辑",addtext:"添加",deltext:"删除"},
            {
                closeAfterEdit:true,
                // frm ---> 表单对象
                beforeShowForm:function (frm) {
                    frm.find("#url").attr("disabled",true);

                }
            },{
                closeAfterAdd:true,
                afterSubmit:function (response,postData) {
                    var bannerID = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${path}/banner/upload",
                        datatype:"json",
                        type:"post",
                        data:{bannerId:bannerID},
                        // 指定的上传input框的id
                        fileElementId:"url",
                        success:function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#bannerList").trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            },{
            closeAfterDel:true
            });
        $("#bannerSubBtn").click(function () {
            $.ajaxFileUpload({
                url:"${path}/banner/uploadXls",
                datatype:"json",
                type:"post",
                // 指定的上传input框的id
                fileElementId:"fileInput",
                success:function (data) {
                    // 输出上传成功
                    // jqgrid重新载入
                    $("#bannerList").trigger("reloadGrid");
                }
            })
            $('#bannerModal').modal('toggle');
        });
    });
    function importExcel() {
        $("#fileInput").val("");
        $('#bannerModal').modal('toggle');
    }
</script>
<div class="modal fade" id="bannerModal"  role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 750px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="modalTitle">导入轮播图数据</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="for1">
                    <input name="id" type="text" hidden id="idInput" />
                    <div class="form-group">
                        <div class="col-sm-10">
                            <input type="file" id="fileInput" name="fileInput" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" id="bannerSubBtn" class="btn btn-primary">提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div>
    <table id="bannerList"></table>
    <div id="bannerPager" style="height: 35px" ></div>
</div>

