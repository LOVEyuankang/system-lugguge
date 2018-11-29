<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

    $(function() {                
        $('#postinfoAddForm').form({
            url : '${path }/postinfo/add',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');;//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为organization.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                }
            }
        });
        
    });
</script>
<div style="padding: 3px;">
    <form id="postinfoAddForm" method="post">
        <table class="grid">
            <tr>
                <td style="text-align: right;">部门编号</td>
                <td><input name="deptid" style="width: 300px;" type="text" value="${code}" class="easyui-validatebox" data-options="required:true" readonly="readonly" ></td>
             </tr>
            <tr>
                <td style="text-align: right;">岗位名称</td>
                <td><input name="postname" style="width: 300px;" type="text" placeholder="请输入岗位名称" class="easyui-validatebox" data-options="required:true" ></td>
      
            </tr>
            <tr>
                <td style="text-align: right;">备注</td>
                <td><input  name="postremark" style="width: 300px;"/></td>
            </tr>
        </table>
    </form>
</div>