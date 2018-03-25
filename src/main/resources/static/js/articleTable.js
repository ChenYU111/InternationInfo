/*	   var $table;
        //初始化bootstrap-table的内容
        function InitMainTable () {
            //记录页面bootstrap-table全局变量$table，方便应用
            var queryUrl = '/TestUser/FindWithPager?rnd=' + Math.random()
            $table = $('#grid').bootstrapTable({
                url: queryUrl,                      // 请求后台的URL（*）
                method: 'post',                      // 请求方式（*）
                // toolbar: '#toolbar', //工具按钮用哪个容器
                striped: true,                      // 是否显示行间隔色
                cache: false,                       // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   // 是否显示分页（*）
                sortable: true,                     // 是否启用排序
                sortOrder: "asc",                   // 排序方式
                sidePagination: "server",           // 分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      // 初始化加载第一页，默认第一页,并记录
                pageSize: rows,                     // 每页的记录行数（*）
                pageList: [10, 25, 50, 100],        // 可供选择的每页的行数（*）
                search: true,                      // 是否显示表格搜索
                strictSearch: true,
                showColumns: true,                  // 是否显示所有的列（选择显示的列）
                showRefresh: true,                  // 是否显示刷新按钮
                minimumCountColumns: 2,             // 最少允许的列数
                clickToSelect: true,                // 是否启用点击选中行
                // height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     // 每一行的唯一标识，一般为主键列
                showToggle: true,                   // 是否显示详细视图和列表视图的切换按钮
                cardView: false,                    // 是否显示详细视图
                detailView: false,                  // 是否显示父子表
                // 得到查询的参数
                queryParams : function (params) {
                    // 这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                    var temp = {   
                        rows: params.limit,                         // 页面大小
                        page: (params.offset / params.limit) + 1,   // 页码
                        sort: params.sort,      // 排序列名
                        sortOrder: params.order // 排位命令（desc，asc）
                    };
                    return temp;
                },
                columns: [{
                    checkbox: true,  
                    visible: true                  // 是否显示复选框
                }, {
                    field: 'title',
                    title: '标题',
                    sortable: true
                }, {
                    field: 'original',
                    title: '原创',
                    sortable: true
                }, {
                    field: 'isprivate',
                    title: '私密',
                    sortable: true,
                    formatter: emailFormatter
                }, {
                    field: 'ispublish',
                    title: '发布',
                    formatter: linkFormatter
                }, {
                    field: 'createTime',
                    title: '发布时间',
                    formatter: dateFormatter
                }, {
                    field:'ID',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: actionFormatter
                }, ],
                onLoadSuccess: function () {
                },
                onLoadError: function () {
                    showTips("数据加载失败！");
                },
                onDblClickRow: function (row, $element) {
                    var id = row.ID;
                    EditViewById(id, 'view');
                },
            });
        };
*/
        
        
        
        
        
        
        
        
        $(document).ready(function () {  
            $('#table').bootstrapTable({  
                url: '@Url.Action("SearchBookInfo", "Home")',  
                queryParamsType: '',              //默认值为 'limit' ,在默认情况下 传给服务端的参数为：offset,limit,sort  
                queryParams: queryParams,  
                method: "post",  
                pagination: true,  
                pageNumber: 1,  
                pageSize: 2,  
                pageList: [10, 20, 50, 100],  
                sidePagination: "server",         //分页方式：client客户端分页，server服务端分页（*）  
                striped: true,                    //是否显示行间隔色  
                cache: false,  
                uniqueId: "BookId",               //每一行的唯一标识，一般为主键列  
                height:300,  
                paginationPreText: "上一页",  
                paginationNextText: "下一页",  

                columns: [{
                    checkbox: true,  
                    visible: true                  // 是否显示复选框
                }, {
                    field: 'title',
                    title: '标题',
                    sortable: true
                }, {
                    field: 'original',
                    title: '原创',
                    sortable: true
                }, {
                    field: 'isprivate',
                    title: '私密',
                    sortable: true,
                    formatter: emailFormatter
                }, {
                    field: 'ispublish',
                    title: '发布',
                    formatter: linkFormatter
                }, {
                    field: 'createTime',
                    title: '发布时间',
                    formatter: dateFormatter
                }, {
                    field:'ID',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: actionFormatter
                }, ],
            });  
        });  
      
        //查询条件  
        function queryParams(params) {  
            return {  
                pageSize: params.pageSize,  
                pageIndex: params.pageNumber,  
                Title: $.trim($("#txtTitle").val()),  
                Author: $.trim($("#txtAuthor").val()),  
                Publish: $.trim($("#txtPublish").val()),  
            };  
        }  
      
        //查询事件  
        function SearchData() {  
            $('#table').bootstrapTable('refresh', { pageNumber: 1 });  
        }  
      
        //编辑操作  
        function EditBook(bookId){  
            alert("编辑操作，图书ID：" + bookId);  
        }  
      
        //删除操作  
        function DeleteBook(bookId) {  
            if (confirm("确定删除图书ID：" + bookId + "吗？"))  
            {  
                alert("执行删除操作");  
            }  
        }  
      
        //批量删除  
        function BtchDeleteBook(){  
            var opts = $('#table').bootstrapTable('getSelections');  
            if (opts == "") {  
                alert("请选择要删除的数据");  
            }  
            else {  
                var idArray = [];  
                for (var i = 0; i < opts.length; i++) {  
                    idArray.push(opts[i].BookId);  
                }  
                if (confirm("确定删除图书ID：" + idArray + "吗？")) {  
                    alert("执行删除操作");  
                }  
            }  
        }  
      
        function getTime(/** timestamp=0 **/) {  
            var ts = arguments[0] || 0;  
            var t, y, m, d, h, i, s;  
            t = ts ? new Date(ts * 1000) : new Date();  
            y = t.getFullYear();  
            m = t.getMonth() + 1;  
            d = t.getDate();  
            h = t.getHours();  
            i = t.getMinutes();  
            s = t.getSeconds();  
            // 可根据需要在这里定义时间格式  
            return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d) + ' ' + (h < 10 ? '0' + h : h) + ':' + (i < 10 ? '0' + i : i) + ':' + (s < 10 ? '0' + s : s);  
        }  
      
        function getShortTime(/** timestamp=0 **/) {  
            var ts = arguments[0] || 0;  
            var t, y, m, d, h, i, s;  
            t = ts ? new Date(ts * 1000) : new Date();  
            y = t.getFullYear();  
            m = t.getMonth() + 1;  
            d = t.getDate();  
            // 可根据需要在这里定义时间格式  
            return y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);  
        }  
      
        