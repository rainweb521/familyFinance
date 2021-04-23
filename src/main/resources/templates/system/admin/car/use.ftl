<div class="row">
    <form id="dispatcherRoleForma">
        <div class="box-body  no-padding">
            <input name="id" hidden value="${id}">
            <table class="table table-striped">
                <tr>
                    <td>使用日期：</td>
                    <td style="width: 90%">
                        <input type="date" class="form-control" value=""  name="use_date" required="required" id="use_date" placeholder="">
                    </td>
                </tr>
                <tr>
                    <td>备注：</td>
                    <td style="width: 90%">
                        <input type="text" class="form-control" value=""  name="remark" required="required" id="remark" placeholder="">
                    </td>
                </tr>
                <tr>
                    <td>快递员：</td>
                    <td>
                    <#list users as user>
                        <label>
                            <input type="radio" name="userid" class="flat-red" value="${user.id}">
                                  ${user.name}
                        </label>
                    </#list>
                    </td>
                </tr>
            </table>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="securitySetRole()" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript">
    function securitySetRole(){
        $.ajax({
            url: '/car/adduse',
            type: 'post',
            dataType: 'text',
            data: $("#dispatcherRoleForma").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("登记成功", "success");
                    reloadTable(list_ajax);
                } else {
                    alertMsg("登记失败:" + json.msg, "success");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
            }
        });
    }
</script>