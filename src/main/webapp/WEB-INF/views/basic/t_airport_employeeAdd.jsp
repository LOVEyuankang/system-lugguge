<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#t_airport_employeeAddForm').form({
			url : '${path }/t_airport_employee/add',
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
		<form id="t_airport_employeeAddForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">操作员编号</td>
					<td><input name="CODE" style="width:200px;" type="text"
						placeholder="请输入操作员编号" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
					<tr>
					<td style="text-align: right;">操作员姓名</td>
					<td><input name="NAME" style="width:200px;" type="text"
						placeholder="请输入操作员姓名" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
						<tr>
					<td style="text-align: right;">所属单位</td>
					<td><input name="CORPATION" style="width:200px;" type="text"
						placeholder="请输入所属单位" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
						<tr>
					<td style="text-align: right;">所属部门</td>
					<td><input name="DEPARTMENT" style="width:200px;" type="text"
						placeholder="请输入所属单位" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
						<tr>
					<td style="text-align: right;">所属岗位</td>
					<td><input name="STATION" style="width:200px;" type="text"
						placeholder="请输入所属岗位" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">状态</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="STATUS"
						type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="正常" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正常&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="STATUS" type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="请假">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请假&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
								<tr>
					<td style="text-align: right;">APP登录用户</td>
					<td><input name="LOGINUSR" style="width:200px;" type="text"
						placeholder="请输入APP登录用户" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
									<tr>
					<td style="text-align: right;">APP登录密码</td>
					<td><input name="LOGINPWD" style="width:200px;" type="text"
						placeholder="请输入APP登录密码" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">权限级别</td>
					<td><select id="cc" class="easyui-combobox" name="RIGHTLEVEL"
						style="width:200px;" panelHeight="75px;">
							<option value="操作员">操作员</option>
							<option value="行李员">行李员</option>
							<option value="管理员">管理员</option>
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