<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">分配管理</h3>

                <div class="box-tools pull-left">
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">
                    <div class="col-md-4">
                        <div class="input-group date ">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" class="form-control pull-right" id="securityTime" placeholder="选择时间...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="securityPremise" placeholder="根据序号搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-search"></i></span>
                            <input type="text" class="form-control" id="roleId" placeholder="根据类型搜索...">
                        </div>
                    </div>
                    <div class="col-md-4">
                        <button type="submit" onclick="securityReload();" class="btn btn-primary">搜索</button>
                    </div>
                </div>
                <table id="security_tab2" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>单号</th>
                        <th>物品类型</th>
                        <th>物品信息</th>
                        <th>寄件人姓名</th>
                        <th>寄件人手机号</th>
                        <th>寄件人地址</th>
                        <th>收件人姓名</th>
                        <th>收件人手机号</th>
                        <th>收件人地址</th>
                        <th>下单日期</th>
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
    var security_tab2;
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
        security_tab2 = $('#security_tab2').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {
                "url": "/courier/page",
                "type": "post",
                "data": {"status" :1}
            },
            "columns": [
                {"data": null},
                {"data": "code"},
                {"data": "type"},
                {"data": "info"},
                {"data": "sender_name"},
                {"data": "sender_phone"},
                {"data": "sender_address"},
                {"data": "recipient_name"},
                {"data": "recipient_phone"},
                {"data": "recipient_address"},
                {"data": "add_date"},
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
                        btn = '<a class="btn btn-xs btn-primary" target="modal" modal="lg" href="/courier/view/' + data.id + '">查看</a> &nbsp;';
                            btn += '<a class="btn btn-xs btn-info" onclick="securityToListAjax();" data-title="分配" target="modal" modal="lg" href="/courier/allot/'+ data.id+ '">分配</a> &nbsp;'
                           + '<a class="btn btn-xs btn-info" onclick="securityToListAjax();" data-title="破损记录" target="modal" modal="lg" href="/courier/broken/'+ data.id+ '">破损</a> &nbsp;';
                        return btn;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

        $("#securitySeek").on("click", function () {
//            reloadTable(security_tab2, "#securityTime", "#securityPremise");
            securityReload();
        });
    });

    function securityReload() {
        reloadTable(security_tab2);
       // var date = $("#securityTime").val();
       // var search = $("#securityPremise").val();
       // var roleId = $("#roleId").val();
       // var param = {
       //     "add_date": date,
       //     "code": search,
       //     "type":roleId
       // };
       // security_tab2.settings()[0].ajax.data = param;
       // security_tab2.ajax.reload();
    }

    function securityToListAjax() {
        list_ajax = security_tab2;
        console.log(list_ajax);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>