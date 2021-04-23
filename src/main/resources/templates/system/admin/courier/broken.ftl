<div class="row">
    <div class="col-md-12">
        <form id="securityEditForm">
            <input type="hidden" id="id" name="id" value="${bean.id}">
            <div class="box-body">
                <div class="form-group">
                    <label id="userNoLabel">物品类型</label>
                    <input type="text" disabled="disabled" class="form-control" name="type" value="${bean.type!}" id="type" placeholder="">
                </div>
                <div class="form-group">
                    <label id="passwordLabel">物品信息</label>
                    <input type="text" disabled="disabled" class="form-control" name="info" value="${bean.info!}" id="info" placeholder="">
                </div>

                <div class="form-group">
                    <label id="nickNameLabel">备注：请输入破损原因及描述</label>
                    <input type="text" class="form-control" name="remark" value="${bean.remark!}" id="remark" placeholder="">
                </div>

            </div>
            <div class="box-footer">
                <div class="pull-right">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i
                            class="fa fa-close"></i>关闭
                    </button>
                    <button onclick="securityUpdateUser();" type="button" class="btn btn-primary btn-sm"><i
                            class="fa fa-paste"></i>更新
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    function securityUpdateUser() {
//        debugger;
        $.ajax({
            url: '/courier/addbroken',
            type: 'post',
            dataType: 'text',
            data: $("#securityEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功", "success");
                    reloadTable(list_ajax);
                } else {
                    alertMsg("添加失败:" + json.msg, "success");
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