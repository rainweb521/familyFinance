<div class="row">
	<div class="col-md-12">
		<form id="securityAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">收入类型</label>
                    <select class="form-control" name="type" id="type" >
                        <option value="薪水">请选择</option>
                        <option value="薪水">薪水</option>
                        <option value="奖金">奖金</option>
                        <option value="投资">投资</option>
                        <option value="还款">还款</option>
                        <option value="彩票">彩票</option>
                        <option value="福利">福利</option>
                    </select>
<#--					<input type="text" class="form-control" name="type" required="required" id="type" placeholder="">-->
					<input type="hidden" class="form-control" name="category" value="收入" id="category" placeholder="">
				</div>
				<div class="form-group">
					<label id="passwordLabel">名称</label>
					<input type="text" class="form-control" name="title" required="required" id="title" placeholder="">
				</div>
				<div class="form-group">
					<label id="nickNameLabel">金额</label>
					<input type="text" class="form-control" name="price" required="required" id="price" placeholder="">
				</div>

                <div class="form-group">
                    <label id="nickNameLabel">时间</label>
                    <input type="date" class="form-control" name="add_date" required="required" id="add_date" placeholder="">
                </div>
                <div class="form-group">
                    <label id="nickNameLabel">备注</label>
                    <input type="text" class="form-control" name="remark" id="remark" placeholder="">
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
    
    if($("#price").val()==""){
        status = 0;
    }
    if($("#remark").val()==""){
        status = 0;
    }

    if(status == 0){
        $("#userNoLabel").prepend('<span class="errorClass" style="color:red">*内容不能为空</span><br class="errorClass"/>');
        return false;
	}else{
		$.ajax({
			url: '/courier/save',
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