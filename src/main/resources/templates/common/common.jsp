<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath %>"/>


<link href="${_cdnStaticResourcesPath}/resources/static/css/shop.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/skin/blue/skin.css" rel="stylesheet" type="text/css" id="skin" />
<link href="${_cdnStaticResourcesPath}/resources/static/css/Sellerber.css" type="text/css"  rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/css/bkg_ui.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/font/css/font-awesome.min.css"  rel="stylesheet" type="text/css" />
<link href="${_cdnStaticResourcesPath}/resources/static/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/css/style.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/css/ace.min.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/css/font-awesome.min.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${_cdnStaticResourcesPath}/resources/static/js/plugins/zTree/3.5.30/css/metroStyle/metroStyle.css" type="text/css">
<link href="${_cdnStaticResourcesPath}/resources/static/js/plugins/message/message.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/js/plugins/alert/alert.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/js/plugins/alert/style.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/js/plugins/zeroModal/zeroModal.css" type="text/css" rel="stylesheet" />
<link href="${_cdnStaticResourcesPath}/resources/static/js/plugins/dialog/css/dialog.css" type="text/css" rel="stylesheet" />

<style>
    /** 表格的分页内容的排版样式 */
    .dataTables_wrapper .dataTables_length{
        float: left;
        padding-top: 10px;
    }
    .dataTables_wrapper .dataTables_info{
        float: left;
        padding-top: 10px;
        padding-left: 20px;
    }
    .dataTables_wrapper .dataTables_paginate {
        float: right;
        padding-top: 10px;
        text-align: right
    }

    /** 表格tr颜色*/
    /*.tr_lock_color{
        background-color: #FCDEB4!important;!**行数据状态为锁定状态下的颜色*!
    }
    .tr_disable_color{
        background-color: #FFBFC9!important;!**行数据状态为禁用状态下的颜色*!
    }*/

    /** 解决jquery table的表格th头的字体默认颜色。拒绝被ace的css覆盖影响*/
    .dataTable th[class*="sorting_disabled"]{
        color: inherit!important;
    }
</style>




<script src="${_cdnStaticResourcesPath}/resources/static/js/jquery-1.9.1.min.js" type="text/javascript" ></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/h-ui/H-ui.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/h-ui/H-ui.admin.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/layer/layer.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/laydate/laydate.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/jquery.dataTables.min.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/jquery.dataTables.bootstrap.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/Sellerber.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/shopFrame.js" type="text/javascript"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/jquery.nicescroll.js" type="text/javascript"></script>
<script type="text/javascript" src="${_cdnStaticResourcesPath}/resources/static/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${_cdnStaticResourcesPath}/resources/static/js/plugins/zTree/3.5.30/js/jquery.ztree.all.min.js"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/message/message.js"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/alert/alert.min.js"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/zeroModal/lib/mustache.js"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/zeroModal/zeroModal.js"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/plugins/dialog/js/dialog.js"></script>
<!--[if lt IE 9]>
<script src="${_cdnStaticResourcesPath}/resources/static/js/html5shiv.js"></script>
<script src="${_cdnStaticResourcesPath}/resources/static/js/respond.min.js"></script>
<script type="text/javascript" src="${_cdnStaticResourcesPath}/resources/static/js/PIE/PIE_IE678.js"></script>
<![endif]-->

<script type="text/javascript">
    //jquery datatable通用的提示信息配置
    var lang = {
        "sProcessing": "数据加载中...",
        "sLengthMenu": "每页 _MENU_ 条",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项",
        "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "无符合条件的记录",
        "sLoadingRecords": "数据加载中...",
        "sInfoThousands": ",",
        "oPaginate": {
            "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页",
            "sJump": "跳转"
        }
    };

    //启用bootstrap的tootip提示功能
    $(function () { $("[data-toggle='tooltip']").tooltip(); });
</script>

<!-- 静态资源服务器地址(不使用jsp的ctx的好处是，方便动静分离，静态资源可以独立存放在某服务器中) -->
<input type="hidden" name="cdnStaticResourcesPath" id="cdnStaticResourcesPath" value="${_cdnStaticResourcesPath}" />