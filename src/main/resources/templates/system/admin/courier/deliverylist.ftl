<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">精选投资方案</h3>

                <div class="box-tools pull-left">
                </div>
            </div>
            <div class="box-body">
                <div class="clearfix">

                </div>
                <table id="security_tab6" class="table table-bordered table-striped">
                    <thead>
                    <tr>
                    <tr>
                        <th>序号</th>
                        <th>名称</th>
                        <th>发行机构</th>
                        <th>风险程度</th>
                        <th>收益利率</th>
                        <th>产品期限</th>
                        <th>产品介绍</th>
                        <th>起购额度</th>
                        <th>产品类型</th>
                        <th>购买规则</th>
                        <th>赎回规则</th>
                        <th>收益规则</th>
                        <th>推荐程度</th>
                    </tr>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var security_tab6;
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
        security_tab6 = $('#security_tab6').DataTable({
            "dom": 'itflp',
            "processing": true,
            "searching": false,
            "serverSide": true, //启用服务器端分页
            "bInfo": false,
            "language": {"url": "adminlte/plugins/datatables/language.json"},
            "ajax": {
                "url": "/courier/page3",
                "type": "post",
                "data": {"status" :2}
            },
            "columns": [
                {"data": null},
                {"data": "name"},
                {"data": "bank"},
                {"data": "risk"},
                {"data": "interest"},
                {"data": "length"},
                {"data": "content"},
                {"data": "start"},
                {"data": "type"},
                {"data": "buyinfo"},
                {"data": "buyinfo2"},
                {"data": "buyinfo3"},
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
//            reloadTable(security_tab6, "#securityTime", "#securityPremise");
            securityReload();
        });
    });

    function securityReload() {
        reloadTable(security_tab6);
       // var date = $("#securityTime").val();
       // var search = $("#securityPremise").val();
       // var roleId = $("#roleId").val();
       // var param = {
       //     "add_date": date,
       //     "code": search,
       //     "type":roleId
       // };
       // security_tab6.settings()[0].ajax.data = param;
       // security_tab6.ajax.reload();
    }

    function securityToListAjax() {
        list_ajax = security_tab6;
        console.log(list_ajax);
    }
    function isNull(data) {
        return (data == "" || data == undefined || data == null) ? true : false;
    }
</script>