<div class="row">
    <div class="col-md-12">
        <form id="securityEditForm">
            <input type="hidden" id="id" name="id" value="${bean.id}">
            <div class="box-body">

                <div class="form-group">
                    <label id="userNoLabel">银行名称</label>
                    <input type="text" class="form-control" value="${bean.name!}"  name="name" required="required" id="name" placeholder="">
                    <input type="hidden" class="form-control" name="status" value="1" id="status" placeholder="">

                </div>
                <div class="form-group">
                    <label id="passwordLabel">卡号</label>
                    <input type="text" class="form-control" value="${bean.card!}"  name="card" required="required" id="card" placeholder="">
                </div>
                <div class="form-group">
                    <label id="passwordLabel">存款总额</label>
                    <input type="text" class="form-control" value="${bean.balance!}"  name="balance" required="required" id="balance" placeholder="">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">类别</label>
                    <input type="text" class="form-control" value="${bean.type!}"  name="type" required="required" id="type" placeholder="">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">备注</label>
                    <input type="text" class="form-control" value="${bean.remark!}"  name="remark" required="required" id="remark" placeholder="">
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
            url: '/car/update',
            type: 'post',
            dataType: 'text',
            data: $("#securityEditForm").serialize(),
            success: function (data) {
                var json = JSON.parse(data);
                if (json.status) {
                    $("#lgModal").modal('hide');
                    alertMsg("更新成功", "success");
                    reloadTable(list_ajax);
                } else {
                    alertMsg("更新失败:" + json.msg, "success");
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