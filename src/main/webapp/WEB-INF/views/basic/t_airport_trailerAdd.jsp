<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#t_airport_trailerAddForm').form({
			url : '${path }/t_airport_trailer/add',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
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
		<form id="t_airport_trailerAddForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">拖车编号</td>
					<td><input name="CODE" style="width:200px;" type="text"
						placeholder="请输入拖车编号" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">拖车类型</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="TYPE"
						type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="头车" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;头车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="TYPE" type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="拖车">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;拖车&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">拖车状态</td>
					<td><select id="cc" class="easyui-combobox" name="STATUS"
						style="width:200px;" panelHeight="75px;">
							<option value="正常">正常</option>
							<option value="故障">故障</option>
							<option value="停用">停用</option>
					</select></td>
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