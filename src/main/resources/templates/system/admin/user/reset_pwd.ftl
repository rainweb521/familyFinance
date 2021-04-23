<div class="row">
	<div class="col-md-12">
		<form id="resetPasswordForm">
			<div class="modal-body">
				<div class="form-group">
					<label>账号</label>
                    <input type="text" class="form-control" value="${bean.username}" disabled>
                    <input id="id" name="id" hidden="hidden" value="${bean.id}">
				</div>
				<div class="form-group">
					<label id="passwordLabel">新密码</label>
					<input type="password" class="form-control" name="password" id="password" placeholder="输入密码...">
				</div>
				<div class="form-group">
					<label id="rePasswordLabel">重复新密码</label>
                    <input type="password" class="form-control" name="rePassword" id="rePassword" placeholder="输入密码...">
				</div>
                <div class="form-group">
                    <label id="notEqual"></label>
                </div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="resetPwd()"><i class="fa fa-save"></i>确认</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function resetPwd(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	var password = $("#password").val();
	var rePassword = $("#rePassword").val()
	if(password==""){
		$("#passwordLabel").prepend('<span class="errorClass" style="color:red">*密码不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if (rePassword==''){
        $("#rePasswordLabel").prepend('<span class="errorClass" style="color:red">*请重复新密码</span><br class="errorClass"/>');
        status = 0;
	}
	if (password != rePassword){
        $("#notEqual").prepend('<span class="errorClass" style="color:red">*输入的密码不一致</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/user/doResetPwd',
	        type: 'post',
	        dataType: 'text',
	        data: $("#resetPasswordForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax);
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>