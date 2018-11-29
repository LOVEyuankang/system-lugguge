<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#t_airport_chuteEditForm').form({
            url : '${path }/t_airport_check_in_counters/edit',
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
                    <td style="text-align: right;">值机岛编号</td>
                    <td><input name="id"  type="hidden"  value="${t_airport_check_in_counters.id}">
                        <input name="checkinid" style="width:200px;" type="text" readonly="readonly" placeholder="请输入值机岛编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_check_in_counters.checkinid}"></td>
                </tr>
                <tr>
                    <td style="text-align: right;">值机柜台编号</td>
                    <td><input name="checkincountersid" style="width:200px;" type="text" readonly="readonly" placeholder="请输入值机柜台编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_check_in_counters.checkincountersid}"></td>
                </tr>
                <tr>
                    <td style="text-align: right;">柜台状态</td>
                    <td><select id="cc" class="easyui-combobox" name="statusbar" style="width:200px;" data-options="editable:false,panelHeight:'75px',value:'${t_airport_check_in_counters.statusbar}'">
                        <option value="可用" selected="selected">可用</option>
                        <option value="不可用">不可用</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">柜台属性</td>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="checkinproperties" type="radio" class="easyui-validatebox span2" data-options="required:true" value="国内" <c:if test="${t_airport_check_in_counters.checkinproperties=='国内'}">checked=checked</c:if>>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input name="checkinproperties" type="radio" class="easyui-validatebox span2" data-options="required:true" value="国际" <c:if test="${t_airport_check_in_counters.checkinproperties=='国际'}">checked=checked</c:if>>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;国际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td style="text-align: right;">备注</td>
                    <td><input name="remarks" style="width:200px;" type="text" placeholder="请输入备注" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_check_in_counters.remarks}"></td>
                </tr>
            </table>
        </form>
    </div>
</div>