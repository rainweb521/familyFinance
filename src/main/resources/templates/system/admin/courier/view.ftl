<div class="row">
	<div class="box-body  no-padding">
		<table class="table table-striped">
			<tr>
				<td>单号：</td>
				<td style="width: 90%">${bean.code!}</td>
			</tr>
			<tr>
				<td>物品类型：</td>
				<td>${bean.type!}</td>
			</tr>
			<tr>
				<td>物品信息:</td>
				<td>
                    ${(bean.info)!}
				</td>
			</tr>
			<tr>
				<td>重量：</td>
				<td>
					${(bean.weight)!}
				</td>
			</tr>
            <tr>
				<td>寄件人姓名：</td>
				<td>
					${(bean.sender_name)!}
				</td>
			</tr>
            <tr>
				<td>寄件人电话：</td>
				<td>
					${(bean.sender_phone)!}
				</td>
			</tr>
            <tr>
				<td>寄件人地址：</td>
				<td>
					${(bean.sender_address)!}
				</td>
			</tr>
            <tr>
				<td>收件人姓名：</td>
				<td>
					${(bean.recipient_name)!}
				</td>
			</tr>
            <tr>
				<td>收件人电话：</td>
				<td>
					${(bean.recipient_phone)!}
				</td>
			</tr>
            <tr>
				<td>收件人地址：</td>
				<td>
					${(bean.recipient_address)!}
				</td>
			</tr>
            <tr>
				<td>费用：</td>
				<td>
					${(bean.price)!}
				</td>
			</tr>
            <tr>
				<td>状态：</td>
				<td>
					${(bean.status_str)!}
				</td>
			</tr>
            <tr>
				<td>备注：</td>
				<td>
					${(bean.remark)!}
				</td>
			</tr>

			<tr>
				<td>下单日期：</td>
				<td>${bean.add_date}</td>
			</tr>
			<tr>
                <#if bean.status==0>
                    <td>记录日期：</td>
                <#else >
                    <td>送达日期：</td>
                </#if>

				<#if bean.send_date??>
                    <td>${bean.send_date}</td>
				</#if>
			</tr>
		</table>
		<div class="box-footer">
			<div class="pull-right">
				<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
			</div>
      	</div>
	</div>
</div>