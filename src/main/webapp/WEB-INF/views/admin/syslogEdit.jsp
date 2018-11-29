<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	$(function() {

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
	       <form id="syslogEditForm" method="post">
			<table class="grid">
				<tr>
					<td style="text-align: right;">操作人:</td>
					<td><input name="id" type="hidden"  value="${sysLog.id}">${sysLog.loginName}</td>
				</tr>
				<tr>
					<td style="text-align: right;">ip地址:</td>
					<td>${sysLog.clientIp}</td>
				</tr>
				<tr>
					<td style="text-align: right;">操作命令:</td>
					<td>${sysLog.command}</td>
				</tr>
				<tr>
					<td style="text-align: right;">操作对象:</td>
					<td>${sysLog.operationobj}</td>
				</tr>
				<tr>
					<td style="text-align: right;">操作时间:</td>
					<td>${sysLog.createTime}</td>
				</tr>
				<tr>
					<td style="text-align: right;">操作详细内容:</td>
					<td><input class="easyui-textbox" data-options="multiline:true" value="${sysLog.optContent}" style="width:300px;height:180px" readonly="readonly"></td>
				</tr>
			</table>
	     </form>
	</div>
</div>