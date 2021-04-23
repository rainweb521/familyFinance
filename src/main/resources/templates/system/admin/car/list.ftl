<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">存款管理</h3>

                <div class="box-tools pull-left">
                    <a onclick="securityToListAjax();" class="btn btn-sm btn-primary" target="modal" modal="lg"
                       href="/car/add">添加</a>
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">
                                        <div class="col-md-4">
                                            <div class="input-group">
                                                <span class="input-group-addon"><i class="fa fa-search"></i></span>
                                                <input type="text" class="form-control" id="bank" placeholder="根据银行搜索...">
                                            </div>
                                        </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="card" placeholder="根据卡号搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
                    </div>
                </div>
                <br>
                <table id="security_tab4" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>银行名称</th>
                        <th>卡号</th>
                        <th>存款总额</th>
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
    var security_tab4;
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
        security_tab4 = $('#security_tab4').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {
                "url": "/car/page",
                "type": "post",
                "data": function(d) {
                    //自定义查询参数
                    d.bank = $("#bank").val();
                    d.card = $("#card").val();
                }
            },
            "columns": [
                {"data": null},
                {"data": "name"},
                {"data": "card"},
                {"data": "balance"},
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
//            reloadTable(security_tab4, "#securityTime", "#securityPremise");
            securityReload();
        });
    });

    function securityReload() {
        reloadTable(security_tab4);
       // var date = $("#securityTime").val();
       // var search = $("#securityPremise").val();
       // var roleId = $("#roleId").val();
       // var param = {
       //     "add_date": date,
       //     "code": search,
       //     "type":roleId
       // };
       // security_tab4.settings()[0].ajax.data = param;
       // security_tab4.ajax.reload();
    }

    function securityToListAjax() {
        list_ajax = security_tab4;
        console.log(list_ajax);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>