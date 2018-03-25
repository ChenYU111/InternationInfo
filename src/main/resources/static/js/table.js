/**
 * 导出按钮
 *
 * @returns
 */
/*function exportExcel() {
    var url = '/admin/sms/exportExcel2?';
    var param = $('#fm').serialize();
    var href = url + param;
    $("#export").attr('href', href);
}*/

/**
 * 获取所有的选中的行
 *
 * @returns
 */
function getIdSelections() {
    return $.map($('#table_list').bootstrapTable('getSelections'), function (row) {
        return row.id
    });
}

/**
 * 批量重发
 *
 * @returns
 */
/*function retrysmslog() {

    var url = '/admin/sms/ResendSms/';
    var ids = getIdSelections();
    if (ids == null || ids == undefined || ids == "") {
        layer.msg('请选择一列数据!', {
            time: 2000 // 2s后自动关闭
        });
    } else {
        layer.confirm("确认要重发吗，重发后不能恢复", {title: "重发确认"}, function (index) {
            $.ajax({
                url: url + ids,
                type: "GET",
                dataType: "json",
                async: true,
                success: function (msg) {
                    layer.msg(msg.message, {time: 2000}, function () {
                        $('#table_list').bootstrapTable("refresh");
                    });
                }

            });
        });
    }
}*/

/**
 * 重置
 *
 * @returns
 */
/*function resetSearch() {
    $("#factoryName").val("");
    $("#phone").val("");
    $("#start").val("");
    $("#end").val("");
    $('#resent').removeAttr('checked');
    $('#sourceSystem').val('');
    $('#content').val('');
    $('#uuid').val('');
    $('#status').val('');
    $('#msgType').val('');
    $('#table_list').bootstrapTable('refresh', urlChoose("/admin/sms/querylist"));
}
*/
/**
 * 加载数据
 */
/*jQuery(document).ready(function ($) {
    $("#table_list").bootstrapTable(urlChoose("/admin/sms/querylist"));
// alert($('#endTime').val());

    $("#search").click(function () {
        $('#table_list').bootstrapTable('refresh', urlChoose("/admin/sms/querylist"));
    });
});*/

/**
 * 表格初始化
 *
 * @param url
 * @returns
 */
