<div class="row">
	<div class="col-md-12">
		<form id="securityAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">银行名称</label>
					<input type="text" class="form-control" name="name" required="required" id="name" placeholder="">
					<input type="hidden" class="form-control" name="status" value="2" id="status" placeholder="">
				</div>
				<div class="form-group">
					<label id="passwordLabel">贷款总额</label>
					<input type="text" class="form-control" name="balance" required="required" id="balance" placeholder="">
				</div>
                <div class="form-group">
                    <label id="passwordLabel">贷款年限(期数)</label>
                    <input type="text" class="form-control" name="card" required="required" id="card" placeholder="">
                </div>
                <div class="form-group">
                    <label id="passwordLabel">剩余还款期数</label>
                    <input type="text" class="form-control" name="scard" required="required" id="scard" placeholder="">
                </div>
                <div class="form-group">
                    <label id="passwordLabel">剩余贷款总额</label>
                    <input type="text" class="form-control" name="sbalance" required="required" id="sbalance" placeholder="">
                </div>
                <div class="form-group">
                    <label id="passwordLabel">每期还款</label>
                    <input type="text" class="form-control" name="month" required="required" id="month" placeholder="">
                </div>
				<div class="form-group">
					<label id="nickNameLabel">类别</label>
					<input type="text" class="form-control" name="type" required="required" id="type" placeholder="">
				</div>
                <div class="form-group">
                    <label id="nickNameLabel">备注</label>
                    <input type="text" class="form-control" name="remark" required="required" id="remark" placeholder="">
                </div>

			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function securitySave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
    if($("#name").val()==""){
        status = 0;
    }


    if(status == 0){
        $("#userNoLabel").prepend('<span class="errorClass" style="color:red">*内容不能为空</span><br class="errorClass"/>');
        return false;
	}else{
		$.ajax({
			url: '/car/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#securityAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#roleTime","#rolePremise");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>