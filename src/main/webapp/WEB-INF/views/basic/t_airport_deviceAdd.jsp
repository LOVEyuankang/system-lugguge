<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#cc').combobox({
			onSelect : function() {
				  var a=$('#cc').combobox('getValue');
	              if(a=="无线AP"){
				  $('#SSID')[0].disabled=false;
				  $('#WIFIpwd')[0].disabled=false;
					}else{
						 $('#SSID')[0].disabled=true;
						 $('#WIFIpwd')[0].disabled=true;
					}
			}
		});
		$('#t_airport_deviceAddForm').form({
			url : '${path }/t_airport_device/add',
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
		<form id="t_airport_deviceAddForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">设备编号</td>
					<td><input name="CODE" style="width:200px;" type="text"
						placeholder="请输入设备编号" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">设备类型</td>
				<td><select id="cc" class="easyui-combobox" name="TYPE"
						style="width:200px;" data-options="editable:false,panelHeight:'120px'">
						    <option value="航显工控机" selected="selected">航显工控机</option>
							<option value="识别工控机">识别工控机</option>
							<option value="天线">天线</option>
							<option value="数据采集器">数据采集器</option>
							<option value="无线AP">无线AP</option>
					</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">设备状态</td>
					<td><select id="aa" class="easyui-combobox" name="STATUS"
						style="width:200px;" panelHeight="75px;">
							<option value="正常">正常</option>
							<option value="故障">故障</option>
							<option value="停用">停用</option>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right;">设备位置</td>
					<td><input name="POSITION" style="width:200px;" type="text"
						placeholder="请输入设备位置" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">工控机IP</td>
					<td><input name="IP" style="width:200px;" type="text"
						placeholder="请输入工控机IP" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">无限AP专用(SSID)</td>
					<td><input id="SSID" name="SSID" style="width:200px;"
						type="text" placeholder="请输入无限AP专用(SSID)"
						class="easyui-validatebox span2" disabled="disabled" data-options="required:true"
						value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">无限AP专用(WIFIpwd)</td>
					<td><input id="WIFIpwd" name="WIFIpwd" style="width:200px;"
						type="text" placeholder="请输入无限AP专用(WIFIpwd)"
						class="easyui-validatebox span2" disabled="disabled" data-options="required:true"
						value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">是否支持WIFI</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name="IS_WIFI"
						type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="是" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="IS_WIFI" type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="否">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">是否支持移动网络</td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="IS_MOBILE" type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="是" checked="checked">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
						name="IS_MOBILE" type="radio" class="easyui-validatebox span2"
						data-options="required:true" value="否">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;否&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
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