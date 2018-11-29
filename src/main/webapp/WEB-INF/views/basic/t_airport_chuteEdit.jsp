<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
		$(function() {		
		$('#t_airport_chuteEditForm').form({
			url : '${path }/t_airport_chute/edit',
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
		<form id="t_airport_chuteEditForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">滑槽编号</td>
					<td><input name="ID"  type="hidden"  value="${t_airport_chute.ID}"><input name="CODE" style="width:200px;" type="text" placeholder="请输入滑槽编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute.CODE}"></td>
				</tr>
				<tr>
					<td style="text-align: right;">滑槽类型</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="TYPE" type="radio" class="easyui-validatebox span2" data-options="required:true" value="正常滑槽" <c:if test="${t_airport_chute.TYPE=='正常滑槽'}">checked=checked</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正常滑槽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="TYPE" type="radio" class="easyui-validatebox span2" data-options="required:true" value="垃圾滑槽" <c:if test="${t_airport_chute.TYPE=='垃圾滑槽'}">checked=checked</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;垃圾滑槽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: right;">滑槽状态</td>
					<td><select id="cc" class="easyui-combobox" name="STATUS"
						style="width:200px;" data-options="editable:false,panelHeight:'75px',value:'${t_airport_chute.STATUS}'">
							<option value="正常" selected="selected">正常</option>
							<option value="故障">故障</option>
							<option value="停用">停用</option>
					</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">滑槽属性</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="ATTR" type="radio" class="easyui-validatebox span2" data-options="required:true" value="国际" <c:if test="${t_airport_chute.ATTR=='国际'}">checked=checked</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="ATTR" type="radio" class="easyui-validatebox span2" data-options="required:true" value="国内" <c:if test="${t_airport_chute.ATTR=='国内'}">checked=checked</c:if>>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td style="text-align: right;">RFID标签号</td>
					<td><input name="RFID" style="width:200px;" type="text" placeholder="请输入RFID标签号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute.RFID}"></td>
				</tr>
					<tr>
					<td style="text-align: right;">备注</td>
					<td><input name="REMARK" style="width:200px;" type="text" placeholder="请输入备注" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_chute.REMARK}"></td>
				</tr>
			</table>
		</form>
	</div>
</div>