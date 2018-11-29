<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#t_airport_chuteEditForm').form({
            url : '${path }/t_airport_warning_threshold/edit',
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
                    <td style="text-align: right;">预警阀值类型</td>
                    <td><input name="id"  type="hidden"  value="${t_airport_warning_threshold.id}">
                        <input name="warningthresholdtype" style="width:200px;" type="text" readonly="readonly" placeholder="请输入分拣转盘编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_warning_threshold.warningthresholdtype}"></td>
                </tr>
                <tr>
                    <td style="text-align: right;">预警阀值时间(分钟)</td>
                    <td><input name="unitssetup" style="width:200px;" type="text" readonly="readonly" placeholder="请输入分拣转盘编号" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_warning_threshold.unitssetup}"></td>
                </tr>
                <tr>
                    <td style="text-align: right;">备注</td>
                    <td><input name="remarks" style="width:200px;" type="text" placeholder="请输入备注" class="easyui-validatebox span2" data-options="required:true" value="${t_airport_warning_threshold.remarks}"></td>
                </tr>
            </table>
        </form>
    </div>
</div>