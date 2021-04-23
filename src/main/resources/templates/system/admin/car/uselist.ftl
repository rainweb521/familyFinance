<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">用车信息</h3>

                <div class="box-tools pull-left">
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">

                </div>
                <table id="security_tab5" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>快递员姓名</th>
                        <th>车辆名称</th>
                        <th>车辆牌号</th>
                        <th>使用日期</th>
                        <th>备注</th>
                    </tr>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var security_tab5;
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
        security_tab5 = $('#security_tab5').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {
                "url": "/car/usepage",
                "type": "post",
                "data": function(d) {
                    //自定义查询参数
                    d.roleId = $("#roleId").val();
                }
            },
            "columns": [
                {"data": null},
                {"data": "name"},
                {"data": "car_name"},
                {"data": "car_code"},
                {"data": "use_date"},
                {"data": "remark"}
            ],
            "columnDefs": [
                {
                    targets: 0,
                    data: null,
                    render: function (data) {
                        No = No + 1;
                        return No;
                    }
                }]
        }).on('preXhr.dt', function (e, settings, data) {
            No = 0;
        });

        $("#securitySeek").on("click", function () {
//            reloadTable(security_tab5, "#securityTime", "#securityPremise");
            securityReload();
        });
    });

    function securityReload() {
        reloadTable(security_tab5);
       // var date = $("#securityTime").val();
       // var search = $("#securityPremise").val();
       // var roleId = $("#roleId").val();
       // var param = {
       //     "add_date": date,
       //     "code": search,
       //     "type":roleId
       // };
       // security_tab5.settings()[0].ajax.data = param;
       // security_tab5.ajax.reload();
    }

    function securityToListAjax() {
        list_ajax = security_tab5;
        console.log(list_ajax);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>