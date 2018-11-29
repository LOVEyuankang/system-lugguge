<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() { 
		$('#DEVICETYPE').combogrid({
			url : '${path }/t_airport_device/tree',
			idField : 'pid',
			textField : 'pid',
			panelHeight : 'auto',
			columns : [ [ {
				field : 'text',
				title : "设备编号",
				width : 99,
				sortable : true
			}, {
				field : 'pid',
				title : "设备类型",
				width : 99,
				sortable : true
			}, ] ],
			onSelect : function(rec) {
				var g = $('#DEVICETYPE').combogrid('grid'); // get datagrid object
				var r = g.datagrid('getSelected'); // get the selected row
				$('#DEVICECO')[0].value = r.text;
			}
		});
		$('#t_airport_chute_deviceAddForm').form({
			url : '${path }/t_airport_chute_device/add',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="t_airport_chute_deviceAddForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">滑槽编号</td>
					<td><input name="CHUTECODE"
						style="width:200px;" type="text" readonly="readonly"
						class="easyui-validatebox span2" value="${t_airport_chute.CODE}"></td>
				</tr>
				<tr>
					<td style="text-align: right;">设备类型</td>
					<td><input id="DEVICETYPE" name="DEVICETYPE"
						style="width:200px;" type="text" placeholder="请输入设备类型"
						 value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">设备编号</td>
					<td><input id="DEVICECO" name="DEVICECODE"
						style="width:200px;" type="text" placeholder="请输入设备编号"
						 value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">备注</td>
					<td><input name="REMARK" style="width:200px;" type="text"
						placeholder="请输入备注" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>