function urlChoose(url) {
    var bootstrapTableParms = {
        // 使用get请求到服务器获取数据
        method: "POST",
        // 必须设置，不然request.getParameter获取不到请求参数
        contentType: "application/x-www-form-urlencoded",
        // 获取数据的Servlet地址
        //<!--url:"",-->
        url: url,
        escape: true,
        // 表格显示条纹
        striped: true,
        // 启动分页
        pagination: true,
        // 每页显示的记录数
        pageSize: 20,
        // 当前第几页
        pageNumber: 1,
        // 记录数可选列表
        pageList: [20, 50, 100],
        // 是否启用查询
        sortOrder: 'desc',

        // search: true,

        /**
         * 导出功能
         */
// showExport : true, <!--是否显示导出按钮-->
// exportDataType : "selected", <!--basic'导出当前页, 'all'导出全部, 'selected'导出选中项.-->

// showExport: true, // 是否显示导出按钮
        buttonsAlign: "right",  // 按钮位置
        exportTypes: ['excel'],  // 导出文件类型
        Icons: 'glyphicon-export',
        exportOptions: {
            ignoreColumn: [0, 11],  // 忽略某一列的索引
            fileName: '短信',  // 文件名称设置
            worksheetName: 'sheet1',  // 表格工作区名称
            tableName: '短信',
            excelstyles: ['background-color', 'color', 'font-size', 'font-weight'],
// onMsoNumberFormat: DoOnMsoNumberFormat
        },


        showRefresh: true, //<!--是否显示刷新功能-->
        showToggle: true, //<!--显示的样子-->
// showColumns: true, <!--要展示的选择项-->
        iconSize: 'outline',
        minimumCountColumns: 2,// <!--最少允许的列数-->
        clickToSelect: true, //<!--是否启用点击选中行-->

        // 表示服务端请求
        sidePagination: "server",
        // 设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        // 设置为limit可以获取limit, offset, search, sort, order
        queryParamsType: "undefined",
        queryParams: queryParams,
        // json数据解析
        responseHandler: function (res) {
            return {
                "rows": res.content,
                "total": res.totalElements
            };
        },
        columns: [
            /* 设置全选 */
            {
                field: 'state',
                checkbox: true
            },
            {
                title: '#',
                formatter: function (value, row, index) {
                    return index+1;
                }
            }, {
                title: "分类",
                field: "category",
                sortable: true
            },/* {
                title: "子分类",
                field: "subCategory"
            }, */{
                title: "UUID",
                field: "uuid",
                sortable: true,
                width: 100
            }, {
                title: "消息内容",
                field: "content",
                sortable: true,
                width: '12%',
                formatter: function (value, row, index) {
                    var nameString = value.substring(0, 10) + '......';
                    return '<span style="cursor:pointer;" title="' + escapeHtml(value) + '">' + escapeHtml(nameString) + '</span>';
                }
            }, {
                title: "消息类型",
                field: "msgType",
                sortable: true,
                formatter: function (value, row, index) {
                    if (value == '1')
                        return '企业微信';
                    else if (value == '2')
                        return '邮件';
                    else if (value == '0')
                        return '短信';
                }
            }, {
                title: "原内容",
                field: "requestContent",
                sortable: true,
                formatter: function (value, row, index) {
                    var nameString = value.substring(0, 10) + '......';
                    return '<span style="cursor:pointer;" title="' + escapeHtml(value) + '">' + escapeHtml(nameString) + '</span>';
                }
            }, {
                title: "电话",
                field: "phoneNum",
                sortable: true
            }, {
                title: "创建时间",
                field: "createTime",
                sortable: true
            }, {
                title: "原系统",
                field: "app",
                sortable: true,
                formatter: function (value, row, index) {
                    if (value == null || value == undefined) {
                        return "<span style='color:red'></span> ";
                    } else {
                        return value.name;
                    }
                }
            }, {
                title: "目标系统",
                field: "targetSystem",
                sortable: true
            }, {
                title: "状态",
                field: "status",
                sortable: true
            }, {
                title: "操作",
                field: "empty",
                formatter: function (value, row, index) {
                    var operateHtml = '<div class="btn-group"><button class="btn btn-primary btn-xs" type="button" onclick="details(\'' + row.id + '\')"><i class="fa fa-edit"></i>&nbsp;消息详情</button> &nbsp;';
                    if (row.status.toLowerCase() == 'resent') {
                        operateHtml = operateHtml + '<button class="btn btn-info btn-xs" type="button" onclick="resentdetails(\'' + row.id + '\')"><i class="fa fa-external-link"></i>&nbsp;重发详情</button> &nbsp;';
                    }
                    operateHtml = operateHtml + '</div>';
                    /*
					 * operateHtml = operateHtml + '<button class="btn
					 * btn-primary btn-xs" type="button"
					 * onclick="retrysms(\''+row.id+'\')"><i class="fa
					 * fa-remove"></i>&nbsp;重发</button>';
					 */
                    return operateHtml;
                }
            }]
    };
    return bootstrapTableParms;
}

/**
 * 传递的查询参数
 *
 * @param params
 * @returns
 */
function queryParams(params) {
    return {
        searchText: params.searchText,
        pageSize: params.pageSize,
        pageNumber: params.pageNumber,
        sortName: params.sortName,
        sortOrder: params.sortOrder,
        start: $("#start").val(),
        end: $("#end").val(),
        phone: $("#phone").val(),
        category: $("#factoryName").val(),
        subCategory: $("#factoryName").val(),
        resent: $('#resent').is(':checked'),
        sourceSystem: $('#sourceSystem').val(),
        content: $('#content').val(),
        uuid: $('#uuid').val(),
        status: $('#status').val(),
        msgType: $('#msgType').val()
    };
}

/**
 * 详情
 *
 * @param id
 * @returns
 */
function details(id) {
    layer.open({
        type: 2,
        title: '消息详情',
        shadeClose: true,
        shade: false,
        area: ['893px', '600px'],
        content: '/admin/sms/details/' + id,
        end: function (index) {
        }
    });
}

/**
 * 重发详情
 *
 * @param uuid
 * @returns
 */
function resentdetails(id) {
    layer.open({
        type: 2,
        title: '重发',
        shadeClose: true,
        shade: false,
        area: ['893px', '600px'],
        content: '/admin/sms/resent/' + id,
        end: function (index) {
            $('#table_list').bootstrapTable("refresh");
        }
    });
}

/*function escapeHtml(string) {
    var entityMap = {
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': '&quot;',
        "'": '&#39;',
        "/": '&#x2F;'
    };
    return String(string).replace(/[&<>"'\/]/g, function (s) {
        return entityMap[s];
    });
}*/

