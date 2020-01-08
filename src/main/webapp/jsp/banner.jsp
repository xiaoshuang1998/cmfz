<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(function () {
        $("#bannerTable").jqGrid(
            {
                url : "${pageContext.request.contextPath}/banner/queryByPage",
                datatype : "json",
                colNames : [ 'ID', '标题', '图片', '超链接', '创建时间', '描述', '状态' ],
                colModel : [
                    {name : 'id',again : "center",hidden:true},
                    {name : 'title',align:"center",editable: true},
                    {name : 'url',align:"center",formatter:function (data) {
                            return "<img style='width: 180px;height: 80px' src='"+data+"'>"
                            },editable: true,edittype:"file",editoptions: {enctype:"multipart/form-data"}},
                    {name : 'href',align:"center",editable: true},
                    {name : 'createDate',align:"center",editable: true,editrules:{required:true},edittype: "date"},
                    {name : 'description',align:"center",editable: true,editrules:{required:true}},
                    {name : 'status',align:"center",formatter:function (data) {
                            if (data=="1"){
                                return "展示";
                            } else return "冻结";
                        },editable:true,editrules:{required:true},edittype:"select",editoptions: {value:"1:展示;2:冻结"}}
                ],
                rowNum : 5,
                rowList : [ 5, 10, 15 ],
                pager : '#bannerPage',
                sortname : 'id',
                mtype : "post",
                viewrecords : true,
                sortorder : "desc",
                caption : "轮播图信息",
                styleUI: "Bootstrap",
                autowidth: true,
                multiselect:true,
                height:"500px",
                editurl: "${pageContext.request.contextPath}/banner/save"
            });
        $("#bannerTable").jqGrid('navGrid', '#bannerPage', {edit : true,add : true,del : true,edittext:"编辑",addtext:"添加",deltext:"删除"},
            // 制定修改|添加|删除 之前 之后的事件
            // {} --> edit
            {
                closeAfterEdit: true,
                afterSubmit:function (response,postData) {
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        // 指定上传路径
                        url:"${pageContext.request.contextPath}/banner/uploadBanner",
                        type:"post",
                        datatype:"json",
                        // 发送添加图片的id至controller
                        data:{bannerId:bannerId},
                        // 指定上传的input框id
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    });
                    // 防止页面报错
                    return postData;
                }
            },{
                closeAfterAdd: true,
                // 数据库添加轮播图后 进行上传 上传完成后需更改url路径 需要获取添加轮播图的Id
                //                   editurl 完成后 返回值信息
                afterSubmit:function (response,postData) {
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        // 指定上传路径
                        url:"${pageContext.request.contextPath}/banner/uploadBanner",
                        type:"post",
                        datatype:"json",
                        // 发送添加图片的id至controller
                        data:{bannerId:bannerId},
                        // 指定上传的input框id
                        fileElementId:"url",
                        success:function (data) {
                            $("#bannerTable").trigger("reloadGrid");
                        }
                    });
                    // 防止页面报错
                    return postData;
                }
            },{
                closeAfterDel: true,
            });
        $("#bannerExport").click(function () {
            $.ajax({
                url:"${pageContext.request.contextPath}/banner/export",
                type:"post",
                datatype:"json",
                success: function (data) {
                    alert("成功导出！");
                }
            })
        })
    });
    function importBanner(){
        $.ajaxFileUpload({
                url: "${pageContext.request.contextPath}/banner/importBanner",
                datatype:"json",
                type: "post",
                fileElementId:"inputBanner",
                success :function (data) {
                    alert("导入成功");
                    $("#myModal2").modal("hide");
                    $("#bannerTable").trigger("reloadGrid");
                }
            }
        )
    }
</script>
<div>
    <h4>轮播图管理</h4>
</div>
<ul class="nav nav-tabs">
    <li><a>轮播图信息</a></li>
    <li><a id="bannerExport" href="javascript:void(0)">导出轮播图信息</a></li>
    <li><a data-toggle="modal" data-target="#myModal2">导入轮播图信息</a></li>
    <%--<li><a>Excel模板下载</a></li>--%>
</ul>
<div class="panel">
    <table id="bannerTable"></table>
    <div id="bannerPage" style="height: 50px"></div>
</div>