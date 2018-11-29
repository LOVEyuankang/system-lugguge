<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<script type="text/javascript">
	$(function() {
		$('#t_airport_luggageAddForm').form({
			url : '${path }/t_airport_luggage/add',
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
	
	$("#op_name").combobox({
	 url:'${path}/t_airport_employee/findAll',
     //required:true,
	 valueField:'code',
	 textField:'name'
	 });
	
	$("#in_name").combobox({
	 url:'${path}/t_airport_employee/findAll',
	 valueField:'code',
	 textField:'name'
	 });
	
	$("#out_name").combobox({
	 url:'${path}/t_airport_employee/findAll',
	 valueField:'code',
	 textField:'name'
	 });
	 
	 
	$("#chuteno").combobox({
	 url:'${path}/t_airport_chute/findAll',
     //required:true,
	 valueField:'code',
	 textField:'code'
	 });
	 $("#trailercode").combobox({
	 url:'${path}/t_airport_trailer/findAll',
     //required:true,
	 valueField:'code',
	 textField:'code'
	 });
	 
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden;padding: 3px;">
		<form id="t_airport_luggageAddForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">航班日期</td>
					<td><input name="plandate" style="width:200px;"
					 type="text" class="easyui-datebox" 
					 data-options="required:true" value=""></td>
						
					<td style="text-align: right;">航空公司</td>
					<td><input name="aircorp" style="width:200px;" type="text"
						placeholder="请输入航空公司二字码" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">航班号</td>
					<td><input name="fltno" style="width:200px;" type="text"
						placeholder="请输入航班号" class="easyui-validatebox span2"
						data-options="required:true" value="">
					</td>
					<td style="text-align: right;">身份证号</td>
					<td><input name="percode" style="width:200px;" type="text"
						placeholder="请输入身份证号" class="easyui-validatebox span2"
						 value="">
					</td>
				</tr>
				<tr>
					<td style="text-align: right;">行李标签号</td>
					<td><input name="bgcode" style="width:200px;" type="text"
						placeholder="请输入行李标签号" class="easyui-validatebox span2"
						data-options="required:true" value=""></td>
					<td style="text-align: right;">行李类型</td>
					<td><select  class="easyui-combobox" name="type"
						style="width:200px;" panelHeight="120px;" data-options="required:true">
							<option value="值机行李">值机行李</option>
							<option value="大件行李">大件行李</option>
							<option value="贵宾行李">贵宾行李</option>
							<option value="临时增加">临时增加</option>
					</select></td>
				</tr>
				<tr>
					<td style="text-align: right;">行李属性</td>
					<td><select class="easyui-combobox" name="attr"
						style="width:200px;" panelHeight="120px;" data-options="required:true">
							<option value="国内">国内</option>
							<option value="国际">国际</option>
					</select></td>
					<td style="text-align: right;">重量</td>
					<td><input name="bgweight" style="width:200px;" type="number"
					 class="easyui-numberbox span2" /></td>
				</tr>
				<tr>
					<td style="text-align: right;">照片路径</td>
					<td><input name="bgphoto" style="width:200px;" type="text"
						placeholder="请输入照片路径" class="easyui-validatebox span2"
			            value=""></td>
					<td style="text-align: right;">行李状态</td>
					<td><select class="easyui-combobox" name="bgstatus"
						style="width:200px;" panelHeight="120px;" data-options="">
							<option value=""></option>
							<option value="一级读卡站">一级读卡站</option>
							<option value="二级读卡站">二级读卡站</option>
							<option value="三级安检">三级读安检</option>
							<option value="装车">装车</option>
							<option value="出库">出库</option>
							<option value="进舱">进舱</option>
							<option value="无法识别">无法识别</option>
							<option value="删除">已删除</option>
					</select></td>
				</tr>
					<tr>
					<td style="text-align: right;">拖车编号</td>
					<td><input id="trailercode" name="trailercode" style="width:200px;" type="text"></td>
					<td style="text-align: right;">操作员</td>
					<td><input id="op_name" name="personcode" style="width:200px;" type="text"/></td>
				</tr>
					<tr>
					<td style="text-align: right;">滑槽编号</td>
					<td><input id="chuteno" name="chutecode" style="width:200px;" type="text"></td>
					<td style="text-align: right;">下滑槽时间</td>
					<td><input name="chuteTime" style="width:200px;"
					 type="text" class="easyui-datetimebox" 
					  value=""></td>
				</tr>
					
					<tr>
					<td style="text-align: right;">装车时间</td>
					<td><input name="trailerTime" style="width:200px;"
					 type="text" class="easyui-datetimebox" 
					  value=""></td>
					<td style="text-align: right;">出库时间</td>
					<td><input name="outTime" style="width:200px;"
					 type="text" class="easyui-datetimebox" 
					  value=""></td>
				</tr>
					
					<tr>
					<td style="text-align: right;">进舱时间</td>
					<td><input name="inCabinTime" style="width:200px;"
					 type="text" class="easyui-datetimebox" 
					  value=""></td>
					<td style="text-align: right;">进舱操作员</td>
					<td><input id="in_name" name="inCabinEmployeeId" style="width:200px;" type="text"/></td>
				</tr>
					
					<tr>
					<td style="text-align: right;">进舱分区</td>
					<td><input name="cationArea" style="width:200px;" type="text"
						placeholder="请输入进舱分区" class="easyui-validatebox span2"
						 value=""></td>
					<td style="text-align: right;">出舱时间</td>
					<td><input name="outCabinTime" style="width:200px;"
					 type="text" class="easyui-datetimebox" 
					  value=""></td>
				</tr>
					
					<tr>
					<td style="text-align: right;">出舱操作员</td>
					<td><input id="out_name" name="outCabinEmployeeId" style="width:200px;" type="text"/></td>
					<td style="text-align: right;">行李级别</td>
					<td><input name="bglevel" style="width:200px;" type="text"
						placeholder="请输入行李级别" class="easyui-validatebox span2"
						 value=""></td>
				</tr>
					<tr>
					<td style="text-align: right;">值机柜台</td>
					<td><input name="chkdesk" style="width:200px;" type="text"
						placeholder="请输入值机柜台" class="easyui-validatebox span2"
						 value=""></td>
					<td style="text-align: right;">值机时间</td>
					<td><input name="inChkTime" style="width:200px;"
					 type="text" class="easyui-datetimebox" 
					  value=""></td>
				</tr>
				<tr>
					<td style="text-align: right;">备注</td>
					<td colspan="3"><input name="REMARTK" style="width:550px;height:80px;" type="text"
						placeholder="请输入备注" class="easyui-validatebox span2"
						 value=""></td>
				</tr>
			</table>
		</form>
	</div>
</div>