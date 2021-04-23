<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">贷款管理</h3>

                <div class="box-tools pull-left">
                    <a onclick="securityToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg"
                       href="/car/add2">添加</a>
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">

                </div>
                <table id="security_tab7" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>银行名称</th>
                        <th>贷款总额</th>
                        <th>贷款年限(期数)</th>
                        <th>剩余还款期数</th>
                        <th>剩余贷款总额</th>
                        <th>每期还款</th>
                        <th>更新时间</th>
                        <th>记录时间</th>
                        <th>类别</th>
                        <th>备注</th>
                        <th>操作</th>
                    </tr>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var security_tab7;
    $(function () {
        //初始化时间选择器
        $('#securityTime').datepicker({
            language: 'zh-CN',
            format: 'yyyy-mm-dd',
            autoclose: true,
            todayHighlight: true
        });
        //初始化表格

        var No = 0;
        security_tab7 = $('#security_tab7').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {
                "url": "/car/page2",
                "type": "post",
                "data": function(d) {
                    //自定义查询参数
                    d.roleId = $("#roleId").val();
                }
            },
            "columns": [
                {"data": null},
                {"data": "name"},
                {"data": "balance"},
                {"data": "card"},
                {"data": "scard"},
                {"data": "sbalance"},
                {"data": "month"},
                {"data": "update_date"},
                {"data": "create_date"},
                {"data": "type"},
                {"data": "remark"},
                {"data": null}
            ],
            "columnDefs": [
                {
                    targets: 0,
                    data: null,
                    render: function (data) {
                        No = No + 1;
                        return No;
                    }
                },
                {
                    "targets": -1,
                    "data": null,
                    "render": function (data) {
//					debugger;
                        var btn = "";
                            btn += '<a class="btn btn-xs btn-info" onclick="securityToListAjax();" data-title="修改" target="modal" modal="lg" href="/car/edit/'+ data.id+ '">修改</a> &nbsp;'
                                    + '<a class="btn btn-xs btn-default" callback="securityReload();" data-body="确认要删除吗？" target="ajaxTodo" href="/car/delete/'+ data.id + '">删除</a>';
                        return btn;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

        $("#securitySeek").on("click", function () {
//            reloadTable(security_tab7, "#securityTime", "#securityPremise");
            securityReload();
        });
    });

    function securityReload() {
        reloadTable(security_tab7);
       // var date = $("#securityTime").val();
       // var search = $("#securityPremise").val();
       // var roleId = $("#roleId").val();
       // var param = {
       //     "add_date": date,
       //     "code": search,
       //     "type":roleId
       // };
       // security_tab7.settings()[0].ajax.data = param;
       // security_tab7.ajax.reload();
    }

    function securityToListAjax() {
        list_ajax = security_tab7;
        console.log(list_ajax);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>