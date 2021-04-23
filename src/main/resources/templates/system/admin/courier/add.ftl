<div class="row">
	<div class="col-md-12">
		<form id="securityAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">支出类型</label>
                    <select class="form-control" name="type" id="type" >
                        <option value="饮食">请选择</option>
                        <option value="饮食">饮食</option>
                        <option value="交通">交通</option>
                        <option value="娱乐">娱乐</option>
                        <option value="购物">购物</option>
                        <option value="医疗">医疗</option>
                        <option value="家居">家居</option>
                        <option value="生活">生活</option>
                        <option value="学习">学习</option>
                    </select>
<#--					<input type="text" class="form-control" name="type" required="required" id="type" placeholder="">-->
					<input type="hidden" class="form-control" name="category" value="支出" id="category" placeholder="">
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