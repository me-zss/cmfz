<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shun
  Date: 2019/11/27
  Time: 下午4:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">专辑章节管理</a>
        </div>
    </div>
</nav>
<ul class="nav nav-tabs">
    <li class="active">
        <a href="#">专辑章节信息</a></li>
    <li>
</ul>

<script type="text/javascript">

    function playAudio(url) {
        $("#audio").attr("src",url);
        $('#myModal').modal('toggle');
    }


    $(function () {
        $("#chapterList").jqGrid(
            {
                url : '${path}/music/findAll',
                datatype : "json",
                height : 190,
                colNames : [ 'id', '专辑名', '作者', '播音', '集数','星级', '封面','状态','描述','内容','创建时间' ],
                colModel : [
                    {name : 'id',hidden:true},
                    {name : 'name',align:"center",editable:true,editrules:{required:true}},
                    {name : 'author',align:"center",editable:true,editrules:{required:true}},
                    {name : 'beam',align:"center",editable:true,editrules:{required:true}},
                    {name : 'episodes',align:"center",editrules:{required:true},width:50,formatter:function (data) {
                            if(!data){
                                return 0;
                            }else{
                                return data;
                            }
                        }},
                    {name : 'star',align:"center",editable:true,editrules:{required:true},width:50},
                    {name : 'pic',align:"center",editable:true,formatter:function (data) {
                            return "<img src='"+data+"' style='width: 100%;' /> ";
                        },edittype:"file",editoptions:{enctype:"multipart/form-data"}},
                    {name : 'status',align:"center",editable:true,edittype:"select",editoptions:{value:"1:展示;2:不展示"},formatter:function (data) {
                            if(data=="1"){
                                return "展示";
                            }else{
                                return "不展示";
                            }
                        }},
                    {name : 'description',align:"center",editable:true,editrules:{required:true}},
                    {name : 'content',align:"center",editable:true,editrules:{required:true}},
                    {name : 'createTime',align:"center",formatter:function (data) {
                            let date = new Date(data);
                            return date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
                        }}
                ],
                rowNum : 10,
                rowList : [ 10, 20, 30, 35 ],
                pager : '#chapterPager',
                viewrecords : true,
                multiselect : true,
                height:400,
                subGrid : true,
                autowidth: true,
                styleUI: "Bootstrap",
                editurl:"${path}/music/edit",
                subGridRowExpanded : function(subgrid_id, row_id) {
                    subGridCreate(subgrid_id,row_id);
                },
                subGridRowColapsed : function(subgrid_id, row_id) {
                    // this function is called before removing the data
                    //var subgrid_table_id;
                    //subgrid_table_id = subgrid_id+"_t";
                    //jQuery("#"+subgrid_table_id).remove();
                }
            });
        $("#chapterList").jqGrid('navGrid', '#chapterPager', {
            add : true,
            edit : true,
            del : true,
            search: false
        },{
            closeAfterEdit: true,
            afterSubmit:function (response,postData) {
                var musicId = response.responseJSON.musicId;
                $.ajaxFileUpload({
                    url:"${path}/music/upload",
                    datatype:"json",
                    type:"post",
                    data:{musicId:musicId},
                    // 指定的上传input框的id
                    fileElementId:"pic",
                    success:function (data) {
                        // 输出上传成功
                        // jqgrid重新载入
                        $("#chapterList").trigger("reloadGrid");
                    }
                })
                return postData;
            }
        },{
            closeAfterAdd: true,
            afterSubmit:function (response,postData) {
                var musicId = response.responseJSON.musicId;
                $.ajaxFileUpload({
                    url:"${path}/music/upload",
                    datatype:"json",
                    type:"post",
                    data:{musicId:musicId},
                    // 指定的上传input框的id
                    fileElementId:"pic",
                    success:function (data) {
                        // 输出上传成功
                        // jqgrid重新载入
                        $("#chapterList").trigger("reloadGrid");
                    }
                })
                return postData;
            }
        });
    });
    function subGridCreate(subgrid_id, row_id) {
        var c, pager_id;
        subgrid_table_id = subgrid_id + "_t";
        pager_id = "p_" + subgrid_table_id;
        $("#" + subgrid_id).html(
            "<table id='" + subgrid_table_id
            + "' class='scroll'></table><div id='"
            + pager_id + "' class='scroll'></div>");
        $("#" + subgrid_table_id).jqGrid(
            {
                url : "${path}/chapter/findByMusicId?musicId=" + row_id,
                datatype : "json",
                colNames : [ 'id','专辑id', '标题', '大小', '时长','链接地址','上传时间','文件名','操作' ],
                colModel : [
                    {name : "id",hidden: true},
                    {name : "musicId",hidden: true,editable:true},
                    {name : "name",align:"center",editable:true,editrules:{required:true}},
                    {name : "size",align:"center"},
                    {name : "time",align:"center"},
                    {name : "url",hidden:true},
                    {name : "createDate",align:"center",formatter:function (data) {
                            let date = new Date(data);
                            return date.getFullYear()+"-"+date.getMonth()+"-"+date.getDate();
                        }},
                    {name : "fileName",align:"center",editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"}},
                    {
                        name: "haha", formatter: function (cellvalue, options, rowObject) {
                            let url = rowObject.url;
                            let fileName = rowObject.fileName;
                            return `<a class="btn btn-lg" href="javascript:void();" onclick="playAudio('`+url+`');"><span class="glyphicon glyphicon-play-circle"></span></a><a class="btn btn-lg" href='${path}/chapter/down?fileName=`+fileName+`'  target="_blank"  ><span class="glyphicon glyphicon-download" ></span></a>`;
                        }, search: false,align:"center"
                    }
                ],
                rowNum : 5,
                pager : pager_id,
                height : '100%',
                autowidth: true,
                styleUI: "Bootstrap",
                editurl: '${path}/chapter/edit',
                multiselect: true
            });
        jQuery("#" + subgrid_table_id).jqGrid('navGrid',
            "#" + pager_id, {
                edit : true,
                add : true,
                del : true,
                search: false
            },{
                closeAfterEdit:true,
                beforeShowForm(frm){
                    frm.find("#createDate").attr("disabled",true);
                }
            },{
                closeAfterAdd: true,
                beforeShowForm(frm) {
                    frm.find("#musicId").val(row_id);
                },
                afterSubmit:function (response,postData) {
                    var chapterId = response.responseJSON.chapterId;
                    $.ajaxFileUpload({
                        url:"${path}/chapter/upload",
                        datatype:"json",
                        type:"post",
                        data:{chapterId:chapterId},
                        // 指定的上传input框的id
                        fileElementId:"fileName",
                        success:function (data) {
                            // 输出上传成功
                            // jqgrid重新载入
                            $("#"+subgrid_table_id).trigger("reloadGrid");
                        }
                    })
                    return postData;
                }
            });
    }


</script>

<div>
    <table id="chapterList"></table>
    <div id="chapterPager" style="height: 35px;"></div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audio" controls="controls" autoplay  src="">您的浏览器不支持audio标签</audio>
    </div>
</div>
