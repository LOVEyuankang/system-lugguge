<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	$(function() {
		$('#placeAddForm').form({
			url : '${path }/place/add',
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
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
		<form id="placeAddForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">三字码</td>
					<td><input name="ESNAME" style="width:200px;" type="text" placeholder="请输入三字码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">四字码</td>
					<td><input name="ICAO" style="width:200px;" type="text" placeholder="请输入四字码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">地名英文全称</td>
					<td><input name="ELNAME" style="width:200px;" type="text" placeholder="请输入地名英文全称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">中文名称</td>
					<td><input name="CSNAME" style="width:200px;" type="text" placeholder="请输入中文名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">航管名称</td>
					<td><input name="CLNAME" style="width:200px;" type="text" placeholder="请输入航管名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">中文简称</td>
					<td><input name="CSSNAME" style="width:200px;" type="text" placeholder="请输入中文简称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">国际标志(是/否)</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="GJBZ" type="radio" class="easyui-validatebox span2" data-options="required:true" value="是" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="GJBZ" type="radio" class="easyui-validatebox span2" data-options="required:true" value="否">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</table>
		</form>
	</div>
</div>