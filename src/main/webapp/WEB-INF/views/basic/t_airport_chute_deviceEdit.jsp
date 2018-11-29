<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
		$(function() {
// 		    $('#CHUTECODE').combogrid({
// 	            url: '${path }/t_airport_chute/tree',
// 	            idField: 'pid',
// 	            textField: 'pid',
// 	            panelHeight : 'auto',
// 	            columns: [[
// 	                {field:'pid',title:"滑槽编号",width:198,sortable:true},
// 	            ]],
// 	            onSelect: function(rec){
// 	                var g = $('#CHUTECODE').combogrid('grid');	// get datagrid object
// 	            	var r = g.datagrid('getSelected');	// get the selected row
// 	            	$('#CHUTECODE')[0].value = r.text;
// 	            }
// 	        });
	       $('#DEVICETYPE').combogrid({
	            url: '${path }/t_airport_device/tree',
	            idField: 'text',
	            textField: 'text',
	            panelHeight : 'auto',
	            columns: [[
	                {field:'pid',title:"设备编号",width:99,sortable:true},
	                {field:'text',title:"设备类型",width:99,sortable:true},
	            ]],
	            onSelect: function(rec){
	                var g = $('#DEVICETYPE').combogrid('grid');	// get datagrid object
	            	var r = g.datagrid('getSelected');	// get the selected row
	            	$('#DEVICECODE')[0].value = r.pid;
	            }
	        });
		$('#t_airport_chute_deviceEditForm').form({
			url : '${path }/t_airport_chute_device/edit',
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
		<form id="t_airport_chute_deviceEditForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">滑槽编号</td>
					<td><input name="ID"  type="hidden"  value="${t_airport_chute_device.ID}"><input name="CHUTECODE" style="width:200px;" type="text" placeholder="请输入滑槽编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute_device.CHUTECODE}"></td>
				</tr>
				<tr>
					<td style="text-align: right;">设备类型</td>
					<td><input id="DEVICETYPE" name="DEVICETYPE" style="width:200px;" type="text" placeholder="请输入设备类型" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute_device.DEVICETYPE}"></td>
				</tr>
				<tr>
					<td style="text-align: right;">设备编号</td>
					<td><input id="DEVICECODE" name="DEVICECODE" style="width:200px;" type="text" placeholder="请输入设备编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute_device.DEVICECODE}"></td>
				</tr>
					<tr>
					<td style="text-align: right;">备注</td>
					<td><input name="REMARK" style="width:200px;" type="text" placeholder="请输入备注" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute_device.REMARK}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>